package kr.co.nft.lms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
	@GetMapping("/memberInsert")
	public String memberInsert() {
		return "memberInsert";
	}
	
}
