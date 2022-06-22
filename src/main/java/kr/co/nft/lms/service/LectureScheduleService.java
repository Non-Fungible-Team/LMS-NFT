package kr.co.nft.lms.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.nft.lms.mapper.LectureScheduleMapper;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.LectureSchedule;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class LectureScheduleService {
	
	@Autowired
	private LectureScheduleMapper lectureScheduleMapper;
	
	// lecture_schedule 테이블 lecture_no에 따른 전체 리스트
	public List<LectureSchedule> getLectureScheduleListByLectureNo(int lectureNo) {
		
		log.debug(A.A + "[LectureScheduleService.getLectureScheduleListByLectureNo] lectureNo : " + lectureNo + A.R);
		
		List<LectureSchedule> lectureScheduleList = lectureScheduleMapper.selectLectureScheduleListByLectureNo(lectureNo);
		log.debug(A.A + "[LectureScheduleService.getLectureScheduleListByLectureNo] lectureScheduleList : " + lectureScheduleList + A.R);
		
		return lectureScheduleList;
	}
	
	// lecture_schedule 테이블 데이터 입력 - 운영자, 강사
	public void addLectureSchedule() {
		// addLectureSchedule(LectureSchedule lectureSchedule)로 수정 필요
		LectureSchedule lectureSchedule = new LectureSchedule();
		
		// String scheduleDate = ""; // 학원 오는 날짜
		
		// String lectureScheduleStartDate = lectureSchedule.getLectureScheduleStartDate();
		// String lectureScheduleEndDate = lectureSchedule.getLectureScheduleEndDate();
		
		String lectureScheduleStartDate = "20220501";
		String lectureScheduleEndDate = "20220510";
		
		// startDate = lectureScheduleStartDate.replace("-",""); // -를 ""으로 바꿔서 int에 저장
		// int endDate1 = Integer.parseInt(lectureScheduleEndDate.replace("-","")); // -를 ""으로 바꿔서 int에 저장
		
		log.debug(A.A + "[LectureScheduleService.addLectureSchedule] lectureScheduleStartDate : " + lectureScheduleStartDate + A.R);
		log.debug(A.A + "[LectureScheduleService.addLectureSchedule] lectureScheduleEndDate : " + lectureScheduleEndDate + A.R);
				
		Date startDate;
		Date endDate;
		Calendar cStartDate = Calendar.getInstance();
		Calendar cEndDate = Calendar.getInstance();
		
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			
			startDate = dateFormat.parse(lectureScheduleStartDate);
			endDate = dateFormat.parse(lectureScheduleEndDate);
			
			log.debug(A.A + "[LectureScheduleService.addLectureSchedule] startDate : " + startDate + A.R);
			log.debug(A.A + "[LectureScheduleService.addLectureSchedule] endDate : " + endDate + A.R);
			
			// Calendar 타입으로 변경 add()메소드로 1일씩 추가해 주기위해 변경
			cStartDate.setTime(startDate);
			cEndDate.setTime(endDate);
			
			log.debug(A.A + "[LectureScheduleService.addLectureSchedule] cStartDate.getTime() : " + cStartDate.getTime() + A.R);
			log.debug(A.A + "[LectureScheduleService.addLectureSchedule] cEndDate.getTime() : " + cEndDate.getTime() + A.R);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// 시작날짜와 끝 날짜를 비교해, 시작날짜가 작거나 같은 경우 출력
		while(cStartDate.compareTo( cEndDate ) != 1) {
			
			// 해당 날짜의 요일 구하기
			int week = cStartDate.get(Calendar.DAY_OF_WEEK);
			log.debug(A.A + "[LectureScheduleService.addLectureSchedule] while(week) : " + week + A.R);
			
			// 해당 날짜가 평일이라면 db에 데이터 입력
			if(week != 1 && week != 7) {
				log.debug(A.A + "[LectureScheduleService.addLectureSchedule] if(week) : " + week + A.R);
				log.debug(A.A + "[LectureScheduleService.addLectureSchedule] if(cStartDate.getTime()) : " + cStartDate.getTime() + A.R);
				
				// 날짜 데이터 타입 변환 후 mapper 실행
				DateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String formatDate = newDateFormat.format(cStartDate.getTime());
				log.debug(A.A + "[LectureScheduleService.addLectureSchedule] formatDate : " + formatDate + A.R);
				
				// mapper에 보내기 위해 lectureSchedule vo로 변경
				lectureSchedule.setLectureScheduleDate(formatDate);
				// log.debug(A.A + "[LectureScheduleService.addLectureSchedule] lectureSchedule : " + lectureSchedule + A.R);
				
				// lectureScheduleMapper.insertLectureSchedule(lectureSchedule);
			}
			
			cStartDate.add(Calendar.DATE, 1); // 1일 더해줌
			
		}
		
	}
	
	// lecture_schedule 테이블 데이터 삭제
	public int removeLectureSchedule(String lectureScheduleDate, int lectureNo) {
		
		log.debug(A.A + "[LectureScheduleService.removeLectureSchedule] lectureScheduleDate : " + lectureScheduleDate + A.R);
		log.debug(A.A + "[LectureScheduleService.removeLectureSchedule] lectureNo : " + lectureNo + A.R);
		
		int row = lectureScheduleMapper.deleteLectureSchedule(lectureScheduleDate, lectureNo);
		
		log.debug(A.A + "[LectureScheduleService.removeLectureSchedule] row : " + row + A.R);
		
		return row;
	}
}
