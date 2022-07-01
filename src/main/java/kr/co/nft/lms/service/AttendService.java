package kr.co.nft.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.nft.lms.mapper.AttendMapper;
import kr.co.nft.lms.mapper.LectureMapper;
import kr.co.nft.lms.mapper.LectureScheduleMapper;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Attend;
import kr.co.nft.lms.vo.LectureSchedule;
import kr.co.nft.lms.vo.StudentLecture;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class AttendService {
	//메퍼 변수 생성
	@Autowired private AttendMapper attendMapper;
	@Autowired private LectureMapper lectureMapper;
	@Autowired private LectureScheduleMapper lectureScheduleMapper;
	
	//1.강의별 전체 학생의 출석 목록(운영자,강사)  + 전체 행수
	public Map<String,Object> getAttendList(int lectureNo, String attendDate){
		log.debug(A.W +"[AttendService.getAttendListByPage.출석목록] 출석목록 서비스 " +A.R);
		log.debug(A.W +"[AttendService.getAttendListByPage.param] lectureNo : "+lectureNo +A.R);
		log.debug(A.W +"[AttendService.getAttendListByPage.param] attendDate : "+attendDate +A.R);
		
		//가져온 번호값 저장
		Map<String,Object>  map = new HashMap<>();
		map.put("lectureNo", lectureNo);
		map.put("attendDate",attendDate);
		log.debug(A.W +"[AttendService.getAttendListByPage.map] map : "+map +A.R);
		
		//출석목록 mapper메소드 호출
		List<Attend> attendList = attendMapper.selectAttendList(map);
		log.debug(A.W +"[AttendService.getAttendListByPage.attendList(출석목록 매퍼호출)] attendList : "+ attendList +A.R);
		List<LectureSchedule> lectureScheduleList = lectureScheduleMapper.selectLectureScheduleDateByLectureNo(map);
		log.debug(A.W +"[AttendService.getAttendListByPage.param] lectureScheduleList(강의시간표) : "+lectureScheduleList +A.R);
		
		//결과값 map으로 반환
		Map<String, Object> returnMap = new HashMap<>(); //객체생성
		returnMap.put("lectureNo", lectureNo); //번호값 저장
		returnMap.put("attendDate",attendDate);
		returnMap.put("attendList",attendList); //출석 목록
		returnMap.put("lectureScheduleList", lectureScheduleList);
		log.debug(A.W +"[AttendService.getAttendListByPage.returnMap(결과값)] lectureNo : "+ lectureNo +A.R);
		log.debug(A.W +"[AttendService.getAttendListByPage.returnMap(결과값)] attendDate : "+ attendDate +A.R);
		log.debug(A.W +"[AttendService.getAttendListByPage.returnMap(결과값)] attendList : "+ attendList +A.R);
		log.debug(A.W +"[AttendService.getAttendListByPage.returnMap(결과값)] returnMap : "+ returnMap +A.R);
		
		return returnMap;
	}
	
	//2.학생이 자신이 듣는 강의별 출석 확인
	public Map<String, Object> getStudentAttendOne(int lectureNo, String memberId){
		log.debug(A.W +"[AttendService.getStudentAttendOne.학생 개인 출석목록] 출석목록 서비스 " +A.R);
		log.debug(A.W +"[AttendService.getStudentAttendOne.param] lectureNo : "+lectureNo +A.R);
		log.debug(A.W +"[AttendService.getStudentAttendOne.param] memberId : "+memberId +A.R);

		//가져온 번호값 저장
		Map<String,Object> map = new HashMap<>();
		map.put("lectureNo", lectureNo);
		map.put("memberId", memberId);
		log.debug(A.W +"[AttendService.getStudentAttendOne.map] map : "+map +A.R);
		log.debug(A.W +"[AttendService.getStudentAttendOne.map] lectureNo : "+lectureNo +A.R);
		log.debug(A.W +"[AttendService.getStudentAttendOne.map] memberId : "+memberId +A.R);
		
		//mapper 호출
		List<Attend> studentAttendList = attendMapper.selectStudentAttendOne(map);
		log.debug(A.W +"[AttendService.getAttendListByPage.studentAttendList(매퍼호출)] studentAttendList : "+ studentAttendList +A.R);
		
		//결과값 map에 저장
		Map<String, Object> returnMap = new HashMap<>(); //객체생성
		returnMap.put("lectureNo", lectureNo); //번호값 저장
		returnMap.put("memberId", memberId); //학생아이디값 저장
		returnMap.put("studentAttendList", studentAttendList); //학생 출석값
		log.debug(A.W +"[AttendService.getAttendListByPage.returnMap(결과값)] returnMap : "+ returnMap +A.R);
		log.debug(A.W +"[AttendService.getAttendListByPage.returnMap(결과값)] lectureNo : "+ lectureNo +A.R);
		log.debug(A.W +"[AttendService.getAttendListByPage.returnMap(결과값)] memberId : "+ memberId +A.R);
		
		return returnMap;
	}
	
	//3.학생 출석 삽입 폼
	public Map<String,Object> addAttendForm(Map<String,Object> map){
		log.debug(A.W +"[AttendService.addAttendForm]  출석 삽입 폼" +A.R);
		
		//mapper 호출(학생,강의 목록 및 강의시간표)
		List<Attend> attendList = attendMapper.selectAttendList(map);
		log.debug(A.W +"[AttendService.addAttendForm.attendList] attendList : " +attendList +A.R);//디버깅코드
		
		
		//결과값 반환하는 객체 생성
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("attendList", attendList);
		log.debug(A.W +"[AttendService.addAttendForm.returnMap] returnMap : " +returnMap +A.R);//디버깅코드
		
		return returnMap;
	}
	
	//출석 삽입 액션
	public int addAttend(Attend attend) {
		log.debug(A.W +"[AttendService.addAttendForm.param] attend(실행) : " +attend +A.R);
		
		//삽입 메소드 호출
		int row = attendMapper.insertAttend(attend);
		if(row == 1) {
			log.debug(A.W +"[AttendService.addAttend.row] row(삽입성공) : " +row +A.R);
		}else {
			log.debug(A.W +"[AttendService.addAttend.row] row(삽입실패) :" +A.R);
		}
		
		return row;
	}
	
	//4.출석 수정 폼
	public Map<String,Object> modifyAttendForm(Map<String,Object> map){
		log.debug(A.W +"[AttendService.modifyAttendForm.param] map(실행시작) : " +map +A.R);
		
		//컨트롤러에서 받아온 값 가공
		String memberId = (String)map.get("memberId"); //String 타입으로 학생 아이디 값 가져오기
		log.debug(A.W +"[AttendService.modifyAttendForm.memberId] memberId(학생아이디) : " +memberId +A.R);
		
		//매개변수에서 가져온 값 저장할 객체 생성
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("memberId", memberId); //가져온 학생아이디 저장
		log.debug(A.W +"[AttendService.modifyAttendForm.paramMap] paramMap: " +paramMap +A.R);
		
		//mapper 반환 값 가공 -> controller
		Attend attend = attendMapper.updateAttendForm(memberId);
		log.debug(A.W +"[AttendService.modifyAttendForm.attend] attend(mapper호출) : " +attend +A.R);
		
		//mapper값 가져오기(학생아이디, 강의정보,강의시간표) 
		List<StudentLecture> studentLectureList = lectureMapper.selectStudentLectureAll(map); //학생-강의 목록
		List<LectureSchedule> lectureScheduleList = lectureScheduleMapper.selectLectureScheduleListByLectureNo(map); //날짜
		log.debug(A.W +"[AttendService.modifyAttendForm.studentLectureList] studentLectureList : " +studentLectureList +A.R);//디버깅코드
		log.debug(A.W +"[AttendService.modifyAttendForm.lectureScheduleList] lectureScheduleList : " +lectureScheduleList +A.R);//디버깅코드
		
		//결과값 저장 객체 생성
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("memberId", memberId); //학생아이디
		returnMap.put("studentLectureList", studentLectureList); //학생-강의 정보
		returnMap.put("lectureScheduleList", lectureScheduleList); //강의시간표
		log.debug(A.W +"[AttendService.modifyAttendForm.returnMap] returnMap(수정폼 결과값) : " +returnMap +A.R);//디버깅코드
		
		return returnMap;
	}
	//액션
	public int modifyAttend(Attend attend) {
		log.debug(A.W +"[AttendService.modifyAttend.param] attend(실행시작) : " +attend +A.R);
		
		//mapper메소드 호출
		int row = attendMapper.updateAttend(attend);
		if(row ==1) {
			log.debug(A.W +"[AttendService.modifyAttend.row] row(수정성공) : " +row +A.R);
		}else {
			log.debug(A.W +"[AttendService.modifyAttend.row] 수정실패 : " +A.R);
		}
		return row;
	}
	
	//5.출석 삭제
	public int removeAttend(String memberId) {
		log.debug(A.W +"[AttendService.removeAttend.param] memberId(실행시작) : " +memberId +A.R);
		
		//mapper 메소드 호출
		int row = attendMapper.deleteAttend(memberId);
		//디버깅
		if(row ==1) {
			log.debug(A.W +"[AttendService.removeAttend.row] row(삭제성공) : " + row + A.R);
		}else {
			log.debug(A.W +"[AttendService.removeAttend.row] 수정실패 : " +A.R);
		}
		return row;
	}
}
