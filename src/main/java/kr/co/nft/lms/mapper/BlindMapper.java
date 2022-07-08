package kr.co.nft.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.nft.lms.vo.LectureBoard;
import kr.co.nft.lms.vo.Notice;
import kr.co.nft.lms.vo.Suggest;

@Mapper
public interface BlindMapper {
	
	//noticeBlindList
	List<Notice> selectNoticeBlindListByPage(Map<String, Object> map);
	//행 count
	int selectNoticeBlindListTotalCount();
	
	
	//suggestBlindList
	List<Suggest> selectSuggestBlindListByPage(Map<String, Object> map);
	//행 count
	int selectSuggestBlindListTotalCount();
	
	
	//lectureBoardBlindList
	List<LectureBoard> selectLectureBoardBlindListByPage(Map<String, Object> map);
	//행 count
	int selectLectureBoardBlindListTotalCount();
	
	
}
