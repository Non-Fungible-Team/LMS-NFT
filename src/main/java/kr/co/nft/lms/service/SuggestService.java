package kr.co.nft.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.nft.lms.mapper.SuggestMapper;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Suggest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class SuggestService {
	@Autowired SuggestMapper suggestMapper;
	
	//Suggest 입력 액션
	public int addSuggest(Suggest suggest) {
		log.debug(A.S + "[SuggestService.addSuggest.param] suggest : "+ suggest + A.R); 
		int row1 = suggestMapper.insertSuggestInBoard(suggest);
		log.debug(A.S + "[SuggestService.addSuggest.boardNo] row1 : "+ row1 + A.R); 
		int row = suggestMapper.insertSuggest(suggest);
		log.debug(A.S + "[SuggestService.addSuggest] row : "+ row + A.R); 
		if(suggest.getRoot()!=0) {
			Suggest suggest1 = new Suggest();
			suggest1.setSuggestNo(suggest.getRoot());
			suggest1.setSuggestStatus("처리완료");
			suggestMapper.updateSuggestStatus(suggest1);
			
		}
		return row;
	}
	
	//Suggest 목록보기
	public Map<String, Object> getSuggestListByPage(int currentPage, int rowPerPage, int memberLevel,int rootNullCurrentPage,int rootNullRowPerPage){
		log.debug(A.S + "[SuggestService.getSuggestListByPage.param] currentPage : "+ currentPage + A.R); 
		log.debug(A.S + "[SuggestService.getSuggestListByPage.param] rowPerPage : "+ rowPerPage + A.R); 
		log.debug(A.S + "[SuggestService.getSuggestListByPage.param] memberLevel : "+ memberLevel + A.R); 
		log.debug(A.S + "[SuggestService.getSuggestListByPage.param] rootNullCurrentPage : "+ rootNullCurrentPage + A.R); 
		log.debug(A.S + "[SuggestService.getSuggestListByPage.param] rootNullRowPerPage : "+ rootNullRowPerPage + A.R); 
		
		//1)controller에서 넘어온 매개변수값을 가공 후 매퍼 호출
		int beginRow = (currentPage - 1) * rowPerPage;
		int rootNullBeginRow = (rootNullCurrentPage - 1) * rootNullRowPerPage;
		log.debug(A.S + "[SuggestService.getSuggestListByPage] beginRow : "+ beginRow + A.R); 
		log.debug(A.S + "[SuggestService.getSuggestListByPage] rootNullBeginRow : "+ rootNullBeginRow + A.R); 
		Map<String, Object> suggestRowMap = new HashMap<>();
		suggestRowMap.put("rowPerPage", rowPerPage);
		suggestRowMap.put("beginRow", beginRow);
		suggestRowMap.put("rootNullRowPerPage", rootNullRowPerPage);
		suggestRowMap.put("rootNullBeginRow", rootNullBeginRow);
		log.debug(A.S + "[SuggestService.getSuggestListByPage] suggestRowMap : "+ suggestRowMap + A.R); 
		
		List<Suggest> suggestList = suggestMapper.selectSuggestListByPage(suggestRowMap);
		List<Suggest> rootNullSuggestList = suggestMapper.selectRootNullSuggestListByPage(suggestRowMap);
		log.debug(A.S + "[SuggestService.getSuggestListByPage] suggestList : "+ suggestList + A.R); 
		
		//2) 매퍼에서 반환된 값을 가공, controller에 반환
		int totalCount = suggestMapper.selectSuggestListTotalCount(memberLevel);
		int rootNullTotalCount = suggestMapper.selectRootNullSuggestListTotalCount(memberLevel);
		log.debug(A.S + "[SuggestService.getSuggestListByPage] totalCount : "+ totalCount + A.R);
		log.debug(A.S + "[SuggestService.getSuggestListByPage] rootNullTotalCount : "+ rootNullTotalCount + A.R);
		int lastPage = (int)Math.ceil((double)totalCount / (double)rowPerPage);
		int rootNullLastPage = (int)Math.ceil((double)rootNullTotalCount / (double)rootNullRowPerPage);
		log.debug(A.S + "[SuggestService.getSuggestListByPage] lastPage : "+ lastPage + A.R);
		log.debug(A.S + "[SuggestService.getSuggestListByPage] rootNullLastPage : "+ rootNullLastPage + A.R);
		
		Map<String, Object> suggestRowReturnMap = new HashMap<>();
		suggestRowReturnMap.put("suggestList", suggestList);
		suggestRowReturnMap.put("lastPage", lastPage);
		suggestRowReturnMap.put("rootNullSuggestList", rootNullSuggestList);
		suggestRowReturnMap.put("rootNullLastPage", rootNullLastPage);
		log.debug(A.S + "[SuggestService.getSuggestListByPage] suggestRowReturnMap : "+ suggestRowReturnMap + A.R);
			
		return suggestRowReturnMap;
	}
	
	//Suggest 상세보기 + 수정폼
	public Suggest getSuggestOne(int suggestNo) {
		log.debug(A.S + "[SuggestService.getSuggestOne.param] suggestNo : "+ suggestNo + A.R);
		Suggest suggest = suggestMapper.selectSuggestOne(suggestNo);
		log.debug(A.S + "[SuggestService.getSuggestOne] suggest : "+ suggest + A.R);
		return suggest;
	}
	
	//Suggest 수정액션
	public int modifySuggest(Suggest suggest) {
		log.debug(A.S + "[SuggestService.modifySuggest.param] suggest : "+ suggest + A.R);
		int row = suggestMapper.updateSuggestInBoard(suggest);
		log.debug(A.S + "[SuggestService.modifySuggest] row : "+ row + A.R);
		return row;		
	}
	
	//Suggest 삭제(블라인드처리)
	public int removeSuggest(int suggestNo) {
		log.debug(A.S + "[SuggestService.removeSuggest.param] suggestNo : "+ suggestNo + A.R);
		int row = suggestMapper.deleteSuggest(suggestNo);
		log.debug(A.S + "[SuggestService.removeSuggest] row : "+ row + A.R);
		return row;
	}
}
