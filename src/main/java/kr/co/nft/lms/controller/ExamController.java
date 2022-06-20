package kr.co.nft.lms.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.nft.lms.service.ExamService;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Exam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/exam")
public class ExamController {
	@Autowired private ExamService examService;
	
	@GetMapping("/getExamListByPage")
	public String getExamListByPage(Model model,
									Exam exam
									,@RequestParam(name="currentPage", defaultValue="1") int currentPage
									,@RequestParam(name="rowPerPage", defaultValue ="10") int rowPerPage) {
			Map<String, Object> returnMap = examService.getExamListByPage(currentPage, rowPerPage);
			log.debug(A.C + "[ExamController.getExamListByPage.param] returnMap: " + returnMap + A.R);
			model.addAttribute("examList", returnMap.get("examList"));
			model.addAttribute("examLastPage", returnMap.get("examLastPage"));
			model.addAttribute("currentPage", currentPage);
			return "/exam/getExamListByPage";
			}

}
