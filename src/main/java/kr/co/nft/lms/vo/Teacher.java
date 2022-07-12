package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class Teacher {
	//Teacher 테이블
	private String memberId;
	private String teacherEntryDate;
	private String teacherName;
	private String teacherBirth;
	private String teacherGender;
	private String teacherEmail;
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
