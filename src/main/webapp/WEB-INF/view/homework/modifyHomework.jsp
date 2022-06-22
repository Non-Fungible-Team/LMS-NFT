<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>과제 수정</h1>
	<form action="modifyHomework" method="post">
		<div>
			<input type="hidden" name ="homeworkNo" value ="${homeworkOne.homeworkNo}" readonly="readonly">
			<p><label>제목</label> <input type="text" name ="homeworkTitle"  value="${homeworkOne.homeworkTitle}"></p>
			<label>내용</label> <textarea name=homeworkContent rows ="8" cols="70"  placeholder="${homeworkOne.homeworkContent}">${homeworkOne.homeworkContent}</textarea><br>
			<p><label>시작날짜</label><input type="datetime-local" name="homeworkStartDate"></p>	
			<p><label>마감날짜</label><input type="datetime-local" name="homeworkEndDate"></p>
			<button type ="submit" class="btn btn-default">완료</button>
			<input type="reset" class="btn btn-default" value="초기화">
		</div>
	</form>

</body>
</html>