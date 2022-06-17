package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class StudentLecture {
	private int lectureNo;
	private String memberId;
	private String memberLectureJob;
	private String memberLectureLegistrationDate;
	private String memberLectureEndDate;
	private int memberLectureScore;
}
