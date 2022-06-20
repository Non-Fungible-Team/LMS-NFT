package kr.co.nft.lms.service;

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
	public List<LectureSchedule> getLectureScheduleListByLectureNo() {
		
		List<LectureSchedule> lectureScheduleList = lectureScheduleMapper.selectLectureScheduleListByLectureNo();
		log.debug(A.A + "[LectureScheduleService.getLectureScheduleListByLectureNo] lectureScheduleList : " + lectureScheduleList + A.R);
		
		return lectureScheduleList;
	}
	
	/*
	// lecture_schedule 테이블 데이터 입력 - 운영자, 강사
	public void addLectureSchedule(LectureSchedule lectureSchedule) {
		
		String scheduleDate = ""; // 학원 오는 날짜
		
		// String lectureScheduleStartDate = lectureSchedule.getLectureScheduleStartDate();
		// String lectureScheduleEndDate = lectureSchedule.getLectureScheduleEndDate();
		
		String lectureScheduleStartDate = "2022-05-01";
		String lectureScheduleEndDate = "2022-05-10";
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		
		Calendar cal = Calendar.getInstance();
		
		// cal.set ( 2019, 1-1, 1 ); // 시작 날짜
		cal.set(lectureScheduleStartDate); // 시작 날짜
		String startDate = dateFormat.format(cal.getTime());
		cal.set ( 2019, 1-1, 1 ); // 종료 날짜
		String endDate = dateFormat.format(cal.getTime());
		
		int i = 0;
		
		while(!startDate.equals(endDate)) {
			
			// 해당 날짜의 요일 구하기
			
			// 해당 날짜가 평일이라면 db에 데이터 입력
			if() {
				// 날짜 데이터 타입 변환 후 mapper 실행
				SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date formatDate = newDateFormat.parse(특정날짜);
				System.out.println("디버깅" + formatDate);
				
				lectureSchedule.setLectureScheduleDate(특정날짜);
				System.out.println("디버깅" + lectureSchedule.setLectureScheduleDate(특정날짜));
				// lectureScheduleMapper.insertLectureSchedule(lectureSchedule);
			}
			
			cal.add(Calendar.DATE, 1); // 1일 더해줌
			
			i++;
		}
		
	}
	*/
	
	// lecture_schedule 테이블 데이터 삭제
}
