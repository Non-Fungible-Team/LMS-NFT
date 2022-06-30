package kr.co.nft.lms.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.nft.lms.service.HomeworkService;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Homework;
import kr.co.nft.lms.vo.HomeworkSubmit;
import kr.co.nft.lms.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeworkController {
	@Autowired private HomeworkService homeworkService;
	
	// 과제 리스트
	@GetMapping("/homework/getHomeworkListByPage")
	public String getHomeworkListByPage(Model model
			,HttpSession session
			,@RequestParam(name="currentPage", defaultValue = "1") int currentPage
			,@RequestParam(name="rowPerPage", defaultValue = "10") int rowPerPage) {
		
		log.debug(A.Q + "HomeworkController.getHomeworkListByPage.param.currentPage :  " + currentPage + A.R);
		log.debug(A.Q + "HomeworkController.getHomeworkListByPage.param.rowPerPage :  " + rowPerPage + A.R);
		
		//세션에 정보 요청
		int lectureNo = (int)session.getAttribute("sessionLectureNo");
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		Map<String, Object> map = homeworkService.getHomeworkListByPage(currentPage, rowPerPage, lectureNo, loginMember);
		log.debug(A.Q + "HomeworkController.getHomeworkListByPage map:  "+ map + A.R);
		
		
		model.addAttribute("homeworkList", map.get("homeworkList"));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		log.debug(A.Q + "HomeworkController.getHomeworkListByPage model :" + model + A.R);
		
		return "/homework/getHomeworkListByPage";
	}
	
	// 과제 상세보기
	@GetMapping("/homework/getHomeworkOne")
	public String getHomeworkOne(Model model
								,@RequestParam(name="homeworkNo") int homeworkNo) {
		log.debug(A.Q+"HomeworkController.getHomeworkOne.param.homeworkNo : "+ homeworkNo +A.R);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap = homeworkService.getHomeworkOne(homeworkNo);
		log.debug(A.Q+"HomeworkController.getHomeworkOne.returnMap : "+ returnMap +A.R);
		
		model.addAttribute("homeworkOne", returnMap.get("homeworkOne"));
		log.debug(A.Q+"HomeworkController.getHomeworkOne.model : "+ model +A.R);

		return "/homework/getHomeworkOne";
		
	}
	// 과제 입력 폼
	@GetMapping("/homework/addHomework")
	public String addHomework() {
		return "/homework/addHomework";
	}
	
	// 과제 입력 액션
	@PostMapping("/homework/addHomework")
	public String addHomework(Homework homework) {
		log.debug(A.Q+"HomeworkController.addHomework.param.homework :" + homework + A.R);
	
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
	@GetMapping("/homework/modifyHomework")
	public String modifyHomework(Model model
								,@RequestParam(name="homeworkNo") int homeworkNo) {
		log.debug(A.Q+"HomeworkController.modifyHomework.param.homeworkNo :"+ homeworkNo +A.R);
		
		Map<String, Object> returnMap = homeworkService.getHomeworkOne(homeworkNo);
		log.debug(A.Q+"HomeworkController.modifyHomework.returnMap : "+ returnMap +A.R);
		
		model.addAttribute("homeworkOne", returnMap.get("homeworkOne"));
		log.debug(A.Q+"HomeworkController.modifyHomework.model : "+ model +A.R);
		return "/homework/modifyHomework";
	}
	
	// 과제 수정 액션
	@PostMapping("/homework/modifyHomework")
	public String modifyHomework(Homework homework) {
		log.debug(A.Q+"HomeworkController.modifyHomework.param.homework : " + homework +A.R);
		
		int row = homeworkService.modifyHomework(homework);
		if(row == 1) {
			log.debug(A.Q+"과제 수정 성공"+A.R);
		} else {
			log.debug(A.Q+"과제 수정 실패"+A.R);
		}
		return "redirect:/homework/getHomeworkOne?homeworkNo=" + homework.getHomeworkNo();
	}
	// 과제 삭제
	@PostMapping("/homework/removeHomework")
	public String removeHomework(@RequestParam(name="homeworkNo") int homeworkNo) {
		log.debug(A.Q+"HomeworkController.removeHomework.param.homeworkNo : "+ homeworkNo +A.R);
		int row = homeworkService.removeHomework(homeworkNo);
		if(row == 1) {
			log.debug(A.Q+"과제 삭제 성공"+A.R);
		} else {
			log.debug(A.Q+"과제 삭제 실패"+A.R);
		}
	return "redirect:/homework/getHomeworkListByPage";
	}
	
	
	// 학생 과제 제출 입력 폼
	@GetMapping("/homework/addHomeworkSubmit")
	public String addHomeworkSubmit() {

		return "/homework/addHomeworkSubmit";
	}
	
	// 학생 과제 제출 입력 액션
	@PostMapping("/homework/addHomeworkSubmit")
	public String addHomeworkSubmit(HttpServletRequest request
									,HomeworkSubmit homeworkSubmit
									,Homework homework) {
		
		URL pathUrl = this.getClass().getResource("/static/");
		String path = pathUrl.getPath()+"/uploadFile/homeworkFile/";
		log.debug(A.Q+"HomeworkController.addHomeworkSubmit path :" + path +A.R);
		log.debug(A.Q+"HomeworkController.addHomeworkSubmit homeworkSubmit" + homeworkSubmit +A.R);

		List<MultipartFile> homeworkSubmitFileList = homeworkSubmit.getHomeworkSubmitFileList();
		if(homeworkSubmitFileList != null && homeworkSubmitFileList.get(0).getSize() > 0) {
			for(MultipartFile mf : homeworkSubmitFileList ) {
				log.debug(A.Q+"HomeworkController.addHomeworkSubmit filename :" + mf.getOriginalFilename() + A.R);
			}
		}
		
		homeworkService.addHomeworkSubmit(homeworkSubmit,homework, path);
		
		return"redirect:/homework/getHomeworkListByPage";
	}
	// 학생 제출과제 리스트
	@GetMapping("/homework/getHomeworkSubmitListByPage")
	public String getHomeworkSubmitListByPage(Model model
											,@RequestParam(name="currentPage", defaultValue = "1") int currentPage
											,@RequestParam(name="rowPerPage", defaultValue = "10") int rowPerPage) {
		
		log.debug(A.Q + "HomeworkController.getHomeworkListByPage.param.currentPage :  " + currentPage + A.R);
		log.debug(A.Q + "HomeworkController.getHomeworkListByPage.param.rowPerPage :  " + rowPerPage + A.R);
		
		Map<String, Object> map = homeworkService.getHomeworkSubmitListByPage(currentPage, rowPerPage);
		log.debug(A.Q + "HomeworkController.getHomeworkListByPage map:  "+ map + A.R);
		
		model.addAttribute("homeworkSubmitList", map.get("homeworkSubmitList"));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		log.debug(A.Q + "HomeworkController.getHomeworkListByPage model :" + model + A.R);
		
		return "/homework/getHomeworkSubmitListByPage";
	}
	
	// 학생 과제 상세보기 
	@GetMapping("/homework/getHomeworkSubmitOne")
	public String getHomeworkSubmitOne(Model model
									,@RequestParam(name="homeworkSubmitNo", defaultValue = "0") int homeworkSubmitNo) {
		log.debug(A.Q+"HomeworkSubmitController.getHomeworkSubmitOne.param.homeworkSubmitNo : "+ homeworkSubmitNo +A.R);
		
		Map<String, Object> map = homeworkService.getHomeworkSubmitOne(homeworkSubmitNo);
		log.debug(A.Q+"HomeworkSubmitController.getHomeworkSubmitOne.homeworkSubmitOne"+ map.get("homeworkSubmitOne") + A.R);
		log.debug(A.Q+"HomeworkSubmitController.getHomeworkSubmitOne.homeworkSubmitOne"+ map.get("homeworkSubmitFileList") + A.R);
		
		model.addAttribute("homeworkSubmitOne", map.get("homeworkSubmitOne"));
		model.addAttribute("homeworkSubmitFileList", map.get("homeworkSubmitFileList"));
		log.debug(A.Q+"HomeworkSubmitController.getHomeworkSubmitOne.model :"+ model +A.R);
		
		return "/homework/getHomeworkSubmitOne";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
