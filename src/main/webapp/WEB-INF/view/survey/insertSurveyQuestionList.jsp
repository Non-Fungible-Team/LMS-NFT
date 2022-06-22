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
	
	$('#insertQuestionList').click(function(){
		if($('#surveyQuestionListName').val() == '') {
			$('#NameHelper').text('제목을 입력해주세요');
    		$('#surveyQuestionListName').focus();
		}else if($('#surveyQuestionListContent').val() == '') {
			$('#NameHelper').text('');
			$('#ContentHelper').text('내용을 입력해주세요');
    		$('#surveyQuestionListContent').focus();
		}else {
			$('#ContentHelper').text('');
			$('#insertList').submit();
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
	<h1>설문조사 리스트 생성</h1>


	<form method="post" action="${pageContext.request.contextPath}/survey/insertSurveyList" id="insertList" name="insertList">
		<table>
			<tr>
				<td>질문 제목</td>
				<td><input type="text" id="surveyQuestionListName" name="surveyQuestionListName">
					&nbsp;<span id="NameHelper" class="helper"></span></td>
			<tr>
				<td>질문 내용</td>
				<td><input type="text" name="surveyQuestionListContent" id="surveyQuestionListContent">
					&nbsp;<span id="ContentHelper" class="helper"></span></td>
		</table>
		<div>
			<button type="button" id="insertQuestionList"
				name="insertQuestionList">생성</button>
		</div>
	</form>



</body>
</html>