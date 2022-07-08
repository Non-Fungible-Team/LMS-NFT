package kr.co.nft.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.nft.lms.mapper.BlindMapper;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.LectureBoard;
import kr.co.nft.lms.vo.Notice;
import kr.co.nft.lms.vo.Suggest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class BlindService {
	@Autowired BlindMapper blindMapper;
	
	//Blind 목록보기
	public Map<String, Object> getBlindListByPage(int noticeBlindCurrentPage, int noticeBlindRowPerPage
														,int suggestBlindCurrentPage, int suggestBlindRowPerPage
														,int lectureBoardBlindCurrentPage, int lectureBoardBlindRowPerPage){
		log.debug(A.S + "[BlindService.getBlindListByPage.param] noticeBlindCurrentPage : "+ noticeBlindCurrentPage + A.R); 
		log.debug(A.S + "[BlindService.getBlindListByPage.param] noticeBlindRowPerPage : "+ noticeBlindRowPerPage + A.R); 
		log.debug(A.S + "[BlindService.getBlindListByPage.param] suggestBlindCurrentPage : "+ suggestBlindCurrentPage + A.R);
		log.debug(A.S + "[BlindService.getBlindListByPage.param] suggestBlindRowPerPage : "+ suggestBlindRowPerPage + A.R);
		log.debug(A.S + "[BlindService.getBlindListByPage.param] lectureBoardBlindCurrentPage : "+ lectureBoardBlindCurrentPage + A.R);
		log.debug(A.S + "[BlindService.getBlindListByPage.param] lectureBoardBlindRowPerPage : "+ lectureBoardBlindRowPerPage + A.R);
		
		//1)controller에서 넘어온 매개변수값을 가공 후 매퍼 호출
		int noticeBlindBeginRow = (noticeBlindCurrentPage - 1) * noticeBlindRowPerPage;
		log.debug(A.S + "[BlindService.getBlindListByPage] noticeBlindBeginRow : "+ noticeBlindBeginRow + A.R);
		int suggestBlindBeginRow = (suggestBlindCurrentPage - 1) * suggestBlindRowPerPage;
		log.debug(A.S + "[BlindService.getBlindListByPage] suggestBlindBeginRow : "+ suggestBlindBeginRow + A.R);
		int lectureBoardBlindBeginRow = (lectureBoardBlindCurrentPage -1) * lectureBoardBlindRowPerPage;
		log.debug(A.S + "[BlindService.getBlindListByPage] lectureBoardBlindBeginRow : "+ lectureBoardBlindBeginRow + A.R);
		
		Map<String, Object> blindRowMap = new HashMap<>();
		blindRowMap.put("noticeBlindBeginRow", noticeBlindBeginRow);
		blindRowMap.put("noticeBlindRowPerPage", noticeBlindRowPerPage);
		blindRowMap.put("suggestBlindBeginRow", suggestBlindBeginRow);
		blindRowMap.put("suggestBlindRowPerPage", suggestBlindRowPerPage);
		blindRowMap.put("lectureBoardBlindBeginRow", lectureBoardBlindBeginRow);
		blindRowMap.put("lectureBoardBlindRowPerPage", lectureBoardBlindRowPerPage);
		log.debug(A.S + "[BlindService.getBlindListByPage] blindRowMap : "+ blindRowMap + A.R);
		
		List<Notice> noticeBlindList = blindMapper.selectNoticeBlindListByPage(blindRowMap);
		List<Suggest>suggestBlindList = blindMapper.selectSuggestBlindListByPage(blindRowMap);
		List<LectureBoard>lectureBoardBlindList = blindMapper.selectLectureBoardBlindListByPage(blindRowMap);
		log.debug(A.S + "[BlindService.getBlindListByPage] noticeBlindList : "+ noticeBlindList + A.R);
		log.debug(A.S + "[BlindService.getBlindListByPage] suggestBlindList : "+ suggestBlindList + A.R);
		log.debug(A.S + "[BlindService.getBlindListByPage] lectureBoardBlindList : "+ lectureBoardBlindList + A.R);
		
		//2) 매퍼에서 반환된 값을 가공, controller에 반환
		int noticeBlindTotalCount = blindMapper.selectNoticeBlindListTotalCount();
		int suggestBlindTotalCount = blindMapper.selectSuggestBlindListTotalCount();
		int lectureBoardBlindTotalCount = blindMapper.selectLectureBoardBlindListTotalCount();
		log.debug(A.S + "[BlindService.getBlindListByPage] noticeBlindTotalCount : "+ noticeBlindTotalCount + A.R);
		log.debug(A.S + "[BlindService.getBlindListByPage] suggestBlindTotalCount : "+ suggestBlindTotalCount + A.R);
		log.debug(A.S + "[BlindService.getBlindListByPage] lectureBoardBlindTotalCount : "+ lectureBoardBlindTotalCount + A.R);
		
		int noticeBlindLastPage = (int)Math.ceil((double)noticeBlindTotalCount / (double)noticeBlindRowPerPage);
		int suggestBlindLastPage = (int)Math.ceil((double)suggestBlindTotalCount / (double)suggestBlindRowPerPage);
		int lectureBoardBlindLastPage = (int)Math.ceil((double)lectureBoardBlindTotalCount / (double)lectureBoardBlindRowPerPage);
		log.debug(A.S + "[BlindService.getBlindListByPage] noticeBlindLastPage : "+ noticeBlindLastPage + A.R);
		log.debug(A.S + "[BlindService.getBlindListByPage] suggestBlindLastPage : "+ suggestBlindLastPage + A.R);
		log.debug(A.S + "[BlindService.getBlindListByPage] lectureBoardBlindLastPage : "+ lectureBoardBlindLastPage + A.R);
		
		Map<String, Object> blindReturnMap = new HashMap<>();
		blindReturnMap.put("noticeBlindList", noticeBlindList);
		blindReturnMap.put("noticeBlindLastPage", noticeBlindLastPage);
		blindReturnMap.put("suggestBlindList", suggestBlindList);
		blindReturnMap.put("suggestBlindLastPage", suggestBlindLastPage);
		blindReturnMap.put("lectureBoardBlindList", lectureBoardBlindList);
		blindReturnMap.put("lectureBoardBlindLastPage", lectureBoardBlindLastPage);
		log.debug(A.S + "[BlindService.getBlindListByPage] blindReturnMap : "+ blindReturnMap + A.R);
		
		return blindReturnMap;
	}

}
