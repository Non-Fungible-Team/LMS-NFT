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
	<!-- 값을 Controller단에서 가공하고 Model로 값을 받았다면 level은 그냥 받을 수 있다.  -->
	<!-- 값을 가공하지 않고 바로 받으려면 `param.level`과 같이 받으면 된다.  -->
	<c:if test="${not empty param.level}">
		<form id="loginForm" method="post" action="${pageContext.request.contextPath}/member/login">
		<input type="hidden" name="memberLevel" value="${param.level}">
         <table>
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
		<a href="${pageContext.request.contextPath}/member/login?level=3">관리자 로그인</a>
		<a href="${pageContext.request.contextPath}/member/login?level=2">강사 로그인</a>
		<a href="${pageContext.request.contextPath}/member/login?level=1">학생 로그인</a>
	</div>
	
</body>
</html>