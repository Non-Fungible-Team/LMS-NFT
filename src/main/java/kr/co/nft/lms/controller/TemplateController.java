package kr.co.nft.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.nft.lms.service.LectureRoomService;
import kr.co.nft.lms.service.LectureScheduleService;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.LectureRoom;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TemplateController {
	
	@Autowired private LectureScheduleService lectureScheduleService;
	
	@Autowired private LectureRoomService lectureRoomService;

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
	
	// LectureScheduleService.addLectureSchedule 테스트
	@GetMapping("/addLectureSchedule")
	public String addLectureSchedule() {
		log.debug(A.A + "[TemplateController.addLectureSchedule] 실행" + A.R);
		lectureScheduleService.addLectureSchedule();
		log.debug(A.A + "[TemplateController.addLectureSchedule] 실행 완료" + A.R);
		return "/lecture/exampleTemplate2";
	}
	
}
