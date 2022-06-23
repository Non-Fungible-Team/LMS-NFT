package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class StudentLecture {
	private int lectureNo;
	private String memberId;
	private String studentLectureJob;
	private String studentLectureLegistrationDate;
	private String studentLectureEndDate;
	private int studentLectureScore;
	
	private String studentName;
}
