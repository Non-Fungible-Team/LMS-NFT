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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class SurveyService {
	@Autowired SurveyMapper surveyMapper;
	
	public int deleteSurveyQuestion(int surveyNo, int surveyQuestionNo) {
		log.debug(A.D+"[SurveyService.getQuestionList] surveyNo " + surveyNo + A.R);
		log.debug(A.D+"[SurveyService.getQuestionList] surveyQuestionNo " + surveyQuestionNo + A.R);
		
		return surveyMapper.deleteSurveyQuestion(surveyNo, surveyQuestionNo);
	}
	
	public SurveyQuestionList getQuestionList(int surveyQuestionListNo) {
		log.debug(A.D+"[SurveyService.getQuestionList] surveyQuestionListNo " + surveyQuestionListNo + A.R);
		SurveyQuestionList QuestionList = surveyMapper.getQuestionList(surveyQuestionListNo);
		log.debug(A.D+"[SurveyService.getQuestionList] QuestionList " + QuestionList + A.R);
		
		
		return QuestionList;
	}
	
	public int updateQuestionList(SurveyQuestionList surveyQuestionList) {
		return surveyMapper.updateSurveyQuestionList(surveyQuestionList);
	}
	
	public Map<String,Object> getSurveyAnswerStatistics(){
		log.debug(A.D+"[SurveyService.getSurveyAnswerStatistics] "+ A.R);
		Map<String,Object> returnMap = new HashMap<>();

			List<SurveyMultipleAnswer> answerAverage = surveyMapper.getAnswerAverage();
			log.debug(A.D+"[SurveyService.getSurveyAnswerStatistics] answerAverage : " + answerAverage + A.R);
			
			List<Map<String,Object>> answerCount = surveyMapper.getAnswerCount();
			log.debug(A.D+"[SurveyService.getSurveyAnswerStatistics] answerCount : " + answerCount + A.R);
			
			
			
			
			List<Map<String,Object>> questionListCount = surveyMapper.questionListCount();
			log.debug(A.D+"[SurveyService.getSurveyAnswerStatistics] questionListCount : " + questionListCount + A.R);
			
			
			returnMap.put("questionListCount", questionListCount);
			returnMap.put("answerAverage", answerAverage);
			returnMap.put("answerCount", answerCount);
			

		log.debug(A.D+"[SurveyService.getSurveyAnswerStatistics] returnMap : " + returnMap + A.R);
		
		return returnMap;
		
		
	}
	
	public Map<String,Object> getAllSurveyAnswer() {
		
		List<Map<String,Object>> shortAnswer = surveyMapper.getShortSurveyAnswer();
		log.debug(A.D+"[SurveyService.getAllSurveyAnswer] shortAnswer : " + shortAnswer + A.R);
		
		List<Map<String,Object>> multipleAnswer = surveyMapper.getMultipleSurveyAnswer();
		log.debug(A.D+"[SurveyService.getAllSurveyAnswer] multipleAnswer : " + multipleAnswer + A.R);
		
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("shortAnswer", shortAnswer);
		returnMap.put("multipleAnswer", multipleAnswer);
		log.debug(A.D+"[SurveyService.getAllSurveyAnswer] returnMap : " + returnMap + A.R);
		
		return returnMap;
		
		 
	}
	
	public Map<String,Object> selectSurveyQuestion(int surveyNo) {
		List<Map<String, Object>> surveyQuestion =  surveyMapper.selectSurveyQuestion(surveyNo);
		log.debug(A.D+"[SurveyService.selectSurveyQuestion] surveyQuestion : " + surveyQuestion + A.R);
		
		List<Map<String, Integer>> QuestionList = surveyMapper.selectSurveyQuestionList();
		log.debug(A.D+"[SurveyService.selectSurveyQuestionList] QuestionList : " + QuestionList + A.R);
		
		Map<String, Object> survey = surveyMapper.selectSurvey(surveyNo);
		log.debug(A.D+"[SurveyService.selectSurveyQuestionList] survey : " + survey + A.R);
		
		Map<String,Object> selectQuestion = new HashMap<>();
		selectQuestion.put("surveyQuestion", surveyQuestion);
		selectQuestion.put("QuestionList", QuestionList);
		selectQuestion.put("survey", survey);
		
		log.debug(A.D+"[SurveyService.selectSurveyQuestion] selectQuestion : " + selectQuestion + A.R);
		
		return selectQuestion;
	}
	
	public Map<String,Object> selectSurveyQuestionList() {
		
		List<Map<String, Integer>> QuestionList = surveyMapper.selectSurveyQuestionList();
		log.debug(A.D+"[SurveyService.selectSurveyQuestionList] QuestionList : " + QuestionList + A.R);
		
		Map<String,Object> selectQuestionList = new HashMap<>();
		selectQuestionList.put("QuestionList", QuestionList);
		
		
		log.debug(A.D+"[SurveyService.selectSurveyQuestionList] select : " + selectQuestionList + A.R);
		
		
		
		return selectQuestionList;
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
	
	public Map<String,Object> selectAddSurveyQuestion(int surveyNo, int surveyQuestionNo){
		Map<String,Object> map = new HashMap<>();
		map.put("surveyNo", surveyNo);
		map.put("surveyQuestionNo", surveyQuestionNo);
		log.debug(A.D+"[SurveyService.selectAddSurveyQuestion] map : " + map + A.R);
		
		List<Map<String,Object>> surveyQuestion = surveyMapper.selectAddSurveyQuestion(surveyNo, surveyQuestionNo);
		log.debug(A.D+"[SurveyService.selectAddSurveyQuestion] surveyQuestion : " + surveyQuestion + A.R);
		
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("surveyQuestion", surveyQuestion);
		
		return returnMap;
	}
	
	public int addQuestion(SurveyQuestion surveyQuestion) {
		log.debug(A.D+"[SurveyService.addQuestion] surveyQuestion : " + surveyQuestion + A.R);
		int row = surveyMapper.insertSurveyQuestion(surveyQuestion);
		
		return row;
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
		
		List<Map<String, Integer>> SurveyQuestionList = surveyMapper.selectSurveyQuestionListByPage(map);
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
	      List<Map<String, Object>> QuestionList = surveyMapper.getSurveyQuestionList(surveyNo);
	      log.debug(A.D+"[SurveyService.getSurveyOneAndQuestion] surveyOne :" + surveyOne +A.R);
	      log.debug(A.D+"[SurveyService.getSurveyOneAndQuestion] SurveyQuestionList : " + QuestionList +A.R);
	      
	      Map<String, Object> returnMap = new HashMap<>();
	      returnMap.put("surveyOne", surveyOne);
	      returnMap.put("QuestionList", QuestionList);
	      
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
	
	public Map<String, Object> selectMultipleSurveyQuestion(int surveyNo){
		log.debug(A.D+"[SurveyService.selectMultipleSurveyQuestion] surveyNo " + surveyNo + A.R);
		
		List<Map<String, Object>> QuestionList = surveyMapper.selectMultipleSurveyQuestion(surveyNo);
		log.debug(A.D+"[SurveyService.selectMultipleSurveyQuestion] QuestionList " + QuestionList + A.R);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("QuestionList", QuestionList);
		
		return returnMap;
	}
}
