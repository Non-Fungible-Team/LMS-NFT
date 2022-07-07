package kr.co.nft.lms.dto;

import lombok.Data;

@Data
public class AddManager {
	private String memberId;
	private String memberPw;
	private String menagerName;
	private String managerEntryDate;
	private String managerName;
	private String managerBirth;
	private String managerGender;
	private String managerEmail;
	private String roadAddr;
	private String province;
	private String city;
	private String town;
	private String zipCode;
	private String addrDetail;
	private String memberPhoneNo;
	private int memberLevel;
}
