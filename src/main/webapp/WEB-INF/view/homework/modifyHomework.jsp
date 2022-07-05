<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 반응형 웹 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<!-- title icon -->
<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/static/assets/images/favicon.png">
<title>과제 수정</title>
<link href="${pageContext.request.contextPath}/static/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/static/dist/css/style.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
</head>
<script>
	$('document').ready(function() {
		$("#navAside").load('${pageContext.request.contextPath}/include/navAside.jsp');
		
		// 유효성 검사
		$('#modifyHomework').click(function(){
			$('#homeworkTitleHelper').text('');
			$('#homeworkContentHelper').text('');
			$('#homeworkStartDateHelper').text('');
			$('#homeworkEndDateHelper').text('');
			
			if($('#homeworkTitle').val()==''){
				$('#homeworkTitleHelper').text('제목을 입력하세요');
				$('#homeworkTitle').focus();
			} else if($('#homeworkContent').val()=='') {
				$('#homeworkContentHelper').text('내용을 입력하세요');
				$('#homeworkTitle').focus();
			} else if($('#homeworkStartDate').val()==''){
				$('#homeworkStartDateHelper').text('시작 날짜를 입력하세요');
				$('#homeworkStartDate').focus();				
			} else if($('#homeworkEndDate').val() <= $('#homeworkStartDate').val() ) {
				$('#homeworkEndDateHelper').text('마감 날짜는 시작날짜 이후여야 합니다');
				$('#homeworkEndDate').focus();
			} else {
				$('#modifyHomeworkForm').submit();
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
				<h1>과제 수정</h1>
				<div class="row">
					<div class="col-lg-12 col-md-12">
						<div class="card">
							<div class="card-body">
								<h4 class="card-title">과제 수정</h4>
								<div class="mt-2" style="height: auto; width: auto;">
									<!-- 테이블 넣는곳, 테이블 색깔 변경 ->class만 변경 -->
									<form action="${pageContext.request.contextPath}/teacher/homework/modifyHomework" id="modifyHomeworkForm" method="post">
										<table id="zero_config" class="table table-striped table-bordered">
											<tr>
												<td>제목</td>
												<td>
													<input type="text" name ="homeworkTitle" id="homeworkTitle" class="form-control" value="${homeworkOne.homeworkTitle}">
													<span id="homeworkTitleHelper" class="helper"></span>
												</td>
											</tr>
											<tr>
												<td>내용</td>
												<td>
													<textarea name=homeworkContent id="homeworkContent"class="form-control" rows ="8" cols="70"  placeholder="${homeworkOne.homeworkContent}">${homeworkOne.homeworkContent}</textarea>
													<span id="homeworkContentHelper" class="helper"></span>
													</td>
											</tr>
											<tr>
												<td>시작날짜</td>
												<td>
													<input type="datetime-local" id="homeworkStartDate" class="form-control" name="homeworkStartDate">
													<span id="homeworkStartDateHelper" class="helper"></span>	
												</td>
											</tr>
											<tr>
												<td>마감날짜</td>
												<td>
													<input type="datetime-local" id="homeworkEndDate" class="form-control" name="homeworkEndDate">
													<span id="homeworkEndDateHelper" class="helper"></span>
												</td>
											</tr>
										
										</table>
										<input type="hidden" name="homeworkNo" value="${homeworkOne.homeworkNo}" readonly="readonly">
										<button type ="button" id="modifyHomework" class="btn btn-outline-success btn-rounded"><i class="fas fa-check"></i> 수정 완료</button>
										<input type="reset" class="btn btn-outline-success btn-rounded" value="초기화">
									</form>
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
</html>