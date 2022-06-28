package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class SurveyMultipleAnswer {
	private int surveyQuestionNo;
	private int surveyNo;
	private String MemberId;
	private String SurveyAnswerType;
	private int surveyMultipleAnswerContent;
	private String surveyMultipleAnswerCreateDate;
	private String surveyMultipleAnswerUpdateDate;
}
