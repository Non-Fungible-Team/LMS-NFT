<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LectureScheduleList</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>

<body>
	<h1>LectureScheduleList</h1>
    <table>
        <thead>
            <tr>
                <th>lecture_schedule_date</th>
                <th>lecture_no</th>
                <th>lecture_schedule_start_date</th>
                <th>lecture_schedule_end_date</th>
                <th>lecture_schedule_create_date</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="l" items="${lectureScheduleList}">
                <tr>
                   <td>${l.lectureScheduleDate}</td>
                   <td>${l.lectureNo}</td>
                   <td>${l.lectureScheduleStartDate}</td>
                   <td>${l.lectureScheduleEndDate}</td>
                   <td>${l.lectureScheduleCreateDate}</td>
                   <td><a href="${pageContext.request.contextPath}/removeLectureSchedule?lectureScheduleDate=${l.lectureScheduleDate}&lectureNo=${l.lectureNo}">삭제</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>