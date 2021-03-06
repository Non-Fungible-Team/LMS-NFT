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
<title>Non-Fungible LMS</title>
<link href="${pageContext.request.contextPath}/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/dist/css/style.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$("#navAside").load('${pageContext.request.contextPath}/include/navAside.jsp');
	
	
	$('#insertQuestionList').click(function(){
		if($('#surveyQuestionListName').val() == '') {
			$('#NameHelper').text('제목을 입력해주세요');
    		$('#surveyQuestionListName').focus();
		}else if($('#surveyQuestionListContent').val() == '') {
			$('#NameHelper').text('');
			$('#ContentHelper').text('내용을 입력해주세요');
    		$('#surveyQuestionListContent').focus();
		}else {
			$('#ContentHelper').text('');
			$('#insertList').submit();
		}
		
	});
});
</script>
<style>
	    .helper {
	    	color : #FF0000;
	    }
	</style>
</head>
<body>
	<div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full" data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
		<!-- header include(네비게이션바) -->
		<div id="navAside"></div>
		<div class="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12 col-md-12">
						<div class="card">
							<div class="mt-2" style="height:auto; width:auto;">
								<div class="card-body">
									<h1>설문조사 질문 항목 생성</h1>
									<a href="${pageContext.request.contextPath}/manager/survey/getSurveyQuestionListByPage" class='btn btn-outline-success' style='float: right'>이전으로</a>
									
										<form method="post" action="${pageContext.request.contextPath}/manager/survey/insertSurveyQuestionList" id="insertList" name="insertList">
										<table id="zero_config" class="table table-striped table-bordered no-wrap">
											<tr>
												<td>질문 제목</td>
												<td><input type="text" id="surveyQuestionListName" name="surveyQuestionListName" class="form-control">
													&nbsp;<span id="NameHelper" class="helper"></span></td>
											<tr>
												<td>질문 내용</td>
												<td><textarea rows="10" cols="10" name="surveyQuestionListContent" id="surveyQuestionListContent" class="form-control"></textarea>
													&nbsp;<span id="ContentHelper" class="helper"></span></td>
										</table>
										<div>
											<button type="button" id="insertQuestionList"
												name="insertQuestionList" class="btn btn-info" style='float: right'>생성</button>
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
    <script src="${pageContext.request.contextPath}/dist/js/pages/chartjs/chartjs.init.js"></script>
    <script src="${pageContext.request.contextPath}/assets/libs/chart.js/dist/Chart.min.js"></script>
</body>
</html>