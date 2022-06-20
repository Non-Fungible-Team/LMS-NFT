<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>subjectList</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		
		$('#addSubject').click(function(){
			if($('#subjectName').val() == '') {
				alert('과목 이름을 입력하세요');
			} else if($('#subjectContent').val() == '') {
				alert('내용을 입력하세요');
			} else {
				$('#addForm').submit();
			}
		});
		
	});	
</script>
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
                <th></th>
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
                   <td><a href="${pageContext.request.contextPath}/removeSubjectOne?subjectNo=${s.subjectNo}">삭제</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <h1>subject add</h1>
    <form method="post" id="addForm" action="${pageContext.request.contextPath}/addSubjectOne">
	    <table>
	    	<tr>
	    		<td>과목 이름 : </td>
	    		<td><input type="text" name="subjectName" id="subjectName"></td>
	    	</tr>
	    	<tr>
	    		<td>내용 : </td>
	    		<td><input type="text" name="subjectContent" id="subjectContent"></td>
	    	</tr>
	    </table>
	    <div>
			<button type="button" class="btn btn-default" id="addSubject">입력</button>
		</div>
    </form>
</body>
</html>