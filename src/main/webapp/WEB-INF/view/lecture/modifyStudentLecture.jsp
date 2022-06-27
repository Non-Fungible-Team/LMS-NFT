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
<title>StudentLecture</title>
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
		
		$('#modifyStudentLecture').click(function(){
			if($('#memberId').val() == '-1') {
				alert('학생을 선택하세요');
			} else if($('#studentLectureJob').val() == '-1') {
				alert('취업 여부를 선택하세요');
			} else if($('#studentLectureLegistrationDate').val() == '') {
				alert('등록일을 입력하세요');
			} else {
				$('#modifyForm').submit();
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
		            <h4 class="card-title">StudentLecture modify</h4>
		            <br>
		            <div style="height:294px;">
		            	<form method="post" id="modifyForm" action="${pageContext.request.contextPath}/teacher/lecture/modifyStudentLectureAction">
                            <div class="form-group">
                               강의 : <input type="text" name="lectureNo" class="form-control" id="lectureNo" value="${ studentLectureOne.lectureNo } (${ studentLectureOne.lectureName })">
                               학생 : <select name="memberId" id="memberId" class="form-control">
                               			<option value="-1">학생 선택</option>
                               			<c:forEach var ="s" items="${ studentTotalList }">
                               				<c:if test="${ s.memberId == studentLectureOne.memberId }">
					                        	<option value="${ s.memberId }" selected>${ studentLectureOne.memberId } (${ studentLectureOne.studentName })</option>
					                      	</c:if>
                               				<option value="${ s.memberId }">${ s.memberId } (${ s.studentName })</option>
                               			</c:forEach>
                              		 </select>
                               취업 여부 : <select name="studentLectureJob" id="studentLectureJob" class="form-control">
		                               		<option value="-1">취업 여부 선택</option>
		                               		<option value="Y">Y</option>
		                               		<option value="N">N</option>
	                              	    </select>
                               등록일 : <input type="date" name="studentLectureLegistrationDate" class="form-control" id="studentLectureLegistrationDate" value="${ studentLectureOne.studentLectureLegistrationDate }">
                               수료일 : <input type="date" name="studentLectureEndDate" class="form-control" id="studentLectureEndDate">
                               전체 성적 : <input type="number" name="studentLectureScore" class="form-control" id="studentLectureScore" value="${ studentLectureOne.studentLectureScore }">
                            </div>
                            <button type="button" class="btn btn-outline-success btn-rounded" id="modifyStudentLecture"><i class="fas fa-check"></i>수정</button>
                        </form>
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