package kr.co.nft.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.nft.lms.mapper.LectureRoomMapper;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.LectureRoom;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class LectureRoomService {
	
	@Autowired
	private LectureRoomMapper lectureRoomMapper;
	
	// lecture_room 테이블 전체 리스트
	public List<LectureRoom> getLectureRoomList() {
		
		List<LectureRoom> lectureRoomList = lectureRoomMapper.selectLectureRoomList();
		
		log.debug(A.A + "[LectureRoomService.getLectureRoomList] lectureRoomList : " + lectureRoomList + A.R);
		
		return lectureRoomList;
	}
	
	// lecture_room 테이블 입력
	public int addLectureRoom(LectureRoom lectureRoom) {
		
		log.debug(A.A + "[LectureRoomService.addLectureRoom] lectureRoom : " + lectureRoom + A.R);
		
		int row = lectureRoomMapper.insertLectureRoom(lectureRoom);
		
		log.debug(A.A + "[LectureRoomService.addLectureRoom] row : " + row + A.R);
		
		return row;
	}
	
	// lecture_room 테이블 삭제
	public int removeLectureRoom(String lectureRoomName) {
		
		log.debug(A.A + "[LectureRoomService.removeLectureRoom] lectureRoomName : " + lectureRoomName + A.R);
		
		int row = lectureRoomMapper.deleteLectureRoom(lectureRoomName);
		
		log.debug(A.A + "[LectureRoomService.removeLectureRoom] row : " + row + A.R);
		
		return row;
	}
}
