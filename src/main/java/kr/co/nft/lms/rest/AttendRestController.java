package kr.co.nft.lms.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.nft.lms.service.AttendService;
import kr.co.nft.lms.util.A;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AttendRestController {
	
	@Autowired
	private AttendService attendService;

	@GetMapping("/rest/manager/lecture/getAttendStatusChart")
	public List<Map<String, Object>> getAttendStatusChart(@RequestParam(name="lectureNo") int lectureNo) {
		
		log.debug(A.A + "[AttendRestController.getAttendStatusChart] lectureNo : " + lectureNo + A.R);
		
		List<Map<String, Object>> attendStatusList = attendService.getAttendStatusChart(lectureNo);
		log.debug(A.A + "[AttendRestController.getAttendStatusChart] attendStatusList : " + attendStatusList + A.R);
		
		return attendStatusList;
	}
}
