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
<title>getScheduleOne</title>
<link href="${pageContext.request.contextPath}/static/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/static/dist/css/style.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
</head>
	<script>
		$('document').ready(function(){
	    	$("#navAside").load('${pageContext.request.contextPath}/include/navAside.jsp');
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
								<h4 class="card-title">getScheduleOne - ${ scheduleOne.scheduleTitle }</h4>
								<div class="mt-2" style="height: auto; width: auto;">

									<table id="zero_config" class="table table-striped table-bordered no-wrap">
										<tr>
											<td>번호</td>
											<td>${ scheduleOne.scheduleNo }</td>
										</tr>
										<tr>
											<td>날짜</td>
											<td>${ scheduleOne.scheduleDate }</td>
										</tr>
										<tr>
											<td>일정 제목</td>
											<td>${ scheduleOne.scheduleTitle }</td>
										</tr>
										<tr>
											<td>내용</td>
											<td>${ scheduleOne.scheduleContent }</td>
										</tr>
										<tr>
											<td>작성자</td>
											<td>${ scheduleOne.memberId }</td>
										</tr>
										<tr>
											<td>생성일</td>
											<td>${ scheduleOne.scheduleCreateDate }</td>
										</tr>
										<tr>
											<td>수정일</td>
											<td>${ scheduleOne.scheduleUpdateDate }</td>
										</tr>
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