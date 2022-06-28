package kr.co.nft.lms.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.nft.lms.service.SurveyService;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.SurveyMultipleAnswer;
import kr.co.nft.lms.vo.SurveyQuestion;
import kr.co.nft.lms.vo.SurveyQuestionList;
import kr.co.nft.lms.vo.SurveyShortAnswer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SurveyRestController {
	@Autowired SurveyService surveyService;
	
	@GetMapping("/rest/manager/survey/getSurveyStatistics") public Map<String,Object>
	getSurveyStatistics(Model model, SurveyQuestion surveyQuestion
	,SurveyQuestionList surveyQuestionList ,SurveyMultipleAnswer
	surveyMultipleAnswer ,SurveyShortAnswer surveyShortAnswer){
	log.debug(A.D+"[SurveyRestController.getSurveyStatistics] " + A.R); // 디버깅 //
	// 전체 답변 가져오기 
	Map<String, Object> returnMap = surveyService.getAllSurveyAnswer(surveyQuestion.getSurveyQuestionList(),
	surveyQuestionList, surveyMultipleAnswer, surveyShortAnswer);
	
	log.debug(A.D+"[SurveyRestController.getSurveyStatistics] returnMap : " +
	returnMap + A.R); // 디버깅
	
	
	return returnMap; }
	 
}
