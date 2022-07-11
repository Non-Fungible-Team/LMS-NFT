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
		
		$('#addLectureRoom').click(function() {
			
			$('#lectureRoomNameHelper').text('');
			$('#lectureRoomLocationHelper').text('');
			$('#lectureRoomPeopleHelper').text('');		
			
			if($('#lectureRoomName').val() == '') {
				$('#lectureRoomNameHelper').text('강의실 이름을 입력하세요');
				$('#lectureRoomNameHelper').focus();
			} else if($('#lectureRoomLocation').val() == '') {
				$('#lectureRoomLocationHelper').text('강의실 위치를 입력하세요');
				$('#lectureRoomLocationHelper').focus();
			} else if($('#lectureRoomPeople').val() == '') {
				$('#lectureRoomPeopleHelper').text('강의실 인원을 입력하세요');	
				$('#lectureRoomPeopleHelper').focus();
			} else {
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
		            <h4 class="card-title">lectureRoomList</h4>
		            <div class="mt-2" style="height:auto; width:auto;">
		            <!-- 테이블 넣는곳, 테이블 색깔 변경 ->class만 변경 -->
		            	<table id="zero_config" class="table table-striped table-bordered no-wrap">
		            		<thead>
					            <tr>
					                <th>room name</th>
					                <th>room location</th>
					                <th>room people</th>
					                <th></th>
					            </tr>
					        </thead>
					        <tbody>
					            <c:forEach var="r" items="${lectureRoomList}">
					                <tr>
					                   <td>${r.lectureRoomName}</td>
					                   <td>${r.lectureRoomLocation}</td>
					                   <td>${r.lectureRoomPeople}</td>
					                   <td><a href="${pageContext.request.contextPath}/teacher/lecture/removeLectureRoom?lectureRoomName=${r.lectureRoomName}" class="btn btn-danger disabled">삭제</a></td>
					                </tr>
					            </c:forEach>
					        </tbody>
		            	</table>
		             </div>   
		        </div>
		    </div>
		</div>
		
		
		<div class="col-lg-12 col-md-6">
		    <div class="card">
		        <div class="card-body">
		            <h4 class="card-title">lectureRoom add</h4>
		            <br>
		            <div style="height:294px;">
		            	<form method="post" id="addForm" action="${pageContext.request.contextPath}/teacher/lecture/addLectureRoom">
                            <div class="form-group">
                               lectureRoom name : <input type="text" name="lectureRoomName" class="form-control" id="lectureRoomName">
                               <span id="lectureRoomNameHelper" class="helper" style="color: red;"></span>
                               <br>
                               lectureRoom location : <input type="text" name="lectureRoomLocation" class="form-control" id="lectureRoomLocation">
                               <span id="lectureRoomLocationHelper" class="helper" style="color: red;"></span>
                               <br>
                               lectureRoom people : <input type="number" name="lectureRoomPeople" class="form-control" id="lectureRoomPeople">
                               <span id="lectureRoomPeopleHelper" class="helper" style="color: red;"></span>
                            </div>
                            <button type="button" class="btn btn-outline-success btn-rounded" id="addLectureRoom"><i class="fas fa-check"></i>입력</button>
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