package kr.co.nft.lms.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.nft.lms.service.HomeworkService;
import kr.co.nft.lms.util.A;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/homework")
public class HomeworkController {
	@Autowired private HomeworkService homeworkService;
	
	// 과제 리스트
	@GetMapping("/getHomeworkListByPage")
	public String getHomeworkListByPage(Model model,
			@RequestParam(name="currentPage", defaultValue = "1") int currentPage,
			@RequestParam(name="rowPerPage", defaultValue = "10") int rowPerPage) {
		
		Map<String, Object> map = homeworkService.getHomeworkListByPage(currentPage, rowPerPage);
		log.debug(A.Q + "HomeworkController.getHomeworkListByPage map:  "+ map.toString() + A.R);
		
		model.addAttribute("homeworkList", map.get("homeworkList"));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		log.debug(A.Q + "HomeworkController.getHomeworkListByPage model :" + model.toString() + A.R);
		
		return "/homework/getHomeworkListByPage";
	}
	
	// 과제 상세보기
	@GetMapping("/getHomeworkOne")
	public String getHomeworkOne(Model model
								,@RequestParam(name="homeworkNo") int homeworkNo) {
		Map<String, Object> returnMap = new HashMap<>();
		returnMap = homeworkService.getHomeworkOne(homeworkNo);
		model.addAttribute("homeworkOne", returnMap.get("homeworkOne"));
		return "/homework/getHomeworkOne";
		
	}

}
