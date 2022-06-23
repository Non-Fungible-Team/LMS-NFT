<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<h1>설문조사</h1>
		<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>내용</th>
				<th>생성일</th>
				<th>설문시작일</th>
				<th>설문마감일</th>
				<th>강의</th>
				<th>작성자</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="sv" items="${surveyList}">
				<tr>
					<td>${sv.surveyNo}</td>
					<td><a href="${pageContext.request.contextPath}/getSurveyOne?surveyNo=${sv.surveyNo}">${sv.surveyTitle}</a></td>
					<td>${sv.surveyContent}</td>
					<td>${sv.surveyCreateDate}</td>
					<td>${sv.surveyStartlineDate}</td>
					<td>${sv.surveyDeadlineDate}</td>
					<td>${sv.lectureNo}</td>
					<td>${sv.memberId}</td>
				</tr>
			</c:forEach>
		</tbody>
		</table>
		<ul>
		<c:if test="${currentPage>1}">
			<li><a
				href="${pageContext.request.contextPath}/getSurveyListByPage?currentPage=${currentPage-1}">이전</a></li>
		</c:if>
		<c:if test="${currentPage<lastPage}">
			<li><a
				href="${pageContext.request.contextPath}/getSurveyListByPage?currentPage=${currentPage+1}">다음</a></li>
		</c:if>
		</ul>

		<div>
			<a class="btn btn-default"
				href="${pageContext.request.contextPath}/survey/insertSurvey">설문조사 등록</a>
		</div>
	</div>
</body>
</html>