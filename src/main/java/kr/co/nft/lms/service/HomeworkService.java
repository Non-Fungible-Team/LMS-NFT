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
		log.debug(A.Q +"HomeworkService.getHomeworkListByPage.param.currentPage" + currentPage +A.R);
		log.debug(A.Q +"HomeworkService.getHomeworkListByPage.param.rowPerPage" + rowPerPage +A.R);
		
		int beginRow = (currentPage-1)*rowPerPage;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginRow", beginRow);
		map.put("rowPerPage", rowPerPage);
		log.debug(A.Q + "HomeworkService.getHomeworkListByPage.map :" + map + A.R);
		
		List<Homework> homeworkList = homeworkMapper.selectHomeworkListByPage(map);
		log.debug(A.Q + "HomeworkService.getHomeworkListByPage.homeworkList :" + homeworkList + A.R);
		
		int totalCount = homeworkMapper.selectTotalCount();
		int lastPage = (int)Math.ceil((double)totalCount / (double)rowPerPage);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("homeworkList", homeworkList);
		returnMap.put("lastPage", lastPage);
		log.debug(A.Q + "HomeworkService.getHomeworkListByPage.returnMap :" + returnMap + A.R);
		
		return returnMap;
	}
	
	// 과제 상세보기
	public Map<String, Object> getHomeworkOne(int homeworkNo){
		log.debug(A.Q +"HomeworkService.getHomeworkOne.param.homeworkNo : " + homeworkNo +A.R);
		Homework homeworkOne = homeworkMapper.selectHomeworkOne(homeworkNo);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("homeworkOne", homeworkOne);
		log.debug(A.Q +"HomeworkService.getHomeworkOne.returnMap : " + returnMap +A.R);
		
		return returnMap;
	}
	
	// 과제 입력
	public int addHomework(Homework homework) {
		log.debug(A.Q+"HomeworkService.addHomework.param.homework : "+ homework +A.R);
		return homeworkMapper.insertHomework(homework);
	}
	
	// 과제 수정 
	public int modifyHomework(Homework homework) {
		log.debug(A.Q+"HomeworkService.modifyHomework.param.homework : "+ homework +A.R);
		return homeworkMapper.updateHomework(homework);
	}
	
	// 과제 삭제
	public int removeHomework(int homeworkNo) {
		log.debug(A.Q+"HomeworkService.removeHomework.param.homeworkNo : "+ homeworkNo +A.R);
		return homeworkMapper.deleteHomework(homeworkNo);
		
	}
	
	
	
	
	
	
}
