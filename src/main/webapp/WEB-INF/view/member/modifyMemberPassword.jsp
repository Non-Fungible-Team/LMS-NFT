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
<title>modifyMembePw</title>
<link href="${pageContext.request.contextPath}/static/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/static/dist/css/style.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>

<style>
.helper {
	color: #FF0000;
}
</style>

</head>
<script>
$('document').ready(function() {
		$("#navAside").load('${pageContext.request.contextPath}/include/navAside.jsp');
		
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
});
</script>
<body>
	<div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full" data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
		<!-- header include(네비게이션바) -->
		<div id="navAside"></div>
		<div class="page-wrapper">
			<div class="container-fluid">
				<h1>비밀번호 변경 페이지</h1>
				<!-- main화면 body start -->
				
				<!-- 첫번쨰 문단 -->
				<div class="container p-5 my-5 border">
					<div class="row">
						<div class="col-lg-12 col-md-12">
							<div class="card">
								<div class="card-body">
									<h4 class="card-title">입력</h4>
									<div class="mt-2" style="height: auto; width: auto;">
										<!-- 테이블 넣는곳, 테이블 색깔 변경 ->class만 변경 -->
										<form action="${pageContext.request.contextPath}/all/modifyMemberPassword" method="post" id="updatePasswordForm">
											<table id="zero_config" class="table table-striped table-bordered">
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
														<button type="submit" name="updatePasswordButton" id="updatePasswordButton" class="btn btn-rounded btn-outline-primary">수정</button>
													</td>
												</tr>
											</table>
										</form>
										<div>
											<a href="${pageContext.request.contextPath}/login" class="btn btn-rounded btn-outline-primary">로그인 페이지</a>
										</div>
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
<script>

	
</script>
</html>