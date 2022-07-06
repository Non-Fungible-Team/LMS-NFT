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
<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/assets/images/favicon.png">
<title>Login</title>
<!-- CSS 링크 -->
<link href="${pageContext.request.contextPath}/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/dist/css/style.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
<script>

</script>
</head>
<body>
	<div class="main-wrapper container">
		<div class="auth-wrapper no-block justify-content-center align-items-center position-relative">
			<h2 class="mt-3 text-center">Sign In</h2>
			<br>
			 <ul class="nav nav-tabs" role="tablist">
			  <li class="nav-item">
			     <a class="nav-link active" data-toggle="tab" href="#student">학생</a>
			  </li>
			  <li class="nav-item">
			   <a class="nav-link" data-toggle="tab" href="#teacher">강사</a>
			  </li>
			  <li class="nav-item">
			    <a class="nav-link" data-toggle="tab" href="#manager">운영자</a>
			  </li>
			</ul>
			<!-- Tab panes -->
			<div class="tab-content">
				<div id="student" class="container tab-pane active">
					<br>
					<h3>학생</h3>
					<form class="mt-4" id="loginForm" method="post" action="${pageContext.request.contextPath}/login">
						<input type="hidden" name="memberLevel" value="4">
						<div class="row">
							<div class="col-lg-12">
								<div class="form-group">
									<label class="text-dark" >ID</label> 
									<input class="form-control" id="uname" type="text" placeholder="아이디를 입력해주세요" name="memberId" value="student">
								</div>
							</div>
							<div class="col-lg-12">
								<div class="form-group">
									<label class="text-dark">Password</label> 
									<input class="form-control" id="pwd" type="password" placeholder="비밀번호를 입력해주세요" name="memberPw" value="1234">
								</div>
							</div>
							<div class="col-lg-12 text-center">
								<button type="submit" class="btn btn-block btn-dark">로그인</button>
							</div>
							<div class="col-lg-12 text-center mt-5">
								Don't have an account? <a href="${pageContext.request.contextPath}/addStudent?memberLevel=4">회원가입</a>
							</div>
						</div>
					</form>
				</div>

				<div id="teacher" class="container tab-pane fade">
					<br>
					<h3>강사</h3>
					<form class="mt-4" id="loginForm" method="post" action="${pageContext.request.contextPath}/login">
						<input type="hidden" name="memberLevel" value="5">
						<div class="row">
							<div class="col-lg-12">
								<div class="form-group">
									<label class="text-dark" for="uname">ID</label> <input class="form-control" id="uname" type="text" placeholder="enter your username" name="memberId" value="teacher">
								</div>
							</div>
							<div class="col-lg-12">
								<div class="form-group">
									<label class="text-dark" for="pwd">Password</label> <input class="form-control" id="pwd" type="password" placeholder="enter your password" name="memberPw" value="1234">
								</div>
							</div>
							<div class="col-lg-12 text-center">
								<button type="submit" class="btn btn-block btn-dark">로그인</button>
							</div>
							<div class="col-lg-12 text-center mt-5">
								Don't have an account? <a href="${pageContext.request.contextPath}/addTeacher?memberLevel=5">회원가입</a>
							</div>
						</div>
					</form>
				</div>

				<div id="manager" class="container tab-pane fade">
					<br>
					<h3>운영자</h3>
					<form class="mt-4" id="loginForm" method="post" action="${pageContext.request.contextPath}/login">
						<input type="hidden" name="memberLevel" value="6">
						<div class="row">
							<div class="col-lg-12">
								<div class="form-group">
									<label class="text-dark">ID</label> <input class="form-control" type="text" placeholder="아이디를 입력해주세요" name="memberId" value="manager">
								</div>
							</div>
							<div class="col-lg-12">
								<div class="form-group">
									<label class="text-dark">Password</label> <input class="form-control" type="password" placeholder="비밀번호를 입력해주세요" name="memberPw" value="1234">
								</div>
							</div>
							<div class="col-lg-12 text-center">
								<button type="submit" class="btn btn-block btn-dark">로그인</button>
							</div>
							<div class="col-lg-12 text-center mt-5">
								Don't have an account? <a href="${pageContext.request.contextPath}/addManager?memberLevel=6">회원가입</a>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- script-->
	<script src="${pageContext.request.contextPath}/assets/libs/jquery/dist/jquery.min.js "></script>
	<!-- Bootstrap tether Core JavaScript -->
	<script src="${pageContext.request.contextPath}/assets/libs/popper.js/dist/umd/popper.min.js "></script>
	<script src="${pageContext.request.contextPath}/assets/libs/bootstrap/dist/js/bootstrap.min.js "></script>
	<!-- ============================================================== -->
	<!-- This page plugin js -->
	<!-- ============================================================== -->
	<script>
   $(".preloader ").fadeOut();
</script>
</body>
</html>