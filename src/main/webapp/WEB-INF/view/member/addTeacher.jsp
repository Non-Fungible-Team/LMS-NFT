<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addTeacher</title>
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$('document').ready(function(){
	//데이터 호출
	var currentPage = 1;
	var countPerPage = 20;
	var keyword = $('#keyword').val();
		console.log('[addteacher.html.keyword] keyword : ' + keyword)
	var confmKey = "U01TX0FVVEgyMDIyMDYxNjE2MzExNTExMjY5ODQ=";
	var resultType = "json";
	var url ="https://www.juso.go.kr/addrlink/addrLinkApi.do?currentPage="+currentPage+"&countPerPage="+countPerPage+"&keyword="+keyword+"&confmKey="+confmKey+"&resultType="+resultType;
	
	/*
		$('#idBtn').click(function(){
			if($('#idck').val().length > 3) {
				$.ajax({
					type:'post'
					,url:'/lms/idCheck'
					,data:{memberId:$('#idck').val()}
					,success:function(ck) {
						console.log('[addteacher.html.param] ck : ' + ck)
						if(ck == 'false') {
							alert('이미 사용중인 아이디입니다');
						} else {
							alert('id는 4자 이상');
						}
					}
				});
			} else {
				alert('id는 4자 이상');
			}
		});
	*/
	
		$('#addrBtn').click(function(){
			$.ajax({
				type:'get'
				,url: url
				,success:function(a) {
				console.log(a)
				var a2 = JSON.stringify(a);
				var a3 = JSON.parse(a2);
               console.log("▶▶▶a2 : "+a);
               console.log("▶▶▶typeof(a3) : "+typeof(a3));
               
               let arr = a3.results.juso; // 주소 배열
               console.log("▶▶▶arr : "+arr);
               
					for(var i=0; i<arr.length; i++){
						  $('#addrList').append(`<option value="arr[i].roadAddr">`+arr[i].roadAddr+`</option>`);	
					}
				}
					
				});
			});
		
	});
</script>
</head>
<body>
	<h1>강사 회원가입</h1>
	<form id="signUpteacher" method="post"
		action="${pageContext.request.contextPath}/addTeacher">
		<table>
			<!--  
				<tr>
					<td>아이디 중복 검사</td>
					<td><input type="text" id="idck">
					<button type="button" id="idBtn">중복 검사</button></td>
				</tr>
			-->
			
			<tr>
				<td>강사 아이디</td>
				<td>
					<input type="text" id="memberId" name="memberId" value="teacher1"> 
					<span id="memberIdHelper" class="helper"></span>
				</td>
			</tr>
			<tr>
				<td>강사 비밀번호</td>
				<td>
					<input type="password" id="memberPw" name="memberPw" value="1234">
					<span id="memberPwHelper" class="helper"></span>
				</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>
					<input type="text" id="teacherName" name="teacherName" value="강사1">
					<span id="teacherNameHelper" class="helper"></span>
				</td>
			</tr>
			<tr>
				<td>생년월일</td>
				<td>
					<input type="date" id="teacherBirth" name="teacherBirth">
					<span id="teacherBirthHelper" class="helper"></span>
				</td>
			</tr>
			<tr>
				<td>성별</td>
				<td>
					<input type="radio" value="M" name="teacherGender" class="gender">남 
					<input type="radio" value="F" name="teacherGender" class="gender">여 
					<span id="teacherGenderHelper" class="helper"></span>
				</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td>
					<input type="text" id="teacherEmailId" name="teacherEmailId" value="teacher2"> @ <select id="emailUrl" name="emailUrl">
						<option value="">::선택::</option>
						<option value="naver.com">네이버</option>
						<option value="daum.net">다음</option>
						<option value="gmail.com">지메일</option>
					</select> 
					<span id="teacherEmailIdHelper" class="helper"></span>
				</td>
			</tr>
			<tr>
				<td>주소</td>
				<td>
					<input type="text" id="keyword" name="keyword" value="가산디지털2로">
					<button type="button" id="addrBtn">주소검색</button>
				</td>
			</tr>
			<tr>
				<td>주소리스트</td>
				<td>
					<select id="addrList" name="roadAddr"></select>
				</td>
			</tr>
			<tr>
				<td>입사일</td>
				<td>
					<input type="date" id="teacherEntryDate" name="teacherEntryDate" value="2022-06-27"> 
					<span id="teacherEntryDateHelper" class="helper"></span>
				</td>
			</tr>
			<tr>
				<td>담당 과목</td>
				<td>
					<input type="text" id="teacherSubject" name="teacherSubject" value="자바"> 
					<span id="teacherSubjectHelper" class="helper"></span>
				</td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td>
					<input type="text" id="memberPhoneNo" name="memberPhoneNo" value="010-1234-1234">
					<span id="memberPhoneNoHelper" class="helper"></span>
				</td>
			</tr>
			<tr>
				<td>레벨</td>
				<td>
					<input type="number" id="memberLevel" name="memberLevel" value="5" readonly="readonly">
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<!-- 폼 text, radio, checkbox 공백이 있는지 체크 -->
					<button type="submit" id="signup">강사 회원가입</button>
					<button type="reset">입력 내용 초기화</button>
				</td>
            </tr>
		</table>
	</form>
</body>

<script>

	//이메일주소 가져오기
	$("#teacherEmailId").blur(function(){
		email();	
	});
	
	$("#emailUrl").change(function(){
		email();	
	});
	
	function email() {
		const email = $("#teacherEmailId").val();
		const middle = $("#middle").text();
		const address = $("#emailUrl").val();
		if(email != "" && address != "") {
			$("#teacherEmailId").val(email+middle+address);
		}
	};
	
	// 유효성 검사 
	$('#signUp').click(function(){
		if($('#memberId').val()=='') {
			$('#memberIdHelper').text('아이디를 입력하세요');
			$('#memberId').focus();
		} else if($('#memberPw').val()=='') {
			$('#memberIdHelper').text('');
			$('#memberPwHelper').text('비밀번호를 입력하세요');
			$('#memberPw').focus();
		} else if($('teacherName').val()=='') {
			$('#memberPwHelper').text('');
			$('#teacherNameHelper').text('이름을 입력하세요');
			$('#teacherName').focus();
		} else if($('teacherBirth').val()=='') { // *
			$('#teacherNameHelper').text('');
			$('#teacherBirthHelper').text('생년월일을 입력하세요');
			$('#teacherBirth').focus();
		} else if($('.teacherGender:checked').length == 0) {
			$('#teacherBirthHelper').text('');
			$('#teacherGenderHelper').text('성별을 고르세요');
			$('.teacherGender').focus();
		} else if($('#teacherEmailId').val()=='' || $('#emailUrl').val()=='') {
			$('#teacherGenderHelper').text('');
			$('#teacherEmailHelper').text('이메일을 입력하세요');
			$('#teacherEmailId').focus();
		} else if($('#teacherEntryDate'.val()=='')) {
			$('#teacherEmailHelper').text('');
			$('#teacherEntryDateHelper').text('입사일을 선택하세요');
			$('#teacherEntryDate').focus();	
		} else if($('#teacherSubject'.val()=='')) {
			$('#teacherEntryDateHelper').text('');
			$('#teacherSubjectHelper').text('담당 과목을 입력하세요');
			$('#teacherSubject').focus();	
		} else if($('#memberPhoneNo').val()=='') {
			$('#teacherSubjectHelper').text('');
			$('#memberPhoneNoHelper').text('전화번호를 입력하세요');
			$('#memberPhoneNo').focus();
		} else {
			$('#signupteacher').submit();
		}
	});
</script>
</html>