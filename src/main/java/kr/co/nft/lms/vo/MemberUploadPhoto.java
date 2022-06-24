package kr.co.nft.lms.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MemberUploadPhoto {
	private int photoNo;
	private MultipartFile memberPhotoOne;
}
