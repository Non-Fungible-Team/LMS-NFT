package kr.co.nft.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.nft.lms.mapper.LectureMapper;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Lecture;
import kr.co.nft.lms.vo.ManagerLecture;
import kr.co.nft.lms.vo.StudentLecture;
import kr.co.nft.lms.vo.TeacherLecture;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class LectureService {
	//Mapper 변수 생성
	@Autowired private LectureMapper lectureMapper;
	
	//1.강의목록(상세보기) + 전체 행 수
	public Map<String,Object> getLectureByPage(int currentPage, int rowPerPage){ //controller 로 넘겨줄 값
		//컨트롤러가 넘겨준 값 가공하기
		//페이지 시작 행 번호
		int beginRow = (currentPage -1)*rowPerPage;
		
		//페이지값 받아오기
		Map<String, Object> map = new HashMap<>();
		map.put("beginRow", beginRow);
		map.put("rowPerPage", rowPerPage);
		log.debug(A.W +"[LectureService.getLectureByPage.map] map : " +map +A.R);

		//강의목록 mapper메소드 호출
		List<Lecture> lectureList = lectureMapper.selectLectureByPage(map);
		log.debug(A.W +"[LectureService.getLectureByPage.lectureList] lectureList : " +lectureList +A.R);
		
		//Mapper에서 반환된값 가공 -> controller로 전달
		int totalCount = lectureMapper.selectLectureCount();//전체행 수
		int lastPage = (int)(Math.ceil((double)totalCount/(double)rowPerPage)); //마지막페이지-> 소수점 올림 해서 int형변환
		log.debug(A.W +"[LectureService.getLectureByPage.totalCount + lastPage] totalCount + lastPage : " +totalCount+ ", " + lastPage +A.R);
		
		//결과값 반환 객체생성
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("lectureList", lectureList); //강의목록 값
		returnMap.put("currentPage", currentPage); //현재페이지
		returnMap.put("lastPage", lastPage); //마지막 페이지
		log.debug(A.W +"[LectureService.getLectureByPage.returnMap + returnMap]  : " + returnMap +A.R);
		
		return returnMap;
	}
}
