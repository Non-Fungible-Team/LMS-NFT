package kr.co.nft.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.nft.lms.mapper.MemberMapper;
import kr.co.nft.lms.vo.Manager;
import kr.co.nft.lms.vo.Member;
import kr.co.nft.lms.vo.Student;
import kr.co.nft.lms.vo.Teacher;


@Service
@Transactional
public class MemberService {
	
	@Autowired MemberMapper memberMapper;
	// 운영자 가입
	public void insertManager(Member member, Manager manager){
		memberMapper.insertMember(member);
		memberMapper.insertManager(manager);
	}
	
	// 강사 가입
	public void insertTeacher(Member member, Teacher teacher) {
		memberMapper.insertMember(member);
		memberMapper.insertTeacher(teacher);
	}
	
	// 학생 가입
	public void insertStudent(Member member, Student student) {
		memberMapper.insertMember(member);
		memberMapper.insertStudent(student);
	}
	
	public void selectIdCheck(Member member) {
		memberMapper.selectIdCheck(member);
	}
	
}
