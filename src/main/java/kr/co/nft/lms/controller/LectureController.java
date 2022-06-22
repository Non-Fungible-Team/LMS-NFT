package kr.co.nft.lms.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import kr.co.nft.lms.service.LectureService;
import kr.co.nft.lms.service.SubjectService;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Lecture;
import kr.co.nft.lms.vo.LectureRoom;
import kr.co.nft.lms.vo.Subject;
import kr.co.nft.lms.vo.TeacherLecture;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LectureController {

	@Autowired private LectureService lectureService;
  
	@Autowired private SubjectService subjectService;
	
	// form - subject 테이블 전체 리스트, subject 테이블 데이터 입력
	@GetMapping("/teacher/subjectList")
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
		
		return "redirect:/teacher/subjectList";
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
		
		return "redirect:/teacher/subjectList";
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
		List<Subject> subjectList = lectureService.addLectureForm();
		//List<LectureRoom> lectureRoomList = 
		model.addAttribute("subjectList",subjectList);
		//model.addAttribute("lectureRoom",lectureRoomList);
		return "lecture/addLecture"; //jsp로 이동
	}
	
	@PostMapping("/manager/lecture/addLecture")
	public String addLecture(Lecture lecture,TeacherLecture teacherLecture) {
		log.debug(A.W +"[LectureController.manager.lecture/addLecture.lecture] lecture : " + lecture +A.R);
		log.debug(A.W +"[LectureController.manager.lecture/addLecture.teacherLecture] teacherLecture : " + teacherLecture +A.R);
		
		int row = lectureService.addLecture(lecture, teacherLecture);
		log.debug(A.W +"[LectureController.manager.lecture/addLecture.row] row : " + row +A.R);
		
		return "redirect:/teacher/lecture/getLectureByPage"; //addLecture로 이동
	}
	
	//3. 강의 수정 폼
	@GetMapping("/teacher/lecture/modifyLecture")
	public String modifyLecture(Model model
								,@RequestParam(name="lectureNo") int lectureNo) {
		log.debug(A.W +"[LectureController.teacher.lecture/modifyLecture.param] lectureNo : " + lectureNo +A.R);
		//받아온 값 저장 객체 생성
		Map<String,Object> map = new HashMap<>();
		map.put("lectureNo", lectureNo); //번호 값 저장
		log.debug(A.W +"[LectureController.teacher.lecture/modifyLecture.map] map : " + map +A.R);
		
		//결과값 저장 객체 생성 -> 서비스에 저장된 값 가져와서 모델객체에 저장
		Map<String,Object> returnMap =lectureService.modifyLectureForm(map);
		model.addAttribute("lecture",returnMap.get("lecture")); 
		log.debug(A.W +"[LectureController.teacher.lecture/modifyLecture.returnMap] returnMap : " + returnMap +A.R);
		
		return "/lecture/modifyLecture";
	}
	//수정 액션
	@PostMapping("/teacher/lecture/modifyLecture")
	public String modifyLecture(Lecture lecture) {
		log.debug(A.W +"[LectureController.teacher.lecture/modifyLecture.lecture] lecture : " + lecture +A.R);
		
		int row = lectureService.modifyLecture(lecture);
		log.debug(A.W +"[LectureController.teacher.lecture/modifyLecture.row] row : " + row +A.R);
		
		return "redirect:/teacher/lecture/getLectureByPage";

	}
	/*
	//4.강의 삭제
	@GetMapping("/teacher/lecture/removeLecture")
	public String removeLecture(@RequestParam(name = "lectureNo") int lectureNo) {
		log.debug(A.A + "[LectureController.teacher.lecture/removeLecture.lecture] lecture : " + lecture + A.R);
		
		int row = lectureService.removeLecture(lecture);
		log.debug(A.A + "[LectureController.teacher.lecture/removeLecture.row] row : " + row + A.R);
		
		return "/lecture/getLectureByPage";
	}
	*/
	/*
	// 5 & 6.학생 강의 목록
	@GetMapping("/teacher/lecture/getStudentLectureByPage")
	public String getStudentLectureByPage(@RequestParam(name = "currentPage",defaultValue = "1") int currentPage
										, @RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage) {
		
		log.debug(A.A + "[LectureController.getStudentLectureByPage] currentPage : " + currentPage + A.R);
		log.debug(A.A + "[LectureController.getStudentLectureByPage] rowPerPage : " + rowPerPage + A.R);
		
		Map<String,Object> returnMap = lectureService.getStudentLectureByPage(currentPage, rowPerPage);
		// model.addAttribute("lecture",returnMap.get("lecture")); 
		return "";
	}
	*/
	// 5-2.학생-강의 삽입
	/*@PostMapping("a")
	public String addStudentLecture() {
		
		return "";
	}
	
	// 5-3. 학생-강의 목록 수정 폼
	@GetMapping("b")
	public String modifyStudentLectureForm() {
		
		return "";
	}
	
	// 학생-강의 목록 수정 액션
	@PostMapping("c")
	public String modifyStudentLecture() {
		
		return "";
	}
	
	// 6. 운영자- 강의 삽입
	@GetMapping("d")
	public String addManagerLecture() {
		
		return "";
	}*/
}