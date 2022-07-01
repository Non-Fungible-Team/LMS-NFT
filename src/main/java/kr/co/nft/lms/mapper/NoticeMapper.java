package kr.co.nft.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.nft.lms.vo.Notice;
import kr.co.nft.lms.vo.NoticeFile;


@Mapper
public interface NoticeMapper {
	//Notice 입력 내용중 board테이블에 해당하는 내용을 board테이블에 insert
	int insertNoticeInBoard(Notice notice);
	//Notice 입력 내용중 notice테이블에 해당하는 내용을 notice 테이블에 insert
	int insertNotice(Notice notice);
	
	//Notice select
	List<Notice> selectNoticeListByPage(Map<String, Object> map);
	
	//Notice 전체row
	int selectNoticeListTotalCount(int memberLevel);
	
	//Notice 상세보기
	Notice selectNoticeOne(int noticeNo);
	
	//Notice update
	int updateNoticeInBoard(Notice notice);
	
	//Notice delete
	int deleteNotice(int noticeNo);
	
	
	//■■■■■■■■■■■■■■■■NoticeFile■■■■■■■■■■■■■■■■
	
	
	//NoticeFile insert
	int insertNoticeFile(NoticeFile noticeFile);
	
	//NoticeFile 상세보기 + 목록
	List<NoticeFile> selectNoticeFileOneList(int noticeFileNo);
	
	//NoticeFile delete
	int deleteNoticeFileList(int noticeNo);
	int deleteNoticeFileOne(int noticeFileNo);
	List<String> selectNoticeFileNameList(int noticeFileNo);
	
}
