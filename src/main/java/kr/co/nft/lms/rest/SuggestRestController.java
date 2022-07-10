package kr.co.nft.lms.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.nft.lms.service.SuggestService;
import kr.co.nft.lms.util.A;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SuggestRestController {
	@Autowired SuggestService suggestService;
	@GetMapping("/rest/all/suggest/selectSuggestCnt")
	public List<Map<String,Object>> selectSuggestCnt(){
		List<Map<String,Object>> suggestChartList = suggestService.selectSuggestCnt();
		log.debug(A.S + "[SuggestRestController.selectSuggestCnt] suggestChartList : "+ suggestChartList + A.R);
		return suggestChartList;
	}
}
