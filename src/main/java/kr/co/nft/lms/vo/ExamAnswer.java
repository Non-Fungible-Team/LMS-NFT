package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class ExamAnswer {
	private int examQuestionNo;
	private String memberId;
	private String examAnswer;
	private int examScore;
	private String examAnswerStartDate;
	private String examAnswerEndDate;

}
