<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>


</script>
</head>
<body>
	<h1>과제 상세보기</h1>
		<div>
			<p><label>번호</label> <input type="text" name ="homeworkNo" value ="${homeworkOne.homeworkNo}" readonly="readonly"></p>
			<p><label>제목</label> <input type="text" name ="homeworkTitle"  value ="${homeworkOne.homeworkTitle}" readonly="readonly"></p>
			<p><label>작성자</label> <input type="text" name="memberId" value="${homeworkOne.memberId }" readonly="readonly"></p>
			<label>내용</label> <textarea name=homeworkContent rows ="8" cols="70"  readonly="readonly">${homeworkOne.homeworkContent}</textarea><br>
			<p><label>날짜</label> <input type="text" name="homeworkCreateDate" size="15" value = "${homeworkOne.homeworkCreateDate}"readonly="readonly"><p>
		</div>
	<form action="removeHomework" method="post">
	<input type="hidden" name ="homeworkNo" value ="${homeworkOne.homeworkNo}" readonly="readonly">
	<button><a href="${pageContext.request.contextPath}/homework/getHomeworkListByPage">과제 목록</a></button>
	<button><a href="${pageContext.request.contextPath}/homework/modifyHomework?homeworkNo=${homeworkOne.homeworkNo}">과제 수정</a></button>
		<button type="submit">과제 삭제</button>
	</form>
</body>
</html>