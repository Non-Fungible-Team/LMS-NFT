<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
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
		$("#navAside").load('${pageContext.request.contextPath}/include/navAside.jsp');
		});
</script>
<body>
<div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full" data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
		<!-- header include(네비게이션바) -->
		<div id="navAside"></div>
		<div class="page-wrapper">
			<div class="container-fluid">
<h1>getHomeworkSubmitOne</h1>
	<div class="row">
					<div class="col-lg-12 col-md-12">
						<div class="card">
							<div class="card-body">
								<h4 class="card-title">제출과제 상세보기</h4>
									<div class="mt-2" style="height: auto; width: auto;">
									<table id="zero_config" class="table table-striped table-bordered">
										<tr>
											<td>homeworkSubmitNo</td>
											<td><input type="number" name="homeworkSubmitNo" value="${homeworkSubmitOne.homeworkSubmitNo}"></td>
										</tr>
										<tr>
											<td>homeworkNo</td>
											<td><input type="text" name="homeworkNo" value="${homeworkSubmitOne.homeworkNo}"></td>
										</tr>
<!-- 										<tr> -->
<!-- 											<td>lectureNo</td> -->
<%-- 											<td><input type="text" name="lectureNo" value="${homeworkSubmitOne.lectureNo}"></td> --%>
<!-- 										</tr> -->
										<tr>
											<td>memberId</td>
											<td><input type="text" name="memberId" value="${homeworkSubmitOne.memberId }"></td>
										</tr>
										<tr>
											<td>homeworkSubmitTitle</td>
											<td><input type="text" name="homeworkSubmitTitle" value="${homeworkSubmitOne.homeworkSubmitTitle }"></td>
										</tr>
										<tr>
											<td>homeworkSubmitContent</td>
											<td><textarea name="homeworkSubmitContent" >${homeworkSubmitOne.homeworkSubmitContent }</textarea></td>
										</tr>
<!-- 										<tr> -->
<!-- 											<td>homeworkSubmitFileName</td> -->
<%-- 											<td><input type="text" name="homeworkSubmitFileName" value="${homeworkSubmitFileList.homeworkSubmitFileName }"></td> --%>
<!-- 										</tr> -->
										<tr>
											<td>homeworkSubmitCreateDate</td>
											<td><input type="text" name="homeworkSubmitCreateDate" value="${homeworkSubmitOne.homeworkSubmitCreateDate }"></td>
										</tr>
										<tr>
											<td>homeworkSubmitUpdateDate</td>
											<td><input type="text" name="homeworkSubmitUpdateDate" value="${homeworkSubmitOne.homeworkSubmitUpdateDate }"></td>
										</tr>
										<tr>
											<td>homeworkSubmitScore</td>
											<td><input type="text" name="homeworkSubmitScore" value="${homeworkSubmitOne.homeworkSubmitScore }"></td>
										</tr>
										<tr>
											<td>homeworkSubmitFeedback</td>
											<td><input type="text" name="homeworkSubmitFeedback" value="${homeworkSubmitOne.homeworkSubmitFeedback}"></td>
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