package kr.co.nft.lms.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.nft.lms.service.SurveyService;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Survey;
import kr.co.nft.lms.vo.SurveyQuestion;
import kr.co.nft.lms.vo.SurveyQuestionList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SurveyController {
	@Autowired private SurveyService surveyService;
	
	@GetMapping("/survey/insertSurvey")
	public String insertSurvey(Survey survey, Model model,
			@RequestParam(name = "currentPage",defaultValue = "1") int currentPage,
			@RequestParam(name = "rowPerPage",defaultValue = "10") int rowPerPage) {	
		Map<String, Object> map = surveyService.selectSurveyQuestionList(currentPage, rowPerPage);
		
		log.debug(A.D+"[SurveyController.insertSurvey] map : " + map + A.R); // 디버깅
		
		model.addAttribute("QuestionList",map.get("QuestionList"));
		
		return "/survey/insertSurvey";
	}
	
	@PostMapping("/survey/insertSurvey")
	public String insertSurveyl(Survey survey, SurveyQuestion surveyQuestion) {
		log.debug(A.D+"[SurveyController.insertSurvey] survey : " + survey + A.R); // 디버깅
		log.debug(A.D+"[SurveyController.insertSurvey] surveyQuestion : " + surveyQuestion + A.R); // 디버깅
		
		
		int row = surveyService.insertSurbey(survey, surveyQuestion.getSurveyQuestionList());
		log.debug(A.D+"[SurveyController.insertSurvey] row : " + row + A.R); // 디버깅
		
		
		
		if(row == 0) {
			log.debug(A.D+"[SurveyController.insertSurvey] 등록실패  " + A.R); // 디버깅
			return "/survey/insertSurvey";
		}
		
		log.debug(A.D+"[SurveyController.insertSurvey] 등록성공  " + A.R); // 디버깅
		return "redirect:/survey/getSurveyListByPage";
	}
	
	@GetMapping("/survey/getSurveyOne")
	public String getSurveyOne(Model model
			,@RequestParam(name="surveyNo") Map<String, Object> surveyNo) {
		log.debug(A.D+"[SurveyController.insertSurvey] surveyNo : " + surveyNo + A.R); // 디버깅
		
		Map<String, Object> returnMap = surveyService.getSurveyOneAndQuestion(surveyNo);
		
		model.addAttribute("getSurveyOne",returnMap.get("getSurveyOne"));
		model.addAttribute("SurveyQuestionList", returnMap.get("SurveyQuestionList"));
		
		
		return"/survey/getSurveyOne";
	}
	
	@GetMapping("/survey/insertSurveyQuestionList")
	public String insertSurveyQuestionList() {
		return "/survey/insertSurveyQuestionList";
	}
	
	@PostMapping("/survey/insertSurveyList")
	public String insertSurveyList(SurveyQuestionList surveyQuestionList) {
		log.debug(A.D+"[SurveyController.insertSurveyList] surveyQuestionList : " + surveyQuestionList.toString() + A.R); // 디버깅

		
		surveyService.insertSurveyList(surveyQuestionList);
		return "redirect:/survey/getSurveyQuestionListByPage";
	}
	
	
	
	@GetMapping("/survey/getSurveyListByPage") // 설문조사 페이지
	public String getSurveyListByPage(Model model,
			@RequestParam(name = "currentPage",defaultValue="1")int currentPage,
			@RequestParam(name = "rowPerPage",defaultValue = "10") int rowPerPage) {// 디폴트 값 설정
		//뷰를 호출시 모델레이어로 부터 반환된 값을 뷰로 보낸다
		Map<String, Object> returnMap = surveyService.getSurveyListByPage(currentPage, rowPerPage);
		log.debug(A.D+"[SurveyController.getSurveyListByPage] returnMap : " + returnMap + A.R); // 디버깅
		
		model.addAttribute("surveyList", returnMap.get("surveyList")); // value를 object로 넘겨줌
		model.addAttribute("lastPage", returnMap.get("lastPage"));
		model.addAttribute("currentPage",currentPage);
		
		return "/survey/getSurveyListByPage";
	}
	
	@GetMapping("/survey/getSurveyQuestionListByPage") // 설문조사 항목 리스트 페이지
	public String getSurveyQuestionListByPage(Model model,
			@RequestParam(name = "currentPage",defaultValue="1")int currentPage,
			@RequestParam(name = "rowPerPage",defaultValue = "10") int rowPerPage) {// 디폴트 값 설정
		//뷰를 호출시 모델레이어로 부터 반환된 값을 뷰로 보낸다
		Map<String, Object> returnMap = surveyService.getSurveyQuestionListByPage(currentPage, rowPerPage);
		log.debug(A.D+"[SurveyController.getSurveyQuestionListByPage] returnMap : " + returnMap + A.R); // 디버깅
		
		model.addAttribute("SurveyQuestionList", returnMap.get("SurveyQuestionList")); // value를 object로 넘겨줌
		model.addAttribute("lastPage", returnMap.get("lastPage"));
		model.addAttribute("currentPage",currentPage);
		
		return "/survey/getSurveyQuestionListByPage";
	}
	
}
