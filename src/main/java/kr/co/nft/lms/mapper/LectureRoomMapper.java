package kr.co.nft.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.nft.lms.vo.LectureRoom;

@Mapper
public interface LectureRoomMapper {
	
	// lecture_room 테이블 전체 리스트
	List<LectureRoom> selectLectureRoomList();
	
	// lecture_room 테이블 상세보기
	LectureRoom selectLectureRoomOne(String lectureRoomName);

	// lecture_room 테이블 입력
	int insertLectureRoom(LectureRoom lectureRoom);

	// lecture_room 테이블 수정
	int updateLectureRoom(LectureRoom lectureRoom);
	
	// lecture_room 테이블 삭제
	int deleteLectureRoom(String lectureRoomName);
}
