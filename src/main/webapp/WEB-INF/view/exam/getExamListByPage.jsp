<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시험 리스트</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<h1>시험 리스트</h1>
    <table>
        <thead>
            <tr>
                <th>시험 번호</th>
                <th>시험 제목</th>
                <th>문항수</th>
                <th>만점</th>
                <th>시험시작일시</th>
                <th>시험종료일시</th>
                <th>시험</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="e" items="${examList}">
                <tr>
                   <td>${e.examNo}</td>
                   <td>${e.examTitle}</td>
                   <td>${e.examCount}</td>
                   <td>${e.examMaxScore}</td>
                   <td>${e.examStartDate}</td>
                   <td>${e.examEndDate}</td>
                   <td><a href="${pageContext.request.contextPath}/getExamOne?examNo=${e.examNo}">문제보기</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
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