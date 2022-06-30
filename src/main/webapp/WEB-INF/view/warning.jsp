<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 반응형 웹 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<!-- title icon -->
<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/static/assets/images/favicon.png">
<title>warning</title>
<style>
.helper {
	color: #FF0000;
}
</style>
<!-- CSS 링크 -->
<link href="${pageContext.request.contextPath}/static/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/static/dist/css/style.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
<script>
$('document').ready(function() {
	//인증번호 제출시 유효성 체크
	$('#verificationCodeButton').click(function(){
		if($('#verificationCode').val()=='') {
			$('#verificationCodeHelper').text('인증번호를 입력하세요');
			$('#verificationCode').focus();
		//빈칸이 아니면 제출
		} else{
			$('#verificationCodeSubmit').submit();
		}
	});
});
</script>
</head>
<body>
	<div class="main-wrapper container">
		<div class="auth-wrapper no-block justify-content-center align-items-center position-relative">
			<h2 class="mt-3 text-center">알 림</h2>
			<br>
			<!-- 휴면 계정 로그인시 부분 -->
			<c:if test="${sessionLoginMember.memberLevel==-4||sessionLoginMember.memberLevel==-5||sessionLoginMember.memberLevel==-6}">
				<h1> 휴면 계정입니다 . </h1>
				 <a type = "button" class="btn btn-info" href="${pageContext.request.contextPath}/member/cancelRestingMember">휴면 계정 해제</a>
				 <!-- 이메일 인증 후 돌아왔을 경우 -->
				 <c:if test="${not empty email}">
				 	<form method="post" id="verificationCodeSubmit" action="${pageContext.request.contextPath}/member/cancelRestingMember">
				 		<input type = "text" name ="email" readonly="readonly" style="border:none">에 전송된 인증번호를 입력해주세요
				 		<input type = "text" name ="verificationCode" id="verificationCode">
				 		<span id="verificationCodeHelper" class="helper"></span>
				 		<button type ="button" id="verificationCodeButton">제출</button>
				 	</form>
				 	
				 </c:if>
			</c:if>
			<!-- 탈퇴 회원 로그인시 부분 -->
			<c:if test="${sessionLoginMember.memberLevel==-14||sessionLoginMember.memberLevel==-15||sessionLoginMember.memberLevel==-16}">
				<h1> 삭제 대기 중인 탈퇴한 회원입니다 . </h1>
				 <a type = "button" class="btn btn-info" href="member/cancelFreezeMember">탈퇴 신청 취소</a>
			</c:if>
		</div>
	</div>
	<!-- script-->
	<script src="${pageContext.request.contextPath}/static/assets/libs/jquery/dist/jquery.min.js "></script>
	<!-- Bootstrap tether Core JavaScript -->
	<script src="${pageContext.request.contextPath}/static/assets/libs/popper.js/dist/umd/popper.min.js "></script>
	<script src="${pageContext.request.contextPath}/static/assets/libs/bootstrap/dist/js/bootstrap.min.js "></script>
	<!-- ============================================================== -->
	<!-- This page plugin js -->
	<!-- ============================================================== -->
	<script>
   $(".preloader ").fadeOut();
</script>
</body>
</html>