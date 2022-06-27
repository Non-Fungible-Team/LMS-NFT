package kr.co.nft.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.nft.lms.vo.LectureSchedule;

@Mapper
public interface LectureScheduleMapper {
	
	// lecture_schedule 테이블 lecture_no에 따른 전체 리스트
	List<LectureSchedule> selectLectureScheduleListByLectureNo(Map<String,Object> map);
		
	// lecture_schedule 테이블 시작일부터 수료일까지 데이터 입력 - 운영자, 강사
	int insertLectureSchedule(LectureSchedule lectureSchedule);
	
	// lecture_schedule 테이블 데이터 삭제
	int deleteLectureSchedule(String lectureScheduleDate, int lectureNo);
	
	//전체 행 수
	int selectLectureSchedulCount();
}
