package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class LectureRoom {
	private int lectureNo;
	private int subjectNo;
	private String lectureName;
	private String lectureStartDate;
	private String lectureEndDate;
	private String lectureCreateDate;
	private String lectureUpdateDate;
	private String lectureRoomName;
}
