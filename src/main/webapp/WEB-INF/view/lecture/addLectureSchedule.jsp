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
		
		//유효성 검사
		$('#addLectureSchedule').click(function(){
			$('#lectureScheduleHelper').text('');
			$('#lectureScheduleStartDateHelper').text('');
			$('#lectureScheduleEndDateHelper').text('');
			
			//시간비교를 위한 날짜 비교 코드
			//시간 저장 변수 생성
			var a=$('#lectureScheduleStartDate').val();
			var b=$('#lectureScheduleEndDate').val();
			//: 단위로 문자열 분할
			var aa1=a.split(":");
			var aa2=b.split(":");
			//2022년 1월1일로 날짜 고정 -> 날짜 같으면 시간 비교
			var d1=new Date(parseInt("2022",10),(parseInt("01",10))-1,parseInt("01",10),parseInt(aa1[0],10),parseInt(aa1[1],10),parseInt(00,10));
			var d2=new Date(parseInt("2022",10),(parseInt("01",10))-1,parseInt("01",10),parseInt(aa2[0],10),parseInt(aa2[1],10),parseInt(00,10));
			//시간을 숫자값으로 변환
			var dd1=d1.valueOf();
			var dd2=d2.valueOf();
			console.log(dd1);
			console.log(dd2);
			console.log(dd2-dd1);
			
			if($('#lectureScheduleDate').val() == '') {
				$('#lectureScheduleHelper').text('날짜를 선택해주세요');
				$('#lectureScheduleDate').focus();
			} else if($('#lectureScheduleStartDate').val() == '') {
				$('#lectureScheduleStartDateHelper').text('수업시작시간을 선택해주세요');
				$('#lectureScheduleStartDate').focus();
			} else if(dd1>dd2 || ((dd2-dd1) < 3600000) ) {
				$('#lectureScheduleEndDateHelper').text('시작시간 1시간 이후로 수업종료시간을 선택해주세요');
				$('#lectureScheduleEndDate').focus();
			}else {
				$('#addForm').submit();
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
		            <h4 class="card-title">시간표 추가</h4>
		            <div style="height:auto">
		            	<form method="post" id="addForm" class="mt-4" action="${pageContext.request.contextPath}/manager/lecture/addLectureScheduleAction">
                            <div class="form-group">
                            	<input type="hidden" name="lectureNo" id="lectureNo" value="${ lectureNo }">
                               날짜 : <input type="date" name="lectureScheduleStartDay" id="lectureScheduleDate" class="form-control">
                               ~ <input type="date" name="lectureScheduleEndDay" id="lectureScheduleDate" class="form-control">
                               <span id="lectureScheduleHelper" class="helper" style="color: red;"></span>
                               <br>
                               수업 시작 시간 : <input type="time" name="lectureScheduleStartDate" id="lectureScheduleStartDate" class="form-control">
                               <span id="lectureScheduleStartDateHelper" class="helper" style="color: red;"></span>
                               <br>
                               수업 종료 시간 : <input type="time" name="lectureScheduleEndDate" id="lectureScheduleEndDate" class="form-control">
                               <span id="lectureScheduleEndDateHelper" class="helper" style="color: red;"></span>
                            </div>
                            <button type="button" class="btn btn-outline-success btn-rounded" id="addLectureSchedule">
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