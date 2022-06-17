package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class Exam {
	private int examNo;
	private String memberId;
	private String lectureNo;
	private String examTitle;
	private String examCreateDate;
	private String examUpdateDate;
}
