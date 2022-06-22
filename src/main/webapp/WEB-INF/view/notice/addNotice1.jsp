<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addNotice</title>
</head>
<body>
	<div>
	    <h1>addNotice</h1>
	    <form action="${pageContext.request.contextPath}/manager/notice/addNotice" method="post">
	    	<table border="1">
	    		<tr>
	    			<th>제목</th>
	    			<td><input type="text" name="noticeTitle"></td>
	    		</tr>
	    		<tr>
	    			<th>내용</th>
	    			<td><input type="text" name="noticeContent" ></td>
	    		</tr>
	    		<tr>
	    			<th>읽기권한</th>
	    			<td>
	    				<select name = "noticePrivilege">
	    					<option value="-1">::읽기권한::</option>
	    					<option value="4">학생이상</option>
	    					<option value="5">강사이상</option>
	    					<option value="6">운영자만</option>
	    				</select>
	    			<td>
	    		</tr>
	    	</table>
	    	<button type="submit">등록</button>
	    </form>
	    <ul class="pager">
	      <li><a href="${pageContext.request.contextPath}/all/notice/getNoticeListByPage">목록으로</a></li>
	    </ul>
	</div>
</body>
</html>