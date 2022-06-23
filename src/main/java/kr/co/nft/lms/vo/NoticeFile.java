package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class NoticeFile {
	private int noticeFileNo;
	private int noticeNo;
	private String noticeFileName;
	private String noticeFileOriginName;
	private String noticeFileType;
	private int noticeFileSize;
}
