<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시험 리스트</title>
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
                   <td><a href="${pageContext.request.contextPath}/exam/getExamOne?examNo=${e.examNo}">문제보기</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <form>
			<button type="button" class="btn btn-default" id="addExam">
			<a href="${pageContext.request.contextPath}/exam/addExam">시험등록</a></button>
		</div>
    </form>
</body>
</html>