package kr.co.nft.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.nft.lms.mapper.ScheduleMapper;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Schedule;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Service
@Transactional
public class ScheduleService {
	
	// Mapper 변수 생성
	@Autowired
	private ScheduleMapper scheduleMapper;
	
	// Schedule 테이블 전체 리스트
	public List<Schedule> getScheduleList() {
		
		List<Schedule> scheduleList = scheduleMapper.selectScheduleList();
		
		log.debug(A.A + "[ScheduleService.getScheduleList] scheduleList : " + scheduleList + A.R);
		
		return scheduleList;
	}
	
	// Schedule 테이블 schedule_no에 따른 상세보기
	public Schedule getScheduleOne(int scheduleNo) {
		
		Schedule scheduleOne = scheduleMapper.selectScheduleOne(scheduleNo);
		
		log.debug(A.A + "[ScheduleService.getScheduleOne] scheduleOne : " + scheduleOne + A.R);
		
		return scheduleOne;
	}
	
	// Schedule 테이블 데이터 입력
	public int addSchedule(Schedule schedule) {
		
		log.debug(A.A + "[ScheduleService.addSchedule] schedule : " + schedule + A.R);
		
		int row = scheduleMapper.insertSchedule(schedule);
		
		log.debug(A.A + "[ScheduleService.addSchedule] row : " + row + A.R);
		
		return row;
	}
	
	// Schedule 테이블 데이터 삭제
	public int removeSchedule(int scheduleNo) {
		
		log.debug(A.A + "[ScheduleService.removeSchedule] scheduleNo : " + scheduleNo + A.R);
		
		int row = scheduleMapper.deleteSchedule(scheduleNo);
		
		log.debug(A.A + "[ScheduleService.removeSchedule] row : " + row + A.R);
		
		return row;
	}
}
