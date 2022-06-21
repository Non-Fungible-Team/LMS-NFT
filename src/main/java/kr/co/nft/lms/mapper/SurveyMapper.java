package kr.co.nft.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.nft.lms.vo.Survey;
import kr.co.nft.lms.vo.SurveyQuestion;
import kr.co.nft.lms.vo.SurveyQuestionList;
import kr.co.nft.lms.vo.SurveyShortAnswer;

@Mapper
public interface SurveyMapper {
	// 설문조사 생성
	int insertSurvey(Survey survey);
	
	// 설문조사 리스트 생성
	int insertSurveyList(SurveyQuestionList surveyQuestionList);
	
	// 설문조사 질문 생성
	int insertSurveyQuestion(SurveyQuestion surveyQuestion);
	
	// 설문조사 리스트 페이지
	List selectSurveyQuestionListByPage(Map<String, Integer> map);
	
	// 설문조사 리스트 페이지 카운트
	int countSurveyList();
	
	// 전체 설문조사 페이지 (리스트 형식)
	List selectSurveyListByPage(Map<String, Integer> map);
	
	// 전체 설문조사 페이지 카운트
	int countSurvey();
	
}
