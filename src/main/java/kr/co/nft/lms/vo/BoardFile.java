package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class BoardFile {
	private int boardFileNo;
	private int boardNo;
	private String boardFileName;
	private String boardFileOriginName;
	private String boardFileType;
	private int boardFileSize;
}
