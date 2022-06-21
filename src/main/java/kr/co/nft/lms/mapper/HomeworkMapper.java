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
	List<Homework> selectHomeworkListByPage(Map<String, Object> map);

	// 과제 상세보기
	Homework selectHomeworkOne(int homeworkNo);
	
	// 과제 입력
	int insertHomework(Homework homework);
	
	// 과제 수정
	int updateHomework(Homework homework);
	
	// 과제 삭제
	int deleteHomework(int homeworkNo);
	
	// 과제 총 갯수
	int selectTotalCount();
	
// 학생 과제 제출
	// 학생 과제 입력
	int insertHomeworkSubmitFile(HomeworkSubmitFile homeworkSubmitFile);
	int insertHomeworkSubmit(HomeworkSubmit homeworkSubmit);
}
