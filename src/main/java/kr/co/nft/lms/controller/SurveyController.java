package kr.co.nft.lms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.nft.lms.service.SurveyService;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Member;
import kr.co.nft.lms.vo.Survey;
import kr.co.nft.lms.vo.SurveyQuestion;
import kr.co.nft.lms.vo.SurveyQuestionList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SurveyController {
	@Autowired private SurveyService surveyService;
	
	
	
	@GetMapping("/manager/survey/insertSurvey") // 설문조사 추가 (설문조사 질문도 같이)
	public String insertSurvey(Survey survey, Model model
			,@RequestParam(name = "currentPage",defaultValue = "1") int currentPage
			,@RequestParam(name = "rowPerPage",defaultValue = "10") int rowPerPage) {	
		Map<String, Object> map = surveyService.selectSurveyQuestionList(currentPage, rowPerPage);
		
		log.debug(A.D+"[SurveyController.insertSurvey] map : " + map + A.R); // 디버깅
		
		model.addAttribute("QuestionList",map.get("QuestionList"));
		
		return "survey/insertSurvey";
	}
	
	@GetMapping("/manager/survey/updateSurveyQuestionList")
	public String updateSurveyQuestionList() {
		return "survey/updateSurveyQuestionList";
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
		return "redirect:/survey/getSurveyListByPage";
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
		model.addAttribute("surveyQuestionList", returnMap.get("SurveyQuestionList"));
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
		model.addAttribute("surveyQuestionList", returnMap.get("SurveyQuestionList"));
		log.debug(A.D+"[SurveyController.getSurveyOne] model : " + model + A.R); // 디버깅
		
		return"survey/getSurveyOneS";
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
			,@RequestParam(name = "currentPage",defaultValue="1")int currentPage
			,@RequestParam(name = "rowPerPage",defaultValue = "10") int rowPerPage) {// 디폴트 값 설정
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		log.debug(A.D+"[SurveyController.getSurveyListByPage] loginMember : "+loginMember+A.R);
		//뷰를 호출시 모델레이어로 부터 반환된 값을 뷰로 보낸다
		Map<String, Object> returnMap = surveyService.getSurveyListByPage(currentPage, rowPerPage);
		log.debug(A.D+"[SurveyController.getSurveyListByPage] returnMap : " + returnMap + A.R); // 디버깅
		
		//모델에 값들 저장
		model.addAttribute("surveyList", returnMap.get("surveyList")); // value를 object로 넘겨줌
		model.addAttribute("loginMember",loginMember);
		model.addAttribute("lastPage", returnMap.get("lastPage"));
		model.addAttribute("currentPage",currentPage);
		
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
