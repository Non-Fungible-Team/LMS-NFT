package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class Board {
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardCategory;
	private String memberId;
	private int boardPrivilege;
	private String boardBlind;
	private String boardCreateDate;
	private String boardUpdateDate;
	
}
