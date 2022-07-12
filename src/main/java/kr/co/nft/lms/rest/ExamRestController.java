package kr.co.nft.lms.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.nft.lms.service.ExamService;
import kr.co.nft.lms.util.A;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ExamRestController {	
	@Autowired ExamService examService;
	@GetMapping("/rest/all/exam/getExamChart")
	public List<Map<String, Object>> getExamScoreListByPage() {
		List<Map<String, Object>> examChartList = examService.selectExamChart();
		log.debug(A.C + "[ExamRestController.getExamChart.Mapper] examChartList : " + examChartList + A.R);
		
		return examChartList;
	}

}
