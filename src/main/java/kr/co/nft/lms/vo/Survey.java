package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class Survey {
	private int surveyNo;
	private String surveyTitle;
	private String surveyContent;
	private String surveyCreateDate;
	private String surveyUpdateDate;
	private String surveyStartlineDate;
	private String surveyDeadlineDate;
	private int lectureNo;
	private String memberId;
}
