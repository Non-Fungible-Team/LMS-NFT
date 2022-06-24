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
<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/static/assets/images/favicon.png">
<title>StudentLecture List</title>
<link href="${pageContext.request.contextPath}/static/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/static/dist/css/style.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		
		$('#addStudentLecture').click(function(){
			if($('#memberId').val() == '-1') {
				alert('학생을 선택하세요');
			} else if($('#studentLectureJob').val() == '-1') {
				alert('취업 여부를 선택하세요');
			} else if($('#studentLectureLegistrationDate').val() == '') {
				alert('등록일을 입력하세요');
			} else {
				$('#addForm').submit();
			}
		});
		
	});	
</script>
</head>
	<script>
		$('document').ready(function(){
	    	$("#navAside").load('${pageContext.request.contextPath}/include/navAside.jsp');
		});
  	</script>
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
		            <h4 class="card-title">StudentLecture add</h4>
		            <br>
		            <div style="height:294px;">
		            	<form method="post" id="addForm" action="${pageContext.request.contextPath}/teacher/lecture/addStudentLectureAction">
                            <div class="form-group">
                               <!-- 강의 : <input type="text" name="lectureNo" class="form-control" id="lectureNo">  -->
                               학생 : <select name="memberId" id="memberId" class="form-control">
	                               		<option value="-1">학생 선택</option>
	                               		<option value="a">학생 아이디(학생 이름) 순으로 보여주기</option>
	                               		<option value="a">if문 돌려서 student memberId 가져오기</option>
                              		 </select>
                               취업 여부 : <select name="studentLectureJob" id="studentLectureJob" class="form-control">
		                               		<option value="-1">취업 여부 선택</option>
		                               		<option value="Y">Y</option>
		                               		<option value="N">N</option>
	                              	    </select>
                               등록일 : <input type="date" name="studentLectureLegistrationDate" class="form-control" id="studentLectureLegistrationDate">
                            </div>
                            <button type="button" class="btn btn-outline-success btn-rounded" id="addStudentLecture"><i class="fas fa-check"></i>입력</button>
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