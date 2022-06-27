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
					<!-- 다크모드 아이콘 --> <img src="${pageContext.request.contextPath}/static/assets/images/computer.PNG" alt="homepage" class="dark-logo" style="width: 15%" />
					<!-- 라이트 모드 아이콘 --> <img src="${pageContext.request.contextPath}/static/assets/images/computer.PNG" alt="homepage" class="light-logo" style="width: 15%" />
					</b> 
					<span class="logo-text">
						<!-- dark Logo text -->
	                    <img src="${pageContext.request.contextPath}/static/assets/images/nfLMS2.PNG" alt="homepage" class="dark-logo" style="width: 88%"/>
	                    <!-- Light Logo text -->
	                    <img src="${pageContext.request.contextPath}/static/assets/images/nfLMS2.PNG" class="light-logo" alt="homepage" />
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
				<ul class="navbar-nav float-left mr-auto ml-3 pl-1"><!-- nav 왼쪽 여백용 -->
	            <!-- 강의를 선택 안했을 시 표시 -->
		            <c:if test="${empty sessionLecture}">
		                <h3 class="page-title text-truncate text-dark font-weight-medium mb-1">수강목록에서 강의를 선택하세요!</h3>
		            </c:if>
		            <c:if test="${not empty sessionLecture }">
		                <h3 class="page-title text-truncate text-dark font-weight-medium mb-1">${sessionLecture.lectureName}</h3>
		                <div class="d-flex align-items-center">
		                    <nav aria-label="breadcrumb">
		                        <ol class="breadcrumb m-0 p-0">
		                            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/all/lectureNoController">클릭시 강의 선택이 초기화 됩니다.</a>
		                            </li>
		                        </ol>
		                    </nav>
		                </div>
		            </c:if>
				</ul>
				
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
							<c:if test="${sessionLoginMember.memberLevel == 4} ">
								<a class="dropdown-item" href="${pageContext.request.contextPath}/all/getStudentOne?memberId=${sessionLoginMember.memberId}"><i data-feather="user" class="svg-icon mr-2 ml-1"></i> My Page</a> <a class="dropdown-item" href="javascript:void(0)"><i data-feather="credit-card" class="svg-icon mr-2 ml-1"></i> My Balance</a> <a class="dropdown-item" href="javascript:void(0)"><i data-feather="mail" class="svg-icon mr-2 ml-1"></i> Inbox</a>
							</c:if>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="javascript:void(0)"><i data-feather="settings" class="svg-icon mr-2 ml-1"></i> Account Setting</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="${pageContext.request.contextPath}/all/logout"><i data-feather="power" class="svg-icon mr-2 ml-1"></i> Logout</a>
								<div class="dropdown-divider"></div>
								<div class="pl-4 p-3">
									<a href="javascript:void(0)" class="btn btn-sm btn-info">View Profile</a>
								</div>
							
							
							<c:if test="${sessionLoginMember.memberLevel == 5} ">
								<a class="dropdown-item" href="${pageContext.request.contextPath}/all/getTeacherOne?memberId=${sessionLoginMember.memberId}"><i data-feather="user" class="svg-icon mr-2 ml-1"></i> My Page</a> <a class="dropdown-item" href="javascript:void(0)"><i data-feather="credit-card" class="svg-icon mr-2 ml-1"></i> My Balance</a> <a class="dropdown-item" href="javascript:void(0)"><i data-feather="mail" class="svg-icon mr-2 ml-1"></i> Inbox</a>
							</c:if>
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
					<!--  공통으로 보여지는 부분 -->
					<!-- 홈버튼 -->
					<li class="sidebar-item"><a class="sidebar-link" href="${pageContext.request.contextPath}/all/home" aria-expanded="false"><i data-feather="home" class="feather-icon"></i><span class="hide-menu">Home</span></a></li>
					<!-- 공지사항 -->
					<li class="sidebar-item"><a class="sidebar-link" href="${pageContext.request.contextPath}/all/notice/getNoticeListByPage" aria-expanded="false"><i data-feather="feather" class="feather-icon"></i><span class="hide-menu">공지사항 </span></a>
					<!-- 학원일정 -->
					<li class="sidebar-item"><a class="sidebar-link" href="${pageContext.request.contextPath}/emptyPage" aria-expanded="false"><i data-feather="calendar" class="feather-icon"></i><span class="hide-menu">학원 일정</span></a></li>
					<!-- 강의 선택시에 강의에 대한 세부 메뉴 시작 -->
					<c:if test="${not empty sessionLecture }">
					<li class="list-divider"></li>
					<!-- 선택한 강의 이름 -->
					<li class="nav-small-cap"><span class="hide-menu">${sessionLecture.lectureName}</span></li>
					<!-- 강의 게시판 -->
					<li class="sidebar-item"><a class="sidebar-link" href="${pageContext.request.contextPath}/emptyPage" aria-expanded="false"><i data-feather="message-square" class="feather-icon"></i><span class="hide-menu">강의게시판</span></a></li>
					<!-- 출석 -->
					<li class="sidebar-item"><a class="sidebar-link" href="${pageContext.request.contextPath}/emptyPage" aria-expanded="false"><i data-feather="tag" class="feather-icon"></i><span class="hide-menu">강의 출석 </span></a></li>
					<!-- 과제 -->
					<li class="sidebar-item"><a class="sidebar-link sidebar-link" href="${pageContext.request.contextPath}/homework/getHomeworkListByPage" aria-expanded="false"><i data-feather="sidebar" class="feather-icon"></i><span class="hide-menu">과제 </span></a></li>
					<!-- 시험 -->
					<li class="sidebar-item"><a class="sidebar-link has-arrow" href="javascript:void(0)" aria-expanded="false"><i data-feather="file-text" class="feather-icon"></i><span class="hide-menu">시험 </span></a>
						<ul aria-expanded="false" class="collapse  first-level base-level-line">
							<li class="sidebar-item"><a href="${pageContext.request.contextPath}/exam/getExamListByPage" class="sidebar-link"><span class="hide-menu"> 시험 목록 </span></a></li>
							<li class="sidebar-item"><a href="${pageContext.request.contextPath}/exam/" class="sidebar-link"><span class="hide-menu"> 점수확인 </span></a></li>
						</ul></li>
					<!-- 강의 설문조사 -->
					<li class="sidebar-item"><a class="sidebar-link sidebar-link" href="${pageContext.request.contextPath}/survey/getSurveyListByPage" aria-expanded="false"><i data-feather="edit-3" class="feather-icon"></i><span class="hide-menu">강의 설문조사</span></a></li>
					</c:if>
					<!-- 강의에 대한 세부 메뉴 끝 -->
					
					<!-- 운영자만의 관리자 메뉴 추가 -->
					<c:if test="${sessionLoginMember.memberLevel > 5 }">
						<li class="list-divider"></li>
						<li class="nav-small-cap"><span class="hide-menu">====관리자 기능====</span></li>
						<li class="list-divider"></li>
						<li class="nav-small-cap"><span class="hide-menu">Lecture</span></li>
	
						<li class="sidebar-item"><a class="sidebar-link" href="${pageContext.request.contextPath}/teacher/lecture/getLectureByPage" aria-expanded="false"><i data-feather="tag" class="feather-icon"></i><span class="hide-menu">Lecture List </span></a></li>
						<li class="sidebar-item"><a class="sidebar-link" href="${pageContext.request.contextPath}/teacher/lecture/subjectList" aria-expanded="false"><i data-feather="tag" class="feather-icon"></i><span class="hide-menu">Subject List </span></a></li>
						<li class="sidebar-item"><a class="sidebar-link has-arrow" href="javascript:void(0)" aria-expanded="false"><i data-feather="file-text" class="feather-icon"></i><span class="hide-menu">Member Lecture </span></a>
							<ul aria-expanded="false" class="collapse  first-level base-level-line">
								<li class="sidebar-item"><a href="${pageContext.request.contextPath}/manager/lecture/getStudentLectureAllByPage" class="sidebar-link"><span class="hide-menu"> Student Lecture </span></a></li>
								<li class="sidebar-item"><a href="##########.jsp" class="sidebar-link"><span class="hide-menu"> Teacher Lecture </span></a></li>
								<li class="sidebar-item"><a href="##########.jsp" class="sidebar-link"><span class="hide-menu"> Manager Lecture </span></a></li>
							</ul></li>
						<li class="sidebar-item"><a class="sidebar-link sidebar-link" href="##########.jsp" aria-expanded="false"><i data-feather="calendar" class="feather-icon"></i><span class="hide-menu">Lecture schedule</span></a></li>
						<li class="sidebar-item"><a class="sidebar-link has-arrow" href="javascript:void(0)" aria-expanded="false"><i data-feather="file-text" class="feather-icon"></i><span class="hide-menu">Etc </span></a>
							<ul aria-expanded="false" class="collapse  first-level base-level-line">
								<li class="sidebar-item"><a href="${pageContext.request.contextPath}/teacher/lecture/getLectureRoomList" class="sidebar-link"><span class="hide-menu"> LectureRoom List </span></a></li>
							</ul></li>
	
						<li class="list-divider"></li>
						<li class="nav-small-cap"><span class="hide-menu">Member</span></li>
						<li class="sidebar-item"><a class="sidebar-link has-arrow" href="javascript:void(0)" aria-expanded="false"><i data-feather="file-text" class="feather-icon"></i><span class="hide-menu">Manager </span></a>
							<ul aria-expanded="false" class="collapse  first-level base-level-line">
								<li class="sidebar-item"><a href="##########.jsp" class="sidebar-link"><span class="hide-menu"> Manager List </span></a></li>
								<li class="sidebar-item"><a href="##########.jsp" class="sidebar-link"><span class="hide-menu"> ##### </span></a></li>
							</ul></li>
						<li class="sidebar-item"><a class="sidebar-link has-arrow" href="javascript:void(0)" aria-expanded="false"><i data-feather="file-text" class="feather-icon"></i><span class="hide-menu">Teacher </span></a>
							<ul aria-expanded="false" class="collapse  first-level base-level-line">
								<li class="sidebar-item"><a href="##########.jsp" class="sidebar-link"><span class="hide-menu"> Teacher List </span></a></li>
								<li class="sidebar-item"><a href="${pageContext.request.contextPath}/manager/lecture/getManagerLectureByPage" class="sidebar-link"><span class="hide-menu"> 강의 강사 리스트</span></a></li>
							</ul></li>
						<li class="sidebar-item"><a class="sidebar-link has-arrow" href="javascript:void(0)" aria-expanded="false"><i data-feather="file-text" class="feather-icon"></i><span class="hide-menu">Student </span></a>
							<ul aria-expanded="false" class="collapse  first-level base-level-line">
								<li class="sidebar-item"><a href="f##########.jsp" class="sidebar-link"><span class="hide-menu"> Student List </span></a></li>
								<li class="sidebar-item"><a href="##########.jsp" class="sidebar-link"><span class="hide-menu"> ##### </span></a></li>
							</ul></li>
							
						<li class="list-divider"></li>
						<li class="nav-small-cap"><span class="hide-menu">Board</span></li>
						<li class="sidebar-item"><a class="sidebar-link has-arrow" href="javascript:void(0)" aria-expanded="false"><i data-feather="file-text" class="feather-icon"></i><span class="hide-menu">Board </span></a>
							<ul aria-expanded="false" class="collapse  first-level base-level-line">
								<li class="sidebar-item"><a href="##########.jsp" class="sidebar-link"><span class="hide-menu"> Board List </span></a></li>
								<li class="sidebar-item"><a href="##########.jsp" class="sidebar-link"><span class="hide-menu"> ##### </span></a></li>
							</ul></li>
						<li class="sidebar-item"><a class="sidebar-link has-arrow" href="javascript:void(0)" aria-expanded="false"><i data-feather="file-text" class="feather-icon"></i><span class="hide-menu">Report Board </span></a>
							<ul aria-expanded="false" class="collapse  first-level base-level-line">
								<li class="sidebar-item"><a href="##########.jsp" class="sidebar-link"><span class="hide-menu"> Report List </span></a></li>
								<li class="sidebar-item"><a href="##########.jsp" class="sidebar-link"><span class="hide-menu"> ##### </span></a></li>
							</ul></li>
						<li class="sidebar-item"><a class="sidebar-link has-arrow" href="javascript:void(0)" aria-expanded="false"><i data-feather="file-text" class="feather-icon"></i><span class="hide-menu">Comment </span></a>
							<ul aria-expanded="false" class="collapse  first-level base-level-line">
								<li class="sidebar-item"><a href="##########.jsp" class="sidebar-link"><span class="hide-menu"> Comment List </span></a></li>
								<li class="sidebar-item"><a href="##########.jsp" class="sidebar-link"><span class="hide-menu"> ##### </span></a></li>
							</ul></li>
						<li class="list-divider"></li>
						<li class="nav-small-cap"><span class="hide-menu">Extra</span></li>
						<li class="sidebar-item"><a class="sidebar-link has-arrow" href="javascript:void(0)" aria-expanded="false"><i data-feather="edit-3" class="feather-icon"></i><span class="hide-menu">Survey </span></a>
							<ul aria-expanded="false" class="collapse  first-level base-level-line">
								<li class="sidebar-item"><a href="##########.jsp" class="sidebar-link"><span class="hide-menu"> Survey List </span></a></li>
								<li class="sidebar-item"><a href="${pageContext.request.contextPath}/manager/survey/getSurveyQuestionListByPage" class="sidebar-link"><span class="hide-menu"> 질문 관리 리스트 </span></a></li>
							</ul></li>
						<li class="sidebar-item"><a class="sidebar-link has-arrow" href="javascript:void(0)" aria-expanded="false"><i data-feather="edit-3" class="feather-icon"></i><span class="hide-menu">Exam </span></a>
							<ul aria-expanded="false" class="collapse  first-level base-level-line">
								<li class="sidebar-item"><a href="##########.jsp" class="sidebar-link"><span class="hide-menu"> Exam List </span></a></li>
								<li class="sidebar-item"><a href="##########.jsp" class="sidebar-link"><span class="hide-menu"> ##### </span></a></li>
							</ul></li>
					</c:if>
					<!-- 관리자 메뉴 끝 -->
				</ul>
			</nav>
			<!-- End Sidebar navigation -->
		</div>
		<!-- End Sidebar scroll-->
	</aside>

</body>
    <script src="${pageContext.request.contextPath}/static/dist/js/app-style-switcher.js"></script>
    <script src="${pageContext.request.contextPath}/static/dist/js/feather.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/dist/js/sidebarmenu.js"></script>
    <script src="${pageContext.request.contextPath}/static/dist/js/custom.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/assets/extra-libs/c3/d3.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/assets/extra-libs/c3/c3.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/assets/libs/chartist/dist/chartist.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/assets/libs/chartist-plugin-tooltips/dist/chartist-plugin-tooltip.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/assets/extra-libs/jvector/jquery-jvectormap-world-mill-en.js"></script>
    <script src="${pageContext.request.contextPath}/static/dist/js/pages/dashboards/dashboard1.min.js"></script>
</html>