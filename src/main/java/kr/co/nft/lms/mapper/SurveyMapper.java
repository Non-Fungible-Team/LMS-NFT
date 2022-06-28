package kr.co.nft.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.nft.lms.vo.Survey;
import kr.co.nft.lms.vo.SurveyAnswer;
import kr.co.nft.lms.vo.SurveyQuestion;
import kr.co.nft.lms.vo.SurveyQuestionList;

@Mapper
public interface SurveyMapper {
	// 설문조사 생성
	int insertSurvey(Survey survey);
	
	// 설문조사 질문 리스트 생성
	int insertSurveyQuestionList(SurveyQuestionList surveyQuestionList);
	
	// 설문조사 질문 생성
	int insertSurveyQuestion(SurveyQuestion surveyQuestion);
	
	// 설문조사 질문 리스트 목록 (제목과 내용만 페이징 없음)
	List selectSurveyQuestionList(Map<String, Integer> map);
	
	// 설문조사 질문 리스트 페이지
	List selectSurveyQuestionListByPage(Map<String, Integer> map);
	
	// 설문조사 리스트 페이지 카운트
	int countSurveyList();
	
	// 설문조사 리스트 (페이징 없는거)
	List selectSurveyList(Map<String, Integer> map);
	
	// 전체 설문조사 페이지 (이너조인) (리스트 형식)
	List<Map<String,Object>> selectSurveyListByPage(Map<String, Integer> map);
	
	// 전체 설문조사 페이지 카운트
	int countSurvey();
	
	// 설문조사 상세보기
	Survey getSurveyOne(int surveyNo);
	
	// 설문조사 상세보기(이너조인)
	List<Map<String,Object>> getSurveyQuestionList(int surveyNo);
	
	// 설문조사 객관식 답변 등록
	int insertSurveyMultipleAnswer(SurveyAnswer surveyMultipleAnswer);
	
	// 설문조사 주관식 답변 등록
	int insertSurveyShortAnswer(SurveyAnswer surveyShortAnswer);
	
	// 설문조사 답변 확인 (전체 리스트형식으로 레프트 조인)
	List<Map<String,Object>> getAllSurveyAnswer(Map<String, Object> map);
	
}
