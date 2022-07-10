package kr.co.nft.lms.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.nft.lms.service.NoticeService;
import kr.co.nft.lms.util.A;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class NoticeRestController {
	@Autowired NoticeService noticeService;
	
	@GetMapping("/rest/teacher/notice/removeNoticeFile")
	public Map<String, Object> removeNoticeFile(HttpServletRequest request, String noticeFileName, int noticeFileNo, int noticeNo){
		String path = request.getServletContext().getRealPath("/uploadFile/noticeFile/");
		log.debug(A.S + "[NoticeRestController.removeNoticeFile] path : "+ path + A.R);
		log.debug(A.S + "[NoticeRestController.removeNoticeFile.param] noticeFileName : "+ noticeFileName + A.R);
		log.debug(A.S + "[NoticeRestController.removeNoticeFile.param] noticeFileNo : "+ noticeFileNo + A.R);
		log.debug(A.S + "[NoticeRestController.removeNoticeFile.param] noticeNo : "+ noticeNo + A.R);
		
		int row = noticeService.removeNoticeFile(noticeNo, noticeFileName, noticeFileNo, path);
		log.debug(A.S + "[NoticeRestController.removeNoticeFile] row : "+ row + A.R);
		Map<String, Object> map = new HashMap<>();
		map.put("row", row);
		
		return map;
	}
}
