<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.helper {
	color: #FF0000;
}
</style>
</head>
<body>
	<form action="${pageContext.request.contextPath}/all/modifyMemberPassword" method="post" id="updatePasswordForm">
		<table border="1">
			<tr>
				<td>ID : </td>
				<td>
					<input type="text" name="memberId" id="memberId" value="${sessionLoginMember.memberId}" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td>현재 PW : </td>
				<td>
					<input type="password" name="currentPassword" id="currentPassword">
					<span id="currentPasswordHelper" class="helper"></span>
				</td>
			</tr>
			<tr>
				<td>신규 PW : </td>
				<td>
					<input type="password" name="memberPw" id="memberPw">
					<span id="memberPwHelper" class="helper"></span>
				</td>
			</tr>
			<tr>
				<td>신규 PW Check: </td>
				<td>
					<input type="password" name="memberPwCheck" id="memberPwCheck">
					<span id="memberPwCheckHelper" class="helper"></span>
				</td>
				<td colspan="2">
					<button type="button" name="updatePasswordButton" id="updatePasswordButton">수정</button>
				</td>
			</tr>
		</table>
	</form>
</body>

<script>

	// 비밀번호 유효성 코드 
	$('#updatePasswordButton').click(function(){
		
		if($('#currentPassword').val() == '' || $('#currentPassword').val().length < 4) {
			$('#currentPasswordHelper').text('사용하던 pw는 4자 이상 입력해주세요');
			$('#currentPassword').focus();
		}
		
		else if($('#memberPw').val() == '' || $('#memberPw').val().length < 4) {
			$('#memberPwHelper').text('새로 사용할 pw는 4자 이상 입력해주세요');
			$('#memberPw').focus();
		}
		
		else if($('#memberPwCheck').val() != $('#memberPw').val()) {
			$('#memberPwHelper').text('');
			$('#memberPwCheckHelper').text('새로 사용할 pw가 일치하지 않습니다.');
			$('#memberPwCheck').focus();
		}
		
		else {
			$('#memberPwCheckHelper').text('');
			$('#updatePasswordForm').submit();
		}
	});
</script>
</html>