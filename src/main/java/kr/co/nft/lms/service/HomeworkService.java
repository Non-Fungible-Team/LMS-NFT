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

import kr.co.nft.lms.mapper.HomeworkMapper;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Homework;
import kr.co.nft.lms.vo.HomeworkSubmit;
import kr.co.nft.lms.vo.HomeworkSubmitFile;
import kr.co.nft.lms.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class HomeworkService {
	@Autowired private HomeworkMapper homeworkMapper;
	
	// 과제 리스트
	public Map<String, Object> getHomeworkListByPage(int currentPage, int rowPerPage,int lectureNo
												, Member loginMember, String searchWord){
		log.debug(A.Q +"HomeworkService.getHomeworkListByPage.param.currentPage" + currentPage +A.R);
		log.debug(A.Q +"HomeworkService.getHomeworkListByPage.param.rowPerPage" + rowPerPage +A.R);
		
		int beginRow = (currentPage-1)*rowPerPage;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginRow", beginRow);
		map.put("rowPerPage", rowPerPage);
		map.put("lectureNo", lectureNo);
		map.put("searchWord", searchWord);
		
		if(loginMember.getMemberLevel()==4) { // level 4인 학생의 경우
			map.put("studentId", loginMember.getMemberId());
		} else if(loginMember.getMemberLevel()==5) { // level 5인 강사의 경우
			map.put("teacherId", loginMember.getMemberId());
		}
		log.debug(A.Q + "HomeworkService.getHomeworkListByPage.map :" + map + A.R);
		
		List<Map<String,Object>> homeworkList = homeworkMapper.selectHomeworkListByPage(map);
		log.debug(A.Q + "HomeworkService.getHomeworkListByPage.homeworkList :" + homeworkList + A.R);
		
		int totalCount = homeworkMapper.selectTotalCount(lectureNo, (String)map.get("searchWord"));
		int lastPage = (int)Math.ceil((double)totalCount / (double)rowPerPage);
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("homeworkList", homeworkList);
		returnMap.put("lastPage", lastPage);
		log.debug(A.Q + "HomeworkService.getHomeworkListByPage.returnMap :" + returnMap + A.R);
		
		return returnMap;
	}
	
	// 과제 상세보기
	public Map<String, Object> getHomeworkOne(int homeworkNo){
		log.debug(A.Q +"HomeworkService.getHomeworkOne.param.homeworkNo : " + homeworkNo +A.R);
		Homework homeworkOne = homeworkMapper.selectHomeworkOne(homeworkNo);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("homeworkOne", homeworkOne);
		log.debug(A.Q +"HomeworkService.getHomeworkOne.returnMap : " + returnMap +A.R);
		
		return returnMap;
	}
	
	// 과제 입력
	public int addHomework(Homework homework) {
		log.debug(A.Q+"HomeworkService.addHomework.param.homework : "+ homework +A.R);
		return homeworkMapper.insertHomework(homework);
	}
	
	// 과제 수정 
	public int modifyHomework(Homework homework) {
		log.debug(A.Q+"HomeworkService.modifyHomework.param.homework : "+ homework +A.R);
		return homeworkMapper.updateHomework(homework);
	}
	
	// 과제 삭제
	public int removeHomework(int homeworkNo) {
		log.debug(A.Q+"HomeworkService.removeHomework.param.homeworkNo : "+ homeworkNo +A.R);
		return homeworkMapper.deleteHomework(homeworkNo);
		
	}
	
	// 학생 과제 제출
	public Map<String, Object> addHomeworkSubmit(HomeworkSubmit homeworkSubmit,Homework homework, String path) {
		log.debug(A.Q+"HomeworkService.addHomeworkSubmit.param homeworkSubmit : " + homeworkSubmit+ A.R);
		log.debug(A.Q+"HomeworkService.addHomeworkSubmit.param path : " + path + A.R);
		
		int lectureNo = 0;
		
		homework.setLectureNo(lectureNo);
		Map<String,Object> map = new HashMap<>();
		map.put("lectureNo", homework.getLectureNo());
		
		
		int row = homeworkMapper.insertHomeworkSubmit(homeworkSubmit);
		if(homeworkSubmit.getHomeworkSubmitFileList() != null && homeworkSubmit.getHomeworkSubmitFileList().get(0).getSize() > 0 && row ==1) {
			log.debug(A.Q+"HomeworkService.addHomeworkSubmit :"+"첨부된 파일이 있습니다."+A.R);
			
			for(MultipartFile mf : homeworkSubmit.getHomeworkSubmitFileList()) {
				HomeworkSubmitFile homeworkSubmitFile = new HomeworkSubmitFile();
				
				String originName = mf.getOriginalFilename();
				
				// originName에서 마지막 .문자열 위치
				String ext = originName.substring(originName.lastIndexOf("."));
				
				// 파일을 저장할때 사용할 중복되지않는 새로운 이름 필요(UUID API사용)
				String fileName = UUID.randomUUID().toString();
				
				fileName = fileName + ext;
				
				homeworkSubmitFile.setHomeworkSubmitNo(homeworkSubmit.getHomeworkSubmitNo());
				homeworkSubmitFile.setHomeworkSubmitFileName(fileName);
				homeworkSubmitFile.setHomeworkSubmitFileOriginal(mf.getOriginalFilename());
				homeworkSubmitFile.setHomeworkSubmitFileType(mf.getContentType());
				homeworkSubmitFile.setHomeworkSubmitFileSize(mf.getSize());
				
				homeworkMapper.insertHomeworkSubmitFile(homeworkSubmitFile);
				log.debug(A.Q+"HomeworkService.addHomeworkSubmit homeworkSubmitFile :"+ homeworkSubmitFile +A.R);
				try {
					mf.transferTo(new File(path + fileName));
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException();
				}
			}
		}
		return map;
	}
	// 학생 과제 목록
	public Map<String, Object> getHomeworkSubmitListByPage(int currentPage, int rowPerPage, int homeworkNo){
		log.debug(A.Q +"HomeworkService.getHomeworkSubmitListByPage.param.currentPage" + currentPage +A.R);
		log.debug(A.Q +"HomeworkService.getHomeworkSubmitListByPage.param.rowPerPage" + rowPerPage +A.R);
		
		int beginRow = (currentPage-1)*rowPerPage;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginRow", beginRow);
		map.put("rowPerPage", rowPerPage);
		map.put("homeworkNo", homeworkNo);
		log.debug(A.Q + "HomeworkService.getHomeworkSubmitListByPage.map :" + map + A.R);
		
		List<Map<String, Object>> homeworkSubmitList = homeworkMapper.selectHomeworkSubmitListByPage(map);
		log.debug(A.Q + "HomeworkService.getHomeworkSubmitListByPage.homeworkSubmitList :" + homeworkSubmitList + A.R);
		
		int totalCount = homeworkMapper.selectSubmitTotalCount();
		int lastPage = (int)Math.ceil((double)totalCount / (double)rowPerPage);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("homeworkSubmitList", homeworkSubmitList);
		returnMap.put("lastPage", lastPage);
		log.debug(A.Q + "HomeworkService.getHomeworkSubmitListByPage.returnMap :" + returnMap + A.R);
		
		return returnMap;
	}
	
	// 학생 과제 상세보기
	public Map<String, Object> getHomeworkSubmitOne(int homeworkSubmitNo) {
		log.debug(A.Q+"HomeworkService.getHomeworkSubmitOne.param.homeworkSubmitNo : "+ homeworkSubmitNo +A.R);
		
		HomeworkSubmit homeworkSubmitOne = homeworkMapper.selectHomeworkSubmitOne(homeworkSubmitNo);
		log.debug(A.Q+"HomeworkService.getHomeworkSubmitOne.homeworkSubmitOne :"+ homeworkSubmitOne +A.R);
		
		List<HomeworkSubmitFile> homeworkSubmitFileList = homeworkMapper.selectHomeworkSubmitFile(homeworkSubmitNo);
		log.debug(A.Q+"HomeworkService.getHomeworkSubmitOne.homeworkSubmitFileList"+ homeworkSubmitFileList+A.R);
		
		Map<String, Object> map = new HashMap<>();
		map.put("homeworkSubmitOne", homeworkSubmitOne);
		map.put("homeworkSubmitFileList", homeworkSubmitFileList);
		map.put("homeworkSubmitNo", homeworkSubmitNo);
		log.debug(A.Q+"HomeworkService.getHomeworkSubmitOne.map :" + map +A.R);
		
		return map;
	}
	
	// 학생 과제 수정
	public int modifyHomeworkSubmit(Homework homework, HomeworkSubmit homeworkSubmit, String path) {
		log.debug(A.Q+"HomeworkService.modifyHomeworkSubmit homeworkSubmit : "+ homeworkSubmit +A.R);
		
		int row = homeworkMapper.updateHomeworkSubmit(homeworkSubmit);
		int fileRow = 0;
		if(homeworkSubmit.getHomeworkSubmitFileList() != null && homeworkSubmit.getHomeworkSubmitFileList().get(0).getSize() > 0 ) {
			log.debug(A.Q+"HomeworkService.addHomeworkSubmit :"+"첨부된 파일이 있습니다."+A.R);
			
			for(MultipartFile mf : homeworkSubmit.getHomeworkSubmitFileList()) {
				HomeworkSubmitFile homeworkSubmitFileModify = new HomeworkSubmitFile();
				
				String originName = mf.getOriginalFilename();
				
				// originName에서 마지막 .문자열 위치
				String ext = originName.substring(originName.lastIndexOf("."));
				
				// 파일을 저장할때 사용할 중복되지않는 새로운 이름 필요(UUID API사용)
				String fileName = UUID.randomUUID().toString();
				
				fileName = fileName + ext;
				
				homeworkSubmitFileModify.setHomeworkSubmitNo(homeworkSubmit.getHomeworkSubmitNo());
				homeworkSubmitFileModify.setHomeworkSubmitFileName(fileName);
				homeworkSubmitFileModify.setHomeworkSubmitFileOriginal(mf.getOriginalFilename());
				homeworkSubmitFileModify.setHomeworkSubmitFileType(mf.getContentType());
				homeworkSubmitFileModify.setHomeworkSubmitFileSize(mf.getSize());
				
				log.debug(A.Q+"HomeworkService.addHomeworkSubmit homeworkSubmitFile :"+ homeworkSubmitFileModify +A.R);
				
				fileRow = fileRow + homeworkMapper.insertHomeworkSubmitFile(homeworkSubmitFileModify);
				try {
					mf.transferTo(new File(path + fileName));
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException();
				}
			}
		}
		
		return row;
	}
	
	// 학생 과제 삭제
	public int removeHomeworkSubmit(int homeworkSubmitNo) {
		log.debug(A.Q+"HomeworkService.removeHomeworkSubmit.param.homeworkSubmitNo :" + homeworkSubmitNo +A.R);
		
		return homeworkMapper.deleteHomeworkSubmit(homeworkSubmitNo);
	}
	// file 삭제
	public int removeHomeworkSubmitFileOne(int homeworkSubmitNo, String homeworkSubmitFileName ,int homeworkSubmitFileNo, String path) {
		log.debug(A.Q+"HomeworkService.removeHomeworkSubmitFileOne.homeworkSubmitFileNo :" + homeworkSubmitFileNo+A.R);
		
		int row = -1;
		try {
			File file = new File(path + homeworkSubmitFileName);
			// 만약 파일이 존재한다면
			if(file.exists()) {
				//삭제한다
				file.delete();
			}
			//경로+이름으로 파일 저장
		} catch (Exception e) {
			e.printStackTrace();
			// 새로운 예외 발생시켜야지만 @Transactional 작동을 위해
			throw new RuntimeException(); // RuntimeException은 예외처리를 하지 않아도 컴파일된다
		}
		//2) DB 삭제 (파일삭제)
		row = homeworkMapper.deleteHomeworkSubmitFileOne(homeworkSubmitFileNo);		
		return row;
	}
	
	
	// 과제 점수 입력
	public int modifyHomeworkSubmitScore(HomeworkSubmit homeworkSubmit) {
		log.debug(A.Q+"HomeworkService.modifyHomeworkSubmitScore.homeworkSubmit :"+ homeworkSubmit +A.R);
		
		return homeworkMapper.updateHomeworkSubmitScore(homeworkSubmit);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
