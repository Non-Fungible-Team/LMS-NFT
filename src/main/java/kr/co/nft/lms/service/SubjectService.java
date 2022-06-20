package kr.co.nft.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.nft.lms.mapper.SubjectMapper;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Subject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class SubjectService {

	@Autowired
	private SubjectMapper subjectMapper;
	
	// subject 테이블 전체 리스트
	public List<Subject> getSubjectList() {
		
		// subjectMapper에서 반환된 값(전체 리스트)을 가공 controller에 반환
		List<Subject> subjectList = subjectMapper.selectSubjectList();
		log.debug(A.A + "[SubjectService.selectSubjectList.param] subjectList : " + subjectList + A.R);
				
		return subjectList;
	}
	
	// subject 테이블 데이터 입력
	public int addSubjectOne(Subject subject) {
		
		// controller에서 넘어온 매개변수 값 디버깅
		log.debug(A.A + "[SubjectService.addSubjectOne.param] subject : " + subject + A.R);
		
		int row = subjectMapper.insertSubjectOne(subject);
		
		return row;
	}
	
	// subject 테이블 데이터 삭제
	public void deleteSubjectOne(int subjectNo) {
		
		// controller에서 넘어온 매개변수 값 디버깅
		log.debug(A.A + "[SubjectService.deleteSubjectOne.param] subjectNo : " + subjectNo + A.R);
		
		int row = subjectMapper.deleteSubjectOne(subjectNo);
	}
}
