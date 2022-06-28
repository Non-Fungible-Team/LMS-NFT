package kr.co.nft.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.nft.lms.service.ScheduleService;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Schedule;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;
	
	// Schedule 테이블 전체 리스트
	@GetMapping("/all/schedule/getScheduleList")
	public String getScheduleList(Model model) {
		
		List<Schedule> scheduleList = scheduleService.getScheduleList();
		
		log.debug(A.A + "[ScheduleController.getScheduleList] scheduleList : " + scheduleList + A.R);
		
		model.addAttribute("scheduleList", scheduleList);
		
		return "/schedule/getScheduleList";
	}
	
	// Schedule 테이블 schedule_no에 따른 상세보기
	@GetMapping("/all/schedule/getScheduleOne")
	public String getScheduleOne(Model model
							   , @RequestParam(name = "scheduleNo") int scheduleNo) {
		
		log.debug(A.A + "[ScheduleController.getScheduleOne] scheduleNo : " + scheduleNo + A.R);
		
		Schedule scheduleOne = scheduleService.getScheduleOne(scheduleNo);
		
		log.debug(A.A + "[ScheduleController.getScheduleOne] scheduleOne : " + scheduleOne + A.R);
		
		model.addAttribute("scheduleOne", scheduleOne);
		
		return "/schedule/getScheduleOne";
	}
	
	// Schedule 테이블 데이터 입력 - Form
	@GetMapping("/manager/schedule/addSchedule")
	public String addScheduleForm() {
		
		return "/schedule/addSchedule";
	}
	
	// Schedule 테이블 데이터 입력 - Action
	@PostMapping("/manager/schedule/addScheduleAction")
	public String addScheduleAction() {
		
		return "redirect:/all/schedule/getScheduleList";
	}
}
