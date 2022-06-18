package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class LectureFile {
	private int lectureFileNo;
	private int lectureNo;
	private String lectureFileName;
	private String lectureFileType;
	private int lectureFileSize;
	private String lectureFileCreateDate;
	private String lectureFileCategory;
}
