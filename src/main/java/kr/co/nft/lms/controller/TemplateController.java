package kr.co.nft.lms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.nft.lms.service.LectureRoomService;
import kr.co.nft.lms.service.LectureScheduleService;
import kr.co.nft.lms.service.LectureService;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.LectureSchedule;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TemplateController {
	
	@Autowired private LectureScheduleService lectureScheduleService;
	
	@Autowired private LectureRoomService lectureRoomService;
	
	@Autowired private LectureService lectureService;
	
	@GetMapping("/teacher/lecture/lectureNameOne")
	public String lectureNameOne(Model model
								, @RequestParam(name = "lectureNo") int lectureNo) {
		log.debug(A.W +"[TemplateController.teacher.lecture/lectureNameOne.param] lectureNo : " + lectureNo +A.R);
		Map<String,Object> ParamMap = new HashMap<>();
		
		ParamMap.put("lectureNo", lectureNo); //번호 값 저장
		log.debug(A.W +"[TemplateController.teacher.lecture/lectureNameOne.ParamMap] ParamMap : " + ParamMap +A.R);
		
		model.addAttribute("lectureNo", lectureNo);
		
		return "lecture/lectureNameOne";
	}
}
