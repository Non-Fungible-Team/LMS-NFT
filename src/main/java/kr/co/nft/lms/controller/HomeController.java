package kr.co.nft.lms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.nft.lms.service.HomeService;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Lecture;
import kr.co.nft.lms.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	//서비스 맵핑
	@Autowired private HomeService homeService;
	//홈화면을 보여주는 컨트롤러
	
	@GetMapping("/all/home")
	public String home(Model model, HttpSession session){
		//세선에 정보 요청
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		int lectureNo = (int)session.getAttribute("sessionLectureNo");
		//수강 목록 출력 부분
		List<Lecture> lectureList = homeService.getLectureByMemberId(loginMember);
		
		//모델값 저장
		model.addAttribute("lectureList", lectureList);
		model.addAttribute("lectureNo", lectureNo);
		return "home";
	}
	
	@GetMapping("/all/lectureNoController")
	public String lecutreHome(Model model, HttpSession session, @RequestParam(name = "lectureNo", defaultValue = "0") int lectureNo){
		log.debug(A.E+"[HomeController.lectureNoController.param] lectureNo"+lectureNo+A.R);
		// lectureNo가 0일경우 강의 선택 안한 상태로 초기화 후 home으로 리다이렉트
		if(lectureNo==0) {
			session.setAttribute("sessionLectureNo", 0);
			session.removeAttribute("sessionLecture");
			return "redirect:/all/home";
			
		// lectureNo가 0이 아닐경우 요청한 강의정보를 session에 저장
		}else {
		//loecturNo로 lecture 정보를 service에 요청
		Lecture lecture = homeService.getLectureByLectureNo(lectureNo);
		log.debug(A.E+"[HomeController.lectureNoController.getLectureByLectureNo] lecture"+lecture+A.R);
		//강의 정보를 session에 저장
		session.setAttribute("sessionLectureNo", lecture.getLectureNo());
		session.setAttribute("sessionLecture", lecture);
		}
		//강의홈으로 리다이렉트
		return "redirect:/all/lectureHome";
	}
	// lectureHome을 리턴하는 컨트롤러
	@GetMapping("/all/lectureHome")
	public String lecutreHome(){
		//지금은 임시로 homecontroller로 리다이렉트
			return "redirect:/all/home";
	}
	// index 대신 home을 연결해주는 컨트롤러
	@GetMapping("/")
	public String index(){
		return "redirect:/all/home";
	}
	// emptyPage을 리턴하는 임시 컨트롤러
	@GetMapping("/emptyPage")
	public String emptyPage(){
		return "emptyPage";
	}
	
}
