package kr.co.nft.lms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	//홈화면을 보여주는 컨트롤러
	@GetMapping("/all/home")
	public String home(){
		return "home";
	}
}
