package kr.co.nft.lms.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.nft.lms.service.MemberService;
import kr.co.nft.lms.util.A;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class MemberRestController {
	
	@Autowired MemberService memberService;
	
	@PostMapping("/idCheck")
	public String idCheck(@RequestParam(value="memberId") String memberId) {
		// 매개 변수 내용 확인 
		log.debug(A.Z+"[MemberRestController.idCheck.param] id : "+memberId+A.R);
		
		// MEMBER 테이블에 매개 변수로 받은 사용자 아이디가 있는지 확인 
		int row = memberService.selectIdCheck(memberId);
		log.debug(A.Z+"[MemberRestController.idCheck] row : "+row+A.R);
		 
		// 입력받은 아이디가 존재하는 경우 
		if(row == 1) {
			return "false";
		} 
		return memberId;
		
		
	}
	
	@GetMapping("/managerLoginForm")
	public Map<String, Object> managerLoginForm() {
		Map<String, Object> map = new HashMap<>();
		map.put(null, map);
		map.put(null, map);
		return map;
	}
	
	@GetMapping("/teacherLoginForm")
	public Map<String, Object> teacherLoginForm() {
		Map<String, Object> map = new HashMap<>();
		map.put(null, map);
		map.put(null, map);
		return map;
	}
	
	@GetMapping("/studentLoginForm")
	public Map<String, Object> studentLoginForm() {
		Map<String, Object> map = new HashMap<>();
		map.put(null, map);
		map.put(null, map);
		return map;
	}
	
	
}
