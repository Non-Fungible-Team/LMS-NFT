<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>getBoardOne</title>
</head>
<body>
	<div>
		<h1>getBoardOne</h1>
		<table border="1">
			<tr>
				<th>번호</th>
				<td>${board.boardNo}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${board.boardTitle}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${board.boardContent}</td>
			</tr>
			<tr>
				<th>블라인드여부</th>
				<td>${board.boardBlind}</td>
			</tr>
			<tr>
				<th>맴버ID</th>
				<td>${board.memberId}</td>
			</tr>
			<tr>
				<th>읽기권한</th>
				<td>${board.boardPrivilege}</td>
			</tr>
			<tr>
				<th>생성날짜</th>
				<td>${board.boardCreateDate}</td>
			</tr>
			<tr>
				<th>수정날짜</th>
				<td>${board.boardUpdateDate}</td>
			</tr>
		</table>
		<ul class="pager">
	      <li><a href="${pageContext.request.contextPath}/modifyBoard?boardNo=${board.boardNo}">수정</a></li>
	      <li><a href="${pageContext.request.contextPath}/removeBoard?oardNo=${board.boardNo}">삭제</a></li>
	      <li><a href="${pageContext.request.contextPath}/getBoardByPage?boardNo=${board.boardNo}">목록으로</a></li>
	   </ul>
	</div>
</body>
</html>