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
		<h1>설문조사 항목 리스트</h1>
		<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>내용</th>
				<th>생성일</th>
				<th>업데이트날짜</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="svql" items="${SurveyQuestionList}">
				<tr>
					<td>${svql.surveyQuestionListNo}</td>
					<td>${svql.surveyQuestionListName}</td>
					<td>${svql.surveyQuestionListContent}</td>
					<td>${svql.surveyQuestionListCreateDate}</td>
					<td>${svql.surveyQuestionListUpdateDate}</td>
				</tr>
			</c:forEach>
		</tbody>
		</table>
		<ul>
		<c:if test="${currentPage>1}">
			<li><a
				href="${pageContext.request.contextPath}/manager/getSurveyQuestionListByPage?currentPage=${currentPage-1}">이전</a></li>
		</c:if>
		<c:if test="${currentPage<lastPage}">
			<li><a
				href="${pageContext.request.contextPath}/manager/getSurveyQuestionListByPage?currentPage=${currentPage+1}">다음</a></li>
		</c:if>
		</ul>

		<div>
			<a class="btn btn-default"
				href="${pageContext.request.contextPath}/manager/insertSurveyList">항목 입력</a>
		</div>
	</div>
</body>
</html>