package kr.co.nft.lms.vo;

import java.util.List;

import lombok.Data;

@Data
public class SurveyQuestion {
	private int surveyQuestionNo;
	private int surveyNo;
	private int surveyQuestionListNo;
	private String surveyQuestionContent;
	private String surveyQuestionType;
	private String surveyQuestionCreateDate;
	private String surveyQuestionUpdateDate;
	
	private List<SurveyQuestion> surveyQuestionList;
}
