package kr.co.nft.lms.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.nft.lms.service.LectureService;
import kr.co.nft.lms.util.A;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@CrossOrigin
public class LectureRestController {
	@Autowired private LectureService lectureService;
	
	//강의 삽입시에, 개강일과 수료일이 정해지면, 그 기간에 수업 가능한 강사 목록, 사용가능한 강의실이 뜨는 레스트 컨트롤러
	@GetMapping("/manager/getTeacherListAndLectureRoomList")
	public Map<String,Object> getTeacherListAndLectureRoomList(String lectureStartDate, String lectureEndDate){
	log.debug(A.E+"[LectureRestController.getTeacherListByDates] lectureStartDate:"+lectureStartDate+A.R);	
	log.debug(A.E+"[LectureRestController.getTeacherListByDates] lectureEndDate:"+lectureEndDate+A.R);
	//lectureService에 강사리스트, 강의실 리스트 요청
	Map<String,Object> returnMap = lectureService.getTeacherListAndLectureRoomList(lectureStartDate, lectureEndDate);
	log.debug(A.E+"[LectureRestController.getTeacherListByDates] returnMap:"+returnMap+A.R);
	return returnMap;
	}
	
	//강사별 수업시간 통계차트
	@GetMapping("/rest/manager/getTeacherLectureChart")
	public Map<String,Object> getTeacherLectureChart(){
		log.debug(A.W +"[LectureRestController.getTeacherLectureChart] 차트실행  " +A.R);
		
		Map<String,Object> returnMap = lectureService.getTeacherLectureChart();
		log.debug(A.W +"[LectureRestController.getTeacherLectureChart.returnMap] returnMap "+returnMap +A.R);
		return returnMap;	
	}

}
