<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>getNoticeOne</title>
</head>
<body>
	<div>
		<h1>getNoticeOne</h1>
		<table border="1">
			<tr>
				<th>번호</th>
				<td>${notice.noticeNo}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${notice.noticeTitle}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${notice.noticeContent}</td>
			</tr>
			<tr>
				<th>블라인드여부</th>
				<td>${notice.noticeBlind}</td>
			</tr>
			<tr>
				<th>맴버ID</th>
				<td>${notice.noticeId}</td>
			</tr>
			<tr>
				<th>읽기권한</th>
				<td>${notice.noticePrivilege}</td>
			</tr>
			<tr>
				<th>생성날짜</th>
				<td>${notice.noticeCreateDate}</td>
			</tr>
			<tr>
				<th>수정날짜</th>
				<td>${notice.noticeUpdateDate}</td>
			</tr>
		</table>
		<ul class="pager">
	      <li><a href="${pageContext.request.contextPath}/manager/notice/modifyNotice?noticeNo=${notice.noticeNo}">수정</a></li>
	      <li><a href="${pageContext.request.contextPath}/notice/removeNotice?noticeNo=${notice.noticeNo}">삭제</a></li>
	      <li><a href="${pageContext.request.contextPath}/notice/getNoticeByPage?noticeNo=${notice.noticeNo}">목록으로</a></li>
	   </ul>
	</div>
</body>
</html>