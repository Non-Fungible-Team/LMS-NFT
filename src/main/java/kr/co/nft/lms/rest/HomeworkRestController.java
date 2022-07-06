package kr.co.nft.lms.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.nft.lms.service.HomeworkService;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.HomeworkSubmit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HomeworkRestController {
	@Autowired HomeworkService homeworkService;
	
	// 학생 과제 파일 삭제 액션
		@PostMapping("/student/homework/removeHomeworkSubmitFileOne")
		public Map<String, Object> removeHomeworkSubmitFileOne(HomeworkSubmit homeworkSubmit
											,@RequestParam(name="homeworkSubmitFileNo") int homeworkSubmitFileNo) {
			int row = homeworkService.removeHomeworkSubmitFileOne(homeworkSubmitFileNo);
			if(row ==1) {
				log.debug(A.Q+"파일 삭제 성공"+A.R);
			} else {
				log.debug(A.Q+"파일 삭제 실패" + A.R);
			}
			
			Map<String, Object> map = new HashMap<>();
			map.put("row", row);
			return map;
			
			// return "redirect:/homework/modifyHomeworkSubmit?homeworkSubmitNo=" + homeworkSubmit.getHomeworkSubmitNo();
		}
	
	
}
