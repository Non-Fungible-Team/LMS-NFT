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
				        
				            <h4 class="card-title">
				        		<a href = "${pageContext.request.contextPath}/all/schedule/getScheduleList?year=${ year }&month=${ month - 1 }" class = "btn btn-outline-secondary">이전 달</a>
				            	&nbsp;
				            	${ year }년 ${ month }월
				            	&nbsp;
				           		<a href = "${pageContext.request.contextPath}/all/schedule/getScheduleList?year=${ year }&month=${ month + 1 }" class = "btn btn-outline-secondary">다음 달</a>
				            </h4>
				            <br>
				            <a href="${pageContext.request.contextPath}/manager/schedule/addSchedule" class="btn btn-primary btn-rounded">
								<i class="fas fa-check"></i>입력
							</a>
							
				            <div class="mt-2" style="height:auto; width:auto;">
				            
				            	<table id="zero_config" class="table table-striped table-bordered no-wrap">
				            		<thead>
			                            <tr>
							                <th>일</th>
								 			<th>월</th>
								 			<th>화</th>
								 			<th>수</th>
								 			<th>목</th>
								 			<th>금</th>
								 			<th>토</th>
							            </tr>
		                            </thead>
		                            
		                            <tbody>
		                            
						                <tr>
											<c:forEach var="i" begin="1" end="${ totalTd }" step="1">
												<c:choose>
													<c:when test="${(i - startBlank) > 0 && i <= endDay+startBlank}">
														<td style="height : 110px;" width="10%">
															${i - startBlank}
															<div>
																<c:forEach var="s" items="${ scheduleListByMonth }">
																	<c:if test="${(s.scheduleDateDay) ==  (i - startBlank)}">
																		<div>
																			<a href="${pageContext.request.contextPath}/all/schedule/getScheduleOne?scheduleNo=${s.scheduleNo}">${s.scheduleTitle}</a>
																		</div>
																	</c:if>
																</c:forEach>
															</div>
														</td>
													</c:when>
													
													<c:when test="${(i - startBlank) < 1 }">
														<td> </td>
													</c:when>
													
													<c:when test="${i > endDay}">
														<td> </td>
													</c:when>
												</c:choose>
												
												<c:if test="${ i % 7 == 0}">
													</tr>
													<tr>
												</c:if>
											</c:forEach>
											</tr>
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