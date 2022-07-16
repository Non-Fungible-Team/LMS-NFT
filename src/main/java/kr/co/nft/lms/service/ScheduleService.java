package kr.co.nft.lms.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public Map<String, Object> getScheduleListByMonth(int year, int month) {
		
		log.debug(A.A + "[ScheduleService.getScheduleList.param] year : " + year + A.R);
		log.debug(A.A + "[ScheduleService.getScheduleList.param] month : " + month + A.R);
		
		Calendar now = Calendar.getInstance(); // ex) 2022.07.01
		
		if(year == -1) { // null 값으로 기본값인 -1이 들어왔을 때, 현재 연도
			year = now.get(Calendar.YEAR);
		}
		if(month == -1) { // null 값으로 기본값인 -1이 들어왔을 때, 현재 월
			month = now.get(Calendar.MONTH)+1;
		}
		if(month == 0) { // 0이 넘어올 시, 작년(year-1) 12월
			month = 12;
			year = year - 1;
		}
		if(month == 13) { // 13이 넘어올 시, 올해(year+1) 1월
			month = 1;
			year = year + 1;
		}
		
		log.debug(A.A + "[ScheduleService.getScheduleList] year : " + year + A.R);
		log.debug(A.A + "[ScheduleService.getScheduleList] month : " + month + A.R);
		
		// firstDay : 오늘 날짜를 먼저 구하여 날짜만 1일로 변경
		Calendar firstDay = Calendar.getInstance(); // ex) 2022.04.19
		firstDay.set(Calendar.YEAR, year);
		firstDay.set(Calendar.MONTH, month - 1); // 자바 달력 API는 1월을 0으로, 2월을 1로, ... 12월을 11로 설정되어있다.
		firstDay.set(Calendar.DATE, 1); // ex) 2022.04.01
		int dayOfWeek = firstDay.get(Calendar.DAY_OF_WEEK);
		// dayOfWeek는 일 1, 월 2, ..., 토 7
		// startBlank는 일 0, 월 1, ..., 토 6
		
		// 1) startBlank : 달력 시작 시 필요한 공백 개수 (td 공백 개수)
		int startBlank = dayOfWeek - 1; // 일 0, 월 1, ..., 토 6 -> 1일의 요일을 이용하여 구한다
		
		// 2) endDay :  마지막 날짜
		int endDay = firstDay.getActualMaximum(Calendar.DATE);
		
		// 3) endBlank / startBlank와 endDay를 합의 결과에 endBlank를 더해서 7의 배수가 되도록
		int endBlank = 0;
		if ((startBlank + endDay) % 7 != 0) {
			// 7에서 startBlank + endDay의 7로 나눈 나머지 값을 빼면
			endBlank = 7 - ((startBlank + endDay) % 7);
		} 
		
		// 4) totalTd : 전체 td 개수
		int totalTd = startBlank + endDay + endBlank;
		
		// 5) schedule 테이블 리스트
		List<Schedule> scheduleListByMonth = scheduleMapper.selectScheduleListByMonth(year, month);
		
		log.debug(A.A + "[ScheduleService.getScheduleList] startBlank : " + startBlank + A.R);
		log.debug(A.A + "[ScheduleService.getScheduleList] endDay : " + endDay + A.R);
		log.debug(A.A + "[ScheduleService.getScheduleList] endBlank : " + endBlank + A.R);
		log.debug(A.A + "[ScheduleService.getScheduleList] totalTd : " + totalTd + A.R);
		log.debug(A.A + "[ScheduleService.getScheduleList] scheduleListByMonth : " + scheduleListByMonth + A.R);
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		returnMap.put("scheduleListByMonth", scheduleListByMonth);
		returnMap.put("startBlank", startBlank);
		returnMap.put("endDay", endDay);
		returnMap.put("endBlank", endBlank);
		returnMap.put("totalTd", totalTd);
		returnMap.put("year", year);
		returnMap.put("month", month);
				
		return returnMap;
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
	
	// Schedule 테이블 데이터 수정
	public int modifySchedule(Schedule schedule) {
		
		log.debug(A.A + "[ScheduleService.modifySchedule] schedule : " + schedule + A.R);
		
		int row = scheduleMapper.updateSchedule(schedule);
		
		log.debug(A.A + "[ScheduleService.modifySchedule] row : " + row + A.R);
		
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
