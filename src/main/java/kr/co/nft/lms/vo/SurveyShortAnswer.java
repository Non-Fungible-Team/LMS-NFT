package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class SurveyShortAnswer {
	private int surveyQuestionNo;
	private int surveyNo;
	private String memberId;
	private String SurveyShortAnswerContent;
	private String SurveyShortAnswerCreateDate;
	private String SurveyShortAnswerUpdateDate;
	
}
