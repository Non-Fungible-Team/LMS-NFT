package kr.co.nft.lms.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.nft.lms.service.LectureBoardService;
import kr.co.nft.lms.util.A;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class LectureBoardRestController {
	@Autowired LectureBoardService lectureBoardService;
	
	@GetMapping("/rest/teacher/lectureBoard/removeLectureFile")
	public Map<String,Object> removeLectureFile(HttpServletRequest request, String lectureFileName, int lectureFileNo, int lectureBoardNo) {
		String path = request.getServletContext().getRealPath("/uploadFile/lectureFile/");
		log.debug(A.S + "[LectureBoardRestController.removeLectureFile] path : "+ path + A.R);
		log.debug(A.S + "[LectureBoardRestController.removeLectureFile.param] lectureFileName : "+ lectureFileName + A.R);
		log.debug(A.S + "[LectureBoardRestController.removeLectureFile.param] lectureFileNo : "+ lectureFileNo + A.R);
		log.debug(A.S + "[LectureBoardRestController.removeLectureFile.param] lectureBoardNo : "+ lectureBoardNo + A.R);
		
		int row = lectureBoardService.removeLectureFile(lectureBoardNo, lectureFileName, lectureFileNo, path);
		log.debug(A.S + "[LectureBoardRestController.removeLectureFile] row : " + row + A.R);
		Map<String,Object> map = new HashMap<>();
		map.put("row", row);
		return map;
	}
}
