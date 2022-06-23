<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modifyStudent</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>

</script>
</head>
<body>
	<h1>modifyStudent</h1>
	<form action="${pageContext.request.contextPath}/all/modifyStudent" method="post" enctype="multipart/form-data">
		<table border="1">
		
			<tr>
				<td>학생 아이디</td>
				<td>
					<input type="text" name="memberId" id="memberId" value="${loginMember.memberId}" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td>학생 이름</td>
				<td>
					<input type="text" name="studentName" id="studentName" value="${getStudentOneByStudentVo.studentName}">
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>
					<input type="text" name="memberPw" id="memberPw" value="${getStudentOneByMemberVo.memberPw}">
				</td>
			</tr>
			<tr>
				<td>생일</td>
				<td>
					<input type="date" name="studentBirth" id="studentBirth" value="${getStudentOneByStudentVo.studentBirth}">
				</td>
			</tr>
			<tr>
				<td>성별</td>
				<td>
					<input type="radio" value="M" name="studentGender" class="gender">남 
					<input type="radio" value="F" name="studentGender" class="gender">여 
				</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td>
					<input type="text" name="studentEmail" id="studentEmail" value="${getStudentOneByStudentVo.studentEmail}">
				</td>
			</tr>
			<tr>
				<td>최종 학력</td>
				<td>
					<input type="text" name="studentEducation" id="studentEducation" value="${getStudentOneByStudentVo.studentEducation}">
				</td>
			</tr>
			<tr>
				<td>학생 사진</td>
				<td>
					<input type="file" id="photoOriginName" name="photoOriginName">
					<span id="studentPhotoSection"></span>
				</td>
			</tr>
			<tr>
				<td>휴대전화</td>
				<td>
					<input type="text" name="memberPhoneNo" id="memberPhoneNo" value="${getStudentOneByMemberVo.memberPhoneNo}">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<button type = "submit" id="submit" >학생 정보 수정</button>
				</td>
			</tr>
		</table>
		
		<div>
			<a href="${pageContext.request.contextPath}/login">로그인 페이지</a>
		</div>
	</form>
</body>
</html>