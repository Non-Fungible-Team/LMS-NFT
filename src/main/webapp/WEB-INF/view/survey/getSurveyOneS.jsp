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
<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/static/assets/images/favicon.png">
<title>설문조사 상세보기</title>
<link href="${pageContext.request.contextPath}/static/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/static/dist/css/style.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$('document').ready(function() {
		$("#navAside").load('${pageContext.request.contextPath}/include/navAside.jsp');
		
		$('#surveySubmit').click(function(){
			$('#surveyAnswerForm').submit();
			
		});
	});
</script>
</head>
<body>
	<div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full" data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
		<!-- header include(네비게이션바) -->
		<div id="navAside"></div>
			<div class="page-wrapper">
				<div class="container-fluid">
					<h1>설문조사 상세보기</h1>
					<div class="mt-2" style="height:auto; width:auto;">
						<a href="${pageContext.request.contextPath}/all/survey/getSurveyListByPage" class="btn">이전으로</a>
						<table id="zero_config" class="table table-striped table-bordered no-wrap">
							<tr>
								<td>설문조사 번호</td>
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
				<h2>답변등록</h2>
				<div class="mt-2" style="height:auto; width:auto;">
					<form method="post" action="${pageContext.request.contextPath}/student/survey/insertSurveyAnswer" id="surveyAnswerForm">
						<table id="zero_config" class="table table-striped table-bordered no-wrap">
						
							<c:forEach var="sqa" items="${surveyQuestionList}" varStatus="status">
								<tr>
									<td>${sqa.surveyQuestionListName}</td>
									<td>
										<input type="hidden" name="surveyAnswer[${status.index}].surveyNo" value="${surveyOne.surveyNo}">
										<input type="hidden" name="surveyAnswer[${status.index}].memberId" value="${loginMember.memberId}">
									</td>
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
								<tr>
							</c:forEach>
						</table>
						<button type="button" id="surveySubmit">설문조사 제출</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>