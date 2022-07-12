package kr.co.nft.lms.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.nft.lms.dto.AddManager;
import kr.co.nft.lms.dto.AddStudent;
import kr.co.nft.lms.dto.AddTeacher;
import kr.co.nft.lms.mapper.MemberMapper;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Address;
import kr.co.nft.lms.vo.LevelHistory;
import kr.co.nft.lms.vo.Manager;
import kr.co.nft.lms.vo.Member;
import kr.co.nft.lms.vo.MemberPhoto;
import kr.co.nft.lms.vo.MemberUploadPhoto;
import kr.co.nft.lms.vo.PwHistory;
import kr.co.nft.lms.vo.Student;
import kr.co.nft.lms.vo.Teacher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class MemberService {

	// Service ← 여기서 사진 파일 가공 

	@Autowired MemberMapper memberMapper;
	

	private ModelMapper modelMapper;
	
	// --------------------------------------- //
	public List<Address> getStudentProvince() {
		
		// 지역별 학생 목록 뽑기 
		List<Address> studentProvinceList = memberMapper.selectStudentProvinceList();
		log.debug(A.Z+"[MemberService.getStudentProvince] studentProvinceList : "+studentProvinceList+A.R);
		
		return studentProvinceList;
	}
	
	// --------------------------------------- //
	
	// 강사 목록 뽑기 
	public Map<String, Object> getTeacherByPage(int currentPage, int rowPerPage) {
		// (1) Controller에서 넘어온 매개 변수값을 가공 후 매퍼 호출
		
		// 매개 변수 내용 확인 
		log.debug(A.Z+"[MemberService.getTeacherByPage.param] currentPage : "+currentPage+A.R);
		log.debug(A.Z+"[MemberService.getTeacherByPage.param] rowPerPage : "+rowPerPage+A.R);
		
		int startRow = (currentPage - 1) * rowPerPage;
		log.debug(A.Z+"[MemberService.getTeacherByPage] startRow : "+startRow+A.R);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rowPerPage", rowPerPage);
		map.put("startRow", startRow);
		List<Teacher> teacherList = memberMapper.selectTeacherByPage(map);
		log.debug(A.Z+"[MemberService.getTeacherByPage] teacherList.size() : "+teacherList.size()+A.R);
		
		// (2) 매퍼에서 반환된 값을 가공. Controller에 반환 
		int totalCount = memberMapper.selectTeacherTotalCount();
		log.debug(A.Z+"[MemberService.getTeacherByPage] totalCount : "+totalCount+A.R);
		
		// ceil() 올림 함수를 사용해서 마지막 페이지를 구한다. 
		int lastPage = (int)(Math.ceil((double)totalCount / (double)rowPerPage));
		log.debug(A.Z+"[MemberService.getTeacherByPage] lastPage : "+lastPage+A.R);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("teacherList", teacherList);
		returnMap.put("lastPage", lastPage);
		return returnMap;
	}
	
	// 학생 목록 뽑기 
	public Map<String, Object> getStudentByPage(int currentPage, int rowPerPage) {
		// (1) Controller에서 넘어온 매개 변수값을 가공 후 매퍼 호출
		
		// 매개 변수 내용 확인 
		log.debug(A.Z+"[MemberService.getStudentByPage.param] currentPage : "+currentPage+A.R);
		log.debug(A.Z+"[MemberService.getStudentByPage.param] rowPerPage : "+rowPerPage+A.R);
		
		int startRow = (currentPage - 1) * rowPerPage;
		log.debug(A.Z+"[MemberService.getStudentByPage] startRow : "+startRow+A.R);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rowPerPage", rowPerPage);
		map.put("startRow", startRow);
		List<Student> studentList = memberMapper.selectStudentByPage(map);
		log.debug(A.Z+"[MemberService.getStudentByPage] studentList : "+studentList+A.R);
		
		// (2) 매퍼에서 반환된 값을 가공. Controller에 반환 
		int totalCount = memberMapper.selectStudentTotalCount();
		log.debug(A.Z+"[MemberService.getStudentByPage] totalCount : "+totalCount+A.R);
		
		// ceil() 올림 함수를 사용해서 마지막 페이지를 구한다. 
		int lastPage = (int)(Math.ceil((double)totalCount / (double)rowPerPage));
		log.debug(A.Z+"[MemberService.getStudentByPage] lastPage : "+lastPage+A.R);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("studentList", studentList);
		returnMap.put("lastPage", lastPage);
		return returnMap;
	}
	
	// --------------------------------------- //
	// 회원 정보 삭제(휴면) 
	public int modifyDormantMember() {
		return memberMapper.updateDormantMember();
	}
	
	// --------------------------------------- //
	// 회원 정보 삭제(탈퇴) 
	
	// 운영자의 레벨이 변경되면 `LEVEL_HISTORY` 테이블에 튜플을 남긴다. 
	public int addLevelHistoryOfManagerRecord(Member member) {
		return memberMapper.insertLevelHistoryOfManagerRecord(member);
	}
	
	// Member 테이블의 운영자 레코드 삭제 
	public int freezeManagerOfMemberTbl(Member member) {
		return memberMapper.updateManagerOfMemberTbl(member);
	}
	
	// 강사의 레벨이 변경되면 `LEVEL_HISTORY` 테이블에 튜플을 남긴다 
	public int addLevelHistoryOfTeacherRecord(Member member) {
		return memberMapper.insertLevelHistoryOfTeacherRecord(member);
	}
	
	// Member 테이블의 강사 레코드 삭제, 삭제시 순서가 밀린다 
	public int freezeTeacherOfMemberTbl(Member member) {
		return memberMapper.updateTeacherOfMemberTbl(member);
	}
	
	// Teacher 테이블의 강사 튜플 삭제, 삭제시 순서가 먼저 와야한다 
	public int freezeTeacherOfTeacherTbl(Member member) {
		return memberMapper.updateTeacherOfTeacherTbl(member);
	}
	
	// 학생의 레벨이 변경되면 `LEVEL_HISTORY` 테이블에 튜플을 남긴다 
	public int addLevelHistoryOfStudentRecord(Member member) {
		return memberMapper.insertLevelHistoryOfStudentRecord(member);
	}
	
	// Member 테이블의 학생 레코드 삭제, 삭제시 순서가 밀린다 
	public int freezeStudentOfMemberTbl(Member member) {
		return memberMapper.updateStudentOfMemberTbl(member);
	}
	
	// Student 테이블의 학생 튜플 삭제, 삭제시 순서가 먼저 와야한다 
	public int freezeStudentOfStudentTbl(Member member) {
		return memberMapper.updateStudentOfStudentTbl(member);
	}
	
	// --------------------------------------- //
	// 회원 정보 수정 
	
	// 회원 비밀번호 수정 
	public int modifyMemberPassword(Member member) {
		
		log.debug(A.Z+"[MemberService.modifyMemberPassword.param] member : "+member+A.R);
		
		// 성공 여부 출력할 변수 초기화 
		int row = -1;
		
		
		
		// Member 테이블에 입력 
		row = memberMapper.updateMemberPw(member);
		log.debug(A.Z+"[MemberService.modifyMemberPassword] row : "+row+A.R);
		row = -1;
		
		// PW_HISTORY 테이블에 입력 
		row = memberMapper.updatePwHistory(member);
		log.debug(A.Z+"[MemberService.updatePwHistory] row : "+row+A.R);
				
		return row;
	}
	
	// MemberPhoto 레코드 정보를 가져오기 위해 사용 
	public MemberPhoto getMemberPhoto(Member member) {
		return memberMapper.selectMemberPhoto(member);
	}
	
	// MemberPhoto 테이블 활용하여 학생, 강사, 운영자 사진 업로드 - 학생, 강사, 운영자 정보 수정과 같이 이루어진다 
	public void addMemberPhoto(Member member, MemberPhoto memberPhoto, MemberUploadPhoto memberUploadPhoto, String path) {
		
		// 매개변수를 잘 받아왔는지 확인 
		log.debug(A.Z+"[MemberService.addStudentPhoto.param] member : "+member+A.R);
		log.debug(A.Z+"[MemberService.addStudentPhoto.param] memberPhoto : "+memberPhoto+A.R);
		log.debug(A.Z+"[MemberService.addStudentPhoto.param] memberUploadPhoto : "+memberUploadPhoto+A.R);
		log.debug(A.Z+"[MemberService.addStudentPhoto.param] path : "+path+A.R);
		
		// 파일을 업로드했고, 파일 사이즈가 0보다 크다면 
		if(memberUploadPhoto.getMemberPhotoOne() != null && memberUploadPhoto.getMemberPhotoOne().getSize() > 0) {
			log.debug(A.Z+"[MemberService.addStudentPhoto]  : "+"첨부된 파일이 있습니다."+A.R);
			
			// MEMBERPHOTO 테이블 필드에 레코드를 넣기 위해 참조 타입 변수 선언 
			memberPhoto = new MemberPhoto();
			
			// 파일을 저장할 때 사용할 중복되지 않는 새로운 이름 필요 
			String photoOriginName = memberUploadPhoto.getMemberPhotoOne().getOriginalFilename();
			
			// originName에서 마지막 문자열 위치 
			// 확장자만 뽑아온다 
			String ext = photoOriginName.substring(photoOriginName.lastIndexOf("."));
			
			// 파일을 저장할 때 사용할 중복되지 않는 새로운 이름 필요 (UUID API 사용)
			String filename = UUID.randomUUID().toString();
			// 파일 이름은 UUID API로 새로 정한 이름과 확장자명을 합한 이름으로 하겠다 
			filename =filename + ext; 
			
			// MEMBERPHOTO 테이블 필드에 레코드를 집어넣는다.
			memberPhoto.setMemberId(member.getMemberId());
			memberPhoto.setPhotoName(filename);
			memberPhoto.setPhotoOriginName(photoOriginName);
			memberPhoto.setPhotoType(memberUploadPhoto.getMemberPhotoOne().getContentType());
			memberPhoto.setPhotoSize(memberUploadPhoto.getMemberPhotoOne().getSize());
			// 값이 제대로 들어갔는지 확인 
			log.debug(A.Z+"[MemberService.addMemberPhoto]  memberPhoto : "+memberPhoto+A.R);
			
			// 사진이 잘 등록되었는지 확인 
			int row = memberMapper.insertMemberPhoto(memberPhoto);
			log.debug(A.Z+"[MemberService.addMemberPhoto]  row : "+row+A.R);
						
			try {
				// multipart 안의 파일을 저장 장치로 저장 
				memberUploadPhoto.getMemberPhotoOne().transferTo(new File(path+filename));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// 새로운 예외 발생 시켜야지만 @Transactional 작동
				// RuntimeException은 예외 처리를 하지 않아도 컴파일된다 
				throw new RuntimeException();
			} 
			
			
		}
		
	}
	
	// Member 테이블 활용하여 운영자 수정 액션 
	public int modifyManager(Member member) {
		return memberMapper.updateManagerByMemberVo(member);
	}
	
	// Manager 테이블 활용하여 운영자 수정 액션 
	public int modifyManager(Manager manager) {
		return memberMapper.updateManagerByManagerVo(manager);
	}
	
	// Member 테이블 활용하여 강사 수정 액션 
	public int modifyTeacher(Member member) {
		return memberMapper.updateTeacherByMemberVo(member);
	}
	
	// Teacher 테이블 활용하여 강사 수정 액션 
	public int modifyTeacher(Teacher teacher) {
		return memberMapper.updateTeacherByTeacherVo(teacher);
	}
	
	// Member, Manager, Member_Photo 테이블 활용하여 학생 정보 수정 
	public int modifyManager(AddManager modifyManager
			, MemberPhoto memberPhoto
			, MemberUploadPhoto memberUploadPhoto
			, HttpServletRequest request) {
	// 매개 변수 확인 
	log.debug(A.Z+"[MemberService.modifyManager.param] modifyManager : "+modifyManager+A.R);
	// mapper 요청 결과 저장할 변수 초기화 
	int row = -1;
	
	// Member 테이블에 UPDATE 요청 
	modelMapper = new ModelMapper();
	// Member 테이블에 들어갈 수 있는 값을 modifyStudent에서 꺼내 넣는다. 
	Member member = modelMapper.map(modifyManager, Member.class);
	row = memberMapper.updateManagerByMemberVo(member);
	log.debug(A.Z+"[MemberService.modifyManager.memberMapper.updateManagerByMemberVo] row : "+row+A.R);
	row = -1;
	
	Manager manager = modelMapper.map(modifyManager, Manager.class);
	row = memberMapper.updateManagerByManagerVo(manager);
	
	String path = request.getServletContext().getRealPath("uploadFile/memberPhoto/managerPhoto/");
	log.debug(A.Z+"[MemberService.modifyManager]  : path "+path+A.R);
	// 사진이 이미 포함되어 있는데 사진을 수정하려고 하는 경우 삭제 후 생성 과정 추가 
	if(memberPhoto.getMemberId() != null) {
	if(memberUploadPhoto.getMemberPhotoOne() != null && memberUploadPhoto.getMemberPhotoOne().getSize() > 0 && row == 1) {
	log.debug(A.Z+"[MemberService.modifyManager]  : "+"사진을 수정하려고 합니다."+A.R);
	row = -1;
	
	row = memberMapper.deleteMemberPhoto(member);
	log.debug(A.Z+"[MemberService.modifyManager]  : "+"MEMBER_PHOTO 테이블 레코드가 삭제되었습니다."+A.R);
	
	File file = new File(path+memberPhoto.getPhotoName());
	log.debug(A.Z+"[MemberService.modifyManager] file : "+file+A.R);
	file.delete();
	
	// 파일을 업로드했고, 파일 사이즈가 0보다 크다면 
	if(memberUploadPhoto.getMemberPhotoOne() != null && memberUploadPhoto.getMemberPhotoOne().getSize() > 0) {
		log.debug(A.Z+"[MemberService.modifyManager]  : "+"첨부된 파일이 있습니다. 사진을 처음 등록하는 사용자입니다."+A.R);
		
		// MEMBERPHOTO 테이블 필드에 레코드를 넣기 위해 참조 타입 변수 선언 
		memberPhoto = new MemberPhoto();
		
		// 파일을 저장할 때 사용할 중복되지 않는 새로운 이름 필요 
		String photoOriginName = memberUploadPhoto.getMemberPhotoOne().getOriginalFilename();
		
		// originName에서 마지막 문자열 위치 
		// 확장자만 뽑아온다 
		String ext = photoOriginName.substring(photoOriginName.lastIndexOf("."));
		
		// 파일을 저장할 때 사용할 중복되지 않는 새로운 이름 필요 (UUID API 사용)
		String filename = UUID.randomUUID().toString();
		// 파일 이름은 UUID API로 새로 정한 이름과 확장자명을 합한 이름으로 하겠다 
		filename =filename + ext; 
		
		// MEMBERPHOTO 테이블 필드에 레코드를 집어넣는다.
		memberPhoto.setMemberId(member.getMemberId());
		memberPhoto.setPhotoName(filename);
		memberPhoto.setPhotoOriginName(photoOriginName);
		memberPhoto.setPhotoType(memberUploadPhoto.getMemberPhotoOne().getContentType());
		memberPhoto.setPhotoSize(memberUploadPhoto.getMemberPhotoOne().getSize());
		// 값이 제대로 들어갔는지 확인 
		log.debug(A.Z+"[MemberService.addMemberPhoto]  memberPhoto : "+memberPhoto+A.R);
		
		// 사진이 잘 등록되었는지 확인 
		int rowOfInsertMemberPhoto = memberMapper.insertMemberPhoto(memberPhoto);
		log.debug(A.Z+"[MemberService.addMemberPhoto]  rowOfInsertMemberPhoto : "+rowOfInsertMemberPhoto+A.R);
					
		try {
			// multipart 안의 파일을 저장 장치로 저장 
			memberUploadPhoto.getMemberPhotoOne().transferTo(new File(path+filename));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 새로운 예외 발생 시켜야지만 @Transactional 작동
			// RuntimeException은 예외 처리를 하지 않아도 컴파일된다 
			throw new RuntimeException();
		} 
	}
	}
	} 
	// 기존에 포함된 사진이 없는 경우 
	else {
	// 파일을 업로드했고, 파일 사이즈가 0보다 크다면 
	if(memberUploadPhoto.getMemberPhotoOne() != null && memberUploadPhoto.getMemberPhotoOne().getSize() > 0 && row == 1) {
	log.debug(A.Z+"[MemberService.modifyTeacher]  : "+"첨부된 파일이 있습니다. 사진을 처음 등록하는 사용자입니다."+A.R);
	
	// MEMBERPHOTO 테이블 필드에 레코드를 넣기 위해 참조 타입 변수 선언 
	memberPhoto = new MemberPhoto();
	
	// 파일을 저장할 때 사용할 중복되지 않는 새로운 이름 필요 
	String photoOriginName = memberUploadPhoto.getMemberPhotoOne().getOriginalFilename();
	
	// originName에서 마지막 문자열 위치 
	// 확장자만 뽑아온다 
	String ext = photoOriginName.substring(photoOriginName.lastIndexOf("."));
	
	// 파일을 저장할 때 사용할 중복되지 않는 새로운 이름 필요 (UUID API 사용)
	String filename = UUID.randomUUID().toString();
	// 파일 이름은 UUID API로 새로 정한 이름과 확장자명을 합한 이름으로 하겠다 
	filename =filename + ext; 
	
	// MEMBERPHOTO 테이블 필드에 레코드를 집어넣는다.
	memberPhoto.setMemberId(member.getMemberId());
	memberPhoto.setPhotoName(filename);
	memberPhoto.setPhotoOriginName(photoOriginName);
	memberPhoto.setPhotoType(memberUploadPhoto.getMemberPhotoOne().getContentType());
	memberPhoto.setPhotoSize(memberUploadPhoto.getMemberPhotoOne().getSize());
	// 값이 제대로 들어갔는지 확인 
	log.debug(A.Z+"[MemberService.addMemberPhoto]  memberPhoto : "+memberPhoto+A.R);
	
	// 사진이 잘 등록되었는지 확인 
	int rowOfInsertMemberPhoto = memberMapper.insertMemberPhoto(memberPhoto);
	log.debug(A.Z+"[MemberService.addMemberPhoto]  rowOfInsertMemberPhoto : "+rowOfInsertMemberPhoto+A.R);
				
	try {
		// multipart 안의 파일을 저장 장치로 저장 
		memberUploadPhoto.getMemberPhotoOne().transferTo(new File(path+filename));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		// 새로운 예외 발생 시켜야지만 @Transactional 작동
		// RuntimeException은 예외 처리를 하지 않아도 컴파일된다 
		throw new RuntimeException();
	} 
	}
	}
	return row;
	}
	
	// Member, Teacher, Member_Photo 테이블 활용하여 학생 정보 수정 
	public int modifyTeacher(AddTeacher modifyTeacher
			, MemberPhoto memberPhoto
			, MemberUploadPhoto memberUploadPhoto
			, HttpServletRequest request) {
	// 매개 변수 확인 
	log.debug(A.Z+"[MemberService.modifyTeacher.param] modifyTeacher : "+modifyTeacher+A.R);
	// mapper 요청 결과 저장할 변수 초기화 
	int row = -1;
	
	// Member 테이블에 UPDATE 요청 
	modelMapper = new ModelMapper();
	// Member 테이블에 들어갈 수 있는 값을 modifyStudent에서 꺼내 넣는다. 
	Member member = modelMapper.map(modifyTeacher, Member.class);
	row = memberMapper.updateTeacherByMemberVo(member);
	log.debug(A.Z+"[MemberService.modifyTeacher.memberMapper.updateTeacherByMemberVo] row : "+row+A.R);
	row = -1;
	
	Teacher teacher = modelMapper.map(modifyTeacher, Teacher.class);
	row = memberMapper.updateTeacherByTeacherVo(teacher);
	
	String path = request.getServletContext().getRealPath("uploadFile/memberPhoto/teacherPhoto/");
	log.debug(A.Z+"[MemberService.modifyTeacher]  : path "+path+A.R);
	// 사진이 이미 포함되어 있는데 사진을 수정하려고 하는 경우 삭제 후 생성 과정 추가 
	if(memberPhoto.getMemberId() != null) {
	if(memberUploadPhoto.getMemberPhotoOne() != null && memberUploadPhoto.getMemberPhotoOne().getSize() > 0 && row == 1) {
	log.debug(A.Z+"[MemberService.modifyTeacher]  : "+"사진을 수정하려고 합니다."+A.R);
	row = -1;
	
	row = memberMapper.deleteMemberPhoto(member);
	log.debug(A.Z+"[MemberService.modifyTeacher]  : "+"MEMBER_PHOTO 테이블 레코드가 삭제되었습니다."+A.R);
	
	File file = new File(path+memberPhoto.getPhotoName());
	log.debug(A.Z+"[MemberService.modifyTeacher] file : "+file+A.R);
	file.delete();
	
	// 파일을 업로드했고, 파일 사이즈가 0보다 크다면 
	if(memberUploadPhoto.getMemberPhotoOne() != null && memberUploadPhoto.getMemberPhotoOne().getSize() > 0) {
		log.debug(A.Z+"[MemberService.modifyTeacher]  : "+"첨부된 파일이 있습니다. 사진을 처음 등록하는 사용자입니다."+A.R);
		
		// MEMBERPHOTO 테이블 필드에 레코드를 넣기 위해 참조 타입 변수 선언 
		memberPhoto = new MemberPhoto();
		
		// 파일을 저장할 때 사용할 중복되지 않는 새로운 이름 필요 
		String photoOriginName = memberUploadPhoto.getMemberPhotoOne().getOriginalFilename();
		
		// originName에서 마지막 문자열 위치 
		// 확장자만 뽑아온다 
		String ext = photoOriginName.substring(photoOriginName.lastIndexOf("."));
		
		// 파일을 저장할 때 사용할 중복되지 않는 새로운 이름 필요 (UUID API 사용)
		String filename = UUID.randomUUID().toString();
		// 파일 이름은 UUID API로 새로 정한 이름과 확장자명을 합한 이름으로 하겠다 
		filename =filename + ext; 
		
		// MEMBERPHOTO 테이블 필드에 레코드를 집어넣는다.
		memberPhoto.setMemberId(member.getMemberId());
		memberPhoto.setPhotoName(filename);
		memberPhoto.setPhotoOriginName(photoOriginName);
		memberPhoto.setPhotoType(memberUploadPhoto.getMemberPhotoOne().getContentType());
		memberPhoto.setPhotoSize(memberUploadPhoto.getMemberPhotoOne().getSize());
		// 값이 제대로 들어갔는지 확인 
		log.debug(A.Z+"[MemberService.addMemberPhoto]  memberPhoto : "+memberPhoto+A.R);
		
		// 사진이 잘 등록되었는지 확인 
		int rowOfInsertMemberPhoto = memberMapper.insertMemberPhoto(memberPhoto);
		log.debug(A.Z+"[MemberService.addMemberPhoto]  rowOfInsertMemberPhoto : "+rowOfInsertMemberPhoto+A.R);
					
		try {
			// multipart 안의 파일을 저장 장치로 저장 
			memberUploadPhoto.getMemberPhotoOne().transferTo(new File(path+filename));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 새로운 예외 발생 시켜야지만 @Transactional 작동
			// RuntimeException은 예외 처리를 하지 않아도 컴파일된다 
			throw new RuntimeException();
		} 
	}
	}
	} 
	// 기존에 포함된 사진이 없는 경우 
	else {
	// 파일을 업로드했고, 파일 사이즈가 0보다 크다면 
	if(memberUploadPhoto.getMemberPhotoOne() != null && memberUploadPhoto.getMemberPhotoOne().getSize() > 0 && row == 1) {
	log.debug(A.Z+"[MemberService.modifyTeacher]  : "+"첨부된 파일이 있습니다. 사진을 처음 등록하는 사용자입니다."+A.R);
	
	// MEMBERPHOTO 테이블 필드에 레코드를 넣기 위해 참조 타입 변수 선언 
	memberPhoto = new MemberPhoto();
	
	// 파일을 저장할 때 사용할 중복되지 않는 새로운 이름 필요 
	String photoOriginName = memberUploadPhoto.getMemberPhotoOne().getOriginalFilename();
	
	// originName에서 마지막 문자열 위치 
	// 확장자만 뽑아온다 
	String ext = photoOriginName.substring(photoOriginName.lastIndexOf("."));
	
	// 파일을 저장할 때 사용할 중복되지 않는 새로운 이름 필요 (UUID API 사용)
	String filename = UUID.randomUUID().toString();
	// 파일 이름은 UUID API로 새로 정한 이름과 확장자명을 합한 이름으로 하겠다 
	filename =filename + ext; 
	
	// MEMBERPHOTO 테이블 필드에 레코드를 집어넣는다.
	memberPhoto.setMemberId(member.getMemberId());
	memberPhoto.setPhotoName(filename);
	memberPhoto.setPhotoOriginName(photoOriginName);
	memberPhoto.setPhotoType(memberUploadPhoto.getMemberPhotoOne().getContentType());
	memberPhoto.setPhotoSize(memberUploadPhoto.getMemberPhotoOne().getSize());
	// 값이 제대로 들어갔는지 확인 
	log.debug(A.Z+"[MemberService.addMemberPhoto]  memberPhoto : "+memberPhoto+A.R);
	
	// 사진이 잘 등록되었는지 확인 
	int rowOfInsertMemberPhoto = memberMapper.insertMemberPhoto(memberPhoto);
	log.debug(A.Z+"[MemberService.addMemberPhoto]  rowOfInsertMemberPhoto : "+rowOfInsertMemberPhoto+A.R);
				
	try {
		// multipart 안의 파일을 저장 장치로 저장 
		memberUploadPhoto.getMemberPhotoOne().transferTo(new File(path+filename));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		// 새로운 예외 발생 시켜야지만 @Transactional 작동
		// RuntimeException은 예외 처리를 하지 않아도 컴파일된다 
		throw new RuntimeException();
	} 
	}
	}
	return row;
	}
	
	// Member, Student, Member_Photo 테이블 활용하여 학생 정보 수정 
	public int modifyStudent(AddStudent modifyStudent
							, MemberPhoto memberPhoto
							, MemberUploadPhoto memberUploadPhoto
							, HttpServletRequest request) {
		// 매개 변수 확인 
		log.debug(A.Z+"[MemberService.getStudentOne.param] modifyStudent : "+modifyStudent+A.R);
		// mapper 요청 결과 저장할 변수 초기화 
		int row = -1;
		
		// Member 테이블에 UPDATE 요청 
		modelMapper = new ModelMapper();
		// Member 테이블에 들어갈 수 있는 값을 modifyStudent에서 꺼내 넣는다. 
		Member member = modelMapper.map(modifyStudent, Member.class);
		row = memberMapper.updateStudentByMemberVo(member);
		log.debug(A.Z+"[MemberService.modifyStudent.memberMapper.updateStudentByMemberVo] row : "+row+A.R);
		row = -1;
		
		Student student = modelMapper.map(modifyStudent, Student.class);
		row = memberMapper.updateStudentByStudentVo(student);
		
		String path = request.getServletContext().getRealPath("uploadFile/memberPhoto/studentPhoto/");
		log.debug(A.Z+"[MemberService.modifyStudent]  : path "+path+A.R);
		// 사진이 이미 포함되어 있는데 사진을 수정하려고 하는 경우 삭제 후 생성 과정 추가 
		if(memberPhoto.getMemberId() != null) {
			if(memberUploadPhoto.getMemberPhotoOne() != null && memberUploadPhoto.getMemberPhotoOne().getSize() > 0 && row == 1) {
				log.debug(A.Z+"[MemberService.modifyStudent]  : "+"사진을 수정하려고 합니다."+A.R);
				row = -1;
				
				row = memberMapper.deleteMemberPhoto(member);
				log.debug(A.Z+"[MemberService.modifyStudent]  : "+"MEMBER_PHOTO 테이블 레코드가 삭제되었습니다."+A.R);
				
				File file = new File(path+memberPhoto.getPhotoName());
				log.debug(A.Z+"[MemberService.modifyStudent] file : "+file+A.R);
				file.delete();
				
				// 파일을 업로드했고, 파일 사이즈가 0보다 크다면 
				if(memberUploadPhoto.getMemberPhotoOne() != null && memberUploadPhoto.getMemberPhotoOne().getSize() > 0) {
					log.debug(A.Z+"[MemberService.modifyStudent]  : "+"첨부된 파일이 있습니다. 사진을 처음 등록하는 사용자입니다."+A.R);
					
					// MEMBERPHOTO 테이블 필드에 레코드를 넣기 위해 참조 타입 변수 선언 
					memberPhoto = new MemberPhoto();
					
					// 파일을 저장할 때 사용할 중복되지 않는 새로운 이름 필요 
					String photoOriginName = memberUploadPhoto.getMemberPhotoOne().getOriginalFilename();
					
					// originName에서 마지막 문자열 위치 
					// 확장자만 뽑아온다 
					String ext = photoOriginName.substring(photoOriginName.lastIndexOf("."));
					
					// 파일을 저장할 때 사용할 중복되지 않는 새로운 이름 필요 (UUID API 사용)
					String filename = UUID.randomUUID().toString();
					// 파일 이름은 UUID API로 새로 정한 이름과 확장자명을 합한 이름으로 하겠다 
					filename =filename + ext; 
					
					// MEMBERPHOTO 테이블 필드에 레코드를 집어넣는다.
					memberPhoto.setMemberId(member.getMemberId());
					memberPhoto.setPhotoName(filename);
					memberPhoto.setPhotoOriginName(photoOriginName);
					memberPhoto.setPhotoType(memberUploadPhoto.getMemberPhotoOne().getContentType());
					memberPhoto.setPhotoSize(memberUploadPhoto.getMemberPhotoOne().getSize());
					// 값이 제대로 들어갔는지 확인 
					log.debug(A.Z+"[MemberService.addMemberPhoto]  memberPhoto : "+memberPhoto+A.R);
					
					// 사진이 잘 등록되었는지 확인 
					int rowOfInsertMemberPhoto = memberMapper.insertMemberPhoto(memberPhoto);
					log.debug(A.Z+"[MemberService.addMemberPhoto]  rowOfInsertMemberPhoto : "+rowOfInsertMemberPhoto+A.R);
								
					try {
						// multipart 안의 파일을 저장 장치로 저장 
						memberUploadPhoto.getMemberPhotoOne().transferTo(new File(path+filename));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						// 새로운 예외 발생 시켜야지만 @Transactional 작동
						// RuntimeException은 예외 처리를 하지 않아도 컴파일된다 
						throw new RuntimeException();
					} 
				}
			}
		} 
		// 기존에 포함된 사진이 없는 경우 
		else {
			// 파일을 업로드했고, 파일 사이즈가 0보다 크다면 
			if(memberUploadPhoto.getMemberPhotoOne() != null && memberUploadPhoto.getMemberPhotoOne().getSize() > 0 && row == 1) {
				log.debug(A.Z+"[MemberService.modifyStudent]  : "+"첨부된 파일이 있습니다. 사진을 처음 등록하는 사용자입니다."+A.R);
				
				// MEMBERPHOTO 테이블 필드에 레코드를 넣기 위해 참조 타입 변수 선언 
				memberPhoto = new MemberPhoto();
				
				// 파일을 저장할 때 사용할 중복되지 않는 새로운 이름 필요 
				String photoOriginName = memberUploadPhoto.getMemberPhotoOne().getOriginalFilename();
				
				// originName에서 마지막 문자열 위치 
				// 확장자만 뽑아온다 
				String ext = photoOriginName.substring(photoOriginName.lastIndexOf("."));
				
				// 파일을 저장할 때 사용할 중복되지 않는 새로운 이름 필요 (UUID API 사용)
				String filename = UUID.randomUUID().toString();
				// 파일 이름은 UUID API로 새로 정한 이름과 확장자명을 합한 이름으로 하겠다 
				filename =filename + ext; 
				
				// MEMBERPHOTO 테이블 필드에 레코드를 집어넣는다.
				memberPhoto.setMemberId(member.getMemberId());
				memberPhoto.setPhotoName(filename);
				memberPhoto.setPhotoOriginName(photoOriginName);
				memberPhoto.setPhotoType(memberUploadPhoto.getMemberPhotoOne().getContentType());
				memberPhoto.setPhotoSize(memberUploadPhoto.getMemberPhotoOne().getSize());
				// 값이 제대로 들어갔는지 확인 
				log.debug(A.Z+"[MemberService.addMemberPhoto]  memberPhoto : "+memberPhoto+A.R);
				
				// 사진이 잘 등록되었는지 확인 
				int rowOfInsertMemberPhoto = memberMapper.insertMemberPhoto(memberPhoto);
				log.debug(A.Z+"[MemberService.addMemberPhoto]  rowOfInsertMemberPhoto : "+rowOfInsertMemberPhoto+A.R);
							
				try {
					// multipart 안의 파일을 저장 장치로 저장 
					memberUploadPhoto.getMemberPhotoOne().transferTo(new File(path+filename));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					// 새로운 예외 발생 시켜야지만 @Transactional 작동
					// RuntimeException은 예외 처리를 하지 않아도 컴파일된다 
					throw new RuntimeException();
				} 
			}
		}
		return row;
	}
	
	// --------------------------------------- // 
	// 상세보기 
	
	// MEMBER, Teacher 테이블에서 학생 정보 상세 조회 
	public Map<String, Object> getManagerOne(Member member) {
		
		log.debug(A.Z+"[MemberService.getManagerOne.param] member : "+member+A.R);
		// 강사 상세보기 리턴값 받을 변수 선언 
		Map<String, Object> map = new HashMap<>();
		
		map = memberMapper.selectManagerOne(member);
		log.debug(A.Z+"[MemberService.selectManagerOne] map : "+map+A.R);
		
		return map;
	}
	
	// MEMBER, Teacher 테이블에서 학생 정보 상세 조회 
	public Map<String, Object> getTeacherOne(Member member) {
		
		log.debug(A.Z+"[MemberService.getStudentOne.param] member : "+member+A.R);
		// 강사 상세보기 리턴값 받을 변수 선언 
		Map<String, Object> map = new HashMap<>();
		
		map = memberMapper.selectTeacherOne(member);
		log.debug(A.Z+"[MemberService.selectTeachertOne] map : "+map+A.R);
		
		return map;
	}
	
	// MEMBER, STUDENT 테이블에서 학생 정보 상세 조회 
	public Map<String, Object> getStudentOne(Member member) {
		
		log.debug(A.Z+"[MemberService.getStudentOne.param] member : "+member+A.R);
		// 학생 상세보기 리턴값 받을 변수 선언 
		Map<String, Object> map = new HashMap<>();
		
		map = memberMapper.selectStudentOne(member);
		log.debug(A.Z+"[MemberService.getStudentOne] map : "+map+A.R);
		
		return map;
	}
	
	// --------------------------------------- // 
	// 회원가입 
	
	//운영자 회원 가입 
	public int addManager(AddManager addManager) {
		log.debug(A.Z+"[MemberService.addManager.param] addManager : "+addManager+A.R);
		//mapper 요청 결과 저장할 변수 초기화
		int row = -1;
		
		//member 테이블에 insert 요청
		//요청 전 MemberVo로 변환
		modelMapper = new ModelMapper();
		Member member = modelMapper.map(addManager, Member.class);
		row = memberMapper.insertMemberByMemberVo(member);
		log.debug(A.Z+"[MemberService.addManager.member] member : "+member+A.R);
		
		//Manager 테이블에 insert 요청
		row = memberMapper.insertManagerByAddManagerDTO(addManager);
		log.debug(A.Z+"[MemberService.addManager.insertManagerTable] row : "+row+A.R);

		//addrress 테이블에 insert 요청
		//요청 전 addressVo로 변환
		Address address = modelMapper.map(addManager, Address.class);
		log.debug(A.Z+"[MemberService.addManager.member] member : "+address+A.R);
		row = memberMapper.insertAddressByAddressVo(address);
		log.debug(A.Z+"[MemberService.addManager.insertAddressTable] row : "+row+A.R);

		//변경 테이블 요청전 insert 된 정보 select
		Member manager = memberMapper.selectMemberByMemberId(addManager.getMemberId());
		
		//회원정보 레벨 변경 이력테이블에 insert 요청
		LevelHistory levelHistory = new LevelHistory();
		levelHistory.setMemberId(addManager.getMemberId());
		levelHistory.setLevelHistoryUpdateDate(manager.getMemberUpdateDate());
		levelHistory.setLevelHistoryNewLevel(manager.getMemberLevel());
		levelHistory.setLevelHistoryReason("가입신청");
		levelHistory.setEditor(addManager.getMemberId());
		log.debug(A.Z+"[MemberService.addManager] levelHistory : "+levelHistory+A.R);
		row = memberMapper.insertLevelHistory(levelHistory);
		log.debug(A.Z+"[MemberService.addManager.insertLevelHistory] row : "+row+A.R);
		
		
		//비밀번호 변경 이력테이블에 insert 요청
		//요청전 createDate를 알기 위해 insert 된 정보 select
		log.debug(A.Z+"[MemberService.addManager.selectMemberByMemberId] manager : "+manager+A.R);
		PwHistory pwHistory = new PwHistory();
		pwHistory.setMemberId(addManager.getMemberId());
		pwHistory.setPwHistoryDate(manager.getMemberPwUpdateDate());
		pwHistory.setPwHistoryNewPw(addManager.getMemberPw());;
		row = memberMapper.insertPwHistory(pwHistory);
		log.debug(A.Z+"[MemberService.addManager.insertpwHistoryTable] row : "+row+A.R);
		//최종적으로 pwHistory insert 결과 리턴
		return row;
	}
	//강사 회원 가입 
	public int addTeacher(AddTeacher addTeacher) {
		log.debug(A.Z+"[MemberService.addTeacher.param] addTeacher : "+addTeacher+A.R);
		//mapper 요청 결과 저장할 변수 초기화
		int row = -1;
		
		//member 테이블에 insert 요청
		//요청 전 MemberVo로 변환
		modelMapper = new ModelMapper();
		Member member = modelMapper.map(addTeacher, Member.class);
		row = memberMapper.insertMemberByMemberVo(member);
		log.debug(A.Z+"[MemberService.addTeacher.member] member : "+member+A.R);

		//Teacher 테이블에 insert 요청
		row = memberMapper.insertTeacherByAddTeacherDTO(addTeacher);
		log.debug(A.Z+"[MemberService.addTeacher.insertTeacherTable] row : "+row+A.R);

		
		//addrress 테이블에 insert 요청
		//요청 전 addressVo로 변환
		Address address = modelMapper.map(addTeacher, Address.class);
		log.debug(A.Z+"[MemberService.addTeacher.member] member : "+address+A.R);
		row = memberMapper.insertAddressByAddressVo(address);
		log.debug(A.Z+"[MemberService.addTeacher.insertAddressTable] row : "+row+A.R);
		
		//이력테이블 insert 요청전 입력 시간을 알기 위해 insert 된 정보 select
		Member teacher = memberMapper.selectMemberByMemberId(addTeacher.getMemberId());
		log.debug(A.Z+"[MemberService.addTeacher.selectMemberByMemberId] teacher : "+teacher+A.R);
		
		//회원정보 레벨 변경 이력테이블에 insert 요청
		LevelHistory levelHistory = new LevelHistory();
		levelHistory.setMemberId(addTeacher.getMemberId());
		levelHistory.setLevelHistoryUpdateDate(teacher.getMemberUpdateDate());
		levelHistory.setLevelHistoryNewLevel(teacher.getMemberLevel());
		levelHistory.setLevelHistoryReason("가입신청");
		levelHistory.setEditor(addTeacher.getMemberId());
		log.debug(A.Z+"[MemberService.addTeacher] levelHistory : "+levelHistory+A.R);
		row = memberMapper.insertLevelHistory(levelHistory);
		log.debug(A.Z+"[MemberService.addTeacher.insertLevelHistory] row : "+row+A.R);
		
		//비밀번호 변경 이력테이블에 insert 요청
		PwHistory pwHistory = new PwHistory();
		pwHistory.setMemberId(addTeacher.getMemberId());
		pwHistory.setPwHistoryDate(teacher.getMemberPwUpdateDate());
		pwHistory.setPwHistoryNewPw(addTeacher.getMemberPw());;
		row = memberMapper.insertPwHistory(pwHistory);
		log.debug(A.Z+"[MemberService.addTeacher.insertpwHistoryTable] row : "+row+A.R);
		//이전 실패시에는 이미 리턴 되었으므로 최종적으로 pwHistory insert 결과 리턴
		return row;
	}
	//학생 회원 가입
	public int addStudent(AddStudent addStudent) {
		log.debug(A.Z+"[MemberService.addStudent.param] addStudent : "+addStudent+A.R);
		//mapper 요청 결과 저장할 변수 초기화
		int row = -1;
		
		//member 테이블에 insert 요청
		//요청 전 MemberVo로 변환
		modelMapper = new ModelMapper();
		Member member = modelMapper.map(addStudent, Member.class);
		row = memberMapper.insertMemberByMemberVo(member);
		log.debug(A.Z+"[MemberService.addStudent.member] member : "+member+A.R);
		
		//Student 테이블에 insert 요청
		row = memberMapper.insertStudentByAddStudentDTO(addStudent);
		log.debug(A.Z+"[MemberService.addStudent.insertStudentTable] row : "+row+A.R);
		
		//addrress 테이블에 insert 요청
		//요청 전 addressVo로 변환
		Address address = modelMapper.map(addStudent, Address.class);
		log.debug(A.Z+"[MemberService.addStudent.member] member : "+address+A.R);
		row = memberMapper.insertAddressByAddressVo(address);
		log.debug(A.Z+"[MemberService.addStudent.insertAddressTable] row : "+row+A.R);

		//요청전 입력시간을 알기 위해 insert 된 정보 select
		Member student = memberMapper.selectMemberByMemberId(addStudent.getMemberId());
		log.debug(A.Z+"[MemberService.addStudent.selectMemberByMemberId] student : "+student+A.R);
		
		//회원정보 레벨 변경 이력테이블에 insert 요청
		LevelHistory levelHistory = new LevelHistory();
		levelHistory.setMemberId(addStudent.getMemberId());
		levelHistory.setLevelHistoryUpdateDate(student.getMemberUpdateDate());
		levelHistory.setLevelHistoryNewLevel(student.getMemberLevel());
		levelHistory.setLevelHistoryReason("가입신청");
		levelHistory.setEditor(addStudent.getMemberId());
		log.debug(A.Z+"[MemberService.addStudent] levelHistory : "+levelHistory+A.R);
		row = memberMapper.insertLevelHistory(levelHistory);
		log.debug(A.Z+"[MemberService.addStudent.insertLevelHistory] row : "+row+A.R);
		
		//비밀번호 변경 이력테이블에 insert 요청
		PwHistory pwHistory = new PwHistory();
		pwHistory.setMemberId(addStudent.getMemberId());
		pwHistory.setPwHistoryDate(student.getMemberPwUpdateDate());
		pwHistory.setPwHistoryNewPw(addStudent.getMemberPw());;
		row = memberMapper.insertPwHistory(pwHistory);
		log.debug(A.Z+"[MemberService.addStudent.insertpwHistoryTable] row : "+row+A.R);
		//최종적으로 pwHistory insert 결과 리턴
		return row;
	}
	
//	
//	// Teacher 테이블에 들어가는 강사 회원 가입 
//	public int addTeacher(Teacher teacher) {
//		return memberMapper.insertTeacherByTeacherVo(teacher);
//	}
//	
//	// Member 테이블에 들어가는 강사 회원 가입 
//		public int addTeacher(Member member) {
//			return memberMapper.insertTeacherByMemberVo(member);
//		}
//	
//	// Student 테이블에 들어가는 학생 회원 가입 
//	public int addStudent(AddStudent addstudent) {
//		log.debug(A.E+"[MemberService.addStudent.param] student : "+addstudent+A.R);
//		int row = -1; // 성공 여부 출력할 변수 초기화
//		// Member 테이블에 입력
//		row = memberMapper.insertMemberByAddstudentDto(addstudent);
//		log.debug(A.E+"[MemberService.addStudent.insertMemberByAddstudentDto] row : "+row+A.R);
//		row = -1;
//		
//		// student 테이블에 입력
//		row = memberMapper.insertStudentByAddstudentDto(addstudent);
//		log.debug(A.E+"[MemberService.addStudent.insertStudentByAddstudentDto] row : "+row+A.R);
//		row = -1;
//		// address 테이블에 입력
//		row = memberMapper.insertAddressByAddstudentDto(addstudent);
//		log.debug(A.E+"[MemberService.addStudent.insertAddressByAddstudentDto] row : "+row+A.R);
//		// 마지막까지 성공해야 성공 -> @Transactional 때문 -> 마지막 성공 행 수만 return
//		return row;
//	}

	
	// --------------------------------------- // 
	
	// --------------------------------------- // 
	// 로그인 정보 확인 
	public Member getMemberOne(Member member) {
		return memberMapper.selectMemberOne(member);
	}
	
	// --------------------------------------- // 
	// 아이디 중복 체크 
	public int selectIdCheck(String memberId) {
		log.debug(A.Z+"[MemberService.selectIdCheck.param] memberId : "+memberId+A.R);
		
		return memberMapper.selectIdCheck(memberId);
	}
	
}