package kr.co.nft.lms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.nft.lms.mapper.ExamMapper;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Exam;
import lombok.extern.slf4j.Slf4j;	

@Slf4j
@Service
@Transactional
public class ExamService {
	@Autowired private ExamMapper examMapper;
	
	// 시험리스트 페이지
	public Map<String, Object> getExamListByPage(int currentPage, int rowPerPage) {
			log.debug(A.C + "[ExamService.getExamListByPage.param] currentPage: " + currentPage + A.R);
			log.debug(A.C + "[ExamService.getExamListByPage.param] rowPerPage: " + rowPerPage+ A.R);

			// 1) 커터롤러의 입력값 가공
			int beginRow = (currentPage-1)*rowPerPage;
					
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("beginRow", beginRow);
			paramMap.put("rowPerPage", rowPerPage);
	
			// 2) 메퍼 반환값 가공
			List<Exam> examList = examMapper.selectExamListByPage(paramMap);
			log.debug(A.C + "[ExamService.getExamListByPage.mapper] examList : "+examList + A.R);
			Map<String, Object> returnMap = new HashMap<>();
			int examTotalCount = examMapper.countExam();
			log.debug(A.C + "[ExamService.getExamListByPage.ExamMapper] examTotalCount :"+examTotalCount + A.R);
			int examLastPage = examTotalCount / (int)(rowPerPage);
			if(examTotalCount % (int)(rowPerPage) != 0) {
					examLastPage += 1; // ++commentLastPage, commentLastPage++
			}
			returnMap.put("examList", examList);
			returnMap.put("examLastPage", examLastPage); 
			return returnMap;
	}
	
	// 시험 상세보기
	public Map<String, Object> getExamOne(int examNo) {
			log.debug(A.C + "[ExamService.getExamOne.param] examNo: " + examNo + A.R);
			
			List<Exam> examOneList = examMapper.selectExamOne(examNo);
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			log.debug(A.C + "[ExamService.getExamOne.Mapper] paramMap : " + paramMap + A.R);
			paramMap.put("examOneList",examOneList);
			return paramMap;
			}
	
	// 시험문제 상세보기
	public Map<String, Object> getExamQuestionOne(int examNo) {
		log.debug(A.C + "[ExamService.getExamOne.param] examNo: " + examNo + A.R);
		
		List<Exam> examQuestionOneList = examMapper.selectExamQuestionOne(examNo);
		Map<String,Object> examExampleOneList = new HashMap<>();
		for(int i=1; i <= examQuestionOneList.size() ;i = i+1 ) {
			examExampleOneList.put("a"+i, examMapper.selectExamExampleOne(examNo,i));
		}
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		log.debug(A.C + "[ExamService.getExamOne.Mapper] returnMap : " + returnMap + A.R);
		returnMap.put("examQuestionOneList",examQuestionOneList);
		returnMap.put("examExampleOneList",examExampleOneList);
		return returnMap;
	}

	/*
	 * // 시험보기 상세보기 public Map<String, Object> getExamExampleOne(int examNo) {
	 * log.debug(A.C + "[ExamService.getExamExampleOne.param] examNo: " + examNo +
	 * A.R);
	 * 
	 * 
	 * 
	 * Map<String, Object> returnMap = new HashMap<String, Object>(); log.debug(A.C
	 * + "[ExamService.getExamOne.Mapper] returnMap : " + returnMap + A.R);
	 * returnMap.put("examExampleOneList",examExampleOneList); return returnMap;
	}
	 */
	
	// 시험문제 등록
	public int addExam(Exam exam) {
			log.debug(A.C + "[ExamService.addExam.param] exam :"+ exam + A.R);
			return examMapper.insertExam(exam);
	}
	
	
	// 시험문제 삭제
	public int removeExam(int examNo) {
		log.debug(A.C + "[ExamService.removeExam.param] examNo :"+ examNo + A.R);
		//exam.setExamNo(exam.getExamNo());
		return examMapper.deleteExam(examNo);
	}
}
