package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class HomeworkSubmit {
	private int homeworkSubmitNo;
	private int homeworkNo;
	private String memeberId;
	private String homeworkSubmitTitle;
	private String homeworkSubmitContent;
	private int homeworkSubmitScore;
	private String homeworkSubmitFeedback;
	private String homeworkSubmitCreateDate;
	private String homeworkSubmitUpdateDate;

}
