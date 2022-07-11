package kr.co.nft.lms.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.nft.lms.service.HomeworkService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HomeworkRestController {
	@Autowired HomeworkService homeworkService;
	
	@GetMapping("/rest/all/homework/getHomeworkChart")
	public List<Map<String, Object>> getHomeworkChart(){
		List<Map<String, Object>> homeworkChartList = homeworkService.getHomeworkChart();
		return homeworkChartList;
	}
	
	@GetMapping("/rest/all/homework/getHomeworkCntChart")
	public List<Map<String, Object>> getHomeworkCntChart(){
		List<Map<String, Object>> homeworkCntChartList = homeworkService.getHomeworkCntChart();
		return homeworkCntChartList;
	}
	
	
}
