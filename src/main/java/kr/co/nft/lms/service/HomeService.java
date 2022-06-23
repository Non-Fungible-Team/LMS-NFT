package kr.co.nft.lms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
