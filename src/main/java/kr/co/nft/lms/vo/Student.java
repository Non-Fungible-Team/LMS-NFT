package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class Student {
	private String memberId;
	private String studentEducation;
	private String studentName;
	private String studentBirth;
	private String studentGender;
	private String studentEmail;
	private String addrDetail;
	
	//MemberTable부분
	private String memberPw;
	private String memberPwUpdateDate;
	private String memberPhoneNo;
	private int memberLevel;
	private String memberLastLoginDate;
	private String memberCreateDate;
	private String memberUpdateDate;
	
	//테이블에 없는 vo 
	
	private String roadAddr;
	private String addressDetail;
}
