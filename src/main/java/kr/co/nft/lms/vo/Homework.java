package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class Homework {
	private int homeworkNo;
	private String memberId;
	private int lectureNo;
	private String homeworkTitle;
	private String homeworkContent;
	private String homeworkStartDate;
	private String homeworkEndDate;
	private String homeworkCreateDate;
	private String homeworkUpdateDate;
}
