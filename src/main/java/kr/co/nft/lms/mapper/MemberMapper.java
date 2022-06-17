package kr.co.nft.lms.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.nft.lms.vo.Member;

@Mapper
public interface MemberMapper {
	//회원가입(멤버)
	String insertMember(Member member);
	
	//회원가입(학생)
	String insertStudent(Member member);
	
	//회원가입(강사)
	String insertTeacher(Member member);
	
	//회원가입(운영자)
	String insertManager(Member member);
	
}
