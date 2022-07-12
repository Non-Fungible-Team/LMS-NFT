package kr.co.nft.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.nft.lms.vo.Survey;
import kr.co.nft.lms.vo.SurveyAnswer;
import kr.co.nft.lms.vo.SurveyMultipleAnswer;
import kr.co.nft.lms.vo.SurveyQuestion;
import kr.co.nft.lms.vo.SurveyQuestionList;

@Mapper
public interface SurveyMapper {
	
	// --------- 테이블에 값 추가 --------- //
	
	// 설문조사 생성
	int insertSurvey(Survey survey);
	
	// 설문조사 질문 리스트 생성
	int insertSurveyQuestionList(SurveyQuestionList surveyQuestionList);
		
	// 설문조사 질문 생성
	int insertSurveyQuestion(SurveyQuestion surveyQuestion);
	
	// 설문조사 객관식 답변 등록
	int insertSurveyMultipleAnswer(SurveyAnswer surveyMultipleAnswer);
	
	// 설문조사 주관식 답변 등록
	int insertSurveyShortAnswer(SurveyAnswer surveyShortAnswer);
	
	// --------- 페이지 ---------- //
	
	// 설문조사 질문 리스트 페이지
	List<Map<String, Integer>> selectSurveyQuestionListByPage(Map<String, Integer> map);
		
	// 설문조사 리스트 페이지 카운트
	int countSurveyList();
	
	// 전체 설문조사 페이지 (이너조인) (리스트 형식)
	List<Map<String,Object>> selectSurveyListByPage(Map<String, Integer> map);
	
	// 전체 설문조사 페이지 카운트
	int countSurvey();
	
	// 설문조사 상세보기
	Survey getSurveyOne(int surveyNo);
	
	
	// 설문조사 질문 리스트 목록 (제목과 내용만 페이징 없음)
	List<Map<String, Integer>> selectSurveyQuestionList();
	
	
	
	
	
	// 설문조사 상세보기(이너조인)
	List<Map<String,Object>> getSurveyQuestionList(int surveyNo);
	
	
	
	// 전체 설문조사 답변 확인 (리스트형식으로 레프트 조인)
	List<Map<String,Object>> getMultipleSurveyAnswer(); //객관식
	List<Map<String,Object>> getShortSurveyAnswer(); //주관식
	
	
	// -------- ajax용 -------- //
	
	// 설문조사 질문별 점수평균 (double값 뱉음)
	List<SurveyMultipleAnswer> getAnswerAverage();
	
	// 설문조사 질문별, 점수 카운트 (리스트 형식)
	List<Map<String, Object>> getAnswerCount();
	
	// 설문조사 차트 만들 때 객관식을 카운트
	List<Map<String, Object>> questionListCount();
	
	// 설문조사 과목,질문별 점수평균 (double값 뱉음)
	List<SurveyMultipleAnswer> getLectureAnswerAverage(int lectureNo);
	
	// 설문조사 과목,질문별, 점수 카운트 (리스트 형식)
	List<Map<String, Object>> getLectureAnswerCount(int lectureNo);
	
	// 설문조사 과목별 차트 만들 때 객관식을 카운트
	List<Map<String, Object>> lectureQuestionListCount(int lectureNo);
	
	// 설문조사 리스트 (페이징 없는거)
	List<Map<String, Object>> selectSurveyList(int lectureNo);
	
	
	// 과목별 설문조사 답변 확인 (리스트형식으로 레프트 조인)
	List<Map<String,Object>> getLectureMultipleSurveyAnswer(int lectureNo); //객관식
	List<Map<String,Object>> getLectureShortSurveyAnswer(int lectureNo); //주관식
	
	// 설문조사 질문 항목정보 가져오기
	SurveyQuestionList getQuestionList(int surveyQuestionListNo);
	
	// 설문조사 질문 항목 업데이트
	int updateSurveyQuestionList(SurveyQuestionList surveyQuestionList);

	// 설문조사 질문 정보 가져오기
	List<Map<String, Object>> selectSurveyQuestion(int surveyNo);
	
	// 설문조사 객관식 정보 가져오기
	List<Map<String, Object>> selectMultipleSurveyQuestion(int surveyNo);
	
	// 설문조사 정보 가져오기
	Map<String, Object> selectSurvey(int surveyNo);
	
	// 설문조사 업데이트 페이지에서 질문 삭제
	int deleteSurveyQuestion (int surveyNo, int surveyQuestionNo);
	
	// 설문조사 업데이트로 추가된 질문 찾기
	List<Map<String,Object>> selectAddSurveyQuestion(int surveyNo, int surveyQuestionNo);

	// 설문조사 업데이트
	int updateSurvey(Survey survey);
}
