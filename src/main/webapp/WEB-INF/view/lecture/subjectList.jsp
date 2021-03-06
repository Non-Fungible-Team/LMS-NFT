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
		
		$('#addSubject').click(function(){
			
			$('#subjectNameHelper').text('');
			$('#subjectContentHelper').text('');
			
			if($('#subjectName').val() == '') {
				$('#subjectNameHelper').text('과목 이름을 작성해주세요.');
				$('#subjectNameHelper').focus();
			} else if($('#subjectContent').val() == '') {
				$('#subjectContentHelper').text('과목 내용을 작성해주세요.');
				$('#subjectContentHelper').focus();
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
		            <h4 class="card-title">수강목록</h4>
		            <div class="mt-2" style="height:auto; width:auto;">
		            <!-- 테이블 넣는곳, 테이블 색깔 변경 ->class만 변경 -->
		            	<table id="zero_config" class="table table-striped table-bordered no-wrap">
		            		<thead>
	                            <tr>
					                <th>번호</th>
					                <th>과목 이름</th>
					                <th>내용</th>
					                <th>생성일</th>
					                <th>수정일</th>
					                <th></th>
					            </tr>
                            </thead>
                            <tbody>
				                <c:forEach var="s" items="${subjectList}">
					                <tr>
					                   <td>${s.subjectNo}</td>
					                   <td>${s.subjectName}</td>
					                   <td>${s.subjectContent}</td>
					                   <td>${s.subjectCreateDate}</td>
					                   <td>${s.subjectUpdateDate}</td>
					                   <td><a href="${pageContext.request.contextPath}/teacher/removeSubjectOne?subjectNo=${s.subjectNo}" class="btn btn-danger disabled">삭제</a></td>
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
		            <h4 class="card-title">subject add</h4>
		            <div style="height:294px;">
		            	<form method="post" id="addForm" class="mt-4" action="${pageContext.request.contextPath}/teacher/addSubjectOne">
                            <div class="form-group">
                               과목 이름 : <input type="text" name="subjectName" id="subjectName" class="form-control">
                               <span id="subjectNameHelper" class="helper" style="color: red;"></span>
                               <br>
                               내용 : <input type="text" name="subjectContent" id="subjectContent" class="form-control">
                               <span id="subjectContentHelper" class="helper" style="color: red;"></span>
                            </div>
                            <button type="button" class="btn btn-outline-success btn-rounded" id="addSubject">
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