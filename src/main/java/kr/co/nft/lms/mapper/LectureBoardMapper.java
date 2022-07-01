package kr.co.nft.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.nft.lms.vo.Comment;
import kr.co.nft.lms.vo.LectureBoard;
import kr.co.nft.lms.vo.LectureFile;

@Mapper
public interface LectureBoardMapper {
	//board테이블에 해당하는 내용을 insert
	int insertLectureBoardInBoard(LectureBoard lectureBoard);
	
	//lectureBoard테이블에 해당하는 내용을 insert
	int insertLectureBoard(LectureBoard lectureBoard);
	
	//전체 select
	List<LectureBoard> selectLectureBoardListByPage(Map<String, Object> map);
	
	//전체 row
	int selectLectureBoardListTotalCount(int lectureNo);
	
	//상세보기
	LectureBoard selectLectureBoardOne(int lectureBoardNo);
	
	//update
	int updateLectureBoardInBoard(LectureBoard lectureBoard);
	
	/*
	//lectureNo update
	int updateLectureBoardInLectureNo(LectureBoard lectureBoard);
	*/
	
	//delete(블라인드처리)
	int deleteLectureBoard(int lectureBoardNo);
	
	//■■■■■■■■■■■■■■■■LectureFile■■■■■■■■■■■■■■■■
	
	//LectureFile insert
	int insertlectureFile(LectureFile lectureFile);
	
	//LectureFile 상세보기 + 목록
	List<LectureFile> selectLectureFileOneList(int lectureFileNo);
	
	//LectureFile delete
	int deleteLectureFileList(int lectureBoardNo);
	int deleteLectureFileOne(int lectureFileNo);
	List<String> selectLectureFileNameList(int lectureFileNo);
	
	
	//■■■■■■■■■■■■■■■■comment■■■■■■■■■■■■■■■■
	
	//comment insert
	int insertComment(Comment comment);
	
	//댓글목록
	List<Comment> selectCommentListByPage(Map<String, Object> map);
	int selectCommentTotalCount(int boardNo);
	
	//Comment delete
	int deleteCommentList(int boardNo);
	int deleteCommentOne(int commentNo);
	
}
