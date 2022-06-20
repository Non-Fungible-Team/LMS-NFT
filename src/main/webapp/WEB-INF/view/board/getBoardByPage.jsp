<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>getBoardByPage</title>
</head>
<body>
	<div>
		<h1>getBoardByPage</h1>
		<table border="1">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>회원ID</th>
					<th>생성날짜</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="b" items="${list}">
					<tr>
						<td>${b.boardNo}</td>
						<td><a href="${pageContext.request.contextPath}/getBoardOne?boardNo=${b.boardNo}">${b.boardTitle}</a></td>
						<td>${b.memberId}</td>
						<td>${b.boardCreateDate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<ul class="pager">
			<c:if test="${currentPage>1}">
				<li class="previous"><a href="${pageContext.request.contextPath}/boardGetByPage?currentPage=${currentPage-1}">이전</a></li>
			</c:if>
			<c:if test="${currentPage<lastPage}">
				<li class="next"><a href="${pageContext.request.contextPath}/boardGetByPage?currentPage=${currentPage+1}">다음</a></li>
			</c:if>
		</ul>
	    <div>
	        <a href="${pageContext.request.contextPath}/board/addBoard">게시판 입력</a>
	    </div>
	</div>
</body>
</html>