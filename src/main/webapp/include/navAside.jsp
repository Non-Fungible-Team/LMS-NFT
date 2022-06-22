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
<link rel="icon" type="image/png" sizes="16x16" href="static/assets/images/favicon.png">
<!-- CSS 링크 -->
<link href="static/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="static/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="static/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="static/dist/css/style.min.css" rel="stylesheet">
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
					<!-- 다크모드 아이콘 --> <img src="static/assets/images/logo-icon.png" alt="homepage" class="dark-logo" style="width: 15%" />
					<!-- 라이트 모드 아이콘 --> <img src="static/assets/images/logo-icon.png" alt="homepage" class="light-logo" style="width: 15%" />
					</b> 
					<span class="logo-text"> LMS </span>
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
						<img src="static/assets/images/member.png" alt="user" class="rounded-circle" width="40"> 
						<span class="ml-2 d-none d-lg-inline-block">
							<span>환영합니다,</span> 
							<span class="text-dark">${sessionLoginMember.memberId} 님</span> 
							<i data-feather="chevron-down" class="svg-icon"></i>
						</span>
						</a>
						<c:if test="${not empty param.memberLevel}">
						<div class="dropdown-menu dropdown-menu-right user-dd animated flipInY">
							<a class="dropdown-item" href="javascript:void(0)"><i data-feather="user" class="svg-icon mr-2 ml-1"></i> My Page</a> <a class="dropdown-item" href="javascript:void(0)"><i data-feather="credit-card" class="svg-icon mr-2 ml-1"></i> My Balance</a> <a class="dropdown-item" href="javascript:void(0)"><i data-feather="mail" class="svg-icon mr-2 ml-1"></i> Inbox</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="javascript:void(0)"><i data-feather="settings" class="svg-icon mr-2 ml-1"></i> Account Setting</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="javascript:void(0)"><i data-feather="power" class="svg-icon mr-2 ml-1"></i> Logout</a>
							<div class="dropdown-divider"></div>
							<div class="pl-4 p-3">
								<a href="javascript:void(0)" class="btn btn-sm btn-info">View Profile</a>
							</div>
						</div>
						</c:if>
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
					<li class="sidebar-item"><a class="sidebar-link sidebar-link" href="index.html" aria-expanded="false"><i data-feather="home" class="feather-icon"></i><span class="hide-menu">Dashboard</span></a></li>
					<li class="list-divider"></li>
					<li class="nav-small-cap"><span class="hide-menu">Applications</span></li>

					<li class="sidebar-item"><a class="sidebar-link" href="ticket-list.html" aria-expanded="false"><i data-feather="tag" class="feather-icon"></i><span class="hide-menu">Ticket List </span></a></li>
					<li class="sidebar-item"><a class="sidebar-link sidebar-link" href="app-chat.html" aria-expanded="false"><i data-feather="message-square" class="feather-icon"></i><span class="hide-menu">Chat</span></a></li>
					<li class="sidebar-item"><a class="sidebar-link sidebar-link" href="app-calendar.html" aria-expanded="false"><i data-feather="calendar" class="feather-icon"></i><span class="hide-menu">Calendar</span></a></li>

					<li class="list-divider"></li>
					<li class="nav-small-cap"><span class="hide-menu">Components</span></li>
					<li class="sidebar-item"><a class="sidebar-link has-arrow" href="javascript:void(0)" aria-expanded="false"><i data-feather="file-text" class="feather-icon"></i><span class="hide-menu">Forms </span></a>
						<ul aria-expanded="false" class="collapse  first-level base-level-line">
							<li class="sidebar-item"><a href="form-inputs.html" class="sidebar-link"><span class="hide-menu"> Form Inputs </span></a></li>
							<li class="sidebar-item"><a href="form-input-grid.html" class="sidebar-link"><span class="hide-menu"> Form Grids </span></a></li>
							<li class="sidebar-item"><a href="form-checkbox-radio.html" class="sidebar-link"><span class="hide-menu"> Checkboxes & Radios </span></a></li>
						</ul></li>
					<li class="sidebar-item"><a class="sidebar-link has-arrow" href="javascript:void(0)" aria-expanded="false"><i data-feather="grid" class="feather-icon"></i><span class="hide-menu">Tables </span></a>
						<ul aria-expanded="false" class="collapse  first-level base-level-line">
							<li class="sidebar-item"><a href="table-basic.html" class="sidebar-link"><span class="hide-menu"> Basic Table </span></a></li>
							<li class="sidebar-item"><a href="table-dark-basic.html" class="sidebar-link"><span class="hide-menu"> Dark Basic Table </span></a></li>
							<li class="sidebar-item"><a href="table-sizing.html" class="sidebar-link"><span class="hide-menu"> Sizing Table </span></a></li>
							<li class="sidebar-item"><a href="table-layout-coloured.html" class="sidebar-link"><span class="hide-menu"> Coloured Table Layout </span></a></li>
							<li class="sidebar-item"><a href="table-datatable-basic.html" class="sidebar-link"><span class="hide-menu"> Basic Datatables Layout </span></a></li>
						</ul></li>
					<li class="sidebar-item"><a class="sidebar-link has-arrow" href="javascript:void(0)" aria-expanded="false"><i data-feather="bar-chart" class="feather-icon"></i><span class="hide-menu">Charts </span></a>
						<ul aria-expanded="false" class="collapse  first-level base-level-line">
							<li class="sidebar-item"><a href="chart-morris.html" class="sidebar-link"><span class="hide-menu"> Morris Chart </span></a></li>
							<li class="sidebar-item"><a href="chart-chart-js.html" class="sidebar-link"><span class="hide-menu"> ChartJs </span></a></li>
							<li class="sidebar-item"><a href="chart-knob.html" class="sidebar-link"><span class="hide-menu"> Knob Chart </span></a></li>
						</ul></li>
					<li class="sidebar-item"><a class="sidebar-link has-arrow" href="javascript:void(0)" aria-expanded="false"><i data-feather="box" class="feather-icon"></i><span class="hide-menu">UI Elements </span></a>
						<ul aria-expanded="false" class="collapse  first-level base-level-line">
							<li class="sidebar-item"><a href="ui-buttons.html" class="sidebar-link"><span class="hide-menu"> Buttons </span></a></li>
							<li class="sidebar-item"><a href="ui-modals.html" class="sidebar-link"><span class="hide-menu"> Modals </span></a></li>
							<li class="sidebar-item"><a href="ui-tab.html" class="sidebar-link"><span class="hide-menu"> Tabs </span></a></li>
							<li class="sidebar-item"><a href="ui-tooltip-popover.html" class="sidebar-link"><span class="hide-menu"> Tooltip & Popover</span></a></li>
							<li class="sidebar-item"><a href="ui-notification.html" class="sidebar-link"><span class="hide-menu">Notification</span></a></li>
							<li class="sidebar-item"><a href="ui-progressbar.html" class="sidebar-link"><span class="hide-menu">Progressbar</span></a></li>
							<li class="sidebar-item"><a href="ui-typography.html" class="sidebar-link"><span class="hide-menu">Typography</span></a></li>
							<li class="sidebar-item"><a href="ui-bootstrap.html" class="sidebar-link"><span class="hide-menu">Bootstrap UI</span></a></li>
							<li class="sidebar-item"><a href="ui-breadcrumb.html" class="sidebar-link"><span class="hide-menu">Breadcrumb</span></a></li>
							<li class="sidebar-item"><a href="ui-list-media.html" class="sidebar-link"><span class="hide-menu">List Media</span></a></li>
							<li class="sidebar-item"><a href="ui-grid.html" class="sidebar-link"><span class="hide-menu"> Grid </span></a></li>
							<li class="sidebar-item"><a href="ui-carousel.html" class="sidebar-link"><span class="hide-menu"> Carousel</span></a></li>
							<li class="sidebar-item"><a href="ui-scrollspy.html" class="sidebar-link"><span class="hide-menu"> Scrollspy</span></a></li>
							<li class="sidebar-item"><a href="ui-toasts.html" class="sidebar-link"><span class="hide-menu"> Toasts</span></a></li>
							<li class="sidebar-item"><a href="ui-spinner.html" class="sidebar-link"><span class="hide-menu"> Spinner </span></a></li>
						</ul></li>
					<li class="sidebar-item"><a class="sidebar-link sidebar-link" href="ui-cards.html" aria-expanded="false"><i data-feather="sidebar" class="feather-icon"></i><span class="hide-menu">Cards </span></a></li>
					<li class="list-divider"></li>
					<li class="nav-small-cap"><span class="hide-menu">Authentication</span></li>

					<li class="sidebar-item"><a class="sidebar-link sidebar-link" href="authentication-login1.html" aria-expanded="false"><i data-feather="lock" class="feather-icon"></i><span class="hide-menu">Login </span></a></li>
					<li class="sidebar-item"><a class="sidebar-link sidebar-link" href="authentication-register1.html" aria-expanded="false"><i data-feather="lock" class="feather-icon"></i><span class="hide-menu">Register </span></a></li>

					<li class="sidebar-item"><a class="sidebar-link has-arrow" href="javascript:void(0)" aria-expanded="false"><i data-feather="feather" class="feather-icon"></i><span class="hide-menu">Icons </span></a>
						<ul aria-expanded="false" class="collapse first-level base-level-line">
							<li class="sidebar-item"><a href="icon-fontawesome.html" class="sidebar-link"><span class="hide-menu"> Fontawesome Icons </span></a></li>

							<li class="sidebar-item"><a href="icon-simple-lineicon.html" class="sidebar-link"><span class="hide-menu"> Simple Line Icons </span></a></li>
						</ul></li>

					<li class="sidebar-item"><a class="sidebar-link has-arrow" href="javascript:void(0)" aria-expanded="false"><i data-feather="crosshair" class="feather-icon"></i><span class="hide-menu">Multi level dd</span></a>
						<ul aria-expanded="false" class="collapse first-level base-level-line">
							<li class="sidebar-item"><a href="javascript:void(0)" class="sidebar-link"><span class="hide-menu"> item 1.1</span></a></li>
							<li class="sidebar-item"><a href="javascript:void(0)" class="sidebar-link"><span class="hide-menu"> item 1.2</span></a></li>
							<li class="sidebar-item"><a class="has-arrow sidebar-link" href="javascript:void(0)" aria-expanded="false"><span class="hide-menu">Menu 1.3</span></a>
								<ul aria-expanded="false" class="collapse second-level base-level-line">
									<li class="sidebar-item"><a href="javascript:void(0)" class="sidebar-link"><span class="hide-menu"> item 1.3.1</span></a></li>
									<li class="sidebar-item"><a href="javascript:void(0)" class="sidebar-link"><span class="hide-menu"> item 1.3.2</span></a></li>
									<li class="sidebar-item"><a href="javascript:void(0)" class="sidebar-link"><span class="hide-menu"> item 1.3.3</span></a></li>
									<li class="sidebar-item"><a href="javascript:void(0)" class="sidebar-link"><span class="hide-menu"> item 1.3.4</span></a></li>
								</ul></li>
							<li class="sidebar-item"><a href="javascript:void(0)" class="sidebar-link"><span class="hide-menu"> item 1.4</span></a></li>
						</ul></li>
					<li class="list-divider"></li>
					<li class="nav-small-cap"><span class="hide-menu">Extra</span></li>
					<li class="sidebar-item"><a class="sidebar-link sidebar-link" href="docs/docs.html" aria-expanded="false"><i data-feather="edit-3" class="feather-icon"></i><span class="hide-menu">Documentation</span></a></li>
					<li class="sidebar-item"><a class="sidebar-link sidebar-link" href="authentication-login1.html" aria-expanded="false"><i data-feather="log-out" class="feather-icon"></i><span class="hide-menu">Logout</span></a></li>
				</ul>
			</nav>
			<!-- End Sidebar navigation -->
		</div>
		<!-- End Sidebar scroll-->
	</aside>

</body>
</html>