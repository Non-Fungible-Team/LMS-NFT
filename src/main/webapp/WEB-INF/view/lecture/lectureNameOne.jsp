<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>임시 페이지 - ${ lectureNo }</h1>
	<a href="${pageContext.request.contextPath}/teacher/lecture/getStudentLectureByPage?lectureNo=${ lectureNo }">학생 목록</a>	
	<a href="${pageContext.request.contextPath}/teacher/lecture/getAttendList?lectureNo=${ lectureNo }">출석 목록</a>	
</body>
</html>