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

import kr.co.nft.lms.mapper.NoticeMapper;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Notice;
import kr.co.nft.lms.vo.NoticeFile;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional 
public class NoticeService {
	@Autowired //스프링에 의해 객체 주입되도록 
	NoticeMapper noticeMapper;
 
	//Notice 입력 액션 
	public int addNotice(Notice notice, NoticeFile noticeFile, String path) { 
		log.debug(A.S + "[NoticeService.addNotice.param] notice : "+ notice + A.R); 
		int row1 = noticeMapper.insertNoticeInBoard(notice);
		log.debug(A.S + "[NoticeService.addNotice.boardNo] row1 : "+ row1 + A.R); 
		int row = noticeMapper.insertNotice(notice);
		log.debug(A.S + "[NoticeService.addNotice.param] row : "+ row + A.R); 
		//파일저장 성공 횟수를 셀 디버깅
		int noticeFileRow = 0;
		//공지사항에 입력한 파일이 있고 위의 공지 추가 코드가 정상적으로 실행되었을경우(row=1) 아래의 코드 실행
		if(noticeFile.getNoticeFileList() != null && noticeFile.getNoticeFileList().get(0).getSize() > 0 && row == 1) {
			log.debug(A.S + "[NoticeService.addNotice] 첨부할 파일이 있습니다." + A.R); 
			log.debug(A.S + "[NoticeService.addNotice.param] 파일 저장 해야되는 갯수 NoticeFileList().size() : "+ noticeFile.getNoticeFileList().size() + A.R); 
			for(MultipartFile mf : noticeFile.getNoticeFileList()) {
				// mf -> Noticefile
				NoticeFile noticeFileOne = new NoticeFile();
				//파일 원래의 이름 요청
				String noticeFileOriginName = mf.getOriginalFilename();
				// originName에서 마지막 .뒤에 글자들 .과 같이 저장(ex: .jpg)
				String ext = noticeFileOriginName.substring(noticeFileOriginName.lastIndexOf("."));
				// 파일을 저장할대 사용할 중복되지않는 새로운 이름 필요(UUID API사용)
				String noticeFileName = UUID.randomUUID().toString();
				noticeFileName = noticeFileName.replace("-","");
				noticeFileName = noticeFileName + ext;
				
				noticeFileOne.setNoticeNo(notice.getNoticeNo());
				noticeFileOne.setNoticeFileName(noticeFileName);
				noticeFileOne.setNoticeFileOriginName(noticeFileOriginName);
				noticeFileOne.setNoticeFileType(mf.getContentType());
				noticeFileOne.setNoticeFileSize(mf.getSize());
				log.debug(A.S + "[NoticeService.addNotice] 저장될 파일 noticeFileOne: "+noticeFileOne + A.R); 
				//입력 및 성공 횟수 저장
				noticeFileRow = noticeFileRow + noticeMapper.insertNoticeFile(noticeFileOne);
				
				try {
					//경로+이름으로 파일 저장
					mf.transferTo(new File(path+noticeFileName));
				} catch (Exception e) {
					e.printStackTrace();
					// 새로운 예외 발생시켜야지만 @Transactional 작동을 위해
					throw new RuntimeException(); // RuntimeException은 예외처리를 하지 않아도 컴파일된다
				}
			}
		}
		log.debug(A.S + "[NoticeService.addNotice.param] 파일 저장 성공 갯수 noticeFileRow : "+ noticeFileRow + A.R); 
		return row;
	}
		

	//Notice 목록 보기 
	public Map<String, Object> getNoticeListByPage(int currentPage, int rowPerPage, int memberLevel){
		log.debug(A.S + "[NoticeService.getNoticeListByPage.param] currentPage : "+ currentPage + A.R);
		log.debug(A.S + "[NoticeService.getNoticeListByPage.param] rowPerPage : "+ rowPerPage + A.R);
		log.debug(A.S + "[NoticeService.getNoticeListByPage.param] memberLevel : "+ memberLevel + A.R);
		
		//1) controller 에서 넘어온 매개변수값을 가공 후 매퍼 호출
		int beginRow = (currentPage -1) * rowPerPage;
		log.debug(A.S + "[NoticeService.getNoticeListByPage] beginRow : "+ beginRow + A.R);
		Map<String, Object> noticeRowMap = new HashMap<>();
		noticeRowMap.put("rowPerPage", rowPerPage); 
		noticeRowMap.put("beginRow", beginRow);
		noticeRowMap.put("memberLevel", memberLevel);
		log.debug(A.S + "[NoticeService.getNoticeListByPage] noticeRowMap : "+ noticeRowMap + A.R);
		List<Notice> noticeList = noticeMapper.selectNoticeListByPage(noticeRowMap);
		log.debug(A.S + "[NoticeService.getNoticeListByPage] noticeList : "+ noticeList + A.R);
  
		//2) 매퍼에서 반환된 값을 가공, controller에 반환 
		int totalCount = noticeMapper.selectNoticeListTotalCount(memberLevel); 
		log.debug(A.S + "[NoticeService.getNoticeListByPage.totalCount] totalCount : "+ totalCount + A.R);
		int lastPage = (int)Math.ceil((double)totalCount/(double)rowPerPage);
		log.debug(A.S + "[NoticeService.getNoticeListByPage.lastPage] lastPage : "+ lastPage + A.R);
  
		Map<String,Object> noticeRowReturnMap = new HashMap<>();
		noticeRowReturnMap.put("noticeList", noticeList);
		noticeRowReturnMap.put("lastPage", lastPage);
		log.debug(A.S + "[NoticeService.getNoticeListByPage.noticeRowReturnMap] noticeRowReturnMap : "+ noticeRowReturnMap + A.R);
		return noticeRowReturnMap;
	}
  
	//Notice 상세보기(File추가) + 수정폼 
	public Map<String, Object> getNoticeOne(int noticeNo){
		log.debug(A.S + "[NoticeService.getNoticeOne.param] noticeNo : "+ noticeNo + A.R); 
		Notice notice = noticeMapper.selectNoticeOne(noticeNo);
		log.debug(A.S + "[NoticeService.getNoticeOne] notice : "+ notice + A.R); 
		
		//File PART
		List<NoticeFile> noticeFileList = noticeMapper.selectNoticeFileOneList(noticeNo);
		log.debug(A.S + "[NoticeService.getNoticeOne] noticeFileList : "+ noticeFileList + A.R); 
		
		//Map으로 묶어줌
		Map<String, Object> noticeOneReturnMap = new HashMap<>();
		noticeOneReturnMap.put("notice", notice);
		noticeOneReturnMap.put("noticeFileList", noticeFileList);
		log.debug(A.S + "[NoticeService.getNoticeOne] noticeOneReturnMap : "+ noticeOneReturnMap + A.R); 
		
		return noticeOneReturnMap;
	}
	  
	//Notice 수정액션 
	public int modifyNotice(Notice notice, NoticeFile noticeFile, String path) {
		log.debug(A.S + "[NoticeService.modifyNotice.param] notice : "+ notice + A.R); 
		int row = noticeMapper.updateNoticeInBoard(notice); 
		log.debug(A.S + "[NoticeService.modifyNotice.row] row : "+ row + A.R); 
		
		
		//파일저장 성공 횟수를 셀 디버깅
		int noticeFileModifyRow = 0;
		//공지사항에 입력한 파일이 있고 위의 공지 추가 코드가 정상적으로 실행되었을경우(row=1) 아래의 코드 실행
		if(noticeFile.getNoticeFileList() != null && noticeFile.getNoticeFileList().get(0).getSize() > 0 && row == 1) {
			log.debug(A.S + "[NoticeService.modifyNotice] 첨부할 파일이 있습니다." + A.R); 
			log.debug(A.S + "[NoticeService.modifyNotice.param] 파일 저장 해야되는 갯수 NoticeFileList().size() : "+ noticeFile.getNoticeFileList().size() + A.R); 
			for(MultipartFile mf : noticeFile.getNoticeFileList()) {
				// mf -> NoticeFile
				NoticeFile noticeFileModify = new NoticeFile();
				//파일 원래의 이름 요청
				String noticeFileOriginName = mf.getOriginalFilename();
				// originName에서 마지막 .뒤에 글자들 .과 같이 저장(ex: .jpg)
				String ext = noticeFileOriginName.substring(noticeFileOriginName.lastIndexOf("."));
				// 파일을 저장할대 사용할 중복되지않는 새로운 이름 필요(UUID API사용)
				String noticeFileName = UUID.randomUUID().toString();
				noticeFileName = noticeFileName.replace("-","");
				noticeFileName = noticeFileName + ext;
				
				noticeFileModify.setNoticeNo(notice.getNoticeNo());
				noticeFileModify.setNoticeFileName(noticeFileName);
				noticeFileModify.setNoticeFileOriginName(noticeFileOriginName);
				noticeFileModify.setNoticeFileType(mf.getContentType());
				noticeFileModify.setNoticeFileSize(mf.getSize());
				log.debug(A.S + "[NoticeService.modifyNotice] 저장될 파일 noticeFileModify: "+noticeFileModify + A.R); 
				//입력 및 성공 횟수 저장
				noticeFileModifyRow = noticeFileModifyRow + noticeMapper.insertNoticeFile(noticeFileModify);
				
				try {
					//경로+이름으로 파일 저장
					mf.transferTo(new File(path+noticeFileName));
				} catch (Exception e) {
					e.printStackTrace();
					// 새로운 예외 발생시켜야지만 @Transactional 작동을 위해
					throw new RuntimeException(); // RuntimeException은 예외처리를 하지 않아도 컴파일된다
				}
			}
		}
		log.debug(A.S + "[NoticeService.modifyNotice.param] 파일 저장 성공 갯수 noticeFileModifyRow : "+ noticeFileModifyRow + A.R); 
		return row;
	}
	
	//Notice + File 삭제
	public int removeNotice(int noticeNo, String path) {
		log.debug(A.S + "[NoticeService.removeNotice.param] noticeNo : "+ noticeNo + A.R); 
		log.debug(A.S + "[NoticeService.removeNotice.param] path : "+ path + A.R); 
		
		int row = -1;
		//1)저장장치의 파일을 삭제 -> 파일이름
		List<String> noticeFileList = noticeMapper.selectNoticeFileNameList(noticeNo);
		log.debug(A.S + "[NoticeService.removeNotice] noticeFileList : "+ noticeFileList + A.R); 
		
		for(String fileName : noticeFileList) {
			File f = new File(path + fileName);
			// 만약 파일이 존재한다면
			if(f.exists()) {
				//삭제한다
				f.delete();
			}
		}
		//2)DB 삭제 (파일삭제 -> 공지삭제)
		noticeMapper.deleteNoticeFileList(noticeNo);
		row = noticeMapper.deleteNotice(noticeNo);
		return row;
	}

	//File 만 삭제
	public int removeNoticeFile(int noticeNo, String noticeFileName,int noticeFileNo, String path) {
		log.debug(A.S + "[NoticeService.removeNoticeFile.param] noticeNo : "+ noticeNo + A.R); 
		log.debug(A.S + "[NoticeService.removeNoticeFile.param] noticeFileName : "+ noticeFileName + A.R); 
		log.debug(A.S + "[NoticeService.removeNoticeFile.param] noticeFileNo : "+ noticeFileNo + A.R); 
		log.debug(A.S + "[NoticeService.removeNoticeFile.param] path : "+ path + A.R); 
		int row = -1;
		
		//1) 저장장치의 파일을 삭제 -> 파일이름,경로 필요함
		//파일 삭제 실패시 @Transactional 작동안함 -> try,catch로 파일삭제 실패시 runtimeException 발생 시킴
		try {
			File fo = new File(path + noticeFileName);
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
		row = noticeMapper.deleteNoticeFileOne(noticeFileNo);		
		return row;
	}
}

 
