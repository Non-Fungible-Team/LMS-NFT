package kr.co.nft.lms.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.nft.lms.service.ExamService;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Exam;
import kr.co.nft.lms.vo.ExamExample;
import kr.co.nft.lms.vo.ExamQuestion;
import kr.co.nft.lms.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ExamController {
	@Autowired private ExamService examService;
	
	// 시험 리스트
	@GetMapping("/all/exam/getExamListByPage")
	public String getExamListByPage(Model model
									,Exam exam
									,HttpSession session
									,@RequestParam(name="currentPage", defaultValue="1") int currentPage
									,@RequestParam(name="rowPerPage", defaultValue ="10") int rowPerPage) {
		
		//세션에 정보 요청
		int sessionLectureNo = (int)session.getAttribute("sessionLectureNo");
		Member sessionLoginMember = (Member)session.getAttribute("sessionLoginMember");
		
		Map<String, Object> returnMap = examService.getExamListByPage(currentPage, rowPerPage, sessionLectureNo);
		log.debug(A.C + "[ExamController.getExamListByPage.param] returnMap: " + returnMap + A.R);
		model.addAttribute("sessionLectureNo", sessionLectureNo);
		model.addAttribute("sessionLoginMember", sessionLoginMember);
		model.addAttribute("examList", returnMap.get("examList"));
		model.addAttribute("examLastPage", returnMap.get("examLastPage"));
		model.addAttribute("currentPage", currentPage);
		return "/exam/getExamListByPage";
	}
		
	// 시험문제 보기
	@GetMapping("/all/exam/getExamOne")
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
		return "/exam/getExamOne";
	}
		
		
	// 시험문제 등록
	@GetMapping("/teacher/exam/addExam")
	public String addExam() {
		return "/exam/addExam";
	}
	@PostMapping("/teacher/exam/addExam")
	public String addExam(HttpServletRequest request, Exam exam, ExamQuestion examQuestion, ExamExample examExample) {
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
	@GetMapping("/teacher/exam/modifyExam")
	public String modifyExam(Model model, @RequestParam(name = "examNo") int examNo) {
		log.debug(A.C + "ExamController.modifyExam.param.examNo :" + examNo + A.R);

		Map<String, Object> returnMap = examService.getExamOne(examNo);
		log.debug(A.C + "ExamController.modifyExam.returnMap : " + returnMap + A.R);

		model.addAttribute("examOne", returnMap.get("examOne"));
		log.debug(A.C + "ExamController.modifyExam.model : " + model + A.R);
		return "/exam/modifyExam";
	}

	// 시험 수정 액션
	@PostMapping("/teacher/exam/modifyExam")
	public String modifyExam(Exam exam, ExamQuestion examQuestion ,ExamExample examExample) {
		log.debug(A.C + "ExamController.modifyExam.param.exam : " + exam+ A.R);

		int row = examService.modifyExam(exam);
		examService.modifyExamQuestion(examQuestion);
		examService.modifyExamExample(examExample);
		if (row == 1) {
			log.debug(A.C + "ExamController.modifyHomework 시험 수정 성공" + A.R);
		} else {
			log.debug(A.C + "ExamController.modifyHomework 시험 수정 실패" + A.R);
		}
		return "redirect:/exam/getExamOne?examNo=" + exam.getExamNo();
	}	
	
	
	// 시험문제 삭제
	@PostMapping("/teacher/exam/removeExam")
	public String removeExam(@RequestParam(name="examNo") int examNo) {
		log.debug(A.C+"ExamController.removeExam.param.examNo : "+ examNo +A.R);
		int row = examService.removeExam(examNo);
		if(row == 1) {
			log.debug(A.C+"ExamController.removeHomework. 과제 삭제 성공"+A.R);
		} else {
			log.debug(A.C+"ExamController.removeHomework. 과제 삭제 실패"+A.R);
		}
	return "redirect:/all/exam/getExamListByPage";
	}

	
//	examAnswer 시험 학생 제출 및 점수 
	// 시험 점수 리스트
		@GetMapping("/all/exam/getExamScoreListByPage")
		public String getExamScoreListByPage(Model model
										,HttpSession session
										,Exam exam
										,@RequestParam(name="currentPage", defaultValue="1") int currentPage
										,@RequestParam(name="rowPerPage", defaultValue ="10") int rowPerPage) {
			
			int sessionLectureNo = (int)session.getAttribute("sessionLectureNo");
			Member loginMember = (Member)session.getAttribute("sessionLoginMember");
			log.debug(A.C + "[ExamController.getExamScoreListByPage.param] loginMember: " + loginMember + A.R);
			
			Map<String, Object> returnMap = examService.getExamScoreListByPage(currentPage, rowPerPage, sessionLectureNo);
			log.debug(A.C + "[ExamController.getExamScoreListByPage.param] returnMap: " + returnMap + A.R);
			model.addAttribute("loginMember", loginMember);
			model.addAttribute("examScoreList", returnMap.get("examScoreList"));
			model.addAttribute("examScoreLastPage", returnMap.get("examScoreLastPage"));
			model.addAttribute("currentPage", currentPage);
			return "/exam/getExamScoreListByPage";
		}
			
		// 시험 응시 페이지
		@GetMapping("/student/exam/submitExamAnswer")
		public String submitExamAnswer(Model model ,@RequestParam(name="examNo") int examNo, Map<String, Object> examQuestionOneList) {
			log.debug(A.C + "[ExamController.submitExamAnswer.param] examNo: " + examNo + A.R);
			Map<String, Object> map1 = examService.getExamOne(examNo); // 시험 정보 호출
			map1 = examService.getExamOne(examNo);
			log.debug(A.C + "[ExamController.submitExamAnswer.map1] examNo: " + examNo + A.R);
			Map<String, Object> map2 = examService.getExamQuestionOne(examNo); // 시험 문제 정보 호출
			model.addAttribute("examOne", map1.get("examOne"));	// 시험정보
			model.addAttribute("examQuestionOneList", map2.get("examQuestionOneList")); // 문제정보
			model.addAttribute("examExampleOneList", map2.get("examExampleOneList")); // 보기정보
			log.debug(A.C + "[ExamController.submitExamAnswer.param] examExampleOneList: " + map2.get("examExampleOneList") + A.R);
			return "/exam/submitExamAnswer";
		}
		@PostMapping("/student/exam/submitExamAnswer")
		public String submitExamAnswer(HttpServletRequest request
														,Model model
														,Exam exam
														,int examNo
														,HttpSession session
														,ExamQuestion examQuestion
														,ExamExample examExample) {
			log.debug(A.C + "[ExamController.submitExamAnswer.param] examNo: " + examNo + A.R);
			log.debug(A.C + "[ExamController.submitExamAnswer.param] request: " + request + A.R);
			log.debug(A.C + "[ExamController.submitExamAnswer.param] exam: " + exam+ A.R);
			
			// 세션 정보 호출
			Member sessionLoginMember = (Member)session.getAttribute("sessionLoginMember");
			log.debug(A.C + "[ExamController.submitExamAnswer.param] loginMember: " + sessionLoginMember + A.R);
			
			int row = examService.submitExamAnswer(exam);
			if(row ==1) {
				log.debug(A.C +"ExamController.submitExamAnswer 답안 제출 성공"+A.R);
			} else {
				log.debug(A.C +"ExamController.submitExamAnswer 답안 제출 실패"+A.R);
			}
			return "redirect:/exam/getExamScoreListByPage";
			
		}
		
		// 제출 답안 상세보기
		@GetMapping("/all/exam/getExamScoreOne")
		public String getExamScoreOne (Model model ,@RequestParam(name="examNo") int examNo, Map<String, Object> examQuestionOneList) {
			log.debug(A.C + "[ExamController.getExamScoreOne.param] examNo: " + examNo + A.R);
			Map<String, Object> map1 = examService.getExamOne(examNo); // 시험 정보 호출
			map1 = examService.getExamOne(examNo);
			log.debug(A.C + "[ExamController.getExamOne.map1] examNo: " + examNo + A.R);
			Map<String, Object> map2 = examService.getExamQuestionOne(examNo); // 시험 문제 정보 호출
			model.addAttribute("examOne", map1.get("examOne"));	// 시험정보
			model.addAttribute("examQuestionOneList", map2.get("examQuestionOneList")); // 문제정보
			model.addAttribute("examExampleOneList", map2.get("examExampleOneList")); // 보기정보
			Map<String, Object> map3 = examService.examScoreOne(examNo); // 제출 답안 호출
			model.addAttribute("examScoreOneList", map3.get("examScoreOneList"));
			log.debug(A.C + "[ExamController.getExamScoreOne.param] examScoreOneList: " + map3.get("examScoreOneList") + A.R);
			return "/exam/getExamScoreOne";
		}
				

}
