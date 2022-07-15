<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<!-- title icon -->
<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/assets/images/favicon.png">
<title>Non-Fungible LMS</title>
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
<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/assets/images/favicon.png">
<title>Lecture List</title>
<link href="${pageContext.request.contextPath}/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/dist/css/style.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
<script>
//페이지 전부 로딩후 실행
$('document').ready(function(){

	//사이드 및 헤더 include
	$("#navAside").load('${pageContext.request.contextPath}/include/navAside.jsp');
	
	//주소 검색 버튼 부분
	$('#addrBtn').click(function(){
		//주소 api 요청값 부분
		var currentPage = 1;
		//api가 제공하는 최대값
		var countPerPage = 100;
		var keyword = $('#keyword').val();
			console.log('[addmanager.jsp.keyword] keyword : ' + keyword)
		var confmKey = "U01TX0FVVEgyMDIyMDYxNjE2MzExNTExMjY5ODQ=";
		var resultType = "json";
		var url ="https://www.juso.go.kr/addrlink/addrLinkApi.do?currentPage="+currentPage+"&countPerPage="+countPerPage+"&keyword="+keyword+"&confmKey="+confmKey+"&resultType="+resultType;
		//출력한 행의 수
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
					//erroMessage가 이미 써있다면 초기화
					$('#addrErroMessage').text('');
	                var errorMessage = a3.results.common.errorMessage;
	                //에러 메세지가 있다면 출력
	                if(errorMessage != '정상'){
					  $('#addrErroMessage').append(errorMessage);
					  return;
	                }
	                //받아온 결과값이 0 또는 결과값이 api 최대치인 100개 보다 많을 경우 erromessage 입력
	                if(a3.results.common.totalCount ==  0 || a3.results.common.totalCount > 100 ){
					  $('#addrErroMessage').append("주소를 더 자세히 입력해주세요");
					  return;
	                }
	               	//성공시 값을 배열로 가공
	                let arr = a3.results.juso;
	                console.log("▶▶▶arr : "+arr);
	                //리스트 요청전 이미 검색된 리스트 초기화
					$('#addrList').text("");
					$('#keyword2').text("");
	                //검색된 주소 리스트 요청
					for(var i=0; i<arr.length; i++){
						  $('#addrList').append("<option id='addrDetail' value='"+arr[i].roadAddr+"'>"+arr[i].roadAddr+"</option>");
					}
	                //keyward2에 검색결과 저장
					  $('#keyword3').append("<input type='hidden' id='keyword2' name='keyword2' value ='"+keyword+"'>");
	                
				}
		});
	});
	
	//아이디 중복 체크 기능
	$('#idBtn').click(function(){
		if($('#idck').val().length > 3) {
			$.ajax({
				type:'post'
				, url:'/lms/idCheck'
				, data:{memberId:$('#idck').val()}
				, success : function(ck) {
					console.log('ck:',ck);
					if(ck=="false") {
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
	
	// submit 버튼 클릭시 유효성 검사 
	$('#signUp').click(function(){
		// 버튼 누를 때마다 helper 값 초기화
		$('#memberIdHelper').text('');
		$('#memberPwHelper').text('');
		$('#managerNameHelper').text('');
		$('#managerBirthHelper').text('');
		$('#memberPhoneNoHelper').text('');
		$('#addressHelper').text('');
		$('#managerGenderHelper').text('');
		$('#managerEmailHelper').text('');
		$('#managerEntryDateHelper').text('');
		$('#plusAddress').text('');
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
		} else if($('#managerName').val()=='') {
			$('#managerNameHelper').text('이름을 입력하세요');
			$('#managerName').focus();
			//생년월일 확인
		} else if($('#managerBirth').val()=='') {
			$('#managerBirthHelper').text('생년월일을 입력하세요');
			$('#managerBirth').focus();
		} else if($('.managerGender:checked').length == 0) {
			$('#managerGenderHelper').text('성별을 고르세요');
			$('.managerGender').focus();
		} else if($('#addrList option:selected').val()=='') {
			$('#addressHelper').text('주소를 선택해주세요');
			$('#keyword').focus();
		} else if($('#managerEmailId').val()=='' || $('#emailUrl').val()=='') {
			$('#managerEmailHelper').text('이메일을 입력하세요');
			$('#managerEmailId').focus();
		} else if($('#managerEntryDate').val()=='') {
			$('#managerEntryDateHelper').text('입사일을 선택하세요');
			$('#managerEntryDate').focus();	
		} else if($('#managerSubject').val()=='') {
			$('#managerSubjectHelper').text('담당 과목을 입력하세요');
			$('#managerSubject').focus();	
		} else if($('#memberPhoneNo').val()=='') {
			$('#memberPhoneNoHelper').text('전화번호를 입력하세요');
			$('#memberPhoneNo').focus();
		} else {	
			//유효성 검사 완료, 주소에서 선택한 값으로 추가 검색하는 기능
			//선택한 버튼 값 변수에 저장
			var addrSelected = $('#addrList option:selected').val();
			//주소 api 요청
			var currentPage = 1;
			var countPerPage = 100;
			var keyword = $('#keyword2').val();
				console.log('[addmanager.html.keyword] keyword : ' + keyword)
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
							 $('#plusAddress').append("<input type='text' name='zipCode' value='"+arr[i].zipNo+"'>");	
							//시 입력
							 $('#plusAddress').append("<input type='text' name='province' value='"+arr[i].siNm+"'>");
							 //일반시, 군, 도 입력
							 $('#plusAddress').append("<input type='text' name='city' value='"+arr[i].sggNm+"'>");
							 //읍면동 입력
							 $('#plusAddress').append("<input type='text' name='town' value='"+arr[i].emdNm+"'>");	
						}
					}
		        // 입력한 email 값으로 email 재입력     
				const email = $("#managerEmailId").val();
				const middle = $("#middleEmail").text();
				const address = $("#emailUrl").val();
				$("#managerEmail").val(email+middle+address);
		        
	            // 유효성 검사 및 추가 주소 정보 입력 완료 후 submit
				$('#signUpManager').submit();
					}
			});
		}
	});
	
});
</script>
</head>
<body>
	<div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full" data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
	<!-- header include(네비게이션바) -->
	<div id="navAside"></div>
	<div class="page-wrapper">
	<div class="container-fluid">
		<h1>운영진 회원가입</h1>
				<!-- 첫번쨰 문단 -->
				<div class="container p-5 my-5 border">
					<div class="row">
						<div class="col-lg-12 col-md-12">
							<div class="card">
								<div class="card-body">
									<h4 class="card-title">운영진 회원 가입</h4>
									<div class="mt-2" style="height: auto; width: auto;">
										<!-- 테이블 넣는곳, 테이블 색깔 변경 ->class만 변경 -->	<form id="signUpManager" method="post" action="${pageContext.request.contextPath}/addManager">
											<table id="zero_config" class="table table-striped table-bordered">
												<tr>
													<td>아이디 중복 검사</td>
													<td>
														<input type="text" id="idck" name="idck">
														<button type="button" id="idBtn">중복 검사</button>
													</td>
												</tr>
												<tr>
													<td>운영진 아이디</td>
													<td>
														<input type="text" id="memberId" name="memberId" readonly="readonly"> 
														<span id="memberIdHelper" class="helper"></span>
													</td>
												</tr>
												<tr>
													<td>운영진 비밀번호</td>
													<td><input type="password" id="memberPw" name="memberPw" >
														<span id="memberPwHelper" class="helper"></span></td>
												</tr>
												<tr>
													<td>비밀번호 확인</td>
													<td><input type="password" id="memberPwCheck">
												</tr>
												<tr>
													<td>이름</td>
													<td>
														<input type="text" id="managerName" name="managerName">
														<span id="managerNameHelper" class="helper"></span>
													</td>
												</tr>
												<tr>
													<td>생년월일</td>
													<td>
														<input type="date" id="managerBirth" name="managerBirth">
														<span id="managerBirthHelper" class="helper"></span>
													</td>
												</tr>
												<tr>
													<td>성별</td>
													<td>
														<input type="radio" value="M" name="managerGender" class="managerGender">남 
														<input type="radio" value="F" name="managerGender" class="managerGender">여 
														<span id="managerGenderHelper" class="helper"></span>
													</td>
												</tr>
												<tr>
													<td>이메일</td>
													<td>
														<input type="text" id="managerEmailId" name="managerEmailId"> @ <select id="emailUrl" name="emailUrl">
															<option value="">::선택::</option>
															<option value="naver.com">네이버</option>
															<option value="daum.net">다음</option>
															<option value="gmail.com">지메일</option>
														</select> 
														<input type="hidden" id="managerEmail" name="managerEmail">
														<span id="managerEmailIdHelper" class="helper"></span>
													</td>
												</tr>
												<tr>
													<td>주소</td>
													<td><input type="text" id="keyword" name="keyword">
													<span id="addressHelper" class="helper"></span>
													<span id="addrErroMessage" class="helper"></span>
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
													<td>입사일</td>
													<td>
														<input type="date" id="managerEntryDate" name="managerEntryDate"> 
														<span id="managerEntryDateHelper" class="helper"></span>
													</td>
												</tr>
												<tr>
													<td>전화번호</td>
													<td>
														<input type="text" id="memberPhoneNo" name="memberPhoneNo" >
														<span id="memberPhoneNoHelper" class="helper"></span>
													</td>
												</tr>
												<tr>
													<td>레벨</td>
													<td>
														<input type="number" id="memberLevel" name="memberLevel" value="3" readonly="readonly">
													</td>
												</tr>
												<!-- 검색한 주소 keyword, 등록 선택시 추가되는 상세 주소 들어올 위치 -->
												<div id ="keyword3"></div>
												<div id ="plusAddress"></div>												
												<tr>
													<td colspan="2">
														<!-- 폼 text, radio, checkbox 공백이 있는지 체크 -->
														<button type="button" id="signUp">운영진 회원가입</button>
														<button type="reset">입력 내용 초기화</button>
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
<script src="${pageContext.request.contextPath}/dist/js/app-style-switcher.js"></script>
<script src="${pageContext.request.contextPath}/dist/js/feather.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/dist/js/sidebarmenu.js"></script>
<script src="${pageContext.request.contextPath}/dist/js/custom.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/extra-libs/c3/d3.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/extra-libs/c3/c3.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/chartist/dist/chartist.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/chartist-plugin-tooltips/dist/chartist-plugin-tooltip.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/extra-libs/jvector/jquery-jvectormap-world-mill-en.js"></script>
<script src="${pageContext.request.contextPath}/dist/js/pages/dashboards/dashboard1.min.js"></script>
</html>