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
<title>removeNotice</title>
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
	$('document').ready(function(){
		$('#uploadRemoveNotice').click(function(){
			
			if($('#inputRemoveNoticeNo').val() != $('#removeNoticeNo').val() || $('#inputRemoveNoticeNo').val() == ''){
				$('#removeNoticeNoHelper').text('입력한 번호를 다시 확인해주세요')
			} else {
				$('#removeNoticeForm').submit();
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
				            <h4 class="card-title">removeNotice</h4>
				            <div class="mt-2" style="height:auto; width:auto;">
								<form id="removeNoticeForm" action="${pageContext.request.contextPath}/manager/notice/removeNotice?noticeNo=${notice.noticeNo}" method="post">
									<table id="zero_config" class="table table-striped table-bordered no-wrap">
										<tr>
											<th>번호</th>
											<td><input type="text" name="noticeNo" id="removeNoticeNo" class="form-control" value="${notice.noticeNo}" readonly="readonly"></td>
										</tr>
										<tr>
											<th>제목을 확인해 주세요</th>
											<td>
												<input type="text" name="noticeTitle" class="form-control" value="${notice.noticeTitle}" readonly="readonly">
											</td>
										</tr>
										<tr>
											<th>삭제할 공지사항 번호 입력</th>
											<td>
												<input type="number" name="noticeNo" id="inputRemoveNoticeNo"class="form-control">
												<span id="removeNoticeNoHelper" class="helper"></span>
											</td>
										</tr>
									</table>
									<div>
					            		<button type="button" id="uploadRemoveNotice" class="btn btn-outline-danger btn-rounded">삭제(블라인드처리)</button>
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