<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>getHomeworkSubmitListByPage</h1>
	<table>
		<thead>
			<tr>
				<th>homeworkSubmitNo</th>
				<th>homeworkNo</th>
				<th>lectureNo</th>
				<th>memberId</th>
				<th>homeworkSubmitTitle</th>
<!-- 				<th>homeworkSubmitFileName</th> -->
				<th>homeworkSubmitCreateDate</th>
				<th>homeworkSubmitUpdateDate</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="hs" items="${homeworkSubmitList }">
				<tr>
					<td>${hs.homeworkSubmitNo }</td>
					<td>${hs.homeworkNo }</td>
					<td>${hs.lectureNo }</td>
					<td>${hs.memberId }</td>
					<td>${hs.homeworkSubmitTitle }</td>
					<td>${hs.homeworkSubmitCreateDate }</td>
					<td>${hs.homeworkSubmitUpdateDate }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<form method="get" action="${pageContext.request.contextPath}/homework/getHomeworkSubmitListByPage">
		<c:if test="${currentPage>1}">
			<button type="submit" class="btn btn-outline-warning btn-rounded" name="currentPage" value="${currentPage-1}">이전</button>
		</c:if>
		<!-- 목록 사이 번호 표시 -->
		<c:forEach begin="1" end="10" step="1" var="i" varStatus="status">
			<c:if test="${ i< 5 && currentPage-(5-i)> 0 }">
				<button type = "submit" value ="${currentPage-(5-i)}" name = "currentPage" class="btn btn-outline-info btn-rounded">${currentPage-(5-i)}</button>
			</c:if>
			<c:if test="${ i==5 }">
				<button type = "submit" value ="${currentPage}" name = "currentPage" class="btn btn-outline-danger btn-rounded">${currentPage}</button>
			</c:if>
			<c:if test="${ i > 5 && currentPage+(i-5) <= lastPage }">
				<button type = "submit" value ="${currentPage+(i-5)}" name = "currentPage" class="btn btn-outline-info btn-rounded">${currentPage+(i-5)}</button>
			</c:if>
		</c:forEach>
		<c:if test="${currentPage<lastPage}">
			<button type="submit" class="btn btn-outline-success btn-rounded" name="currentPage" value="${currentPage+1}">다음</button>
		</c:if>
	</form>

</body>
</html>