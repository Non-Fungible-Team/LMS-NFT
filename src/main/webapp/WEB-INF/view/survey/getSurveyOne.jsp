<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
$(document).ready(function(){
	var appendCount = 1;
	var appendCountList = 0;
	
	if($('#questionType').text('') == '객관식') {
		if (appendCount >= 11) return;
		appendCountList = appendCount-1;
		$('#questionAnswerForm').append("<tr><td><input type='text' name='shortSurveyAnswer["appendCountList"].surveyShortAnswerContent'></td></tr>");
		appendCount++;
		
	} else if ($('#questionType').text('') == '주관식') {
		if (appendCount >= 11) return;
		appendCountList = appendCount-1;
		$('#questionAnswerForm').append("<tr><td><input type='radio' name='multiSurveyAnswer["appendCountList"].surveyMultipleAnswerContent' value=1>1<input type='radio' name='multiSurveyAnswer["appendCountList"].surveyMultipleAnswerContent' value=2>2<input type='radio' name='multiSurveyAnswer["appendCountList"].surveyMultipleAnswerContent' value=3>3<input type='radio' name='multiSurveyAnswer["appendCountList"].surveyMultipleAnswerContent' value=4>4<input type='radio' name='multiSurveyAnswer["appendCountList"].surveyMultipleAnswerContent' value=5>5</td></tr>");
		appendCount++;
	}
	
	$('#surveySubmit').click(function(){
		
		if(){
			
		} else {
			
		}
		
	});
});

</script>
<body>
	<h1>설문조사</h1>
	<a href="${pageContext.request.contextPath}/survey/getSurveyListByPage" class="btn">이전으로</a>
		<table>
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
			</table>
	<h2>답변등록</h2>
	<form method="post" action="${pageContext.request.contextPath}/survey/insertSurveyAnswer" id="surveyAnswerForm">
		<table>
			<c:forEach var="sqa" items="${surveyQuestionList}">
				<tr>
					<td>${sqa.surveyQuestionListName}</td>
				</tr>
				<tr>
					<td id="questionType">${sqa.surveyQuestionType}</td>
					<td>${sqa.surveyQuestionContent}</td>
				</tr> 
				<tr>
					<td>답변</td>
					<td id="questionAnswerForm"></td>
				<tr>
			</c:forEach>
		</table>
		<button type="button" id="surveySubmit">설문조사 제출</button>
	</form>
</body>
</html>