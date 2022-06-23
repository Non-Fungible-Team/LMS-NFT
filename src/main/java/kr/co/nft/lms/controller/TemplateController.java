package kr.co.nft.lms.controller;

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
	
	// managerPage.jsp 테스트
	@GetMapping("/managerPage")
	public String managerPage(Model model
							, @RequestParam(name = "currentPage", defaultValue = "1") int currentPage
							, @RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage) {
		log.debug(A.A + "[TemplateController.managerPage] 실행" + A.R);
		//디버깅코드
		log.debug(A.A + "[LectureController.managerPage.model] model : " + model +A.R);
		log.debug(A.A + "[LectureController.managerPage.currentPage] currentPage : " + currentPage + A.R);
		log.debug(A.A + "[LectureController.managerPage.rowPerPage] rowPerPage : " + rowPerPage + A.R);
		
		Map<String,Object> map = lectureService.getLectureByPage(currentPage, rowPerPage); //강의목록 서비스 호출해서 map객체에 저장
		 //서비스에 저장된 값 가져와서 모델객체에 저장
		model.addAttribute("lectureList", map.get("lectureList")); //강의목록
		model.addAttribute("currentPage", map.get("currentPage")); //현재페이지
		model.addAttribute("lastPage",map.get("lastPage") ); //마지막페이지
		//디버깅코드
		log.debug(A.A + "[LectureController.managerPage.map] map(Service 호출) : " + map + A.R);
		log.debug(A.A + "[LectureController.managerPage.model] model.lectureList : " + map.get("lectureList") + A.R);
		log.debug(A.A + "[LectureController.managerPage.model] model.currentPage : " + map.get("currentPage") + A.R);
		log.debug(A.A + "[LectureController.managerPage.model] model.lastPage : " + map.get("lastPage") + A.R);
		
		log.debug(A.A + "[TemplateController.managerPage] 실행" + A.R);
		return "/managerPage";
	}
}
