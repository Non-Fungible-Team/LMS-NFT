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
	<h1>getHomeworkListByPage</h1>

	<table>
		<thead>
			<tr>
				<th>homeworkNo</th>
				<th>memberId</th>
				<th>lectureNo</th>
				<th>homeworkTitle</th>
				<th>homeworkContent</th>
				<th>homeworkStartDate</th>
				<th>homeworkEndDate</th>
				<th>homeworkCreateDate</th>
				<th>homeworkUpdateDate</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="h" items="${homeworkList}">
				<tr>
					<td>${h.homeworkNo }</td>
					<td>${h.memberId }</td>
					<td>${h.lectureNo }</td>
					<td><a href="${pageContext.request.contextPath}/homework/getHomeworkOne?homeworkNo=${h.homeworkNo}">${h.homeworkTitle }</a></td>
					<td>${h.homeworkContent }</td>
					<td>${h.homeworkStartDate }</td>
					<td>${h.homeworkEndDate }</td>
					<td>${h.homeworkCreateDate }</td>
					<td>${h.homeworkUpdateDate }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
		<button><a href="${pageContext.request.contextPath}/homework/getHomeworkListByPage?currentPage=${currentPage-1}">이전</a></button>
		<button><a href="${pageContext.request.contextPath}/homework/getHomeworkListByPage?currentPage=${currentPage+1}">다음</a></button>
	<hr>
	
</body>
</html>