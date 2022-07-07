package kr.co.nft.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.nft.lms.dto.AddStudent;
import kr.co.nft.lms.vo.Manager;
import kr.co.nft.lms.vo.Member;
import kr.co.nft.lms.vo.MemberPhoto;
import kr.co.nft.lms.vo.Student;
import kr.co.nft.lms.vo.Teacher;

@Mapper
public interface MemberMapper {
	
	// -------------- 운영자가 보는 목록 뽑기 ------------------ //
	
	// 강사 테이블 전체 행 개수 출력 
	int selectTeacherTotalCount();
	
	// 강사 테이블 필드 뽑기 
	List<Teacher> selectTeacherByPage(Map<String, Object> map);
	
	// 학생 테이블 전체 행 개수 출력  
	int selectStudentTotalCount();
	
	// 학생 테이블 필드 뽑기 
	List<Student> selectStudentByPage(Map<String, Object> map);
	
	// -------------- 목록 뽑기 ------------------ //
	// ~에서 사용함. 
	
	// 사용자 전체 리스트
	List<Member> selectMemberList();
	
	// 관리자 전체 리스트
	List<Manager> selectManagerList();
	
	// 학생 전체 리스트
	List<Student> selectStudentList();
	
	// 강사 전체 리스트
	List<Teacher> selectTeacherList();
	
	// -------------- 개인 정보 휴면 처리 ------------------ // 
	
	// 일정 기간이 지나도 접속을 하지 않은 사용자 휴면 계정 처리 
	int updateDormantMember();
	
	// -------------- 개인 정보 삭제(탈퇴) ------------------ // 
	
	// Member 테이블의 운영자 레코드 삭제 
	int updateManagerOfMemberTbl(Member member);
	
	// LEVEL_HISTORY 테이블에 이력 남기기 
	int insertLevelHistoryOfManagerRecord(Member member);
	
	// Member 테이블의 강사 레코드 삭제 
	int updateTeacherOfMemberTbl(Member member); 
	
	// LEVEL_HISTORY 테이블에 이력 남기기 
	int insertLevelHistoryOfTeacherRecord(Member member);
	
	// Teacher 테이블의 강사 튜플 삭제 
	int updateTeacherOfTeacherTbl(Member member);
	
	// Member 테이블의 학생 레코드 삭제 
	int updateStudentOfMemberTbl(Member member);
	
	// LEVEL_HISTORY 테이블에 이력 남기기 
	int insertLevelHistoryOfStudentRecord(Member member);
	
	// Student 테이블의 학생 튜플 삭제 
	int updateStudentOfStudentTbl(Member member);
	
	// -------------- 개인 정보 수정 ------------------ // 
	
	// 비밀번호 변경시 비밀번호 이력 테이블 함께 변경 
	int updatePwHistory(Member member);
	
	// 회원 비밀번호 변경 
	int updateMemberPw(Member member);
	
	// MemberPhoto 튜플 정보 가져오기 
	MemberPhoto selectMemberPhoto(Member member);
	
	// MemberPhoto 테이블에 회원 사진 등록
	int insertMemberPhoto(MemberPhoto memberPhoto);
	
	// Member 테이블 활용하여 운영자 정보 수정 
	int updateManagerByMemberVo(Member member);
	
	// Manager 테이블 활용하여 운영자 정보 수정 
	int updateManagerByManagerVo(Manager manager);
	
	// Member 테이블 활용하여 강사 정보 수정 
	int updateTeacherByMemberVo(Member member);
	
	// Teacher 테이블 활용하여 강사 정보 수정 
	int updateTeacherByTeacherVo(Teacher teacher);
	
	// Member 테이블 활용하여 학생 정보 수정 
	int updateStudentByMemberVo(Member member);
	
	// Student 테이블 활용하여 학생 정보 수정 
	int updateStudentByStudentVo(Student student);
	
	// -------------- 상세 보기 ------------------ //  
	
	// Member, Student 테이블 활용하여 학생 상세보기 
	Map<String, Object> selectStudentOne(Member member);
	
	// Member 테이블 활용하여 운영자 상세보기 
	Member selectManagerOneByMemberVo(Member member);
	
	// Manager 테이블 활용하여 운영자 상세보기 
	Manager selectManagerOneByManagerVo(Member member);
	
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
	
	// addStudent DTO 필드 활용하여 MEMBER 테이블 기입 
	int insertMemberByAddstudentDto(AddStudent member);

	// addStudent DTO 필드 활용하여 STUDENT 테이블 기입 
	int insertStudentByAddstudentDto(AddStudent student);
	
	// addStudent DTO 필드 활용하여 ADDRESS 테이블 기입
	int insertAddressByAddstudentDto(AddStudent student);
	
	// --------------------------------------------- //  
	
	// 로그인
	
	Member selectMemberOne(Member member);
	
	// --------------------------------------------- //  
	
	// id 중복 체크
	
	int selectIdCheck(String memberId);

	// --------------------------------------------- //  
}