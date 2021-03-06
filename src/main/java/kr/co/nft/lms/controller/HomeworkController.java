package kr.co.nft.lms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.co.nft.lms.service.HomeworkService;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Homework;
import kr.co.nft.lms.vo.HomeworkSubmit;
import kr.co.nft.lms.vo.HomeworkSubmitFile;
import kr.co.nft.lms.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeworkController {
	@Autowired private HomeworkService homeworkService;
	
	// 과제 리스트
	@GetMapping("/all/homework/getHomeworkListByPage")
	public String getHomeworkListByPage(Model model
			,HttpSession session
			,@RequestParam(name="currentPage", defaultValue = "1") int currentPage
			,@RequestParam(name="rowPerPage", defaultValue = "10") int rowPerPage
			,@RequestParam(value="searchWord", required = false) String searchWord) {
		
		log.debug(A.Q + "HomeworkController.getHomeworkListByPage.param.currentPage :  " + currentPage + A.R);
		log.debug(A.Q + "HomeworkController.getHomeworkListByPage.param.rowPerPage :  " + rowPerPage + A.R);
		
		//세션에 정보 요청
		int lectureNo = (int)session.getAttribute("sessionLectureNo");
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		Map<String, Object> map = homeworkService.getHomeworkListByPage(currentPage, rowPerPage, lectureNo, loginMember, searchWord);
		log.debug(A.Q + "HomeworkController.getHomeworkListByPage map:  "+ map + A.R);
		
		
		model.addAttribute("homeworkList", map.get("homeworkList"));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("searchWord", searchWord);
		log.debug(A.Q + "HomeworkController.getHomeworkListByPage model :" + model + A.R);
		
		return "/homework/getHomeworkListByPage";
	}
	
	// 과제 상세보기
	@GetMapping("/all/homework/getHomeworkOne")
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
	@GetMapping("/teacher/homework/addHomework")
	public String addHomework() {
		return "/homework/addHomework";
	}
	
	// 과제 입력 액션
	@PostMapping("/teacher/homework/addHomework")
	public String addHomework(Homework homework) {
		log.debug(A.Q+"HomeworkController.addHomework.param.homework :" + homework + A.R);
	
		int row = homeworkService.addHomework(homework);
		log.debug(A.Q+ "HomeworkController.addHomework.row :" + row + A.R);
		
		if(row ==1) {
			log.debug(A.Q+"HomeworkController.addHomework 과제 입력 성공"+A.R);
		} else {
			log.debug(A.Q+"HomeworkController.addHomework 과제 입력 실패"+A.R);
		}
		
		return "redirect:/all/homework/getHomeworkListByPage";
		
	}
	
	// 과제 수정 폼
	@GetMapping("/teacher/homework/modifyHomework")
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
	@PostMapping("/teacher/homework/modifyHomework")
	public String modifyHomework(Homework homework) {
		log.debug(A.Q+"HomeworkController.modifyHomework.param.homework : " + homework +A.R);
		
		int row = homeworkService.modifyHomework(homework);
		if(row == 1) {
			log.debug(A.Q+"과제 수정 성공"+A.R);
		} else {
			log.debug(A.Q+"과제 수정 실패"+A.R);
		}
		return "redirect:/all/homework/getHomeworkOne?homeworkNo=" + homework.getHomeworkNo();
	}
	// 과제 삭제
	@PostMapping("/teacher/homework/removeHomework")
	public String removeHomework(@RequestParam(name="homeworkNo") int homeworkNo) {
		log.debug(A.Q+"HomeworkController.removeHomework.param.homeworkNo : "+ homeworkNo +A.R);
		int row = homeworkService.removeHomework(homeworkNo);
		if(row == 1) {
			log.debug(A.Q+"과제 삭제 성공"+A.R);
		} else {
			log.debug(A.Q+"과제 삭제 실패"+A.R);
		}
	return "redirect:/all/homework/getHomeworkListByPage";
	}
	
	
	// 학생 과제 제출 입력 폼
	@GetMapping("/student/homework/addHomeworkSubmit")
	public String addHomeworkSubmit() {

		return "/homework/addHomeworkSubmit";
	}
	
	// 학생 과제 제출 입력 액션
	@PostMapping("/student/homework/addHomeworkSubmit")
	public String addHomeworkSubmit(HttpServletRequest request
									,HomeworkSubmit homeworkSubmit
									,Homework homework) {
		
//		URL pathUrl = this.getClass().getResource("/static/");
//		String path = pathUrl.getPath()+"/uploadFile/homeworkFile/";
		
		String path = request.getServletContext().getRealPath("/uploadFile/homeworkFile/");
		
		log.debug(A.Q+"HomeworkController.addHomeworkSubmit path :" + path +A.R);
		log.debug(A.Q+"HomeworkController.addHomeworkSubmit homeworkSubmit" + homeworkSubmit +A.R);

		List<MultipartFile> homeworkSubmitFileList = homeworkSubmit.getHomeworkSubmitFileList();
		if(homeworkSubmitFileList != null && homeworkSubmitFileList.get(0).getSize() > 0) {
			for(MultipartFile mf : homeworkSubmitFileList ) {
				log.debug(A.Q+"HomeworkController.addHomeworkSubmit filename :" + mf.getOriginalFilename() + A.R);
			}
		}
		
		homeworkService.addHomeworkSubmit(homeworkSubmit,homework, path);
		
		return"redirect:/all/homework/getHomeworkListByPage";
	}
	// 학생 제출과제 리스트
	@GetMapping("/all/homework/getHomeworkSubmitListByPage")
	public String getHomeworkSubmitListByPage(Model model
											,@RequestParam(name="currentPage", defaultValue = "1") int currentPage
											,@RequestParam(name="rowPerPage", defaultValue = "10")  int rowPerPage
											,@RequestParam(name="homeworkNo", required=false) int homeworkNo){
		log.debug(A.Q + "HomeworkController.getHomeworkListByPage.param.currentPage :  " + currentPage + A.R);
		log.debug(A.Q + "HomeworkController.getHomeworkListByPage.param.rowPerPage :  " + rowPerPage + A.R);

		//		Homework homework = new Homework();
//		int homeworkNo = homework.getHomeworkNo();
		
		Map<String, Object> map = homeworkService.getHomeworkSubmitListByPage(currentPage, rowPerPage, homeworkNo);
		log.debug(A.Q + "HomeworkController.getHomeworkListByPage map:  "+ map + A.R);
		
		model.addAttribute("homeworkSubmitList", map.get("homeworkSubmitList"));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		log.debug(A.Q + "HomeworkController.getHomeworkListByPage model :" + model + A.R);
		
		return "/homework/getHomeworkSubmitListByPage";
	}
	
	// 학생 과제 상세보기 
	@GetMapping("/all/homework/getHomeworkSubmitOne")
	public String getHomeworkSubmitOne(Model model
									,@RequestParam(name="homeworkSubmitNo", required=false) int homeworkSubmitNo) {
		log.debug(A.Q+"HomeworkSubmitController.getHomeworkSubmitOne.param.homeworkSubmitNo : "+ homeworkSubmitNo +A.R);
		
		Map<String, Object> map = homeworkService.getHomeworkSubmitOne(homeworkSubmitNo);
		log.debug(A.Q+"HomeworkSubmitController.getHomeworkSubmitOne.homeworkSubmitOne"+ map.get("homeworkSubmitOne") + A.R);
		log.debug(A.Q+"HomeworkSubmitController.getHomeworkSubmitOne.homeworkSubmitOne"+ map.get("homeworkSubmitFileList") + A.R);
		
		model.addAttribute("homeworkSubmitOne", map.get("homeworkSubmitOne"));
		model.addAttribute("homeworkSubmitFileList", map.get("homeworkSubmitFileList"));
		log.debug(A.Q+"HomeworkSubmitController.getHomeworkSubmitOne.model :"+ model +A.R);
		
		return "/homework/getHomeworkSubmitOne";
	}
	
	// 학생 점수 입력
	@PostMapping("/all/homework/getHomeworkSubmitOne")
	public String getHomeworkSubmitOne(HomeworkSubmit homeworkSubmit) {
		int row = homeworkService.modifyHomeworkSubmitScore(homeworkSubmit);
		if(row ==1) {
			log.debug(A.Q+"점수 입력 성공"+A.R);
		} else {
			log.debug(A.Q+"점수 입력 실패"+A.R);
		}
		return "redirect:/all/homework/getHomeworkSubmitOne?homeworkSubmitNo=" +homeworkSubmit.getHomeworkSubmitNo();
	}
	
	// 학생 과제 수정 폼
	@GetMapping("/student/homework/modifyHomeworkSubmit")
	public String modifyHomeworkSubmit(Model model
									,@RequestParam(name="homeworkSubmitNo", required=false) int homeworkSubmitNo) {
		log.debug(A.Q+"HomeworkController.modifyHomeworkSubmit homeworkSubmitNo :"+ homeworkSubmitNo +A.R);
		
		Map<String, Object> map = homeworkService.getHomeworkSubmitOne(homeworkSubmitNo);
		log.debug(A.Q+"HomeworkController.modifyHomeworkSubmit.map"+ map +A.R);
		
		model.addAttribute("homeworkSubmitOne", map.get("homeworkSubmitOne"));
		model.addAttribute("homeworkSubmitFileList", map.get("homeworkSubmitFileList"));
		return "/homework/modifyHomeworkSubmit";
	}
	
	// 학생 과제 수정 액션
	@PostMapping("/student/homework/modifyHomeworkSubmit")
	public String modifyHomeworkSubmit(HttpServletRequest request
									,HomeworkSubmit homeworkSubmit
									,Homework homework) {
		log.debug(A.Q+"HomeworkController.modifyHomeworkSubmit.param.homework : " + homeworkSubmit +A.R);
		
//		URL pathUrl = this.getClass().getResource("/static/");
//		String path = pathUrl.getPath()+"/uploadFile/homeworkFile/";
		String path = request.getServletContext().getRealPath("/uploadFile/homeworkFile/");
		
		// homeworkService.addHomeworkSubmit(homeworkSubmit, homework, path);
		
		List<MultipartFile> homeworkSubmitFileList = homeworkSubmit.getHomeworkSubmitFileList();
		if(homeworkSubmitFileList != null && homeworkSubmitFileList.get(0).getSize() > 0) {
			for(MultipartFile mf : homeworkSubmitFileList ) {
				log.debug(A.Q+"HomeworkController.modifyHomeworkSubmit filename :" + mf.getOriginalFilename() + A.R);
			}
		}

		int row = homeworkService.modifyHomeworkSubmit(homework, homeworkSubmit, path);
		if(row == 1) {
			log.debug(A.Q+"과제 수정 성공"+A.R);
		} else {
			log.debug(A.Q+"과제 수정 실패"+A.R);
		}
		
		return "redirect:/all/homework/getHomeworkSubmitOne?homeworkSubmitNo=" + homeworkSubmit.getHomeworkSubmitNo();
	}
	
	// 학생 과제 삭제 액션
	@PostMapping("/student/homework/removeHomeworkSubmit")
	public String removeHomeworkSubmit(@RequestParam(name="homeworkSubmitNo") int homeworkSubmitNo) {
		log.debug(A.Q+"HomeworkController.removeHomeworkSubmit.param.homeworkSubmitNo :" + homeworkSubmitNo + A.R);
		
		int row = homeworkService.removeHomeworkSubmit(homeworkSubmitNo);
		if(row == 1) {
			log.debug(A.Q+ "과제 삭제 성공" + A.R);
		} else {
			log.debug(A.Q+ "과제 삭제 실패" + A.R);
		}
		
		return "redirect:/all/homework/getHomeworkListByPage";
	}
	
	// 학생 과제 파일 삭제 액션
	@GetMapping("/student/homework/removeHomeworkSubmitFileOne")
	public String removeHomeworkSubmitFileOne(HttpServletRequest request, String homeworkSubmitFileName,int homeworkSubmitFileNo, int homeworkSubmitNo) {
		String path = request.getServletContext().getRealPath("/uploadFile/homeworkFile/");
		int row = homeworkService.removeHomeworkSubmitFileOne(homeworkSubmitNo, homeworkSubmitFileName, homeworkSubmitFileNo, path);
		if(row ==1) {
			log.debug(A.Q+"파일 삭제 성공"+A.R);
		} else {
			log.debug(A.Q+"파일 삭제 실패" + A.R);
		}
		
		return "redirect:/student/homework/modifyHomeworkSubmit?homeworkSubmitNo=" + homeworkSubmitNo;
	}
	

	
	
	
	
	

}
