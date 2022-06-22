package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class Exam {
	private int examNo;
	private String memberId;
	private String lectureNo;
	private String examTitle;
	private int examCount;
	private int examMaxScore;
	private String examStartDate;
	private String examEndDate;
	private String examCreateDate;
	private String examUpdateDate;
	private int examQuestionNo;
	private String examContent;
	private String examCorrectAnswer;
	private int examPoint;
	private String examType;
	private int exampleNo;
	private String exampleContent;
	private String exampleCreateDate;
	private String exampleUpdateDate;
}
