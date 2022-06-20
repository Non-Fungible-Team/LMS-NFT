<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문제등록</title>
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
	    		<td>만점: </td>
	    		<td><input type="text" name="examMaxScore" id="examMaxScore"></td>
	    	</tr>
	    	<tr>
	    		<td>시험 내용 : </td>
	    		<td><input type="text" name="examContent" id="examContent"></td>
	    	</tr>
	    </table>
	    <div>
			<button type="button" class="btn btn-default" id="addExam">시험등록</button>
		</div>
    </form>

</body>
</html>