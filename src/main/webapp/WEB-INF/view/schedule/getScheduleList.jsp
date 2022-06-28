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
<title>getScheduleList</title>
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
		
		$("#navAside").load('${pageContext.request.contextPath}/include/navAside.jsp');
		
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
		            <h4 class="card-title">getScheduleList - 달력 형식으로 수정 필요</h4>
		            <br>
		            <a href="${pageContext.request.contextPath}/teacher/lecture/add########" class="btn btn-primary btn-rounded">
						<i class="fas fa-check"></i>입력
					</a>
		            <div class="mt-2" style="height:auto; width:auto;">
		            <!-- 테이블 넣는곳, 테이블 색깔 변경 ->class만 변경 -->
		            	<table id="zero_config" class="table table-striped table-bordered no-wrap">
		            		<thead>
	                            <tr>
					                <th>번호</th>
					                <th>날짜</th>
					                <th>일정 제목</th>
					                <th>내용</th>
					                <th>작성자</th>
					                <th>생성일</th>
					                <th>수정일</th>
					            </tr>
                            </thead>
                            <tbody>
				                <c:forEach var="s" items="${scheduleList}">
					                <tr>
					                   <td>${s.scheduleNo}</td>
					                   <td>${s.scheduleDate}</td>
					                   <td>
					                   		<a href="${pageContext.request.contextPath}/all/schedule/getScheduleOne?scheduleNo=${s.scheduleNo}">${s.scheduleTitle}</a>
					                   </td>
					                   <td>${s.scheduleContent}</td>
					                   <td>${s.memberId}</td>
					                   <td>${s.scheduleCreateDate}</td>
					                   <td>${s.scheduleUpdateDate}</td>
					                </tr>
					            </c:forEach>
                            </tbody>
		            	</table>
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