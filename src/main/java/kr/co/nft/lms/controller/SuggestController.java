package kr.co.nft.lms.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.nft.lms.service.SuggestService;
import kr.co.nft.lms.util.A;
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
	
	//Suggest 목록보기
	@GetMapping("/all/suggest/getSuggestListByPage")
	public String getSuggestListByPage(Model model
									,@RequestParam(name="currentPage", defaultValue = "1") int currentPage
									,@RequestParam(name="rowPerPage", defaultValue = "10") int rowPerPage) {
		log.debug(A.S + "[SuggestController.getSuggestListByPage.param] currentPage : " + currentPage + A.R);
		log.debug(A.S + "[SuggestController.getSuggestListByPage.param] rowPerPage : " + rowPerPage + A.R);
		
		Map<String, Object> suggestRowRetrunMap = suggestService.getSuggestListByPage(currentPage, rowPerPage);
		log.debug(A.S + "[SuggestController.getSuggestListByPage] suggestRowRetrunMap : "+ A.R);
		
		model.addAttribute("suggestList", suggestRowRetrunMap.get("suggestList"));
		model.addAttribute("lastPage", suggestRowRetrunMap.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		log.debug(A.S + "[SuggestController.getSuggestListByPage] model : " + model + A.R);
		
		return "/suggest/getSuggestListByPage";
	}
	
	//Suggest 상세보기
	
	
	//Suggest 수정폼
	
	//Suggest 수정액션
	
	//Suggest 삭제액션
	
}
