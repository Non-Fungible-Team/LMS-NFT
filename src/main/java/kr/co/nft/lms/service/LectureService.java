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
	public Map<String,Object> getLectureByPage(int currentPage, int rowPerPage){ //controller 넘겨온 값
		//디버깅코드
		log.debug(A.W +"[LectureService.getLectureByPage.currentPage] currentPage  : " + currentPage +A.R);
		log.debug(A.W +"[LectureService.getLectureByPage.rowPerPage ] rowPerPage : " + rowPerPage +A.R);
		
		//컨트롤러가 넘겨준 값 가공하기
		//페이지 시작 행 번호
		int beginRow = (currentPage -1)*rowPerPage;
		log.debug(A.W +"[LectureService.getLectureByPage.beginRow ]  beginRow : " + beginRow +A.R);//디버깅코드
		
		//페이지값 받아오기
		Map<String, Object> map = new HashMap<>();
		map.put("beginRow", beginRow);
		map.put("rowPerPage", rowPerPage);
		log.debug(A.W +"[LectureService.getLectureByPage.map] map : " +map +A.R);//디버깅코드

		//강의목록 mapper메소드 호출
		List<Lecture> lectureList = lectureMapper.selectLectureByPage(map);
		log.debug(A.W +"[LectureService.getLectureByPage.lectureList] lectureList : " +lectureList +A.R);//디버깅코드
		
		//Mapper에서 반환된값 가공 -> controller로 전달
		int totalCount = lectureMapper.selectLectureCount();//전체행 수
		int lastPage = (int)(Math.ceil((double)totalCount/(double)rowPerPage)); //마지막페이지-> 소수점 올림 해서 int형변환
		//디버깅코드
		log.debug(A.W +"[LectureService.getLectureByPage.totalCount] totalCount: " +totalCount +A.R);
		log.debug(A.W +"[LectureService.getLectureByPage.lastPage] lastPage : "  + lastPage +A.R);
		
		//결과값 반환 객체생성
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("lectureList", lectureList); //강의목록 값
		returnMap.put("currentPage", currentPage); //현재페이지
		returnMap.put("lastPage", lastPage); //마지막 페이지
		log.debug(A.W +"[LectureService.getLectureByPage.returnMap] returnMap : " + returnMap +A.R);//디버깅코드
		
		return returnMap;
	}
	
	//2.강의 / 강의-강사 삽입
	public int addLecture(Lecture lecture,TeacherLecture teacherLecture) { //controller 넘겨오는 값
		log.debug(A.W +"[LectureService.addLecture.lecture] lecture  : " + lecture +A.R);
		log.debug(A.W +"[LectureService.addTeacherLecture.teacherLecture] teacherLecture  : " + teacherLecture +A.R);
		//mapper 메소드 호출
		int lectureNo = lectureMapper.insertLecture(lecture);
		log.debug(A.W +"[LectureService.addLecture.row] row : " + lectureNo +A.R);
		teacherLecture.setLectureNo(lectureNo);
		
		int row = lectureMapper.insertTeacherLecture(teacherLecture);
		//insert시 입력된 autoincrement값이 출력됨

		return row; //입력된 행 반환 = 1
	}
	
	//3. 강의수정 폼
	public Map<String,Object> modify(Map<String,Object> map) {
		log.debug(A.W +"[LectureService.updateLectureForm.map] map  : " + map +A.R);
		
		//컨트롤러 입력된 값 가공
		int lectureNo = (int)map.get("lectureNo"); //int형으로 번호 값 가져오기
		log.debug(A.W +"[LectureService.modifyLectureForm.lectureNo] lectureNo  : " + lectureNo +A.R);
		
		//가져온 번호 값 저장할 객체 생성
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("lectureNo", lectureNo); //가져온 번호 값 저장
		log.debug(A.W +"[LectureService.modifyLectureForm.paramMap] paramMap  : " + paramMap +A.R);
		
		//매퍼 반환 값 가공해서 controller 넘겨줌
		//수정폼 mapper 메소드 호출 및 저장객체 생성
		Lecture lecture = lectureMapper.updateLectureForm(lectureNo);
		log.debug(A.W +"[LectureService.modifyLectureForm.lecture] lecture  : " + lecture +A.R);
		
		//결과 값 저장
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("lecture", lecture);
		log.debug(A.W +"[LectureService.modifyLectureForm.returnMap] returnMap  : " + returnMap +A.R);
		
		return returnMap;
		
	}
	//강의수정 액션
	public int modifyLecture(Lecture lecture) {
		log.debug(A.W +"[LectureService.modifyLecture.lecture] lecture  : " + lecture +A.R);
		
		//mapper 메소드호출
		int row = lectureMapper.updateLecture(lecture);
		log.debug(A.W +"[LectureService.modifyLecture.row] row  : " + row +A.R);
		
		return row;
	}
	
	//4. 강의 삭제
	public int removeLecture(Lecture lecture) {
		log.debug(A.W +"[LectureService.removeLecture.lecture] lecture  : " + lecture +A.R);
	
		//mapper 메소드 호출
		int row = lectureMapper.deleteLecture(lecture);
		log.debug(A.W +"[LectureService.removeLecture.row] row  : " + row +A.R);
		
		return row;
	}
	
	//5&6.학생 강의 목록
	public Map<String,Object> getStudentLectureByPage(int currentPage, int rowPerPage){
		//디버깅코드
		log.debug(A.W +"[LectureService.getStudentLectureByPage.currentPage] currentPage  : " + currentPage +A.R);
		log.debug(A.W +"[LectureService.getStudentLectureByPage.rowPerPage ] rowPerPage : " + rowPerPage +A.R);
		
		//컨트롤러가 넘겨준 값 가공하기
		//페이지 시작 행 번호
		int beginRow = (currentPage -1)*rowPerPage;
		log.debug(A.W +"[LectureService.getStudentLectureByPage.beginRow ]  beginRow : " + beginRow +A.R);//디버깅코드
		
		//페이지값 받아오기
		Map<String, Object> map = new HashMap<>();
		map.put("beginRow", beginRow);
		map.put("rowPerPage", rowPerPage);
		log.debug(A.W +"[LectureService.getStudentLectureByPage.map] map : " +map +A.R);//디버깅코드

		//강의목록 mapper메소드 호출
		List<Lecture> studentLectureList = lectureMapper.selectStudentLecture(map);
		log.debug(A.W +"[LectureService.getStudentLectureByPage.studentLectureList] studentLectureList : " +studentLectureList +A.R);//디버깅코드
		
		//Mapper에서 반환된값 가공 -> controller로 전달
		int totalCount = lectureMapper.selectStudentLectureCount();
		int lastPage = (int)(Math.ceil((double)totalCount/(double)rowPerPage)); //마지막페이지-> 소수점 올림 해서 int형변환
		//디버깅코드
		log.debug(A.W +"[LectureService.getStudentLectureByPage.totalCount] totalCount: " +totalCount +A.R);
		log.debug(A.W +"[LectureService.getStudentLectureByPage.lastPage] lastPage : "  + lastPage +A.R);
		
		//결과값 반환 객체생성
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("studentLectureList", studentLectureList); //강의목록 값
		returnMap.put("currentPage", currentPage); //현재페이지
		returnMap.put("lastPage", lastPage); //마지막 페이지
		log.debug(A.W +"[LectureService.getStudentLectureByPage.returnMap] returnMap : " + returnMap +A.R);//디버깅코드
		
		return returnMap;
	}
	
	//5-2.
}
