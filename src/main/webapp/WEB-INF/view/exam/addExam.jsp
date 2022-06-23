<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문제등록</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
$(document).ready(function(){
	var appendCountQuestion = 1;
	var appendCountExample = 1;
	
	$('#addExamQuestion').click(function(){
		if (appendCountQuestion >= 20) return; 
		$('#questionSection').append("<table border='1'><tr><td>문제 번호"+appendCountQuestion+"번<input type='hidden' name='examQuestionNo' id='examQuestionNo'></td></tr><tr><td>시험 문제 내용</td><td><input type='text' name='examContent'></td></tr><tr><td>시험 문제 답안</td><td><input type='text' name='examCorrectAnswer'></td></tr><tr><td>배점</td><td><input type='text' name='examPoint'></td></tr><tr><td>시험 문제 타입</td><td><input type='radio' name='examType' id='examType' value='객관식'>객관식<input type='radio' name='examType' id='examType' value='주관식'>주관식</td></tr></table>");
		appendCountQuestion++;
		});
		
	$('#addExample').click(function(){
		$('#exampleSection').appendTo("<table border='1'><tr><td>보기 번호"+appendCountExample+"번<input type='hidden' name='exampleNo' id='exampleNo'></td></tr><tr><td>보기 내용</td><td><input type='text' name='exampleContent' id='exampleContent''></td></tr>");
		appendCountExample++;
	});
	
	$('#deleteExamQuestion').click(function(){
		$('#questionSection table:last').remove();
		appendCountQuestion--;
		if(appendCountQuestion<0){
			appendCountQuestion=1;
		}
	});
});
	
</script>
</head>
<body>
  <h1>시험등록</h1>
    <form method="post" id="addExam" action="${pageContext.request.contextPath}/exam/addExam">
	    <table>
<!-- 	    시험등록 -->
	    	<tr>
	    		<td>시험 이름 : </td>
	    		<td><input type="text" name="examTitle" id="examTitle"></td>
	    	</tr>
	    	<tr>
	    		<td>시험 문항 수 : </td>
	    		<td><input type="text" name="examCount" id="examCount"></td>
	    	</tr>
	    	<tr>
	    		<td>만점 : </td>
	    		<td><input type="text" name="examMaxScore" id="examMaxScore"></td>
	    	</tr>
	    	<tr>
	    		<td>시험시작일시 : </td>
	    		<td><input type="datetime-local" name="examStartDate" id="examStartDate"></td>
	    	</tr>
	    	<tr>
	    		<td>시험종료일시: </td>
	    		<td><input type="datetime-local" name="examEndDate" id="examEndDate"></td>
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
			<td><button type="button" id="addExamQuestion" name="addExamQuestion">문제 추가</button></td>
			<td><button type="button" id="deleteExamQuestion" name="deleteExamQuestion">문제 삭제</button></td>
			</tr>
			<tr>
			<td id="exampleSection"></td>
			</tr>
			<tr>
			<td><button type="button" id="addExamExample" name="addExample">보기 추가</button></td>
			<td><button type="button" id="deleteExample" name="deleteExample">보기 삭제</button></td>
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
			<button type="submit" class="btn btn-default" id="addExam">시험등록</button>
		</div>
    </form>

</body>
</html>