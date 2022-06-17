package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class Schedule {
	private int scheduleNo;
	private String scheduleTitle;
	private String scheduleContent;
	private String memberId;
	private String scheduleDate;
	private String scheduleCreateDate;
	private String scheduleUpdateDate;
}
