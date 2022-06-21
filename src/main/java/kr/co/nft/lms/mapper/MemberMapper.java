package kr.co.nft.lms.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.nft.lms.vo.Manager;
import kr.co.nft.lms.vo.Member;
import kr.co.nft.lms.vo.Student;
import kr.co.nft.lms.vo.Teacher;

@Mapper
public interface MemberMapper {
	
	// 학생 회원가입 
	int insertStudent(Member member, Student student);
	
	// 로그인
	Member selectMemberOne(Member member);
	
	//회원가입(멤버)
	int insertMember(Member member);
	
	//회원가입(학생)
	int insertStudent(Student student);
	
	//회원가입(강사)
	int insertTeacher(Teacher teacher);
	
	//회원가입(운영자)
	int insertManager(Manager manager);
	
	//id체크
	int selectIdCheck(Member member);

	
}
