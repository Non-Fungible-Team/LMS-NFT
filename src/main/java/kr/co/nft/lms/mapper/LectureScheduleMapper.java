package kr.co.nft.lms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.nft.lms.vo.LectureSchedule;

@Mapper
public interface LectureScheduleMapper {
	
	// lecture_schedule 테이블 lecture_no에 따른 전체 리스트
	List<LectureSchedule> selectLectureScheduleListByLectureNo();
		
	// lecture_schedule 테이블 시작일부터 수료일까지 데이터 입력 - 운영자, 강사
	int insertLectureSchedule();
	
	// lecture_schedule 테이블 데이터 하나 입력 - 운영자, 강사
	int insertLectureScheduleOne(LectureSchedule lectureSchedule);
		
	// lecture_schedule 테이블 데이터 삭제
	int deleteLectureScheduleOne(String lectureScheduleDate, int lectureNo);
}
