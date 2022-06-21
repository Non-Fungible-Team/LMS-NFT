<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addBoard</title>
</head>
<body>
	<div>
	    <h1>addBoard</h1>
	    <form action="${pageContext.request.contextPath}/board/addBoard" method="post">
	    	<table border="1">
	    		<tr>
	    			<th>제목</th>
	    			<td><input type="text" name="boardTitle" value="${board.boardTitle}"></td>
	    		</tr>
	    		<tr>
	    			<th>내용</th>
	    			<td><input type="text" name="boardContent" value="${board.boardContent}"></td>
	    		</tr>
	    		<%-- <tr>
	    			<th>읽기권한</th>
	    			<td><input type="checkbox" name="boardBlind" value="${board.boardPrivilege}"></td>
	    		</tr> --%>
	    	</table>
	    	<button type="submit">등록</button>
	    </form>
	    <ul class="pager">
	      <li><a href="${pageContext.request.contextPath}/board/getBoardByPage?boardNo=${board.boardNo}">목록으로</a></li>
	    </ul>
	</div>
</body>
</html>