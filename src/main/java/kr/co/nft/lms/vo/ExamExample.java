package kr.co.nft.lms.vo;

import java.util.List;

import lombok.Data;

@Data
public class ExamExample {
	private int examQuestionNo;
	private int examNo;
	private int exampleNo;
	private String exampleContent;
	private String exampleCreateDate;
	private String exampleUpdateDate;
	private int examQuestionListExamCount;
	private int examQuestionListExamMaxScore;
	private int examQuestionListExamQuestionNo;
	private String examQuestionListExamContent;
	private String examQuestionListExamCorrectAnswer;
	private int examQuestionListExamPoint;
	private String examQuestionListExamType;
	private int examQuestionListExampleNo;
	private String examQuestionListExampleContent;
	private List<ExamExample> getExamQuestionList;

}
