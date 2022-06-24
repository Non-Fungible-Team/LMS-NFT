<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 반응형 웹 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<!-- title icon -->
<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/static/assets/images/favicon.png">
<!-- CSS 링크 -->
<link href="${pageContext.request.contextPath}/static/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/static/dist/css/style.min.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
	<header class="topbar" data-navbarbg="skin6">
		<nav class="navbar top-navbar navbar-expand-md">
			<div class="navbar-header" data-logobg="skin6">
				<!-- This is for the sidebar toggle which is visible on mobile only -->
				<a class="nav-toggler waves-effect waves-light d-block d-md-none" href="javascript:void(0)"><i class="ti-menu ti-close"></i></a>
				<!-- Logo -->
				<div class="navbar-brand">
					<!-- 로고 아이콘 -->
					<a href=" "> <!-- 건들면 안됨.  -> http://lms-->
					<b class="logo-icon"> 
					<!-- 다크모드 아이콘 --> <img src="${pageContext.request.contextPath}/static/assets/images/logo-icon.png" alt="homepage" class="dark-logo" style="width: 15%" />
					<!-- 라이트 모드 아이콘 --> <img src="${pageContext.request.contextPath}/static/assets/images/logo-icon.png" alt="homepage" class="light-logo" style="width: 15%" />
					</b> 
					<span class="logo-text">
						<!-- dark Logo text -->
	                    <img src="${pageContext.request.contextPath}/static/assets/images/nfLMS.png" alt="homepage" class="dark-logo" style="width: 88%"/>
	                    <!-- Light Logo text -->
	                    <img src="${pageContext.request.contextPath}/static/assets/images/nfLMS.png" class="light-logo" alt="homepage" />
                    </span>
					</a>
				</div>
				<!-- End Logo -->

				<!-- Toggle 모바일 버전에서만 볼 수 있는 토글 -->
				<a class="topbartoggler d-block d-md-none waves-effect waves-light" href="javascript:void(0)" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
					<i class="ti-more"></i>
				</a>
			</div>
			<!-- nav -->
			<div class="navbar-collapse collapse" id="navbarSupportedContent">
				<ul class="navbar-nav float-left mr-auto ml-3 pl-1"><!-- nav 왼쪽 여백용 --></ul>
				<ul class="navbar-nav float-right">
					<!-- 프로필 -->
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="javascript:void(0)" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 
						<img src="${pageContext.request.contextPath}/static/assets/images/member.png" alt="user" class="rounded-circle" width="40"> 
						<span class="ml-2 d-none d-lg-inline-block">
							<span>환영합니다,</span> 
							<span class="text-dark">${sessionLoginMember.memberId} 님</span> 
							<i data-feather="chevron-down" class="svg-icon"></i>
						</span>
						</a>
						
						<div class="dropdown-menu dropdown-menu-right user-dd animated flipInY">
							<a class="dropdown-item" href="${pageContext.request.contextPath}/all/getStudentOne?memberId=${sessionLoginMember.memberId}"><i data-feather="user" class="svg-icon mr-2 ml-1"></i> My Page</a> <a class="dropdown-item" href="javascript:void(0)"><i data-feather="credit-card" class="svg-icon mr-2 ml-1"></i> My Balance</a> <a class="dropdown-item" href="javascript:void(0)"><i data-feather="mail" class="svg-icon mr-2 ml-1"></i> Inbox</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="javascript:void(0)"><i data-feather="settings" class="svg-icon mr-2 ml-1"></i> Account Setting</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="${pageContext.request.contextPath}/all/logout"><i data-feather="power" class="svg-icon mr-2 ml-1"></i> Logout</a>
							<div class="dropdown-divider"></div>
							<div class="pl-4 p-3">
								<a href="javascript:void(0)" class="btn btn-sm btn-info">View Profile</a>
							</div>
						</div>
						
					</li>
					<!-- ============================================================== -->
					<!-- User profile and search -->
					<!-- ============================================================== -->
				</ul>
			</div>
		</nav>
	</header>
	<!-- ============================================================== -->
	<!-- End Topbar header -->
	<!-- ============================================================== -->
	<!-- ============================================================== -->
	<!-- Left Sidebar - style you can find in sidebar.scss  -->
	<!-- ============================================================== -->
	<aside class="left-sidebar" data-sidebarbg="skin6">
		<!-- Sidebar scroll-->
		<div class="scroll-sidebar" data-sidebarbg="skin6">
			<!-- Sidebar navigation-->
			<nav class="sidebar-nav">
				<ul id="sidebarnav">
					<li class="sidebar-item"><a class="sidebar-link sidebar-link" href="${pageContext.request.contextPath}/managerPage" aria-expanded="false"><i data-feather="home" class="feather-icon"></i><span class="hide-menu">Home</span></a></li>

				</ul>
			</nav>
			<!-- End Sidebar navigation -->
		</div>
		<!-- End Sidebar scroll-->
	</aside>

</body>
</html>