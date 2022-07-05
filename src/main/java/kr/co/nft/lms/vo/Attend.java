package kr.co.nft.lms.vo;

import java.util.List;

import lombok.Data;

@Data
public class Attend {
	private String memberId;
	private int lectureNo;
	private String attendDate;
	private String attendStatus;
	private String attendReason;
	private String attendCreateDate;
	private String attendUpdateDate;
	
	private String lectureName;
	private String StudentName;
	//jsp에서 Attend 여러 값을 받기위해
	private List<Attend> attendList;
}
