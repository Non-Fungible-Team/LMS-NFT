<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>getStudentOne</title>
</head>
<body>
	<h1>학생 상세보기</h1>
	<table border="1">
		<tr>
			<td>학생 사진</td>
			<td colspan="2">
				<c:if test="${getMemberPhoto.photoType=='image/jpg' || getMemberPhoto.photoType== 'image/png' || getMemberPhoto.photoType == 'image/jpeg' }">
					<img src="${pageContext.request.contextPath}/memberPhoto/${getMemberPhoto.photoName}">
				</c:if>
				<a href="${pageContext.request.contextPath}/memberPhoto/${getMemberPhoto.photoName}" download="${getMemberPhoto.photoOriginName}">
					${getMemberPhoto.photoOriginName}
				</a>
			</td>
		</tr>
		<tr>
			<td>아이디</td>
			<td>${loginMember.memberId}</td>
		</tr>
		<tr>
			<td>학생 이름</td>
			<td>${getStudentOneByStudentVo.studentName}</td>
		</tr>
		<tr>
			<td>생일</td>
			<td>${getStudentOneByStudentVo.studentBirth}</td>
		</tr>
		<tr>
			<td>성별</td>
			<td>${getStudentOneByStudentVo.studentGender}</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td>${getStudentOneByStudentVo.studentEmail}</td>
		</tr>
		<tr>
			<td>최종 학력</td>
			<td>${getStudentOneByStudentVo.studentEducation}</td>
		</tr>
		<tr>
			<td>휴대전화</td>
			<td>${getStudentOneByMemberVo.memberPhoneNo}</td>
		</tr>
		<tr>
			<td>마지막 로그인 날짜</td>
			<td>${getStudentOneByMemberVo.memberLastLoginDate}</td>
		</tr>
	</table>
	
	<div>
		<a href="${pageContext.request.contextPath}/all/modifyStudent?memberId=${loginMember.memberId}">학생 정보 수정</a>
		
		<a href="${pageContext.request.contextPath}/all/freezeStudent?memberId=${loginMember.memberId}">학생 정보 삭제</a>
	</div>
</body>
</html>