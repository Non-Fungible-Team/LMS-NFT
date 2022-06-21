<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <!-- 타이틀 아이콘 -->
<link rel="icon" type="image/png" sizes="16x16" href="assets/images/favicon.png">
<title>getLectureByPage</title>
<link href="assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="dist/css/style.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">

</head>
<body>
<div class="container">
   <div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full" data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
       <div class="page-wrapper">
           <!-- ============================================================== -->
           <!-- Bread crumb and right sidebar toggle -->
           <!-- ============================================================== -->
           <div class="page-breadcrumb">
               <div class="row">
                   <div class="col-7 align-self-center">
                       <h4 class="page-title text-truncate text-dark font-weight-medium mb-1">Lecture List</h4>
                   </div>
               </div>
           </div>
           
           <div class="container-fluid">
               <!-- Start Page Content -->
				
               <div class="row">
                   <div class="col-12">
                       <div class="card"> 
                         <div class="table-responsive">
                        	 <a href="${pageContext.request.contextPath}/" class="btn btn-outline-dark">강의 입력</a>
                             <table id="zero_config" class="table table-striped table-bordered no-wrap">
                                 <thead>
                                     <tr>
                                         <th>강의번호</th>
                                         <th>과목</th>
                                         <th>강의명</th>
                                         <th>개강일</th>
                                         <th>수료일</th>
                                         <th>강의실</th>
                                         <th>생성날짜</th>
                                     </tr>
                                 </thead>
                                 <tbody>
                                   <c:forEach var="l" items="${lectureList}">
					                <tr>
					                   <td>${l.lectureNo}</td>
					                   <td>${l.subjectName}</td>
					                   <td>${l.lectureName}</td>
					                   <td>${l.lectureStartDate}</td>
					                   <td>${l.lectureEndDate}</td>
					                   <td>${l.lectureroomName}</td>
					                   <td>${l.lectureCreateDate}</td>
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
 <script src="assets/libs/jquery/dist/jquery.min.js"></script>
 <script src="assets/libs/popper.js/dist/umd/popper.min.js"></script>
 <script src="assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
 <!-- apps -->
 <!-- apps -->
 <script src="dist/js/app-style-switcher.js"></script>
 <script src="dist/js/feather.min.js"></script>
 <script src="assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
 <script src="dist/js/sidebarmenu.js"></script>
 <!--Custom JavaScript -->
 <script src="dist/js/custom.min.js"></script>
 <!--This page JavaScript -->
 <script src="assets/extra-libs/c3/d3.min.js"></script>
 <script src="assets/extra-libs/c3/c3.min.js"></script>
 <script src="assets/libs/chartist/dist/chartist.min.js"></script>
 <script src="assets/libs/chartist-plugin-tooltips/dist/chartist-plugin-tooltip.min.js"></script>
 <script src="assets/extra-libs/jvector/jquery-jvectormap-2.0.2.min.js"></script>
 <script src="assets/extra-libs/jvector/jquery-jvectormap-world-mill-en.js"></script>
 <script src="dist/js/pages/dashboards/dashboard1.min.js"></script>
</body>
</html>