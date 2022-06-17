package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class Manager {
	private String memberId;
	private String memberEntryDate;
	private String memberResugnDate;
	private String memberName;
	private String memberBirth;
	private String memberGender;
	private String memberEmail;
	private int photoNo;
	private String addressDetail;
}
