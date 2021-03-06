package kr.co.nft.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.nft.lms.mapper.AttendMapper;
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
		List<StudentLecture> studentLectureList = attendMapper.insertAttendForm(map);
		log.debug(A.W +"[AttendService.addAttendForm.studentLectureList] studentLectureList : " +studentLectureList +A.R);//디버깅코드
		List<LectureSchedule> lectureScheduleList = lectureScheduleMapper.selectLectureScheduleDateByLectureNo(map);
		log.debug(A.W +"[AttendService.addAttendForm.lectureScheduleList] lectureScheduleList : "+lectureScheduleList +A.R);
		List<Attend> attendList = attendMapper.selectAttendList(map);
		log.debug(A.W +"[AttendService.addAttendForm.attendList] attendList : " +attendList +A.R);//디버깅코드
		
		//결과값 반환하는 객체 생성
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("studentLectureList", studentLectureList);
		returnMap.put("attendList", attendList);
		log.debug(A.W +"[AttendService.addAttendForm.returnMap] returnMap : " +returnMap +A.R);//디버깅코드
		
		return returnMap;
	}
	
	//출석 삽입 액션
	public int addAttend(Attend attend) {
		log.debug(A.W +"[AttendService.addAttendForm.param] attend(실행) : " +attend +A.R);
		List<Attend> attendList = attend.getAttendList();
		log.debug(A.W +"[AttendService.addAttendForm.param] attendList(실행) : " +attendList +A.R);
		
		//삽입 메소드 호출
		int row = 0;
		for(Attend a : attendList) { //attendList에 저장된 리스트를 하나씩 읽어서 삽입
			attendMapper.insertAttend(a);
			row = row + 1;
		}
		//디버깅
		if(row == attendList.size()) {
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
		int lectureNo = (int)map.get("lectureNo"); //int형으로 번호 값 가져오기
		log.debug(A.W +"[AttendService.modifyAttendForm.lectureNo] lectureNo(강의번호) : " +lectureNo +A.R);
		String attendDate = (String)map.get("attendDate"); //String 타입으로 날짜 값 가져오기
		log.debug(A.W +"[AttendService.modifyAttendForm.attendDate] attendDate(출석날짜) : " +attendDate +A.R);
		String memberId = (String)map.get("memberId"); //String 타입으로 학생 아이디 값 가져오기
		log.debug(A.W +"[AttendService.modifyAttendForm.memberId] memberId(학생아이디) : " +memberId +A.R);
		
		//매개변수에서 가져온 값 저장할 객체 생성
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("lectureNo", lectureNo); //강의번호
		paramMap.put("attendDate", attendDate); //출석날짜
		paramMap.put("memberId", memberId); //가져온 학생아이디 저장
		log.debug(A.W +"[AttendService.modifyAttendForm.paramMap] paramMap: " +paramMap +A.R);
		
		//mapper 호출
		List<Attend> attendList = attendMapper.updateAttendForm(map);
		log.debug(A.W +"[AttendService.modifyAttendForm.attendList] attendList(mapper호출) : " +attendList +A.R);
		
		//결과값 저장 객체 생성
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("lectureNo", lectureNo); //강의번호
		returnMap.put("attendDate", attendDate);//날짜
		returnMap.put("memberId", memberId); //학생아이디
		returnMap.put("attendList", attendList);// 출석목록
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
	public int removeAttend(Attend attend) {
		log.debug(A.W +"[AttendService.removeAttend.param] attend(실행시작) : " +attend +A.R);
		
		//mapper 메소드 호출
		int row = attendMapper.deleteAttend(attend);
		//디버깅
		if(row == 1) {
			log.debug(A.W +"[AttendService.removeAttend.row] row(삭제성공) : " + row + A.R);
		}else {
			log.debug(A.W +"[AttendService.removeAttend.row] 삭제실패 : " +A.R);
		}
		return row;
	}
	
	// 7. 강의별 학생 출석률
	public List<Map<String, Object>> getAttendStatusChart(int lectureNo) {
		
		log.debug(A.A +"[AttendService.getAttendStatusChart] lectureNo : " + lectureNo + A.R);
		
		List<Map<String, Object>> returnList = attendMapper.selectAttendStatusChart(lectureNo);
		log.debug(A.A +"[AttendService.getAttendStatusChart] returnList : " + returnList + A.R);
		
		return returnList;
	}

}
