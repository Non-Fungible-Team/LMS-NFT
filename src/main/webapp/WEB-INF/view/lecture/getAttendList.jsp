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
<title>Attend List</title>
<link href="${pageContext.request.contextPath}/static/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/static/dist/css/style.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
</head>
<script>
	$('document').ready(function() {
		$("#navAside").load(
			'${pageContext.request.contextPath}/include/navAside.jsp');
	});
</script>
<body>
	<div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full" data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
		<!-- header include(네비게이션바) -->
		<div id="navAside"></div>
		<div class="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12 col-md-6">
						<div class="card">
							<div class="card-body">
								<h4 class="card-title">출석 현황</h4>
								<div class="mt-2" style="height: auto; width: auto;">
									<div>
										<form method="get" action="${pageContext.request.contextPath}/teacher/lecture/getAttendList">
											<input type ="hidden" name="lectureNo" value="${lectureNo}">
											<select name="attendDate" onchange ="this.form.submit()">
												<option value="">출석 날짜 선택</option>
												<c:forEach var="ls" items="${lectureScheduleList}">
													<option value="${ls.lectureScheduleDate}">${ls.lectureScheduleDate}</option>
												</c:forEach>
											</select>
										</form>
									</div>
								 </div>
								
									<table id="zero_config" class="table table-striped table-bordered no-wrap">
										<thead>
											<tr>
												<th>출석 날짜</th>
												<th>강의 번호</th>
												<th>강의명</th>
												<th>학생 아이디</th>
												<th>학생 이름</th>
												<th>출석 상태</th>
												<th>사유</th>
												<th>체크날짜</th>
												<th><a href="${pageContext.request.contextPath}/manager/lecture/addAttend" class="btn btn-primary btn-rounded"> <i class="fas fa-check"></i> 출석 체크
												</a></th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="a" items="${attendList}">
												<tr>
													<td>${a.attendDate}</td>
													<td>${a.lectureNo}</td>
													<td>${a.lectureName}</td>
													<td>${a.memberId}</td>
													<td>${a.studentName}</td>
													<td>${a.attendStatus}</td>
													<td>${a.attendReason}</td>
													<td>${a.attendCreateDate}</td>
													<td>
														<div class="btn-group" role="group" aria-label="Basic example">
															<a href="${pageContext.request.contextPath}/#" class="btn btn-info">수정</a> <a href="${pageContext.request.contextPath}/#" class="btn btn-danger">삭제</a>
														</div>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
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