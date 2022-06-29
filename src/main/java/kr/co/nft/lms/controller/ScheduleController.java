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
		
		log.debug(A.A + "[ScheduleController.addScheduleForm] 실행" + A.R);
		
		return "/schedule/addSchedule";
	}
	
	// Schedule 테이블 데이터 입력 - Action
	@PostMapping("/manager/schedule/addScheduleAction")
	public String addScheduleAction(@RequestParam(name = "scheduleTitle") String scheduleTitle
								  , @RequestParam(name = "scheduleMemberId") String scheduleMemberId
								  , @RequestParam(name = "scheduleContent") String scheduleContent
								  , @RequestParam(name = "scheduleDate") String scheduleDate) {
		
		log.debug(A.A + "[ScheduleController.addScheduleAction] scheduleTitle : " + scheduleTitle + A.R);
		log.debug(A.A + "[ScheduleController.addScheduleAction] scheduleMemberId : " + scheduleMemberId + A.R);
		log.debug(A.A + "[ScheduleController.addScheduleAction] scheduleContent : " + scheduleContent + A.R);
		log.debug(A.A + "[ScheduleController.addScheduleAction] scheduleDate : " + scheduleDate + A.R);
		
		Schedule schedule = new Schedule();
		schedule.setScheduleTitle(scheduleTitle);
		schedule.setMemberId(scheduleMemberId);
		schedule.setScheduleContent(scheduleContent);
		schedule.setScheduleDate(scheduleDate);
		
		log.debug(A.A + "[ScheduleController.addScheduleAction] schedule : " + schedule + A.R);
		
		int row = scheduleService.addSchedule(schedule);
		
		log.debug(A.A + "[ScheduleController.addScheduleAction] row : " + row + A.R);
		
		if(row == 1) {
			log.debug(A.A + "[ScheduleController.addScheduleAction] schedule 입력 성공 " + A.R);
			return "redirect:/all/schedule/getScheduleList";
		} else {
			log.debug(A.A + "[ScheduleController.addScheduleAction] schedule 입력 실패 " + A.R);
			return "redirect:/manager/schedule/addSchedule";
		}
		
	}
	
	// Schedule 테이블 데이터 수정 - Form
	@GetMapping("/manager/schedule/modifySchedule")
	public String modifySchedule(Model model
			   				   , @RequestParam(name = "scheduleNo") int scheduleNo) {
		
		log.debug(A.A + "[ScheduleController.modifySchedule] scheduleNo : " + scheduleNo + A.R);
		
		Schedule scheduleOne = scheduleService.getScheduleOne(scheduleNo);
		
		log.debug(A.A + "[ScheduleController.modifySchedule] scheduleOne : " + scheduleOne + A.R);
		
		model.addAttribute("scheduleOne", scheduleOne);
		
		return "/schedule/modifySchedule";
	}
	
	// Schedule 테이블 데이터 수정 - Action
	@PostMapping("/manager/schedule/modifyScheduleAction")
	public String modifyScheduleAction(@RequestParam(name = "scheduleNo") int scheduleNo
									 , @RequestParam(name = "scheduleTitle") String scheduleTitle
									 , @RequestParam(name = "scheduleMemberId") String scheduleMemberId
									 , @RequestParam(name = "scheduleContent") String scheduleContent
									 , @RequestParam(name = "scheduleDate") String scheduleDate) {

		log.debug(A.A + "[ScheduleController.modifyScheduleAction] scheduleNo : " + scheduleNo + A.R);
		log.debug(A.A + "[ScheduleController.modifyScheduleAction] scheduleTitle : " + scheduleTitle + A.R);
		log.debug(A.A + "[ScheduleController.modifyScheduleAction] scheduleMemberId : " + scheduleMemberId + A.R);
		log.debug(A.A + "[ScheduleController.modifyScheduleAction] scheduleContent : " + scheduleContent + A.R);
		log.debug(A.A + "[ScheduleController.modifyScheduleAction] scheduleDate : " + scheduleDate + A.R);
		
		Schedule schedule = new Schedule();
		schedule.setScheduleNo(scheduleNo);
		schedule.setScheduleTitle(scheduleTitle);
		schedule.setMemberId(scheduleMemberId);
		schedule.setScheduleContent(scheduleContent);
		schedule.setScheduleDate(scheduleDate);
		
		log.debug(A.A + "[ScheduleController.modifyScheduleAction] schedule : " + schedule + A.R);
		
		int row = scheduleService.modifySchedule(schedule);
		
		log.debug(A.A + "[ScheduleController.modifyScheduleAction] row : " + row + A.R);
		
		if(row == 1) {
			log.debug(A.A + "[ScheduleController.modifyScheduleAction] schedule 수정 성공 " + A.R);
			
		} else {
			log.debug(A.A + "[ScheduleController.modifyScheduleAction] schedule 수정 실패 " + A.R);
		}
		
		return "redirect:/all/schedule/getScheduleList";		
	}
	
	// Schedule 테이블 데이터 삭제
	@GetMapping("/manager/schedule/removeSchedule")
	public String removeSchedule(@RequestParam(name = "scheduleNo") int scheduleNo) {
				
		log.debug(A.A + "[ScheduleController.removeSchedule] scheduleNo : " + scheduleNo + A.R);
		
		int row = scheduleService.removeSchedule(scheduleNo);
		
		log.debug(A.A + "[ScheduleController.removeSchedule] row : " + row + A.R);
		
		if(row == 1) {
			log.debug(A.A + "[ScheduleController.removeSchedule] schedule 삭제 성공 " + A.R);
		} else {
			log.debug(A.A + "[ScheduleController.removeSchedule] schedule 삭제 실패 " + A.R);
		}
		
		return "redirect:/all/schedule/getScheduleList";
	}
}
