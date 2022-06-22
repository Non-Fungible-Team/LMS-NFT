<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>lectureRoomList</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		
		$('#addLectureRoom').click(function() {
			if($('#lectureRoomName').val() == '') {
				alert('강의실 이름을 입력하세요');
			} else if($('#lectureRoomLocation').val() == '') {
				alert('강의실 위치를 입력하세요');
			} else if($('#lectureRoomPeople').val() == '') {
				alert('강의실 인원을 입력하세요');
			} else {
				$('#addForm').submit();
			}
		});
		
	});	
</script>
</head>

<body>
	<h1>lectureRoomList</h1>
    <table>
        <thead>
            <tr>
                <th>room name</th>
                <th>room location</th>
                <th>room people</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="r" items="${lectureRoomList}">
                <tr>
                   <td>${r.lectureRoomName}</td>
                   <td>${r.lectureRoomLocation}</td>
                   <td>${r.lectureRoomPeople}</td>
                   <td><a href="${pageContext.request.contextPath}/teacher/lecture/removeLectureRoom?lectureRoomName=${r.lectureRoomName}">삭제</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <h1>lectureRoom add</h1>
    <form method="post" id="addForm" action="${pageContext.request.contextPath}/teacher/lecture/addLectureRoom">
	    <table>
	    	<tr>
	    		<td>lectureRoom name : </td>
	    		<td><input type="text" name="lectureRoomName" id="lectureRoomName"></td>
	    	</tr>
	    	<tr>
	    		<td>lectureRoom location : </td>
	    		<td><input type="text" name="lectureRoomLocation" id="lectureRoomLocation"></td>
	    	</tr>
	    	<tr>
	    		<td>lectureRoom people : </td>
	    		<td><input type="number" name="lectureRoomPeople" id="lectureRoomPeople"></td>
	    	</tr>
	    </table>
	    <div>
			<button type="button" class="btn btn-default" id="addLectureRoom">입력</button>
		</div>
    </form>
</body>
</html>