package kr.co.nft.lms.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.nft.lms.service.NoticeService;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Notice;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class NoticeController {
	@Autowired NoticeService noticeService;
	
	//Notice입력폼
	@GetMapping("/manager/notice/addNotice")
	public String addNotice() {
		return "/notice/addNotice";
	}
	//Notice 입력 액션
	@PostMapping("/manager/notice/addNotice")
	public String addNotice(Notice notice) {
		log.debug(A.S + "[NoticeController.addNotice.param] notice : "+ notice + A.R);
		int row = noticeService.addNotice(notice);
		log.debug(A.S + "[NoticeController.addNotice] row : "+ row + A.R);
		//row가 0 이면 입력 실패
		if(row==0) {
			log.debug(A.S + "[NoticeController.addNotice.param] 입력실패"+ A.R);
			return "redirect:/manager/notice/addNotice?msg=fail";
		}
		//입력성공 했을 경우
		log.debug(A.S + "[NoticeController.addNotice.param] 입력성공"+ A.R);
		return "redirect:/all/notice/getNoticeListByPage";
	}
	
	//Notice 목록보기
	@GetMapping("/all/notice/getNoticeListByPage")
	public String getNoticeListByPage(Model model
									,@RequestParam(name= "currentPage", defaultValue = "1") int currentPage
									,@RequestParam(name= "rowPerPage", defaultValue = "10") int rowPerPage) {
		log.debug(A.S + "[NoticeController.getNoticeListByPage.param] currentPage : " + currentPage + A.R);
		log.debug(A.S + "[NoticeController.getNoticeListByPage.param] rowPerPage : " + rowPerPage + A.R);
		
		Map<String, Object> noticeRowReturnMap = noticeService.getNoticeListByPage(currentPage, rowPerPage);
		log.debug(A.S + "[NoticeController.getNoticeListByPage] noticeRowReturnMap : " + noticeRowReturnMap + A.R);
		
		model.addAttribute("noticeList", noticeRowReturnMap.get("noticeList"));
		model.addAttribute("lastPage", noticeRowReturnMap.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		log.debug(A.S + "[NoticeController.getNoticeListByPage] model : " + model + A.R);
		
		return "/notice/getNoticeListByPage";
	}
	
	//Notice 상세보기
	@GetMapping("/all/notice/getNoticeOne")
	public String getNoticeOne(Model model
							,@RequestParam(name="noticeNo") int noticeNo) {
		log.debug(A.S + "[NoticeController.getNoticeOne.param] noticeNo : " + noticeNo + A.R);
		Notice notice = noticeService.getNoticeOne(noticeNo);
		log.debug(A.S + "[NoticeController.getNoticeOne] notice : " + notice + A.R);
		model.addAttribute("notice", notice);
		log.debug(A.S + "[NoticeController.getNoticeOne] model : " + model + A.R);
		return "/notice/getNoticeOne";
	}
	
	//Notice 수정폼
	@GetMapping("/manager/notice/modifyNotice")
	public String modifyNotice(Model model
							,@RequestParam(name="noticeNo") int noticeNo) {
		log.debug(A.S + "[NoticeController.modifyNotice.param] noticeNo : " + noticeNo + A.R);
		Notice notice = noticeService.getNoticeOne(noticeNo);
		log.debug(A.S + "[NoticeController.modifyNotice] notice : " + notice + A.R);
		model.addAttribute("notice", notice);
		return "/notice/modifyNotice";
	}
	
	//Notice 수정 액션
	@PostMapping("/manager/notice/modifyNotice")
	public String modifyNotice(Notice notice) {
		log.debug(A.S + "[NoticeController.modifyNotice.param] notice : " + notice + A.R);
		int row = noticeService.modifyNotice(notice);
		log.debug(A.S + "[NoticeController.modifyNotice] row : " + row + A.R);
		//row가 0 이면 입력 실패
		if(row==0) {
			log.debug(A.S + "[NoticeController.modifyNotice.row] 수정실패"+ A.R);
			return "redirect:/manager/notice/modifyNotice?msg=fail";
		}
		//입력성공 했을 경우
		log.debug(A.S + "[NoticeController.modifyNotice.row] 수정성공"+ A.R);
		return "redirect:/all/notice/getNoticeOne?noticeNo=" + notice.getNoticeNo();
	}		 
	
	//Notice 삭제 액션
	@GetMapping("/manager/notice/removeNotice")
	public String removeNotice(int noticeNo) {
		log.debug(A.S + "[NoticeController.removeNotice.param] noticeNo : " + noticeNo + A.R);
		int row = noticeService.removeNotice(noticeNo);
		log.debug(A.S + "[NoticeController.removeNoticex] row : " + row + A.R);
		//row가 0 이면 입력 실패
		if(row==0) {
			log.debug(A.S + "[NoticeController.removeNotice.param] 삭제(블라인드처리)실패"+ A.R);
			return "redirect:/manager/notice/removeNotice?msg=fail";
		}
		//입력성공 했을 경우
		log.debug(A.S + "[NoticeController.removeNotice.param] 삭제(블라인드처리)성공"+ A.R);
		return "redirect:/all/notice/getNoticeListByPage";
	}
	
}
