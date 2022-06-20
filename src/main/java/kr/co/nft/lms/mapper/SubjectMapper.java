package kr.co.nft.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.nft.lms.vo.Subject;

@Mapper
public interface SubjectMapper {
	
	// subject 테이블 전체 리스트
	List<Subject> selectSubjectList();
	
	// subject 테이블 데이터 입력
	int insertSubjectOne(Subject subject);
	
	// subject 테이블 데이터 삭제
	int deleteSubjectOne(int subjectNo);
}
