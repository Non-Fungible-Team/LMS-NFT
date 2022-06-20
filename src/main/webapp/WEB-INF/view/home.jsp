<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>home</title>
</head>
<body>
	<h1>로그인 직후 초기 페이지입니다.</h1>
	<div>
		<c:if test="${loginMember != null}">
			${loginMember.memberId}님 반갑습니다. <a href="${pageContext.request.contextPath}/member/logout">로그아웃</a>
		</c:if>
	</div>
</body>
</html>