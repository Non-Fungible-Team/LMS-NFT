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
	<h1>과제 목록</h1>

	<table>
		<thead>
			<tr>
				<th>순번</th>
				<th>강의번호</th>
				<th>과제</th>
				<th>제출 기간</th>
				<th>작성자</th>
				<th>homeworkCreateDate</th>
				<th>homeworkUpdateDate</th>
				<th><input type="hidden" value ="homeworkContent"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="h" items="${homeworkList}" >
				<tr>
					<td>${h.homeworkNo }</td>
					<td>${h.lectureNo }</td>
					<td><a href="${pageContext.request.contextPath}/homework/getHomeworkOne?homeworkNo=${h.homeworkNo}">${h.homeworkTitle }</a></td>
					<td>${h.homeworkStartDate } ~ ${h.homeworkEndDate }</td>
					<td>${h.memberId }</td>
					<td>${h.homeworkCreateDate }</td>
					<td>${h.homeworkUpdateDate }</td>
					<td><input type="hidden" value="${h.homeworkContent }"></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
		<button><a href="${pageContext.request.contextPath}/homework/getHomeworkListByPage?currentPage=${currentPage-1}">이전</a></button>
		<button><a href="${pageContext.request.contextPath}/homework/getHomeworkListByPage?currentPage=${currentPage+1}">다음</a></button>
	<hr>
	<div>
        <button><a class="btn btn-default" href="${pageContext.request.contextPath}/homework/addHomework">과제 입력</a></button>
    </div>
	
</body>
</html>