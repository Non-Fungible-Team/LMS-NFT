<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 반응형 웹 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<!-- title icon -->
<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/assets/images/favicon.png">
<title>Non-fungible LMS</title>
<link href="${pageContext.request.contextPath}/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/dist/css/style.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
</head>
<script>
	$('document').ready(function() {
		$("#navAside").load('${pageContext.request.contextPath}/include/navAside.jsp');
		
		// 유효성 검사
		$('#uploadHomework').click(function(){
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
				$('#addHomeworkForm').submit();
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
<h1>과제 입력</h1>
<div class="row">
					<div class="col-lg-12 col-md-12">
						<div class="card">
							<div class="card-body">
								<h4 class="card-title">과제 입력</h4>
								<div class="mt-2" style="height: auto; width: auto;">
									<!-- 테이블 넣는곳, 테이블 색깔 변경 ->class만 변경 -->
									<form action="${pageContext.request.contextPath}/teacher/homework/addHomework" method="post" id="addHomeworkForm">
										 <div class="table-responsive">
											<table id="zero_config" class="table table-striped table-bordered">
												<tr>
													<td>작성자</td>
													<td>
														<input type="text" class="form-control" name="memberId" value="${sessionLoginMember.memberId}">
													</td>
												</tr>
												<tr>
													<td>강의 번호</td>
													<td>
														<input type="text" class="form-control" name="lectureNo" value="${sessionLectureNo}">
													</td>
												</tr>
												<tr>
													<td>과제 제목</td>
													<td>
														<input type="text" id="homeworkTitle" class="form-control" name="homeworkTitle">
														<span id="homeworkTitleHelper" class="helper"></span>
													</td>
												</tr>
												<tr>
													<td>과제 내용</td>
													<td>
														<textarea name="homeworkContent" id="homeworkContent" class="form-control" rows="10" cols="70"></textarea>
														<span id="homeworkContentHelper" class="helper"></span>
													</td>
												</tr>
												<tr>
													<td>시작 날짜</td>
													<td>
														<input type="datetime-local" id="homeworkStartDate" size="20" name="homeworkStartDate">
														<span id="homeworkStartDateHelper" class="helper"></span>
													</td>
												</tr>
												<tr>
													<td>마감 날짜</td>
													<td>
														<input type="datetime-local" id="homeworkEndDate" size="20" name="homeworkEndDate">
														<span id="homeworkEndDateHelper" class="helper"></span>
													</td>
												</tr>
											</table>
										</div>
											<button type="button" id="uploadHomework" class="btn btn-outline-success btn-rounded">
											<i class="fas fa-check"></i> 과제 등록</button>
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