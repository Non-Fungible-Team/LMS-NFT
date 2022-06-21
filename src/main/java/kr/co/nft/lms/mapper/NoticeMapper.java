package kr.co.nft.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.nft.lms.vo.Notice;


@Mapper
public interface NoticeMapper {
	//Notice 입력 내용중 board테이블에 해당하는 내용을 board테이블에 insert
	int insertNoticeInBoard(Notice notice);
	//Notice 입력 내용중 notice테이블에 해당하는 내용을 notice 테이블에 insert
	int insertNotice(Notice notice);
	
	//select
	List<Notice> selectNoticeListByPage(Map<String, Object> map);
	//전체row
	int selectNoticeListTotalCount();
	
	//상세보기
	Notice selectNoticeOne(int noticeNo);
	
	//update
	int updateNoticeInBoard(Notice notice);
	
	//delete
	int deleteNotice(int noticeNo);
	
}
