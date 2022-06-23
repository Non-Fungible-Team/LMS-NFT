package kr.co.nft.lms.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class HomeworkSubmit {
	private int homeworkSubmitNo;
	private int homeworkNo;
	private String memberId;
	private String homeworkSubmitTitle;
	private String homeworkSubmitContent;
	private int homeworkSubmitScore;
	private String homeworkSubmitFeedback;
	private String homeworkSubmitCreateDate;
	private String homeworkSubmitUpdateDate;
	private List<MultipartFile> homeworkSubmitFileList;

}
