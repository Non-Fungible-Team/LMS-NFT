<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>subjectList</title>
</head>
<body>
	<h1>subjectList</h1>
    <table>
        <thead>
            <tr>
                <th>번호</th>
                <th>과목 이름</th>
                <th>내용</th>
                <th>생성일</th>
                <th>수정일</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="s" items="${subjectList}">
                <tr>
                   <td>${s.subjectNo}</td>
                   <td>${s.subjectName}</td>
                   <td>${s.subjectContent}</td>
                   <td>${s.subjectCreateDate}</td>
                   <td>${s.subjectUpdateDate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>