package kr.co.nft.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.nft.lms.mapper.BoardMapper;
import kr.co.nft.lms.vo.Board;

@Service
@Transactional
public class BoardService {
	@Autowired //스프링에 의해 객체 주입되도록
	BoardMapper boardMapper;
	
	//Board 입력 액션
	public int addBoard(Board board) {
		return boardMapper.insertBoard(board);
	}
	
	//Notice 목록 보기
	public Map<String, Object> getBoardByPage(int currentPage, int rowPerPage){
		//1) controller 에서 넘어온 매개변수값을 가공 후 매퍼 호출
		int startRow = (currentPage -1) * rowPerPage;
		Map<String, Object> map = new HashMap<>();
		map.put("rowPerPage", rowPerPage);
		map.put("startRow", startRow);
		List<Board> boardList = boardMapper.selectBoardByPage(map);
		
		//2) 매퍼에서 반환된 값을 가공, controller에 반환
		int totalCount = boardMapper.selectTotalCount();
		int lastPage = (int)Math.ceil((double)totalCount/(double)rowPerPage);
		
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("boardList", boardList);
		returnMap.put("lastPage", lastPage);
		return returnMap;
	}
	
	//Notice 상세보기 + 삭제폼
	public Board getBoardOne(int boardNo){
		return boardMapper.selectBoardOne(boardNo);
	}
	
	//Notice 수정액션
	public int modifyBoard(Board board) {
		return boardMapper.updateBoard(board);
	}
	
	//Notice 삭제(블라인드로 변경)
	public int removeBoard(int boardNo) {
		return boardMapper.deleteBoard(boardNo);
	}
	
	
}
