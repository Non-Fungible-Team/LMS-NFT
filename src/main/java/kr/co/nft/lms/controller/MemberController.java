package kr.co.nft.lms.controller;

import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.nft.lms.dto.AddManager;
import kr.co.nft.lms.dto.AddStudent;
import kr.co.nft.lms.dto.AddTeacher;
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

	// ------------------ 사용자 목록 뽑기 ( 운영자가 사용 ) ------------------ //
	
	// 강사 목록 뽑기 
	@GetMapping("/manager/getTeacherByPage")
	public String getTeacherByPage(HttpSession session
									, Model model
									, @RequestParam(name="currentPage", defaultValue = "1") int currentPage
									, @RequestParam(name="rowPerPage", defaultValue = "10") int rowPerPage) {
		// 매개 변수에 담긴 내용 확인 
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		log.debug(A.Z+"[MemberController.getTeacherByPage.param] loginMember : "+loginMember+A.R);
		log.debug(A.Z+"[MemberController.getTeacherByPage.param] model : "+model+A.R);
		log.debug(A.Z+"[MemberController.getTeacherByPage.param] currentPage : "+currentPage+A.R);
		log.debug(A.Z+"[MemberController.getTeacherByPage.param] rowPerPage : "+rowPerPage+A.R);
		
		// 계정 정보 없으면 로그인 실패 
		if(loginMember == null) {
			log.debug(A.Z+"[MemberController.getTeacherByPage]  : "+"로그인 실패"+A.R);
			// 로그인 페이지로 이동
			return "/member/memberLogin";
		} else if(loginMember.getMemberLevel() < 6) {
			log.debug(A.Z+"[MemberController.getTeacherByPage]  : "+"관리자 아이디로 접근하세요"+A.R);
			// 로그인 페이지로 이동
			return "/member/memberLogin";
		}
		
		Map<String, Object> map = memberService.getTeacherByPage(currentPage, rowPerPage);
		log.debug(A.Z+"[MemberController.getTeacherByPage] map : "+map+A.R);
		
		// 뷰 페이지에 사용자 레벨 넘긴다. 운영자만 이용 가능 
		
		model.addAttribute("teacherList", map.get("teacherList"));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", map.get("currentPage"));
		
		// 세션으로 로그인한 유저의 아이디와 과목 번호 ( 사용이 되야 할 곳이 생길 수 있음. 계속 넘기기로 협의 ) 
		session.setAttribute("loginMember", loginMember);
		session.setAttribute("sessionLectureNo", 0);
		
		// 학생 목록 확인하는 페이지로 이동 
		return "/member/getTeacherByPage";
	}
	
	// 학생 목록 뽑기 
	@GetMapping("/manager/getStudentByPage")
	public String getStudentByPage(HttpSession session
									, Model model
									
									, @RequestParam(name="currentPage", defaultValue = "1") int currentPage
									, @RequestParam(name="rowPerPage", defaultValue = "10" ) int rowPerPage) {
		// 매개 변수에 담긴 내용 확인 
		
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		log.debug(A.Z+"[MemberController.getStudentByPage.param] loginMember : "+loginMember+A.R);
		log.debug(A.Z+"[MemberController.getStudentByPage.param] model : "+model+A.R);
		log.debug(A.Z+"[MemberController.getStudentByPage.param] currentPage : "+currentPage+A.R);
		log.debug(A.Z+"[MemberController.getStudentByPage.param] rowPerPage : "+rowPerPage+A.R);
		
		// 계정 정보 없으면 로그인 실패 
		if(loginMember == null) {
			log.debug(A.Z+"[MemberController.getStudentByPage]  : "+"로그인 실패"+A.R);
			// 로그인 페이지로 이동
			return "/member/memberLogin";
		} else if(loginMember.getMemberLevel() < 6) {
			log.debug(A.Z+"[MemberController.getStudentByPage]  : "+"관리자 아이디로 접근하세요"+A.R);
			// 로그인 페이지로 이동
			return "/member/memberLogin";
		}
		
		Map<String, Object> map = memberService.getStudentByPage(currentPage, rowPerPage);
		log.debug(A.Z+"[MemberController.getStudentByPage] map : "+map+A.R);
		
		// 뷰 페이지에 사용자 레벨 넘긴다. 운영자만 이용 가능 
		
		model.addAttribute("studentList", map.get("studentList"));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", map.get("currentPage"));
		
		// 세션으로 로그인한 유저의 아이디와 과목 번호 ( 사용이 되야 할 곳이 생길 수 있음. 계속 넘기기로 협의 ) 
		session.setAttribute("loginMember", loginMember);
		session.setAttribute("sessionLectureNo", 0);
		
		// 학생 목록 확인하는 페이지로 이동 
		return "/member/getStudentByPage";
	}
	
	// ------------------ 개인 정보 휴면(삭제) 처리 ------------------ //
	
	// 운영자 휴면 처리 ( 삭제 대체 ) 
	@PostMapping("/manager/freezeManager")
	public String removeManager(HttpSession session
								, Member member) {
		
		// 매개 변수 내용 확인 
		log.debug(A.Z+"[MemberController.removeManager.param] member : "+member+A.R);
		
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		log.debug(A.Z+"[MemberController.removeManager.param] loginMember : "+loginMember+A.R);
		
		// int rowOfTeacherTbl = memberService.freezeTeacherOfTeacherTbl(member);
		// log.debug(A.Z+"[MemberController.removeTeacher] rowOfteacherTbl : "+rowOfTeacherTbl+A.R);
		
		// Member 테이블에 변경된 내용 확인 
		
		int rowOfMemberTbl = memberService.freezeManagerOfMemberTbl(member);
		log.debug(A.Z+"[MemberController.removeManager] rowOfMemberTbl : "+rowOfMemberTbl+A.R);
		
		// 액터별 레벨 이력 테이블 변경 여부 확인 
		int rowOfLevelHistoryTbl = memberService.addLevelHistoryOfManagerRecord(loginMember);
		log.debug(A.Z+"[MemberController.removeManager] rowOfLevelHistoryTbl : "+rowOfLevelHistoryTbl+A.R);
		
		// GetMapping(/login) 
		return "redirect:/all/logout";
	}
	
	// 운영자 휴면 처리 ( 삭제 대체 ) 
	@GetMapping("/manager/freezeManager")
	public String removeManager(HttpSession session) {
		
		// 매개 변수 내용 확인 
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		log.debug(A.Z+"[MemberController.removeManager.param] loginMember : "+loginMember+A.R);
		
		// 계정 정보 없으면 로그인 실패 
		if(loginMember == null) {
			log.debug(A.Z+"MemberController.removeManager : "+"로그인 실패"+A.R);
			// member/memberLogin.jsp 
			return "/member/memberLogin";
		}
		
		// 세션에 로그인 사용자 정보와 과목 번호 저장 
		session.setAttribute("loginMember", loginMember);
		session.setAttribute("sessionLectureNo", "");
		
		// 해당하는 경로의 뷰 페이지로 이동 
		return "/member/freezeManager";
	}
	
	// 강사 휴면 처리 ( 삭제 대체 ) 
	@PostMapping("/teacher/freezeTeacher")
	public String removeTeacher(HttpSession session
								, Member member) {
		
		// 매개 변수 내용 확인 
		log.debug(A.Z+"[MemberController.removeTeacher.param] member : "+member+A.R);
		
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		log.debug(A.Z+"[MemberController.removeTeacher.param] loginMember : "+loginMember+A.R);
		
		// int rowOfTeacherTbl = memberService.freezeTeacherOfTeacherTbl(member);
		// log.debug(A.Z+"[MemberController.removeTeacher] rowOfteacherTbl : "+rowOfTeacherTbl+A.R);
		
		// Member 테이블에 변경된 내용 확인 
		
		int rowOfMemberTbl = memberService.freezeTeacherOfMemberTbl(member);
		log.debug(A.Z+"[MemberController.removeTeacher] rowOfMemberTbl : "+rowOfMemberTbl+A.R);
		
		// 액터별 레벨 이력 테이블 변경 여부 확인 
		int rowOfLevelHistoryTbl = memberService.addLevelHistoryOfTeacherRecord(loginMember);
		log.debug(A.Z+"[MemberController.removeTeacher] rowOfLevelHistoryTbl : "+rowOfLevelHistoryTbl+A.R);
		
		// GetMapping(/login) 
		return "redirect:/all/logout";
	}
	
	// 강사 휴면 처리 ( 삭제 대체 ) 
	@GetMapping("/teacher/freezeTeacher")
	public String removeTeacher(HttpSession session) {
		
		// 매개 변수 내용 확인 
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		log.debug(A.Z+"[MemberController.removeTeacher.param] loginMember : "+loginMember+A.R);
		
		// 계정 정보 없으면 로그인 실패 
		if(loginMember == null) {
			log.debug(A.Z+"MemberController.removeTeacher : "+"로그인 실패"+A.R);
			// member/memberLogin.jsp 
			return "/member/memberLogin";
		}
		
		// 세션에 로그인 사용자 정보와 과목 번호 저장 
		session.setAttribute("loginMember", loginMember);
		session.setAttribute("sessionLectureNo", "");
		
		// 해당하는 경로의 뷰 페이지로 이동 
		return "/member/freezeTeacher";
	}
	
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
		return "redirect:/all/logout";
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
	
	// ------------------ 개인 정보 수정 ------------------ //
	
	// 휴면 계정 또는 사용자가 비밀번호를 변경하고자 하는 경우
	@PostMapping("/all/modifyMemberPassword")
	public String modifyMemberPassword(HttpSession session
								, Member member) {
		// 세션에서 아이디와 레벨은 계속 가져온다 
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		// 매개변수 확인 
		log.debug(A.Z+"[MemberController.modifyPassword.param] member : "+member+A.R);
		
		int row = memberService.modifyMemberPassword(member);
		log.debug(A.Z+"[MemberController.modifyMemberPassword] row : "+row+A.R);
		
		// 최종 입력 실패 -> 수정 페이지 또는 휴면 계정 전용 페이지로 이동 
		if(row == 0 && member.getMemberLevel() > 3) {
			return "/member/login"; // 테스트 
		} else if(row == 0 && member.getMemberLevel() < 0) {
			return "/member/login"; // 테스트 
		}
		
		return "/member/login";
	}
	
	// 휴면 계정 또는 사용자가 비밀번호를 변경하고자 하는 경우 
	@GetMapping("/all/modifyMemberPassword")
	public String modifyMemberPassword(HttpSession session
								, Model model
								, Member member) {
		// 세션에서 아이디와 레벨은 계속 가져온다 
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		// 매개 변수가 잘 들어왔는지 확인 
		log.debug(A.Z+"[MemberController.modifyPassword.param] member : "+member+A.R);
		
		// 비밀번호 변경 페이지로 이동 
		return "/member/modifyMemberPassword";
	}
	
	// 운영자 정보 수정 
	@PostMapping("/manager/modifyManager")
	public String modifyManager(HttpSession session
								, HttpServletRequest request
								, Member member
								, Manager manager
								, MemberPhoto memberPhoto
								, MemberUploadPhoto memberUploadPhoto) {
		
		// 매개변수가 잘 들어왔는지 확인 
		// 운영자 정보 수정시 MEMBER 테이블에 수정해야 할 내용 받기 
		log.debug(A.Z+"[MemberController.modifyManager.param] member : "+member+A.R);
		// 운영자 정보 수정시  MANAGER 테이블에 수정해야 할 내용 받기 
		log.debug(A.Z+"[MemberController.modifyManager.param]  manager : "+manager+A.R);
		log.debug(A.Z+"[MemberController.modifyManager.param] memberPhoto : "+memberPhoto+A.R);
		// 운영자 정보 수정시 MEMBERPHOTO 테이블에 수정해야 할 내용 받기
		log.debug(A.Z+"[MemberController.modifyManager.param] memberUploadPhoto : "+memberUploadPhoto+A.R);
		
		// 사진을 업로드하면 저장되는 경로 설정 
		URL pathUrl = this.getClass().getResource("/static/");
		log.debug(A.Z+"[MemberController.modifyManager] pathUrl : "+pathUrl+A.R);
		
		String path = pathUrl.getPath()+"/uploadFile/memberPhoto/managerPhoto/";
		log.debug(A.Z+"[MemberController.modifyManager] path : "+path+A.R);
		
		// 세션에서 아이디와 레벨은 계속 가져온다 
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		
		// modifyManager.jsp 페이지에서 파일 받은 내용 확인 
		MultipartFile managerPhoto = memberUploadPhoto.getMemberPhotoOne();
		log.debug(A.Z+"[MemberController.modifyManager.param] managerPhoto : "+managerPhoto+A.R);
		
		// 파일이 들어왔고 파일 사이즈가 0보다 크면 
		if(managerPhoto != null && managerPhoto.getSize() > 0) {
			// 파일이 들어온 내용을 확인 
			log.debug(A.Z+"[MemberController.modifyManager] managerPhoto : "+managerPhoto+A.R);
		}
		
		// 사용자 사진 업로드 
		memberService.addMemberPhoto(member, memberPhoto, memberUploadPhoto, path);
		
		// 운영자 정보가 바뀌었는지 확인 
		// 멤버 테이블에 변경 내용이 반영되었는지 확인 
	    int rowOfMember = memberService.modifyManager(member);
	    log.debug(A.Z+"[MemberController.modifyManager] rowOfMember : "+rowOfMember+A.R);
	    // 운영자 테이블에 변경 내용이 반영되었는지 확인 
	    int rowOfManager = memberService.modifyManager(manager);
	    log.debug(A.Z+"[MemberController.modifyManager] rowOfManager : "+rowOfManager+A.R);
		
	    // redirect 사용하면 Controller 상에 매핑된 주소를 찾아간다  
	    return "redirect:/manager/getManagerOne";
	}
	
	// 운영자 정보 수정 
	@GetMapping("/manager/modifyManager")
	public String modifyManager(HttpSession session
								, @RequestParam(value="memberId") String memberId
								, Model model) {
		// 매개 변수 확인 
		log.debug(A.Z+"[MemberController.modifyManager.param] memberId : "+memberId+A.R);
		// 세션 매개 변수 확인 
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		log.debug(A.Z+"[MemberController.modifyManager] loginMember : "+loginMember+A.R);
		
		// 계정 정보 없으면 로그인 실패 
		if(memberId == null) {
			log.debug(A.Z+"MemberController.modifyManager : "+"로그인 실패"+A.R);
			// member/memberLogin.jsp 
			return "/member/memberLogin";
		}

		// 운영자의 상세 정보를 가져온다 
		// VO 두개를 합쳐야 한 운영자의 정보가 나온다 
		Manager getManagerOneByManagerVo = memberService.getManagerOneReturnManagerVo(loginMember);
		log.debug(A.Z+"[MemberController.modifyManager] getManagerOneByManagerVo : "+getManagerOneByManagerVo+A.R);
		
		Member getManagerOneByMemberVo = memberService.getManagerOneReturnMemberVo(loginMember);
		log.debug(A.Z+"[MemberController.modifyManager] getManagerOneByMemberVo : "+getManagerOneByMemberVo+A.R);
		
		// 이동하려는 뷰 페이지에 해당하는 데이터들을 보낸다 
		model.addAttribute("getManagerOneByManagerVo", getManagerOneByManagerVo);
		model.addAttribute("getManagerOneByMemberVo", getManagerOneByMemberVo);
		
		// 세션에 로그인 사용자 정보와 과목 번호를 보낸다 
		session.setAttribute("loginMember", loginMember);
		session.setAttribute("sessionLectureNo", 0);
		
		// 해당하는 뷰 페이지로 이동 
		return "/member/modifyManager";
	}
	
	// 강사 정보 수정 
	@PostMapping("/teacher/modifyTeacher")
	public String modifyTeacher(HttpSession session
								, HttpServletRequest request
								, Member member
								, Teacher teacher
								, MemberPhoto memberPhoto
								, MemberUploadPhoto memberUploadPhoto) {
		
		// 매개변수가 잘 들어왔는지 확인 
		// 강사 정보 수정시 MEMBER 테이블에 수정해야 할 내용 받기 
		log.debug(A.Z+"[MemberController.modifyTeacher.param] member : "+member+A.R);
		// 강사 정보 수정시  TEACHER 테이블에 수정해야 할 내용 받기 
		log.debug(A.Z+"[MemberController.modifyTeacher.param]  teacher : "+teacher+A.R);
		log.debug(A.Z+"[MemberController.modifyTeacher.param] memberPhoto : "+memberPhoto+A.R);
		// 강사 정보 수정시 MEMBERPHOTO 테이블에 수정해야 할 내용 받기
		log.debug(A.Z+"[MemberController.modifyTeacher.param] memberUploadPhoto : "+memberUploadPhoto+A.R);
		
		// 사진을 업로드하면 저장되는 경로 설정 
		String path = request.getServletContext().getRealPath("/memberPhoto/teacherPhoto/");
		log.debug(A.Z+"[MemberController.modifyTeacher.param] path : "+path+A.R);
		
		// 세션에서 아이디와 레벨은 계속 가져온다 
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		
		// modifyTeacher.jsp 페이지에서 파일 받은 내용 확인 
		MultipartFile teacherPhoto = memberUploadPhoto.getMemberPhotoOne();
		log.debug(A.Z+"[MemberController.modifyTeacher.param] teacherPhoto : "+teacherPhoto+A.R);
		
		// 파일이 들어왔고 파일 사이즈가 0보다 크면 
		if(teacherPhoto != null && teacherPhoto.getSize() > 0) {
			// 파일이 들어온 내용을 확인 
			log.debug(A.Z+"[MemberController.modifyTeacher] teacherPhoto : "+teacherPhoto+A.R);
		}
		
		// 사용자 사진 업로드 
		memberService.addMemberPhoto(member, memberPhoto, memberUploadPhoto, path);
		
		// 강사 정보가 바뀌었는지 확인 
		// 멤버 테이블에 변경 내용이 반영되었는지 확인 
	    int rowOfMember = memberService.modifyTeacher(member);
	    log.debug(A.Z+"[MemberController.modifyTeacher] rowOfMember : "+rowOfMember+A.R);
	    // 학생 테이블에 변경 내용이 반영되었는지 확인 
	    int rowOfTeacher = memberService.modifyTeacher(teacher);
	    log.debug(A.Z+"[MemberController.modifyTeacher] rowOfTeacher : "+rowOfTeacher+A.R);
		
	    // redirect 사용하면 Controller 상에 매핑된 주소를 찾아간다  
	    return "redirect:/teacher/getTeacherOne";
	}
	
	// 강사 정보 수정 
	@GetMapping("/teacher/modifyTeacher")
	public String modifyTeacher(HttpSession session
								, @RequestParam(value="memberId") String memberId
								, Model model) {
		// 매개 변수 확인 
		log.debug(A.Z+"[MemberController.modifyTeacher.param] memberId : "+memberId+A.R);
		// 세션 매개 변수 확인 
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		log.debug(A.Z+"[MemberController.modifyTeacher] loginMember : "+loginMember+A.R);
		
		// 계정 정보 없으면 로그인 실패 
		if(memberId == null) {
			log.debug(A.Z+"MemberController.modifyTeacher : "+"로그인 실패"+A.R);
			// member/memberLogin.jsp 
			return "/member/memberLogin";
		}

		// 강사의 상세 정보를 가져온다 
		// VO 두개를 합쳐야 한 강사의 정보가 나온다 
		Teacher getTeacherOneByTeacherVo = memberService.getTeacherOneReturnTeacherVo(loginMember);
		log.debug(A.Z+"[MemberController.modifyTeacher] getTeacherOneByTeacherVo : "+getTeacherOneByTeacherVo+A.R);
		Member getTeacherOneByMemberVo = memberService.getTeacherOneReturnMemberVo(loginMember);
		log.debug(A.Z+"[MemberController.modifyTeacher] getTeacherOneByMemberVo : "+getTeacherOneByMemberVo+A.R);
		
		// 이동하려는 뷰 페이지에 해당하는 데이터들을 보낸다 
		model.addAttribute("getTeacherOneByTeacherVo", getTeacherOneByTeacherVo);
		model.addAttribute("getTeacherOneByMemberVo", getTeacherOneByMemberVo);
		
		// 세션에 로그인 사용자 정보와 과목 번호를 보낸다 
		session.setAttribute("loginMember", loginMember);
		session.setAttribute("sessionLectureNo", 0);
		
		// 해당하는 뷰 페이지로 이동 
		return "/member/modifyTeacher";
	}
	
	// 학생 정보 수정 POST 
	// 학생 수정 POST에 AddStudent DTO 재활용, 받는 내용 겹치니까 
	@PostMapping("/all/modifyStudent")
	public String modifyStudent(HttpSession session
								, AddStudent modifyStudent
								, MemberPhoto memberPhoto
								, MemberUploadPhoto memberUploadPhoto
								, HttpServletRequest request) {
		// 매개변수 내용 확인  
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		log.debug(A.Z+"[MemberController.modifyStudent.param] loginMember : "+loginMember+A.R);
		log.debug(A.Z+"[MemberController.modifyStudent.param] modifyStudent : "+modifyStudent+A.R);
		// 사진 고유 이름 나옴. photoOriginName 
		log.debug(A.Z+"[MemberController.modifyStudent.param] memberPhoto : "+memberPhoto+A.R);
		
		// 학생 수정 정보 update 요청 
		int row = memberService.modifyStudent(modifyStudent, memberPhoto, memberUploadPhoto, request);
		log.debug(A.Z+"[MemberController.modifyStudent] row : "+row+A.R);
		
		if(row == 0) {
			return "redirect:/all/modifyStudent";
		}
		
		return "redirect:/all/getStudentOne";
	}
	
	/*
	// 학생 정보 수정 POST 
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
	*/
	
	// 학생 정보 수정 GET 
	@GetMapping("/all/modifyStudent")
	public String modifyStudent(HttpSession session
								, Model model) {
		
		// 세션 매개 변수 확인 
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		log.debug(A.Z+"[MemberController.modifyStudent] loginMember : "+loginMember+A.R);

		// 학생의 상세 정보를 가져온다 
		// 서비스는 한개만 사용 
		// 학생 한명의 정보를 가져오기 위해 하나의 서비스 호출 
		Map<String, Object> returnMap = memberService.getStudentOne(loginMember);
		
		// 뷰 페이지 getStudentOne.jsp 페이지로 참조 변수 인스턴스들을 넘긴다. 
		model.addAttribute("studentOne",returnMap);
				
		// 해당하는 뷰 페이지로 이동 
		return "/member/modifyStudent";
	}
	
	// ------------------ 상세보기 ------------------ //
	
	// 운영자 상세보기 
	// 세션 받음 
	@GetMapping("/manager/getManagerOne")
	public String getManagerOne(HttpSession session
								, Model model) {
		// 세션에서 받아온 참조 변수 Member의 ID, LEVEL 필드를 받는다 
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		log.debug(A.Z+"[MemberController.getManagerOne.param] loginMember : "+loginMember+A.R);

		// 계정 정보 없으면 로그인 실패 
		if(loginMember == null) {
			log.debug(A.Z+"MemberController.getManagerOne : "+"로그인 실패"+A.R);
			// member/memberLogin.jsp 
			return "/member/memberLogin";
		}
		
		// 운영자 개인의 정보를 띄우기 위해 Member, Manager, MemberPhoto 테이블에서 거의 모든 필드들을 가져온다 
		Manager getManagerOneByManagerVo = memberService.getManagerOneReturnManagerVo(loginMember);
		log.debug(A.Z+"[MemberController.getManagerOne] getManagerOneByManagerVo : "+getManagerOneByManagerVo+A.R);
		
		Member getManagerOneByMemberVo = memberService.getManagerOneReturnMemberVo(loginMember);
		log.debug(A.Z+"[MemberController.getManagerOne] getManagerOneByMemberVo : "+getManagerOneByMemberVo+A.R);
		
		MemberPhoto getMemberPhoto = memberService.getMemberPhoto(loginMember);
		log.debug(A.Z+"[MemberController.getManagerOne] getMemberPhoto : "+getMemberPhoto+A.R);
		
		// 뷰 페이지 getTeacherOne.jsp 페이지로 참조 변수 인스턴스들을 넘긴다. 
		model.addAttribute("getManagerOneByManagerVo", getManagerOneByManagerVo);
		model.addAttribute("getManagerOneByMemberVo", getManagerOneByMemberVo);
		model.addAttribute("getMemberPhoto", getMemberPhoto);
		
		// 세션으로 로그인한 유저의 정보와 과목 번호를 넘긴다 
		session.setAttribute("loginMember", loginMember);
		session.setAttribute("sessionLectureNo", 0);
		
		return "/member/getManagerOne";
	}
	
	// 강사 상세보기 
	// 세션 받음 
	@GetMapping("/teacher/getTeacherOne")
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
	
	
	// 학생 상세보기 GET 
	@GetMapping("/all/getStudentOne")
	public String getStudentOne(HttpSession session
								, Model model) {
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		log.debug(A.Z+"[MemberController.getStudentOne.param] loginMember : "+loginMember+A.R);
			
		// 학생 한명의 정보를 가져오기 위해 하나의 서비스 호출 
		Map<String, Object> returnMap = memberService.getStudentOne(loginMember);
		
		// 뷰 페이지 getStudentOne.jsp 페이지로 참조 변수 인스턴스들을 넘긴다. 
		model.addAttribute("studentOne",returnMap);
				
		return "/member/getStudentOne";
	}
	
	// ------------------ 회원가입 ------------------ //
	
	// 운영자 회원가입 POST 
	@PostMapping("/addManager")
	public String addManager(AddManager addManager) {
		// 매개 변수 내용 확인 
		log.debug(A.Z+"[MemberController.addManager.param] addManager : "+addManager+A.R);
		
		// 운영자 회원가입 정보 insert 요청 
		int row = memberService.addManager(addManager);
		log.debug(A.Z+"[MemberController.addManager] row : "+row+A.R);
		//실패시에 입력폼으로 리다이렉트
		if(row == 0) {
			return "redirect:/addManager";
		}
		// 해당하는 뷰 페이지로 이동 
		return "redirect:/login";
	}
	
	// 운영자 회원가입 GET 
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
	
	// 강사 회원가입 POST 
	@PostMapping("/addTeacher")
	public String addTeacher(AddTeacher addteacher) {
		// 매개 변수 내용 확인 
		log.debug(A.Z+"[MemberController.addteacher.param] addteacher : "+addteacher+A.R);
		
		// 운영자 회원가입 정보 insert 요청 
		int row = memberService.addTeacher(addteacher);
		log.debug(A.Z+"[MemberController.addTeacher] row : "+row+A.R);
		//실패시에 입력폼으로 리다이렉트
		if(row == 0) {
			return "redirect:/addteacher";
		}
		// 해당하는 뷰 페이지로 이동 
		return "redirect:/login";
	}
	
	// 강사 회원가입 GET 
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

	// 학생 회원가입 POST 
	@PostMapping("/addStudent")
	public String addStudent(AddStudent addstudent) {
		// 매개 변수 내용 확인 
		log.debug(A.Z+"[MemberController.addstudent.param] addstudent : "+addstudent+A.R);
		
		// 운영자 회원가입 정보 insert 요청 
		int row = memberService.addStudent(addstudent);
		log.debug(A.Z+"[MemberController.addstudent] row : "+row+A.R);
		//실패시에 입력폼으로 리다이렉트
		if(row == 0) {
			return "redirect:/addStudent";
		}
		// 해당하는 뷰 페이지로 이동 
		return "redirect:/login";
	}
	
	// 학생 회원가입 GET 
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
	
	// ------------------ 로그인 / 로그아웃 ------------------ // 
	
	// 휴면 처리 계정 접속시 휴면 계정임을 보여주는 페이지로 이동시킴 
	@GetMapping("/restingMember")
	public String restingMember(HttpSession session) {
		return "/member/restingMember";
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
		if(sessionLoginMember.getMemberLevel() < 0) {
			return "/member/memberLogin";
		} else {
			return "redirect:/all/home";
		}
		
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