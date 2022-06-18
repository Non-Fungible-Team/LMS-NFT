package kr.co.nft.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.nft.lms.vo.LectureRoom;

@Mapper
public interface LectureRoomMapper {
	//1.강의실 목록, List 값 전달 받음, map형 반환
	List<LectureRoom> selectLectureRoom(Map<String, Object> map);
	//2.강의실 삽입, vo 값 전달 받음, int형 반환
	int insertLectureRoom(LectureRoom lectureRoom);
	//3.강의실 수정
	//form :lectureRoomName 값 전달받음, vo객체로 반환
	LectureRoom updateLectureRoomForm(String lectureRoomName);
	//action : 강의실이름 전달받음, int형 반환
	int updateLectureRoomAction(LectureRoom lectureRoom);
	
	//4.강의실 삭제
	//action : 강의실명 값 전달 받음, int 반환
	int deleteLectureRoom(String lectureRoomName);
}
