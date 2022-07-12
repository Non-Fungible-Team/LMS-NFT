<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 반응형 웹 -->
<link rel="stylesheet"	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<!-- title icon -->
<link rel="icon" type="image/png" sizes="16x16"	href="${pageContext.request.contextPath}/assets/images/favicon.png">
<title>Non-Fungible LMS</title>
<link href="${pageContext.request.contextPath}/assets/extra-libs/c3/c3.min.css"	rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/dist/css/style.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/assets/libs/jquery/dist/jquery.min.js"></script> 
<script src="${pageContext.request.contextPath}/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script	src="${pageContext.request.contextPath}/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
</head>
<script>
	$('document')
			.ready(
					function() {
						$("#navAside")
								.load(
										'${pageContext.request.contextPath}/include/navAside.jsp');
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
				<h1>시험 수정</h1>
				<div class="row">
					<div class="col-lg-12 col-md-12">
						<div class="card">
							<div class="card-body">
								<h4 class="card-title">시험 수정</h4>
								<div class="mt-2" style="height: auto; width: auto;">
									<!-- 테이블 넣는곳, 테이블 색깔 변경 ->class만 변경 -->
									<form
										action="${pageContext.request.contextPath}/teacher/exam/modifyExam"
										method="post">
										<input type="hidden" name="examNo" value="${exam.examNo}">
										<input type="hidden" name="examQuestionNo"
											value="${examQuestion.examQuestionNo}"> <input
											type="hidden" name="exampleNo"
											value="${examExample.exampleNo}">
										<table id="zero_config"
											class="table table-striped table-bordered">
											<!-- 	    시험 수정 -->
											<tr>
												<td>시험 이름 :</td>
												<td><input type="text" name="examTitle" id="examTitle"
													class="form-control" value="${exam.examTitle}"></td>
											</tr>
											<tr>
												<td>시험 문항 수 :</td>
												<td><input type="text" name="examCount" id="examCount"
													class="form-control" value="${exam.examCount}"></td>
											</tr>
											<tr>
												<td>만점 :</td>
												<td><input type="text" name="examMaxScore"
													class="form-control" id="examMaxScore"
													value="${exam.examMaxScore}"></td>
											</tr>
											<tr>
												<td>시험시작일시 :</td>
												<td><input type="datetime-local" name="examStartDate"
													class="form-control" id="examStartDate"
													value="${exam.examStartDate}"></td>
											</tr>
											<tr>
												<td>시험종료일시:</td>
												<td><input type="datetime-local" name="examEndDate"
													class="form-control" id="examEndDate"
													value="${exam.examEndDate}"></td>
											</tr>
											<!--	   	문제 수정 -->
											<tr>
												<td>시험 문제 번호 :</td>
												<td><input type="text" name="examQuestionNo"
													id="examQuestionNo" class="form-control"
													value="${examQuestion.examQuestionNo}"></td>
											</tr>
											<tr>
												<td>시험 문제 내용 :</td>
												<td><input type="text" name="examContent"
													id="examContent" class="form-control"
													value="${examQuestion.examContent}"></td>
											</tr>
											<tr>
												<td>시험 문제 답안 :</td>
												<td><input type="text" name="examCorrectAnswer"
													id="examCorrectAnswer" class="form-control"
													value="${examQuestion.examCorrectAnswer}"></td>
											</tr>
											<tr>
												<td>시험 문제 배점 :</td>
												<td><input type="text" name="examPoint" id="examPoint"
													class="form-control" value="${examQuestion.examPoint}"></td>
											</tr>
											<tr>

												<td>시험 문제 타입 :</td>
												<td>
												<input type="radio" name="examType" id="examType" value="객관식">객관식 
												<input	type="radio" name="examType" id="examType" value="주관식">주관식
												</td>
											</tr>
											<!--    	보기 수정 -->
											<tr>
												<td>보기 번호 :</td>
												<td><input type="text" name="exampleNo" id="exampleNo"
													class="form-control" value="${examExample.exampleNo}"></td>
											</tr>
											<tr>
												<td>보기 내용 :</td>
												<td><input type="text" name="exampleContent"
													id="exampleContent" class="form-control"
													value="${examExample.exampleNo}"></td>
											</tr>
										</table>
										<!-- example input 태그가 추가될 영역 -->
										<div id="QuestionSection">
											<button type="button" id="addExamQuestion"
												class="btn btn-outline-success btn-rounded">문제 추가</button>
										</div>
										<div id="ExampleSection"></div>
										<button type="button" id="addExamExample"
											class="btn btn-outline-success btn-rounded">보기 추가</button>
										<div>
											<button class="btn btn-info" type="submit"
												class="btn btn-default">
												<i class="fas fa-check"></i>수정완료
											</button>
											<input type="reset"
												class="btn btn-outline-success btn-rounded" value="초기화">
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