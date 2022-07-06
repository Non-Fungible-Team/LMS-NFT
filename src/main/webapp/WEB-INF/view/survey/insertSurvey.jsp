<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 반응형 웹 -->
<!-- 반응형 웹 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<!-- title icon -->
<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/static/assets/images/favicon.png">
<title>Lecture List</title>
<link href="${pageContext.request.contextPath}/static/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/static/dist/css/style.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$("#navAside").load('${pageContext.request.contextPath}/include/navAside.jsp');
	
	var appendCount = 1;
	var appendCountList = 0;
	
	
	$('#multipleSurvey').click(function(){
		if (appendCount >= 11) return; 
			//jquery api 사용
			
			appendCountList = appendCount-1;
			$('#surveyQuestionForm').append("<tr><td colspan='2'>질문 "+appendCount+"번<input type='hidden' name='surveyQuestionList["+appendCountList+"].surveyQuestionNo' value='"+appendCount+"'></td></tr><tr><td>질문 항목"+appendCount+"</td><td><select name='surveyQuestionList["+appendCountList+"].surveyQuestionListNo'><option>항목선택</option><c:forEach var='ql' items='${QuestionList}'><option value='${ql.surveyQuestionListNo}'>${ql.surveyQuestionListName}</option></c:forEach></select><input type='text' name='surveyQuestionList["+appendCountList+"].surveyQuestionType' value='객관식' readonly></td></tr><tr><td>"+appendCount+"번 질문 내용입력</td><td><input type='text' name='surveyQuestionList["+appendCountList+"].surveyQuestionContent'></td></tr>");
			appendCount++;
	});
	
	$('#shortSurvey').click(function(){
		if (appendCount >= 11) return;
			//jquery api 사용
			appendCountList = appendCount-1;
			$('#surveyQuestionForm').append("<tr><td>질문 "+appendCount+"번<input type='hidden' name='surveyQuestionList["+appendCountList+"].surveyQuestionNo' value='"+appendCount+"'></td></tr><tr><td>질문 항목"+appendCount+"</td><td><select name='surveyQuestionList["+appendCountList+"].surveyQuestionListNo'><option>항목선택</option><c:forEach var='ql' items='${QuestionList}'><option value='${ql.surveyQuestionListNo}'>${ql.surveyQuestionListName}</option></c:forEach></select><input type='text' name='surveyQuestionList["+appendCountList+"].surveyQuestionType' value='주관식' readonly></td></tr><tr><td>"+appendCount+"번 질문 내용입력</td><td><input type='text' name='surveyQuestionList["+appendCountList+"].surveyQuestionContent'></td></tr>");
			appendCount++;
	});
	
	
	
	$('#deleteTypeButton').click(function(){
		$('#surveyQuestionForm table:last').remove();
		appendCount--;
		appendCountList--;
		if(appendCount <= 0) {
			appendCount = 1;
			appendCountList = 0;
		}
	});
	
	$('#insertQuestionList').click(function(){
		if($('#surveyTitle').val() == '') {
			$('#surveyTitleHelper').text('제목을 입력하세요');
			$('lectureHelper').text('');
			$('#surveyContentHelper').text('');
			$('#surveyNameHelper').text('');
			$('#deadlineHelper').text('');
			
    		$('#surveyTitle').focus();
		}else if($('#lectureNo').val() == '') {
			$('#surveyTitleHelper').text('');
			$('lectureHelper').text('과목을 선택해주세요');
			$('#surveyContentHelper').text('');
			$('#startlineHelper').text('');
			$('#deadlineHelper').text('');
			
			$('#lectureHelper').focus();
		}else if($('#surveyContent').val() == '') {
			$('#surveyTitleHelper').text('');
			$('lectureHelper').text('');
			$('#surveyContentHelper').text('내용을 입력하세요');
			$('#startlineHelper').text('');
			$('#deadlineHelper').text('');
			
			$('#surveyContent').focus();
		}else if($('#surveyStartlineDate').val() == '') {
			$('#surveyTitleHelper').text('');
			$('lectureHelper').text('');
			$('#surveyContentHelper').text('');
			$('#startlineHelper').text('설문 시작일을 입력하세요');
			$('#deadlineHelper').text('');
			
			$('#surveyDeadlineDate').focus();
		}else if($('#surveyDeadlineDate').val() == '') {
			$('#surveyTitleHelper').text('');
			$('lectureHelper').text('');
			$('#surveyContentHelper').text('');
			$('#startlineHelper').text('');
			$('#deadlineHelper').text('설문 마감일을 입력하세요');
			
			$('#surveyDeadlineDate').focus();
		}else if(appendCount == 1){
			$('#surveyTitleHelper').text('');
			$('lectureHelper').text('');
			$('#surveyContentHelper').text('');
			$('#startlineHelper').text('');
			$('#deadlineHelper').text('');
			$('#qestionFormHelper').text('질문을 선택해주세요');
		}else {
			$('#insertSurvey').submit();
		}
		
	});
		
});
</script>
<style>
	    .helper {
	    	color : #FF0000;
	    }
	</style>
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
				<div class="container p-5 my-5 border">
					<div class="row">
						<div class="col-lg-12 col-md-12">
							<div class="card">
								<div class="mt-2" style="height:auto; width:auto;">
									<div class="card-body">
	
										<h1 class="card-title">설문조사 생성</h1>
	
										<div>설문조사 항목 타입 선택</div>
										<button type="button" id="multipleSurvey" name="multipleSurvey">객관식
											문제 추가</button>
										<button type="button" id="shortSurvey" name="shortSurvey">주관식
											문제 추가</button>
										<button type="button" id="deleteTypeButton"
											name="deleteTypeButton">삭제</button>
										<div>&nbsp;</div>
	
	
										<form method="post"
											action="${pageContext.request.contextPath}/manager/survey/insertSurvey"
											id="insertSurvey">
											<table>
												<tr>
													<td><input type="hidden" name="memberId" value="${loginMember.memberId}"></td>
												</tr>
												<tr>
													<td>설문조사 제목</td>
													<td><input type="text" name="surveyTitle" id="surveyTitle" class="form-control">
														<span id="surveyTitleHelper" class="helper"></span></td>
												</tr>
												<tr>
													<td>강의명</td>
													<td><select name="lectureNo">
													<option>-----강의 선택-----</option>
													<c:forEach var="lT" items="${lectureNoNameList}">
														<option value="${lT.lectureNo}">${lT.lectureName}</option>
													</c:forEach>
													</select>
													<span id="lectureHelper" class="helper"></span></td>
												</tr>
												<tr>
													<td>설문조사 내용</td>
													<td><input type="text" name="surveyContent" id="surveyContent" class="form-control"> 
													<span id="surveyContentHelper" class="helper"></span></td>
												</tr>
												<tr>
													<td>설문 시작일</td>
													<td><input type="date" name="surveyStartlineDate" id="surveyStartlineDate" class="form-control"> 
													<span id="startlineHelper" class="helper"></span></td>
												</tr>
												<tr>
													<td>설문 마감일</td>
													<td><input type="date" name="surveyDeadlineDate" id="surveyDeadlineDate" class="form-control">
													<span id="deadlineHelper" class="helper"></span></td>
												</tr>
											</table>
											<table>
												<tr>
													<td id="surveyQuestionForm">
													<span id="qestionFormHelper" class="helper"></span></td>
												</tr>
											</table>
											<button type="button" id="insertQuestionList" name="insertQuestionList">생성</button>
										</form>
									</div>
								</div>
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