package kr.co.nft.lms.dto;

import lombok.Data;

@Data
public class AddTeacher {
	private String memberId;
	private String memberPw;
	private String teacherName;
	private String teacherBirth;
	private String teacherGender;
	private String teacherEmailId;
	private String teacherEntryDate;
	private String teacherSubject;
	private String memberPhoneNo;
	private int memberLevel;
	private String roadAddr;
	private String province;
	private String city;
	private String town;
	private String zipCode;
	private String addrDetail;
	
}
