package kr.co.nft.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.nft.lms.mapper.MemberMapper;
import kr.co.nft.lms.vo.Manager;
import kr.co.nft.lms.vo.Member;
import kr.co.nft.lms.vo.Student;
import kr.co.nft.lms.vo.Teacher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class MemberService {
	
	@Autowired MemberMapper memberMapper;
	
	
	// Student 테이블에 들어가는 학생 회원 가입 
	public int addStudent(Student student) {
		return memberMapper.insertStudentByStudentVo(student);
	}
	
	// Member 테이블에 들어가는 학생 회원 가입 
	public int addStudent(Member member) {
		return memberMapper.insertStudentByMemberVo(member);
	}
	
	public Member getMemberOne(Member member) {
		return memberMapper.selectMemberOne(member);
	}
	
	public void selectIdCheck(Member member) {
		memberMapper.selectIdCheck(member);
	}
	
}
