package kr.co.nft.lms.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.nft.lms.service.LectureRoomService;
import kr.co.nft.lms.service.LectureService;
import kr.co.nft.lms.service.SubjectService;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Lecture;
import kr.co.nft.lms.vo.LectureRoom;
import kr.co.nft.lms.vo.Student;
import kr.co.nft.lms.vo.StudentLecture;
import kr.co.nft.lms.vo.Subject;
import kr.co.nft.lms.vo.TeacherLecture;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LectureController {

	@Autowired private LectureService lectureService;
  
	@Autowired private SubjectService subjectService;
	
	@Autowired private LectureRoomService lectureRoomService;
	
	// form - subject 테이블 전체 리스트, subject 테이블 데이터 입력
	@GetMapping("/teacher/lecture/subjectList")
	public String subjectList(Model model) {
		
		List<Subject> subjectList = subjectService.getSubjectList();
		
		log.debug(A.A + "[LectureController.subjectList] subjectList : " + subjectList + A.R);
		
		model.addAttribute("subjectList", subjectList);
		
		log.debug(A.A + "[LectureController.subjectList] model : " + model + A.R);
		
		return "/lecture/subjectList";
	}
	
	// action - subject 테이블 데이터 입력
	@PostMapping("/teacher/addSubjectOne")
	public String addSubjectOne(@RequestParam(name = "subjectName") String subjectName
								, @RequestParam(name = "subjectContent") String subjectContent) {
		
		log.debug(A.A + "[LectureController.addSubjectOne] subjectName : " + subjectName + A.R);
		log.debug(A.A + "[LectureController.addSubjectOne] subjectContent : " + subjectContent + A.R);
		
		Subject subject = new Subject();
		subject.setSubjectName(subjectName);
		subject.setSubjectContent(subjectContent);
		
		log.debug(A.A + "[LectureController.addSubjectOne] subject : " + subject + A.R);
		
		int row = subjectService.addSubjectOne(subject);
		
		log.debug(A.A + "[LectureController.addSubjectOne] row : " + row + A.R);
		
		if(row == 1) {
			log.debug(A.A + "[LectureController.addSubjectOne] subject 입력 성공" + A.R);
		} else {
			log.debug(A.A + "[LectureController.addSubjectOne] subject 입력 실패" + A.R);
		}
		
		return "redirect:/teacher/lecture/subjectList";
	}
	
	// action - subject 테이블 데이터 삭제
	@GetMapping("/teacher/removeSubjectOne")
	public String removeSubjectOne(@RequestParam(name = "subjectNo") int subjectNo) {
		
		log.debug(A.A + "[LectureController.removeSubjectOne] subjectNo : " + subjectNo + A.R);
		
		int row = subjectService.deleteSubjectOne(subjectNo);
		
		log.debug(A.A + "[LectureController.removeSubjectOne] row : " + row + A.R);
		
		if(row == 1) {
			log.debug(A.A + "[LectureController.removeSubjectOne] subject 삭제 성공" + A.R);
		} else {
			log.debug(A.A + "[LectureController.removeSubjectOne] subject 삭제 실패" + A.R);
		}
		
		return "redirect:/teacher/lecture/subjectList";
	}

	////////////* 강의 *////////////
	//1. 강의 목록(상세보기)
	@GetMapping("/teacher/lecture/getLectureByPage")
	public String getLectureByPage(Model model
			,@RequestParam(name = "currentPage",defaultValue = "1") int currentPage  // 디폴트값 설정, 자동형변환
			,@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage) {
		//디버깅코드
		log.debug(A.W +"[LectureController.teacher.lecture.getLectureByPage.model] model : " + model +A.R);
		log.debug(A.W +"[LectureController.teacher.lecture.getLectureByPage.currentPage] currentPage : " +currentPage +A.R);
		log.debug(A.W +"[LectureController.teacher.lecture.getLectureByPage.rowPerPage] rowPerPage : " +rowPerPage +A.R);
		
		Map<String,Object> map = lectureService.getLectureByPage(currentPage, rowPerPage); //강의목록 서비스 호출해서 map객체에 저장
		 //서비스에 저장된 값 가져와서 모델객체에 저장
		model.addAttribute("lectureList", map.get("lectureList")); //강의목록
		model.addAttribute("currentPage", map.get("currentPage")); //현재페이지
		model.addAttribute("lastPage",map.get("lastPage") ); //마지막페이지
		//디버깅코드
		log.debug(A.W +"[LectureController.getLectureByPage.map] map(Service 호출) : " +map +A.R);
		log.debug(A.W +"[LectureController.getLectureByPage.model] model.lectureList : " +map.get("lectureList") +A.R);
		log.debug(A.W +"[LectureController.getLectureByPage.model] model.currentPage : " +map.get("currentPage") +A.R);
		log.debug(A.W +"[LectureController.getLectureByPage.model] model.lastPage : " +map.get("lastPage") +A.R);
		
		return "/lecture/getLectureByPage"; //jsp로 이동
		
	}
	
	//2.강의 / 강의-강사 삽입
	@GetMapping("/manager/lecture/addLecture")
	public String addLecture(Model model) {
		log.debug(A.W +"[LectureController.manager.lecture/addLecture.param] model : " + model +A.R);
		Map<String,Object> map = lectureService.addLectureForm(); 
		model.addAttribute("returnMap",map.get("returnMap"));
		model.addAttribute("subjectList", map.get("subjectList"));
		model.addAttribute("lectureRoomList", map.get("lectureRoomList"));
		model.addAttribute("teacherList", map.get("teacherList"));
		
		log.debug(A.W +"[LectureController.manager.lecture/addLecture.map] map : " + map +A.R);
		
		return "lecture/addLecture"; //jsp로 이동
	}
	
	@PostMapping("/manager/lecture/addLecture")
	public String addLecture(Lecture lecture) {
		log.debug(A.W +"[LectureController.manager.lecture/addLecture.lecture] lecture : " + lecture +A.R);
		
		int row = lectureService.addLecture(lecture);
		log.debug(A.W +"[LectureController.manager.lecture/addLecture.row] row : " + row +A.R);
		
		return "redirect:/teacher/lecture/getLectureByPage"; //addLecture로 이동
	}
	
	//3. 강의 수정 폼
	@GetMapping("/manager/lecture/modifyLecture")
	public String modifyLecture(Model model
								,@RequestParam(name="lectureNo") int lectureNo) {
		log.debug(A.W +"[LectureController.manager.lecture/modifyLecture.param] lectureNo : " + lectureNo +A.R);
		//받아온 값 저장 객체 생성
		Map<String,Object> Parammap = new HashMap<>();
		Parammap.put("lectureNo", lectureNo); //번호 값 저장
		log.debug(A.W +"[LectureController.manager.lecture/modifyLecture.Parammap] Parammap : " + Parammap +A.R);
		
		//결과값 저장 객체 생성 -> 서비스에 저장된 값 가져와서 모델객체에 저장
		Map<String,Object> map =lectureService.modifyLectureForm(Parammap);
		model.addAttribute("lecture",map.get("lecture")); 
		model.addAttribute("subjectList", map.get("subjectList"));
		model.addAttribute("lectureRoomList", map.get("lectureRoomList"));
		model.addAttribute("teacherList", map.get("teacherList"));
		log.debug(A.W +"[LectureController.manager.lecture/modifyLecture.map] map : " + map +A.R);
		
		return "/lecture/modifyLecture";
	}
	//수정 액션
	@PostMapping("/manager/lecture/modifyLecture")
	public String modifyLecture(Lecture lecture, TeacherLecture teacherLecture) {
		log.debug(A.W +"[LectureController.manager.lecture/modifyLecture.lecture] lecture : " + lecture +A.R);
		log.debug(A.W +"[LectureController.manager.lecture/modifyLecture.teacherLecture] teacherLecture : " + teacherLecture +A.R);
		
		int row = lectureService.modifyLecture(lecture, teacherLecture);
		log.debug(A.W +"[LectureController.manager.lecture/modifyLecture.row] row : " + row +A.R);
		
		return "redirect:/teacher/lecture/getLectureByPage";

	}
	
	//4.강의 삭제
	@GetMapping("/manager/lecture/removeLecture")
	public String removeLecture(@RequestParam(name = "lectureNo") int lectureNo) {
		log.debug(A.A + "[LectureController.teacher.lecture/removeLecture.lectureNo] lectureNo : " + lectureNo + A.R);
		
		int row = lectureService.removeLecture(lectureNo);
		log.debug(A.A + "[LectureController.teacher.lecture/removeLecture.row] row : " + row + A.R);
		
		return "redirect:/teacher/lecture/getLectureByPage";
	}
	
	// 5 & 6.학생 강의 목록
	@GetMapping("/teacher/lecture/getStudentLectureByPage")
	public String getStudentLectureByPage(Model model
										, @RequestParam(name = "lectureNo", defaultValue = "0") int lectureNo
										, @RequestParam(name = "currentPage", defaultValue = "1") int currentPage
										, @RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage) {
		
		log.debug(A.A + "[LectureController.getStudentLectureByPage] lectureNo : " + lectureNo + A.R);
		log.debug(A.A + "[LectureController.getStudentLectureByPage] currentPage : " + currentPage + A.R);
		log.debug(A.A + "[LectureController.getStudentLectureByPage] rowPerPage : " + rowPerPage + A.R);
		
		Map<String,Object> returnMap = lectureService.getStudentLectureByPage(lectureNo, currentPage, rowPerPage);
		
		log.debug(A.A + "[LectureController.getStudentLectureByPage] returnMap : " + returnMap + A.R);
		
		model.addAttribute("studentLectureList", returnMap.get("studentLectureList")); 
		model.addAttribute("currentPage", returnMap.get("currentPage")); 
		model.addAttribute("rowPerPage", returnMap.get("rowPerPage")); 
		
		log.debug(A.A + "[LectureController.getStudentLectureByPage] model : " + model + A.R);
		
		return "/lecture/getStudentLectureByPage";
	}
	
	// 5 & 6.학생 강의 전체 목록
	@GetMapping("/manager/lecture/getStudentLectureAllByPage")
	public String getStudentLectureAllByPage(Model model
											, @RequestParam(name = "currentPage", defaultValue = "1") int currentPage
											, @RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage) {
		
		log.debug(A.A + "[LectureController.getStudentLectureAllByPage] currentPage : " + currentPage + A.R);
		log.debug(A.A + "[LectureController.getStudentLectureAllByPage] rowPerPage : " + rowPerPage + A.R);
		
		Map<String,Object> returnMap = lectureService.getStudentLectureAllByPage(currentPage, rowPerPage);
		
		log.debug(A.A + "[LectureController.getStudentLectureAllByPage] returnMap : " + returnMap + A.R);
		
		model.addAttribute("studentLectureAllList", returnMap.get("studentLectureAllList")); 
		model.addAttribute("currentPage", returnMap.get("currentPage")); 
		model.addAttribute("rowPerPage", returnMap.get("rowPerPage")); 
		
		log.debug(A.A + "[LectureController.getStudentLectureAllByPage] model : " + model + A.R);
		
		return "/lecture/getStudentLectureAllByPage";
	}
	
	// 5-2. 학생-강의 삽입 폼
	@GetMapping("/teacher/lecture/addStudentLecture")
	public String addStudentLecture(Model model) {
		
		log.debug(A.A + "[LectureController.addStudentLecture] 실행" + A.R);		
		
		List<Student> studentList = lectureService.addStudentLecture();
		
		log.debug(A.A + "[LectureController.addStudentLecture] studentList : " + studentList + A.R);
		
		model.addAttribute("studentList", studentList);
		
		return "/lecture/addStudentLecture";
	}
	
	// 5-2. 학생-강의 삽입 액션
	@PostMapping("/teacher/lecture/addStudentLectureAction")
		public String addStudentLectureAction(@RequestParam(name = "lectureNo", defaultValue = "0") int lectureNo
									  		, @RequestParam(name = "memberId") String memberId
								  			, @RequestParam(name = "studentLectureJob") String studentLectureJob
								  			, @RequestParam(name = "studentLectureLegistrationDate") String studentLectureLegistrationDate) {
		
		lectureNo = 1;
		memberId = "student2";
				
		log.debug(A.A + "[LectureController.addStudentLectureAction] lectureNo : " + lectureNo + A.R);
		log.debug(A.A + "[LectureController.addStudentLectureAction] memberId : " + memberId + A.R);
		log.debug(A.A + "[LectureController.addStudentLectureAction] studentLectureJob : " + studentLectureJob + A.R);
		log.debug(A.A + "[LectureController.addStudentLectureAction] studentLectureLegistrationDate : " + studentLectureLegistrationDate + A.R);
		
		StudentLecture studentLecture = new StudentLecture();
		studentLecture.setLectureNo(lectureNo);
		studentLecture.setMemberId(memberId);
		studentLecture.setStudentLectureJob(studentLectureJob);
		studentLecture.setStudentLectureLegistrationDate(studentLectureLegistrationDate);
		
		log.debug(A.A + "[LectureController.studentLecture] studentLecture : " + studentLecture + A.R);
		
		int row = lectureService.addStudentLectureAction(studentLecture);
		
		if(row == 1) {
			log.debug(A.A + "[LectureController.addStudentLectureAction] student_lecture 입력 성공" + A.R);
		} else {
			log.debug(A.A + "[LectureController.addStudentLectureAction] student_lecture 입력 실패" + A.R);
		}

		return "redirect:/teacher/lecture/getStudentLectureByPage";
	}
	
	// 5-3. 학생-강의 목록 수정 폼
	@GetMapping("/teacher/lecture/modifyStudentLecture")
	public String modifyStudentLectureForm(Model model
										 , @RequestParam(name = "lectureNo", defaultValue = "0") int lectureNo
	  									 , @RequestParam(name = "memberId") String memberId) {
		
		log.debug(A.A + "[LectureController.modifyStudentLectureForm] lectureNo : " + lectureNo + A.R);
		log.debug(A.A + "[LectureController.modifyStudentLectureForm] memberId : " + memberId + A.R);
		
		// 학생 이름 리스트
		List<Student> studentTotalList = lectureService.addStudentLecture();
		
		// 학생 강의 One 리스트 + 강의 이름과 번호 리스트
		Map<String , Object> studentLectureOneMap  = lectureService.modifyStudentLectureForm(lectureNo, memberId);
		
		log.debug(A.A + "[LectureController.modifyStudentLectureForm] studentTotalList : " + studentTotalList + A.R);
		log.debug(A.A + "[LectureController.modifyStudentLectureForm] lectureNoNameList : " + studentLectureOneMap.get("lectureNoNameList") + A.R);
		log.debug(A.A + "[LectureController.modifyStudentLectureForm] studentLectureOne : " + studentLectureOneMap.get("studentLectureOne") + A.R);
		
		model.addAttribute("studentTotalList", studentTotalList);
		model.addAttribute("lectureNoNameList", studentLectureOneMap.get("lectureNoNameList"));
		model.addAttribute("studentLectureOne", studentLectureOneMap.get("studentLectureOne"));
		
		return "/lecture/modifyStudentLecture";
	}
	
	
	// 학생-강의 목록 수정 액션
	@PostMapping("/teacher/lecture/modifyStudentLectureAction")
	public String modifyStudentLectureAction(@RequestParam(name = "lectureNo", defaultValue = "0") int lectureNo
											, @RequestParam(name = "memberId") String memberId
											, @RequestParam(name = "studentLectureJob") String studentLectureJob
											, @RequestParam(name = "studentLectureLegistrationDate") String studentLectureLegistrationDate
											, @RequestParam(name = "studentLectureEndDate", defaultValue = "null") String studentLectureEndDate
											, @RequestParam(name = "studentLectureScore", defaultValue = "0") int studentLectureScore) {
		
		log.debug(A.A + "[LectureController.modifyStudentLectureAction] lectureNo : " + lectureNo + A.R);
		log.debug(A.A + "[LectureController.modifyStudentLectureAction] memberId : " + memberId + A.R);
		log.debug(A.A + "[LectureController.modifyStudentLectureAction] studentLectureJob : " + studentLectureJob + A.R);
		log.debug(A.A + "[LectureController.modifyStudentLectureAction] studentLectureLegistrationDate : " + studentLectureLegistrationDate + A.R);
		log.debug(A.A + "[LectureController.modifyStudentLectureAction] studentLectureEndDate : " + studentLectureEndDate + A.R);
		log.debug(A.A + "[LectureController.modifyStudentLectureAction] studentLectureScore : " + studentLectureScore + A.R);
		
		StudentLecture studentLecture = new StudentLecture();
		studentLecture.setLectureNo(lectureNo);
		studentLecture.setMemberId(memberId);
		studentLecture.setStudentLectureJob(studentLectureJob);
		studentLecture.setStudentLectureLegistrationDate(studentLectureLegistrationDate);
		studentLecture.setStudentLectureEndDate(studentLectureEndDate);
		studentLecture.setStudentLectureScore(studentLectureScore);
		
		log.debug(A.A + "[LectureController.modifyStudentLectureAction] studentLecture : " + studentLecture + A.R);
		
		int row = lectureService.modifyStudentLectureAction(studentLecture);
		
		log.debug(A.A + "[LectureController.modifyStudentLectureAction] row : " + row + A.R);
		
		if(row == 1) {
			log.debug(A.A + "[LectureController.modifyStudentLectureAction] student_lecture 수정 성공" + A.R);
		} else {
			log.debug(A.A + "[LectureController.modifyStudentLectureAction] student_lecture 수정 실패" + A.R);
		}
		
		return "redirect:/manager/lecture/getStudentLectureAllByPage";
	}
	
	// 5-4. 학생 강의 삭제
	@GetMapping("/teacher/lecture/removeStudentLectureAction")
	public String removeStudentLectureAction(@RequestParam(name = "lectureNo", defaultValue = "0") int lectureNo
											, @RequestParam(name = "memberId") String memberId) {
		
		log.debug(A.A + "[LectureController.removeStudentLectureAction] lectureNo : " + lectureNo + A.R);
		log.debug(A.A + "[LectureController.removeStudentLectureAction] memberId : " + memberId + A.R);
		
		int row = lectureService.removeStudentLectureAction(lectureNo, memberId);
		
		if(row == 1) {
			log.debug(A.A + "[LectureController.removeStudentLectureAction] student_lecture 삭제 성공" + A.R);
		} else {
			log.debug(A.A + "[LectureController.removeStudentLectureAction] student_lecture 삭제 실패" + A.R);
		}
		
		return "redirect:/manager/lecture/getStudentLectureAllByPage";
	}
		
	//6.강사_강의 목록
	@GetMapping("/manager/lecture/getManagerLectureByPage")
	public String getManagerLectureByPage(Model model
					,@RequestParam(name = "currentPage",defaultValue = "1") int currentPage  // 디폴트값 설정, 자동형변환
					,@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage) {
		//디버깅코드
		log.debug(A.W +"[LectureController.teacher.lecture.getLectureByPage.model] model : " + model +A.R);
		log.debug(A.W +"[LectureController.teacher.lecture.getLectureByPage.currentPage] currentPage : " +currentPage +A.R);
		log.debug(A.W +"[LectureController.teacher.lecture.getLectureByPage.rowPerPage] rowPerPage : " +rowPerPage +A.R);
			
		Map<String,Object> map = lectureService.getManagerLectureByPage(currentPage, rowPerPage); //강사_강의목록 서비스 호출해서 map객체에 저장
		 //서비스에 저장된 값 가져와서 모델객체에 저장 
		model.addAttribute("teacherLectureList", map.get("teacherLectureList")); //강사_강의목록
		model.addAttribute("currentPage", map.get("currentPage")); //현재페이지
		model.addAttribute("lastPage",map.get("lastPage") ); //마지막페이지
		//디버깅코드
		log.debug(A.W +"[LectureController.getLectureByPage.map] map(Service 호출) : " +map +A.R);
		log.debug(A.W +"[LectureController.getLectureByPage.model] model.teacherLectureList : " +map.get("teacherLectureList") +A.R);
		log.debug(A.W +"[LectureController.getLectureByPage.model] model.currentPage : " +map.get("currentPage") +A.R);
		log.debug(A.W +"[LectureController.getLectureByPage.model] model.lastPage : " +map.get("lastPage") +A.R);
			
		return "/lecture/getManagerLectureByPage"; //jsp로 이동

		}
	
	// ------------------------------------------------ lecture_room
	// form - lecture_room 테이블 전체 리스트, lecture_room 테이블 데이터 입력
	@GetMapping("/teacher/lecture/getLectureRoomList")
	public String getLectureRoomList(Model model) {
				
		List<LectureRoom> lectureRoomList = lectureRoomService.getLectureRoomList();
		
		log.debug(A.A + "[LectureController.getLectureRoomList] lectureRoomList : " + lectureRoomList + A.R);
		
		model.addAttribute("lectureRoomList", lectureRoomList);
		
		log.debug(A.A + "[LectureController.getLectureRoomList] model : " + model + A.R);
		
		return "/lecture/getLectureRoomList";
	}
	
	// action - lecture_room 테이블 데이터 입력
	@PostMapping("/teacher/lecture/addLectureRoom")
	public String addLectureRoom(@RequestParam(name = "lectureRoomName") String lectureRoomName
								, @RequestParam(name = "lectureRoomLocation") String lectureRoomLocation
								, @RequestParam(name = "lectureRoomPeople", defaultValue = "0") int lectureRoomPeople) {
		
		log.debug(A.A + "[LectureController.addLectureRoom] lectureRoomName : " + lectureRoomName + A.R);
		log.debug(A.A + "[LectureController.addLectureRoom] lectureRoomLocation : " + lectureRoomLocation + A.R);
		log.debug(A.A + "[LectureController.addLectureRoom] lectureRoomPeople : " + lectureRoomPeople + A.R);
		
		LectureRoom lectureRoom = new LectureRoom();
		lectureRoom.setLectureRoomName(lectureRoomName);
		lectureRoom.setLectureRoomLocation(lectureRoomLocation);
		lectureRoom.setLectureRoomPeople(lectureRoomPeople);
		
		int row = lectureRoomService.addLectureRoom(lectureRoom);
		
		log.debug(A.A + "[LectureController.addLectureRoom] row : " + row + A.R);
		
		if(row == 1) {
			log.debug(A.A + "[LectureController.addLectureRoom] lecture_room 입력 성공" + A.R);
		} else {
			log.debug(A.A + "[LectureController.addLectureRoom] lecture_room 입력 실패" + A.R);
		}
		
		return "redirect:/teacher/lecture/getLectureRoomList";
	}
	
	// action - lecture_room 테이블 데이터 삭제
	@GetMapping("/teacher/lecture/removeLectureRoom")
	public String removeLectureRoom(@RequestParam(name = "lectureRoomName") String lectureRoomName) {
		
		log.debug(A.A + "[LectureController.removeLectureRoom] lectureRoomName : " + lectureRoomName + A.R);
		
		int row = lectureRoomService.removeLectureRoom(lectureRoomName);
		
		log.debug(A.A + "[LectureController.removeLectureRoom] row : " + row + A.R);
		
		if(row == 1) {
			log.debug(A.A + "[LectureController.removeLectureRoom] lecture_room 입력 성공" + A.R);
		} else {
			log.debug(A.A + "[LectureController.removeLectureRoom] lecture_room 입력 실패" + A.R);
		}
		
		return "redirect:/teacher/lecture/getLectureRoomList";
	}
}