package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class Comment {
	private int commentNo;
	private int lectureBoardNo;
	private String commentContent;
	private String commentBlind;
	private String memberId;
	private String commentCreateDate;
	private String commentUpdateDate;
}
