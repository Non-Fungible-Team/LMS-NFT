package kr.co.nft.lms.rest;

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
	
	
}
