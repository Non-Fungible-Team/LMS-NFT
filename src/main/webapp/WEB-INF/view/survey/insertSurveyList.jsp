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
	let flag = true;
	$('#selectTypeButton').click(function(){
		if($('#selectType').val() == 'multipleSurvey') {
			//jquery api 사용
			$('#surveyListForm').append("<form action='${pageContext.request.contextPath}/manager/insertSurveyList'><table><tr><td>질문 제목</td><td><input type='text' id='multipleSurveyQuestionListName'  name='multiplesurveyQuestionListName' ><span id='multipleNameHelper' class='helper'></span></td><tr><td>질문 내용</td><td><input type='text' name='multipleSurveyQuestionListContent' id='multipleSurveyQuestionListContent'><span id='multipleContentHelper' class='helper'></span></td></table><div><button type='button' id='insertQuestionList' name='insertQuestionList'>생성</button></div></form>");
			
		} else if($('#selectType').val() == 'shortSurvey') {
			//jquery api 사용
			$('#surveyListForm').append("<form action='${pageContext.request.contextPath}/manager/insertSurveyList'><table><tr><td>질문 제목</td><td><input type='text' id='shortSurveyQuestionListName'  name='shortsurveyQuestionListName' ><span id='shortNameHelper' class='helper'></span></td><tr><td>질문 내용</td><td><input type='text' name='shortSurveyQuestionListContent' id='shortSurveyQuestionListContent'><span id='shortContentHelper' class='helper'></span></td></table><div><button type='button' id='insertQuestionList' name='insertQuestionList'>생성</button></div></form>");
			
		} else {
			alert('선택 실패');
		}
	});
	
	$('#insertQuestionList').click(function(){
		if($('#selectType').val() == 'multipleSurvey') {
			if($('#multipleSurveyQuestionListName').val() == '') {
				$('#multipleNameHelper').text('제목을 입력해주세요');
    			$('#multipleSurveyQuestionListName').focus();
			}else if($('#multipleSurveyQuestionListContent').val() == '') {
				$('#multipleNameHelper').text('');
				$('#multipleContentHelper').text('내용을 입력해주세요');
    			$('#multipleSurveyQuestionListContent').focus();
			}else {
				
			}
		} else if($('#selectType').val() == 'shortSurvey') {
			if($('#shortSurveyQuestionListName').val() == '') {
				$('#shortNameHelper').text('제목을 입력해주세요');
    			$('#shortSurveyQuestionListName').focus();
			}else if($('#shortSurveyQuestionListContent').val() == ''){
				$('#shortNameHelper').text('');
				$('#shortContentHelper').text('내용을 입력해주세요');
    			$('#shortSurveyQuestionListContent').focus();
			}else {
				
			}
		} else {
			alert('잘못된 요청입니다');
		}
	});
});
</script>
</head>
<body>
	<h1>설문조사 리스트 생성</h1>
	
	<div>설문조사 항목 타입 선택</div>
	<div>
		<select id="selectType" name="selectType">
			<option>타입 선택</option>
			<option value="multipleSurvey">객관식</option>
		   	<option value="shortSurvey">주관식</option>
 	  	</select>
		<button type="button" id="selectTypeButton" name="selectTypeButton">선택</button></div>
	<div id="surveyListForm">
		</div>
	
		
		
		
</body>
</html>