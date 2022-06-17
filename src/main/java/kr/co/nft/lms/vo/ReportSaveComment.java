package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class ReportSaveComment {
	private int reportSaveCommentNo;
	private int commentNo;
	private int reportCommentNo;
	private String memberID;
	private String reportSaveCommentCreateDate;
	private String reportSaveCommentUpdateDate;
}
