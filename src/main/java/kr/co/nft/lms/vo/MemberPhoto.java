package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class MemberPhoto {
	private String memberId;
	private String photoName;
	private String photoOriginName;
	private String photoType;
	private long photoSize;
	private String photoCreateDate;
	private String photoUpdateDate;
}
