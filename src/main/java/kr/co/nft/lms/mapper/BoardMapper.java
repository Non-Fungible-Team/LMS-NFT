package kr.co.nft.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.nft.lms.vo.Board;

@Mapper
public interface BoardMapper {
	//insert
	int insertBoard(Board board);
	
	//전체row
	int selectTotalCount();
	
	//select
	List<Board> selectBoardByPage(Map<String, Object> map);
	
	//상세보기
	Board selectBoardOne(int boardNo);
	
	//update
	int updateBoard(Board board);
	
	//delete
	int deleteBoard(int boardNo);
	
}
