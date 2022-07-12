<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 반응형 웹 -->
<!-- 반응형 웹 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<!-- title icon -->
<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/assets/images/favicon.png">
<title>getTeacherOne</title>
<link href="${pageContext.request.contextPath}/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/dist/css/style.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
</head>
<script>
	$('document').ready(function() {
		$("#navAside").load('${pageContext.request.contextPath}/include/navAside.jsp');
		});
</script>
<body>

<div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full" data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
		<!-- header include(네비게이션바) -->
		<div id="navAside"></div>
		<div class="page-wrapper">
			<div class="container-fluid">
				<h1>강사 상세 보기</h1>
				<!-- main화면 body start -->

				<!-- 첫번쨰 문단 -->
				<div class="container p-5 my-5 border">
					<div class="row">
						<div class="col-lg-12 col-md-12">
							<div class="card">
								<div class="card-body">
									<h4 class="card-title">강사 정보</h4>
									<div class="mt-2" style="height: auto; width: auto;">
										<!-- 테이블 넣는곳, 테이블 색깔 변경 ->class만 변경 -->
										<table id="zero_config" class="table table-striped table-bordered">
											<tr>
												<td>강사 사진</td>
												<td>
												<!-- 경로  ${pageContext.request.contextPath}/memberPhoto/${getMemberPhoto.photoName} 이와같이 작성하면 파일 못찾음 -->
													<c:if test="${teacherOne.photoType=='image/jpg' || teacherOne.photoType== 'image/png' || teacherOne.photoType == 'image/jpeg' }">
														<img src="${pageContext.request.contextPath}/uploadFile/memberPhoto/teacherPhoto/${getMemberPhoto.photoName}" width="200" height="200">
													</c:if>
													<a href="${pageContext.request.contextPath}/uploadFile/memberPhoto/teacherPhoto/${teacherOne.photoName}" download="${teacherOne.photoOriginName}">
														${teacherOne.photoOriginName}
													</a>
												</td>
											</tr>
											<tr>
												<td>아이디</td>
												<td>${sessionLoginMember.memberId}</td>
											</tr>
											<tr>
												<td>강사 이름</td>
												<td>${teacherOne.teacherName}</td>
											</tr>
											<tr>
												<td>생일</td>
												<td>${teacherOne.teacherBirth}</td>
											</tr>
											<tr>
												<td>성별</td>
												<td>${teacherOne.teacherGender}</td>
											</tr>
											<tr>
												<td>이메일</td>
												<td>${teacherOne.teacherEmail}</td>
											</tr>
											<tr>
												<td>입사일</td>
												<td>${teacherOne.teacherEntryDate}</td>
											</tr>
											
											<tr>
												<td>휴대전화</td>
												<td>${teacherOne.memberPhoneNo}</td>
											</tr>
											<tr>
												<td>마지막 로그인 날짜</td>
												<td>${teacherOne.memberLastLoginDate}</td>
											</tr>
										</table>
									</div>
									<div>
										<a href="${pageContext.request.contextPath}/teacher/modifyTeacher?memberId=${sessionLoginMember.memberId}" class="btn btn-rounded btn-outline-info">강사 정보 수정</a>
										<span></span>
										<a href="${pageContext.request.contextPath}/teacher/freezeTeacher?memberId=${sessionLoginMember.memberId}" class="btn btn-rounded btn-outline-danger">강사 정보 삭제</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="${pageContext.request.contextPath}/dist/js/app-style-switcher.js"></script>
<script src="${pageContext.request.contextPath}/dist/js/feather.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/dist/js/sidebarmenu.js"></script>
<script src="${pageContext.request.contextPath}/dist/js/custom.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/extra-libs/c3/d3.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/extra-libs/c3/c3.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/chartist/dist/chartist.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/chartist-plugin-tooltips/dist/chartist-plugin-tooltip.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/extra-libs/jvector/jquery-jvectormap-world-mill-en.js"></script>
<script src="${pageContext.request.contextPath}/dist/js/pages/dashboards/dashboard1.min.js"></script>
</html>