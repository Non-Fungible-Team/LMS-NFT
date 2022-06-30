package kr.co.nft.lms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.nft.lms.mapper.LectureMapper;
import kr.co.nft.lms.mapper.MemberMapper;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Lecture;
import kr.co.nft.lms.vo.Manager;
import kr.co.nft.lms.vo.Member;
import kr.co.nft.lms.vo.Student;
import kr.co.nft.lms.vo.Teacher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class HomeService {
	@Autowired private LectureMapper lectureMapper;
	@Autowired private MemberMapper memberMapper;
	@Autowired private UtilService utilService;
	@Autowired private ServletContext application;
	
	//수강목록 조회 서비스
	public Map<String,Object> getLectureByMemberId(Member loginMember,int currentPage,int rowPerPage) {
		//parameter 값 디버깅
		log.debug(A.E+"[HomeService.getLectureByMemberId.param] loginMember :" +loginMember+A.R);
		log.debug(A.E+"[HomeService.getLectureByMemberId.param] currentPage :" +currentPage+A.R);
		log.debug(A.E+"[HomeService.getLectureByMemberId.param] rowPerPage :" +rowPerPage+A.R);
		//요청값 가공
		Map<String, Object> putMap = new HashMap<>();
		putMap.put("memberId", loginMember.getMemberId());
		putMap.put("beginRow", (currentPage-1)*rowPerPage);
		putMap.put("rowPerPage", rowPerPage);
		
		//모델값 변수 초기화
		int lectureListTotalCount = 0;
		int lectureListLastPage = 0;
		
		//loginMember 안에 level로 학생과 강사,운영자를 구분 후 List에 lectureList 저장
		List<Lecture> lectureList = new ArrayList<>();
		// 1. 학생인경우
		if(loginMember.getMemberLevel()==4) {
			// lecturelist rowPerPage개 요청
			lectureList = lectureMapper.selectLectureByStudent(putMap);
			//전체 lectureList 갯수 요청
			lectureListTotalCount = lectureMapper.selectLectureTotalCountByStudent(); 
			log.debug(A.E+"[HomeService.getLectureByMemberId.selectLectureByStudent] 학생 lectureList :" +lectureList+A.R);
			log.debug(A.E+"[HomeService.getLectureByMemberId.selectLectureByStudent] 학생 lectureListTotalCount :" +lectureListTotalCount+A.R);
			//모델 값 가공 - 마지막 페이지
			lectureListLastPage=((lectureListTotalCount - 1) / rowPerPage + 1);
		//2.강사의 경우	
		}else if(loginMember.getMemberLevel()==5) {
			// lecturelist rowPerPage개 요청
			lectureList = lectureMapper.selectLectureByTeacher(putMap);
			//전체 lectureList 갯수 요청
			lectureListTotalCount = lectureMapper.selectLectureTotalCountByTeacher(); 
			//전체 lectureList 갯수 요청
			log.debug(A.E+"[HomeService.getLectureByMemberId.selectLectureByStudent] 강사 lectureList :" +lectureList+A.R);
			log.debug(A.E+"[HomeService.getLectureByMemberId.selectLectureByStudent] 강사 lectureListTotalCount :" +lectureListTotalCount+A.R);
			//모델 값 가공 - 마지막 페이지
			lectureListLastPage=((lectureListTotalCount - 1) / rowPerPage + 1);
		//3.운영자의 경우 모든 페이지 출력
	}else if(loginMember.getMemberLevel() >= 6) {
		// lecturelist 10개 요청
		lectureList = lectureMapper.selectLectureByPage(putMap);
		//전체 lectureList 갯수 요청
		lectureListTotalCount = lectureMapper.selectLectureCount();
		//모델 값 가공 - 마지막 페이지
		lectureListLastPage=((lectureListTotalCount - 1) / rowPerPage + 1);
		log.debug(A.E+"[HomeService.getLectureByMemberId.selectLectureByStudent] 강사 lectureList :" +lectureList+A.R);
	}
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("lectureList", lectureList);
		returnMap.put("lectureListLastPage", lectureListLastPage);
		returnMap.put("lectureListTotalCount", lectureListTotalCount);
		return returnMap;
	}
		
		//선택한 강의정보 불러오는 서비스
		public Lecture getLectureByLectureNo(int lectureNo) {
			log.debug(A.E+"[HomeService.getLectureByLectureNo.param] lectureNo :" +lectureNo+A.R);
			//loginMember 안에 level로 학생과 강사를 구분 후 List에 lectureList 저장
				Lecture lecture = lectureMapper.selectLecturebyLectureNo(lectureNo);
				log.debug(A.E+"[HomeService.getLectureByLectureNo.selectLecturebyLectureNo] lecture :" +lecture+A.R);
			return lecture;
	}
		//휴면 계정 이메일 인증번호 요청 기능
		public String cancelRestingMember(Member loginMember) {
			//DB에 email 회원 정보 요청
			String email = null;
			//휴면 학생인경우
			if(loginMember.getMemberLevel() ==-4) {
				Student student = memberMapper.selectStudentOneByStudentVo(loginMember);
				email = student.getStudentEmail();
			//휴면 강사인 경우
			}else if(loginMember.getMemberLevel() ==-5) {
				Teacher teacher  = memberMapper.selectTeacherOneByTeacherVo(loginMember);
				email = teacher.getTeacherEmail();
			//휴먼 운영자의 경우
			}else if(loginMember.getMemberLevel() ==-6){
				Manager manager = memberMapper.selectManagerOneByManagerVo(loginMember);
				email = manager.getManagerEmail();
			}
			log.debug(A.E+"[MemberController.cancelRestingMember] 휴면 계정 해제 요청 email :"+email+A.R);
			//메일 인증코드 생성
			//중복되지 않는 새로운 문자열 필요 (UUID API 사용)
			String randomUUID = UUID.randomUUID().toString();
			//6글자로 잘라서 전송
			String verificationCode = randomUUID.substring(0,6);
			
			//메일에 인증번호 요청
			utilService.sendMail(email, verificationCode);
			//인증번호와 아이디를 application Scope에 저장
			application.setAttribute(loginMember.getMemberId()+"verify", verificationCode);
			log.debug(A.E+"[MemberController.cancelRestingMember] 휴면 계정 해제 요청 verificationCode :"+application.getAttribute(loginMember.getMemberId()+"verify")+A.R);
			//email 리턴
			return email;
		}
		
	

	

}
