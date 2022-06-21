package kr.co.nft.lms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.nft.lms.service.ExamService;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Exam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/exam")
public class ExamController {
	@Autowired private ExamService examService;
	
	// 시험 리스트
	@GetMapping("/getExamListByPage")
	public String getExamListByPage(Model model,
									Exam exam
									,@RequestParam(name="currentPage", defaultValue="1") int currentPage
									,@RequestParam(name="rowPerPage", defaultValue ="10") int rowPerPage) {
		Map<String, Object> returnMap = examService.getExamListByPage(currentPage, rowPerPage);
		log.debug(A.C + "[ExamController.getExamListByPage.param] returnMap: " + returnMap + A.R);
		model.addAttribute("examList", returnMap.get("examList"));
		model.addAttribute("examLastPage", returnMap.get("examLastPage"));
		model.addAttribute("currentPage", currentPage);
		return "/exam/getExamListByPage";
	}
		
	// 시험문제 보기
	@GetMapping("/getExamOne")
	public String getExamOne(Model model ,@RequestParam(name="examNo") int examNo) {
		log.debug(A.C + "[ExamController.getExamOne.param] examNo: " + examNo + A.R);
		Map<String, Object> map1 = examService.getExamOne(examNo); // 시험 정보 호출
		Map<String, Object> map2 = examService.getExamQuestionOne(examNo); // 시험 문제 정보 호출
		Map<String, Object> map3 = examService.getExamExampleOne(examNo); // 시험 보기 정보 호출
		
		model.addAttribute("examOneList", map1.get("examOneList"));
		model.addAttribute("examQuestionOneList", map2.get("examQuestionOneList"));
		model.addAttribute("examExampleOneList", map3.get("examExampleOneList"));
		return "/exam/getExamOne";
	}
		
		
	// 시험문제 등록
	@GetMapping("/addExam")
	public String addExam() {
		return "/exam/addExam";
	}
	@PostMapping("/addExam")
	public String addNotice(HttpServletRequest request, Exam exam) {
		log.debug(A.C + "[ExamController.addExam.param] request: " + request + A.R);
		log.debug(A.C + "[ExamController.addExam.param] exam: " + exam+ A.R);
		examService.addExam(exam);
		return "redirect:/exam/addExam";
	}
	
	
	

}
