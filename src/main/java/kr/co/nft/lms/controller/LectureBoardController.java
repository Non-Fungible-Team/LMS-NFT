package kr.co.nft.lms.controller;

import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.nft.lms.service.LectureBoardService;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Lecture;
import kr.co.nft.lms.vo.LectureBoard;
import kr.co.nft.lms.vo.LectureFile;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LectureBoardController {
	@Autowired LectureBoardService lectureBoardService;
	
	//LectureBoard 입력폼
	@GetMapping("/teacher/lectureBoard/addLectureBoard")
	public String addLectureBoard() {
		return "/lectureBoard/addLectureBoard";
	}
	
	//LectureBoard 입력액션
	@PostMapping("/teacher/lectureBoard/addLectureBoard")
	public String addLectureBoard(HttpServletRequest request, LectureBoard lectureBoard, LectureFile lectureFile) {
		//파일 저장 경로 설정
		URL pathUrl = this.getClass().getResource("/static/");
		String path = pathUrl.getPath() + "/uploadFile/lectureFile";
		log.debug(A.S + "[LectureBoardController.addLectureBoard] path : "+ path + A.R);
		log.debug(A.S + "[LectureBoardController.addLectureBoard.param] lectureBoard : "+ lectureBoard + A.R);
		log.debug(A.S + "[LectureBoardController.addLectureBoard.param] lectureFile : "+ lectureFile + A.R);
		
		int row = lectureBoardService.addLectureBoard(lectureBoard, lectureFile, path);
		log.debug(A.S + "[LectureBoardController.addLectureBoard] row : "+ row + A.R);
		//row 가 0 이면 입력실패
		if(row == 0) {
			log.debug(A.S + "[LectureBoardController.addLectureBoard] 입력실패 " + A.R);
			return "redirect:/teacher/lectureBoard/addLectureBoard?msg=fail";
		}
		//입력 성공시
		log.debug(A.S + "[LectureBoardController.addLectureBoard] 입력성공 " + A.R);
		return "redirect:/all/lectureBoard/getLectureBoardListByPage";
	}
	
	//LectureBoard 목록보기
	@GetMapping("/all/lectureBoard/getLectureBoardListByPage")
	public String getLectureBoardListByPage(Model model
										,HttpSession session
										,@RequestParam(name="currentPage", defaultValue = "1") int currentPage
										,@RequestParam(name="rowPerPage", defaultValue = "10") int rowPerPage
										) {
		//요청값 디버깅
		log.debug(A.S + "[LectureBoardController.getLectureBoardListByPage.param] currentPage : "+ currentPage + A.R);
		log.debug(A.S + "[LectureBoardController.getLectureBoardListByPage.param] rowPerPage : "+ rowPerPage + A.R);
		
		//세션에 lecture 정보 요청
		Lecture lecture = (Lecture)session.getAttribute("sessionLecture");
		
		Map<String, Object> lectureBoardRowReturnMap = lectureBoardService.getLectureBoardListByPage(currentPage, rowPerPage, lecture);
		log.debug(A.S + "[LectureBoardController.getLectureBoardListByPage.param] lectureBoardRowReturnMap : "+ lectureBoardRowReturnMap + A.R);
		model.addAttribute("lectureBoardList", lectureBoardRowReturnMap.get("lectureBoardList"));
		model.addAttribute("lastPage", lectureBoardRowReturnMap.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		log.debug(A.S + "[LectureBoardController.getLectureBoardListByPage] model : "+ model + A.R);
		
		return "/lectureBoard/getLectureBoardListByPage";
	}
	
	//LectureBoard 상세보기
	@GetMapping("/all/lectureBoard/getLectureBoardOne")
	public String getLectureBoardOne(Model model
									,HttpSession session
									,@RequestParam(name="lectureBoardNo") int lectureBoardNo) {
		//요청값 디버깅
		log.debug(A.S + "[LectureBoardController.getLectureBoardOne.param] lectureBoardNo : "+ lectureBoardNo + A.R);
		//세션에 lecture 정보 요청
		Lecture lecture = (Lecture)session.getAttribute("sessionLecture");
		log.debug(A.S + "[LectureBoardController.getLectureBoardOne.session] lecture : "+ lecture + A.R);
		
		Map<String, Object> lectureBoardOneReturnMap = lectureBoardService.getLectureBoardOne(lectureBoardNo);
		log.debug(A.S + "[LectureBoardController.getLectureBoardOne] lectureBoardOneReturnMap : "+ lectureBoardOneReturnMap + A.R);
		
		model.addAttribute("lectureBoard", lectureBoardOneReturnMap.get("lectureBoard"));
		model.addAttribute("lectureFileList", lectureBoardOneReturnMap.get("lectureFileList"));
		log.debug(A.S + "[LectureBoardController.getLectureBoardOne] model : "+ model + A.R);
		
		return "/lectureBoard/getLectureBoardOne";
	}
	
	//LectureBoard 수정폼
	@GetMapping("/teacher/lectureBoard/modifyLectureBoard")
	public String modifyLectureBoard(Model model
									,HttpSession session
									,@RequestParam(name="lectureBoardNo") int lectureBoardNo) {
		log.debug(A.S + "[LectureBoardController.modifyLectureBoard.param] lectureBoardNo : "+ lectureBoardNo + A.R);
		//세션에 Lecture 정보요청
		Lecture lecture = (Lecture)session.getAttribute("sessionLecture");
		log.debug(A.S + "[LectureBoardController.modifyLectureBoard.session] lecture : "+ lecture + A.R);
		
		Map<String, Object> lectureBoardOneReturnMap = lectureBoardService.getLectureBoardOne(lectureBoardNo);
		log.debug(A.S + "[LectureBoardController.modifyLectureBoard] lectureBoardOneReturnMap : "+ lectureBoardOneReturnMap + A.R);
		
		model.addAttribute("lectureBoard", lectureBoardOneReturnMap.get("lectureBoard"));
		model.addAttribute("lectureFileList", lectureBoardOneReturnMap.get("lectureFileList"));
		log.debug(A.S + "[LectureBoardController.modifyLectureBoard] model : "+ model + A.R);
		
		return"/lectureBoard/modifyLectureBoard";		
	}
	
	//LectureBoard 수정 액션
	@PostMapping("/teacher/lectureBoard/modifyLectureBoard")
	public String modifyLectureBoard(HttpServletRequest request, LectureBoard lectureBoard, LectureFile lectureFile) {
		//파일 저장경로 설정
		URL pathUrl = this.getClass().getResource("/static/");
		String path = pathUrl.getPath()+"/uploadFile/lectureFile/";
		log.debug(A.S + "[LectureBoardController.modifyLectureBoard] path : "+ path + A.R);
		log.debug(A.S + "[LectureBoardController.modifyLectureBoard.param] lectureBoard : "+ lectureBoard + A.R);
		log.debug(A.S + "[LectureBoardController.modifyLectureBoard.param] lectureFile : "+ lectureFile + A.R);
		
		int row = lectureBoardService.modifyLectureBoard(lectureBoard, lectureFile, path);
		log.debug(A.S + "[LectureBoardController.modifyLectureBoard] row : "+ row + A.R);
		
		//row 가 0이면 입력실패
		if(row == 0) {
			log.debug(A.S + "[LectureBoardController.modifyLectureBoard] 수정실패 " + A.R);
			return "redirect:/teacher/lectureBoard/modifyLectureBoard?msg=fail";
		}
		//입력 성공
		log.debug(A.S + "[LectureBoardController.modifyLectureBoard] 수정성공 " + A.R);
		return "redirect:/all/lectureBoard/getLectureBoardOne?lectureBoardNo=" + lectureBoard.getLectureBoardNo();
	}
	
	//LectureBoard 삭제폼
	@GetMapping("/teacher/lectureBoard/removeLectureBoard")
	public String removeLectureBoard(Model model
									,@RequestParam(name="lectureBoardNo") int lectureBoardNo) {
		log.debug(A.S + "[LectureBoardController.removeLectureBoard.param] lectureBoardNo : "+ lectureBoardNo + A.R);
		Map<String, Object> lectureBoardOneReturnMap = lectureBoardService.getLectureBoardOne(lectureBoardNo);
		log.debug(A.S + "[LectureBoardController.removeLectureBoard.param] lectureBoardOneReturnMap : "+ lectureBoardOneReturnMap + A.R);
		model.addAttribute("lectureBoard", lectureBoardOneReturnMap.get("lectureBoard"));
		return "/lectureBoard/removeLectureBoard";
	}
	
	//LectureBoard 삭제 액션
	@PostMapping("/teacher/lectureBoard/removeLectureBoard")
	public String removeLectureBoard(HttpServletRequest request, int lectureBoardNo) {
		URL pathUrl = this.getClass().getResource("/static/");
		String path = pathUrl.getPath()+"/uploadFile/lectureFile/";
		log.debug(A.S + "[LectureBoardController.removeLectureBoard] path : "+ path + A.R);
		log.debug(A.S + "[LectureBoardController.removeLectureBoard.param] lectureBoardNo : "+ lectureBoardNo + A.R);
		
		int row = lectureBoardService.removeLectureBoard(lectureBoardNo, path);
		log.debug(A.S + "[LectureBoardController.removeLectureBoard.param] row : "+ row + A.R);
		
		//row 가 0이면 삭제실패
		if(row == 0) {
			log.debug(A.S + "[LectureBoardController.removeLectureBoard] 삭제(블라인드처리)실패 " + A.R);
			return "redirect:/teacher/lectureBoard/removeLectureBoard?msg=fail";
		}
		//삭제 성공시
		log.debug(A.S + "[LectureBoardController.removeLectureBoard] 삭제(블라인드처리)실패 " + A.R);
		return "redirect:/all/lectureBoard/getLectureBoardListByPage";
	}
	
	//LectureFile 삭제 액션
	@GetMapping("/teacher/lectureBoard/removeLectureFile")
	public String removeLectureFile(HttpServletRequest request, String lectureFileName, int lectureFileNo, int lectureBoardNo) {
		URL pathUrl = this.getClass().getResource("/static/");
		String path = pathUrl.getPath()+"/uploadFile/lectureFile/";
		log.debug(A.S + "[LectureBoardController.removeLectureFile] path : "+ path + A.R);
		log.debug(A.S + "[LectureBoardController.removeLectureFile.param] lectureFileName : "+ lectureFileName + A.R);
		log.debug(A.S + "[LectureBoardController.removeLectureFile.param] lectureFileNo : "+ lectureFileNo + A.R);
		log.debug(A.S + "[LectureBoardController.removeLectureFile.param] lectureBoardNo : "+ lectureBoardNo + A.R);
		
		int row = lectureBoardService.removeLectureFile(lectureBoardNo, lectureFileName, lectureFileNo, path);
		log.debug(A.S + "[LectureBoardController.removeLectureFile] row : " + row + A.R);
		//row가 0 이면 삭제실패
		if(row==0) {
			log.debug(A.S + "[LectureBoardController.removeLectureFile] lectureFile삭제 실패"+ A.R);
			return "redirect:/teacher/lectureBoard/removeLectureFile?msg=fail";
		}
		//삭제성공 했을 경우
		log.debug(A.S + "[LectureBoardController.removeLectureFile] lectureFile삭제 성공"+ A.R);
		return "redirect:/teacher/lectureBoard/modifyLectureBoard?lectureBoardNo=" + lectureBoardNo;
		
	}
}