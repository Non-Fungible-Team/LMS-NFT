package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class Attend {
	private String memberId;
	private int lectureNo;
	private String attendDate;
	private String attendStatus;
	private String attendReason;
	private String attendCreateDate;
	private String attendUpdateDate;
}
