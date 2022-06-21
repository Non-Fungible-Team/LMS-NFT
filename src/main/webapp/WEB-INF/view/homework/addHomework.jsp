<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>과제 입력</h1>
	
	<form action="addHomework"method="post">
		<p><label>작성자</label><input type="text" name="memberId"></p>	
		<p><label>강의번호</label><input type="text" name="lectureNo"></p>	
		<p><label>과제제목</label><input type="text" name="homeworkTitle"></p>	
		<p><label>과제내용</label><textarea name="homeworkContent" rows="10" cols="70"></textarea></p>	
		<p><label>시작날짜</label><input type="datetime-local" name="homeworkStartDate"></p>	
		<p><label>마감날짜</label><input type="datetime-local" name="homeworkEndDate"></p>	
		<button type="submit">과제 등록</button>
	</form>
			
</body>
</html>