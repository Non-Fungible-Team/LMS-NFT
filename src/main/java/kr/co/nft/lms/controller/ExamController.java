package kr.co.nft.lms.controller;

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
import kr.co.nft.lms.vo.ExamExample;
import kr.co.nft.lms.vo.ExamQuestion;
import kr.co.nft.lms.vo.Homework;
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
	public String getExamOne(Model model ,@RequestParam(name="examNo") int examNo, Map<String, Object> examQuestionOneList) {
		log.debug(A.C + "[ExamController.getExamOne.param] examNo: " + examNo + A.R);
		Map<String, Object> map1 = examService.getExamOne(examNo); // 시험 정보 호출
		map1 = examService.getExamOne(examNo);
		log.debug(A.C + "[ExamController.getExamOne.map1] examNo: " + examNo + A.R);
		Map<String, Object> map2 = examService.getExamQuestionOne(examNo); // 시험 문제 정보 호출
		
		model.addAttribute("examOne", map1.get("examOne"));	// 시험정보
		model.addAttribute("examQuestionOneList", map2.get("examQuestionOneList")); // 문제정보
		model.addAttribute("examExampleOneList", map2.get("examExampleOneList")); // 보기정보
		log.debug(A.C + "[ExamController.getExamOne.param] examExampleOneList: " + map2.get("examExampleOneList") + A.R);
		return "exam/getExamOne";
	}
		
		
	// 시험문제 등록
	@GetMapping("/addExam")
	public String addExam() {
		return "/exam/addExam";
	}
	@PostMapping("/addExam")
	public String addNotice(HttpServletRequest request, Exam exam, ExamQuestion examQuestion, ExamExample examExample) {
		log.debug(A.C + "[ExamController.addExam.param] request: " + request + A.R);
		log.debug(A.C + "[ExamController.addExam.param] exam: " + exam+ A.R);
		
		int row = examService.addExam(exam);
		examService.addExamQeustion(examQuestion);
		examService.addExamExample(examExample);
		if(row ==1) {
			log.debug(A.C +"ExamController.addExam 시험 입력 성공"+A.R);
		} else {
			log.debug(A.C +"ExamController.addExam 시험 입력 실패"+A.R);
		}
		return "redirect:/exam/getExamListByPage";
	}
	
	// 시험 수정 폼
	@GetMapping("/modifyExam")
	public String modifyExam(Model model, @RequestParam(name = "examNo") int examNo) {
		log.debug(A.C + "ExamController.modifyExam.param.examNo :" + examNo + A.R);

		Map<String, Object> returnMap = examService.getExamOne(examNo);
		log.debug(A.C + "ExamController.modifyExam.returnMap : " + returnMap + A.R);

		model.addAttribute("examOne", returnMap.get("examOne"));
		log.debug(A.C + "ExamController.modifyExam.model : " + model + A.R);
		return "/exam/modifyExam";
	}

	// 시험 수정 액션
	@PostMapping("/modifyExam")
	public String modifyExam(Exam exam, ExamQuestion examQuestion ,ExamExample examExample) {
		log.debug(A.C + "ExamController.modifyExam.param.exam : " + exam+ A.R);

		int row = examService.modifyExam(exam);
		examService.modifyExamQuestion(examQuestion);
		examService.modifyExamExample(examExample);
		if (row == 1) {
			log.debug(A.C + "시험 수정 성공" + A.R);
		} else {
			log.debug(A.C + "시험 수정 실패" + A.R);
		}
		return "redirect:/exam/getExamOne?examNo=" + exam.getExamNo();
	}	
	
	
	// 시험문제 삭제
	@PostMapping("/removeExam")
	public String removeExam(@RequestParam(name="examNo") int examNo) {
		log.debug(A.C+"ExamController.removeExam.param.examNo : "+ examNo +A.R);
		int row = examService.removeExam(examNo);
		if(row == 1) {
			log.debug(A.C+"ExamController.removeHomework. 과제 삭제 성공"+A.R);
		} else {
			log.debug(A.C+"ExamController.removeHomework. 과제 삭제 실패"+A.R);
		}
	return "redirect:/exam/getExamListByPage";
	}

	
	
	

}
