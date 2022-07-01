package kr.co.nft.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.nft.lms.mapper.SurveyMapper;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Lecture;
import kr.co.nft.lms.vo.Survey;
import kr.co.nft.lms.vo.SurveyAnswer;
import kr.co.nft.lms.vo.SurveyMultipleAnswer;
import kr.co.nft.lms.vo.SurveyQuestion;
import kr.co.nft.lms.vo.SurveyQuestionList;
import kr.co.nft.lms.vo.SurveyShortAnswer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class SurveyService {
	@Autowired SurveyMapper surveyMapper;
	
	public Map<String,Object> getSurveyAnswerStatistics(int surveyQuestionNo ){
		log.debug(A.D+"[SurveyService.getSurveyAnswerStatistics] surveyQuestioNo : " + surveyQuestionNo + A.R);
		Map<String,Object> returnMap = new HashMap<>();
		
//		for (SurveyMultipleAnswer ma : SurveyMultipleAnswerList) {
			List<SurveyMultipleAnswer> answerAverage = surveyMapper.getAnswerAverage(surveyQuestionNo);
			log.debug(A.D+"[SurveyService.getSurveyAnswerStatistics] answerAverage : " + answerAverage + A.R);
			
			List answerCount = surveyMapper.getAnswerCount(surveyQuestionNo);
			log.debug(A.D+"[SurveyService.getSurveyAnswerStatistics] answerCount : " + answerCount + A.R);
			
			
			returnMap.put("answerAverage", answerAverage);
			returnMap.put("answerCount", answerCount);
			
//		}
		log.debug(A.D+"[SurveyService.getSurveyAnswerStatistics] returnMap : " + returnMap + A.R);
		
		return returnMap;
		
		
	}
	
	public Map<String,Object> getAllSurveyAnswer(int surveyQuestionNo) {
		
		List<Map<String,Object>> allAnswer = surveyMapper.getAllSurveyAnswer();
		log.debug(A.D+"[SurveyService.getAllSurveyAnswer] allAnswer : " + allAnswer + A.R);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("allAnswer", allAnswer);
		log.debug(A.D+"[SurveyService.getAllSurveyAnswer] returnMap : " + returnMap + A.R);
		
		return returnMap;
		
		 
	}
	
	public Map<String,Object> selectSurveyQuestionList(int currentPage, int rowPerPage) {
		
		Map<String, Integer> map = new HashMap<>();
		map.put("rowPerPage", rowPerPage);
		log.debug(A.D+"[SurveyService.selectSurveyQuestionList] map : " + map + A.R);
		
		List QuestionList = surveyMapper.selectSurveyQuestionList(map);
		log.debug(A.D+"[SurveyService.selectSurveyQuestionList] QuestionList : " + QuestionList + A.R);
		
		Map<String,Object> select = new HashMap<>();
		select.put("QuestionList", QuestionList);
		
		
		log.debug(A.D+"[SurveyService.selectSurveyQuestionList] select : " + select + A.R);
		
		
		
		return select;
	}
	
	public int insertSurbey(Survey survey, List<SurveyQuestion> surveyQuestionList) {
		log.debug(A.D+"[SurveyService.insertSurbey] survey : " + survey + A.R);
		int row = surveyMapper.insertSurvey(survey);
		log.debug(A.D+"[SurveyService.insertSurbey] surveyNo : " + row+ A.R);
		
		
		int row2 = 0;
		for(SurveyQuestion s : surveyQuestionList) {
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
	
	public Map<String,Object> getSurveyListByPage(int currentPage, int rowPerPage, Lecture lecture) {
		int beginRow = (currentPage - 1)*rowPerPage;
		Map<String, Integer> map = new HashMap<>();
		map.put("lectureNo", lecture.getLectureNo());
		map.put("rowPerPage", rowPerPage);
		map.put("beginRow", beginRow);
		log.debug(A.D+"[SurveyService.getSurveyListByPage] map : " + map + A.R);
		
		List<Map<String,Object>> surveyList = surveyMapper.selectSurveyListByPage(map);
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
		log.debug(A.D+"[SurveyService.getSurveyQuestionListByPage] map : " + map + A.R);
		
		List SurveyQuestionList = surveyMapper.selectSurveyQuestionListByPage(map);
		log.debug(A.D+"[SurveyService.getSurveyQuestionListByPage] SurveyQuestionList : " + SurveyQuestionList + A.R);
		
		int totalCount = surveyMapper.countSurveyList();
		int lastPage = (int)(Math.ceil((double)totalCount / (double)rowPerPage));
	
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("SurveyQuestionList", SurveyQuestionList);
		returnMap.put("lastPage",lastPage);
		log.debug(A.D+"[SurveyService.getSurveyQuestionListByPage] returnMap : " + returnMap + A.R);
		
		return returnMap;
	}
	
	 public Map<String, Object> getSurveyOneAndQuestion(int surveyNo) {
		 log.debug(A.D+"[SurveyService.getSurveyOneAndQuestion]" + A.R);
		 
	      Survey surveyOne = surveyMapper.getSurveyOne(surveyNo);
	      List<Map<String, Object>> SurveyQuestionList = surveyMapper.getSurveyQuestionList(surveyNo);
	      log.debug(A.D+"[SurveyService.getSurveyOneAndQuestion] surveyOne :" + surveyOne +A.R);
	      log.debug(A.D+"[SurveyService.getSurveyOneAndQuestion] SurveyQuestionList : " + SurveyQuestionList +A.R);
	     
	      Map<String, Object> returnMap = new HashMap<>();
	      returnMap.put("surveyOne", surveyOne);
	      returnMap.put("SurveyQuestionList", SurveyQuestionList);
	      log.debug(A.D+"[SurveyService.getSurveyOneAndQuestion] returnMap :" + returnMap +A.R);
	      
	      return returnMap;
	   }
	public int addSurveyQuestionAnswer(List<SurveyAnswer> surveyAnswer) {
		
		int row = 0;
		
		for(SurveyAnswer s : surveyAnswer) {
			if(s.getSurveyAnswerType().equals("객관식")) {
				row = row + surveyMapper.insertSurveyMultipleAnswer(s);
				log.debug(A.D+"[SurveyService.addSurveyQuestionAnswer] row :" + row +A.R);
			} else if(s.getSurveyAnswerType().equals("주관식")) {
				row = row + surveyMapper.insertSurveyShortAnswer(s);
				log.debug(A.D+"[SurveyService.addSurveyQuestionAnswer] row :" + row +A.R);
			}
		}
		return row;
		
	}
}
