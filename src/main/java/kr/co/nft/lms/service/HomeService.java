package kr.co.nft.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.nft.lms.mapper.LectureMapper;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Lecture;
import kr.co.nft.lms.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class HomeService {
	@Autowired private LectureMapper lectureMapper;
	//수강목록 조회 서비스
	public List<Lecture> getLectureByMemberId(Member loginMember) {
		log.debug(A.E+"[HomeService.getLectureByMemberId.param] loginMember :" +loginMember+A.R);
		//loginMember 안에 level로 학생과 강사를 구분 후 List에 lectureList 저장
		List<Lecture> lectureList = new ArrayList<>();
		// 1. 학생인경우
		if(loginMember.getMemberLevel()==4) {
			lectureList = lectureMapper.selectLectureByStudent(loginMember.getMemberId());
			log.debug(A.E+"[HomeService.getLectureByMemberId.selectLectureByStudent] 학생 lectureList :" +lectureList+A.R);
		//2.강사의 경우	
		}else if(loginMember.getMemberLevel()==5) {
			lectureList = lectureMapper.selectLectureByTeacher(loginMember.getMemberId());
			log.debug(A.E+"[HomeService.getLectureByMemberId.selectLectureByStudent] 강사 lectureList :" +lectureList+A.R);
		}
		return lectureList;
	}
		
		//선택한 강의정보 불러오는 서비스
		public Lecture getLectureByLectureNo(int lectureNo) {
			log.debug(A.E+"[HomeService.getLectureByLectureNo.param] lectureNo :" +lectureNo+A.R);
			//loginMember 안에 level로 학생과 강사를 구분 후 List에 lectureList 저장
				Lecture lecture = lectureMapper.selectLecturebyLectureNo(lectureNo);
				log.debug(A.E+"[HomeService.getLectureByLectureNo.selectLecturebyLectureNo] lecture :" +lecture+A.R);
			return lecture;
	}

}
