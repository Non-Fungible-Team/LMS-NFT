package kr.co.nft.lms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.nft.lms.service.AttendService;
import kr.co.nft.lms.service.LectureScheduleService;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Attend;
import kr.co.nft.lms.vo.LectureSchedule;
import kr.co.nft.lms.vo.Member;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class LectureScheduleController {
	//서비스 객체 생성
	@Autowired private LectureScheduleService lectureScheduleService; //강의시간표
	@Autowired private AttendService attendService; //출석
	
	// 1. 강의시간표 입력 Form
	@GetMapping("/manager/lecture/addLectureScheduleForm")
	public String addLectureScheduleForm(Model model
										, @RequestParam(name = "lectureNo", defaultValue = "0") int lectureNo) {
		
		log.debug(A.A + "[LectureScheduleController.addLectureScheduleForm] lectureNo : " + lectureNo + A.R);
		
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
	
	// 2. 강의시간표 목록
	@GetMapping("/all/lecture/getLectureScheduleListByLectureNo")
	public String getLectureScheduleListByLectureNo(Model model
													,@RequestParam(name = "currentPage", defaultValue = "1") int currentPage  // 디폴트값 설정, 자동형변환
													,@RequestParam(name = "lectureNo", defaultValue = "1") int lectureNo  // 디폴트값 설정, 자동형변환
													,@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage) {
		log.debug(A.W +"[LectureScheduleController.student.lecture.getLectureScheduleListByLectureNo.currentPage] currentPage : " +currentPage +A.R);
		log.debug(A.W +"[LectureScheduleController.student.lecture.getLectureScheduleListByLectureNo.rowPerPage] rowPerPage : " +rowPerPage +A.R);
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
	
	// 3. 강의 시간표 삭제 -> 강의번호 받아오기 수정
	@GetMapping("/manager/lecture/removeLectureSchedule")
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
		
		return "redirect:/all/lecture/getLectureScheduleListByLectureNo";
	}
	
	// 4. 강의 시간표 수정 Form
	@GetMapping("/manager/lecture/modifyLectureScheduleForm")
	public String modifyLectureScheduleForm(Model model
										  , @RequestParam(name = "lectureScheduleDate") String lectureScheduleDate
			  							  , @RequestParam(name = "lectureNo") int lectureNo) {

		log.debug(A.A + "[LectureScheduleController.modifyLectureScheduleForm] 실행" + A.R);
		
		log.debug(A.A + "[LectureScheduleController.modifyLectureScheduleForm] lectureScheduleDate : " + lectureScheduleDate + A.R);
		log.debug(A.A + "[LectureScheduleController.modifyLectureScheduleForm] lectureNo : " + lectureNo + A.R);
		
		LectureSchedule lectureScheduleOne = lectureScheduleService.modifyLectureScheduleForm(lectureScheduleDate, lectureNo);
		
		log.debug(A.A + "[LectureScheduleController.modifyLectureScheduleForm] lectureScheduleOne : " + lectureScheduleOne + A.R);

		model.addAttribute("lectureScheduleOne", lectureScheduleOne);
		
		return "/lecture/modifyLectureSchedule";
	}
	
	// 4-1. 강의 시간표 수정 Action
	@PostMapping("/manager/lecture/modifyLectureScheduleAction")
	public String modifyLectureScheduleAction(@RequestParam(name = "lectureNo", defaultValue = "0") int lectureNo
										    , @RequestParam(name = "lectureScheduleDate", defaultValue = "0") String lectureScheduleDate
										    , @RequestParam(name = "lectureScheduleStartDate", defaultValue = "0") String lectureScheduleStartDate
										    , @RequestParam(name = "lectureScheduleEndDate", defaultValue = "0") String lectureScheduleEndDate) {
		
		log.debug(A.A + "[LectureScheduleController.modifyLectureScheduleAction] lectureNo : " + lectureNo + A.R);
		log.debug(A.A + "[LectureScheduleController.modifyLectureScheduleAction] lectureScheduleDate : " + lectureScheduleDate + A.R);
		log.debug(A.A + "[LectureScheduleController.modifyLectureScheduleAction] lectureScheduleStartDate : " + lectureScheduleStartDate + A.R);
		log.debug(A.A + "[LectureScheduleController.modifyLectureScheduleAction] lectureScheduleEndDate : " + lectureScheduleEndDate + A.R);
		
		LectureSchedule lectureSchedule = new LectureSchedule();
		lectureSchedule.setLectureNo(lectureNo);
		lectureSchedule.setLectureScheduleDate(lectureScheduleDate);
		lectureSchedule.setLectureScheduleStartDate(lectureScheduleStartDate);
		lectureSchedule.setLectureScheduleEndDate(lectureScheduleEndDate);
		
		int row = lectureScheduleService.modifyLectureScheduleAction(lectureSchedule);
		
		if(row == 1) {
			log.debug(A.A + "[LectureScheduleController.modifyLectureScheduleAction] lecture_schedule 수정 성공" + A.R);
		} else {
			log.debug(A.A + "[LectureScheduleController.modifyLectureScheduleAction] lecture_schedule 수정 실패" + A.R);
		}  
		
		return "redirect:/all/lecture/getLectureScheduleListByLectureNo";
	}
	
	//***************출석*******************//
	//1.강의별 전체 학생 출석 목록 - 운영자,강사
	@GetMapping("/teacher/lecture/getAttendList")
	public String getAttendList(Model model
							,@RequestParam(name = "lectureNo") int lectureNo
							,@RequestParam(name= "attendDate", defaultValue = "") String attendDate
							,Map<String,Object> map) {
		log.debug(A.W +"[LectureScheduleController.teacher.lecture.getAttendList.param] model(전체학생출석목록실행) : " +model +A.R);
		log.debug(A.W +"[LectureScheduleController.teacher.lecture.getAttendList.param] lectureNo(강의번호) : " +lectureNo +A.R);
		if(attendDate == "") {
			attendDate = "2022-07-01";
		}
		log.debug(A.W +"[LectureScheduleController.teacher.lecture.getAttendList.param] attendDate(출석날짜) : " +attendDate +A.R);
		
		//출석 서비스 호출
		Map<String,Object> paramMap = attendService.getAttendList(lectureNo, attendDate);
		model.addAttribute("lectureNo", paramMap.get("lectureNo"));
		model.addAttribute("attendList", paramMap.get("attendList"));
		model.addAttribute("lectureScheduleList", paramMap.get("lectureScheduleList"));
		log.debug(A.W +"[LectureScheduleController.teacher.lecture.getAttendList.map] map(서비스호출) : " +paramMap +A.R);
		log.debug(A.W +"[LectureScheduleController.teacher.lecture.getAttendList.model.lectureNo] lectureNo(model) : " +paramMap.get("lectureNo") +A.R);
		log.debug(A.W +"[LectureScheduleController.teacher.lecture.getAttendList.model.attendList] attendList(model) : " +paramMap.get("attendList") +A.R);
		log.debug(A.W +"[LectureScheduleController.teacher.lecture.getAttendList.model.lectureScheduleList] lectureScheduleList(model) : " +paramMap.get("lectureScheduleList") +A.R);
		
		return "/lecture/getAttendList";
		
	}
	//2.수강중인 강의별 출석 확인 - 학생
	@GetMapping("/student/lecture/getStudentAttendOne")
	public String getStudentAttendOne(Model model,HttpSession session) {
		log.debug(A.W +"[LectureScheduleController.teacher.lecture.getStudentAttendOne.param] model(전체학생출석목록실행) : " +model +A.R);
		//session에 memberId와 lectureNo 요청
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		String memberId = loginMember.getMemberId();
		int lectureNo = (int)session.getAttribute("sessionLectureNo");
		log.debug(A.W +"[LectureScheduleController.teacher.lecture.getStudentAttendOne.param] lectureNo(강의번호) : " +lectureNo +A.R);
		log.debug(A.W +"[LectureScheduleController.teacher.lecture.getStudentAttendOne.param] memberId(학생아이디) : " +memberId +A.R);
		
		//출석 서비스 호출
		Map<String,Object> paramMap = attendService.getStudentAttendOne(lectureNo, memberId);
		//model에 값 추가
		model.addAttribute("lectureNo", lectureNo);
		model.addAttribute("memberId", memberId);
		model.addAttribute("studentAttendList", paramMap.get("studentAttendList"));
		log.debug(A.W +"[LectureScheduleController.teacher.lecture.getStudentAttendOne.map] map(서비스호출) : " +paramMap +A.R);
		log.debug(A.W +"[LectureScheduleController.teacher.lecture.getStudentAttendOne.model.lectureNo] lectureNo(model) : " +lectureNo +A.R);
		log.debug(A.W +"[LectureScheduleController.teacher.lecture.getStudentAttendOne.model.memberId] memberId(model) : " + memberId +A.R);
		log.debug(A.W +"[LectureScheduleController.teacher.lecture.getStudentAttendOne.model.studentAttendList] studentAttendList(model) : " +paramMap.get("studentAttendList") +A.R);

		return "/lecture/getStudentAttendOne";
	}
	
	//3.학생 출석 삽입
	//폼
	@GetMapping("/manager/lecture/addAttend")
	public String addAttend(Model model
						,@RequestParam(name ="attendDate") String attendDate
						,@RequestParam(name = "lectureNo") int lectureNo) {
		log.debug(A.W +"[LectureScheduleController.manager.lecture.addAttend.param] model(controller실행) : " + model +A.R);
		
		//값 가져와서 저장
		Map<String,Object> map = new HashMap<>();
		map.put("attendDate", attendDate);
		map.put("lectureNo", lectureNo);
		log.debug(A.W +"[LectureScheduleController.manager.lecture.addAttend.map] map : " + map +A.R);
		log.debug(A.W +"[LectureScheduleController.manager.lecture.addAttend.attendDate] attendDate : " + attendDate +A.R);
		log.debug(A.W +"[LectureScheduleController.manager.lecture.addAttend.lectureNo] lectureNo : " + lectureNo +A.R);
		
		//service 호출
		Map<String,Object> returnMap = attendService.addAttendForm(map);
		model.addAttribute("lectureList", returnMap.get("lectureList"));
		model.addAttribute("studentLectureList", returnMap.get("studentLectureList"));
		model.addAttribute("lectureScheduleList", returnMap.get("lectureScheduleList"));
		model.addAttribute("attendDate", returnMap.get("attendDate"));
		model.addAttribute("lectureNo", returnMap.get("lectureNo"));
		model.addAttribute("attendList",returnMap.get("attendList"));
		log.debug(A.W +"[LectureScheduleController.manager.lecture.addAttend.returnMap] returnMap : " + returnMap +A.R);
		
		return "lecture/addAttend";
	}
	//액션
	@PostMapping("/manager/lecture/addAttend")
	public String addAttend(Attend attend) {
		log.debug(A.W +"[LectureScheduleController.manager.lecture.addAttend.attend] attend(출석 삽입실행) : " + attend +A.R);
		
		//서비스 호출
		int row = attendService.addAttend(attend);
		if(row ==1) { 
			log.debug(A.W +"[LectureScheduleController.manager.lecture.addAttend.row] row(출석 삽입 완료) : " + row +A.R);
		}else {
			log.debug(A.W +"[LectureScheduleController.manager.lecture.addAttend.row] 출석 삽입 실패 " +A.R);
		}
		
		return "redirect:/teacher/lecture/getAttendList";
		
	}
	//4.학생 출석 수정
	
	//5.학생 출석 삭제
}
