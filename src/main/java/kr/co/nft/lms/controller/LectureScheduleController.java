package kr.co.nft.lms.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.nft.lms.service.LectureScheduleService;
import kr.co.nft.lms.service.LectureService;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.LectureSchedule;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class LectureScheduleController {
	
	@Autowired private LectureScheduleService lectureScheduleService;
	
	@Autowired private LectureService lectureService;
	
	//1.강의시간표 입력 Form
	@GetMapping("/manager/lecture/addLectureScheduleForm")
	public String addLectureScheduleForm(Model model
										, @RequestParam(name = "lectureNo", defaultValue = "0") int lectureNo) {
		
		log.debug(A.A + "[LectureScheduleController.addLectureSchedule] lectureNo : " + lectureNo + A.R);
		
		model.addAttribute("lectureNo", lectureNo);
						
		return "/lecture/addLectureSchedule";
	}
	
	// 1-1. 강의시간표 입력 Action
	@PostMapping("/manager/lecture/addLectureScheduleAction")
	public String addLectureScheduleAction(@RequestParam(name = "lectureNo", defaultValue = "0") int lectureNo
										 , @RequestParam(name = "lectureScheduleDate", defaultValue = "0") String lectureScheduleDate
										 , @RequestParam(name = "lectureScheduleStartDate", defaultValue = "0") String lectureScheduleStartDate
										 , @RequestParam(name = "lectureScheduleEndDate", defaultValue = "0") String lectureScheduleEndDate
										 , @RequestParam(name = "lectureScheduleStartDay", defaultValue = "0") String lectureScheduleStartDay
										 , @RequestParam(name = "lectureScheduleEndDay", defaultValue = "0") String lectureScheduleEndDay) {
		
		log.debug(A.A + "[LectureScheduleController.addLectureScheduleAction] lectureNo : " + lectureNo + A.R);
		log.debug(A.A + "[LectureScheduleController.addLectureScheduleAction] lectureScheduleDate : " + lectureScheduleDate + A.R);
		log.debug(A.A + "[LectureScheduleController.addLectureScheduleAction] lectureScheduleStartDate : " + lectureScheduleStartDate + A.R);
		log.debug(A.A + "[LectureScheduleController.addLectureScheduleAction] lectureScheduleEndDate : " + lectureScheduleEndDate + A.R);
		log.debug(A.A + "[LectureScheduleController.addLectureScheduleAction] lectureScheduleStartDay : " + lectureScheduleStartDay + A.R);
		log.debug(A.A + "[LectureScheduleController.addLectureScheduleAction] lectureScheduleEndDay : " + lectureScheduleEndDay + A.R);
		
		LectureSchedule lectureSchedule = new LectureSchedule();
		lectureSchedule.setLectureNo(lectureNo);
		lectureSchedule.setLectureScheduleDate(lectureScheduleDate);
		lectureSchedule.setLectureScheduleStartDate(lectureScheduleStartDate);
		lectureSchedule.setLectureScheduleEndDate(lectureScheduleEndDate);

		log.debug(A.A + "[LectureScheduleController.addLectureScheduleAction] lectureSchedule : " + lectureSchedule + A.R);
		
		lectureScheduleService.addLectureSchedule(lectureSchedule, lectureScheduleStartDay, lectureScheduleEndDay);
		
		return "redirect:/all/lecture/getLectureScheduleListByLectureNo";
	}
	
	//2.강의시간표 목록
	@GetMapping("/all/lecture/getLectureScheduleListByLectureNo")
	public String getLectureScheduleListByLectureNo(Model model
													,HttpSession session
													,@RequestParam(name = "currentPage",defaultValue = "1") int currentPage  // 디폴트값 설정, 자동형변환
													,@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage) {
		log.debug(A.W +"[LectureScheduleController.student.lecture.getLectureScheduleListByLectureNo.currentPage] currentPage : " +currentPage +A.R);
		log.debug(A.W +"[LectureScheduleController.student.lecture.getLectureScheduleListByLectureNo.rowPerPage] rowPerPage : " +rowPerPage +A.R);
		//session에 lectureNo 값 요청
		int lectureNo = (int)session.getAttribute("sessionLectureNo");
		log.debug(A.A + "[LectureScheduleController.student.lecture.getLectureScheduleListByLectureNo] 실행(lectureNo) :" +lectureNo + A.R);
		
		
		//서비스 호출해서 list에 저장
		Map<String,Object> lectureScheduleMap = lectureScheduleService.getLectureScheduleListByLectureNo(lectureNo, currentPage, rowPerPage);
		log.debug(A.A + "[LectureScheduleController.getLectureScheduleListByLectureNo.lectureScheduleMap] lectureScheduleMap(전) : " + lectureScheduleMap + A.R);
		
		model.addAttribute("lectureScheduleList",lectureScheduleMap.get("lectureScheduleList")); //강의목록
		model.addAttribute("lectureNo", lectureScheduleMap.get("lectureNo")); //강의 번호
		model.addAttribute("currentPage", lectureScheduleMap.get("currentPage")); //현재페이지
		model.addAttribute("lastPage", lectureScheduleMap.get("lastPage")); //마지막페이지
		log.debug(A.A + "[LectureScheduleController.getLectureScheduleListByLectureNo.lectureScheduleMap] lectureScheduleMap(후)"+lectureScheduleMap + A.R);
		log.debug(A.W +"[LectureScheduleController.getLectureScheduleListByLectureNo.model] model.lectureScheduleList : " +lectureScheduleMap.get("lectureScheduleList") +A.R);
		log.debug(A.W +"[LectureScheduleController.getLectureScheduleListByLectureNo.model] model.lectureNo : " +lectureScheduleMap.get("lectureNo") +A.R);
		log.debug(A.W +"[LectureScheduleController.getLectureScheduleListByLectureNo.model] model.currentPage : " +lectureScheduleMap.get("currentPage") +A.R);
		log.debug(A.W +"[LectureScheduleController.getLectureScheduleListByLectureNo.model] model.lastPage : " +lectureScheduleMap.get("lastPage") +A.R);
		
		return "/lecture/getLectureScheduleListByLectureNo";
	}
	
	//3.강의 시간표 삭제 -> 강의번호 받아오기 수정
	@GetMapping("manager/lecture/removeLectureSchedule")
	public String removeLectureSchedule(@RequestParam(name = "lectureScheduleDate") String lectureScheduleDate
									  , @RequestParam(name = "lectureNo") int lectureNo) {
		
		log.debug(A.A + "[LectureScheduleController.removeLectureSchedule] lectureScheduleDate : " + lectureScheduleDate + A.R);
		log.debug(A.A + "[LectureScheduleController.removeLectureSchedule] lectureNo : " + lectureNo + A.R);
		
		int row = lectureScheduleService.removeLectureSchedule(lectureScheduleDate, lectureNo);
		
		log.debug(A.A + "[LectureScheduleController.removeLectureSchedule] row : " + row + A.R);
		
		if(row == 1) {
			log.debug(A.A + "[LectureScheduleController.removeLectureSchedule] lecture_schedule 삭제 성공" + A.R);
		} else {
			log.debug(A.A + "[LectureScheduleController.removeLectureSchedule] lecture_schedule 삭제 실패" + A.R);
		}  
		
		return "/all/lecture/getLectureScheduleListByLectureNo";
	}
}
