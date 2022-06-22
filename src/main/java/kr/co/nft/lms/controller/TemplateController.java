package kr.co.nft.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.nft.lms.service.LectureRoomService;
import kr.co.nft.lms.service.LectureScheduleService;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.LectureSchedule;
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
	
	// LectureScheduleService.getLectureScheduleListByLectureNo 테스트
	@GetMapping("/getLectureScheduleListByLectureNo")
	public String getLectureScheduleListByLectureNo(Model model
													, @RequestParam(name = "lectureNo", defaultValue = "0") int lectureNo) {
		log.debug(A.A + "[TemplateController.getLectureScheduleListByLectureNo] 실행" + A.R);
		
		lectureNo = 1;
		log.debug(A.A + "[TemplateController.getLectureScheduleListByLectureNo] lectureNo : " + lectureNo + A.R);
		
		List<LectureSchedule> lectureScheduleList = lectureScheduleService.getLectureScheduleListByLectureNo(lectureNo);
		
		log.debug(A.A + "[TemplateController.getLectureScheduleListByLectureNo] lectureScheduleList : " + lectureScheduleList + A.R);
		
		model.addAttribute("lectureScheduleList", lectureScheduleList);
		
		log.debug(A.A + "[TemplateController.getLectureScheduleListByLectureNo] 실행 완료" + A.R);
		
		return "/lecture/getLectureScheduleListByLectureNo";
	}
	
	// LectureScheduleService.removeLectureSchedule 테스트
	@GetMapping("/removeLectureSchedule")
	public String removeLectureSchedule(@RequestParam(name = "lectureScheduleDate") String lectureScheduleDate
										, @RequestParam(name = "lectureNo", defaultValue = "0") int lectureNo) {
		
		log.debug(A.A + "[TemplateController.removeLectureSchedule] lectureScheduleDate : " + lectureScheduleDate + A.R);
		log.debug(A.A + "[TemplateController.removeLectureSchedule] lectureNo : " + lectureNo + A.R);
		
		int row = lectureScheduleService.removeLectureSchedule(lectureScheduleDate, lectureNo);
		
		log.debug(A.A + "[TemplateController.removeLectureSchedule] row : " + row + A.R);
		
		if(row == 1) {
			log.debug(A.A + "[LectureController.removeLectureSchedule] lecture_schedule 삭제 성공" + A.R);
		} else {
			log.debug(A.A + "[LectureController.removeLectureSchedule] lecture_schedule 삭제 실패" + A.R);
		}
		
		return "redirect:/getLectureScheduleListByLectureNo";
	}
}
