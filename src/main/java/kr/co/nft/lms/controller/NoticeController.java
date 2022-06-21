/*package kr.co.nft.lms.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.nft.lms.service.NoticeService;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Board;
import kr.co.nft.lms.vo.Notice;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class NoticeController {
	@Autowired NoticeService noticeService;
	
	//Notice입력폼
	@GetMapping("/manager/notice/addNotice")
	public String addBoard() {
		return "/board/addBoard";
	}
	//Board 입력 액션
	@PostMapping("/manager/addNotice")
	public String addNotice(Notice notice) {
		log.debug(A.S + "NoticeController.addBoard.param : "+ notice + A.R);
		int row = noticeService.addNotice(notice);
		log.debug(A.S + "NoticeController.addNotice.row : "+ row + A.R);
		//row가 0 이면 입력 실패
		if(row==0) {
			log.debug(A.S + "[NoticeController.addNotice.param] 입력실패"+ A.R);
			return "redirect:/manager/notice/addNotice?msg=fail";
		}
		//입력성공 했을 경우
		log.debug(A.S + "[NoticeController.addNotice.param] 입력성공"+ A.R);
		return "redirect:/all/notice/getNoticeListByPage";
	}
	
	//Notice 목록보기
	@GetMapping("/getBoardByPage")
	public String getBoardByPage(Model model
								,@RequestParam(name= "currnetPage", defaultValue = "1") int currentPage
								,@RequestParam(name= "rowPerPage", defaultValue = "10") int rowPerPage) {
		Map<String, Object> returnMap = boardService.getBoardByPage(currentPage, rowPerPage);
		log.debug(A.S + "BoardController.BoardGetByPage.param : ", currnetPage + A.R);
		model.addAttribute("boardList", returnMap.get("boardList"));
		model.addAttribute("lastPage", returnMap.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		
		return "/board/getBoardByPage";
	}
	
	//Notice 상세보기
	@GetMapping("/getBoardOne")
	public String getBoardOne(Model model
							,@RequestParam(name="boardNo") int boardNo) {
		log.debug(A.S + "BoardController.getBoardOne.param boardNo : "+ boardNo + A.R);
		Board getBoardOne = boardService.getBoardOne(boardNo);
		model.addAttribute("board", getBoardOne);
		return "/board/getBoardOne";
	}
	
	//Notice 수정폼
	@GetMapping("/modifyBoard")
	public String modifyBoard(Model model
							,@RequestParam(name="boardNo") int boardNo) {
		log.debug(A.S + "BoardController.modifyBoard.param boardNo : "+ boardNo + A.R);
		Board board = boardService.getBoardOne(boardNo);
		model.addAttribute("board", board);
		return "/board/modifyBoard";
	}
	
	//Notice 수정 액션
	@PostMapping("/modifyBoard")
	public String modifyBoard(Board board) {
		int row = boardService.modifyBoard(board);
		log.debug(A.S + "BoardController.modifyBoard.row : "+ row + A.R);
		return "redirect:/board/getBoardOne?boardNo=" + board.getBoardNo();
	}
	
	
	 * //Notice 삭제 폼
	 * 
	 * @GetMapping("/removeBoard") public String removeBoard(Model model
	 * ,@RequestParam(name="boardNo") int boardNo) { log.debug(A.S +
	 * "BoardController.removeBoard.param.boardNo : "+ boardNo + A.R); Board board =
	 * boardService.getBoardOne(boardNo); model.addAttribute("board", board); return
	 * "/board/removeBoard"; }
	 
	
	//Notice 삭제 액션
	@GetMapping("/removeBoard")
	public String removeBoard(int boardNo) {
		int row = boardService.removeBoard(boardNo);
		
		log.debug(A.S + "BoardController.removeBoard.row : "+ row + A.R);
		return "redirect:/board/getBoardByPage";
	}
}*/
