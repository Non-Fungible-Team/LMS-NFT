<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>Non-Fungible LMS</title>
<link href="${pageContext.request.contextPath}/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/dist/css/style.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		
		$("#navAside").load('${pageContext.request.contextPath}/include/navAside.jsp');
		
		$('#modifySchedule').click(function(){
			if($('#scheduleTitle').val() == '') {
				$('#scheduleTitleHelper').text('제목를 입력하세요');
				$('#scheduleTitleHelper').focus('');
			} else if($('#scheduleContent').val() == '') {
				$('#scheduleContentHelper').text('내용을 입력하세요');
				$('#scheduleContentHelper').focus('');
			} else if($('#scheduleDate').val() == '') {
				$('#scheduleDateHelper').text('날짜를 입력하세요');
				$('#scheduleDateHelper').focus('');
			} else {
				$('#modifyForm').submit();
			}
		});
		
	});	
</script>
</head>

<body>
<div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" 
data-sidebartype="full"  data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
	<!-- header include(네비게이션바) -->
    <div id="navAside"></div>
  
<div class="page-wrapper">
<div class="container-fluid">
	<div class="row">

		<div class="col-lg-12 col-md-6">
		    <div class="card">
		        <div class="card-body">
		            <h4 class="card-title">일정 수정</h4>
		            <div style="height:auto">
		            	<form method="post" id="modifyForm" class="mt-4" action="${pageContext.request.contextPath}/manager/schedule/modifyScheduleAction">
                            <div class="form-group">
                            		<input type="hidden" name="scheduleNo" id="scheduleNo" class="form-control" value="${ scheduleOne.scheduleNo }">
                               제목 : <input type="text" name="scheduleTitle" id="scheduleTitle" class="form-control" value="${ scheduleOne.scheduleTitle }">
                               <span id="scheduleTitleHelper" class="helper" style="color: red;"></span>
                               <br>
                               작성자 : <input type="text" name="scheduleMemberId" id="scheduleMemberId" class="form-control" value="${ sessionLoginMember.memberId }" readonly="readonly">
                               내용 : <textarea name="scheduleContent" id="scheduleContent" rows="5" cols="33" class="form-control" placeholder="${ scheduleOne.scheduleContent }">${ scheduleOne.scheduleContent }</textarea>
                               <span id="scheduleContentHelper" class="helper" style="color: red;"></span>
                               <br>
                               날짜 : <input type="date" name="scheduleDate" id="scheduleDate" class="form-control" value="${ scheduleOne.scheduleDate }">
                               <span id="scheduleDateHelper" class="helper" style="color: red;"></span>
                               <br>
                            </div>
                            <button type="button" class="btn btn-outline-success btn-rounded" id="modifySchedule">
                          		<i class="fas fa-check"></i> 수정
                          	</button>
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