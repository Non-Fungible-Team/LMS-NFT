package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class TeacherLecture {
	private String memberId;
	private int lectureNo;
	private String teacherName;
	private String lectureName;
}
