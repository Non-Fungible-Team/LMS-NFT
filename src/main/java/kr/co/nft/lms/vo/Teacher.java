package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class Teacher {
	private String memberId;
	private String memberEntryDate;
	private String memberResignDate;
	private String teacherSubject;
	private String memberName;
	private String memberBirth;
	private String memberGender;
	private String memberEmail;
	private int photoNo;
	private String addressDetail;
}
