package kr.co.nft.lms.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.nft.lms.service.SurveyService;
import kr.co.nft.lms.util.A;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SurveyController {
	@Autowired private SurveyService surveyService;
	
	
	
	@GetMapping("/manager/insertSurveyList")
	public String insertSurveyList() {
		return "/survey/insertSurveyList";
	}
	
	
	
	@GetMapping("/all/getSurveyListByPage") // 설문조사 페이지
	public String getSurveyListByPage(Model model,
			@RequestParam(name = "currentPage",defaultValue="1")int currentPage,
			@RequestParam(name = "rowPerPage",defaultValue = "10") int rowPerPage) {// 디폴트 값 설정
		//뷰를 호출시 모델레이어로 부터 반환된 값을 뷰로 보낸다
		Map<String, Object> returnMap = surveyService.getSurveyListByPage(currentPage, rowPerPage);
		log.debug(A.D+"[SurveyController.getSurveyListByPage] map : " + returnMap + A.R); // 디버깅
		
		model.addAttribute("surveyList", returnMap.get("surveyList")); // value를 object로 넘겨줌
		model.addAttribute("lastPage", returnMap.get("lastPage"));
		model.addAttribute("currentPage",currentPage);
		
		return "/survey/getSurveyListByPage";
	}
	
	@GetMapping("/manager/getSurveyQuestionListByPage") // 설문조사 항목 리스트 페이지
	public String getSurveyQuestionListByPage(Model model,
			@RequestParam(name = "currentPage",defaultValue="1")int currentPage,
			@RequestParam(name = "rowPerPage",defaultValue = "10") int rowPerPage) {// 디폴트 값 설정
		//뷰를 호출시 모델레이어로 부터 반환된 값을 뷰로 보낸다
		Map<String, Object> returnMap = surveyService.getSurveyListByPage(currentPage, rowPerPage);
		log.debug(A.D+"[SurveyController.getSurveyQuestionListByPage] map : " + returnMap + A.R); // 디버깅
		
		model.addAttribute("SurveyQuestionList", returnMap.get("SurveyQuestionList")); // value를 object로 넘겨줌
		model.addAttribute("lastPage", returnMap.get("lastPage"));
		model.addAttribute("currentPage",currentPage);
		
		return "/survey/getSurveyQuestionListByPage";
	}
	
}
