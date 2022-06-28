package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class Member {
	private String memberId;
	private String memberPw;
	private String memberPwUpdateDate;
	private String memberPhoneNo;
	private int memberLevel;
	private String memberLastLoginDate;
	private String memberCreateDate;
	private String memberUpdateDate;
	
	//테이블에 없는 vo
	private String roadAddr;
	private String addrDetail;
	private String photoName;
}
