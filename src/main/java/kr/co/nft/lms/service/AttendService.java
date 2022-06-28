package kr.co.nft.lms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import kr.co.nft.lms.mapper.AttendMapper;
import kr.co.nft.lms.mapper.LectureMapper;
import kr.co.nft.lms.mapper.MemberMapper;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Attend;
import kr.co.nft.lms.vo.Student;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class AttendService {
	//메퍼 변수 생성
	@Autowired private AttendMapper attendMapper;
	@Autowired private LectureMapper lectureMapper;
	@Autowired private MemberMapper memberMapper;
	
	//1.강의별 전체 학생의 출석 목록(운영자,강사)  + 전체 행수
	public Map<String,Object> getAttendListByPage(int lectureNo){
		log.debug(A.W +"[AttendService.getAttendListByPage.출석목록] 출석목록 서비스 " +A.R);
		log.debug(A.W +"[AttendService.getAttendListByPage.param] lectureNo : "+lectureNo +A.R);
		
		//출석목록 mapper메소드 호출
		List<Attend> attendList = attendMapper.selectAttendListByPage(lectureNo);
		log.debug(A.W +"[AttendService.getAttendListByPage.attendList(매퍼호출)] attendList : "+ attendList +A.R);
		
		//결과값 map으로 반환
		Map<String, Object> returnMap = new HashMap<>(); //객체생성
		returnMap.put("lectureNo", lectureNo); //번호값 저장
		log.debug(A.W +"[AttendService.getAttendListByPage.returnMap(강의번호 값)] returnMap : "+ returnMap +A.R);
		
		return returnMap;
	}
	
	//2.학생이 자신이 듣는 강의별 출석 확인
	public Map<String, Object> getStudentAttendOne(int lectureNo, String memberId){
		log.debug(A.W +"[AttendService.getStudentAttendOne.학생 개인 출석목록] 출석목록 서비스 " +A.R);
		log.debug(A.W +"[AttendService.getStudentAttendOne.param] lectureNo : "+lectureNo +A.R);
		log.debug(A.W +"[AttendService.getStudentAttendOne.param] memberId : "+memberId +A.R);
		
		List<Attend> studentAttendList = attendMapper.selectStudentAttendOne(lectureNo, memberId);
		log.debug(A.W +"[AttendService.getAttendListByPage.studentAttendList(매퍼호출)] studentAttendList : "+ studentAttendList +A.R);
		
		//결과값 map으로 반환
		Map<String, Object> returnMap = new HashMap<>(); //객체생성
		returnMap.put("lectureNo", lectureNo); //번호값 저장
		returnMap.put("memberId", memberId); //학생아이디값 저장
		log.debug(A.W +"[AttendService.getAttendListByPage.returnMap(강의번호 값)] returnMap : "+ returnMap +A.R);
		
		return returnMap;
	}
	
	//3.학생 출석 삽입 폼
	/*public Map<String,Object> addAttendForm(){
		log.debug(A.W +"[AttendService.addAttendForm]  출석 삽입 폼" +A.R);
		
		
		return null;
	}*/
	
}
