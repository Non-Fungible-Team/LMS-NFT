package kr.co.nft.lms.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.nft.lms.mapper.MemberMapper;
import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.LevelHistory;
import kr.co.nft.lms.vo.Manager;
import kr.co.nft.lms.vo.Member;
import kr.co.nft.lms.vo.MemberPhoto;
import kr.co.nft.lms.vo.MemberUploadPhoto;
import kr.co.nft.lms.vo.Student;
import kr.co.nft.lms.vo.Teacher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class MemberService {

	// Service 여기서 사진 파일 가공 

	@Autowired MemberMapper memberMapper;
	
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
	
	// Member 테이블 활용하여 학생 수정 액션
	public int modifyStudent(Member member) {
		return memberMapper.updateStudentByMemberVo(member);
	}
	
	// Student 테이블 활용하여 학생 수정 액션 
	public int modifyStudent(Student student) {
		return memberMapper.updateStudentByStudentVo(student);
	}
	
	// --------------------------------------- // 
	// 상세보기 
	
	// Member 테이블 활용하여 운영자 상세 조회 
	public Member getManagerOneReturnMemberVo(Member member) {
		return memberMapper.selectManagerOneByMemberVo(member);
	}
	
	// Manager 테이블 활용하여 운영자 상세 조회 
	public Manager getManagerOneReturnManagerVo(Member member) {
		return memberMapper.selectManagerOneByManagerVo(member);
	}
	
	// Member 테이블 활용하여 강사 상세 조회 
	public Member getTeacherOneReturnMemberVo(Member member) {
		return memberMapper.selectTeacherOneByMemberVo(member); 
	}
	
	// Teacher 테이블 활용하여 강사 상세 조회 
	public Teacher getTeacherOneReturnTeacherVo(Member member) {
		return memberMapper.selectTeacherOneByTeacherVo(member);
	}
	
	// Member 테이블 활용하여 학생 상세 조회 
	public Member getStudentOneReturnMemberVo(Member member) {
		return memberMapper.selectStudentOneByMemberVo(member);
	}
	
	// Student 테이블 활용하여 학생 상세 조회 
	public Student getStudentOneReturnStudentVo(Member member) {
		return memberMapper.selectStudentOneByStudentVo(member);
	}
	
	// --------------------------------------- // 
	// 회원가입 
	
	// Manager 테이블에 들어가는 운영자 회원 가입 
	public int addManager(Manager manager) {
		return memberMapper.insertManagerByManagerVo(manager);
	}
	
	// Member 테이블에 들어가는 운영자 회원 가입 
		public int addManager(Member member) {
			return memberMapper.insertManagerByMemberVo(member);
		}
	
	// Teacher 테이블에 들어가는 강사 회원 가입 
	public int addTeacher(Teacher teacher) {
		return memberMapper.insertTeacherByTeacherVo(teacher);
	}
	
	// Member 테이블에 들어가는 강사 회원 가입 
		public int addTeacher(Member member) {
			return memberMapper.insertTeacherByMemberVo(member);
		}
	
	// Student 테이블에 들어가는 학생 회원 가입 
	public int addStudent(Student student) {
		return memberMapper.insertStudentByStudentVo(student);
	}

	// Member 테이블에 들어가는 학생 회원 가입 
	public int addStudent(Member member) {
		return memberMapper.insertStudentByMemberVo(member);
	}
	
	// --------------------------------------- // 
	
	// 학생 상세 정보 확인 
	public Member getMemberOne(Member member) {
		return memberMapper.selectMemberOne(member);
	}
	
	public void selectIdCheck(Member member) {
		memberMapper.selectIdCheck(member);
	}
	
}
