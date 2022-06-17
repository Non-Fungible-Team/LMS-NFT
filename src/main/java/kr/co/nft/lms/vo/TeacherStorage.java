package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class TeacherStorage {
	private int storagefileNo;
	private int lectureNo;
	private String storagefileName;
	private String storagefileType;
	private int storagefileSize;
	private String storagefileCreateDate;
	private String storagefileCategory;
}
