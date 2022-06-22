package kr.co.nft.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.nft.lms.mapper.NoticeMapper;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Notice;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional 
public class NoticeService {
	@Autowired //스프링에 의해 객체 주입되도록 
	NoticeMapper noticeMapper;
 
	//Notice 입력 액션 
	public int addNotice(Notice notice) { 
		log.debug(A.S + "[NoticeService.addNotice.param] notice : "+ notice + A.R); 
		int boardNo = noticeMapper.insertNoticeInBoard(notice);
		log.debug(A.S + "[NoticeService.addNotice.boardNo] boardNo : "+ boardNo + A.R); 
		notice.setNoticeNo(boardNo);
		int row = noticeMapper.insertNotice(notice);
		log.debug(A.S + "[NoticeService.addNotice.param] row : "+ row + A.R); 
		return row;
	}

	//Notice 목록 보기 
	public Map<String, Object> getNoticeListByPage(int currentPage, int rowPerPage){
		log.debug(A.S + "[NoticeService.getNoticeListByPage.param] currentPage : "+ currentPage + A.R);
		log.debug(A.S + "[NoticeService.getNoticeListByPage.param] rowPerPage : "+ rowPerPage + A.R);
		
		//1) controller 에서 넘어온 매개변수값을 가공 후 매퍼 호출
		int beginRow = (currentPage -1) * rowPerPage;
		log.debug(A.S + "[NoticeService.getNoticeListByPage.beginRow] beginRow : "+ beginRow + A.R);
		Map<String, Object> map = new HashMap<>();
		map.put("rowPerPage", rowPerPage); 
		map.put("beginRow", beginRow);
		List<Notice> noticeList = noticeMapper.selectNoticeListByPage(map);
  
		//2) 매퍼에서 반환된 값을 가공, controller에 반환 
		int totalCount = noticeMapper.selectNoticeListTotalCount(); 
		log.debug(A.S + "[NoticeService.getNoticeListByPage.totalCount] totalCount : "+ totalCount + A.R);
		int lastPage = (int)Math.ceil((double)totalCount/(double)rowPerPage);
		log.debug(A.S + "[NoticeService.getNoticeListByPage.lastPage] lastPage : "+ lastPage + A.R);
  
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("noticeList", noticeList);
		returnMap.put("lastPage", lastPage);
		return returnMap;
	}
  
	//Notice 상세보기 + 수정폼 
	public Notice getNoticeOne(int noticeNo){
		log.debug(A.S + "[NoticeService.getNoticeOne.param] noticeNo : "+ noticeNo + A.R); 
		Notice notice = noticeMapper.selectNoticeOne(noticeNo);
		log.debug(A.S + "[NoticeService.getNoticeOne.notice] notice : "+ notice + A.R); 
		return notice;
	}
	  
	//Notice 수정액션 
	public int modifyNotice(Notice notice) {
		log.debug(A.S + "[NoticeService.modifyNotice.param] notice : "+ notice + A.R); 
		int row = noticeMapper.updateNoticeInBoard(notice); 
		log.debug(A.S + "[NoticeService.modifyNotice.row] row : "+ row + A.R); 
		return row; 
	}
	  
	//Notice 삭제(블라인드로 변경) 
	public int removeNotice(int boardNo) { 
		log.debug(A.S + "[NoticeService.removeNotice.param] boardNo : "+ boardNo + A.R); 
		int row = noticeMapper.deleteNotice(boardNo);
		log.debug(A.S + "[NoticeService.removeNotice.row] row : "+ row + A.R); 
		return row;
	}
}
 