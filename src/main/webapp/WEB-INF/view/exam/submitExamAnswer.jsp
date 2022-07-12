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
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<!-- title icon -->
<link rel="icon" type="image/png" sizes="16x16"
	href="${pageContext.request.contextPath}/assets/images/favicon.png">
<title>Non-Fungible LMS</title>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link href="${pageContext.request.contextPath}/assets/extra-libs/c3/c3.min.css"	rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/dist/css/style.min.css" rel="stylesheet">
<script	src="${pageContext.request.contextPath}/assets/libs/jquery/dist/jquery.min.js"></script>
<script	src="${pageContext.request.contextPath}/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script	src="${pageContext.request.contextPath}/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<script>
	$('document').ready(function(){
	    $("#navAside").load('${pageContext.request.contextPath}/include/navAside.jsp');
	});
</script>
<body>
	<div id="main-wrapper" data-theme="light" data-layout="vertical"
		data-navbarbg="skin6" data-sidebartype="full"
		data-sidebar-position="fixed" data-header-position="fixed"
		data-boxed-layout="full">
		<!-- header include(네비게이션바) -->
		<div id="navAside"></div>
		<div class="page-wrapper">
			<div class="container-fluid">
				<h1>시험응시</h1>
				<!-- main화면 body start -->
				<!-- 첫번쨰 문단 -->
				<div class="row">
					<div class="col-lg-12 col-md-12">
						<div class="card">
							<div class="card-body">
								<h4 class="card-title">시험응시</h4>
								<form action="${pageContext.request.contextPath}/student/exam/submitExamAnswer" method="post" id="submitAnswer">
										<input type="hidden" name="examNo" value="${param.examNo}" readonly="readonly">
										<input type="hidden" class="form-control"	name="memberId" value="${sessionLoginMember.memberId}">
								<div class="mt-2" style="height: auto; width: auto;">
									<!-- 테이블 넣는곳, 테이블 색깔 변경 ->class만 변경 -->
									<table id="zero_config"
										class="table table-striped table-bordered table table-hover">
										<thead>
											<tr>
												<th class="table-primary">시험 번호</th>
												<th class="table-primary">시험 제목</th>
												<th class="table-primary">응시자</th>
												<th class="table-primary">시험 문항수</th>
												<th class="table-primary">만점</th>
												<th class="table-primary">시험 시작 일시</th>
												<th class="table-primary">시험 종료 일시</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td class="table-light"><input type="hidden" name="examNo">${examOne.examNo}</td>
												<td class="table-light">${examOne.examTitle}</td>
												<td class="table-light">${sessionLoginMember.memberId}</td>
												<td class="table-light">${examOne.examCount}</td>
												<td class="table-light">${examOne.examMaxScore}</td>
												<td class="table-light">${examOne.examStartDate}</td>
												<td class="table-light">${examOne.examEndDate}</td>
											</tr>
										</tbody>
									</table>
									<c:forEach var="examQuestionOneList" items="${examQuestionOneList}"
										varStatus="status">
										<c:set var="qeustionNo" value="Q${examQuestionOneList.examQuestionNo}"></c:set>
										<c:set var="answerNo" value="A${examQuestionOneList.examQuestionNo}"></c:set>
										<table id="zero_config"
											class="table table-striped table-bordered">
											<thead>
												<tr>
													<th class="table-info">문제 번호</th>
													<th class="table-info">문제 내용</th>
													<th class="table-info">배점</th>
													<th class="table-info">문제유형</th>
													<th class="table-info">보기</th>
													<th class="table-info">답안</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td class="table-light"><input type="hidden" name="examAnswer[${status.count}].examQuestionNo" id="examQuestionNo" value="${examQuestionOneList.examQuestionNo}">${examQuestionOneList.examQuestionNo}</td>
													<td class="table-light">${examQuestionOneList.examContent}</td>
													<td class="table-light">${examQuestionOneList.examPoint}</td>
													<td class="table-light">${examQuestionOneList.examType}</td>
													<td class="table-light">
														<table>
															<thead>
																<tr>
																	<th class="table-info">보기 번호</th>
																	<th class="table-info">보기 내용</th>
																</tr>
															</thead>
															<c:forEach var="examExampleOneList" items="${examExampleOneList}">
																<c:forEach var="example" items="${examExampleOneList[qeustionNo]}">
																	<tbody>
																		<tr>
																			<td class="table-light">${example.exampleNo}</td>
																			<td class="table-light">${example.exampleContent}</td>
																		</tr>
																	</tbody>
																</c:forEach>
															</c:forEach>
														</table>
													</td>
													<td><input type="text" name="examAnswer[${status.count}].examAnswer" id="examAnswer"></td>
												</tr>
											</tbody>
										</table>
									</c:forEach>
									<div style="float: right">
										<button type="submit" class="btn btn-outline-success btn-rounded">
											<i class="fas fa-check"></i>제출
										</button>
									</div>
								</div>
										</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script	src="${pageContext.request.contextPath}/dist/js/app-style-switcher.js"></script>
<script	src="${pageContext.request.contextPath}/dist/js/feather.min.js"></script>
<script	src="${pageContext.request.contextPath}/assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
<script	src="${pageContext.request.contextPath}/dist/js/sidebarmenu.js"></script>
<script	src="${pageContext.request.contextPath}/dist/js/custom.min.js"></script>
<script	src="${pageContext.request.contextPath}/assets/extra-libs/c3/d3.min.js"></script>
<script	src="${pageContext.request.contextPath}/assets/extra-libs/c3/c3.min.js"></script>
<script	src="${pageContext.request.contextPath}/assets/libs/chartist/dist/chartist.min.js"></script>
<script	src="${pageContext.request.contextPath}/assets/libs/chartist-plugin-tooltips/dist/chartist-plugin-tooltip.min.js"></script>
<script	src="${pageContext.request.contextPath}/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.min.js"></script>
<script	src="${pageContext.request.contextPath}/assets/extra-libs/jvector/jquery-jvectormap-world-mill-en.js"></script>
<script	src="${pageContext.request.contextPath}/dist/js/pages/dashboards/dashboard1.min.js"></script>
</html>