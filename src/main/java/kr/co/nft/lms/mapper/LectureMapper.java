package kr.co.nft.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.nft.lms.vo.Lecture;
@Mapper
public interface LectureMapper {
	//1.강의 목록(상세보기)- 전체 강의에 대한
	//강의데이터가 키값으로 전달받음, List로 반환
	List<Lecture> selectLectureByPage(Map<String,Object> map);
	
	//2.강의 삽입 - action
	//int형으로 반환, notice 객체 값? 전달받음
	int insertLecture(Lecture lecture);
	
	//3.강의 수정 - form, action
	//form : Lecture 객체타입으로 반환, 번호값 전달받음
	Lecture updateLectureForm(int lectureNo);
	
	//action : int형으로 반환, notice객체값 전달됨(여러 값 수정)
	int updateLecture(Lecture lecture);
	
	//4.강의 삭제 - action
	//int형으로 반환, 번호값 전달받음
	int deleteLecture(int lectureNo);
	
	//student-lecture -> 뷰만 다름(가져오는 값이 다름)
	//5.학생_강의 목록 - 강의별 학생의 목록(운영자,강사)
	//6.학생 수강목록 - 학생이 자기가 듣는 수강하는 강의 목록
	//List로 반환, 키값으로 데이터 전달받음.
	List<Lecture> selectStudentLecture(Map<String, Object> map);
	
}
