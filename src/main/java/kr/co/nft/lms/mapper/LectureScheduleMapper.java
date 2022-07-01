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
	
	// lecture_schedule 테이블 lecture_no, lectureScheduleDate에 따른 수정 리스트
	LectureSchedule selectLectureScheduleOne(String lectureScheduleDate, int lectureNo);
	
	// lecture_schedule 테이블 데이터 수정
	int updateLectureSchedule(LectureSchedule lectureSchedule);
	
	//강의시간표 날짜만 출력 - 출석에 이용
	List<LectureSchedule> selectLectureScheduleDateByLectureNo(Map<String,Object> map);
}
