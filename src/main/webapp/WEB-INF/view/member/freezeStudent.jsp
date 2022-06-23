<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>removeStudent</title>
</head>
<body>
	<h1>removeStudent</h1>
	<form action="${pageContext.request.contextPath}/all/freezeStudent" method="post">
		<table border="1">
			<tr>
				<td>학생 아이디</td>
				<td>
					<input type="text" name="memberId" id="memberId" value="${loginMember.memberId}" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td>레벨</td>
				<td>
					<input type="text" name="memberLevel" id="memberPw" value="${loginMember.memberLevel}">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<button type = "submit" id="submit">학생 정보 삭제</button> <!-- 삭제라 쓰고 휴면으로 처리한다 -->
				</td>
			</tr>
		</table>
	</form>
</body>
</html>