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
@RequestMapping("/lecture")
public class LectureController {
	
	@Autowired
	private LectureService lectureService;
	@Autowired
	private SubjectService subjectService;
	
	// form - subject 테이블 전체 리스트, subject 테이블 데이터 입력
	@GetMapping("/subjectList")
	public String subjectList(Model model) {
		
		List<Subject> subjectList = subjectService.getSubjectList();
		
		log.debug(A.A + "[LectureController.subjectList.param] subjectList : " + subjectList + A.R);
		
		model.addAttribute("subjectList", subjectList);
		
		return "/lecture/subjectList";
	}
	
	// action - subject 테이블 데이터 입력
	@PostMapping("/addSubjectOne")
	public String addSubjectOne(@RequestParam(name = "subjectName") String subjectName
								, @RequestParam(name = "subjectContent") String subjectContent) {
		
		Subject subject = new Subject();
		subject.setSubjectName(subjectName);
		subject.setSubjectContent(subjectContent);
		
		log.debug(A.A + "[LectureController.addSubjectOne.param] subject : " + subject + A.R);
		
		int row = subjectService.addSubjectOne(subject);
		
		if(row == 1) {
			log.debug(A.A + "[LectureController.addSubjectOne.param] subject 입력 성공" + A.R);
		} else {
			log.debug(A.A + "[LectureController.addSubjectOne.param] subject 입력 실패" + A.R);
		}
		
		return "redirect:/lecture/subjectList";
	}
	
	// action - subject 테이블 데이터 삭제
	@GetMapping("/removeSubjectOne")
	public String removeSubjectOne(@RequestParam(name = "subjectNo") int subjectNo) {
		
		int row = subjectService.deleteSubjectOne(subjectNo);
		
		if(row == 1) {
			log.debug(A.A + "[LectureController.removeSubjectOne.param] subject 삭제 성공" + A.R);
		} else {
			log.debug(A.A + "[LectureController.removeSubjectOne.param] subject 삭제 실패" + A.R);
		}
		
		return "redirect:/lecture/subjectList";
	}
	
	////////////* 강의 *////////////
	//1. 강의 목록(상세보기)
	@GetMapping("/getLectureByPage")
	public String getLectureByPage(Model model
			,@RequestParam(name = "currentPage",defaultValue = "1") int currentPage  // 디폴트값 설정, 자동형변환
			,@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage) {
		Map<String,Object> map = lectureService.getLectureByPage(currentPage, rowPerPage); //강의목록 서비스 호출
		 //서비스에 저장된 값 가져오서 모델객체에 저장
		model.addAttribute("lectureList", map.get("lectureList"));
		model.addAttribute("currentPage", map.get("currentPage"));
		model.addAttribute("lastPage",map.get("lastPage") );
		log.debug(A.W +"[LectureController.getLectureByPage.model] model.lectureList : " +map.get("lectureList") +A.R);
		log.debug(A.W +"[LectureController.getLectureByPage.model] model.currentPage : " +map.get("currentPage") +A.R);
		log.debug(A.W +"[LectureController.getLectureByPage.model] model.lastPage : " +map.get("lastPage") +A.R);
		
		
		return "LectureList"; //jsp로 이동
		
	}
	
}
