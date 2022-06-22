package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class HomeworkSubmitFile {
	private int homeworkSubmitFileNo;
	private int homeworkSubmitNo;
	private String homeworkSubmitFileName;
	private String homeworkSubmitFileOriginal;
	private String homeworkSubmitFileType;
	private long homeworkSubmitFileSize;
	private String homeworkSubmitFileCreateDate;
	private String homeworkSubmitFileUpdateDate;
	

}
