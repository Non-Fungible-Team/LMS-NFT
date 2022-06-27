package kr.co.nft.lms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.nft.lms.vo.LevelHistory;
import kr.co.nft.lms.vo.Manager;
import kr.co.nft.lms.vo.Member;
import kr.co.nft.lms.vo.MemberPhoto;
import kr.co.nft.lms.vo.Student;
import kr.co.nft.lms.vo.Teacher;

@Mapper
public interface MemberMapper {
	
	// 관리자 전체 리스트
	List<Manager> selectManagerList();
	
	// 학생 전체 리스트
	List<Student> selectStudentList();
	
	// 강사 전체 리스트
	List<Teacher> selectTeacherList();
	
	// Member 테이블의 학생 레코드 삭제 
	int updateStudentOfMemberTbl(Member member);
	
	// LEVEL_HISTORY 테이블에 이력 남기기 
	int insertLevelHistoryOfStudentRecord(Member member);
	
	// Student 테이블의 학생 튜플 삭제 
	int updateStudentOfStudentTbl(Member member);
	
	// MemberPhoto 튜플 정보 가져오기 
	MemberPhoto selectMemberPhoto(Member member);
	
	// MemberPhoto 테이블에 회원 사진 등록
	int insertMemberPhoto(MemberPhoto memberPhoto);
	
	// Member 테이블 활용하여 학생 정보 수정 
	int updateStudentByMemberVo(Member member);
	
	// Student 테이블 활용하여 학생 정보 수정 
	int updateStudentByStudentVo(Student student);
	
	// -------------- 상세 보기 ------------------ //  
	
	// Member 테이블 활용하여 강사 상세보기 
	Member selectTeacherOneByMemberVo(Member member);
	
	// Teacher 테이블 활용하여 강사 상세보기 
	Teacher selectTeacherOneByTeacherVo(Member member);
	
	// Member 테이블 활용하여 학생 상세보기 
	Member selectStudentOneByMemberVo(Member member);
	
	// Student 테이블 활용하여 학생 상세보기 
	Student selectStudentOneByStudentVo(Member member);
	
	// -------------- 회원 가입 ------------------ //  
	
	// Manager VO 필드 활용하여 운영자 회원가입
	int insertManagerByManagerVo(Manager manager);
	
	// Teacher VO 필드 활용하여 강사 회원가입 
	int insertTeacherByTeacherVo(Teacher teacher);
	
	// Student VO 필드 활용하여 학생 회원가입 
	int insertStudentByStudentVo(Student student);
	
	// Member VO 필드 활용하여 운영자 회원가입 
	int insertManagerByMemberVo(Member member);
	
	// Member VO 필드 활용하여 강사 회원가입
	int insertTeacherByMemberVo(Member member);
	
	// Member VO 필드 활용하여 학생 회원가입 
	int insertStudentByMemberVo(Member member);
	
	// --------------------------------------------- //  
	
	// 로그인
	Member selectMemberOne(Member member);
	
	//id 체크
	int selectIdCheck(Member member);

	
}
