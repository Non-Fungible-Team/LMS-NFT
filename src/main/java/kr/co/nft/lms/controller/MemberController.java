package kr.co.nft.lms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.nft.lms.service.MemberService;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Manager;
import kr.co.nft.lms.vo.Member;
import kr.co.nft.lms.vo.MemberPhoto;
import kr.co.nft.lms.vo.MemberUploadPhoto;
import kr.co.nft.lms.vo.Student;
import kr.co.nft.lms.vo.Teacher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	
	@Autowired MemberService memberService;
	
	// 학생 휴면 처리 ( 삭제 대체 ) 
	@PostMapping("/all/freezeStudent")
	public String removeStudent(HttpSession session
								, Member member) {
		
		// 매개 변수 내용 확인 
		log.debug(A.Z+"[MemberController.removeStudent.param] member : "+member+A.R);
		
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		log.debug(A.Z+"[MemberController.removeStudent.param] loginMember : "+loginMember+A.R);
		
		// int rowOfStudentTbl = memberService.freezeStudentOfStudentTbl(member);
		// log.debug(A.Z+"[MemberController.removeStudent] rowOfStudentTbl : "+rowOfStudentTbl+A.R);
		
		// Member 테이블에 변경된 내용 확인 
		int rowOfMemberTbl = memberService.freezeStudentOfMemberTbl(member);
		log.debug(A.Z+"[MemberController.removeStudent] rowOfMemberTbl : "+rowOfMemberTbl+A.R);
		
		// 액터별 레벨 이력 테이블 변경 여부 확인 
		int rowOfLevelHistoryTbl = memberService.addLevelHistoryOfStudentRecord(loginMember);
		log.debug(A.Z+"[MemberController.removeStudent] rowOfLevelHistoryTbl : "+rowOfLevelHistoryTbl+A.R);
		
		// GetMapping(/login) 
		return "redirect:/login";
	}
	
	// 학생 휴면 처리 ( 삭제 대체 ) 
	@GetMapping("/all/freezeStudent")
	public String removeStudent(HttpSession session) {
		
		// 매개 변수 내용 확인 
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		log.debug(A.Z+"[MemberController.removeStudent.param] loginMember : "+loginMember+A.R);
		
		// 계정 정보 없으면 로그인 실패 
		if(loginMember == null) {
			log.debug(A.Z+"MemberController.removeStudent : "+"로그인 실패"+A.R);
			// member/memberLogin.jsp 
			return "/member/memberLogin";
		}
		
		// 세션에 로그인 사용자 정보와 과목 번호 저장 
		session.setAttribute("loginMember", loginMember);
		session.setAttribute("sessionLectureNo", "");
		
		// 해당하는 경로의 뷰 페이지로 이동 
		return "/member/freezeStudent";
	}
	
	// 학생 정보 수정 
	@PostMapping("/all/modifyStudent")
	public String modifyStudent(HttpSession session
								, HttpServletRequest request
								, Member member
								, Student student
								, MemberPhoto memberPhoto
								, MemberUploadPhoto memberUploadPhoto) {
		
		// 매개변수가 잘 들어왔는지 확인 
		// 학생 정보 수정시 MEMBER 테이블에 수정해야 할 내용 받기 
		log.debug(A.Z+"[MemberController.modifyStudent.param] member : "+member+A.R);
		// 학생 정보 수정시 STUDENT 테이블에 수정해야 할 내용 받기 
		log.debug(A.Z+"[MemberController.modifyStudent.param] student : "+student+A.R);
		log.debug(A.Z+"[MemberController.modifyStudent.param] memberPhoto : "+memberPhoto+A.R);
		// // 학생 정보 수정시 MEMBERPHOTO 테이블에 수정해야 할 내용 받기
		log.debug(A.Z+"[MemberController.modifyStudent.param] memberUploadPhoto : "+memberUploadPhoto+A.R);
		
		// 사진을 업로드하면 저장되는 경로 설정 
		String path = request.getServletContext().getRealPath("/memberPhoto/");
		log.debug(A.Z+"[MemberController.modifyStudent.param] path : "+path+A.R);
		
		// 세션에서 아이디와 레벨은 계속 가져온다 
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		
		// modifyStudent.jsp 페이지에서 파일 받은 내용 확인 
		MultipartFile studentPhoto = memberUploadPhoto.getMemberPhotoOne();
		log.debug(A.Z+"[MemberController.modifyStudent.param] studentPhoto : "+studentPhoto+A.R);
		
		// 파일이 들어왔고 파일 사이즈가 0보다 크면 
		if(studentPhoto != null && studentPhoto.getSize() > 0) {
			// 파일이 들어온 내용을 확인 
			log.debug(A.Z+"[MemberController.modifyStudent] studentPhoto : "+studentPhoto+A.R);
		}
		
		// 사용자 사진 업로드 
		memberService.addMemberPhoto(member, memberPhoto, memberUploadPhoto, path);
		
		// 학생 정보가 바뀌었는지 확인 
		// 멤버 테이블에 변경 내용이 반영되었는지 확인 
	    int rowOfMember = memberService.modifyStudent(member);
	    log.debug(A.Z+"[MemberController.modifyStudent] rowOfMember : "+rowOfMember+A.R);
	    // 학생 테이블에 변경 내용이 반영되었는지 확인 
	    int rowOfStudent = memberService.modifyStudent(student);
	    log.debug(A.Z+"[MemberController.modifyStudent] rowOfStudent : "+rowOfStudent+A.R);
		
	    // redirect 사용하면 Controller 상에 매핑된 주소를 찾아간다  
	    return "redirect:/all/getStudentOne";
	}
	
	// 학생 정보 수정 
	@GetMapping("/all/modifyStudent")
	public String modifyStudent(HttpSession session
								, @RequestParam(value="memberId") String memberId
								, Model model) {
		// 매개 변수 확인 
		log.debug(A.Z+"[MemberController.modifyStudent.param] memberId : "+memberId+A.R);
		// 세션 매개 변수 확인 
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		log.debug(A.Z+"[MemberController.modifyStudent] loginMember : "+loginMember+A.R);
		
		// 계정 정보 없으면 로그인 실패 
		if(memberId == null) {
			log.debug(A.Z+"MemberController.getStudentOne : "+"로그인 실패"+A.R);
			// member/memberLogin.jsp 
			return "/member/memberLogin";
		}

		// 학생의 상세 정보를 가져온다 
		// VO 두개를 합쳐야 한 학생의 정보가 나온다 
		Student getStudentOneByStudentVo = memberService.getStudentOneReturnStudentVo(loginMember);
		log.debug(A.Z+"[MemberController.modifyStudent] getStudentOneByStudentVo : "+getStudentOneByStudentVo+A.R);
		Member getStudentOneByMemberVo = memberService.getStudentOneReturnMemberVo(loginMember);
		log.debug(A.Z+"[MemberController.modifyStudent] getStudentOneByMemberVo : "+getStudentOneByMemberVo+A.R);
		
		// 이동하려는 뷰 페이지에 해당하는 데이터들을 보낸다 
		model.addAttribute("getStudentOneByStudentVo", getStudentOneByStudentVo);
		model.addAttribute("getStudentOneByMemberVo", getStudentOneByMemberVo);
		
		// 세션에 로그인 사용자 정보와 과목 번호를 보낸다 
		session.setAttribute("loginMember", loginMember);
		session.setAttribute("sessionLectureNo", 0);
		
		// 해당하는 뷰 페이지로 이동 
		return "/member/modifyStudent";
	}
	
	// 강사 상세보기 
	// 세션 받음 
	@GetMapping("/all/getTeacherOne")
	public String getTeacherOne(HttpSession session
								, Model model) {
		// 세션에서 받아온 참조 변수 Member의 ID, LEVEL 필드를 받는다 
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		log.debug(A.Z+"[MemberController.getTeacherOne.param] loginMember : "+loginMember+A.R);

		// 계정 정보 없으면 로그인 실패 
		if(loginMember == null) {
			log.debug(A.Z+"MemberController.getTeacherOne : "+"로그인 실패"+A.R);
			// member/memberLogin.jsp 
			return "/member/memberLogin";
		}
		
		// 강사 개인의 정보를 띄우기 위해 Member, Teacher, MemberPhoto 테이블에서 거의 모든 필드들을 가져온다 
		Teacher getTeacherOneByTeacherVo = memberService.getTeacherOneReturnTeacherVo(loginMember);
		log.debug(A.Z+"[MemberController.getTeacherOne] getTeacherOneByTeacherVo : "+getTeacherOneByTeacherVo+A.R);
		
		Member getTeacherOneByMemberVo = memberService.getTeacherOneReturnMemberVo(loginMember);
		log.debug(A.Z+"[MemberController.getTeacherOne] getTeacherOneByMemberVo : "+getTeacherOneByMemberVo+A.R);
		
		MemberPhoto getMemberPhoto = memberService.getMemberPhoto(loginMember);
		log.debug(A.Z+"[MemberController.getTeacherOne] getMemberPhoto : "+getMemberPhoto+A.R);
		
		// 뷰 페이지 getTeacherOne.jsp 페이지로 참조 변수 인스턴스들을 넘긴다. 
		model.addAttribute("getTeacherOneByTeacherVo", getTeacherOneByTeacherVo);
		model.addAttribute("getTeacherOneByMemberVo", getTeacherOneByMemberVo);
		model.addAttribute("getMemberPhoto", getMemberPhoto);
		
		// 세션으로 로그인한 유저의 정보와 과목 번호를 넘긴다 
		session.setAttribute("loginMember", loginMember);
		session.setAttribute("sessionLectureNo", 0);
		
		return "/member/getTeacherOne";
	}
	
	// 학생 상세보기
	// 세션 받는다 
	@GetMapping("/all/getStudentOne")
	public String getStudentOne(HttpSession session
								, Model model) {
		// 세션에서 받아온 참조 변수 Member의 ID, LEVEL 필드를 받는다 
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		log.debug(A.Z+"[MemberController.getStudentOne.param] loginMember : "+loginMember+A.R);

		// 계정 정보 없으면 로그인 실패 
		if(loginMember == null) {
			log.debug(A.Z+"MemberController.getStudentOne : "+"로그인 실패"+A.R);
			// member/memberLogin.jsp 
			return "/member/memberLogin";
		}
		
		// 학생 개인의 정보를 띄우기 위해 Member, Student, MemberPhoto 테이블에서 거의 모든 필드들을 가져온다 
		Student getStudentOneByStudentVo = memberService.getStudentOneReturnStudentVo(loginMember);
		log.debug(A.Z+"[MemberController.getStudentOne] getStudentOneByStudentVo : "+getStudentOneByStudentVo+A.R);
		
		Member getStudentOneByMemberVo = memberService.getStudentOneReturnMemberVo(loginMember);
		log.debug(A.Z+"[MemberController.getStudentOne] getStudentOneByMemberVo : "+getStudentOneByMemberVo+A.R);
		
		MemberPhoto getMemberPhoto = memberService.getMemberPhoto(loginMember);
		log.debug(A.Z+"[MemberController.getStudentOne] getMemberPhoto : "+getMemberPhoto+A.R);
		
		// 뷰 페이지 getStudentOne.jsp 페이지로 참조 변수 인스턴스들을 넘긴다. 
		model.addAttribute("getStudentOneByStudentVo", getStudentOneByStudentVo);
		model.addAttribute("getStudentOneByMemberVo", getStudentOneByMemberVo);
		model.addAttribute("getMemberPhoto", getMemberPhoto);
		
		// 세션으로 로그인한 유저의 정보와 과목 번호를 넘긴다 
		session.setAttribute("loginMember", loginMember);
		session.setAttribute("sessionLectureNo", 0);
		
		return "/member/getStudentOne";
	}
	
	// 운영자 회원가입 
	@PostMapping("/addManager")
	public String addManager(Member member
							,Manager manager) {
		// 매개 변수 내용 확인 
		log.debug(A.Z+"[MemberController.addManager.param] member : "+member+A.R);
		log.debug(A.Z+"[MemberController.addManager.param] teacher : "+manager+A.R);
		
		// 운영자 한명의 정보를 저장하기 위해서 두개의 테이블 Member, Manager가 필요 
		int rowOfMember = memberService.addManager(member);
		log.debug(A.Z+"[MemberController.addManager] rowOfMember : "+rowOfMember+A.R);
		int rowOfManager = memberService.addManager(manager);
		log.debug(A.Z+"[MemberController.addManager] rowOfManager : "+rowOfManager+A.R);
		
		// 해당하는 뷰 페이지로 이동 
		return "/member/memberLogin";
	}
	
	// 강사 회원가입
	@PostMapping("/addTeacher")
	public String addTeacher(Member member
							,Teacher teacher) {
		// 매개 변수 내용 확인 
		log.debug(A.Z+"[MemberController.addTeacher.param] member : "+member+A.R);
		log.debug(A.Z+"[MemberController.addTeacher.param] teacher : "+teacher+A.R);
		
		// 한 강사의 정보를 저장하기 위해서 두개의 테이블 Member, Teacher가 필요 
		int rowOfMember = memberService.addTeacher(member);
		log.debug(A.Z+"[MemberController.addTeacher] rowOfMember : "+rowOfMember+A.R);
		int rowOfTeacher = memberService.addTeacher(teacher);
		log.debug(A.Z+"[MemberController.addTeacher] rowOfTeacher : "+rowOfTeacher+A.R);
		
		// 해당하는 뷰 페이지로 이동 
		return "/member/memberLogin";
	}
	
	// 학생 회원가입 
	@PostMapping("/addStudent")
	public String addStudent(Member member
							,Student student) {
		// 매개 변수 내용 확인 
		log.debug(A.Z+"[MemberController.addStudent.param] member : "+member+A.R);
		log.debug(A.Z+"[MemberController.addStudent.param] student : "+student+A.R);
		
		// 한 학생 정보를 저장하기 위해서 두개의 테이블 Member, Student가 필요 
		int rowOfMember = memberService.addStudent(member);
		log.debug(A.Z+"[MemberController.addStudent] rowOfMember : "+rowOfMember+A.R);
		int rowOfStudent = memberService.addStudent(student);
		log.debug(A.Z+"[MemberController.addStudent] rowOfStudent : "+rowOfStudent+A.R);
		
		// 해당하는 뷰 페이지로 이동 
		return "/member/memberLogin";
	}
	
	// 운영자 회원가입
	@GetMapping("/addManager") 
	// `member_level` 필드 값 받기 위해 파라미터로 Member VO 넣음 
	// `memberLevel` 데이터 잘 받으면 VO로 안받아도 상관 없을듯 
	public String addManager(Model model
							, Member member
							, @RequestParam(value="memberLevel") int memberLevel) {
		
		// 매개 변수에 담긴 내용 확인 
		log.debug(A.Z+"[MemberController.addManager.param] model : "+model+A.R);
		log.debug(A.Z+"[MemberController.addManager.param] member : "+member+A.R);
		log.debug(A.Z+"[MemberController.addManager.param] memberLevel : "+memberLevel+A.R);
		
		// 뷰 페이지에 사용자 레벨 넘긴다 
		model.addAttribute("memberLevel", memberLevel);
		
		// 운영자 회원 가입 페이지 양식으로 이동 
		return "/member/addManager";
	}
	
	// 강사 회원가입 
	@GetMapping("/addTeacher") 
	// `member_level` 필드 값 받기 위해 파라미터로 Member VO 넣음 
	// `memberLevel` 데이터 잘 받으면 VO로 안받아도 상관 없을듯 
	public String addTeacher(Model model
							, Member member
							, @RequestParam(value="memberLevel") int memberLevel) {
		
		// 매개 변수에 담긴 내용 확인 
		log.debug(A.Z+"[MemberController.addTeacher.param] model : "+model+A.R);
		log.debug(A.Z+"[MemberController.addTeacher.param] member : "+member+A.R);
		log.debug(A.Z+"[MemberController.addTeacher.param] memberLevel : "+memberLevel+A.R);
		
		// 뷰 페이지에 사용자 레벨 넘긴다 
		model.addAttribute("memberLevel", memberLevel);
		
		// 강사 회원 가입 페이지 양식으로 이동 
		return "/member/addTeacher";
	}
	
	// 학생 회원가입 
	@GetMapping("/addStudent") 
	// `member_level` 필드 값 받기 위해 파라미터로 Member VO 넣음 
	// `memberLevel` 데이터 잘 받으면 VO로 안받아도 상관 없을듯 
	public String addStudent(Model model
							, Member member
							, @RequestParam(value="memberLevel") int memberLevel) {
		
		// 매개 변수에 담긴 내용 확인 
		log.debug(A.Z+"[MemberController.addStudent.param] model : "+model+A.R);
		log.debug(A.Z+"[MemberController.addStudent.param] member : "+member+A.R);
		log.debug(A.Z+"[MemberController.addStudent.param] memberLevel : "+memberLevel+A.R);
		
		// 뷰 페이지에 사용자 레벨 넘긴다 
		model.addAttribute("memberLevel", memberLevel);
		
		// 학생 회원 가입 페이지 양식으로 이동 
		return "/member/addStudent";
	}
	
	
	// 로그아웃 
	@GetMapping("/all/logout")
	public String logout(HttpSession session) {
		// 세션 갱신 
		session.invalidate();
		
		// GetMapping 사용 
		return "redirect:/login";
	}
	
	// 로그인 액션 
		@PostMapping("/login")
		public String login(HttpSession session
							, Member member) {
			// 매개 변수 확인 
			log.debug(A.Z+"MemberController.login.param : member : "+member+A.R);
			
			Member sessionLoginMember = memberService.getMemberOne(member);
			log.debug(A.Z+"MemberController.login.sessionLoginMember : "+sessionLoginMember+A.R);
				
			// 계정 정보 없으면 로그인 실패 
			if(sessionLoginMember == null) {
				log.debug(A.Z+"MemberController.login : "+"로그인 실패"+A.R);
				// member/memberLogin.jsp 
				return "/member/memberLogin";
			}

			// 로그인 성공 
			session.setAttribute("sessionLoginMember", sessionLoginMember);
			session.setAttribute("sessionLectureNo", 0);
			
			// 로그인 성공하면 홈 화면으로 이동 
			return "redirect:/all/home";
		}
	
	// 로그인 폼 
	@GetMapping("/login")
	public String login(HttpSession session) {
		// 세션에 저장된 로그인 사용자 정보가 있다면 홈 화면으로 이동 
		if(session.getAttribute("sessionLoginMember") != null) {
			return "redirect:/all/home";
		}
		
		// 그렇지 않다면 다시 로그인 페이지로 이동 
		return "/member/memberLogin";
	}

}
