<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>설문조사</h1>
	<a href="${pageContext.request.contextPath}/survey/getSurveyListByPage"
			class="btn">이전으로</a>
			<table>
				<tr>
				<th>공지 번호</th>
				<td>${getSurveyOne.surveyNo}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${getSurveyOne.surveyTitle}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${getSurveyOne.surveyContent}</td>
			</tr>
			<tr>
				<th>등록일</th>
				<td>${getSurveyOne.surveyDate}</td>
			</tr>
		</table>
	<h2>답변등록</h2>
	<form>
		<table>
			<c:forEach var="sqa" items="SurveyQuestionList">
				
			</c:forEach>
		</table>
	</form>
</body>
</html>