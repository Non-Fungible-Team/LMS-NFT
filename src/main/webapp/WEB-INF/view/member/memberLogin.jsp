<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberLogin</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>

</script>
</head>
<body>
	<h1>로그인 페이지</h1>
	<c:if test="${not empty param.level}">
		<form id="loginForm" method="post" action="${pageContext.request.contextPath}/member/login">
		<input type="hidden" name="memberLevel" value="${param.level}">
         <table class="table table-striped">
            <tr>
               <td>아이디</td>
               <td><input type="text" name="memberId"></td>
            </tr>
            <tr>
               <td>비밀번호</td>
               <td><input type="password" name="memberPw"></td>
            </tr>
            <tr>
               <td colspan="2"><button type="submit">로그인</button></td>
            </tr>
         </table>
      </form>
	</c:if>
	
	<div>
		<a href="${pageContext.request.contextPath}/member/login">관리자 로그인</a>
		<a href="${pageContext.request.contextPath}/member/login">강사 로그인</a>
		<a href="${pageContext.request.contextPath}/member/login?level=1">학생 로그인</a>
	</div>
	
</body>
</html>