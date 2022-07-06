package kr.co.nft.lms.vo;

import java.util.List;

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
	private int examQuestionListExamCount;
	private int examQuestionListExamMaxScore;
	private int examQuestionListExamQuestionNo;
	private String examQuestionListExamContent;
	private String examQuestionListExamCorrectAnswer;
	private int examQuestionListExamPoint;
	private String examQuestionListExamType;
	private int examQuestionListExampleNo;
	private String examQuestionListExampleContent;
	private List<ExamQuestion> examQuestionList;
	private int exampleNo;
	private String exampleContent;
	private String exampleOneContent;
	private String exampleTwoContent;
	private String exampleThreeContent;
	private String exampleFourContent;
	private String exampleCreateDate;
	private String exampleUpdateDate;
}
