package kr.co.nft.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.nft.lms.dto.AddManager;
import kr.co.nft.lms.dto.AddStudent;
import kr.co.nft.lms.dto.AddTeacher;
import kr.co.nft.lms.vo.Address;
import kr.co.nft.lms.vo.LevelHistory;
import kr.co.nft.lms.vo.Manager;
import kr.co.nft.lms.vo.Member;
import kr.co.nft.lms.vo.MemberPhoto;
import kr.co.nft.lms.vo.PwHistory;
import kr.co.nft.lms.vo.Student;
import kr.co.nft.lms.vo.Teacher;

@Mapper
public interface MemberMapper {
	
	// -------------- 차트 뽑기 ------------------ //
	
	// 지역별 학생 수 차트 뽑기
	List<Address> selectStudentProvinceList();
	

	// -------------- 운영자가 보는 목록 뽑기 ------------------ //
	// 학생 테이블 전체 행 개수 출력  
	int selectStudentTotalCount();
	// 강사 테이블 전체 행 개수 출력 
	int selectTeacherTotalCount();
	
	
	// 강사 테이블 필드 뽑기 
	List<Teacher> selectTeacherByPage(Map<String, Object> map);
	//전체 행의 수
	int selectTeacherTotalCountByMemberLevel(Map<String,Object> map);

	// 운영자 테이블 필드 뽑기 
	List<Manager> selectManagerByPage(Map<String, Object> map);
	//전체 행의 수
	int selectManagerTotalCountByMemberLevel(Map<String,Object> map);
	
	
	// 학생 테이블 필드 뽑기 
	List<Student> selectStudentByPage(Map<String, Object> map);
	//전체 행의 수
	int selectStudentTotalCountByMemberLevel(Map<String,Object> map);
	
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
	
	// 이미 사진 등록한 사용자가 사진을 삭제하고 재등록 하는 경우
	int deleteMemberPhoto(Member member);
	
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
	
	// Member 테이블 활용하여 학생 정보 수정 updateStudentByMemberVo 
	int updateStudentByMemberVo(Member member);
	
	// Student 테이블 활용하여 학생 정보 수정 
	int updateStudentByStudentVo(Student student);
	
	// -------------- 상세 보기 ------------------ //  
	
	// Member, Teacher 테이블 활용하여 강사 상세보기 
	Map<String, Object> selectManagerOne(Member member);
	
	// Member, Teacher 테이블 활용하여 강사 상세보기 
	Map<String, Object> selectTeacherOne(Member member);
	
	// Member, Student 테이블 활용하여 학생 상세보기 
	Map<String, Object> selectStudentOne(Member member);
	
	// 아래 코드는 시행착오 //
	
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
	
	// MemberId로 Member 정보 가져오기 
	Member selectMemberByMemberId(String memberId);
	
	// -------------- 회원 가입 ------------------ //  
	
	// AddManager DTO 필드 활용하여 운영자 회원가입
	int insertManagerByAddManagerDTO(AddManager addManager);
	
	// AddTeacher DTO 필드 활용하여 강사 회원가입 
	int insertTeacherByAddTeacherDTO(AddTeacher addteacher);
	
	// AddStudent DTO 필드 활용하여 학생 회원가입 
	int insertStudentByAddStudentDTO(AddStudent addstudent);
	
	// Member VO 필드 활용하여 모든 회원 회원가입 
	int insertMemberByMemberVo(Member member);
	
	// address Vo 필드 활용하여 address 테이블 기입 
	int insertAddressByAddressVo(Address address);
	
	//----------멤버아이디에 따른 멤버 레벨 변경------------//
	int updateMemberLevel(Member member);
	// -----------------비밀번호 변경 이력 테이블---------------------- //  
	int insertPwHistory(PwHistory pwHistory);
	// -----------------회원 레벨 변경 이력 테이블---------------------- //  
	int insertLevelHistory(LevelHistory levelHistory);
	// 로그인
	
	Member selectMemberOne(Member member);
	
	// --------------------------------------------- //  
	
	// id 중복 체크
	
	int selectIdCheck(String memberId);

	// --------------------------------------------- //  
}