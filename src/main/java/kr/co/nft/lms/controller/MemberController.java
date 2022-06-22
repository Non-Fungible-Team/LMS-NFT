package kr.co.nft.lms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.nft.lms.service.MemberService;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Member;
import kr.co.nft.lms.vo.Student;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	
	@Autowired MemberService memberService;
	
	// 학생 상세보기
	@GetMapping("/student/getStudentOne")
	public String getStudentOne(HttpSession session
								, @RequestParam(value="memberId") String memberId
								, Model model) {
		
		log.debug(A.Z+"[MemberController.getStudentOne.param] memberId : "+memberId+A.R);

		// 계정 정보 없으면 로그인 실패 
		if(memberId == null) {
			log.debug(A.Z+"MemberController.getStudentOne : "+"로그인 실패"+A.R);
			// member/memberLogin.jsp 
			return "/member/memberLogin";
		}
			
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		log.debug(A.Z+"[MemberController.getStudentOne] loginMember : "+loginMember+A.R);
		
		Student getStudentOneByStudentVo = memberService.getStudentOneReturnStudentVo(loginMember);
		Member getStudentOneByMemberVo = memberService.getStudentOneReturnMemberVo(loginMember);
		
		model.addAttribute("getStudentOneByStudentVo", getStudentOneByStudentVo);
		model.addAttribute("getStudentOneByMemberVo", getStudentOneByMemberVo);
		
		session.setAttribute("loginMember", loginMember);
		session.setAttribute("sessionLectureNo", "");
		
		return "/member/getStudentOne";
	}
	
	// 학생 회원가입 
	@PostMapping("/member/addStudent")
	public String addStudent(Member member
							,Student student) {
		log.debug(A.Z+"[MemberController.addStudent.param] member : "+member+A.R);
		log.debug(A.Z+"[MemberController.addStudent.param] student : "+student+A.R);
		
		int rowOfMember = memberService.addStudent(member);
		log.debug(A.Z+"[MemberController.addStudent] rowOfMember : "+rowOfMember+A.R);
		int rowOfStudent = memberService.addStudent(student);
		log.debug(A.Z+"[MemberController.addStudent] rowOfStudent : "+rowOfStudent+A.R);
		
		return "/member/memberLogin";
	}
	
	// 학생 회원가입 
	@GetMapping("/member/addStudent") 
	// `member_level` 필드 값 받기 위해 파라미터로 Member VO 넣음 
	// `memberLevel` 데이터 잘 받으면 VO로 안받아도 상관 없을듯 
	public String addStudent(Model model
							, Member member
							, @RequestParam(value="memberLevel") int memberLevel) {
		
		log.debug(A.Z+"[MemberController.addStudent.param] model : "+model+A.R);
		log.debug(A.Z+"[MemberController.addStudent.param] member : "+member+A.R);
		log.debug(A.Z+"[MemberController.addStudent.param] memberLevel : "+memberLevel+A.R);
		
		model.addAttribute("memberLevel", memberLevel);
		return "/member/addStudent";
	}
	
	
	// 로그아웃 
	@GetMapping("/all/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "/member/memberLogin";
	}
	
	// 로그인 폼 
	@GetMapping("/login")
	public String login(HttpSession session) {
		if(session.getAttribute("sessionLoginMember") != null) {
			return "home";
		}
		return "/member/memberLogin";
	}
	
	// 로그인 액션 
	@PostMapping("/login")
	public String login(HttpSession session
						, Member member) {
		
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
		session.setAttribute("sessionLectureNo", "");
		return "home";
	}
	
	
}
