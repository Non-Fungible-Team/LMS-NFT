package kr.co.nft.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
	
	// action - subject 테이블 데이터 삭제
}
