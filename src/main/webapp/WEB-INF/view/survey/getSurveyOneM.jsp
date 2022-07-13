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
	$('document').ready(function() {
		$("#navAside").load('${pageContext.request.contextPath}/include/navAside.jsp');
		
		var today = new Date();

		var year = today.getFullYear();
		var month = ('0' + (today.getMonth() + 1)).slice(-2);
		var day = ('0' + today.getDate()).slice(-2);

		var dateString = year + '-' + month  + '-' + day;
		console.log(dateString);
		
		var sn = ${surveyOne.surveyNo}
		
		
		$(".surveyAtag").click(function(){
			var s = $(this).data('startline');
			console.log("s= "+s);
			console.log("dateString"+dateString);
			if(dateString < s){
				$('#surveyHelper').text('');
				$('#updateSurveyForm').submit();
			}else {
				$('#surveyHelper').text('설문조사 기간입니다');
			}
		})
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
								<h1 class="card-title">설문조사 상세보기</h1>
								<span id="surveyHelper" class="helper"></span>
								<div class="mt-2" style="height:auto; width:auto;">
									<a href="${pageContext.request.contextPath}/all/survey/getSurveyListByPage?lectureNo=${surveyOne.lectureNo}" class='btn btn-outline-success'>이전으로</a>
									<form method="get" action="${pageContext.request.contextPath}/manager/survey/updateSurveyForm?surveyNo=${surveyOne.surveyNo}" id="updateSurveyForm">
									<input type ="hidden" value="${surveyOne.surveyNo}" name="surveyNo">
									<a href="#" onclick="return false;" class="surveyAtag btn btn-outline-info btn-rounded" data-deadline="${surveyOne.surveyDeadlineDate}">수정</a>
									</form>
									<table id="zero_config" class="table table-striped table-bordered no-wrap">
										<tr>
											<td>공지 번호</td>
											<td>${surveyOne.surveyNo}</td>
										</tr>
										<tr>
											<td>제목</td>
											<td>${surveyOne.surveyTitle}</td>
										</tr>
										<tr>
											<td>내용</td>
											<td>${surveyOne.surveyContent}</td>
										</tr>
										<tr>
											<td>등록일</td>
											<td>${surveyOne.surveyCreateDate}</td>
										</tr>
										<tr>
											<td>설문 시작일</td>
											<td>${surveyOne.surveyStartlineDate}</td>
										</tr>
										<tr>
											<td>설문 마감일</td>
											<td>${surveyOne.surveyDeadlineDate}</td>
										</tr>
									</table>
								</div>
							<h1 class="card-title">질문</h1>
							<div class="mt-2" style="height:auto; width:auto;">
								<table id="zero_config" class="table table-striped table-bordered no-wrap">
									<c:forEach var="sqa" items="${QuestionList}" varStatus="status">
										<tr>
											<td>${sqa.surveyQuestionListName}</td>
										</tr>
										<tr>
											<td id="questionType">${sqa.surveyQuestionType}</td>
											<td>${sqa.surveyQuestionContent}</td>
										</tr> 
										<tr>
											<td>답변</td>
											<td>
												<c:if test="${sqa.surveyQuestionType=='객관식'}">
													<input type='hidden' name='surveyAnswer[${status.index}].SurveyShortAnswerType' value="객관식" readonly>
													<input type='number' name='surveyAnswer[${status.index}].surveyQuestionNo' value="${sqa.surveyQuestionNo}" readonly>
													<input type='radio' name='surveyAnswer[${status.index}].surveyMultipleAnswerContent' value=1> 1  &nbsp;&nbsp;
													<input type='radio' name='surveyAnswer[${status.index}].surveyMultipleAnswerContent' value=2> 2  &nbsp;&nbsp;
													<input type='radio' name='surveyAnswer[${status.index}].surveyMultipleAnswerContent' value=3> 3  &nbsp;&nbsp;
													<input type='radio' name='surveyAnswer[${status.index}].surveyMultipleAnswerContent' value=4> 4  &nbsp;&nbsp;
													<input type='radio' name='surveyAnswer[${status.index}].surveyMultipleAnswerContent' value=5> 5  &nbsp;&nbsp;
												</c:if>
												<c:if test="${sqa.surveyQuestionType=='주관식'}">
													<input type='hidden' name='surveyAnswer[${status.index}].SurveyShortAnswerType' value="주관식" readonly>
													<input type='number' name='surveyAnswer[${status.index}].surveyQuestionNo' value="${sqa.surveyQuestionNo}" readonly>
													<input type='text' name='surveyAnswer[${status.index}].surveyShortAnswerContent'>
												</c:if>
											</td>
										</tr>
									</c:forEach>
								</table>
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