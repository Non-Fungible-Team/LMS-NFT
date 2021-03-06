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
<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/assets/images/favicon.png">
<title>Non-fungible LMS</title>
<link href="${pageContext.request.contextPath}/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/dist/css/style.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="http://ajax.googLeapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$('document').ready(function(){
		$('#navAside').load('${pageContext.request.contextPath}/include/navAside.jsp');
		
		let flag = true;
		$('#addFileUpload').click(function(){
			
			
			$('.homeworkSubmitFileList').each(function(){
				if($(this).val() == ''){
					flag = false;
				}
			});
				
			if(flag){
				$('#fileSection').append("<div class='card'><input type='file' class='homeworkSubmitFileList' name='homeworkSubmitFileList'/></div>")
			} else {
				alert('파일이 첨부되지 않은 list가 존재합니다')					
			}
		});
		
		
		$('#addHomeworkSubmit').click(function(){
			$('#homeworkSubmitTitleHelper').text('');
			$('#homeworkSubmitContentHelper').text('');
			
			if($('#homeworkSubmitTitle').val() == ''){
				$('#homeworkSubmitTitleHelper').text('제목을 입력하세요')
				$('#homeworkSubmitTitle').focus();
			} else if($('#homeworkSubmitContent').val() == '') {
				$('#homeworkSubmitContentHelper').text('내용을 입력하세요');
				$('#homeworkSubmitContent').focus();
			} else {
				$('.homeworkSubmitFileList').each(function(){
					if($(this).val() == ''){
						flag = false;
					}
				});
				if(flag){
					$('#addHomeworkSubmitFileForm').submit();
				} else {
					$('#addFileUploadHelper').text('파일을 첨부해 주세요');
					$('#addFileUpload').focus();
				}
			}
		});
	});
</script>
</head>
<script>
</script>
<body>
	<div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full" data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
		<!-- header include(네비게이션바) -->
		<div id="navAside"></div>
		<div class="page-wrapper">
			<div class="container-fluid">
				<h1>과제 제출</h1>
				<div class="row">
					<div class="col-lg-12 col-md-12">
						<div class="card">
							<div class="card-body">
								<h4 class="card-title">과제 제출</h4>
								<div class="mt-2" style="height: auto; width: auto;">
									<form action="${pageContext.request.contextPath}/student/homework/addHomeworkSubmit" method="post" id="addHomeworkSubmitFileForm" enctype="multipart/form-data">
										 <div class="table-responsive">
											<table id="zero_config" class="table table-striped table-bordered">
												<tr>
													<td>작성자</td>
													<td>
														<input type="text" name="memberId" class="form-control" value="${sessionLoginMember.memberId}" readonly="readonly">
													</td>
												</tr>										
												<tr>
													<td>과제번호</td>
													<td>
														<input type="text" name="homeworkNo" class="form-control" value="${param.homeworkNo}" readonly="readonly">
													</td>
												</tr>										
												<tr>
													<td>강의번호</td><td><input type="text" class="form-control" name="lectureNo" value="${sessionLectureNo}" readonly="readonly"></td>
												</tr>										
												<tr>
													<td>과제제목</td>
													<td>
														<input type="text" name="homeworkSubmitTitle" class="form-control" id="homeworkSubmitTitle">
														<span id="homeworkSubmitTitleHelper" class="helper"></span>	
													</td>
												</tr>										
												<tr>
													<td>과제내용</td>
													<td>
														<textarea name="homeworkSubmitContent" id="homeworkSubmitContent" class="form-control" rows="10" cols="70"></textarea>
														<span id ="homeworkSubmitContentHelper" class="helper"></span>
													</td>
												</tr>										
											</table>
										</div>
										<button type="button" class="btn btn-outline-success btn-rounded" id="addFileUpload">파일업로드</button>
										<div id="fileSection">
											<span id ="addFileUploadHelper" class="helper"></span>
										</div>
										<!-- 파일업로드 input 추가될 영역 -->
										<hr>
										<button type="button" class="btn btn-outline-success btn-rounded" id="addHomeworkSubmit"><i class="fas fa-check"></i>제출</button>
										<button type="button" class="btn btn-outline-success btn-rounded float-right" onclick="location.href='${pageContext.request.contextPath}/all/homework/getHomeworkListByPage'">목록</button>
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