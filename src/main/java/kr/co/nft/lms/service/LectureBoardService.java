package kr.co.nft.lms.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.co.nft.lms.mapper.LectureBoardMapper;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Comment;
import kr.co.nft.lms.vo.Lecture;
import kr.co.nft.lms.vo.LectureBoard;
import kr.co.nft.lms.vo.LectureFile;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class LectureBoardService {
	@Autowired //스프링에 의해 객체 주입되도록
	LectureBoardMapper lectureBoardMapper;
	
	//LectureBoard 입력 액션
	public int addLectureBoard(LectureBoard lectureBoard, LectureFile lectureFile, String path) {
		log.debug(A.S + "[LectureBoardService.addLectureBoard.param] lectureBoard : "+ lectureBoard + A.R);
		log.debug(A.S + "[LectureBoardService.addLectureBoard.param] lectureFile : "+ lectureFile + A.R);
		log.debug(A.S + "[LectureBoardService.addLectureBoard.param] path : "+ path + A.R);
		int row1 = lectureBoardMapper.insertLectureBoardInBoard(lectureBoard);
		log.debug(A.S + "[LectureBoardService.addLectureBoard.boardNo] row1 : "+ row1 + A.R);
		int row = lectureBoardMapper.insertLectureBoard(lectureBoard);
		log.debug(A.S + "[LectureBoardService.addLectureBoard] row : "+ row + A.R);
		//파일저장 성공 횟수를 셀 디버깅
		int lectureFileRow = 0;
		//강의게시판에 입력한 파일이 있고 row=1일경우 아래코드 실행
		if(lectureFile.getLectureFileList() != null && lectureFile.getLectureFileList().get(0).getSize() > 0 && row ==1) {
			log.debug(A.S + "[LectureBoardService.addLectureBoard] 첨부할 파일이 있습니다." + A.R); 
			log.debug(A.S + "[LectureBoardService.addLectureBoard] 파일 저장 해야되는 갯수 getLectureFileList().size() : "+ lectureFile.getLectureFileList().size() + A.R);
			for(MultipartFile mf : lectureFile.getLectureFileList()) {
				//mf -> lectureFile
				LectureFile lectureFileOne = new LectureFile();
				//파일 원래의 이름 요청
				String lectureFileOriginName = mf.getOriginalFilename();
				//originName 에서 마지막 . 뒤에 글자들과 같이 저장(ex: .jpg)
				String ext = lectureFileOriginName.substring(lectureFileOriginName.lastIndexOf("."));
				//파일을 저장할때 사용할 중복되지 않는 새로운 이름 필요 (UUID API 사용)
				String lectureFileName = UUID.randomUUID().toString();
				lectureFileName = lectureFileName.replace("-", "");
				lectureFileName = lectureFileName + ext;
				
				lectureFileOne.setLectureBoardNo(lectureBoard.getLectureBoardNo());
				lectureFileOne.setLectureFileName(lectureFileName);
				lectureFileOne.setLectureFileOriginName(lectureFileOriginName);
				lectureFileOne.setLectureFileType(mf.getContentType());
				lectureFileOne.setLectureFileSize(mf.getSize());
				log.debug(A.S + "[LectureBoardService.addLectureBoard] 저장될 파일 lectureFileOne: "+lectureFileOne + A.R); 
				//입력 및 성공 횟수 저장
				lectureFileRow = lectureFileRow + lectureBoardMapper.insertlectureFile(lectureFileOne);
				
				try {
					//경로+이름으로 파일 저장
					mf.transferTo(new File(path+lectureFileName));
				} catch (Exception e) {
					e.printStackTrace();
					// 새로운 예외 발생시켜야지만 @Transactional 작동을 위해
					throw new RuntimeException(); // RuntimeException은 예외처리를 하지 않아도 컴파일된다
				}
			}
		}
		log.debug(A.S + "[LectureBoardService.addLectureBoard.param] 파일 저장 성공 갯수 lectureFileRow : "+ lectureFileRow + A.R); 
		return row;
	}
	
	//LectureBoard 목록 보기
	public Map<String, Object> getLectureBoardListByPage(int currentPage, int rowPerPage, Lecture lecture){
		log.debug(A.S + "[LectureBoardService.getLectureBoardListByPage.param] currentPage : " + currentPage + A.R); 
		log.debug(A.S + "[LectureBoardService.getLectureBoardListByPage.param] rowPerPage : " + rowPerPage + A.R); 
		log.debug(A.S + "[LectureBoardService.getLectureBoardListByPage.param] lecture : " + lecture + A.R); 
		
		//1)controller에서 넘어온 매개변수값을 가공 후 매퍼 호출
		int beginRow = (currentPage - 1) * rowPerPage;
		log.debug(A.S + "[LectureBoardService.getLectureBoardListByPage] beginRow : " + beginRow + A.R); 
		Map<String, Object> lectureBoardRowMap = new HashMap<>();
		lectureBoardRowMap.put("rowPerPage", rowPerPage);
		lectureBoardRowMap.put("beginRow", beginRow);
		lectureBoardRowMap.put("lectureNo", lecture.getLectureNo());
		log.debug(A.S + "[LectureBoardService.getLectureBoardListByPage] lectureBoardRowMap : " + lectureBoardRowMap + A.R); 
		List<LectureBoard> lectureBoardList = lectureBoardMapper.selectLectureBoardListByPage(lectureBoardRowMap);
		log.debug(A.S + "[LectureBoardService.getLectureBoardListByPage] lectureBoardList : " + lectureBoardList + A.R);
		
		//2)매퍼에서 반환된 값을 가공, controller에 반환
		int totalCount = lectureBoardMapper.selectLectureBoardListTotalCount(lecture.getLectureNo());
		log.debug(A.S + "[LectureBoardService.getLectureBoardListByPage] totalCount : " + totalCount + A.R);
		int lastPage = (int)Math.ceil((double)totalCount / (double)rowPerPage);
		log.debug(A.S + "[LectureBoardService.getLectureBoardListByPage] lastPage : " + lastPage + A.R);
		
		Map<String, Object> lectureBoardRowReturnMap = new HashMap<>();
		lectureBoardRowReturnMap.put("lectureBoardList", lectureBoardList);
		lectureBoardRowReturnMap.put("lastPage", lastPage);
		log.debug(A.S + "[LectureBoardService.getLectureBoardListByPage] lectureBoardRowReturnMap : " + lectureBoardRowReturnMap + A.R);
		return lectureBoardRowReturnMap;
	}
	
	//Lecture 상세보기(File추가) + 수정폼 + ■■■■■■■■■■■■■■comment List■■■■■■■■■■■■■■
	public Map<String, Object> getLectureBoardOne(int lectureBoardNo /*, Map<String, Object> map*/){
		log.debug(A.S + "[LectureBoardService.getLectureBoardOne.param] lectureBoardNo : " + lectureBoardNo + A.R);
		LectureBoard lectureBoard = lectureBoardMapper.selectLectureBoardOne(lectureBoardNo);
		log.debug(A.S + "[LectureBoardService.getLectureBoardOne] lectureBoard : " + lectureBoard + A.R);
/*
		//comment List 페이징
		//1)컨트롤러 입력값 가공
		int commentCurrentPage = (int)map.get("commentCurrentPage");
		int rowPerPage = (int)map.get("rowPerPage");
		int beginRow = (commentCurrentPage - 1) * rowPerPage;
		//Map으로 묶어줌
		Map<String, Object> commentParamMap = new HashMap<>();
		commentParamMap.put("beginRow", beginRow);
		commentParamMap.put("rowPerPage", rowPerPage);
		//2)매퍼의 반환값 가공
		List<Comment> commentList = lectureBoardMapper.selectCommentListByPage(commentParamMap);
		Map<String, Object> commentReturnMap = new HashMap<>();
		int commentTotalCount = lectureBoardMapper.selectCommentTotalCount(lectureBoardNo);
		int commentLastPage = commentTotalCount /(int)(map.get("rowPerPage"));
		if(commentTotalCount % (int)(map.get("rowPerPage")) != 0) {
			commentLastPage = commentLastPage + 1;
		}
		commentReturnMap.put("lectureBoard", lectureBoard);
		commentReturnMap.put("commentList", commentList);
		commentReturnMap.put("commentTotalCount", commentTotalCount);
*/		
		
		//File Part
		List<LectureFile> lectureFileList = lectureBoardMapper.selectLectureFileOneList(lectureBoardNo);
		log.debug(A.S + "[LectureBoardService.getLectureBoardOne] lectureFileList : " + lectureFileList + A.R);
		
		//Map으로 묶어줌
		Map<String, Object> lectureBoardReturnMap = new HashMap<>();
		lectureBoardReturnMap.put("lectureBoard", lectureBoard);
		lectureBoardReturnMap.put("lectureFileList", lectureFileList);
		log.debug(A.S + "[LectureBoardService.getLectureBoardOne] lectureBoardReturnMap : " + lectureBoardReturnMap + A.R);
		
		return lectureBoardReturnMap;
	}
	
	//Lecture 수정 액션
	public int modifyLectureBoard(LectureBoard lectureBoard, LectureFile lectureFile, String path) {
		log.debug(A.S + "[LectureBoardService.modifyLectureBoard.param] lectureBoard : " + lectureBoard + A.R);
		log.debug(A.S + "[LectureBoardService.modifyLectureBoard.param] lectureFile : " + lectureFile + A.R);
		log.debug(A.S + "[LectureBoardService.modifyLectureBoard.param] path : " + path + A.R);
		int row = lectureBoardMapper.updateLectureBoardInBoard(lectureBoard);
		log.debug(A.S + "[LectureBoardService.modifyLectureBoard] row : " + row + A.R);
		
		//파일저장 성공 횟수를 셀 디버깅
		int lectureFileModifyRow = 0;
		//강의게시판에 입력한 파일이 있고 row==1 일경우 아래코드실행
		if(lectureFile.getLectureFileList() != null && lectureFile.getLectureFileList().get(0).getSize() > 0 && row == 1) {
			log.debug(A.S + "[LectureBoardService.modifyLectureBoard] 첨부할 파일이 있습니다." + A.R); 
			log.debug(A.S + "[LectureBoardService.modifyLectureBoard.param] 파일 저장 해야되는 갯수 LectureFileList().size() : "+ lectureFile.getLectureFileList().size() + A.R);
			for(MultipartFile mf : lectureFile.getLectureFileList()) {
				//mf -> lectureFile
				LectureFile lectureFileModify = new LectureFile();
				//파일 원래이름 요청
				String lectureFileOriginName = mf.getOriginalFilename();
				// originName 에서 마지막 .뒤에 글자와 함께 저장 (ex : .jpg)
				String ext = lectureFileOriginName.substring(lectureFileOriginName.lastIndexOf("."));
				//파일을 저장할때 사용 할 중복되지 않는 새로운 이름 필요 (UUID API 사용)
				String lectureFileName = UUID.randomUUID().toString();
				lectureFileName = lectureFileName.replace("-", "");
				lectureFileName = lectureFileName + ext;
				
				lectureFileModify.setLectureBoardNo(lectureBoard.getLectureBoardNo());
				lectureFileModify.setLectureFileName(lectureFileName);
				lectureFileModify.setLectureFileOriginName(lectureFileOriginName);
				lectureFileModify.setLectureFileType(mf.getContentType());
				lectureFileModify.setLectureFileSize(mf.getSize());
				log.debug(A.S + "[LectureBoardService.modifyLectureBoard] 저장될 파일 lectureFileModify: " + lectureFileModify + A.R); 
				
				//입력 및 성공 횟수 저장
				lectureFileModifyRow = lectureFileModifyRow + lectureBoardMapper.insertlectureFile(lectureFileModify);
				
				try {
					//경로+이름으로 파일 저장
					mf.transferTo(new File(path+lectureFileName));
				} catch (Exception e) {
					e.printStackTrace();
					// 새로운 예외 발생시켜야지만 @Transactional 작동을 위해
					throw new RuntimeException(); // RuntimeException은 예외처리를 하지 않아도 컴파일된다
				}
			}
		}
		log.debug(A.S + "[LectureBoardService.modifyLectureBoard.param] 파일 저장 성공 갯수 lectureFileModifyRow : "+ lectureFileModifyRow + A.R); 
		return row;
	}
	
	//LectureBoard + File 삭제
	public int removeLectureBoard(int lectureBoardNo, String path) {
		log.debug(A.S + "[LectureBoardService.removeLectureBoard.param] lectureBoardNo : " + lectureBoardNo + A.R); 
		log.debug(A.S + "[LectureBoardService.removeLectureBoard.param] path : " + path + A.R); 
	
		int row = -1;
		//1)저장 장치의 파일을 삭제 -> 파일이름
		List<String> lectureFileList = lectureBoardMapper.selectLectureFileNameList(lectureBoardNo);
		log.debug(A.S + "[LectureBoardService.removeLectureBoard] lectureFileList : " + lectureFileList + A.R); 
		
		for(String fileName : lectureFileList) {
			File f = new File(path + fileName);
			// 만약 파일이 존재한다면
			if(f.exists()) {
				//삭제한다
				f.delete();
			}
		}
		//2)DB 삭제 (파일삭제 -> 공지삭제)
		lectureBoardMapper.deleteLectureFileList(lectureBoardNo);
		row = lectureBoardMapper.deleteLectureBoard(lectureBoardNo);
		return row;
	}
	

	//File 만 삭제
	public int removeLectureFile(int lectureBoardNo, String lectureFileName,int lectureFileNo, String path) {
		log.debug(A.S + "[LectureBoardService.removeLectureFile.param] lectureBoardNo : "+ lectureBoardNo + A.R); 
		log.debug(A.S + "[LectureBoardService.removeLectureFile.param] lectureFileName : "+ lectureFileName + A.R); 
		log.debug(A.S + "[LectureBoardService.removeLectureFile.param] lectureFileNo : "+ lectureFileNo + A.R); 
		log.debug(A.S + "[LectureBoardService.removeLectureFile.param] path : "+ path + A.R); 
		int row = -1;
		
		//1) 저장장치의 파일을 삭제 -> 파일이름,경로 필요함
		//파일 삭제 실패시 @Transactional 작동안함 -> try,catch로 파일삭제 실패시 runtimeException 발생 시킴
		try {
			File fo = new File(path + lectureFileName);
			// 만약 파일이 존재한다면
			if(fo.exists()) {
				//삭제한다
				fo.delete();
			}
			//경로+이름으로 파일 저장
		} catch (Exception e) {
			e.printStackTrace();
			// 새로운 예외 발생시켜야지만 @Transactional 작동을 위해
			throw new RuntimeException(); // RuntimeException은 예외처리를 하지 않아도 컴파일된다
		}
		//2) DB 삭제 (파일삭제)
		row = lectureBoardMapper.deleteLectureFileOne(lectureFileNo);		
		return row;
	}
	//■■■■■■■■■■■■■■■■comment■■■■■■■■■■■■■■■■
	
	//comment 입력액션
	public int addComment(Comment comment) {
		log.debug(A.S + "[LectureBoardService.addComment.param] comment : "+ comment + A.R); 
		int row = lectureBoardMapper.insertComment(comment);
		return row;
	}
	
	
}
