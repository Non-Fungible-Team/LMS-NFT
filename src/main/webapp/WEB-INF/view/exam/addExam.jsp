<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문제등록</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
  <h1>문제등록</h1>
    <form method="post" id="addForm" action="${pageContext.request.contextPath}/addExamOne">
	    <table>
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
	    		<td><input type="text" name="examStartDate" id="examStartDate"></td>
	    	</tr>
	    	<tr>
	    		<td>시험종료일시: </td>
	    		<td><input type="text" name="examEndDate" id="examEndDate"></td>
	    	</tr>
	    	<tr>
	    		<td>시험 내용 : </td>
	    		<td><input type="text" name="examContent" id="examContent"></td>
	    	</tr>
	    	<tr>
	    		<td>시험 문제 번호 : </td>
	    		<td><input type="text" name="examQuestionNo" id="examQuestionNo"></td>
	    	</tr>
	    	<tr>
	    		<td>시험 문제 내용 : </td>
	    		<td><input type="text" name="examContent" id="examContent"></td>
	    	</tr>
	    	<tr>
	    		<td>시험 문제 답안 : </td>
	    		<td><input type="text" name="examCorrectAnswer" id="examCorrectAnswer"></td>
	    	</tr>
	    	<tr>
	    		<td>시험 문제 배점 : </td>
	    		<td><input type="text" name="examPoint" id="examPoint"></td>
	    	</tr>
	    	<tr>
	    		<td>시험 문제 타입 : </td>
	    		<td><input type="text" name="examType" id="examType"></td>
	    	</tr>
	    	<tr>
	    		<td>보기 번호 : </td>
	    		<td><input type="text" name="exampleNo" id="exampleNo"></td>
	    	</tr>
	    	<tr>
	    		<td>보기 내용 : </td>
	    		<td><input type="text" name="exampleContent" id="exampleContent"></td>
	    	</tr>
	    </table>
	    <div>
			<button type="button" class="btn btn-default" id="addExam">시험등록</button>
		</div>
    </form>

</body>
</html>