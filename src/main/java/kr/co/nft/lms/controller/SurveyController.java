package kr.co.nft.lms.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.nft.lms.service.LectureService;
import kr.co.nft.lms.service.SurveyService;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Lecture;
import kr.co.nft.lms.vo.Member;
import kr.co.nft.lms.vo.Survey;
import kr.co.nft.lms.vo.SurveyAnswer;
import kr.co.nft.lms.vo.SurveyQuestion;
import kr.co.nft.lms.vo.SurveyQuestionList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SurveyController {
	@Autowired private SurveyService surveyService;
	@Autowired private LectureService lectureService;
	
	@PostMapping("/manager/survey/updateSurvey")
	public String updateSurvey(Survey survey) {
		int row = surveyService.updateSurvey(survey);
		log.debug(A.D+"[SurveyController.updateSurvey] row : "+ row +A.R);
		
		return "redirect:/all/survey/getSurveyListByPage";
	}
	
	
	@GetMapping("/manager/survey/updateSurveyForm")
	public String updateSurveyForm(Survey survey, Model model, HttpSession session
			,@RequestParam(name = "lectureNo", defaultValue = "0") int lectureNo
			,@RequestParam(name = "surveyNo") int surveyNo) {
		// 로그인 정보 가져오기
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		// 학생 강의 One 리스트 + 강의 이름과 번호 리스트
		Map<String, Object> LectureMap  = lectureService.addStudentLecture();
		// 질문 가져오기
		Map<String, Object> selectQuestion = surveyService.selectSurveyQuestion(surveyNo);
		
		
		log.debug(A.D+"[SurveyController.insertSurvey] studentLectureOne : " + LectureMap + A.R);
		log.debug(A.D+"[SurveyController.insertSurvey] loginMember : " + loginMember +A.R);
		log.debug(A.D+"[SurveyController.insertSurvey] selectQuestion : " + selectQuestion + A.R); // 디버깅
		
		model.addAttribute("questionList",selectQuestion.get("questionList"));
		model.addAttribute("surveyQuestion",selectQuestion.get("surveyQuestion"));
		model.addAttribute("survey",selectQuestion.get("survey"));
		model.addAttribute("loginMember",loginMember);
		model.addAttribute("lectureNoNameList", LectureMap.get("lectureNoNameList"));
		model.addAttribute("surveyNo",surveyNo);
		
		return "survey/updateSurveyForm";
	}
	
	@GetMapping("/manager/survey/getSurveyStatistics")
	public String getSurveyStatistics(Model model) {
		// 전체 답변 가져오기 
		Map<String, Object> returnMap = surveyService.getAllSurveyAnswer();
		
		log.debug(A.D+"[SurveyController.getSurveyStatistics] returnMap : " + returnMap + A.R); // 디버깅
		
		model.addAttribute("multipleAnswer",returnMap.get("multipleAnswer"));
		model.addAttribute("shortAnswer",returnMap.get("shortAnswer"));
		model.addAttribute("questionListCount",returnMap.get("questionListCount"));
		
		
		
		return "survey/getSurveyStatistics";
	}
	
	@GetMapping("/teacher/survey/getSurveyLectureStatistics")
	public String getSurveyLectureStatistics(Model model, HttpSession session) {
		Lecture lecture = (Lecture)session.getAttribute("sessionLecture");
		// 과목별 답변 가져오기 
		Map<String, Object> returnMap = surveyService.getLectureSurveyAnswer(lecture.getLectureNo());
		
		log.debug(A.D+"[SurveyController.getSurveyStatistics] returnMap : " + returnMap + A.R); // 디버깅
		
		model.addAttribute("multipleAnswer",returnMap.get("multipleAnswer"));
		model.addAttribute("shortAnswer",returnMap.get("shortAnswer"));
		model.addAttribute("questionListCount",returnMap.get("questionListCount"));
		
		
		
		return "survey/getSurveyLectureStatistics";
	}
	
	@GetMapping("/manager/survey/updateSurveyQuestionList")
	public String updateSurveyQuestionList(Model model 
				,@RequestParam(name="surveyQuestionListNo") int surveyQuestionListNo) {
		log.debug(A.D+"[SurveyController.updateSurveyQuestionList] "+ A.R); // 디버깅
		SurveyQuestionList QuestionList = surveyService.getQuestionList(surveyQuestionListNo);
		log.debug(A.D+"[SurveyController.updateSurveyQuestionList] QuestionList : "+ QuestionList + A.R); // 디버깅
		model.addAttribute("QuestionList",QuestionList);
		
		return "survey/updateSurveyQuestionList";
	}
	
	@PostMapping("/manager/survey/updateSurveyQuestionList")
	public String updateSurveyQuestionList(SurveyQuestionList surveyQuestionList) {
		
		int row = surveyService.updateQuestionList(surveyQuestionList);
		log.debug(A.D+"[SurveyController.updateSurveyQuestionList] row : "+ row + A.R); // 디버깅
		return "redirect:/manager/survey/getSurveyQuestionListByPage";
	}
	
	@GetMapping("/manager/survey/insertSurvey") // 설문조사 추가 (설문조사 질문도 같이)
	public String insertSurvey(Survey survey, Model model, HttpSession session
			,@RequestParam(name = "currentPage",defaultValue = "1") int currentPage
			,@RequestParam(name = "rowPerPage",defaultValue = "10") int rowPerPage
			,@RequestParam(name = "lectureNo", defaultValue = "0") int lectureNo) {	
		// 로그인 정보 가져오기
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		// 학생 강의 One 리스트 + 강의 이름과 번호 리스트
		Map<String, Object> LectureMap  = lectureService.addStudentLecture();
		
		
		// 질문 항목 가져오기
		Map<String, Object> map = surveyService.selectSurveyQuestionList();
		
		
		log.debug(A.D+"[SurveyController.insertSurvey] studentLectureOne : " + LectureMap + A.R);
		log.debug(A.D+"[SurveyController.insertSurvey] loginMember : " + loginMember +A.R);
		log.debug(A.D+"[SurveyController.insertSurvey] map : " + map + A.R); // 디버깅
		
		model.addAttribute("QuestionList",map.get("QuestionList"));
		model.addAttribute("loginMember",loginMember);
		model.addAttribute("lectureNoNameList", LectureMap.get("lectureNoNameList"));
		return "survey/insertSurvey";
	}
	
	
	
	@PostMapping("/manager/survey/insertSurvey") // 설문조사 추가 (설문조사 질문도 같이)
	public String insertSurveyl(Survey survey, SurveyQuestion surveyQuestion) {
		log.debug(A.D+"[SurveyController.insertSurvey] survey : " + survey + A.R); // 디버깅
		log.debug(A.D+"[SurveyController.insertSurvey] surveyQuestion : " + surveyQuestion + A.R); // 디버깅
		
		
		int row = surveyService.insertSurbey(survey, surveyQuestion.getSurveyQuestionList());
		log.debug(A.D+"[SurveyController.insertSurvey] row : " + row + A.R); // 디버깅
		
		
		
		if(row == 0) {
			log.debug(A.D+"[SurveyController.insertSurvey] 등록실패  " + A.R); // 디버깅
			return "survey/insertSurvey";
		}
		
		log.debug(A.D+"[SurveyController.insertSurvey] 등록성공  " + A.R); // 디버깅
		return "redirect:/all/survey/getSurveyListByPage";
	}
	
	@GetMapping("manager/survey/getSurveyOneM")// 설문조사 상세보기 페이지
	public String getSurveyOneManager(Model model
			,@RequestParam(name="surveyNo") int surveyNo) {
		log.debug(A.D+"[SurveyController.getSurveyOne] " + A.R); // 디버깅
		log.debug(A.D+"[SurveyController.getSurveyOne] surveyNo : " + surveyNo + A.R); // 디버깅
		
		Map<String, Object> returnMap = surveyService.getSurveyOneAndQuestion(surveyNo);
		log.debug(A.D+"[SurveyController.getSurveyOne] returnMap : " + returnMap + A.R); // 디버깅
		
		//모델에 값들 저장
		model.addAttribute("surveyOne",returnMap.get("surveyOne"));
		model.addAttribute("QuestionList", returnMap.get("QuestionList"));
		
		log.debug(A.D+"[SurveyController.getSurveyOne] model : " + model + A.R); // 디버깅
		
		return"survey/getSurveyOneM";
	}
	
	@GetMapping("/student/survey/getSurveyOneS")// 설문조사 상세보기 페이지
	public String getSurveyOneStudent(Model model , HttpSession session
			,@RequestParam(name="surveyNo") int surveyNo) {
		log.debug(A.D+"[SurveyController.getSurveyOne] " + A.R); // 디버깅
		log.debug(A.D+"[SurveyController.getSurveyOne] surveyNo : " + surveyNo + A.R); // 디버깅
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		log.debug(A.D+"[SurveyController.getSurveyOne] loginMember : "+loginMember+A.R);
		
		Map<String, Object> returnMap = surveyService.getSurveyOneAndQuestion(surveyNo);
		log.debug(A.D+"[SurveyController.getSurveyOne] returnMap : " + returnMap + A.R); // 디버깅
		
		//모델에 값들 저장
		model.addAttribute("loginMember",loginMember);
		model.addAttribute("surveyOne",returnMap.get("surveyOne"));
		model.addAttribute("QuestionList", returnMap.get("QuestionList"));
		
		log.debug(A.D+"[SurveyController.getSurveyOne] model : " + model + A.R); // 디버깅
		
		return"survey/getSurveyOneS";
	}
	
	@PostMapping("/student/survey/addSurveyAnswer")// 설문조사 답변 추가
	public String addSurveyAnswer(SurveyAnswer surveyAnswer) {
		log.debug(A.D+"[SurveyController.addSurveyAnswer] surveyAnswer : " + surveyAnswer + A.R);
		int row = surveyService.addSurveyQuestionAnswer(surveyAnswer.getSurveyAnswer());
		log.debug(A.D+"[SurveyController.addSurveyAnswer] row : " + row + A.R);
		return "redirect:/all/survey/getSurveyListByPage";
		
	}
	
	@GetMapping("/manager/survey/insertSurveyQuestionList")// 설문조사 질문 항목 추가
	public String insertSurveyQuestionList() {
		return "survey/insertSurveyQuestionList";
	}
	
	@PostMapping("/manager/survey/insertSurveyQuestionList")// 설문조사 질문 항목 추가
	public String insertSurveyList(SurveyQuestionList surveyQuestionList) {
		log.debug(A.D+"[SurveyController.insertSurveyList] surveyQuestionList : " + surveyQuestionList.toString() + A.R); // 디버깅

		
		surveyService.insertSurveyList(surveyQuestionList);
		return "redirect:/manager/survey/getSurveyQuestionListByPage";
	}
	
	
	
	
	
	@GetMapping("/all/survey/getSurveyListByPage") // 설문조사 페이지
	public String getSurveyListByPage(Model model, HttpSession session
			,@RequestParam(name = "currentPage",defaultValue="1")int currentPage // 디폴트 값 설정
			,@RequestParam(name = "rowPerPage",defaultValue = "10") int rowPerPage
			) {
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		Lecture lecture = (Lecture)session.getAttribute("sessionLecture");
		
		log.debug(A.D+"[SurveyController.getSurveyListByPage] loginMember : " + loginMember + A.R);
		//뷰를 호출시 모델레이어로 부터 반환된 값을 뷰로 보낸다
		Map<String, Object> returnMap = surveyService.getSurveyListByPage(currentPage, rowPerPage, lecture);
		log.debug(A.D+"[SurveyController.getSurveyListByPage] returnMap : " + returnMap + A.R); // 디버깅
		
		//모델에 값들 저장
		model.addAttribute("surveyList", returnMap.get("surveyList")); // value를 object로 넘겨줌
		model.addAttribute("lastPage", returnMap.get("lastPage"));
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("lectureNo",lecture.getLectureNo());
		
		return "survey/getSurveyListByPage";
	}
	
	@GetMapping("/manager/survey/getSurveyQuestionListByPage") // 설문조사 항목 리스트 페이지
	public String getSurveyQuestionListByPage(Model model
			,@RequestParam(name = "currentPage",defaultValue="1")int currentPage
			,@RequestParam(name = "rowPerPage",defaultValue = "10") int rowPerPage) {// 디폴트 값 설정
		//뷰를 호출시 모델레이어로 부터 반환된 값을 뷰로 보낸다
		Map<String, Object> returnMap = surveyService.getSurveyQuestionListByPage(currentPage, rowPerPage);
		log.debug(A.D+"[SurveyController.getSurveyQuestionListByPage] returnMap : " + returnMap + A.R); // 디버깅
		
		//모델에 값들 저장
		model.addAttribute("SurveyQuestionList", returnMap.get("SurveyQuestionList")); // value를 object로 넘겨줌
		model.addAttribute("lastPage", returnMap.get("lastPage"));
		model.addAttribute("currentPage",currentPage);
		
		return "survey/getSurveyQuestionListByPage";
	}
	
}
