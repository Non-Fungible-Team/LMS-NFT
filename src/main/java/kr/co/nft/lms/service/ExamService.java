package kr.co.nft.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.nft.lms.mapper.ExamMapper;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.*;
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
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		log.debug(A.C + "[ExamService.getExamOne.Mapper] returnMap : " + returnMap + A.R);
		returnMap.put("examQuestionOneList",examQuestionOneList);
		return returnMap;
	}
	// 시험보기 상세보기
	public Map<String, Object> getExamExampleOne(int examNo) {
			log.debug(A.C + "[ExamService.getExamExampleOne.param] examNo: " + examNo + A.R);
			
			List<Exam> examExampleOneList = examMapper.selectExamExampleOne(examNo);
			
			Map<String, Object> returnMap = new HashMap<String, Object>();
			log.debug(A.C + "[ExamService.getExamOne.Mapper] returnMap : " + returnMap + A.R);
			returnMap.put("examExampleOneList",examExampleOneList);
			return returnMap;
	}
	
	// 시험문제 등록
	public void addExam(Exam exam) {
			log.debug(A.C + "[ExamService.addExam.param] exam :"+ exam + A.R);
			
			// ExamMapper
			exam.setExamTitle(exam.getExamTitle());
			exam.setExamCount(exam.getExamCount());
			exam.setExamMaxScore(exam.getExamMaxScore());
			exam.setExamStartDate(exam.getExamStartDate());
			exam.setExamEndDate(exam.getExamEndDate());
			// notice.getNoticeId() --> 0
			int row = examMapper.insertExam(exam);
			// insert시 입력된 autoincrement값이 출력됨
			log.debug(A.C + "[ExamService.addExam.param] examNo :"+ exam.getExamNo() + A.R);
			
			}
}
