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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired MemberService memberService;
	
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
		Member loginMember = memberService.getMemberOne(member);
				
		// 계정 정보 없으면 로그인 실패 
		if(loginMember == null) {
			log.debug(A.Z+"MemberController.login : "+"로그인 실패"+A.R);
			return "/member/memberLogin";
		}
		
		// 로그인 성공 
		session.setAttribute("loginMember", loginMember);
		return "home";
	}
	
	// 회원가입 폼 
	@GetMapping("/memberInsert")
	public String memberInsert() {
		return "/member/memberInsert";
	}
	
	
	
	
}
