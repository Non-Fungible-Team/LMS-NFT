<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 반응형 웹 -->
<!-- 반응형 웹 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<!-- title icon -->
<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/static/assets/images/favicon.png">
<title>설문조사 통계 페이지</title>
<link href="${pageContext.request.contextPath}/static/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/static/dist/css/style.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/chart.js/2.5.0/Chart.min.js"></script>
<script>
$(document).ready(function() {
	$("#navAside").load(
	'${pageContext.request.contextPath}/include/navAside.jsp');
	
	
});
</script>

</head>
<body>
	<div id="main-wrapper" data-theme="light" data-layout="vertical"
		data-navbarbg="skin6" data-sidebartype="full"
		data-sidebar-position="fixed" data-header-position="fixed"
		data-boxed-layout="full">
		<!-- header include(네비게이션바) -->
		<div id="navAside"></div>

		<div class="page-wrapper">
			<div class="container-fluid">
				<h4>객관식 답변 통계</h4>
				<div class="col-lg-6">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title">bar-chart-horizontal</h4>
							<div id="myChart" style="width:100%; max-width:600px; height:500px;"></div>
						</div>
					</div>
				</div>
				
				<h4>주관식 답변 통계</h4>
				<table id="zero_config"
					class="table table-striped table-bordered no-wrap">
					<c:forEach var="qs" items="${allAnswer}">
						<c:if test="${qs.surveyAnswerType=='주관식'}">
								<td>${qs.surveyQuestionListName}</td>
						</c:if>
						<c:if test="${qs.surveyAnswerType=='주관식'}">
							<c:forEach var="answer" items="${allAnswer}">
								<c:if test="${qs.surveyQuestionListName==answer.surveyQuestionListName}">
									<tr>
										<td>질문 답변</td>
										<td>${answer.surveyShortAnswerContent}</td>
									</tr>
								</c:if>
							</c:forEach>
						</c:if>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	
</body>
</html>