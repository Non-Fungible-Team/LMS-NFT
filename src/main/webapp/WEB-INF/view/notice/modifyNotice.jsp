<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1"> <!-- 반응형 웹 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<!-- title icon -->
<link rel="icon" type="image/png" sizes="16x16" href="static/assets/images/favicon.png">
<title>modifyNotice</title>
<!-- CSS 링크 -->
<link href="static/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="static/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="static/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="static/dist/css/style.min.css" rel="stylesheet">
<script src="static/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="static/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="static/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js">
</script>
</head>
	<script>
		//유효성검사
		$('document').ready(function(){
			$('#uploadModifyNotice').click(function(){
					$('#modifyNoticeTitleHelper').text('');
					$('#modifyNoticeContentHelper').text('');
					$('#noticePrivilegeHelper').text('');
					
				if($('#modifyNoticeTitle').val() == ''){
					$('#modifyNoticeTitleHelper').text('제목을 입력하세요');
					$('#modifyNoticeTitle').focus();
				} else if($('#modifyNoticeContent').val() == ''){
					$('#modifyNoticeContentHelper').text('내용을 입력하세요');
					$('#modifyNoticeContent').focus();
				} else if($('#modifyNoticePrivilege').val() == '-1'){
					$('#modifyNoticePrivilegeHelper').text('읽기권한을 선택하세요');
					$('#modifyNoticePrivilege').focus();
				} else {
					$('#modifyNoticeForm').submit();
				}
			});
		});
		
	</script>
<body>
	<div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" 
data-sidebartype="full"  data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
	<!-- header include(네비게이션바) -->
	<div id="navAside"></div>
	    <div class="container p-5 my-5 border">
			<div class="row">
				<div class="col-lg-12 col-md-6">
				    <div class="card">
				        <div class="card-body">
				            <h4 class="card-title">modifyNotice</h4>
				            <div class="mt-2" style="height:auto; width:auto;">
								<form id="modifyNoticeForm" action="${pageContext.request.contextPath}/manager/notice/modifyNotice?noticeNo=${notice.noticeNo}" method="post">
									<table id="zero_config" class="table table-striped table-bordered no-wrap">
										<tr>
											<th>번호</th>
											<td>${notice.noticeNo}</td>
										</tr>
										<tr>
											<th>제목</th>
											<td>
												<input type="text" name="noticeTitle" id="modifyNoticeTitle" class="form-control" value="${notice.noticeTitle}">
												<span id="modifyNoticeTitleHelper" class="helper"></span>
											</td>
										</tr>
										<tr>
						    				<th>내용</th>
							    			<td height= 500px>
							    				<textarea rows="20" Cols="10" name="noticeContent" id="modifyNoticeContent" class="form-control">${notice.noticeContent}</textarea>
							    				<span id="modifyNoticeContentHelper" class="helper"></span>
							    			</td>
							    		</tr>
										<tr>
							    			<th>읽기권한</th>
							    			<td>
							    				<select name = "noticePrivilege" id="modifyNoticePrivilege">
							    					<option value="-1">::읽기권한::</option>
							    					<option value="4">학생이상</option>
							    					<option value="5">강사이상</option>
							    					<option value="6">운영자만</option>
							    				</select>
							    				<span id="modifyNoticePrivilegeHelper" class="helper"></span>
							    			</td>
							    		</tr>
									</table>
									<div>
					            		<button type="button" id="uploadModifyNotice" class="btn btn-outline-success btn-rounded">수정</button>
								        <a href="${pageContext.request.contextPath}/all/notice/getNoticeListByPage">
								        	<input type="button" class="btn btn-info" style="float: right" value="목록으로">
								        </a>
						        	</div>
								</form>
				             </div>   
				        </div>
				    </div>
				</div>
			</div>
		</div>
	</div>
</body>
	<script>
    	$("#navAside").load('${pageContext.request.contextPath}/include/navAside.jsp');
  	</script>

    <script src="static/dist/js/app-style-switcher.js"></script>
    <script src="static/dist/js/feather.min.js"></script>
    <script src="static/assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
    <script src="static/dist/js/sidebarmenu.js"></script>
    <script src="static/dist/js/custom.min.js"></script>
    <script src="static/assets/extra-libs/c3/d3.min.js"></script>
    <script src="static/assets/extra-libs/c3/c3.min.js"></script>
    <script src="static/assets/libs/chartist/dist/chartist.min.js"></script>
    <script src="static/assets/libs/chartist-plugin-tooltips/dist/chartist-plugin-tooltip.min.js"></script>
    <script src="static/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.min.js"></script>
    <script src="static/assets/extra-libs/jvector/jquery-jvectormap-world-mill-en.js"></script>
    <script src="static/dist/js/pages/dashboards/dashboard1.min.js"></script>
    
</html>