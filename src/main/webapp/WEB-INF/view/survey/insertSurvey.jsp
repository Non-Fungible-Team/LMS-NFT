<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
$(document).ready(function(){
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
		$('#surveyQuestionForm').empty();
		appendCount = 1;
		appendCountList = 0;
	});
	
	$('#insertQuestionList').click(function(){
		if($('#surveyTitle').val() == '') {
			$('#surveyTitleHelper').text('제목을 입력하세요');
			$('#surveyContentHelper').text('')
			$('#surveyNameHelper').text('')
			$('#deadlineHelper').text('');
			
    		$('#surveyTitle').focus();
		}else if($('#surveyContent').val() == '') {
			$('#surveyTitleHelper').text('');
			$('#surveyContentHelper').text('내용을 입력하세요')
			$('#startlineHelper').text('');
			$('#deadlineHelper').text('');
			
			$('#surveyContent').focus();
		}else if($('#surveyStartlineDate').val() == '') {
			$('#surveyTitleHelper').text('');
			$('#surveyContentHelper').text('')
			$('#startlineHelper').text('설문 시작일을 입력하세요');
			$('#deadlineHelper').text('');
			
			$('#surveyDeadlineDate').focus();
		}else if($('#surveyDeadlineDate').val() == '') {
			$('#surveyTitleHelper').text('');
			$('#surveyContentHelper').text('')
			$('#startlineHelper').text('');
			$('#deadlineHelper').text('설문 마감일을 입력하세요');
			
			$('#surveyDeadlineDate').focus();
		}else if(appendCount == 1){
			$('#surveyTitleHelper').text('');
			$('#surveyContentHelper').text('')
			$('#startlineHelper').text('');
			$('#deadlineHelper').text('');
			$('#qestionFormHelper').text('질문을 선택해주세요')
		}else {
			$('#insertSurvey').submit();
		}
		
	});
		
});
</script>
</head>
<body>
	<h1>설문조사</h1>
	
	<div>설문조사 항목 타입 선택</div>
		<button type="button" id="multipleSurvey" name="multipleSurvey">객관식 문제 추가</button>
		<button type="button" id="shortSurvey" name="shortSurvey">주관식 문제 추가</button>
		<button type="button" id="deleteTypeButton" name="deleteTypeButton">삭제</button>
	<div>&nbsp;</div>
	
	
	<form method="post" action='${pageContext.request.contextPath}/survey/insertSurvey' id="insertSurvey">
		<table>
			<tr>
				<td>설문조사 제목</td>
				<td><input type="text" name="surveyTitle" id="surveyTitle">
				<span id='surveyTitleHelper' class='helper'></span></td>
			</tr>
			<tr>
				<td>설문조사 내용</td>
				<td><input type="text" name="surveyContent" id="surveyContent">
				<span id='surveyContentHelper' class='helper'></span></td>
			</tr>
			<tr><td>설문 시작일</td>
				<td>
				<input type="date" name="surveyStartlineDate" id="surveyStartlineDate"> 
				<span id='startlineHelper' class='helper'></span></td>
			</tr>
			<tr>
				<td>설문 마감일</td>
				<td>
				<input type="date" name="surveyDeadlineDate" id="surveyDeadlineDate">
				<span id='deadlineHelper' class='helper'></span></td>
			</tr>
			<tr>
			<td>
			<td id="surveyQuestionForm">
			<span id='qestionFormHelper' class='helper'></span></td>
			
			
			
		</table>
		<button type='button' id='insertQuestionList' name='insertQuestionList'>생성</button>
	</form>
		
		
		
</body>
</html>