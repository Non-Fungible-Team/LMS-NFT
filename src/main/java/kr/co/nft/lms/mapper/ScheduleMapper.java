package kr.co.nft.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.nft.lms.vo.Schedule;

@Mapper
public interface ScheduleMapper {
	
	// Schedule 테이블 전체 리스트
	List<Schedule> selectScheduleListByMonth(int year, int month);
	
	// Schedule 테이블 schedule_no에 따른 상세보기
	Schedule selectScheduleOne(int scheduleNo);
	
	// Schedule 테이블 데이터 입력
	int insertSchedule(Schedule schedule);
	
	// Schedule 테이블 데이터 수정
	int updateSchedule(Schedule schedule);
	
	// Schedule 테이블 데이터 삭제
	int deleteSchedule(int scheduleNo);
}
