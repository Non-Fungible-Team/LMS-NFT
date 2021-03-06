package kr.co.nft.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.nft.lms.vo.Lecture;
import kr.co.nft.lms.vo.LectureRoom;
import kr.co.nft.lms.vo.ManagerLecture;
import kr.co.nft.lms.vo.StudentLecture;
import kr.co.nft.lms.vo.Teacher;
import kr.co.nft.lms.vo.TeacherLecture;

@Mapper
public interface LectureMapper {
	//1.강의 목록(상세보기)- 전체 강의에 대한
	//강의데이터가 키값으로 전달받음, List로 반환
	List<Lecture> selectLectureByPage(Map<String, Object> map);
	//페이징을 위한 전체 행의 수
	int selectLectureCount();
	//1-1.수강 중인 강의 목록(학생)
	List<Lecture> selectLectureByStudent(Map<String, Object> map);
	//페이징을 위한 전체 행의 수
	int selectLectureTotalCountByStudent();
	//1-2.수강 중인 강의 목록(강사)
	List<Lecture> selectLectureByTeacher(Map<String, Object> map);
	//페이징을 위한 전체 행의 수
	int selectLectureTotalCountByTeacher();

	//2.강의 삽입 - action
	//int형으로 반환, notice 객체 값? 전달받음
	int insertLecture(Lecture lecture);

	//2-2 강의_강사 삽입
	int insertTeacherLecture(Lecture lecture);

	//3.강의 수정 - form, action
	//form : Lecture 객체타입으로 반환, 번호값 전달받음
	Lecture updateLectureForm(int lectureNo);

	//action : int형으로 반환, notice객체값 전달됨(여러 값 수정)
	int updateLecture(Lecture lecture);
	
	//3-2 강의-강사 수정
	int updateTeacherLecture(TeacherLecture teacherLecture);

	//4.강의 삭제 - action
	//int형으로 반환, 번호값 전달받음
	int deleteLecture(int lectureNo);
	
	//4-2.강의-강사 삭제
	int deleteTeacherLecture(int lectureNo);

	//5.학생-강의 목록 - 강의별 학생의 목록(운영자,강사)
	//List로 반환, 키값으로 데이터 전달받음.
	List<StudentLecture> selectStudentLecture(Map<String, Object> map);
	List<StudentLecture> selectStudentLectureAll(Map<String, Object> map);

	//5-2.학생-강의 삽입
	int insertStudentLecture(StudentLecture studentLecture);

	//5-3. 학생-강의 목록 수정(학생아이디 누르면 상세정보)
	//수정폼(학생개인 수강정보)
	StudentLecture updateStudentLectureForm(StudentLecture studentLecture);

	//수정액션
	int updateStudentLecture(StudentLecture studentLecture);

	//5-4. 학생-강의 목록 삭제
	int deleteStudentLecture(StudentLecture studentLecture);

	//6.운영자_강의 삽입
	int insertManagerLecture(ManagerLecture managerLecture);

	//7-2. 강의-학생 전체 행
	int selectStudentLectureCount();

	//8. 강의-강사 목록
	List<TeacherLecture> selectTeacherLectureByPage(Map<String,Object> map);
	
	//8-2. 강의_강사 목록 전체행
	int selectTeacherLectureCount();
	
	//9. 강의 번호로 강의 하나의 정보를 select
	Lecture selectLecturebyLectureNo(int lectureNo);
	
	//10-1. 강의 삽입시에, 개강일과 수료일이 정해지면, 그 기간에 수업 가능한 강사 목록
	List<Teacher> selectTeacherListByDates(String lectureStartDate,String lectureEndDate);
	//10-2. 강의 삽입시에, 개강일과 수료일이 정해지면, 그 기간에 사용가능한 강의실 목록
	List<LectureRoom> selectLectureRoomListByDates(String lectureStartDate,String lectureEndDate);
	
	// 강의 이름과 강의 번호만 select
	List<Lecture> selectLectureNoNameList();
	
	//차트 - 강사별 수업시간 통계
	List<Map<String,Object>> selectTeacherLectureChart();
}