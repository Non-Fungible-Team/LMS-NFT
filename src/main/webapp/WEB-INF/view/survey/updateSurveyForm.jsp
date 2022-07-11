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
<link href="${pageContext.request.contextPath}/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/dist/css/style.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$("#navAside").load('${pageContext.request.contextPath}/include/navAside.jsp');
	var appendCount = 1;
	var appendCountList = 0;
	console.log(${surveyNo});
	
	var url = '${pageContext.request.contextPath}/rest/manager/survey/updateSurveyForm?surveyNo=${surveyNo}';
	
	update();
	
	function update(){
		$.ajax({
			type:'get'
			,url: url
			,success: function(data){
				console.log(data);
				var surveyQuestion = [data.surveyQuestion];
				console.log(surveyQuestion[0].length);
				var l = surveyQuestion[0].length+1;
				console.log(surveyQuestion[0].length+1);
				appendCount = surveyQuestion[0].length+1;
				console.log(appendCount);
					
				
				
				$('#multipleSurvey').click(function(){
					
					if (appendCount >= 11) return; 
						//jquery api 사용
						
						appendCountList = appendCount-1;
						console.log(appendCountList);
						$('#surveyQuestionForm').append("<form id='addQuestion"+appendCount+"'><table><tr><td colspan='2'>질문 "+appendCount+"번<input type='hidden' name='surveyQuestionNo' id='surveyQuestionNo"+appendCount+"' value='"+appendCount+"'></td><td><input type='hidden' name='surveyNo' value='${surveyNo}'> </td></tr><tr><td>"+appendCount+"번 질문 항목</td><td><select name='surveyQuestionListNo' id='surveyQuestionListNo"+appendCount+"'><option value='0'>항목선택</option><c:forEach var='ql' items='${questionList}'><option value='${ql.surveyQuestionListNo}'>${ql.surveyQuestionListName}</option></c:forEach></select><input type='text' name='surveyQuestionType' value='객관식' readonly></td></tr><tr><td>"+appendCount+"번 질문 내용입력</td><td><input type='text' name='surveyQuestionContent' id='surveyQuestionContent"+appendCount+"'></td><td><button type='button' data-value='"+appendCount+"' class='addSurvayQuestion'>질문 추가</button></td></tr></table></form>");
						appendCount++;
						console.log(appendCount);
						addSurvayQuestionButton();
				});
				
				$('#shortSurvey').click(function(){
					
					if (appendCount >= 11) return;
						//jquery api 사용
						
						appendCountList = appendCount-1;
						console.log(appendCountList);
						$('#surveyQuestionForm').append("<form id='addQuestion"+appendCount+"'><table><tr><td>질문"+appendCount+"번<input type='hidden' name='surveyQuestionNo' id='surveyQuestionNo"+appendCount+"'value='"+appendCount+"'></td><td><input type='hidden' name='surveyNo' value='${surveyNo}'></td></tr><tr><td>"+appendCount+"번 질문 항목</td><td><select name='surveyQuestionListNo' id='surveyQuestionListNo"+appendCount+"'><option value='0'>항목선택</option><c:forEach var='ql' items='${questionList}'><option value='${ql.surveyQuestionListNo}'>${ql.surveyQuestionListName}</option></c:forEach></select><input type='text' name='surveyQuestionType' value='주관식' readonly></td></tr><tr><td>"+appendCount+"번 질문 내용입력</td><td><input type='text' name='surveyQuestionContent' id='surveyQuestionContent"+appendCount+"'></td><td><button type='button' data-value='"+appendCount+"' class='addSurvayQuestion'>질문 추가</button></td></tr></table></form>");
						appendCount++;
						console.log(appendCount);
						addSurvayQuestionButton();
				});
				
				$('#deleteTypeButton').click(function(){
					
					if(appendCount < surveyQuestion[0].length+2) {
						
						appendCountList = appendCount-1;
						var r = appendCount-2;
						console.log("r="+r);
						var form1 = $("#updateSurveyQuestion"+r).serialize();
						console.log(form1);
						var url2 = '${pageContext.request.contextPath}/rest/manager/survey/updateSurveyQuestion';
						$.ajax({
							type: "post"
							,url: url2
							,data: form1
							,dataType: 'json'
							,success: function(a) {
								console.log(a);
							}
						});
						$('#surveyQuestionForm2 table:last').remove();
						appendCount--;
						appendCountList--;
						console.log(appendCount);
						console.log(appendCountList);
						
						if(appendCount <= 0) {
							appendCount = 1;
							appendCountList = 0;
						}
					} else if(appendCount > surveyQuestion[0].length+1) {
						appendCountList = appendCount-1;
						$('#surveyQuestionForm table:last').remove();
						appendCount--;
						appendCountList--;
						console.log(appendCount);
						console.log(appendCountList);
					}
				});
				function addSurvayQuestionButton(){
					$('.addSurvayQuestion').click(function(){
						var v = $(this).data('value');
						console.log("v= "+v);
						console.log("l= "+l);
						var url3 = '${pageContext.request.contextPath}/rest/manager/survey/addSurveyQuestion';
						var form2 = $("#addQuestion"+v).serialize();
						
						if($('#surveyQuestionListNo'+l).val()==0){
							$('#qestionFormHelper').text('질문 항목을 선택 해 주세요');
							
						}else if($('#surveyQuestionContent'+l).val()==''){
							$('#qestionFormHelper').text('질문 내용을 입력 해 주세요');
							
						}else{
							if(v==l){
								$('#qestionFormHelper').text('');
								$.ajax({
									type: "post"
									,url: url3
									,data: form2
									,dataType: 'json'
									,success: function(b){
										console.log("생성 성공");
										console.log(b);
										
										$('#surveyQuestionForm table:first').remove();
										
										var url4 = '${pageContext.request.contextPath}/rest/manager/survey/selectAddSurveyQuestion?surveyNo=${surveyNo}&surveyQuestionNo='+v;
										console.log(form2);
										
										$.ajax({
											type: "get"
											,url: url4
											,success: function(c){
												console.log(c);
												var content = [c.surveyQuestion[0].surveyQuestionContent];
												console.log("질문내용"+content);
												var type = [c.surveyQuestion[0].surveyQuestionType];
												console.log("질문분류"+type);
												var questionNo = [c.surveyQuestion[0].surveyQuestionNo];
												console.log("질문번호"+questionNo);
												var index = questionNo-1;
												console.log("인덱스번호"+index);
												
												$('#surveyQuestionForm2').append("<form method='post'action='${pageContext.request.contextPath}/rest/manager/survey/updateSurveyQuestion?surveyQuestionNo="+questionNo+"' id='updateSurveyQuestion"+index+"'><table><tr><td colspan='2'>질문 "+questionNo+"번 <input type='hidden' name='surveyQuestionNo' value='"+questionNo+"'></td></tr><tr><td>질문 항목</td><td><select name='surveyQuestionListNo'><option>항목선택</option><c:forEach var='ql' items='${questionList}'><option value='${ql.surveyQuestionListNo}'>${ql.surveyQuestionListName}</option></c:forEach></select><input type='text' name='surveyQuestionType' value='"+type+"' readonly></td></tr><tr><td>"+questionNo+"번 질문 내용입력</td><td><input type='text' name='surveyQuestionContent' value='"+content+"'></td></tr><tr><td><input type='hidden' name='surveyNo' value='${surveyNo}'></td></tr></table></form>");
												
												l++;
												
											}
										});
									}
								});
							}else {
								$('#qestionFormHelper').text(v+'번 문제를 추가할 수 없습니다 '+l+'번 문제부터 추가해주세요');
							}
						}
						
						
						
					});
				};
				
			}
		});
	}
	
	$('#updateQuestionList').click(function(){
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
			$('#updateSurvey').submit();
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
								<div class="card-body">
									<div class="mt-2" style="height:auto; width:auto;">
										<h1 class="card-title">설문조사 업데이트</h1>
	
										<div>설문조사 항목 타입 선택</div>
										<button class="btn btn-info" id="multipleSurvey" name="multipleSurvey">객관식
											문제 추가</button>
										<button class="btn btn-info" id="shortSurvey" name="shortSurvey">주관식
											문제 추가</button>
										<button class="btn btn-info" id="deleteTypeButton"
											name="deleteTypeButton">삭제</button>
										<div>&nbsp;</div>
										
										<form method="post" id="updateSurvey" action="${pageContext.request.contextPath}/manager/survey/updateSurvey">
											<table>
												<tr>
													<td><input type="hidden" name="memberId" value="${loginMember.memberId}"></td>
													<td><input type="hidden" name="surveyNo" value="${surveyNo}">
												</tr>
												<tr>
													<td>설문조사 제목</td>
													<td><input type="text" name="surveyTitle" id="surveyTitle" class="form-control" value="${survey.surveyTitle}">
														<span id="surveyTitleHelper" class="helper"></span></td>
												</tr>
												<tr>
													<td>강의명</td>
													<td><select name="lectureNo">
													<option>-----강의 선택-----</option>
													
													<c:forEach var="lT" items="${lectureNoNameList}">
													<c:if test="${lT.lectureNo==survey.lectureNo}">
														<option value="${lT.lectureNo}" selected>${lT.lectureName}</option>
													</c:if>
													<c:if test="${lT.lectureNo!=survey.lectureNo}">
														<option value="${lT.lectureNo}">${lT.lectureName}</option>
													</c:if>
													</c:forEach>
													</select>
													<span id="lectureHelper" class="helper"></span></td>
												</tr>
												<tr>
													<td>설문조사 내용</td>
													<td><input type="text" name="surveyContent" id="surveyContent" class="form-control" value="${survey.surveyContent}"> 
													<span id="surveyContentHelper" class="helper"></span></td>
												</tr>
												<tr>
													<td>설문 시작일</td>
													<td><input type="date" name="surveyStartlineDate" id="surveyStartlineDate" class="form-control" value="${survey.surveyStartlineDate}"> 
													<span id="startlineHelper" class="helper"></span></td>
												</tr>
												<tr>
													<td>설문 마감일</td>
													<td><input type="date" name="surveyDeadlineDate" id="surveyDeadlineDate" class="form-control" value="${survey.surveyDeadlineDate}">
													<span id="deadlineHelper" class="helper"></span></td>
												</tr>
											</table>
											<button type="button" id="updateQuestionList" name="updateQuestionList">수정</button>
										</form>
										<div id="surveyQuestionForm2">
										<c:forEach var='sq' items='${surveyQuestion}' varStatus='status'>
											<form method='post'
												action='${pageContext.request.contextPath}/rest/manager/survey/updateSurveyQuestion?surveyQuestionNo=${sq.surveyQuestionNo}'
												id='updateSurveyQuestion${status.index}'>
												<table>
													<tr>
														<td colspan='2'>질문 ${sq.surveyQuestionNo}번 <input
															type='hidden'
															name='surveyQuestionNo'
															value='${sq.surveyQuestionNo}'>
														</td>
													</tr>
													<tr>
														<td>질문 항목</td>
														<td><select
															name='surveyQuestionListNo'>
																<option>항목선택</option>
																<c:forEach var='ql' items='${questionList}'>
																	<option value='${ql.surveyQuestionListNo}'>${ql.surveyQuestionListName}</option>
																</c:forEach>
														</select> <input type='text'
															name='surveyQuestionType'
															value='${sq.surveyQuestionType}' readonly></td>
													</tr>
													<tr>
														<td>${sq.surveyQuestionNo}번 질문 내용입력</td>
														<td><input type='text'
															name='surveyQuestionContent' value='${sq.surveyQuestionContent}'></td>
													</tr>
													<tr>
														<td>
															<input type='hidden' name='surveyNo' value='${sq.surveyNo}'>
														</td>
													</tr>
												</table>
											</form>
										</c:forEach>
										</div>
										<div id='surveyQuestionForm'><span id='qestionFormHelper' class='helper'></span></div>
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
</body>
</html>