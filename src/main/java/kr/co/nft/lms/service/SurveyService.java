package kr.co.nft.lms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.nft.lms.mapper.SurveyMapper;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Survey;
import kr.co.nft.lms.vo.SurveyQuestion;
import kr.co.nft.lms.vo.SurveyQuestionList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class SurveyService {
	@Autowired SurveyMapper surveyMapper;
	
	public Map<String,Object> selectSurveyQuestionList(int currentPage, int rowPerPage) {
		
		Map<String, Integer> map = new HashMap<>();
		map.put("rowPerPage", rowPerPage);
		
		List QuestionList = surveyMapper.selectSurveyQuestionList(map);
		
		Map<String,Object> select = new HashMap<>();
		select.put("QuestionList", QuestionList);
		
		
		log.debug(A.D+"[SurveyService.selectSurveyQuestionList] select : " + select + A.R);
		
		
		
		return select;
	}
	
	public int insertSurbey(Survey survey,ArrayList<SurveyQuestion> surveyQuestion) {
		log.debug(A.D+"[SurveyService.insertSurbey] survey : " + survey + A.R);
		int row = surveyMapper.insertSurvey(survey);
		log.debug(A.D+"[SurveyService.insertSurbey] surveyNo : " + row+ A.R);
		
		
		int row2 = 0;
		for(SurveyQuestion s : surveyQuestion) {
			s.setSurveyNo(survey.getSurveyNo());
			row2 = row2 + surveyMapper.insertSurveyQuestion(s);
			
			log.debug(A.D+"[SurveyService.insertSurbey] row : " + row + A.R);
		}
		
		
		
		return row2;
	}
	
	
	public int insertSurveyList(SurveyQuestionList surveyQuestionList) {
		log.debug(A.D+"[SurveyService.insertSurveyList] surveyQuestionList : " + surveyQuestionList + A.R);
		
		
		return surveyMapper.insertSurveyQuestionList(surveyQuestionList);
		
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
