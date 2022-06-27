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
<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/static/assets/images/favicon.png">
<title>Main Home</title>
<!-- CSS 링크 -->
<link href="${pageContext.request.contextPath}/static/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/static/dist/css/style.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
</head>
	<script>
	$('document').ready(function(){
    	$("#navAside").load('${pageContext.request.contextPath}/include/navAsideTeacher.jsp');
	});
  	</script>
<body>
<div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" 
data-sidebartype="full"  data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
	<div id="navAside"></div>
	<!-- header include(네비게이션바) -->
      <div class="page-wrapper">
      <!-- 접속한 강의 명 출력 부분 -->
       	<div class="page-breadcrumb">
	        <div class="row">
	            <div class="col-7 align-self-center">

	            </div>
	        </div>
	    </div>

	<!-- main화면 body start -->
	 <div class="container-fluid">
	<!-- 첫번쨰 문단 -->
	<div class="row">
		<div class="col-lg-12 col-md-12">
		    <div class="card">
		        <div class="card-body">
		        <c:if test="${not empty param.msg }">
		            <h1>임시 오류 메세지 : ${param.msg}</h1>
		        </c:if>
		            <h4 class="card-title">수강목록</h4>
		            <h6>강의명을 클릭하시면 강의 페이지로 이동합니다</h6>
		            <div class="mt-2" style="height:auto; width:auto;">
		            <!-- 테이블 넣는곳, 테이블 색깔 변경 ->class만 변경 -->
		            	<table id="zero_config" class="table table-striped table-bordered no-wrap">
		            		<thead>
	                            <tr>
	                                <th>과목명</th>
	                                <th>강의명</th>
	                                <th>수강시작날짜</th>
	                                <th>수료날짜</th>
	                                <th>강의실</th>
	                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="l" items="${lectureList}">
				                <tr>
				                   <td>${l.subjectName}</td>
				                   <td><a href="${pageContext.request.contextPath}/all/lectureNoController?lectureNo=${l.lectureNo}">${l.lectureName}</a></td>
				                   <td>${l.lectureStartDate}</td>
				                   <td>${l.lectureEndDate}</td>
				                   <td>${l.lectureRoomName}</td>
				                </tr>
                            </c:forEach>
                            </tbody>
		            	</table>
				                   <!-- 페이지 목록 표시 부분 -->
									<!-- 이전 목록 표시 -->
										<form method="get" action = "${pageContext.request.contextPath}/all/home">
												<c:if test="${currentPage>10}">
												 	<button type = "submit" value ="${currentPage-10}" name = "currentPage" class="btn btn-secondary" >이전목록</button>
												</c:if>
												<!-- 이전 부분 -->
												<c:if test="${currentPage>1}">
												 	<button type = "submit" value ="${currentPage-1}" name = "currentPage" class="btn btn-secondary" >이전</button>
												</c:if>
												<!-- 목록 사이 번호 표시 -->
												<c:forEach begin="1" end="10" step="1" var="i" varStatus="status">
														<c:if test="${ i< 5 && currentPage-(5-i)> 0 }">
															<button type = "submit" value ="${currentPage-(5-i)}" name = "currentPage" class="btn btn-light">${currentPage-(5-i)}</button>
														</c:if>
														<c:if test="${ i==5 }">
															<button type = "submit" value ="${currentPage}" name = "currentPage" class="btn btn-danger">${currentPage}</button>
														</c:if>
														<c:if test="${ i > 5 && currentPage+(i-5) <= lastPage }">
															<button type = "submit" value ="${currentPage+(i-5)}" name = "currentPage" class="btn btn-light">${currentPage+(i-5)}</button>
														</c:if>
												</c:forEach>
												<!-- 다음 부분 -->
												<c:if test="${currentPage< lastPage }">
											 		<button type = "submit" value ="${currentPage+1}" name = "currentPage" class="btn btn-secondary" >다음</button>
												</c:if>
												<!-- 다음목록 표시 -->
												<c:if test="${currentPage+10 <= lastPage }">
											 		<button type = "submit" value ="${currentPage+10}" name = "currentPage" class="btn btn-secondary">다음목록</button>
												</c:if>
										</form>
		             </div>   
		        </div>
		    </div>
		</div>
		<!-- 첫번쨰 끝 -->
		<!-- 2번째 문단 -->
		<div class="col-lg-12 col-md-6">
		    <div class="card">
		        <div class="card-body">
		            <h4 class="card-title">타이틀</h4>
		            <div style="height:294px;">
		            	<!-- 내용입력 칸 ex) 테이블 및 폼 -->
		            	<form class="mt-4" method="" action="#">
                            <div class="form-group">
                               입력 : <input type="text" class="form-control">
                               비번 : <input type="password" class="form-control" id="passtext" placeholder="Password">
                            </div>
                            <button type="button" class="btn btn-outline-success btn-rounded">
                          		<i class="fas fa-check"></i> 입력
                          	</button>
                        </form>
		            </div>     
		        </div>
		    </div>
		</div>
		<!-- 2번째 문단 끝 -->
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