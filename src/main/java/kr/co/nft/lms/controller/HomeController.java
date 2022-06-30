package kr.co.nft.lms.controller;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.nft.lms.service.HomeService;
import kr.co.nft.lms.service.MemberService;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Lecture;
import kr.co.nft.lms.vo.Manager;
import kr.co.nft.lms.vo.Member;
import kr.co.nft.lms.vo.Student;
import kr.co.nft.lms.vo.Teacher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	//서비스 맵핑
	@Autowired private HomeService homeService;
	@Autowired private MemberService memberService;
	@Autowired private ServletContext application;
	//홈화면을 보여주는 컨트롤러
	
	@GetMapping("/all/home")
	public String home(Model model,
			HttpSession session
			,@RequestParam(name = "currentPage",defaultValue = "1") int currentPage
			,@RequestParam(name = "rowPerPage", defaultValue = "5") int rowPerPage){
		
		log.debug(A.E+"[HomeController.home.param] currentPage"+currentPage+A.R);
		log.debug(A.E+"[HomeController.home.param] rowPerPage"+rowPerPage+A.R);
		//세선에 정보 요청
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		int lectureNo = (int)session.getAttribute("sessionLectureNo");
		
		//수강 목록 출력 부분
		Map<String,Object> returnMap = homeService.getLectureByMemberId(loginMember,currentPage,rowPerPage);
		log.debug(A.E+"[HomeController.home.getLectureByMemberId] returnMap"+returnMap+A.R);
		
		//모델값 저장
		model.addAttribute("lectureList", returnMap.get("lectureList"));
		model.addAttribute("lectureListLastPage", returnMap.get("lectureListLastPage"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", returnMap.get("lectureListLastPage"));
		
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
	//정상적이지 않은 회원이 접속할 Page
	@GetMapping("warning")
	public String warning() {
		
	return "warning";
	}
	//휴면 계정 해제 기능
	@GetMapping("/member/cancelRestingMember")
	public String cancelRestingMember(Model model,HttpSession session) {
		//로그인 여부 확인
		if(session.getAttribute("sessionLoginMember")==null) {
			return "redirect:/login";
			
		}
		//세션에 회원 정보 요청
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		log.debug(A.E+"[MemberController.cancelRestingMember] 휴면 계정 해제 요청 :loginMember"+loginMember+A.R);
		//휴면 계정 이메일 인증번호 요청 기능 서비스 요청 + email값 리턴
		String email = homeService.cancelRestingMember(loginMember);
		log.debug(A.E+"[MemberController.cancelRestingMember] 메일 인증요청 :email"+email+A.R);
		//모델에 이메일  저장
		model.addAttribute("email", email);
		return "warning";
	}
	//휴면 계정 이메일 인증 후
	@PostMapping("/member/cancelRestingMember")
	public String cancelRestingMemberverfiyEmail(Model model,HttpSession session,String email,String verificationCode ) {
		//로그인 여부 확인
		if(session.getAttribute("sessionLoginMember")==null) {
			return "redirect:/login";
		}
		//세션에 정보 확인
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		log.debug(A.E+"[MemberController.cancelRestingMemberverfiyEmail] 휴먼회원 이메일인증확인 요청 :loginMember"+loginMember+A.R);
		//입력한 인증번호 체크
		if(!verificationCode.equals(application.getAttribute(loginMember.getMemberId()+"verify"))) {
			log.debug(A.E+"[MemberController.cancelRestingMemberverfiyEmail] 휴먼회원 이메일인증확인 요청 verificationCode :"+verificationCode+A.R);
			log.debug(A.E+"[MemberController.cancelRestingMemberverfiyEmail] 휴먼회원 이메일인증확인 코드 :verificationCode :"+application.getAttribute(loginMember.getMemberId()+"verify")+A.R);
			//문자가 다를시에 email과 메세지와 함께 다시 돌려보냄
			model.addAttribute("email", email);
			model.addAttribute("msg", "인증코드가 맞지 않습니다.");
			return"warning";
		}
		//성공시에 서비스에 휴면 해제 요청
		log.debug(A.E+"[MemberController.cancelRestingMemberverfiyEmail] 휴먼회원 이메일 인증 성공 :loginMember"+loginMember+A.R);
		///-----서비스 부분 ----/////
		//휴면 해제  성공시 홈으로 redirect
		return "redirect:/all/home";
	}
	
	
	//탈퇴 계정취소 기능
	@GetMapping("member/cancelFreezeMember")
	public String cancelFreezeMember(HttpSession session) {
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		log.debug(A.E+"[MemberController.cancelFreezeMember]탈퇴 취소 요청 loginMember"+loginMember+A.R);
		//~~~~~~~~탈퇴 서비스 추가부분~~~~~~~~
		
		return "redirect:/all/home";
	}
	
	
}
