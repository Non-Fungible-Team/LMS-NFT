<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>getNoticeByPage</title>
</head>
<body>
	<div>
		<h1>getNoticeByPage</h1>
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
				<c:forEach var="n" items="${noticeList}">
					<tr>
						<td>${n.noticeNo}</td>
						<td><a href="${pageContext.request.contextPath}/all/notice/getNoticeOne?noticeNo=${n.noticeNo}">${n.noticeTitle}</a></td>
						<td>${n.memberId}</td>
						<td>${n.noticeCreateDate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
			<form method="get" action="${pageContext.request.contextPath}/all/notice/getNoticeListByPage">
				<c:if test="${currentPage>1}">
					<button type = "submit" value="${currentPage-1}" >이전</button>
				</c:if>
				<c:if test="${currentPage<lastPage}">
					<button type = "submit" value="${currentPage+1}" >다음</button>
				</c:if>
			</form>
	    <div>
	        <a href="${pageContext.request.contextPath}/manager/notice/addNotice">게시판 입력</a>
	    </div>
	</div>
</body>
</html>