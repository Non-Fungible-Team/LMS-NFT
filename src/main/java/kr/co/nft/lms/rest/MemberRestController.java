package kr.co.nft.lms.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberRestController {
	
	@PostMapping("/memberInsert")
	public String idCheck(@RequestParam(value="memberId") String memberId) {
		
		
		if(memberId != null) {
			return "false";
		} 
		return memberId;
	}
}
