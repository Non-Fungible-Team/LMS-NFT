package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class LectureBoard {
	private int lectureNo;
	private String lectureName;
	//private int boardNo;
	private int lectureBoardNo;
	
	
	private String lectureBoardTitle;
	private String lectureBoardContent;
	private String lectureBoardCategory;
	private String memberId;
	private String lectureBoardPrivilege;
	private String lectureBoardBlind;
	private String lectureBoardCreateDate;
	private String lectureBoardUpdateDate;
}
