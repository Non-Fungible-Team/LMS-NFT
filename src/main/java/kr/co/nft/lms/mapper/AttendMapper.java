package kr.co.nft.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.nft.lms.vo.Attend;
@Mapper
public interface AttendMapper {
	
	//1.강의별 전체 학생 출석 목록 - 강사/운영자
	List<Attend> selectAttendList(Map<String,Object> map);
	
	//2.학생이 자신이 듣는 강의별 출석 확인 
	List<Attend> selectStudentAttendOne(Map<String,Object> map);
	
	//3.학생 출석 삽입
	int insertAttend(Attend attend); 
	
	//4.학생 출석 수정(폼,액션)
	Attend updateAttendForm(String memberId);//FORM
	int updateAttend(Attend attend);//ACTION
	
	//5.학생 출석 삭제  
	int deleteAttend(String memberId);
	
}
