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
<link rel="icon" type="image/png" sizes="16x16" href="assets/images/favicon.png">
<title>add Lecture</title>
<!-- CSS 링크 -->
<link href="assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="dist/css/style.min.css" rel="stylesheet">
<script src="assets/libs/jquery/dist/jquery.min.js"></script>
<script src="assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
<script>
	$(document).ready(function() {
		$('#addLectureBtn').click(function() {
			if('#subjectName').val( == ' '){
				alert('과목을 선택해주세요'); 
			}else if('#lectureName'.val( == ' ')){
				alert('강의명을 입력해주세요'); 
			}else if('#lectureStartDate'.val( == ' ')){
				alert('개강일을 선택해주세요'); 
			}else if('#lectureEndDate'.val( == ' ')){
				alert('수료일을 선택해주세요'); 
			}else if('#lectureRoom'.val( == ' ')){
				alert('강의실을 선택해주세요'); 
			}else if('#teacher'.val( == ' ')){
				alert('강사를 선택해주세요'); 
			}else{
				$('#addLectureForm').submit();
			}
		});
	});
</script>
</head>

<body>
<div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" 
data-sidebartype="full"  data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
	<!-- header include(네비게이션바) -->
	<div id="test"></div>
	<!-- main화면 body start -->
		<div class="col-lg-12 col-md-6">
		    <div class="card">
		        <div class="card-body">
		            <h4 class="card-title">강의 삽입</h4>
		            <div style="height:auto;">
		            	<form class="mt-4" method="post" id="addLectureForm" action="${pageContext.request.contextPath}/manager/lecture/addLecture">
                            <div class="form-group">
                               <div>
                               		과목명  
	                               <select id="subjectName">
	                               		<option value=" ">과목 선택</option>
		                               	<c:forEach var="s" items="${subjectList}">
		                               			<option value="${s.subjectName}">${s.subjectName}</option>
		                               	</c:forEach>
	                               </select>
                               </div>
                               <br>
                               	강의명  <input type="text" id="lectureName" class="form-control" placeholder="강의명 입력해주세요"><br>
                            	개강일
                            	<input type="date" id="lectureStartDate" class="form-control"><br>
                            	수료일
                            	<input type="date" id="lectureEndDate" class="form-control"><br>
                            	<div>
                            	강의실
                            	<select id="lectureRoom">
                               		<option value=" ">강의실 선택</option>
	                               	<c:forEach var="l" items="${lectureRoomList}">
	                               			<option value="${l.lectureRoom}">${l.lectureRoom}</option>
	                               	</c:forEach>
                               </select>
                               </div>
                               <br>
                            	강사
                            	<input type="text" id="teacher" class="form-control"><br>             
                            </div>
                            <button type="button" id="addLectureBtn" class="btn btn-outline-success btn-rounded">
                          		<i class="fas fa-check"></i>강의 입력
                          	</button>
                        </form>
		            </div>     
		        </div>
		    </div>
		</div>
	</div>
</body>
	<script>
    	$("#test").load('${pageContext.request.contextPath}/include/test.jsp');
  	</script>

    <script src="dist/js/app-style-switcher.js"></script>
    <script src="dist/js/feather.min.js"></script>
    <script src="assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
    <script src="dist/js/sidebarmenu.js"></script>
    <script src="dist/js/custom.min.js"></script>
    <script src="assets/extra-libs/c3/d3.min.js"></script>
    <script src="assets/extra-libs/c3/c3.min.js"></script>
    <script src="assets/libs/chartist/dist/chartist.min.js"></script>
    <script src="assets/libs/chartist-plugin-tooltips/dist/chartist-plugin-tooltip.min.js"></script>
    <script src="assets/extra-libs/jvector/jquery-jvectormap-2.0.2.min.js"></script>
    <script src="assets/extra-libs/jvector/jquery-jvectormap-world-mill-en.js"></script>
    <script src="dist/js/pages/dashboards/dashboard1.min.js"></script>
    
</html>