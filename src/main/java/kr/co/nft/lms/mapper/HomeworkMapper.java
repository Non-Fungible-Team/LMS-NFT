package kr.co.nft.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.nft.lms.vo.Homework;
import kr.co.nft.lms.vo.HomeworkSubmit;
import kr.co.nft.lms.vo.HomeworkSubmitFile;

@Mapper
public interface HomeworkMapper {
	
// 강사 과제 입력	
	// 과제 목록 페이지에서 목록 페이징
	List<Map<String,Object>> selectHomeworkListByPage(Map<String, Object> map);

	// 과제 상세보기
	Homework selectHomeworkOne(int homeworkNo);
	
	// 과제 입력
	int insertHomework(Homework homework);
	
	// 과제 수정
	int updateHomework(Homework homework);
	
	// 과제 삭제
	int deleteHomework(int homeworkNo);
	
	// 과제 총 갯수
	int selectTotalCount(int lectureNo);
	
// 학생 과제 제출
	// 학생 과제 입력
	int insertHomeworkSubmitFile(HomeworkSubmitFile homeworkSubmitFile);
	int insertHomeworkSubmit(HomeworkSubmit homeworkSubmit);
	
	// 학생 제출과제 목록 
	List<Map<String, Object>> selectHomeworkSubmitListByPage(Map<String, Object> map);
	
	// 학생 제출과제 상세보기
	HomeworkSubmit selectHomeworkSubmitOne(int homeworkSubmitNo);
	
	
	// 학생 제출과제 수정
	int updateHomeworkSubmit(HomeworkSubmit homeworkSubmit);
	
	// 학생 제출과제 삭제
	int deleteHomeworkSubmit(int homeworkSubmitNo);
	
	// 학생 과제 피드백 점수 입력
	int updateHomeworkSubmitScore(HomeworkSubmit homeworkSubmit);
	
	// 학생 과제 총 갯수
	int selectSubmitTotalCount();
	
	// File 상세보기
	List<HomeworkSubmitFile> selectHomeworkSubmitFile(int homeworkSubmitFileNo);
	List<String> selectHomeworkSubmitFileNameList(int homeworkSubmitFileNo);
	// File 삭제
	int deleteHomeworkSubmitFileOne(int homeworkSubmitFileNo);
	int deleteHomeworkSubmitFileList(int homeworkSubmitFileNo);
	
}
