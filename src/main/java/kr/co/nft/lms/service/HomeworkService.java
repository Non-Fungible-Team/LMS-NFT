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
	public Map<String, Object> getHomeworkListByPage(int currentPage, int rowPerPage,int lectureNo, Member loginMember){
		log.debug(A.Q +"HomeworkService.getHomeworkListByPage.param.currentPage" + currentPage +A.R);
		log.debug(A.Q +"HomeworkService.getHomeworkListByPage.param.rowPerPage" + rowPerPage +A.R);
		
		int beginRow = (currentPage-1)*rowPerPage;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginRow", beginRow);
		map.put("rowPerPage", rowPerPage);
		map.put("lectureNo", lectureNo);
		if(loginMember.getMemberLevel()==4) {
			map.put("studentId", loginMember.getMemberId());
		} else if(loginMember.getMemberLevel()==5) {
			map.put("teacherId", loginMember.getMemberId());
		}
		log.debug(A.Q + "HomeworkService.getHomeworkListByPage.map :" + map + A.R);
		
		List<Map<String,Object>> homeworkList = homeworkMapper.selectHomeworkListByPage(map);
		log.debug(A.Q + "HomeworkService.getHomeworkListByPage.homeworkList :" + homeworkList + A.R);
		
		int totalCount = homeworkMapper.selectTotalCount();
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
	public Map<String, Object> addHomeworkSubmit(HomeworkSubmit homeworkSubmit, String path) {
		log.debug(A.Q+"HomeworkService.addHomeworkSubmit.param homeworkSubmit : " + homeworkSubmit+ A.R);
		log.debug(A.Q+"HomeworkService.addHomeworkSubmit.param path : " + path + A.R);
		
		int lectureNo = 0;
		
		Homework homework = new Homework();
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
	public Map<String, Object> getHomeworkSubmitListByPage(int currentPage, int rowPerPage){
		log.debug(A.Q +"HomeworkService.getHomeworkSubmitListByPage.param.currentPage" + currentPage +A.R);
		log.debug(A.Q +"HomeworkService.getHomeworkSubmitListByPage.param.rowPerPage" + rowPerPage +A.R);
		
		int beginRow = (currentPage-1)*rowPerPage;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginRow", beginRow);
		map.put("rowPerPage", rowPerPage);
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
		
		log.debug(A.Q+"HomeworkService.getHomeworkSubmitOne.map :" + map +A.R);
		
		return map;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
