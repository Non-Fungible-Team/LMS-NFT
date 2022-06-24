package kr.co.nft.lms.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class NoticeFile {
	private int noticeFileNo;
	private int noticeNo;
	private String noticeFileName;
	private String noticeFileOriginName;
	private String noticeFileType;
	private long noticeFileSize;
	
	private List<MultipartFile> noticeFileList;
}
