package kr.co.nft.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.nft.lms.mapper.HomeworkMapper;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Homework;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class HomeworkService {
	@Autowired private HomeworkMapper homeworkMapper;
	
	// 과제 목록
	public Map<String, Object> getHomeworkListByPage(int currentPage, int rowPerPage){
		
		int beginRow = (currentPage-1)*rowPerPage;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginRow", beginRow);
		map.put("rowPerPage", rowPerPage);
		
		List<Homework> homeworkList = homeworkMapper.selectHomeworkListByPage(map);
		log.debug(A.Q + "HomeworkService.getHomeworkListByPage :" + map.toString() + A.R);
		
		int totalCount = homeworkMapper.selectTotalCount();
		int lastPage = (int)Math.ceil((double)totalCount / (double)rowPerPage);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("homeworkList", homeworkList);
		returnMap.put("lastPage", lastPage);
		
		
		return returnMap;
	}
	
	// 과제 상세보기
	public Map<String, Object> getHomeworkOne(int homeworkNo){
		Homework homeworkOne = homeworkMapper.selectHomeworkOne(homeworkNo);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("homeworkOne", homeworkOne);
		
		return returnMap;
	}
	
	// 과제 입력
	public int addHomework(Homework homework) {
		return homeworkMapper.insertHomework(homework);
	}
	
	// 과제 수정
	public int modifyHomework(Homework homework) {
		
		return homeworkMapper.updateHomework(homework);
	}
	
	// 과제 삭제
	public int removeHomework(int homeworkNo) {
		return homeworkMapper.deleteHomework(homeworkNo);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
