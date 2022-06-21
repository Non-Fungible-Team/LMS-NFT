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
	var maxAppend1 = 1;
	var maxAppend2 = 1;
	
	$('#multipleSurvey').click(function(){
		if (maxAppend1 >= 11) return; 
			//jquery api 사용
			$('#multipleSurveyForm').append("<tr><td>객관식 질문 리스트</td></tr><tr><td>질문 제목</td><td><input type='text' id='multipleSurveyQuestionName'  name='multiplesurveyQuestionName' ><span id='multipleNameHelper' class='helper'></span></td><tr><td>질문 내용</td><td><input type='text' name='multipleSurveyQuestionContent' id='multipleSurveyQuestionContent'><span id='multipleContentHelper' class='helper'></span></td>");
			maxAppend1++;
	});
	
	$('#shortSurvey').click(function(){
		if (maxAppend2 >= 11) return;
			//jquery api 사용
			$('#shortSurveyForm').append("<tr><td>주관식 질문 리스트</td></tr><tr><td>질문 제목</td><td><input type='text' id='shortSurveyQuestionName'  name='shortsurveyQuestionName' ><span id='shortNameHelper' class='helper'></span></td><tr><td>질문 내용</td><td><input type='text' name='shortSurveyQuestionContent' id='shortSurveyQuestionContent'><span id='shortContentHelper' class='helper'></span></td>");
			maxAppend2++;
	});
		
	
	
	$('#deleteTypeButton').click(function(){
		$('#multipleSurveyForm').empty();
		$('#shortSurveyForm').empty();
		maxAppend1 = 0;
		maxAppend2 = 0;
	});
	
	$('#multipleSurvey').click(function(){
		if($('#multipleSurveyQuestionName').val() == '') {
			$('#multipleNameHelper').text('제목을 입력해주세요');
    		$('#multipleSurveyQuestionName').focus();
		}else if($('#multipleSurveyQuestionContent').val() == '') {
			$('#multipleNameHelper').text('');
			$('#multipleContentHelper').text('내용을 입력해주세요');
    		$('#multipleSurveyQuestionContent').focus();
		}else {
				
		}
		
	});
		
	$('#shortSurvey').click(function(){	
		if($('#shortSurveyQuestionName').val() == '') {
			$('#shortNameHelper').text('제목을 입력해주세요');
    		$('#shortSurveyQuestionName').focus();
		}else if($('#shortSurveyQuestioContent').val() == ''){
			$('#shortNameHelper').text('');
			$('#shortContentHelper').text('내용을 입력해주세요');
    		$('#shortSurveyQuestionContent').focus();
		}else {
			
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
	<form action='${pageContext.request.contextPath}/survey/insertSurveyQuestion'>
		<table>
			<tr>
			<td id="multipleSurveyForm">
			</td>
			
			<tr>
				<td>객관식 질문 리스트</td>
			</tr>
			
			<tr>
				<td>질문 항목</td>
				<td><select id="selectQuestionList">
						<option>항목선택</option>
						<c:forEach var="ql" items="SurveyQuestionList">
							<option value="${ql.surveyQuestionListNo}">${ql.surveyQuestionListName}</option>
						</c:forEach>
					</select>
					<input type="text" class="readonry" value="객관식">
					<span id='multipleNameHelper' class='helper'></span></td>
			</tr>
			<tr>
				<td>질문 내용</td>
				<td><input type='text' name='multipleSurveyQuestionContent'
					id='multipleSurveyQuestionContent'>
					<span id='multipleContentHelper' class='helper'></span></td><td id="shortSurveyForm">
			</td>
			
			
		</table>
		<button type='button' id='insertQuestionList' name='insertQuestionList'>생성</button>
	</form>
		
		
		
</body>
</html>