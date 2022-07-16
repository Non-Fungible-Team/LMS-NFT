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
<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/assets/images/favicon.png">
<!-- CSS 링크 -->
<link href="${pageContext.request.contextPath}/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/dist/css/style.min.css" rel="stylesheet">
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
					<!-- 다크모드 아이콘 --> <img src="${pageContext.request.contextPath}/assets/images/computer.PNG" alt="homepage" class="dark-logo" style="width: 15%" />
					<!-- 라이트 모드 아이콘 --> <img src="${pageContext.request.contextPath}/assets/images/computer.PNG" alt="homepage" class="light-logo" style="width: 15%" />
					</b> 
					<span class="logo-text">
						<!-- dark Logo text -->
	                    <img src="${pageContext.request.contextPath}/assets/images/nfLMS2.PNG" alt="homepage" class="dark-logo" style="width: 88%"/>
	                    <!-- Light Logo text -->
	                    <img src="${pageContext.request.contextPath}/assets/images/nfLMS2.PNG" class="light-logo" alt="homepage" />
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
		                <h3 class="page-title text-truncate text-dark font-weight-medium mb-1">기능확인을 원하시면 수강목록에서 java 기초부터 웹개발 마스터(1)를 선택해주세요.</h3>
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
						<img src="${pageContext.request.contextPath}/assets/images/member.png" alt="user" class="rounded-circle" width="40"> 
						<span class="ml-2 d-none d-lg-inline-block">
							<span>환영합니다, </span> 
							<span class="text-dark">${sessionLoginMember.memberId} 님</span> 
							<i data-feather="chevron-down" class="svg-icon"></i>
						</span>
						</a>
						<div class="dropdown-menu dropdown-menu-right user-dd animated flipInY">
							<c:if test="${sessionLoginMember.memberLevel==4}">
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="${pageContext.request.contextPath}/all/getStudentOne?memberId=${sessionLoginMember.memberId}">
								<i data-feather="user" class="svg-icon mr-2 ml-1"></i> 개인 정보 확인</a> 
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="${pageContext.request.contextPath}/all/logout"><i data-feather="power" class="svg-icon mr-2 ml-1"></i> 로그아웃</a>
								<div class="dropdown-divider"></div>
							</c:if>	
							
							<c:if test="${sessionLoginMember.memberLevel==5}">
								<a class="dropdown-item" href="${pageContext.request.contextPath}/teacher/getTeacherOne?memberId=${sessionLoginMember.memberId}">
								<div class="dropdown-divider"></div>
								<i data-feather="user" class="svg-icon mr-2 ml-1"></i> 개인 정보 확인</a> 
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="${pageContext.request.contextPath}/all/logout"><i data-feather="power" class="svg-icon mr-2 ml-1"></i> 로그아웃</a>
								<div class="dropdown-divider"></div>
							</c:if>
							
							<c:if test="${sessionLoginMember.memberLevel==6}">
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="${pageContext.request.contextPath}/manager/getManagerOne?memberId=${sessionLoginMember.memberId}">
								<i data-feather="user" class="svg-icon mr-2 ml-1"></i> 개인 정보 확인</a> 
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="${pageContext.request.contextPath}/all/logout"><i data-feather="power" class="svg-icon mr-2 ml-1"></i> 로그아웃</a>
								<div class="dropdown-divider"></div>
								</div>
							</c:if>
					</li>
				</ul>
			</div>
		</nav>
	</header>
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
					<li class="sidebar-item"><a class="sidebar-link" href="${pageContext.request.contextPath}/all/schedule/getScheduleList" aria-expanded="false"><i data-feather="calendar" class="feather-icon"></i><span class="hide-menu">학원 일정</span></a></li>
					<!-- 건의 게시판 -->
					<li class="sidebar-item"><a class="sidebar-link" href="${pageContext.request.contextPath}/all/suggest/getSuggestListByPage" aria-expanded="false"><i data-feather="message-square" class="feather-icon"></i><span class="hide-menu">건의 게시판</span></a></li>
					<!-- 강의 선택시에 강의에 대한 세부 메뉴 시작 -->
					<c:if test="${not empty sessionLecture }">
						<li class="list-divider"></li>
						<!-- 선택한 강의 이름 -->
						<li class="nav-small-cap"><span class="hide-menu">${sessionLecture.lectureName}</span></li>
						<!-- 강의 게시판 -->
						<li class="sidebar-item"><a class="sidebar-link" href="${pageContext.request.contextPath}/all/lectureBoard/getLectureBoardListByPage" aria-expanded="false"><i data-feather="message-square" class="feather-icon"></i><span class="hide-menu">강의게시판</span></a></li>
						<!-- 수강생 목록 강사이상이어야 볼 수 있음-->
						<li class="sidebar-item"><a class="sidebar-link" href="${pageContext.request.contextPath}/teacher/lecture/getStudentLectureByPage" aria-expanded="false"><i data-feather="message-square" class="feather-icon"></i><span class="hide-menu">수강생 목록</span></a></li>
						<!-- 강의 시간표 -->
						<li class="sidebar-item"><a class="sidebar-link" href="${pageContext.request.contextPath}/all/lecture/getLectureScheduleListByLectureNo" aria-expanded="false"><i data-feather="calendar" class="feather-icon"></i><span class="hide-menu">Lecture schedule</span></a></li>
						<!-- 출석 -->
						<c:if test="${sessionLoginMember.memberLevel > 4 }">
							<li class="sidebar-item"><a class="sidebar-link" href="${pageContext.request.contextPath}/teacher/lecture/getAttendList?lectureNo=${sessionLectureNo}" aria-expanded="false"><i data-feather="tag" class="feather-icon"></i><span class="hide-menu">강의 출석 </span></a></li>
						</c:if>
						<c:if test="${sessionLoginMember.memberLevel == 4 }">
							<li class="sidebar-item"><a class="sidebar-link" href="${pageContext.request.contextPath}/student/lecture/getStudentAttendOne?lectureNo=${sessionLectureNo}" aria-expanded="false"><i data-feather="tag" class="feather-icon"></i><span class="hide-menu">강의 출석 </span></a></li>
						</c:if>
						<!-- 과제 -->
						<li class="sidebar-item"><a class="sidebar-link sidebar-link" href="${pageContext.request.contextPath}/all/homework/getHomeworkListByPage?lectureNo=${sessionLectureNo}" aria-expanded="false"><i data-feather="sidebar" class="feather-icon"></i><span class="hide-menu">과제 </span></a></li>
						<!-- 시험 -->
						<li class="sidebar-item"><a class="sidebar-link has-arrow" href="javascript:void(0)" aria-expanded="false"><i data-feather="file-text" class="feather-icon"></i><span class="hide-menu">시험 </span></a>
							<ul aria-expanded="false" class="collapse  first-level base-level-line">
								<li class="sidebar-item"><a href="${pageContext.request.contextPath}/all/exam/getExamListByPage" class="sidebar-link"><span class="hide-menu"> 시험 목록 </span></a></li>
								<li class="sidebar-item"><a href="${pageContext.request.contextPath}/all/exam/getExamScoreListByPage" class="sidebar-link"><span class="hide-menu"> 점수 확인 </span></a></li>
							</ul></li>
						<!-- 강의 설문조사 -->
						<li class="sidebar-item"><a class="sidebar-link sidebar-link" href="${pageContext.request.contextPath}/all/survey/getSurveyListByPage" aria-expanded="false"><i data-feather="edit-3" class="feather-icon"></i><span class="hide-menu">강의 설문조사</span></a></li>
					</c:if>
					<!-- 강의에 대한 세부 메뉴 끝 -->
					
					<!-- 운영자만의 관리자 메뉴 추가 -->
					<c:if test="${sessionLoginMember.memberLevel >= 6 }">
						<li class="list-divider"></li>
						<li class="list-divider"></li>
						<li class="nav-small-cap"><span class="hide-menu">운영자 기능</span></li>
						<li class="sidebar-item"><a class="sidebar-link has-arrow" href="javascript:void(0)" aria-expanded="false"><i data-feather="file-text" class="feather-icon"></i><span class="hide-menu">강의 관리</span></a>
							<ul aria-expanded="false" class="collapse  first-level base-level-line">
								<li class="sidebar-item"><a class="sidebar-link" href="${pageContext.request.contextPath}/teacher/lecture/getLectureByPage" aria-expanded="false"><i data-feather="tag" class="feather-icon"></i><span class="hide-menu">전체 강의 관리</span></a></li>
								<li class="sidebar-item"><a class="sidebar-link" href="${pageContext.request.contextPath}/teacher/lecture/subjectList" aria-expanded="false"><i data-feather="tag" class="feather-icon"></i><span class="hide-menu">과목 관리</span></a></li>
								<li class="sidebar-item"><a href="${pageContext.request.contextPath}/teacher/lecture/getLectureRoomList" class="sidebar-link"><i data-feather="tag" class="feather-icon"></i><span class="hide-menu"> 강의실 목록 관리 </span></a></li>
								<li class="sidebar-item"><a href="${pageContext.request.contextPath}/manager/lecture/getTeacherLectureByPage" class="sidebar-link"><i data-feather="tag" class="feather-icon"></i><span class="hide-menu"> 강사 별 강의 목록 </span></a></li>
								<li class="sidebar-item"><a href="${pageContext.request.contextPath}/manager/lecture/getStudentLectureAllByPage" class="sidebar-link"><i data-feather="tag" class="feather-icon"></i><span class="hide-menu"> 전체 강의 별 학생 목록 </span></a></li>
							</ul></li>
						<li class="sidebar-item"><a class="sidebar-link has-arrow" href="javascript:void(0)" aria-expanded="false"><i data-feather="file-text" class="feather-icon"></i><span class="hide-menu">회원 관리</span></a>
							<ul aria-expanded="false" class="collapse  first-level base-level-line">
									<li class="sidebar-item"><a href="${pageContext.request.contextPath}/manager/member/approvalMember" class="sidebar-link"><span class="hide-menu">가입 승인 대기자 목록</span></a></li>
									<%-- <li class="sidebar-item"><a href="${pageContext.request.contextPath}/emptyPage" class="sidebar-link"><span class="hide-menu"> 운영자 목록</span></a></li> --%>
									<li class="sidebar-item"><a href="${pageContext.request.contextPath}/manager/getTeacherByPage" class="sidebar-link"><span class="hide-menu"> 강사 목록</span></a></li>
									<li class="sidebar-item"><a href="${pageContext.request.contextPath}/manager/getStudentByPage" class="sidebar-link"><span class="hide-menu"> 학생 목록</span></a></li>
							</ul></li>
						<li class="sidebar-item"><a class="sidebar-link has-arrow" href="javascript:void(0)" aria-expanded="false"><i data-feather="edit-3" class="feather-icon"></i><span class="hide-menu">설문 조사 관리</span></a>
							<ul aria-expanded="false" class="collapse  first-level base-level-line">
								<li class="sidebar-item"><a href="${pageContext.request.contextPath}/manager/survey/getSurveyQuestionListByPage" class="sidebar-link"><span class="hide-menu"> 질문 관리 리스트 </span></a></li>
							</ul></li>
						<li class="sidebar-item"><a href="${pageContext.request.contextPath}/manager/blind/blindList" class="sidebar-link"><i class="fas fa-eye-slash"></i><span class="hide-menu"> 삭제된 게시판 관리 </span></a></li>
					</c:if>
					<!-- 관리자 메뉴 끝 -->
				</ul>
			</nav>
			<!-- End Sidebar navigation -->
		</div>
		<!-- End Sidebar scroll-->
	</aside>

</body>
    <script src="${pageContext.request.contextPath}/dist/js/app-style-switcher.js"></script>
    <script src="${pageContext.request.contextPath}/dist/js/feather.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/dist/js/sidebarmenu.js"></script>
    <script src="${pageContext.request.contextPath}/dist/js/custom.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/extra-libs/c3/d3.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/extra-libs/c3/c3.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/extra-libs/jvector/jquery-jvectormap-world-mill-en.js"></script>
    <script src="${pageContext.request.contextPath}/dist/js/pages/dashboards/dashboard1.min.js"></script>
</html>