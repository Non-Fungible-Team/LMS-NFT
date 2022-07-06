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
<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/assets/images/favicon.png">
<title>getNoticeListByPage</title>
<!-- CSS 링크 -->
<link href="${pageContext.request.contextPath}/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/dist/css/style.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>

</head>
<script>
	$('document').ready(function(){
	    $("#navAside").load('${pageContext.request.contextPath}/include/navAside.jsp');
	});
</script>
<body>
	<div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full"  data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
		<!-- header include(네비게이션바) -->
		<div id="navAside"></div>
		<div class="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12 col-md-12">
				    	<div class="card">
				        	<div class="card-body">
			            		<div>
			            			<h4 class="card-title">공지사항</h4>
			            			<c:if test="${sessionLoginMember.memberLevel >= 6}">
					            		<div>
									        <a href="${pageContext.request.contextPath}/manager/notice/addNotice">
									        	<input type="button" class="btn btn-success" style="float: right" value="공지사항입력">
									        </a>
								        </div>
							        </c:if>
						   		</div>
				          		<div class="mt-2" style="height:auto; width:100%;">
				            		<table id="zero_config" class="table table-striped table-bordered no-wrap">
				            			<thead>
				                            <tr>
				                                <th>번호</th>
												<th>제목</th>
												<th>읽기권한</th>
												<th>작성자ID</th>
												<th>생성날짜</th>
				                            </tr>
			                            </thead>
			                            <tbody>
							                <tr>
												<c:forEach var="n" items="${noticeList}">
													<tr>
														<td>${n.noticeNo}</td>
														<td><a href="${pageContext.request.contextPath}/all/notice/getNoticeOne?noticeNo=${n.noticeNo}">${n.noticeTitle}</a></td>
														<td>${n.privilegeName}</td>
														<td>${n.memberId}</td>
														<td>${n.noticeCreateDate}</td>
													</tr>
												</c:forEach>
							                </tr>
			                            </tbody>
					            	</table>
					            	<div style="text-align: center;">
						            	<form method="get" action="${pageContext.request.contextPath}/all/notice/getNoticeListByPage">
											<c:if test="${currentPage>1}">
												<button type="submit" class="btn btn-outline-warning btn-rounded" name="currentPage" value="${currentPage-1}">이전</button>
											</c:if>
											<!-- 목록 사이 번호 표시 -->
											<c:forEach begin="1" end="10" step="1" var="i" varStatus="status">
												<c:if test="${ i< 5 && currentPage-(5-i)> 0 }">
													<button type = "submit" value ="${currentPage-(5-i)}" name = "currentPage" class="btn btn-outline-info btn-rounded">${currentPage-(5-i)}</button>
												</c:if>
												<c:if test="${ i==5 }">
													<button type = "submit" value ="${currentPage}" name = "currentPage" class="btn btn-outline-danger btn-rounded">${currentPage}</button>
												</c:if>
												<c:if test="${ i > 5 && currentPage+(i-5) <= lastPage }">
													<button type = "submit" value ="${currentPage+(i-5)}" name = "currentPage" class="btn btn-outline-info btn-rounded">${currentPage+(i-5)}</button>
												</c:if>
											</c:forEach>
											<c:if test="${currentPage<lastPage}">
												<button type="submit" class="btn btn-outline-success btn-rounded" name="currentPage" value="${currentPage+1}">다음</button>
											</c:if>
										</form>
									</div>
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
