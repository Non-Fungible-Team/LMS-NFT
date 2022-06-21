package kr.co.nft.lms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.nft.lms.util.A;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TemplateController {

	@GetMapping("/exampleTemplate")
	public String exampleTemplate() {
		log.debug(A.A + "[TemplateController.exampleTemplate] 실행" + A.R);
		return "/exampleTemplate";
	}
	
	@GetMapping("/exampleTemplate2")
	public String exampleTemplate2() {
		log.debug(A.A + "[TemplateController.exampleTemplate2] 실행" + A.R);
		return "/lecture/exampleTemplate2";
	}
}
