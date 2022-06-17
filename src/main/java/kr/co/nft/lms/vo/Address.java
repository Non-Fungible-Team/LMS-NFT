package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class Address {
	private String memberId;
	private String zipCode;
	private String province;
	private String city;
	private String town;
	private String roadAddr;
	private String addr_detail;
}
