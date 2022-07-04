package kr.co.nft.lms.vo;

import java.util.List;

import lombok.Data;

@Data
public class SurveyMultipleAnswer {
	private int surveyMultipleAnswerNo;
	private int surveyQuestionNo;
	private int surveyNo;
	private String memberId;
	private String surveyAnswerType;
	private int surveyMultipleAnswerContent;
	private String surveyMultipleAnswerCreateDate;
	private String surveyMultipleAnswerUpdateDate;
	
	private List<SurveyMultipleAnswer> SurveyMultipleAnswerList;
}
