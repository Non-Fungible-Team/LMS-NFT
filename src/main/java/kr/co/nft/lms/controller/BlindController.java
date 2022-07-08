package kr.co.nft.lms.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.nft.lms.service.BlindService;
import kr.co.nft.lms.util.A;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BlindController {
	@Autowired BlindService blindService;
	
	//Blind 목록보기
	@GetMapping("/manager/blind/blindList")
	public String getBlindListByPage(Model model
										,@RequestParam(name="noticeBlindCurrentPage", defaultValue = "1") int noticeBlindCurrentPage
										,@RequestParam(name="noticeBlindRowPerPage", defaultValue = "5") int noticeBlindRowPerPage
										,@RequestParam(name="suggestBlindCurrentPage", defaultValue = "1") int suggestBlindCurrentPage
										,@RequestParam(name="suggestBlindRowPerPage", defaultValue = "5") int suggestBlindRowPerPage
										,@RequestParam(name="lectureBoardBlindCurrentPage", defaultValue = "1") int lectureBoardBlindCurrentPage
										,@RequestParam(name="lectureBoardBlindRowPerPage", defaultValue = "5") int lectureBoardBlindRowPerPage) {
		log.debug(A.S + "[BlindController.getBlindListByPage.param] noticeBlindCurrentPage : " + noticeBlindCurrentPage + A.R);
		log.debug(A.S + "[BlindController.getBlindListByPage.param] noticeBlindRowPerPage : " + noticeBlindRowPerPage + A.R);
		log.debug(A.S + "[BlindController.getBlindListByPage.param] suggestBlindCurrentPage : " + suggestBlindCurrentPage + A.R);
		log.debug(A.S + "[BlindController.getBlindListByPage.param] suggestBlindRowPerPage : " + suggestBlindRowPerPage + A.R);
		log.debug(A.S + "[BlindController.getBlindListByPage.param] lectureBoardBlindCurrentPage : " + lectureBoardBlindCurrentPage + A.R);
		log.debug(A.S + "[BlindController.getBlindListByPage.param] lectureBoardBlindRowPerPage : " + lectureBoardBlindRowPerPage + A.R);
		
		Map<String, Object> blindRowReturnMap = blindService.getBlindListByPage(noticeBlindCurrentPage, noticeBlindRowPerPage, suggestBlindCurrentPage, suggestBlindRowPerPage, lectureBoardBlindCurrentPage, lectureBoardBlindRowPerPage);
		log.debug(A.S + "[BlindController.getBlindListByPage] blindRowReturnMap : " + blindRowReturnMap + A.R);
		
		model.addAttribute("noticeBlindList", blindRowReturnMap.get("noticeBlindList"));
		model.addAttribute("noticeBlindLastPage", blindRowReturnMap.get("noticeBlindLastPage"));
		model.addAttribute("noticeBlindCurrentPage", noticeBlindCurrentPage);
		model.addAttribute("suggestBlindList", blindRowReturnMap.get("suggestBlindList"));
		model.addAttribute("suggestBlindLastPage", blindRowReturnMap.get("suggestBlindLastPage"));
		model.addAttribute("suggestBlindCurrentPage", suggestBlindCurrentPage);
		model.addAttribute("lectureBoardBlindList", blindRowReturnMap.get("lectureBoardBlindList"));
		model.addAttribute("lectureBoardBlindLastPage", blindRowReturnMap.get("lectureBoardBlindLastPage"));
		model.addAttribute("lectureBoardBlindCurrentPage", lectureBoardBlindCurrentPage);
		log.debug(A.S + "[BlindController.getBlindListByPage] model : " + model + A.R);
		
		return "/blind/blindList";
	}
}