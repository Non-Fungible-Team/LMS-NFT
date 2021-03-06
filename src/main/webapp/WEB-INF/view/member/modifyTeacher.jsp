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
<title>강사 정보 수정</title>
<link href="${pageContext.request.contextPath}/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/dist/css/style.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$('document').ready(function() {
		$("#navAside").load('${pageContext.request.contextPath}/include/navAside.jsp');
	});
</script>
</head>
<body>

	<div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full" data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
		<!-- header include(네비게이션바) -->
		<div id="navAside"></div>
		<div class="page-wrapper">
			<div class="container-fluid">
				<h1>강사 정보 수정</h1>
				<!-- main화면 body start -->

				<!-- 첫번쨰 문단 -->
				<div class="container p-5 my-5 border">
					<div class="row">
						<div class="col-lg-12 col-md-12">
							<div class="card">
								<div class="card-body">
									<h4 class="card-title">강사 정보 수정</h4>
									<div class="mt-2" style="height: auto; width: auto;">
										<!-- 테이블 넣는곳, 테이블 색깔 변경 ->class만 변경 -->
										<form action="${pageContext.request.contextPath}/teacher/modifyTeacher" method="post" enctype="multipart/form-data">
											<table id="zero_config" class="table table-striped table-bordered">
												<tr>
													<td>강사 아이디</td>
													<td>
														<input type="text" name="memberId" id="memberId" value="${sessionLoginMember.memberId}" readonly="readonly">
													</td>
												</tr>
												<tr>
													<td>강사 이름</td>
													<td>
														<input type="text" name="teacherName" id="teacherName" value="${teacherOne.teacherName}">
													</td>
												</tr>
												
												<tr>
													<td>생일</td>
													<td>
														<input type="date" name="teacherBirth" id="teacherBirth" value="${teacherOne.teacherBirth}">
													</td>
												</tr>
												<tr>
													<td>성별</td>
													<c:if test="${teacherOne.teacherGender=='M'}">
														<td>
															<input type="radio" value="M" name="teacherGender" class="gender" checked>남 
															<input type="radio" value="F" name="teacherGender" class="gender">여 
														</td>
													</c:if>
													<c:if test="${teacherOne.teacherGender=='F'}">
														<td>
															<input type="radio" value="M" name="teacherGender" class="gender" >남 
															<input type="radio" value="F" name="teacherGender" class="gender" checked>여 
														</td>
													</c:if>
												</tr>
												<tr>
													<td>이메일</td>
													<td>
														<input type="text" name="teacherEmail" id="teacherEmail" value="${teacherOne.teacherEmail}">
													</td>
												</tr>
												<tr>
													<td>입사일</td>
													<td>
														<input type="text" name="teacherEntryDate" id="teacherEntryDate" value="${teacherOne.teacherEntryDate}">
													</td>
												</tr>
												<tr>
													<td>담당 과목</td>
													<td>
														<input type="text" name="teacherSubject" id="teacherSubject" value="${teacherOne.teacherSubject}">
													</td>
												</tr>
												<tr>
													<td>강사 사진</td>
													<td>
														<input type="file" id="memberPhotoOne" name="memberPhotoOne">
														<span id="teacherPhotoSection"></span>
													</td>
												</tr>
												<tr>
													<td>휴대전화</td>
													<td>
														<input type="text" name="memberPhoneNo" id="memberPhoneNo" value="${teacherOne.memberPhoneNo}">
													</td>
												</tr>
												<tr>
													<td colspan="2">
														<button type = "submit" id="submit"  class="btn btn-rounded btn-outline-success">강사 정보 수정</button>
													</td>
												</tr>
											</table>
										<div>
											<a href="${pageContext.request.contextPath}/login" class="btn btn-rounded btn-outline-primary">로그인 페이지</a>
											<a href="${pageContext.request.contextPath}/all/modifyMemberPassword" class="btn btn-rounded btn-outline-primary">비밀번호 변경</a>
										</div>
									</form>
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