package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class ExamQuestion {
	private int examQuestionNo;
	private int examNo;
	private String examContent;
	private String examCorrectAnswer;
	private int examPoint;
	private String examType;
	private String examQuestionCreateDate;
	private String examQuestionUpdateDate;
}
