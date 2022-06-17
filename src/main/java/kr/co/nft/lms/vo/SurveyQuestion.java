package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class SurveyQuestion {
	private int surveyQuestionNo;
	private int surveyNo;
	private int surveyQuestionListNo;
	private String surveyQuestionType;
	private String surveyQuestionCreateDate;
	private String surveyQuestionUpdateDate;
}
