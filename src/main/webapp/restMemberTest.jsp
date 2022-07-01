<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table, tr, td {
		border: 1px solid #000000;
		border-collapse: collapse;
	}
	
	table {
		width: 100%;
	}
	
	.helper {
		color: #FF0000;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<script>
$('document').ready(function(){
	$('#idBtn').click(function(){
		if($('#idck').val().length > 3) {
			$.ajax({
				type:'post'
				, url:'/lms/idCheck'
				, data:{memberId:$('#idck').val()}
				, success : function(ck) {
					console.log('ck:',ck);
					if(ck='false') {
						alert('이미 사용중인 아이디입니다');
					} else {
						$('#memberId').val(ck);
					}
				}
			});
		} else {
			alert('id는 4자 이상');
		}
	});
	
	if($('#memberId').val()=='') {
		$('#memberIdHelper').text('아이디를 입력하세요');
		$('#memberId').focus();
	}
});
</script>
<body>
	<table border="1">
			<tr>
				<td>아이디 중복 검사</td>
				<td>
					<input type="text" id="idck" name="idck">
					<button type="button" id="idBtn">중복 검사</button>
				</td>
			</tr>
			
			
			<tr>
				<td>학생 아이디</td>
				<td>
					<input type="text" id="memberId" name="memberId" > 
					<span id="memberIdHelper" class="helper"></span>
				</td>
			</tr>
	</table>
</body>
</html>