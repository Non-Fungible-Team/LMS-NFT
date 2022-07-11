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
		
		$('#updateLectureSchedule').click(function(){
			
			$('#lectureScheduleDateHelper').text('');
			$('#lectureScheduleStartDateHelper').text('');
			$('#lectureScheduleEndDateHelper').text('');
			
			if($('#lectureScheduleDate').val() == '') {
				$('#lectureScheduleDateHelper').text('날짜를 입력하세요');
				$('#lectureScheduleDateHelper').focus();
			} else if($('#lectureScheduleStartDate').val() == '') {
				$('#lectureScheduleStartDateHelper').text('수업 시작 시간을 입력하세요');
				$('#lectureScheduleStartDateHelper').focus();
			} else if($('#lectureScheduleEndDate').val() == '') {
				$('#lectureScheduleEndDateHelper').text('수업 종료 시간을 입력하세요');
				$('#lectureScheduleEndDateHelper').focus();
			} else {
				$('#updateForm').submit();
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
		            <h4 class="card-title">시간표 수정 ${ lectureScheduleOne.lectureNo }</h4>
		            <div style="height:auto">
		            	<form method="post" id="updateForm" class="mt-4" action="${pageContext.request.contextPath}/manager/lecture/modifyLectureScheduleAction">
                            <div class="form-group">
                            	<input type="hidden" name="lectureNo" id="lectureNo" value="${ lectureScheduleOne.lectureNo }">
                               날짜 : <input type="date" name="lectureScheduleDate" id="lectureScheduleDate" class="form-control" value="${ lectureScheduleOne.lectureScheduleDate }">
                               <span id="lectureScheduleDateHelper" class="helper" style="color: red;"></span>
                               <br>
                               수업 시작 시간 : <input type="time" name="lectureScheduleStartDate" id="lectureScheduleStartDate" class="form-control" value="${ lectureScheduleOne.lectureScheduleStartDate }">
                               <span id="lectureScheduleStartDateHelper" class="helper" style="color: red;"></span>
                               <br>
                               수업 종료 시간 : <input type="time" name="lectureScheduleEndDate" id="lectureScheduleEndDate" class="form-control" value="${ lectureScheduleOne.lectureScheduleEndDate }">
                               <span id="lectureScheduleEndDateHelper" class="helper" style="color: red;"></span>
                            </div>
                            <button type="button" class="btn btn-outline-success btn-rounded" id="updateLectureSchedule">
                          		<i class="fas fa-check"></i> 입력
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