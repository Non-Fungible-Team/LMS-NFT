package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class Notice {
	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private String noticeCategory;
	private String memberId;
	private int noticePrivilege;
	private String noticeBlind;
	private String noticeCreateDate;
	private String noticeUpdateDate;
}
