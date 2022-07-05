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
	$(document).ready(function() {
		$("#navAside").load('${pageContext.request.contextPath}/include/navAside.jsp');
		
		var appendCount = 1;
		var appendCountList = 0;
// 		객관식 문제 추가
		$('#addExamMultiQuestion').click(function() {
			if (appendCount >= 20)	return;
			appendCountList = appendCount-1;
			$('#questionSection').append("<table border='1'><tr><td>문제 번호  "+ appendCount +"번<input type='hidden' name='examQuestionList["+appendCountList+"].examQuestionNo' value='"+appendCount+"'></td></tr><tr><td>문제 내용</td><td><input type='text' name='examQuestionList["+appendCountList+"].examContent'></td></tr><tr><td><input type='hidden' name='examQuestionList["+appendCountList+"].exampleNo' value='1'>보기 1번</td><td><input type='text' name='examQuestionList["+appendCountList+"].exampleOneContent'></td></tr><tr><td><input type='hidden' name='examQuestionList["+appendCountList+"].exampleNo' value='2'>보기 2번</td><td><input type='text' name='examQuestionList["+appendCountList+"].exampleTwoContent'></td></tr><tr><td><input type='hidden' name='examQuestionList["+appendCountList+"].exampleNo' value='3'>보기 3번</td><td><input type='text' name='examQuestionList["+appendCountList+"].exampleThreeContent'></td></tr><tr><td><input type='hidden' name='examQuestionList["+appendCountList+"].exampleNo' value='4'>보기 4번</td><td><input type='text' name='examQuestionList["+appendCountList+"].exampleFourContent'></td></tr><tr><td>문제 답안</td><td><input type='text' name='examQuestionList["+appendCountList+"].examCorrectAnswer'></td></tr><tr><td>배점</td><td><input type='text' name='examQuestionList["+appendCountList+"].examPoint'></td></tr><tr><td>문제 타입</td><td><input type='hidden' name='examQuestionList["+appendCountList+"].examType' value='객관식'>객관식</td></tr></table><br>");
				appendCount++;
			});
// 		주관식 문제 추가
		$('#addExamShortQuestion').click(function() {
			if (appendCount >= 20)	return;
			appendCountList = appendCount-1;
			$('#questionSection').append("<table border='1'><tr><td>문제 번호  "+ appendCount +"번<input type='hidden' name='examQuestionList["+appendCountList+"].examQuestionNo' value='"+appendCount+"'></td></tr><tr><td>문제 내용</td><td><input type='text' name='examQuestionList["+appendCountList+"].examContent'></td></tr><tr><td>문제 답안</td><td><input type='text' name='examQuestionList["+appendCountList+"].examCorrectAnswer'></td></tr><tr><td>배점</td><td><input type='text' name='examQuestionList["+appendCountList+"].examPoint'></td></tr><tr><td>문제 타입</td><td><input type='hidden' name='examQuestionList["+appendCountList+"].examType' value='주관식'>주관식</td></tr></table><br>");
				appendCount++;
			});
// 		문제 삭제
		$('#deleteExamQuestion').click(function() {
			$('#questionSection table:last').remove();
				appendCount--;
				appendCountList--;
				if(appendCount <= 0) {
					appendCount = 1;
					appendCountList = 0;
				}
			});
		
		$('#addExam').click(function(){
			if($('#examTitle').val() == '') {
				$('#examTitleHelper').text('제목을 입력하세요');
				$('examCountHelper').text('');
				$('#examMaxScoreHelper').text('');
				$('#examStartDateHelper').text('');
				$('#examEndDateHelper').text('');
				$('#questionSectionHelper').text('');
				
	    		$('#examTitle').focus();
			} else if($('#examCount').val() == '') {
				$('#examTitleHelper').text('');
				$('#examCountHelper').text('문항 수를 입력하세요');
				$('#examMaxScoreHelper').text('');
				$('#examStartDateHelper').text('');
				$('#examEndDateHelper').text('');
				$('#questionSectionHelper').text('');
				
	    		$('#examCount').focus();
			} else if($('#examMaxScore').val() == '') {
				$('#examTitleHelper').text('');
				$('examCountHelper').text('');
				$('#examMaxScoreHelper').text('만점을 입력하세요');
				$('#examStartDateHelper').text('');
				$('#examEndDateHelper').text('');
				$('#questionSectionHelper').text('');
				
	    		$('#examMaxScore').focus();
			} else if($('#examStartDate').val() == '') {
				$('#examTitleHelper').text('');
				$('examCountHelper').text('');
				$('#examMaxScoreHelper').text('');
				$('#examStartDateHelper').text('시작일시를 선택하세요');
				$('#examEndDateHelper').text('');
				$('#questionSectionHelper').text('');
				
	    		$('#examStartDate').focus();
			} else if($('#examEndDate').val() == '') {
				$('#examTitleHelper').text('');
				$('examCountHelper').text('');
				$('#examMaxScoreHelper').text('');
				$('#examStartDateHelper').text('');
				$('#examEndDateHelper').text('종료일시를 선택하세요');
				$('#questionSectionHelper').text('');
				
	    		$('#examEndDate').focus();
			} else if($('#questionSectionHelper').val() == '') {
				$('#examTitleHelper').text('');
				$('examCountHelper').text('');
				$('#examMaxScoreHelper').text('');
				$('#examStartDateHelper').text('');
				$('#examEndDateHelper').text('');
				$('#questionSectionHelper').text('문제를 입력하세요');
			} else {
	    		$('#addExam').submit();
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
												<td>
												<input type="hidden" class="form-control"	name="memberId" value="${sessionLoginMember.memberId}">
												<input type="hidden" class="form-control"	name="lectureNo" value="${sessionLectureNo}">
												<input type="text" class="form-control"	name="examTitle" id="examTitle">
												<span id="examTitleHelper" class="helper"></span></td>
											</tr>
											<tr>
												<td>시험 문항 수 :</td>
												<td><input type="text" class="form-control"	name="examCount" id="examCount">
												<span id="examCountHelper" class="helper"></span></td>
											</tr>
											<tr>
												<td>만점 :</td>
												<td><input type="text" class="form-control"	name="examMaxScore" id="examMaxScore">
												<span id="examMaxScoreHelper" class="helper"></span></td>
											</tr>
											<tr>
												<td>시험시작일시 :</td>
												<td><input type="datetime-local" class="form-control" name="examStartDate" id="examStartDate">
												<span id="examStartDateHelper" class="helper"></span></td>
											</tr>
											<tr>
												<td>시험종료일시:</td>
												<td><input type="datetime-local" class="form-control" name="examEndDate" id="examEndDate">
												<span id="examEndDateHelper" class="helper"></span></td>
											</tr>
											<tr>
												<td id="questionSection"><span id="questionSectionHelper" class="helper"></span></td>
											</tr>
										</table>
											<button type="button" class="btn btn-outline-success btn-rounded" id="addExamMultiQuestion" name="addExamMultiQuestion">
												<i class="fas fa-check"></i>객관식 문제 추가
											</button>
											<button type="button" class="btn btn-outline-success btn-rounded" id="addExamShortQuestion" name="addExamShortQuestion">
												<i class="fas fa-check"></i>주관식 문제 추가
											</button>
											<button type="button" class="btn btn-outline-danger btn-rounded" id="deleteExamQuestion" name="deleteExamQuestion">
												<i class="fas fa-check"></i>문제 삭제
											</button>
										<hr>
										<div>
											<button type="submit" class="btn btn-success" id="addExam">
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