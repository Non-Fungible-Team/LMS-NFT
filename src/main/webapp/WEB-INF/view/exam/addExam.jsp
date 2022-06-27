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
	href="${pageContext.request.contextPath}/static/assets/images/favicon.png">
<title>Insert title here</title>
<link
	href="${pageContext.request.contextPath}/static/assets/extra-libs/c3/c3.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/static/assets/libs/chartist/dist/chartist.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/static/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/static/dist/css/style.min.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/static/assets/libs/jquery/dist/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>문제등록</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(document)
			.ready(
					function() {
						var appendCountQuestion = 1;
						var appendCountExample = 1;

						$('#addExamQuestion')
								.click(
										function() {
											if (appendCountQuestion >= 20)
												return;
											$('#questionSection')
													.append(
															"<table border='1'><tr><td>문제 번호"
																	+ appendCountQuestion
																	+ "번<input type='hidden' name='examQuestionNo' id='examQuestionNo'></td></tr><tr><td>시험 문제 내용</td><td><input type='text' name='examContent'></td></tr><tr><td>시험 문제 답안</td><td><input type='text' name='examCorrectAnswer'></td></tr><tr><td>배점</td><td><input type='text' name='examPoint'></td></tr><tr><td>시험 문제 타입</td><td><input type='radio' name='examType' id='examType' value='객관식'>객관식<input type='radio' name='examType' id='examType' value='주관식'>주관식</td></tr></table>");
											appendCountQuestion++;
										});

						$('#addExample')
								.click(
										function() {
											$('#exampleSection')
													.appendTo(
															"<table border='1'><tr><td>보기 번호"
																	+ appendCountExample
																	+ "번<input type='hidden' name='exampleNo' id='exampleNo'></td></tr><tr><td>보기 내용</td><td><input type='text' name='exampleContent' id='exampleContent''></td></tr>");
											appendCountExample++;
										});

						$('#deleteExamQuestion').click(function() {
							$('#questionSection table:last').remove();
							appendCountQuestion--;
							if (appendCountQuestion < 0) {
								appendCountQuestion = 1;
							}
						});
					});
</script>
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
				<h1>시험등록</h1>
				<div class="row">
					<div class="col-lg-12 col-md-12">
						<div class="card">
							<div class="card-body">
								<h4 class="card-title">과제 입력</h4>
								<div class="mt-2" style="height: auto; width: auto;">
									<form method="post" id="addExam"
										action="${pageContext.request.contextPath}/teacher/exam/addExam">
										<table id="zero_config"
											class="table table-striped table-bordered">
											<!-- 	    시험등록 -->
											<tr>
												<td>시험 이름 :</td>
												<td><input type="text" class="form-control"
													name="examTitle" id="examTitle"></td>
											</tr>
											<tr>
												<td>시험 문항 수 :</td>
												<td><input type="text" class="form-control"
													name="examCount" id="examCount"></td>
											</tr>
											<tr>
												<td>만점 :</td>
												<td><input type="text" class="form-control"
													name="examMaxScore" id="examMaxScore"></td>
											</tr>
											<tr>
												<td>시험시작일시 :</td>
												<td><input type="datetime-local" class="form-control"
													name="examStartDate" id="examStartDate"></td>
											</tr>
											<tr>
												<td>시험종료일시:</td>
												<td><input type="datetime-local" class="form-control"
													name="examEndDate" id="examEndDate"></td>
											</tr>
											<!--		   	문제 등록 -->
											<!-- 	    	<tr> -->
											<!-- 	    		<td>시험 문제 번호 : </td> -->
											<!-- 	    		<td><input type="text" name="examQuestionNo" id="examQuestionNo"></td> -->
											<!-- 	    	</tr> -->
											<!-- 	    	<tr> -->
											<!-- 	    		<td>시험 문제 내용 : </td> -->
											<!-- 	    		<td><input type="text" name="examContent" id="examContent"></td> -->
											<!-- 	    	</tr> -->
											<!-- 	    	<tr> -->
											<!-- 	    		<td>시험 문제 답안 : </td> -->
											<!-- 	    		<td><input type="text" name="examCorrectAnswer" id="examCorrectAnswer"></td> -->
											<!-- 	    	</tr> -->
											<!-- 	    	<tr> -->
											<!-- 	    		<td>시험 문제 배점 : </td> -->
											<!-- 	    		<td><input type="text" name="examPoint" id="examPoint"></td> -->
											<!-- 	    	</tr> -->
											<!-- 	    	<tr> -->
											<!-- 	    		<td>시험 문제 타입 : </td> -->
											<!-- 			    <td><input type="radio" name="examType" id="examType" value="객관식">객관식 -->
											<!--       			<input type="radio" name="examType" id="examType" value="주관식">주관식 -->
											<!-- 				</td> -->
											<!-- 	    	</tr> -->
											<tr>
												<td id="questionSection"></td>
											</tr>
											<tr>
												<td><button type="button"
														class="btn btn-outline-success btn-rounded"
														id="addExamQuestion" name="addExamQuestion">
														<i class="fas fa-check"></i>문제 추가
													</button></td>
												<td><button type="button"
														class="btn btn-outline-success btn-rounded"
														id="deleteExamQuestion" name="deleteExamQuestion">
														<i class="fas fa-check"></i>문제 삭제
													</button></td>
											</tr>
											<tr>
												<td id="exampleSection"></td>
											</tr>
											<tr>
												<td><button type="button"
														class="btn btn-outline-success btn-rounded"
														id="addExamExample" name="addExample">
														<i class="fas fa-check"></i>보기 추가
													</button></td>
												<td><button type="button"
														class="btn btn-outline-success btn-rounded"
														id="deleteExample" name="deleteExample">
														<i class="fas fa-check"></i>보기 삭제
													</button></td>
											</tr>
											<!--    	보기 등록 -->
											<!-- 	    	<tr> -->
											<!-- 	    		<td>보기 번호 : </td> -->
											<!-- 	    		<td><input type="text" name="exampleNo" id="exampleNo"></td> -->
											<!-- 	    	</tr> -->
											<!-- 	    	<tr> -->
											<!-- 	    		<td>보기 내용 : </td> -->
											<!-- 	    		<td><input type="text" name="exampleContent" id="exampleContent"></td> -->
											<!-- 	    	</tr> -->
										</table>
										<div>
											<button type="submit"
												class="btn btn-outline-success btn-rounded" id="addExam">
												<i class="fas fa-check"></i>시험등록
											</button>
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
<script
	src="${pageContext.request.contextPath}/static/dist/js/app-style-switcher.js"></script>
<script
	src="${pageContext.request.contextPath}/static/dist/js/feather.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/dist/js/sidebarmenu.js"></script>
<script
	src="${pageContext.request.contextPath}/static/dist/js/custom.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/assets/extra-libs/c3/d3.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/assets/extra-libs/c3/c3.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/assets/libs/chartist/dist/chartist.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/assets/libs/chartist-plugin-tooltips/dist/chartist-plugin-tooltip.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/assets/extra-libs/jvector/jquery-jvectormap-world-mill-en.js"></script>
<script
	src="${pageContext.request.contextPath}/static/dist/js/pages/dashboards/dashboard1.min.js"></script>
</html>