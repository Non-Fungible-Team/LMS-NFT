package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class Student {
	private String memberId;
	private String studentEducation;
	private String memberName;
	private String memberBirth;
	private String memberGender;
	private String memberEmail;
	private int photoNo;
	private String addressDetail;
}
