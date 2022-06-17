package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class BoardAuthority {
	private int boardNo;
	private String boardCategory;
	private String memberId;
	private String boardAuthorityRead;
	private String boardAuthorityUpdate;
	private String boardAuthorityCreate;
	private String boardAuthorityDelete;
	
}
