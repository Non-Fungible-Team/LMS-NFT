package kr.co.nft.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.nft.lms.service.LectureService;
import kr.co.nft.lms.service.SubjectService;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Subject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
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
		
		return "subjectList";
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
		
		return "redirect:/subjectList";
	}
	
	// action - subject 테이블 데이터 삭제
}
