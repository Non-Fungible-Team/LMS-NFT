package kr.co.nft.lms.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.nft.lms.service.BoardService;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Board;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired BoardService boardService;
	
	//Board 입력폼
	@GetMapping("/addBoard")
	public String addBoard() {
		return "/board/addBoard";
	}
	//Board 입력 액션
	@PostMapping("/addBoard")
	public String addBoard(Board board) {
		log.debug(A.S + "BoardController.addBoard.param : "+ board + A.R);
		int row = boardService.addBoard(board);
		log.debug(A.S + "BoardController.addBoard.row : "+ row + A.R);
		return "redirect:/getBoardByPage";
	}
	
	//Notice 목록보기
	@GetMapping("/getBoardByPage")
	public String getBoardByPage(Model model
								,@RequestParam(name= "currnetPage", defaultValue = "1") int currentPage
								,@RequestParam(name= "rowPerPage", defaultValue = "10") int rowPerPage) {
		Map<String, Object> map = boardService.getBoardByPage(currentPage, rowPerPage);
		/*log.debug(A.S + "BoardController.BoardGetByPage.param : ", currnetPage + A.R);*/
		model.addAttribute("list", map.get("list"));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		
		return "/board/getBoardByPage";
	}
	
	//Notice 상세보기
}
