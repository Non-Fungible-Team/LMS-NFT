package kr.co.nft.lms.service;

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
			log.debug("[ExamService.getExamListByPage.mapper] examList :"+examList);
			Map<String, Object> returnMap = new HashMap<>();
			int examTotalCount = examMapper.countExam(); 
			log.debug("[ExamService.getExamListByPage.ExamMapper] examTotalCount :"+examTotalCount);
			int examLastPage = examTotalCount / (int)(rowPerPage);
			if(examTotalCount % (int)(rowPerPage) != 0) {
					examLastPage += 1; // ++commentLastPage, commentLastPage++
			}
			returnMap.put("examList", examList);
			returnMap.put("examLastPage", examLastPage); 
			return returnMap;
	}
}
