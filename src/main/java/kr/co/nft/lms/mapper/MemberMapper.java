package kr.co.nft.lms.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.nft.lms.vo.Manager;
import kr.co.nft.lms.vo.Member;
import kr.co.nft.lms.vo.Student;
import kr.co.nft.lms.vo.Teacher;

@Mapper
public interface MemberMapper {
	
	// 학생 회원가입 
	int insertStudentByStudentVo(Student student);
	
	// 학생 회원가입 
	int insertStudentByMemberVo(Member member);
	
	// 로그인
	Member selectMemberOne(Member member);
	
	//id체크
	int selectIdCheck(Member member);

	
}
