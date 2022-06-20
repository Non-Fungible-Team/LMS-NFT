package kr.co.nft.lms.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class MemberRestController {
	
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
	
	@PostMapping("/memberInsert")
	public String idCheck(@RequestParam(value="memberId") String memberId) {
		
		
		if(memberId != null) {
			return "false";
		} 
		return memberId;
	}
}
