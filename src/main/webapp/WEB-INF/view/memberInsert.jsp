<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 신청</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script>
$('document').ready(function(){
	$('#btn').click(function(){
		if($('#idck').val().length > 3) {
			$.ajax({
				type:'post'
				,url:'/lms/idCheck'
				,data:{id:$('#idck').val()}
				,success:function(ck){
					console.log('ck:',ck);
					if(ck=='false') {
						alert('이미 사용중인 아이디 입니다');
					} else {
						$('#id').val(ck);
					}
				}
			});
		} else {
			alert('id는 4자 이상');
		}
		
	});
});
</script>
</head>
<body>
	<form method="post" action="${pageContext.request.contextPath}/memberInsert" id="managerInsertForm">
		<table>
			<tr>
				<td>id 중복 확인 : </td>
				<td><input type="text" id="idck"><button type="button" id="btn">중복검사</button></td>
			</tr>
			<tr>
				<td>id</td>
				<td> <input type="text" id="id" name="id" readonly="readonly"> </td>
			</tr>
			<tr>
				<td>pw</td>
				<td><input type="text" id="pw" name="pw"> </td>
			</tr>
			<tr>
				<td>pw ck</td>
				<td><input type="text" id="pwck" name="pwck"> </td>
			</tr>
			<tr>
				<td>phoneNo</td>
				<td><input type="text" id="phoneNo" name="phoneNo"></td>
			</tr>
			<tr>
				<td>name</td>
				<td><input type="text" id="name" name="name"></td>
			</tr>
			<tr>
				<td>gender</td>
				<td>
					<input type="radio" value="M" name="gender" class="gender">M 
					&nbsp; 
					<input type="radio" value="F" name="gender" class="gender">F 
					&nbsp; 
					<span id="genderHelper" class="helper"></span>
				</td>
			</tr>
			<tr>
				<td>birth</td>
				<td>
					<input type="date" name="birth" id="birth"> 
					&nbsp; 
					<span id="birthHelper" class="helper"></span>
				</td>
			</tr>
			<tr>
				<td>email</td>
				<td>
					<input type="text" id="emailId" name="emailId"> @
					<select id="emailUrl" name="emailUrl">
						<!-- 필수 -->
						<option value="">::선택::</option>
						<option value="naver.com">naver.com</option>
						<option value="daum.net">daum.net</option>
						<option value="gmail.com">gmail.com</option>
					</select> 
					&nbsp; 
					<span id="emailHelper" class="helper"></span>
				</td>
			</tr>
			<tr>
				<td>phone</td>
				<td>
					<input type="text" id="phone" name="phone"> 
					&nbsp; 
					<span id="phoneHelper" class="helper"></span>
				</td>
			</tr>
			<tr>
               <td colspan="2"><button type="submit">회원가입 신청</button></td>
            </tr>
		</table>
	</form>
</body>
</html>