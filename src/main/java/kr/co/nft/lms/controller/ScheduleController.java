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
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class ScheduleController {
	@Autowired private LectureScheduleService lectureScheduleService;
	@Autowired private LectureService lectureService;
	
	//1.강의시간표 입력
	@GetMapping("/manager/lecture/addLectureSchedule")
	public String addLectureSchedule() {
		log.debug(A.A + "[ScheduleController.addLectureSchedule] 실행" + A.R);
		lectureScheduleService.addLectureSchedule(); 
		log.debug(A.A + "[ScheduleController.addLectureSchedule] 실행 완료" + A.R);
		return "/lecture/addLectureSchedule";
	}
	
	
	//2.강의시간표 목록
	@GetMapping("/lecture/getLectureScheduleListByLectureNo")
	public String getLectureScheduleListByLectureNo(Model model
													,HttpSession session
													,@RequestParam(name = "currentPage",defaultValue = "1") int currentPage  // 디폴트값 설정, 자동형변환
													,@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage) {
		log.debug(A.W +"[ScheduleController.student.lecture.getLectureScheduleListByLectureNo.currentPage] currentPage : " +currentPage +A.R);
		log.debug(A.W +"[ScheduleController.student.lecture.getLectureScheduleListByLectureNo.rowPerPage] rowPerPage : " +rowPerPage +A.R);
		//session에 lectureNo 값 요청
		int lectureNo = (int)session.getAttribute("sessionLectureNo");
		log.debug(A.A + "[ScheduleController.student.lecture.getLectureScheduleListByLectureNo] 실행(lectureNo) :" +lectureNo + A.R);
		
		
		//서비스 호출해서 list에 저장
		Map<String,Object> lectureScheduleMap = lectureScheduleService.getLectureScheduleListByLectureNo(lectureNo, currentPage, rowPerPage);
		log.debug(A.A + "[ScheduleController.getLectureScheduleListByLectureNo.lectureScheduleMap] lectureScheduleMap(전) : " + lectureScheduleMap + A.R);
		model.addAttribute("lectureScheduleList",lectureScheduleMap.get("lectureScheduleList")); //강의목록
		model.addAttribute("lectureNo", lectureScheduleMap.get("lectureNo")); //강의 번호
		model.addAttribute("currentPage", lectureScheduleMap.get("currentPage")); //현재페이지
		model.addAttribute("lastPage", lectureScheduleMap.get("lastPage")); //마지막페이지
		log.debug(A.A + "[ScheduleController.getLectureScheduleListByLectureNo.lectureScheduleMap] lectureScheduleMap(후)"+lectureScheduleMap + A.R);
		log.debug(A.W +"[ScheduleController.getLectureScheduleListByLectureNo.model] model.lectureScheduleList : " +lectureScheduleMap.get("lectureScheduleList") +A.R);
		log.debug(A.W +"[ScheduleController.getLectureScheduleListByLectureNo.model] model.lectureNo : " +lectureScheduleMap.get("lectureNo") +A.R);
		log.debug(A.W +"[ScheduleController.getLectureScheduleListByLectureNo.model] model.currentPage : " +lectureScheduleMap.get("currentPage") +A.R);
		log.debug(A.W +"[ScheduleController.getLectureScheduleListByLectureNo.model] model.lastPage : " +lectureScheduleMap.get("lastPage") +A.R);
		
		return "/lecture/getLectureScheduleListByLectureNo";
	}
	
	//3.강의 시간표 삭제 -> 강의번호 받아오기 수정
	@GetMapping("manager/lecture/removeLectureSchedule")
	public String removeLectureSchedule(@RequestParam(name = "lectureScheduleDate") String lectureScheduleDate
										, @RequestParam(name = "lectureNo") int lectureNo) {
		
		log.debug(A.A + "[ScheduleController.removeLectureSchedule] lectureScheduleDate : " + lectureScheduleDate + A.R);
		log.debug(A.A + "[ScheduleController.removeLectureSchedule] lectureNo : " + lectureNo + A.R);
		
		int row = lectureScheduleService.removeLectureSchedule(lectureScheduleDate, lectureNo);
		
		log.debug(A.A + "[ScheduleController.removeLectureSchedule] row : " + row + A.R);
		
		if(row == 1) {
			log.debug(A.A + "[ScheduleController.removeLectureSchedule] lecture_schedule 삭제 성공" + A.R);
		} else {
			log.debug(A.A + "[ScheduleController.removeLectureSchedule] lecture_schedule 삭제 실패" + A.R);
		}
		
		return "redirect:/lecture/getLectureScheduleListByLectureNo";
	}
}
