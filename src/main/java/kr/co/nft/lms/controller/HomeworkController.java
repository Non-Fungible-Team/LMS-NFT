package kr.co.nft.lms.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.nft.lms.service.HomeworkService;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Homework;
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
	// 과제 입력 폼
	@GetMapping("/addHomework")
	public String addHomework() {
		return "/homework/addHomework";
	}
	// 과제 입력 액션
	@PostMapping("/addHomework")
	public String addHomework(Homework homework) {
		log.debug(A.Q+"HomeworkController.addHomework.param.homework :" + homework.toString() + A.R);
		int row = homeworkService.addHomework(homework);
		log.debug(A.Q+ "HomeworkController.addHomework.row :" + row + A.R);
		if(row ==1) {
			log.debug(A.Q+"HomeworkController.addHomework 과제 입력 성공"+A.R);
		} else {
			log.debug(A.Q+"HomeworkController.addHomework 과제 입력 실패"+A.R);
		}
		return "redirect:/homework/getHomeworkListByPage";
		
	}
	
	// 과제 수정 폼
//	@GetMapping("/modifyHomework")
//	public String modifyHomework(Model model
//								,@RequestParam(name="homeworkNo") int homeworkNo) {
//		Map<String, Object> homework = homeworkService.getHomeworkOne(homeworkNo);
//		model.addAttribute("",);
//		return "/homework/modifyHomework";
//	}
	
	// 과제 수정 액션
	@PostMapping("modifyHomework")
	public String modifyHomework(Homework homework) {
	
		int row = homeworkService.modifyHomework(homework);
		if(row == 1) {
			log.debug(A.Q+"과제 수정 성공"+A.R);
		} else {
			log.debug(A.Q+"과제 수정 실패"+A.R);
		}
		return "redirect:/homework/getHomeworkOne?homeworkNo=" + homework.getHomeworkNo();
	}
	// 과제 삭제
	@GetMapping("/removeHomework")
	public String removeHomework(@RequestParam(name="homeworkNo") int homeworkNo) {
		int row = homeworkService.removeHomework(homeworkNo);
		if(row == 1) {
			log.debug(A.Q+"과제 삭제 성공"+A.R);
		} else {
			log.debug(A.Q+"과제 삭제 실패"+A.R);
		}
	return "redirect:/homework/getHomeworkListByPage";
	}

}
