package kr.co.nft.lms.vo;

import java.util.List;

import lombok.Data;

@Data
public class SurveyAnswer {
	private int surveyNo;
	private String memberId;
	private String surveyAnswerType;
	private int surveyQuestionNo;
	private int surveyMultipleAnswerContent;
	private String surveyShortAnswerContent;
	
	private List<SurveyAnswer> surveyAnswer;
	
	

}
