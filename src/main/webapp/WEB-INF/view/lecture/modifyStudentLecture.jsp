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
		
		$('#modifyStudentLecture').click(function(){
			
			$('#lectureNoHelper').text('');
			$('#memberIdHelper').text('');
			$('#studentLectureJobHelper').text('');
			$('#studentLectureLegistrationDateHelper').text('');
			
			if($('#memberId').val() == '-1') {
				$('#lectureNoHelper').text('강의를 선택하세요');
				$('#lectureNoHelper').focus('');
			} else if($('#memberId').val() == '-1') {
				$('#memberIdHelper').text('학생을 선택하세요');
				$('#memberIdHelper').focus();
			} else if($('#studentLectureJob').val() == '-1') {
				$('#studentLectureJobHelper').text('취업 여부를 선택하세요');
				$('#studentLectureJobHelper').focus();
			} else if($('#studentLectureLegistrationDate').val() == '') {
				$('#studentLectureLegistrationDateHelper').text('등록일을 입력하세요');
				$('#studentLectureLegistrationDateHelper').focus();
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
		            <div style="height:auto;">
		            	<form method="post" id="modifyForm" action="${pageContext.request.contextPath}/teacher/lecture/modifyStudentLectureAction">
                            <div class="form-group">
                               강의 :
                               <select name="lectureNo" id="lectureNo" class="form-control">
                           			<option value="-1">강의 선택</option>
                           			<c:forEach var ="ln" items="${ lectureNoNameList }">
                           				<c:if test="${ ln.lectureNo == studentLectureOne.lectureNo }">
		                        			<option value="${ studentLectureOne.lectureNo }" selected>${ studentLectureOne.lectureNo } (${ studentLectureOne.lectureName })</option>
				                      	</c:if>
                           				<c:if test="${ ln.lectureNo != studentLectureOne.lectureNo }">
                              				<option value="${ ln.lectureNo }">${ ln.lectureNo } (${ ln.lectureName })</option>
				                      	</c:if>
                           			</c:forEach>
                       		   </select>
                       		   <span id="lectureNoHelper" class="helper" style="color: red;"></span>
                       		   <br>
                               학생 : <select name="memberId" id="memberId" class="form-control">
                               			<option value="-1">학생 선택</option>
                               			<c:forEach var ="s" items="${ studentTotalList }">
                               				<c:if test="${ s.memberId == studentLectureOne.memberId }">
					                        	<option value="${ s.memberId }" selected>${ studentLectureOne.memberId } (${ studentLectureOne.studentName })</option>
					                      	</c:if>
                               				<c:if test="${ s.memberId != studentLectureOne.memberId }">
                               				<option value="${ s.memberId }">${ s.memberId } (${ s.studentName })</option>
					                      	</c:if>
                               			</c:forEach>
                              		 </select>
                              		 <span id="memberIdHelper" class="helper" style="color: red;"></span>
                              		 <br>
                               취업 여부 : <select name="studentLectureJob" id="studentLectureJob" class="form-control">
		                               		<option value="-1">취업 여부 선택</option>
		                               		<c:if test="${ studentLectureOne.studentLectureJob == 'Y' }">
			                        			<option value="Y" selected>Y</option>
			                        			<option value="N">N</option>
					                      	</c:if>
	                           				<c:if test="${ studentLectureOne.studentLectureJob == 'N' }">
	                           					<option value="Y">Y</option>
	                              				<option value="N" selected>N</option>
					                      	</c:if>
	                              	    </select>
	                              	    <span id="studentLectureJobHelper" class="helper" style="color: red;"></span>
	                              	    <br>
                               등록일 : <input type="date" name="studentLectureLegistrationDate" class="form-control" id="studentLectureLegistrationDate" value="${ studentLectureOne.studentLectureLegistrationDate }">
                               		  <span id="studentLectureLegistrationDateHelper" class="helper" style="color: red;"></span>
                               		  <br>
                               수료일 : <input type="date" name="studentLectureEndDate" class="form-control" id="studentLectureEndDate" value="${ studentLectureOne.studentLectureEndDate }">
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