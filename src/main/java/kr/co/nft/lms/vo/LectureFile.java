package kr.co.nft.lms.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class LectureFile {
	private int lectureFileNo;
	private int lectureBoardNo;
	private String lectureFileName;
	private String lectureFileOriginName;
	private String lectureFileType;
	private long lectureFileSize;
	//private String lectureFileCreateDate;
	//private String lectureFileCategory;
	
	private List<MultipartFile> lectureFileList;
	
}
