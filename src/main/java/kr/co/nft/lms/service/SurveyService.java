package kr.co.nft.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.nft.lms.mapper.SurveyMapper;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.SurveyQuestion;
import kr.co.nft.lms.vo.SurveyQuestionList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class SurveyService {
	@Autowired SurveyMapper surveyMapper;
	
	public int insertSurveyQuestion(SurveyQuestion surveyQuestion) {
		
		
		
		return surveyMapper.insertSurveyQuestion(surveyQuestion);
	}
	
	public int insertSurveyList(SurveyQuestionList surveyQuestionList) {
		
		log.debug(A.D+"[SurveyService.getSurveyListByPage] surveyQuestionList : " + surveyQuestionList + A.R);
		
		return surveyMapper.insertSurveyList(surveyQuestionList);
		
	}
	
	public Map<String,Object> getSurveyListByPage(int currentPage, int rowPerPage) {
		int beginRow = (currentPage - 1)*rowPerPage;
		Map<String, Integer> map = new HashMap<>();
		map.put("rowPerPage", rowPerPage);
		map.put("beginRow", beginRow);
		
		List surveyList = surveyMapper.selectSurveyListByPage(map);
		log.debug(A.D+"[SurveyService.getSurveyListByPage] surveyList : " + surveyList + A.R);
		
		int totalCount = surveyMapper.countSurvey();
		int lastPage = (int)(Math.ceil((double)totalCount / (double)rowPerPage));
	
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("surveyList", surveyList);
		returnMap.put("lastPage",lastPage);
		log.debug(A.D+"[SurveyService.getSurveyListByPage] returnMap : " + returnMap + A.R);
		
		return returnMap;
	}
	
	public Map<String,Object> getSurveyQuestionListByPage(int currentPage, int rowPerPage) {
		int beginRow = (currentPage - 1)*rowPerPage;
		Map<String, Integer> map = new HashMap<>();
		map.put("rowPerPage", rowPerPage);
		map.put("beginRow", beginRow);
		
		List SurveyQuestionList = surveyMapper.selectSurveyQuestionListByPage(map);
		log.debug(A.D+"[SurveyService.getSurveyQuestionListByPage] surveyList : " + SurveyQuestionList + A.R);
		
		int totalCount = surveyMapper.countSurveyList();
		int lastPage = (int)(Math.ceil((double)totalCount / (double)rowPerPage));
	
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("SurveyQuestionList", SurveyQuestionList);
		returnMap.put("lastPage",lastPage);
		log.debug(A.D+"[SurveyService.getSurveyQuestionListByPage] returnMap : " + returnMap + A.R);
		
		return returnMap;
	}
	
	
}
