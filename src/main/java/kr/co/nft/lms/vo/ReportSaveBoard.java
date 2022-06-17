package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class ReportSaveBoard {
	private int reportSaveBoardNo;
	private int boardNo;
	private int reportedBoardNo;
	private String memberId;
	private String reportSaveBoardCreateDate;
	private String reportSaveBoardUpdateDate;
}
