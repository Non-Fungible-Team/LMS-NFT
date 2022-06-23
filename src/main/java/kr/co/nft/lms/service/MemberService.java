package kr.co.nft.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.nft.lms.mapper.MemberMapper;
import kr.co.nft.lms.vo.LevelHistory;
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
	
	// 학생의 레벨이 변경되면 `level_history` 테이블에 튜플을 남긴다 
	public int addLevelHistoryOfStudentRecord(Member member) {
		return memberMapper.insertLevelHistoryOfStudentRecord(member);
	}
	
	// Member 테이블의 학생 튜플 삭제, 삭제시 순서가 밀린다 
	public int freezeStudentOfMemberTbl(Member member) {
		return memberMapper.updateStudentOfMemberTbl(member);
	}
	
	// Student 테이블의 학생 레코드 삭제, 삭제시 순서가 먼저 와야한다 
	public int freezeStudentOfStudentTbl(Member member) {
		return memberMapper.updateStudentOfStudentTbl(member);
	}
	
	// Member 테이블 활용하여 학생 수정 액션
	public int modifyStudent(Member member) {
		return memberMapper.updateStudentByMemberVo(member);
	}
	
	// Student 테이블 활용하여 학생 수정 액션 
	public int modifyStudent(Student student) {
		return memberMapper.updateStudentByStudentVo(student);
	}
	
	// Member 테이블 활용하여 학생 상세 조회 
	public Member getStudentOneReturnMemberVo(Member member) {
		return memberMapper.selectStudentOneByMemberVo(member);
	}
	
	// Student 테이블 활용하여 학생 상세 조회 
	public Student getStudentOneReturnStudentVo(Member member) {
		return memberMapper.selectStudentOneByStudentVo(member);
	}
	
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
