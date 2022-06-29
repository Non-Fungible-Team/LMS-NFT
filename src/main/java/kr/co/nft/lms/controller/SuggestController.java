package kr.co.nft.lms.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.nft.lms.service.SuggestService;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Member;
import kr.co.nft.lms.vo.Suggest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SuggestController {
	@Autowired SuggestService suggestService;
	
	//Suggest 입력폼
	@GetMapping("/all/suggest/addSuggest")
	public String addSuggest() {
		return "/suggest/addSuggest";
	}
	//Suggest 답글 입력폼(운영자만)
	@GetMapping("/manager/suggest/addSuggest")
	public String addSuggestAnswer(Model model
								,@RequestParam(name="root") int root 
								,@RequestParam(name="suggestSecret") String suggestSecret
								,@RequestParam(name="suggestTitle") String suggestTitle) {
		log.debug(A.S + "[SuggestController.addSuggestAnswer.param] root : "+ root + A.R);
		log.debug(A.S + "[SuggestController.addSuggestAnswer.param] suggestSecret : "+ suggestSecret + A.R);
		log.debug(A.S + "[SuggestController.addSuggestAnswer.param] suggestTitle : "+ suggestTitle + A.R);
		model.addAttribute("root", root);
		model.addAttribute("suggestSecret", suggestSecret);
		model.addAttribute("suggestTitle", suggestTitle);
		log.debug(A.S + "[SuggestController.addSuggestAnswer] model : " + model + A.R);
		return "/suggest/addSuggest";
	}
	
	//Suggest 입력 액션
	@PostMapping("/all/suggest/addSuggest")	
	public String addSuggest(Suggest suggest) {
		log.debug(A.S + "[SuggestController.addSuggest.param] suggest : "+ suggest + A.R);
		int row = suggestService.addSuggest(suggest);
		log.debug(A.S + "[SuggestController.addSuggest] row : "+ row + A.R);
		//row가 0 이면 입력 실패
		if(row==0) {
			log.debug(A.S + "[SuggestController.addSuggest.row] 입력실패"+ A.R);
			return "redirect:/all/suggest/addSuggest?msg=fail";
		}
		//입력성공 했을 경우
		log.debug(A.S + "[SuggestController.addSuggest.row] 입력성공"+ A.R);
		return "redirect:/all/suggest/getSuggestListByPage";
	}
	
	//Suggest 전체 목록보기
	@GetMapping("/all/suggest/getSuggestListByPage")
	public String getSuggestListByPage(Model model
									,HttpSession session
									,@RequestParam(name="currentPage", defaultValue = "1") int currentPage
									,@RequestParam(name="rowPerPage", defaultValue = "10") int rowPerPage
									,@RequestParam(name="rootNullCurrentPage", defaultValue = "1") int rootNullCurrentPage
									,@RequestParam(name="rootNullRowPerPage", defaultValue = "5") int rootNullRowPerPage) {
		log.debug(A.S + "[SuggestController.getSuggestListByPage.param] currentPage : " + currentPage + A.R);
		log.debug(A.S + "[SuggestController.getSuggestListByPage.param] rowPerPage : " + rowPerPage + A.R);
		log.debug(A.S + "[SuggestController.getSuggestListByPage.param] rootNullCurrentPage : " + rootNullCurrentPage + A.R);
		log.debug(A.S + "[SuggestController.getSuggestListByPage.param] rootNullRowPerPage : " + rootNullRowPerPage + A.R);
		//세션에 로그인 정보 요청
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		
		Map<String, Object> suggestRowRetrunMap = suggestService.getSuggestListByPage(currentPage, rowPerPage, loginMember.getMemberLevel(),rootNullCurrentPage,rootNullRowPerPage);
		log.debug(A.S + "[SuggestController.getSuggestListByPage] suggestRowRetrunMap : " + suggestRowRetrunMap + A.R);
		
		model.addAttribute("suggestList", suggestRowRetrunMap.get("suggestList"));
		model.addAttribute("lastPage", suggestRowRetrunMap.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rootNullSuggestList", suggestRowRetrunMap.get("rootNullSuggestList"));
		model.addAttribute("rootNullLastPage", suggestRowRetrunMap.get("rootNullLastPage"));
		model.addAttribute("rootNullCurrentPage", rootNullCurrentPage);
		log.debug(A.S + "[SuggestController.getSuggestListByPage] model : " + model + A.R);
		
		return "/suggest/getSuggestListByPage";
	}
	
	
	//Suggest 상세보기
	@GetMapping("/all/suggest/getSuggestOne")
	public String getSuggestOne(Model model
							,@RequestParam(name="suggestNo") int suggestNo) {
		log.debug(A.S + "[SuggestController.getSuggestOne.param] suggestNo : " + suggestNo + A.R);
		Suggest suggest = suggestService.getSuggestOne(suggestNo);
		log.debug(A.S + "[SuggestController.getSuggestOne] suggest : " + suggest + A.R);
		
		model.addAttribute("suggest", suggest);
		return "/suggest/getSuggestOne";
	}
	
	//Suggest 수정폼
	@GetMapping("/all/suggest/modifySuggest")
	public String modifySuggest(Model model
							,HttpSession session
							,@RequestParam(name="suggestNo") int suggestNo) {
		log.debug(A.S + "[SuggestController.modifySuggest.param] suggestNo : " + suggestNo + A.R);
		//세션에 로그인 정보 요청
		Member loginMember = (Member)session.getAttribute("sessionLoginMember");
		
		Suggest suggest = suggestService.getSuggestOne(suggestNo);
		log.debug(A.S + "[SuggestController.modifySuggest] suggest : " + suggest + A.R);
		model.addAttribute("suggest", suggest);
		log.debug(A.S + "[SuggestController.modifySuggest] model : " + model + A.R);
		//가져온 상세보기가 로그인한 회원의 권한 밖의 게시물이면 list로 redirect
		if(suggest.getMemberId() != loginMember.getMemberId()){
			log.debug(A.S + "[SuggestController.modifySuggest] 권한밖의 suggest게시물 수정 요청" + A.R);
			return "redirect:/all/suggest/getSuggestListByPage";
		}
		return "suggest/modifySuggest";
	}
	
	//Suggest 수정액션
	@PostMapping("/all/suggest/modifySuggest")
	public String modifySuggest(Suggest suggest) {
		log.debug(A.S + "[SuggestController.modifySuggest.param] suggest : " + suggest + A.R);
		int row = suggestService.modifySuggest(suggest);
		log.debug(A.S + "[SuggestController.modifySuggest] row : " + row + A.R);
		//row가 0 이면 입력 실패
		if(row==0) {
			log.debug(A.S + "[SuggestController.modifySuggest.row] 수정실패"+ A.R);
			return "redirect:/all/suggest/modifySuggest?msg=fail";
		}
		//입력성공 했을 경우
		log.debug(A.S + "[SuggestController.modifySuggest.row] 수정성공"+ A.R);
		return "redirect:/all/suggest/getSuggestOne?suggestNo=" + suggest.getSuggestNo();
	}
	
	//Suggest 삭제 폼
	@GetMapping("/all/suggest/removeSuggest")
	public String removeSuggest(Model model
							,@RequestParam(name="suggestNo") int suggestNo) {
		log.debug(A.S + "[SuggestController.removeSuggest.param] suggestNo : " + suggestNo + A.R);
		Suggest suggest = suggestService.getSuggestOne(suggestNo);
		log.debug(A.S + "[SuggestController.removeSuggest] suggest : " + suggest + A.R);
		model.addAttribute("suggest", suggest);
		return "/suggest/removeSuggest";
	}
	
	//Suggest 삭제액션
	@PostMapping("all/suggest/removeSuggest")
	public String removeSuggest(int suggestNo) {
		log.debug(A.S + "[SuggestController.removeSuggest.param] suggestNo : " + suggestNo + A.R);
		int row = suggestService.removeSuggest(suggestNo);
		log.debug(A.S + "[SuggestController.removeSuggest] row : " + row + A.R);
		//row가 0 이면 입력 실패
		if(row==0) {
			log.debug(A.S + "[SuggestController.removeSuggest.row] 삭제(블라인드처리)실패"+ A.R);
			return "redirect:/all/suggest/removeSuggest?msg=fail";
		}
		//입력성공 했을 경우
		log.debug(A.S + "[SuggestController.removeSuggest.row] 삭제(블라인드처리)실패" + A.R);
		return "redirect:/all/suggest/getSuggestListByPage" ;
	}
}
