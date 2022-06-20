package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class Teacher {
	private String memberId;
	private String teacherEntryDate;
	private String teacherSubject;
	private String teacherName;
	private String teacherBirth;
	private String teacherGender;
	private String teacherEmail;
	private int photoNo;
	private String addressDetail;
}
