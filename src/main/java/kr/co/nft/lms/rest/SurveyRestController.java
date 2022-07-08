package kr.co.nft.lms.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.nft.lms.service.SurveyService;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.SurveyQuestion;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SurveyRestController {
	@Autowired SurveyService surveyService;
	
	@GetMapping("/rest/manager/survey/getSurveyStatistics") 
	public Map<String,Object> getSurveyStatistics(){

		Map<String,Object> returnMap = surveyService.getSurveyAnswerStatistics();
		log.debug(A.D+"[SurveyRestController.getSurveyStatistics] returnMap : " + returnMap + A.R); // 디버깅
		
		
		
		return returnMap;
	}
	
	@GetMapping("/rest/student/survey/getSurveyOneS")
	public Map<String,Object> getSurveyQuestionList(@RequestParam(name="surveyNo") int surveyNo){
		
		Map<String, Object> returnMap = surveyService.selectMultipleSurveyQuestion(surveyNo);
		log.debug(A.D+"[SurveyRestController.getSurveyQuestionList] returnMap : " + returnMap + A.R);
		
		return returnMap;
		
	}
	
	@GetMapping("/rest/manager/survey/updateSurveyForm")
	public Map<String,Object> getSurveyQuestion(@RequestParam(name="surveyNo") int surveyNo){
		
		Map<String,Object> returnMap = surveyService.selectSurveyQuestion(surveyNo);
		
		return returnMap;
	}
	
	@PostMapping("/rest/manager/survey/updateSurveyQuestion")
	public Map<String,Object> deleteSurveyQuestion(@RequestParam(name="surveyQuestionNo") int surveyQuestionNo
									,@RequestParam(name="surveyNo") int surveyNo){
		int row = surveyService.deleteSurveyQuestion(surveyNo,surveyQuestionNo);
		Map<String,Object> map = new HashMap<>();
		map.put("row", row);
		return map;
		
	}
	
	@PostMapping("/rest/manager/survey/addSurveyQuestion")
	public Map<String,Object> addSurveyQuestion(SurveyQuestion surveyQuestion){
		int row = surveyService.addQuestion(surveyQuestion);
		Map<String,Object> map = new HashMap<>();
		map.put("row", row);
		return map;
	}
	
	@GetMapping("/rest/manager/survey/selectAddSurveyQuestion")
	public Map<String,Object> selectAddSurveyQuestion (@RequestParam(name="surveyNo") int surveyNo, 
												@RequestParam(name="surveyQuestionNo") int surveyQuestionNo){
		Map<String,Object> returnMap = surveyService.selectAddSurveyQuestion(surveyNo, surveyQuestionNo);
		
		return returnMap;
	}
}
