package kr.co.nft.lms.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.nft.lms.service.NoticeService;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Member;
import kr.co.nft.lms.vo.Notice;
import kr.co.nft.lms.vo.NoticeFile;
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
	public String addNotice(HttpServletRequest request,Notice notice,NoticeFile noticeFile) {
		//파일 저장 경로 설정
		String path = request.getServletContext().getRealPath("/static/uploadFile/noticeFile/");
		log.debug(A.S + "[NoticeController.addNotice.param] notice : "+ notice + A.R);
		log.debug(A.S + "[NoticeController.addNotice.param] noticeFile : "+ noticeFile + A.R);
		int row = noticeService.addNotice(notice,noticeFile,path);
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
									,HttpSession session
									,@RequestParam(name= "currentPage", defaultValue = "1") int currentPage
									,@RequestParam(name= "rowPerPage", defaultValue = "10") int rowPerPage) {
		//요청값 디버깅
		log.debug(A.S + "[NoticeController.getNoticeListByPage.param] currentPage : " + currentPage + A.R);
		log.debug(A.S + "[NoticeController.getNoticeListByPage.param] rowPerPage : " + rowPerPage + A.R);
		//세션에 로그인 정보 요청
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		
		
		Map<String, Object> noticeRowReturnMap = noticeService.getNoticeListByPage(currentPage, rowPerPage,loginMember.getMemberLevel());
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
							,HttpSession session
							,@RequestParam(name="noticeNo") int noticeNo) {
		//요청값 디버깅
		log.debug(A.S + "[NoticeController.getNoticeOne.param] noticeNo : " + noticeNo + A.R);
		//세션에 로그인 정보 요청
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		
		Map<String, Object> noticeOneReturnMap = noticeService.getNoticeOne(noticeNo);
		log.debug(A.S + "[NoticeController.getNoticeOne] notice : " + noticeOneReturnMap.get("notice") + A.R);
		log.debug(A.S + "[NoticeController.getNoticeOne] noticeFileList : " + noticeOneReturnMap.get("noticeFileList") + A.R);
		//returnMap 안에 notice는 권한 비교를 위해 notice변수에 저장
		Notice notice = (Notice)noticeOneReturnMap.get("notice");
		
		model.addAttribute("notice", notice);
		model.addAttribute("noticeFileList", noticeOneReturnMap.get("noticeFileList"));
		log.debug(A.S + "[NoticeController.getNoticeOne] model : " + model + A.R);
		//가져온 상세보기가 로그인한 회원의 권한 밖의 게시물이면 list로 redirect
		if(notice.getNoticePrivilege() > loginMember.getMemberLevel()){
			log.debug(A.S + "[NoticeController.getNoticeOne] 권한밖의 notice게시물 상세보기 요청" + A.R);
			return "redirect:/all/notice/getNoticeListByPage";
		}
		return "/notice/getNoticeOne";
	}
	
	//Notice 수정폼
	@GetMapping("/manager/notice/modifyNotice")
	public String modifyNotice(Model model
							,HttpSession session
							,@RequestParam(name="noticeNo") int noticeNo) {
		log.debug(A.S + "[NoticeController.modifyNotice.param] noticeNo : " + noticeNo + A.R);
		//세션에 로그인 정보 요청
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		
		Map<String, Object> noticeOneReturnMap = noticeService.getNoticeOne(noticeNo);
		log.debug(A.S + "[NoticeController.modifyNotice] notice : " + noticeOneReturnMap.get("notice") + A.R);
		log.debug(A.S + "[NoticeController.modifyNotice] noticeFileList : " + noticeOneReturnMap.get("noticeFileList") + A.R);
		//returnMap 안에 notice는 권한 비교를 위해 notice변수에 저장
		Notice notice = (Notice)noticeOneReturnMap.get("notice");
		
		model.addAttribute("notice", noticeOneReturnMap.get("notice"));
		model.addAttribute("noticeFileList", noticeOneReturnMap.get("noticeFileList"));
		log.debug(A.S + "[NoticeController.modifyNotice] model : " + model + A.R);
		
		//가져온 상세보기가 로그인한 회원의 권한 밖의 게시물이면 list로 redirect
			if(notice.getNoticePrivilege() > loginMember.getMemberLevel()){
				log.debug(A.S + "[NoticeController.getNoticeOne] 권한밖의 notice게시물 수정 요청" + A.R);
				return "redirect:/all/notice/getNoticeListByPage";
			}
		return "/notice/modifyNotice";
	}
	
	//Notice 수정 액션
	@PostMapping("/manager/notice/modifyNotice")
	public String modifyNotice(HttpServletRequest request, Notice notice, NoticeFile noticeFile) {
		//파일 저장 경로 설정
		String path = request.getServletContext().getRealPath("/static/uploadFile/noticeFile/");
		log.debug(A.S + "[NoticeController.modifyNotice.param] notice : " + notice + A.R);
		log.debug(A.S + "[NoticeController.modifyNotice.param] noticeFile : " + noticeFile + A.R);
		int row = noticeService.modifyNotice(notice,noticeFile,path);
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
	
	//Notice 삭제폼
	@GetMapping("/manager/notice/removeNotice")
	public String removeNotice(Model model
							,@RequestParam(name="noticeNo") int noticeNo) {
		log.debug(A.S + "[NoticeController.removeNotice.param] noticeNo : " + noticeNo + A.R);
		Map<String, Object> noticeOneReturnMap = noticeService.getNoticeOne(noticeNo);
		log.debug(A.S + "[NoticeController.removeNotice] notice : " + noticeOneReturnMap.get("notice") + A.R);
		model.addAttribute("notice", noticeOneReturnMap.get("notice"));
		return "/notice/removeNotice";
	}
	
	//Notice 삭제 액션
	@PostMapping("/manager/notice/removeNotice")
	public String removeNotice(HttpServletRequest request,int noticeNo) {
		String path = request.getServletContext().getRealPath("/static/uploadFile/noticeFile/");
		log.debug(A.S + "[NoticeController.removeNotice.param] noticeNo : " + noticeNo + A.R);
		int row = noticeService.removeNotice(noticeNo,path);
		log.debug(A.S + "[NoticeController.removeNotice] row : " + row + A.R);
		//row가 0 이면 입력 실패
		if(row==0) {
			log.debug(A.S + "[NoticeController.removeNotice.param] 삭제(블라인드처리)실패"+ A.R);
			return "redirect:/manager/notice/removeNotice?msg=fail";
		}
		//입력성공 했을 경우
		log.debug(A.S + "[NoticeController.removeNotice.param] 삭제(블라인드처리)성공"+ A.R);
		return "redirect:/all/notice/getNoticeListByPage";
	}

	//NoticeFile 삭제 액션
	@GetMapping("/manager/notice/removeNoticeFile")
	public String removeNoticeFile(HttpServletRequest request, String noticeFileName,int noticeFileNo, int noticeNo) {
		String path = request.getServletContext().getRealPath("/static/uploadFile/noticeFile/");
		log.debug(A.S + "[NoticeController.removeNoticeFile.param] noticeFileName : " + noticeFileName + A.R);
		log.debug(A.S + "[NoticeController.removeNoticeFile.param] noticeNo : " + noticeNo + A.R);
		log.debug(A.S + "[NoticeController.removeNoticeFile.param] noticeFileNo : " + noticeFileNo + A.R);
		int row = noticeService.removeNoticeFile(noticeNo,noticeFileName,noticeFileNo,path);
		log.debug(A.S + "[NoticeController.removeNoticeFile] row : " + row + A.R);
		//row가 0 이면 삭제실패
		if(row==0) {
			log.debug(A.S + "[NoticeController.removeNoticeFile.row] noticeFile삭제 실패"+ A.R);
			return "redirect:/manager/notice/removeNoticeFile?msg=fail";
		}
		//삭제성공 했을 경우
		log.debug(A.S + "[NoticeController.removeNoticeFile.row] noticeFile삭제 성공"+ A.R);
		return "redirect:/manager/notice/modifyNotice?noticeNo=" + noticeNo;
		
		
	}
}
