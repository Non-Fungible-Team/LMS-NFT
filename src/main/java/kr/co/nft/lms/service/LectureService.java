package kr.co.nft.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.nft.lms.mapper.LectureMapper;
import kr.co.nft.lms.mapper.LectureRoomMapper;
import kr.co.nft.lms.mapper.MemberMapper;
import kr.co.nft.lms.mapper.SubjectMapper;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Lecture;
import kr.co.nft.lms.vo.LectureRoom;
import kr.co.nft.lms.vo.Student;
import kr.co.nft.lms.vo.StudentLecture;
import kr.co.nft.lms.vo.Subject;
import kr.co.nft.lms.vo.Teacher;
import kr.co.nft.lms.vo.TeacherLecture;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Service
@Transactional
public class LectureService {
	//Mapper 변수 생성
	@Autowired private LectureMapper lectureMapper;
	@Autowired private SubjectMapper subjectMapper;
	@Autowired private LectureRoomMapper lectureRoomMapper;
	@Autowired private MemberMapper memberMapper;
	
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
	public Map<String,Object> addLectureForm(){
		
		List<Subject> subjectList = subjectMapper.selectSubjectList();
		List<LectureRoom> lectureRoomList = lectureRoomMapper.selectLectureRoomList();
		List<Teacher> teacherList = memberMapper.selectTeacherList();
		log.debug(A.W +"[LectureService.addLectureForm.subjectList] subjectList : " +subjectList +A.R);//디버깅코드
		log.debug(A.W +"[LectureService.addLectureForm.lectureRoomList] lectureRoomList : " +lectureRoomList +A.R);//디버깅코드
		log.debug(A.W +"[LectureService.addLectureForm.teacherList] teacherList : " +teacherList +A.R);//디버깅코드
		
		//결과값 반환 객체생성
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("subjectList", subjectList); //과목목록 값
		returnMap.put("lectureRoomList", lectureRoomList); //강의실목록 값
		returnMap.put("teacherList", teacherList); //강의실목록 값
		log.debug(A.W +"[LectureService.addLectureForm.returnMap] returnMap : " +returnMap +A.R);//디버깅코드
		
		return returnMap;
	}
	//액션
	public int addLecture(Lecture lecture) { //controller 넘겨오는 값
		log.debug(A.W +"[LectureService.addLecture.lecture] lecture  : " + lecture +A.R);
		//mapper 메소드 호출
		int lectureRow = lectureMapper.insertLecture(lecture);
		if(lectureRow == 1) {
		log.debug(A.W +"[LectureService.addLecture.lectureRow] lectureRow : " + lectureRow +A.R);
		log.debug(A.W +"[LectureService.addLecture.lectureRow] 입력 성공" +A.R);
		}else {
			log.debug(A.W +"[LectureService.addLecture.lectureRow] 입력 실패"+A.R);
		}
		int TeacherLectureRow = lectureMapper.insertTeacherLecture(lecture);
		if(TeacherLectureRow == 1) {
			log.debug(A.W +"[LectureService.addLecture.TeacherLectureRow] TeacherLectureRow : " + TeacherLectureRow +A.R);
			log.debug(A.W +"[LectureService.addLecture.TeacherLectureRow] 입력 성공 " +A.R);
		}else {
			log.debug(A.W +"[LectureService.addLecture.TeacherLectureRow] 입력 실패 "+A.R);
		}
		
		return TeacherLectureRow; //입력된 행 반환 = 1
	}
	
	//3. 강의수정 폼
	public Map<String,Object> modifyLectureForm(Map<String,Object> map) {
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
		
		//mapper 값 가져오기
		List<Subject> subjectList = subjectMapper.selectSubjectList();
		List<LectureRoom> lectureRoomList = lectureRoomMapper.selectLectureRoomList();
		List<Teacher> teacherList = memberMapper.selectTeacherList();
		log.debug(A.W +"[LectureService.modifyLectureForm.subjectList] subjectList : " +subjectList +A.R);//디버깅코드
		log.debug(A.W +"[LectureService.modifyLectureForm.lectureRoomList] lectureRoomList : " +lectureRoomList +A.R);//디버깅코드
		log.debug(A.W +"[LectureService.modifyLectureForm.teacherList] teacherList : " +teacherList +A.R);//디버깅코드
				
		//결과 값 저장
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("lecture", lecture);
		returnMap.put("subjectList", subjectList); //과목목록 값
		returnMap.put("lectureRoomList", lectureRoomList); //강의실목록 값
		returnMap.put("teacherList", teacherList); //강의실목록 값
	
		log.debug(A.W +"[LectureService.modifyLectureForm.returnMap] returnMap  : " + returnMap +A.R);
		
		return returnMap;	
	}
	//강의수정 액션
	public int modifyLecture(Lecture lecture, TeacherLecture teacherLecture) {
		log.debug(A.W +"[LectureService.modifyLecture.lecture] lecture  : " + lecture +A.R);
		
		//mapper 메소드호출
		int row = lectureMapper.updateLecture(lecture);
		if(row == 1) {
			log.debug(A.W +"[LectureService.modifyLecture.row] row  : " + row +A.R);
			log.debug(A.W +"[LectureService.modifyLecture.row] 수정성공" +A.R);
		}else {
			log.debug(A.W +"[LectureService.modifyLecture.row] 수정실패" +A.R);
		}
		
		int TeacherLectureRow = lectureMapper.updateTeacherLecture(teacherLecture);
		if(TeacherLectureRow == 1) {
			log.debug(A.W +"[LectureService.modifyLecture.TeacherLectureRow] TeacherLectureRow  : " + TeacherLectureRow +A.R);
			log.debug(A.W +"[LectureService.modifyLecture.TeacherLectureRow] 수정성공" +A.R);
		}else {
			log.debug(A.W +"[LectureService.modifyLecture.TeacherLectureRow] 수정실패" +A.R);
		}
		
		return TeacherLectureRow;
	}
	
	//4. 강의 삭제
	public int removeLecture(int lectureNo) {
		log.debug(A.W +"[LectureService.removeLecture.lectureNo] lectureNo  : " + lectureNo +A.R);
	
		//mapper 메소드 호출
		int TeacherLectureRow = lectureMapper.deleteTeacherLecture(lectureNo);
		if(TeacherLectureRow == 1) {
			log.debug(A.W +"[LectureService.removeLecture.TeacherLectureRow] TeacherLectureRow  : " + TeacherLectureRow +A.R);
			log.debug(A.W +"[LectureService.removeLecture.TeacherLectureRow] 삭제 성공 " +A.R);
		}else {
			log.debug(A.W +"[LectureService.removeLecture.TeacherLectureRow] 삭제 실패 " +A.R);
		}
		
		int lectureRow = lectureMapper.deleteLecture(lectureNo);
		if(lectureRow ==1) {
			log.debug(A.W +"[LectureService.removeLecture.lectureRow] lectureRow  : " + lectureRow +A.R);
			log.debug(A.W +"[LectureService.removeLecture.lectureRow] 삭제 성공 " +A.R);
		}else {
			log.debug(A.W +"[LectureService.removeLecture.lectureRow] 삭제 실패 " +A.R);
		}

		return lectureRow;
	}
	
	//5.학생 강의 목록
	public Map<String,Object> getStudentLectureByPage(int lectureNo) {
		//디버깅코드
		log.debug(A.A + "[LectureService.getStudentLectureByPage.lectureNo] lectureNo  : " + lectureNo + A.R);
		
		//매개변수값 받아오기
		Map<String, Object> map = new HashMap<>();
		map.put("lectureNo", lectureNo);
		log.debug(A.W +"[LectureService.getStudentLectureByPage.map] map : " +map +A.R);//디버깅코드

		//강의목록 mapper메소드 호출
		List<StudentLecture> studentLectureList = lectureMapper.selectStudentLecture(map);
		log.debug(A.W +"[LectureService.getStudentLectureByPage.studentLectureList] studentLectureList : " +studentLectureList +A.R);//디버깅코드
		
		//결과값 반환 객체생성
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("studentLectureList", studentLectureList); //강의목록 값
		log.debug(A.W +"[LectureService.getStudentLectureByPage.returnMap] returnMap : " + returnMap +A.R);//디버깅코드
		
		return returnMap;
	}
	
	//5.학생 강의 전체 목록
	public Map<String,Object> getStudentLectureAllByPage(int currentPage, int rowPerPage) {
		//디버깅코드
		log.debug(A.A + "[LectureService.getStudentLectureAllByPage.currentPage] currentPage  : " + currentPage + A.R);
		log.debug(A.A + "[LectureService.getStudentLectureAllByPage.rowPerPage ] rowPerPage : " + rowPerPage + A.R);
		
		//컨트롤러가 넘겨준 값 가공하기
		//페이지 시작 행 번호
		int beginRow = (currentPage - 1) * rowPerPage;
		log.debug(A.A + "[LectureService.getStudentLectureAllByPage.beginRow ] beginRow : " + beginRow + A.R);
		
		//페이지값 받아오기
		Map<String, Object> map = new HashMap<>();
		map.put("beginRow", beginRow);
		map.put("rowPerPage", rowPerPage);
		log.debug(A.A +"[LectureService.getStudentLectureAllByPage.map] map : " + map + A.R);

		//강의목록 mapper메소드 호출
		List<StudentLecture> studentLectureAllList = lectureMapper.selectStudentLectureAll(map);
		log.debug(A.A + "[LectureService.getStudentLectureAllByPage.studentLectureAllList] studentLectureAllList : " + studentLectureAllList + A.R);//디버깅코드
		
		//Mapper에서 반환된값 가공 -> controller로 전달
		int totalCount = lectureMapper.selectStudentLectureCount();
		int lastPage = (int)(Math.ceil((double)totalCount/(double)rowPerPage)); //마지막페이지-> 소수점 올림 해서 int형변환
		//디버깅코드
		log.debug(A.A + "[LectureService.getStudentLectureAllByPage.totalCount] totalCount : " + totalCount + A.R);
		log.debug(A.A + "[LectureService.getStudentLectureAllByPage.lastPage] lastPage : "  + lastPage + A.R);
		
		//결과값 반환 객체생성
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("studentLectureAllList", studentLectureAllList); //강의목록 값
		returnMap.put("currentPage", currentPage); //현재페이지
		returnMap.put("lastPage", lastPage); //마지막 페이지
		log.debug(A.A +"[LectureService.getStudentLectureByPage.returnMap] returnMap : " + returnMap + A.R);//디버깅코드
		
		return returnMap;
	}
		
	//5-2.학생-강의 삽입 폼
	public Map<String, Object> addStudentLecture() {
		
		//mapper 메소드 호출
		List<Student> studentList = memberMapper.selectStudentList();
		List<Lecture> lectureNoNameList = lectureMapper.selectLectureNoNameList();
		
		log.debug(A.A +"[LectureService.addStudentLecture] studentList : " + studentList + A.R);
		log.debug(A.A +"[LectureService.addStudentLecture] lectureNoNameList : " + lectureNoNameList + A.R);

		Map<String , Object> returnMap = new HashMap<>();
		returnMap.put("studentList", studentList);
		returnMap.put("lectureNoNameList", lectureNoNameList);
		
		return returnMap;
	}
	
	//5-2.학생-강의 삽입 액션
	public int addStudentLectureAction(StudentLecture studentLecture) { //controller 넘겨오는 값
		
		log.debug(A.A +"[LectureService.addStudentLectureAction.studentLecture] studentLecture  : " + studentLecture + A.R);
		
		//mapper 메소드 호출
		int row = lectureMapper.insertStudentLecture(studentLecture);
		log.debug(A.W +"[LectureService.addStudentLecture.row] row : " + row +A.R);
						
		return row;
	}
	
	//5-3. 학생-강의 목록 수정폼
	public StudentLecture modifyStudentLectureForm(int lectureNo, String memberId) {
		
		// 파라미터 값 디버깅
		log.debug(A.A +"[LectureService.modifyStudentLectureForm] lectureNo : " + lectureNo + A.R);
		log.debug(A.A +"[LectureService.modifyStudentLectureForm] memberId : " + memberId + A.R);
		
		// 컨트롤러 입력된 값 가공 + 디버깅
		StudentLecture studentLecture = new StudentLecture();
		studentLecture.setLectureNo(lectureNo);
		studentLecture.setMemberId(memberId);
		
		log.debug(A.A +"[LectureService.modifyStudentLectureForm] studentLecture : " + studentLecture + A.R);
		
		// 매퍼 반환 값 가공해서 controller에 넘겨줌 (수정폼 mapper 메소드 호출 및 저장객체 생성)
		StudentLecture studentLectureOne = lectureMapper.updateStudentLectureForm(studentLecture);
		
		log.debug(A.A +"[LectureService.modifyStudentLectureForm] studentLectureOne : " + studentLectureOne + A.R);
		
		return studentLectureOne;
	}
	
	//학생-강의 목록 수정액션
	public int modifyStudentLectureAction(StudentLecture studentLecture) {
		log.debug(A.A +"[LectureService.modifyStudentLectureAction.studentLecture] studentLecture  : " + studentLecture +A.R);
		
		int row = lectureMapper.updateStudentLecture(studentLecture);
		log.debug(A.A +"[LectureService.modifyStudentLectureAction.row] row  : " + row + A.R);
		
		return row;
	}
	
	// 5-4. 학생 강의 삭제
	public int removeStudentLectureAction(int lectureNo, String memberId) {
		
		log.debug(A.A +"[LectureService.removeStudentLectureAction] lectureNo  : " + lectureNo + A.R);
		log.debug(A.A +"[LectureService.removeStudentLectureAction] memberId  : " + memberId + A.R);
		
		StudentLecture studentLecture = new StudentLecture();
		studentLecture.setLectureNo(lectureNo);
		studentLecture.setMemberId(memberId);
		
		int row = lectureMapper.deleteStudentLecture(studentLecture);
		
		log.debug(A.A +"[LectureService.removeStudentLectureAction] row  : " + row + A.R);
		
		return row;
	}
	
	//6. 운영자- 강의 목록
	public Map<String,Object> getTeacherLectureByPage(int currentPage, int rowPerPage){ //controller 넘겨온 값
		//디버깅코드
		log.debug(A.W +"[LectureService.getManagerLectureByPage.currentPage] currentPage  : " + currentPage +A.R);
		log.debug(A.W +"[LectureService.getManagerLectureByPage.rowPerPage ] rowPerPage : " + rowPerPage +A.R);
		
		//컨트롤러가 넘겨준 값 가공하기
		//페이지 시작 행 번호
		int beginRow = (currentPage -1)*rowPerPage;
		log.debug(A.W +"[LectureService.getManagerLectureByPage.beginRow ]  beginRow : " + beginRow +A.R);//디버깅코드
		
		//페이지값 받아오기
		Map<String, Object> map = new HashMap<>();
		map.put("beginRow", beginRow);
		map.put("rowPerPage", rowPerPage);
		log.debug(A.W +"[LectureService.getManagerLectureByPage.map] map : " +map +A.R);//디버깅코드 

		//강사_강의목록 mapper메소드 호출
		List<TeacherLecture> teacherLectureList = lectureMapper.selectTeacherLectureByPage(map);
		
		log.debug(A.W +"[LectureService.getManagerLectureByPage.teacherLectureList] teacherLectureList : " +teacherLectureList  +A.R);//디버깅코드
		
		
		
		//Mapper에서 반환된값 가공 -> controller로 전달
		int totalCount = lectureMapper.selectTeacherLectureCount();//전체행 수
		int lastPage = (int)(Math.ceil((double)totalCount/(double)rowPerPage)); //마지막페이지-> 소수점 올림 해서 int형변환
		//디버깅코드
		log.debug(A.W +"[LectureService.getManagerLectureByPage.totalCount] totalCount: " +totalCount +A.R);
		log.debug(A.W +"[LectureService.getManagerLectureByPage.lastPage] lastPage : "  + lastPage +A.R);
		
		//결과값 반환 객체생성
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("teacherLectureList", teacherLectureList); //강가_강의목록 값
		returnMap.put("currentPage", currentPage); //현재페이지
		returnMap.put("lastPage", lastPage); //마지막 페이지
		log.debug(A.W +"[LectureService.getLectureByPage.returnMap] returnMap : " + returnMap +A.R);//디버깅코드
		
		return returnMap;
	}
	//강의 삽입시에, 개강일과 수료일이 정해지면, 그 기간에 수업 가능한 강사 목록, 사용가능한 강의실을 DB에 요청하는 서비스
	//[LectureRestController.getTeacherListByDates]에서 호출
	public Map<String,Object> getTeacherListAndLectureRoomList(String lectureStartDate,String lectureEndDate){
		log.debug(A.E+"[LectureService.getTeacherListByDates] lectureStartDate:"+lectureStartDate+A.R);
		log.debug(A.E+"[LectureService.getTeacherListByDates] lectureEndDate:"+lectureEndDate+A.R);
		//강의 시간이 겹치지않은 강사 리스트 요청
		List<Teacher> teacherList = lectureMapper.selectTeacherListByDates(lectureStartDate, lectureEndDate);
		//강의 시간이 겹치지않은 강의실 리스트 요청
		log.debug(A.E+"[LectureService.getTeacherListByDates] teacherList:"+teacherList+A.R);
		List<LectureRoom> lectureRoomList = lectureMapper.selectLectureRoomListByDates(lectureStartDate, lectureEndDate);
		log.debug(A.E+"[LectureService.getTeacherListByDates] lectureRoomList:"+lectureRoomList+A.R);
		//모델값 반환
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("teacherList", teacherList);
		returnMap.put("lectureRoomList", lectureRoomList);
		return returnMap;
	}
	
	//차트 - 강사별 강의시간 통계
	public Map<String,Object> getTeacherLectureChart(){
		log.debug(A.W +"[LectureService.getTeacherLectureChart] 차트 통계 " +A.R);//디버깅코드

		List<Map<String, Object>> teacherLectureChart = lectureMapper.selectTeacherLectureChart();
		log.debug(A.W +"[LectureService.getTeacherLectureChart.teacherLectureChart]teacherLectureChart "+teacherLectureChart +A.R);//디버깅코드
		
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("teacherLectureChart", teacherLectureChart);
		log.debug(A.W +"[LectureService.getTeacherLectureChart.returnMap] returnMap "+returnMap +A.R);//디버깅코드
		
		return returnMap;
		
	}
}