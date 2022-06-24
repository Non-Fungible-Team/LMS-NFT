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
<title>설문조사 페이지</title>
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
		});
</script>
</head>
<body>
	<div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full" data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
		<!-- header include(네비게이션바) -->
		<div id="navAside"></div>
			<div class="page-wrapper">
				<div class="container-fluid">
					<h1>설문조사</h1>
						<div class="mt-2" style="height:auto; width:auto;">
				            <table id="zero_config" class="table table-striped table-bordered no-wrap">
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>내용</th>
								<th>생성일</th>
								<th>설문시작일</th>
								<th>설문마감일</th>
								<th>강의</th>
								<th>작성자</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="sv" items="${surveyList}">
								<tr>
									<td>${sv.surveyNo}</td>
									<td><a href="${pageContext.request.contextPath}/survey/getSurveyOne?surveyNo=${sv.surveyNo}">${sv.surveyTitle}</a></td>
									<td>${sv.surveyContent}</td>
									<td>${sv.surveyCreateDate}</td>
									<td>${sv.surveyStartlineDate}</td>
									<td>${sv.surveyDeadlineDate}</td>
									<td>${sv.lectureNo}</td>
									<td>${sv.memberId}</td>
								</tr>
							</c:forEach>
						</tbody>
						</table>
						<ul>
						<c:if test="${currentPage>1}">
							<li><a
								href="${pageContext.request.contextPath}/getSurveyListByPage?currentPage=${currentPage-1}">이전</a></li>
						</c:if>
						<c:if test="${currentPage<lastPage}">
							<li><a
								href="${pageContext.request.contextPath}/getSurveyListByPage?currentPage=${currentPage+1}">다음</a></li>
						</c:if>
					</ul>
	
					<div>
						<a class="btn btn-info" href="${pageContext.request.contextPath}/manager/survey/insertSurvey">설문조사 등록</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>