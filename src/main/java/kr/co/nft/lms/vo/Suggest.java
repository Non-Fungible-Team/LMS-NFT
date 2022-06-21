package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class Suggest {
	private int suggestNo;
	private int root; //답변할 게시판 번호
	private String suggestSecret;
	private String suggestStatus;
	
	private String suggestTitle;
	private String suggestContent;
	private String suggestCategory;
	private String memberId;
	private String suggestPrivilege;
	private String suggestBlind;
	private String suggestCreateDate;
	private String suggestUpdateDate;
}
