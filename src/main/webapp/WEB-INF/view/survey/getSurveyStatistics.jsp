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
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script>
$(document).ready(function() {
	$("#navAside").load(
	'${pageContext.request.contextPath}/include/navAside.jsp');
	
	getGraph();
	
	
	function getGraph(){
		let AnswerContent = [];
		
		var url = '${pageContext.request.contextPath}/rest/manager/survey/getSurveyStatistics?surveyQuestionNo=1'; 
		console.log(url);
		$.ajax({
			type:'get'
			,url: url
			,success: function(data){ // 백앤드 응답 문자열을 자바스크립트 객체로 변환 후 매개값 입력됨
				console.log(data);
				for(var i=0; i<jsonData.length; i++){
					AnswerContent[i].push(data[i]);
				}
				
				

		new Chart("bar-chart-horizontal", {
				type:"horizontalBar"
				,data:{
					labels:["1점","2점","3점","4점","5점"]
					,datasets:[{
					label:"123"
					,backgroundColor:["#22ca80","#e83e8c","#5f76e8","#fdc16a","#343a40"]
					,data:[8478,6267,5534,4784,3433]}]
					},
					options:{legend:{display:!1}
					,title:{display:!0,text:"${answerAverage.surveyQuestionListName} 점수 분포도"}}});
		
			}
		});
	};
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
				<c:forEach var="multi" items="${allAnswer}">
					<c:if test="${multi.surveyAnswerType=='객관식'}">
						<div class="col-lg-6">
							<div class="card">
								<div class="card-body">
									<h4 class="card-title">${multi.surveyQuestionListName}</h4>
									<canvas id="bar-chart-horizontal" height="150"> </canvas>
								</div>
							</div>
						</div>
					</c:if>
				</c:forEach>
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
    <script src="${pageContext.request.contextPath}/static/dist/js/pages/chartjs/chartjs.init.js"></script>
    <script src="${pageContext.request.contextPath}/static/assets/libs/chart.js/dist/Chart.min.js"></script>
</body>
</html>