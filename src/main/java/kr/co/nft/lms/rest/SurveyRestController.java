package kr.co.nft.lms.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.nft.lms.service.SurveyService;
import kr.co.nft.lms.util.A;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SurveyRestController {
	@Autowired SurveyService surveyService;
	
	@GetMapping("/rest/manager/survey/getSurveyStatistics") 
	public Map<String,Object> getSurveyStatistics(){
//		log.debug(A.D+"[SurveyRestController.getSurveyStatistics] surveyQuestionNo : " + surveyQuestionNo + A.R); // 디버깅
		Map<String,Object> returnMap = surveyService.getSurveyAnswerStatistics();
		log.debug(A.D+"[SurveyRestController.getSurveyStatistics] returnMap : " + returnMap + A.R); // 디버깅
		
		
		
		return returnMap;
	}
	 
}
