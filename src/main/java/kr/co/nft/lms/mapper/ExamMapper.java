package kr.co.nft.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.nft.lms.vo.*;

@Mapper
public interface ExamMapper {
	
	// 시험문제 리스트
	List<Exam> selectExamListByPage(Map<String, Object> map);
	
	// 시험 문항수
	int countExam();
	
//  시험 문제 보기
	// 시험 상세보기
	Exam selectExamOne(int examNo);
	// 시험문제 상세보기
	List<Exam> selectExamQuestionOne(int examNo);
	// 시험보기 상세보기
	List<Exam> selectExamExampleOne(int examNo,int examQuestionNo);
	
// 	시험 등록
	// 시험 등록
	int insertExam(Exam exam);
	// 문제 등록
	int insertExamQuestion(ExamQuestion examQuestion);
	// 보기 등록
	int insertExamExample(ExamExample examExample);
// 	시험 수정
	// 시험 수정
	int updateExam(Exam exam);
	// 문제 수정
	int updateExamQuestion(ExamQuestion examQuestion);
	// 보기 수정
	int updateExamExample(ExamExample examExample);
	
	// 시험문제 삭제
	int deleteExam(int examNo);
	
//  시험 점수 확인
	
}
