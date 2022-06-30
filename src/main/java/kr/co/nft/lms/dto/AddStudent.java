package kr.co.nft.lms.dto;

import lombok.Data;

@Data
public class AddStudent {
	private String memberId;
	private String memberPw;
	private String studentName;
	private String studentBirth;
	private String studentGender;
	private String studentEmail;
	private String roadAddr;
	private String province;
	private String city;
	private String town;
	private String zipCode;
	private String addrDetail;
	private String studentEducation;
	private String memberPhoneNo;
	private int memberLevel;
	
}
