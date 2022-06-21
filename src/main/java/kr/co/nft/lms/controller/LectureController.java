package kr.co.nft.lms.controller;
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
import kr.co.nft.lms.vo.Subject;
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
		 //서비스에 저장된 값 가져오서 모델객체에 저장
		model.addAttribute("lectureList", map.get("lectureList")); //강의목록
		model.addAttribute("currentPage", map.get("currentPage")); //현재페이지
		model.addAttribute("lastPage",map.get("lastPage") ); //마지막페이지
		//디버깅코드
		log.debug(A.W +"[LectureController.getLectureByPage.map] map(Service 호출) : " +map +A.R);
		log.debug(A.W +"[LectureController.getLectureByPage.model] model.lectureList : " +map.get("lectureList") +A.R);
		log.debug(A.W +"[LectureController.getLectureByPage.model] model.currentPage : " +map.get("currentPage") +A.R);
		log.debug(A.W +"[LectureController.getLectureByPage.model] model.lastPage : " +map.get("lastPage") +A.R);
		
		return "lecture/getLectureByPage"; //jsp로 이동
		
	}
	
}