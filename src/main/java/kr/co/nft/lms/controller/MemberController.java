package kr.co.nft.lms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.nft.lms.service.MemberService;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Member;
import kr.co.nft.lms.vo.Student;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	
	@Autowired MemberService memberService;
	
	// 학생 회원가입 
	@PostMapping("/member/addStudent")
	public String addStudent(Member member
							,Student student) {
		log.debug(A.Z+"[MemberController.addStudent.param] member : "+member+A.R);
		log.debug(A.Z+"[MemberController.addStudent.param] student : "+student+A.R);
		
		int row = memberService.addStudent(member, student);
		log.debug(A.Z+"[MemberController.addStudent] row : "+row+A.R);
		
		return "/member/memberLogin";
	}
	
	// 학생 회원가입 
	@GetMapping("/member/addStudent") 
	public String addStudent() {
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
		if(session.getAttribute("loginMember") != null) {
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
		log.debug(A.Z+"MemberController.login.loginMember : "+sessionLoginMember+A.R);
			
		// 계정 정보 없으면 로그인 실패 
		if(sessionLoginMember == null) {
			log.debug(A.Z+"MemberController.login : "+"로그인 실패"+A.R);
			// member/memberLogin.jsp 
			return "/member/memberLogin";
		}
		
		// 로그인 성공 
		session.setAttribute("sessionLoginMember", sessionLoginMember);
		return "home";
	}
	
	// 회원가입 폼 
	@GetMapping("/memberInsert")
	public String memberInsert() {
		return "/member/memberInsert";
	}
	
}
