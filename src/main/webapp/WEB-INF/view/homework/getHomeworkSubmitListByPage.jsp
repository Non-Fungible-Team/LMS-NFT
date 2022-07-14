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
<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/assets/images/favicon.png">
<title>Non-fungible LMS</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link href="${pageContext.request.contextPath}/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/dist/css/style.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
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
<h1>제출된 과제 목록</h1>
<div class="row">
					<div class="col-lg-12 col-md-12">
						<div class="card">
							<div class="card-body">
								<h4 class="card-title">제출된 과제 목록</h4>
									<a href="${pageContext.request.contextPath}/all/homework/getHomeworkListByPage?lectureNo=${sessionLectureNo}">
										<input type="button" class="btn btn-outline-success btn-rounded" style="float: right" value="목록">
									</a>
									<div class="mt-2" style="height: auto; width: auto;">
									<div class="table-responsive">
										<table id="zero_config" class="table table-striped table-bordered">
											<thead>
												<tr>
													<th style="text-align:center">과제번호</th>
													<th style="text-align:center">제출번호</th>
													<th style="text-align:center">강의번호</th>
													<th style="text-align:center">학생ID</th>
													<th style="text-align:center">과제제목</th>
													<th style="text-align:center">생성일</th>
													<th style="text-align:center">수정일</th>
													<th style="text-align:center">확인</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="hs" items="${homeworkSubmitList}">
													<tr>
														<td>${hs.homeworkNo }</td>
														<td>${hs.homeworkSubmitNo }</td>
														<td>${hs.lectureNo }</td>
														<td>${hs.memberId }</td>
														<td><a href="${pageContext.request.contextPath}/all/homework/getHomeworkSubmitOne?homeworkSubmitNo=${hs.homeworkSubmitNo}">${hs.homeworkSubmitTitle }</a></td>
														<td>${hs.homeworkSubmitCreateDate }</td>
														<td>${hs.homeworkSubmitUpdateDate }</td>
														<c:if test="${not empty hs.homeworkSubmitScore && not empty hs.homeworkSubmitFeedback }">
															<td><i class="fas fa-check">완료</i></td>
														</c:if>
														<c:if test="${empty hs.homeworkSubmitScore || empty hs.homeworkSubmitFeedback }">
															<td><a href="${pageContext.request.contextPath}/all/homework/getHomeworkSubmitOne?homeworkSubmitNo=${hs.homeworkSubmitNo}">채점</a></td>
														</c:if>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
	
	<form method="get" action="${pageContext.request.contextPath}/all/homework/getHomeworkSubmitListByPage">
		<c:if test="${currentPage>1}">
			<button type="submit" class="btn btn-outline-warning btn-rounded" name="currentPage" value="${currentPage-1}">이전</button>
		</c:if>
		<!-- 목록 사이 번호 표시 -->
		<c:forEach begin="1" end="10" step="1" var="i" varStatus="status">
			<c:if test="${ i< 5 && currentPage-(5-i)> 0 }">
				<button type = "submit" value ="${currentPage-(5-i)}" name = "currentPage" class="btn btn-outline-info btn-rounded">${currentPage-(5-i)}</button>
			</c:if>
			<c:if test="${ i==5 }">
				<button type = "submit" value ="${currentPage}" name = "currentPage" class="btn btn-outline-danger btn-rounded">${currentPage}</button>
			</c:if>
			<c:if test="${ i > 5 && currentPage+(i-5) <= lastPage }">
				<button type = "submit" value ="${currentPage+(i-5)}" name = "currentPage" class="btn btn-outline-info btn-rounded">${currentPage+(i-5)}</button>
			</c:if>
		</c:forEach>
		<c:if test="${currentPage<lastPage}">
			<button type="submit" class="btn btn-outline-success btn-rounded" name="currentPage" value="${currentPage+1}">다음</button>
		</c:if>
	</form>
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