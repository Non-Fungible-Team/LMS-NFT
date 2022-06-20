<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LectureScheduleList</title>
</head>
<body>
	<h1>LectureScheduleList</h1>
    <table>
        <thead>
            <tr>
                <th>날짜</th>
                <th>수업 시작 시간</th>
                <th>수업 종료 시간</th>
                <th>시간표 작성 날짜</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="ls" items="${lectureScheduleList}">
                <tr>
                   <td>${ls.lectureScheduleDate}</td>
                   <td>${ls.lectureNo}</td>
                   <td>${ls.lectureScheduleStartDate}</td>
                   <td>${ls.lectureScheduleEndDate}</td>
                   <td><a href="${pageContext.request.contextPath}/lecture/remove#######One?#####No=${ls}">삭제</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>