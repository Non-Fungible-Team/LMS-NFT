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
	<h1>getHomeworkOne</h1>
	<div>
	<p><label>번호</label> <input type="text" name ="homeworkNo" value ="${homeworkOne.homeworkNo}" readonly="readonly"></p>
	<p><label>제목</label> <input type="text" name ="homeworkTitle"  value ="${homeworkOne.homeworkTitle}" readonly="readonly"></p>
	<p><label>작성자</label> <input type="text" name="memberId" value="${homeworkOne.memberId }" readonly="readonly"></p>
	<label>내용</label> <textarea name=homeworkContent rows ="8" cols="70"  readonly="readonly">${homeworkOne.homeworkContent}</textarea><br>
	<p><label>날짜</label> <input type="text" name="homeworkCreateDate" size="15" value = "${homeworkOne.homeworkCreateDate}"readonly="readonly"><p>
	
	
	</div>
</body>
</html>