package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class ReportBoard {
	private int ReportboardNo;
	private int boardNo;
	private String memberId;
	private String reportBoardTitle;
	private String reportBoardContent;
	private String reportBoardCategory;
	private String reportBoardStatus;
	private String reportBoardCreateDate;
	private String reportBoardUpdateDate;
}
