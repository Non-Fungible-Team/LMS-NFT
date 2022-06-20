package kr.co.nft.lms.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.nft.lms.service.ExamService;
import kr.co.nft.lms.vo.Exam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ExamController {
	@Autowired private ExamService examService;
	
	@GetMapping("/getExamListByPage")
	public String getNoticeListByPage(Model model,
			Exam exam
			,@RequestParam(name="currentPage", defaultValue="1") int currentPage
			,@RequestParam(name="rowPerPage", defaultValue = "10") int rowPerPage) {
			Map<String, Object> returnMap = examService.getExamListByPage(currentPage, rowPerPage);
			log.debug("▶▶▶▶▶▶ NoticeController.getNoticeListByPage.param.returnMap : "+ returnMap);
			model.addAttribute("noticeList", returnMap.get("noticeList"));
			model.addAttribute("noticeLastPage", returnMap.get("noticeLastPage"));
			model.addAttribute("currentPage", currentPage);
			return "getNoticeListByPage";
			}

}
