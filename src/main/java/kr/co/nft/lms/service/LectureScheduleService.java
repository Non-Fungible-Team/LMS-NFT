package kr.co.nft.lms.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	//1.lecture_schedule 테이블 lecture_no에 따른 전체 리스트
	public Map<String,Object> getLectureScheduleListByLectureNo(int lectureNo, int currentPage, int rowPerPage ) {
		log.debug(A.W + "[LectureScheduleService.getLectureScheduleListByLectureNo] lectureNo : " + lectureNo + A.R);
		log.debug(A.W +"[LectureScheduleService.getLectureScheduleListByLectureNo.currentPage] currentPage  : " + currentPage +A.R);
		log.debug(A.W +"[LectureScheduleService.getLectureScheduleListByLectureNo.currentPage] rowPerPage  : " + rowPerPage +A.R);
		
		//컨트롤러가 넘겨준 값 가공하기
		//페이지 시작 행
		int beginRow = (currentPage -1)*rowPerPage; 
		log.debug(A.W +"[LectureScheduleService.getLectureScheduleListByLectureNo.beginRow ] beginRow : " + beginRow +A.R);//디버깅코드
		
		//페이지값 받기
		Map<String, Object> map = new HashMap<>();
		map.put("beginRow", beginRow);
		map.put("rowPerPage", rowPerPage);
		map.put("lectureNo", lectureNo);
		log.debug(A.W +"[LectureScheduleService.getLectureScheduleListByLectureNo.map] map(페이지값) : " +map +A.R);//디버깅코드
		
		//mapper메소드 호출
		List<LectureSchedule> lectureScheduleList = lectureScheduleMapper.selectLectureScheduleListByLectureNo(map);
		log.debug(A.A + "[LectureScheduleService.getLectureScheduleListByLectureNo] lectureScheduleList : " + lectureScheduleList + A.R);
		
		//mapper에서 반환된 값 가공 -> controller로 전달
		int totalCount = lectureScheduleMapper.selectLectureSchedulCount(); //전체행 수
		int lastPage = (int)(Math.ceil((double)totalCount/(double)rowPerPage)); //마지막페이지-> 소수점 올림 해서 int형변환
		log.debug(A.W +"[LectureScheduleService.getLectureScheduleListByLectureNo.totalCount] totalCount(전체행) : " +totalCount +A.R);//디버깅코드
		log.debug(A.W +"[LectureScheduleService.getLectureScheduleListByLectureNo.lastPage] lastPage(마지막페이지) : " +lastPage +A.R);//디버깅코드
		
		//결과값 반환 객체 생성
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("lectureNo", lectureNo); //강의 번호
		returnMap.put("lectureScheduleList", lectureScheduleList); //강의시간표 목록
		returnMap.put("currentPage", currentPage); //현재페이지
		returnMap.put("lastPage", lastPage); //마지막 페이지
		log.debug(A.W +"[LectureScheduleService.getLectureScheduleListByLectureNo.returnMap] returnMap : " + returnMap +A.R);//디버깅코드
		
		return returnMap;
	}
	
	//2.lecture_schedule 테이블 데이터 입력 - 운영자, 강사
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
	
	//3.lecture_schedule 테이블 데이터 삭제
	public int removeLectureSchedule(String lectureScheduleDate, int lectureNo) {
		
		log.debug(A.A + "[LectureScheduleService.removeLectureSchedule] lectureScheduleDate : " + lectureScheduleDate + A.R);
		log.debug(A.A + "[LectureScheduleService.removeLectureSchedule] lectureNo : " + lectureNo + A.R);
		
		int row = lectureScheduleMapper.deleteLectureSchedule(lectureScheduleDate, lectureNo);
		
		log.debug(A.A + "[LectureScheduleService.removeLectureSchedule] row : " + row + A.R);
		
		return row;
	}
}
