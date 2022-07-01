package kr.co.nft.lms.vo;

import java.util.List;

import lombok.Data;

@Data
public class Exam {
	private int examNo;
	private String memberId;
	private String sessionLoginMember;
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
	private String examAnswer;
	private int examScore;
	private String examAnswerStartDate;
	private String examAnswerEndDate;
	private String examAnswerApply;
	private int examQuestionListExamCount;
	private int examQuestionListExamMaxScore;
	private int examQuestionListExamQuestionNo;
	private String examQuestionListExamContent;
	private String examQuestionListExamCorrectAnswer;
	private int examQuestionListExamPoint;
	private String examQuestionListExamType;
	private int examQuestionListExampleNo;
	private String examQuestionListExampleContent;
	private List<Exam> examQuestionList;
}
