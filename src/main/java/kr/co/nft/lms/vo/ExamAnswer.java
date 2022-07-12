package kr.co.nft.lms.vo;

import java.util.List;

import lombok.Data;

@Data
public class ExamAnswer {
	private int examNo;
	private int examQuestionNo;
	private String memberId;
	private String examAnswer;
	private int examScore;
	private String examAnswerStartDate;
	private String examAnswerEndDate;
	private List<ExamAnswer> exmaAnswerList;

}
