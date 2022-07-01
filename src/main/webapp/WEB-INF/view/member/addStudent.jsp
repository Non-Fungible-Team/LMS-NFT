<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 반응형 웹 -->
<!-- 반응형 웹 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<!-- title icon -->
<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/static/assets/images/favicon.png">
<title>학생 회원가입</title>
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
<!-- title icon -->
<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/static/assets/images/favicon.png">
<title>Lecture List</title>
<link href="${pageContext.request.contextPath}/static/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/static/dist/css/style.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
<script>
	//페이지 전부 로딩후 실행
	$('document').ready(function(){
		//주소 검색 버튼 부분
		$('#addrBtn').click(function(){
			//주소 api 요청값 부분
			var currentPage = 1;
			var countPerPage = 20;
			var keyword = $('#keyword').val();
				console.log('[addStudent.jsp.keyword] keyword : ' + keyword)
			var confmKey = "U01TX0FVVEgyMDIyMDYxNjE2MzExNTExMjY5ODQ=";
			var resultType = "json";
			var url ="https://www.juso.go.kr/addrlink/addrLinkApi.do?currentPage="+currentPage+"&countPerPage="+countPerPage+"&keyword="+keyword+"&confmKey="+confmKey+"&resultType="+resultType;
			//주소 api 요청
			$.ajax({
					type:'get'
					,url: url
					,success:function(a) {
						console.log(a)
						// json 파일을 json 문자열 타입으로 변경
						var a2 = JSON.stringify(a);
		            	console.log("▶▶▶a2 : "+a);
						// json문자열을 javascript 값으로 변경
						var a3 = JSON.parse(a2);
		                console.log("▶▶▶typeof(a3) : "+typeof(a3));
		               	// 값을 배열로 가공
		                let arr = a3.results.juso;
		                console.log("▶▶▶arr : "+arr);
		                //검색된 주소 리스트 요청
						for(var i=0; i<arr.length; i++){
							  $('#addrList').append("<option id='addrDetail' value='"+arr[i].roadAddr+"'>"+arr[i].roadAddr+"</option>");
						}
					}
			});
		});
		
		// submit 버튼 클릭시 유효성 검사 
		$('#signUp').click(function(){
			//버튼누를때마다 helper 값 초기화
			$('#memberIdHelper').text('');
			$('#memberPwHelper').text('');
			$('#studentNameHelper').text('');
			$('#studentBirthHelper').text('');
			$('#studentGenderHelper').text('');
			$('#studentEmailIdHelper').text('');
			$('#studentEducationHelper').text('');
			$('#memberPhoneNoHelper').text('');
			$('#addressHelper').text('');
			
			//아이디검사
			if($('#memberId').val()=='') {
				$('#memberIdHelper').text('아이디를 입력하세요');
				$('#memberId').focus();
			//비밀번호 검사
			} else if($('#memberPw').val()=='') {
				$('#memberPwHelper').text('비밀번호를 입력하세요');
				$('#memberPw').focus();
			} else if($('#memberPwCheck').val() != $('#memberPw').val()) {
				$('#memberPwHelper').text('비밀번호를 확인하세요');
				$('#memberPw').focus();
				//이름화인
			} else if($('#studentName').val()=='') {
				$('#studentNameHelper').text('이름을 입력하세요');
				$('#studentName').focus();
				//생년월일 확인
			} else if($('#studentBirth').val()=='') {
				$('#studentBirthHelper').text('생년월일을 입력하세요');
				$('#studentBirth').focus();
			} else if($('.studentGender:checked').length == 0) {
				$('#studentGenderHelper').text('성별을 고르세요');
				$('.studentGender').focus();
			} else if($('#studentEmailId').val()=='' || $('#emailUrl option:selected').val()=='') {
				$('#emailHelper').text('이메일을 입력하세요');
				$('#studentEmailId').focus();
			} else if($('#addrList option:selected').val()=='') {
				$('#addressHelper').text('주소를 선택해주세요');
				$('#keyword').focus();
			} else if($('#studentEducation').val()=='') {
				$('#studentEducationHelper').text('최종 학력을 입력하세요');
				$('#studentEducation').focus();	
			} else if($('#memberPhoneNo').val()=='') {
				$('#memberPhoneNoHelper').text('전화번호를 입력하세요');
				$('#memberPhoneNo').focus();
			} else {	
				//유효성 검사 완료, 주소에서 선택한 값으로 추가 검색하는 기능
				//선택한 버튼 값 변수에 저장
				var addrSelected = $('#addrList option:selected').val();
				//주소 api 요청
				var currentPage = 1;
				var countPerPage = 20;
				var keyword = $('#keyword').val();
					console.log('[addStudent.html.keyword] keyword : ' + keyword)
				var confmKey = "U01TX0FVVEgyMDIyMDYxNjE2MzExNTExMjY5ODQ=";
				var resultType = "json";
				var url ="https://www.juso.go.kr/addrlink/addrLinkApi.do?currentPage="+currentPage+"&countPerPage="+countPerPage+"&keyword="+keyword+"&confmKey="+confmKey+"&resultType="+resultType;
				//주소 api 요청
				$.ajax({
					type:'get'
					,url: url
					,success:function(a) {
						console.log(a)
						//json파일을 json문자열타입으로 변경
						var a2 = JSON.stringify(a);
			           	console.log("▶▶▶a2 : "+a);
						//json문자열을 javascript 값으로 변경
						var a3 = JSON.parse(a2);
			               console.log("▶▶▶typeof(a3) : "+typeof(a3));
			              	// 값을 배열로 가공
			               let arr = a3.results.juso;
			               console.log("▶▶▶arr : "+arr);
			               //검색된 주소 리스트 요청
						for(var i=0; i<arr.length; i++){
							//selected 된 검색값과 같은 정보 추가 입력
							if(addrSelected==arr[i].roadAddr){
								//우편번호 입력
								 $('#abc').append("<input type='text' name='zipCode' value='"+arr[i].zipNo+"'>");	
								//시 입력
								 $('#abc').append("<input type='text' name='province' value='"+arr[i].siNm+"'>");
								 //일반시, 군, 도 입력
								 $('#abc').append("<input type='text' name='city' value='"+arr[i].sggNm+"'>");
								 //읍면동 입력
								 $('#abc').append("<input type='text' name='town' value='"+arr[i].emdNm+"'>");	
							}
						}
			        // 입력한 email 값으로 email 재입력     
			        // $('#abc').append("<input type='text' name='studentEmail' value='"+$('#studentEmailId').val()+$('#middleEmail').val()+$('#emailUrl option:selected').val()+"'>");
			        
		            // 유효성 검사 및 추가 주소 정보 입력 완료 후 submit
					$('#signUpStudent').submit();
 					}
				});
			}
		});
	});
</script>
</head>
<script>
	$('document').ready(function() {
		$("#navAside").load('${pageContext.request.contextPath}/include/navAside.jsp');
		});
</script>
<body>

	<div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full" data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
		<!-- header include(네비게이션바) -->
		<div id="navAside"></div>
		<div class="page-wrapper">
			<div class="container-fluid">
				<h1>학생 회원가입</h1>
				<!-- main화면 body start -->

				<!-- 첫번쨰 문단 -->
				<div class="container p-5 my-5 border">
					<div class="row">
						<div class="col-lg-12 col-md-12">
							<div class="card">
								<div class="card-body">
									<h4 class="card-title">학생 회원 가입</h4>
									<div class="mt-2" style="height: auto; width: auto;">
										<!-- 테이블 넣는곳, 테이블 색깔 변경 ->class만 변경 -->
										<form id="signUpStudent" method="post" action="${pageContext.request.contextPath}/addStudent">
											<table id="zero_config" class="table table-striped table-bordered">
												
												<!--  
													<tr>
														<td>아이디 중복 검사</td>
														<td><input type="text" id="idck">
														<button type="button" id="idBtn">중복 검사</button></td>
													</tr>
												-->
												
												<tr>
													<td>학생 아이디</td>
													<td><input type="text" id="memberId" name="memberId" > <span
														id="memberIdHelper" class="helper"></span></td>
												</tr>
												<tr>
													<td>학생 비밀번호</td>
													<td><input type="password" id="memberPw" name="memberPw" >
														<span id="memberPwHelper" class="helper"></span></td>
												</tr>
												<tr>
													<td>비밀번호 확인</td>
													<td><input type="password" id="memberPwCheck">
												</tr>
												<tr>
													<td>이름</td>
													<td><input type="text" id="studentName" name="studentName" >
														<span id="studentNameHelper" class="helper"></span></td>
												</tr>
												<tr>
													<td>생년월일</td>
													<td><input type="date" id="studentBirth" name="studentBirth">
														<span id="studentBirthHelper" class="helper"></span></td>
												</tr>
												<tr>
													<td>성별</td>
													<td>
														<input type="radio" value="M" id="studentGender" name="studentGender" class="studentGender">남 
														<input type="radio" value="F" id="studentGender" name="studentGender" class="studentGender">여 
														<span id="studentGenderHelper" class="helper"></span>
													</td>
												</tr>
												<tr>
													<td>이메일</td>
													<td>
														<input type="text" id="studentEmailId" name="studentEmailId" value="stud3">
														<span id="middleEmail">@</span>
														<select id="emailUrl" name="emailUrl">
															<option value="">::선택::</option>
															<option value="naver.com">네이버</option>
															<option value="daum.net">다음</option>
															<option value="gmail.com">지메일</option>
														</select> 
														<input type="hidden" id="studentEmail" name="studentEmail">
														<span id="studentEmailIdHelper" class="helper"></span>
													</td>
												</tr>
												<tr>
													<td>주소</td>
													<td><input type="text" id="keyword" name="keyword">
													<span id="addressHelper" class="helper"></span>
													<button type="button" id="addrBtn" class="btn btn-rounded btn-outline-secondary">주소검색</button></td>
												</tr>
												<tr>
													<td>주소리스트</td>
													<td>
														<select id="addrList" name="roadAddr">
														<option value="">주소검색을 해주세요</option>
														</select>
														상세주소 : <input type = "text" name = "addrDetail">
													</td>
												</tr>
												<tr>
													<td>최종 학력</td>
													<td>
														<input type="text" id="studentEducation" name="studentEducation" value="대졸"> 
														<span id="studentEducationHelper" class="helper"></span>
													</td>
												</tr>
												<tr>
													<td>전화번호</td>
													<td><input type="text" id="memberPhoneNo" name="memberPhoneNo" value="010-1234-1234">
														<span id="memberPhoneNoHelper" class="helper"></span></td>
												</tr>
												<div>
													<input type=hidden id="memberLevel" name="memberLevel" value="4" readonly="readonly">
												</div>
												<div id ="abc"></div>
												<tr>
													<td colspan="2">
														<!-- 폼 text, radio, checkbox 공백이 있는지 체크 -->
														<button type="button" id="signUp" class="btn btn-rounded btn-outline-success">학생 회원가입</button>
														<button type="reset" class="btn btn-rounded btn-outline-warning">입력 내용 초기화</button>
													</td>
									            </tr>
											</table>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</div>
</body>

<script>
	//이메일주소 가져오기
	// 이 부분 밑에 있어야 함 
	$("#studentEmailId").blur(function(){
		email();	
	});
	
	$("#emailUrl").change(function(){
		email();	
	});
	
	function email() {
		const email = $("#studentEmailId").val();
		const middle = $("#middleEmail").text();
		const address = $("#emailUrl").val();
		if(email != "" && address != "") {
			$("#studentEmail").val(email+middle+address);
		}
	};
</script>
<script src="${pageContext.request.contextPath}/static/dist/js/app-style-switcher.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/feather.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/sidebarmenu.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/custom.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/extra-libs/c3/d3.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/extra-libs/c3/c3.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/chartist/dist/chartist.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/chartist-plugin-tooltips/dist/chartist-plugin-tooltip.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/extra-libs/jvector/jquery-jvectormap-world-mill-en.js"></script>
<script src="${pageContext.request.contextPath}/static/dist/js/pages/dashboards/dashboard1.min.js"></script>
</html>