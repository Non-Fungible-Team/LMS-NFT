package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class ReportComment {
	private int reportCommentNo;
	private int commentNo;
	private String memberId;
	private String reportCommentTitle;
	private String reportCommentContent;
	private String reportCommentCategory;
	private String reportCommentStatus;
	private String reportCommentCreateDate;
	private String reportCommentUpdateDate;
}
