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
	var appendCountMultiple = 1;
	var appendCountShort = 1;
	
	
	$('#multipleSurvey').click(function(){
		if (appendCountMultiple >= 11) return; 
			//jquery api 사용
			
			
			$('#multipleSurveyForm').append("<tr><td>객관식 질문 "+appendCountMultiple+"번</td></tr><tr><td>질문 항목"+appendCountMultiple+"</td><td><select id='surveyQuestionListNo'><option>항목선택</option><c:forEach var='ql' items='${QuestionList}'><option value='${ql.surveyQuestionListNo}'>${ql.surveyQuestionListName}</option></c:forEach></select><input type='text' id='surveyQuestionType' value='객관식' readonly></td></tr>");
			appendCountMultiple++;
	});
	
	$('#shortSurvey').click(function(){
		if (appendCountShort >= 11) return;
			//jquery api 사용
			$('#shortSurveyForm').append("<tr><td>주관식 질문 "+appendCountShort+"번</td></tr><tr><td>질문 항목"+appendCountShort+"</td><td><select id='surveyQuestionListNo'><option>항목선택</option><c:forEach var='ql' items='${QuestionList}'><option value='${ql.surveyQuestionListNo}'>${ql.surveyQuestionListName}</option></c:forEach></select><input type='text' id='surveyQuestionType' value='주관식' readonly></td></tr>");
			appendCountShort++;
	});
	
	
	
	
	$('#deleteTypeButton').click(function(){
		$('#multipleSurveyForm').empty();
		$('#shortSurveyForm').empty();
		appendCountMultiple = 1;
		appendCountShort = 1;
	});
	
	$('#insertQuestionList').click(function(){
		if($('#surveyName').val() == '') {
			$('#surveyNameHelper').text('제목을 입력하세요');
			$('#surveyContentHelper').text('')
			$('#surveyNameHelper').text('')
			$('#deadlineHelper').text('');
			
    		$('#surveyName').focus();
		}else if($('#surveyContent').val() == '') {
			$('#surveyNameHelper').text('');
			$('#surveyContentHelper').text('내용을 입력하세요')
			$('#startlineHelper').text('');
			$('#deadlineHelper').text('');
			
			$('#surveyContent').focus();
		}else if($('#startline').val() == '') {
			$('#surveyNameHelper').text('');
			$('#surveyContentHelper').text('')
			$('#startlineHelper').text('설문 시작일을 입력하세요');
			$('#deadlineHelper').text('');
			
			$('#startline').focus();
		}else if($('#deadline').val() == '') {
			$('#surveyNameHelper').text('');
			$('#surveyContentHelper').text('')
			$('#startline').text('');
			$('#deadlineHelper').text('설문 마감일을 입력하세요');
			
			$('#deadlineHelper').focus();
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
				<td><input type="text" name="surveyTitle">
				<span id='surveyNameHelper' class='helper'></span></td>
			</tr>
			<tr>
				<td>설문조사 내용</td>
				<td><input type="text" name="surveyContent">
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
			<td id="multipleSurveyForm"></td>
			<td id="shortSurveyForm"></td>
			<tr>
				
		</table>
		<button type='button' id='insertQuestionList' name='insertQuestionList'>생성</button>
	</form>
		
		
		
</body>
</html>