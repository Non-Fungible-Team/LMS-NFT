package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class SurveyShortAnswer {
	private int surveyShortAnswerNo;
	private int surveyQuestionNo;
	private int surveyNo;
	private String memberId;
	private String surveyAnswerType;
	private String surveyShortAnswerContent;
	private String surveyShortAnswerCreateDate;
	private String surveyShortAnswerUpdateDate;
	
}
