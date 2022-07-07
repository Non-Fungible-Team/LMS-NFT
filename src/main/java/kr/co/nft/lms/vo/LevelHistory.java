package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class LevelHistory {
	private String memberId;
	private int levelHistoryNewLevel;
	private String levelHistoryReason;
	private String editor;
	private String levelHistoryUpdateDate;
	
}
