<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 반응형 웹 -->
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
</head>
<script>
	$('document').ready(function() {
		$("#navAside").load('${pageContext.request.contextPath}/include/navAside.jsp');
		});
</script>
<body>
	<div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full" data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
		<!-- header include(네비게이션바) -->
		<div id="navAside"></div>
		<div class="page-wrapper">
			<div class="container-fluid">
				<!-- main화면 body start -->
				<!-- 첫번쨰 문단 -->
				<div class="row">
					<div class="col-lg-12 col-md-12">
						<div class="card">
							<div class="mt-2" style="height: auto; width: auto;">
								<!-- 운영자만 보이는 건의 답변전 목록테이블 -->
								<c:if test="${sessionLoginMember.memberLevel >=6 }">
									<div class="card-body">
										<h4 class="card-title">건의 답변전 목록</h4>
										<table id="zero_config" class="table table-striped table-bordered">
											<thead>
												<tr>
													<th>번호</th>
													<th>답변상태</th>
													<th>제목</th>
													<th>작성자ID</th>
													<th>비밀글여부</th>
													<th>생성날짜</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<c:forEach var="s" items="${rootNullSuggestList}">
														<tr>
															<td>${s.suggestNo}</td>
															<td>${s.suggestStatus}</td>
															<td>
																<a href="${pageContext.request.contextPath}/all/suggest/getSuggestOne?suggestNo=${s.suggestNo}">${s.suggestTitle}</a>
															</td>
															<td>${s.memberId}</td>
															<td>${s.suggestSecret}</td>
															<td>${s.suggestCreateDate}</td>
														</tr>
													</c:forEach>
												</tr>
											</tbody>
										</table>
										<div style="text-align: center;">
							            	<form method="get" action="${pageContext.request.contextPath}/all/suggest/getSuggestListByPage">
												<c:if test="${rootNullCurrentPage>1}">
													<button type="submit" class="btn btn-outline-warning btn-rounded" name="rootNullCurrentPage" value="${rootNullCurrentPage-1}">이전</button>
												</c:if>
												<!-- 목록 사이 번호 표시 -->
												<c:forEach begin="1" end="10" step="1" var="i" varStatus="status">
													<c:if test="${ i< 5 && rootNullCurrentPage-(5-i)> 0 }">
														<button type = "submit" value ="${rootNullCurrentPage-(5-i)}" name = "rootNullCurrentPage" class="btn btn-outline-info btn-rounded">${rootNullCurrentPage-(5-i)}</button>
													</c:if>
													<c:if test="${ i==5 }">
														<button type = "submit" value ="${rootNullCurrentPage}" name = "rootNullCurrentPage" class="btn btn-outline-danger btn-rounded">${rootNullCurrentPage}</button>
													</c:if>
													<c:if test="${ i > 5 && rootNullCurrentPage+(i-5) <= rootNullLastPage }">
														<button type = "submit" value ="${rootNullCurrentPage+(i-5)}" name = "rootNullCurrentPage" class="btn btn-outline-info btn-rounded">${rootNullCurrentPage+(i-5)}</button>
													</c:if>
												</c:forEach>
												<c:if test="${rootNullCurrentPage<rootNullLastPage}">
													<button type="submit" class="btn btn-outline-success btn-rounded" name="rootNullCurrentPage" value="${rootNullCurrentPage+1}">다음</button>
												</c:if>
											</form>
										</div>
									</div>
								</c:if>							
								
								<!-- 모두가 보이는 건의사항 목록 -->
								<div class="card-body">
									<h4 class="card-title">건의게시판</h4>
									<a href="${pageContext.request.contextPath}/all/suggest/addSuggest">
										<input type="button" class="btn btn-success" style="float: right" value="건의사항입력">
									</a>
									<table id="zero_config" class="table table-striped table-bordered">
										<thead>
											<tr>
												<th>번호</th>
												<th>답변상태</th>
												<th>제목</th>
												<th>작성자ID</th>
												<th>비밀글여부</th>
												<th>생성날짜</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<c:forEach var="s" items="${suggestList}">
													<tr>
														<td>${s.suggestNo}</td>
														<td>${s.suggestStatus}</td>
														<td>
															<c:if test="${sessionLoginMember.memberLevel>=6 || sessionLoginMember.memberId == s.memberId || sessionLoginMember.memberLevel < 6 && s.suggestSecret =='N'}">
																<c:if test="${s.root != 0}">
																	┗【Re:】
																</c:if>
																<a href="${pageContext.request.contextPath}/all/suggest/getSuggestOne?suggestNo=${s.suggestNo}">${s.suggestTitle}</a>
															</c:if>
															<c:if test="${sessionLoginMember.memberLevel < 6 && s.suggestSecret =='Y' && sessionLoginMember.memberId != s.memberId}">
																<c:if test="${s.root != 0}">
																	┗【Re:】
																</c:if>
																<div>비밀글입니다.</div>
															</c:if>
																
														</td>
														<td>${s.memberId}</td>
														<td>${s.suggestSecret}</td>
														<td>${s.suggestCreateDate}</td>
													</tr>
												</c:forEach>
											</tr>
										</tbody>
									</table>
									<div style="text-align: center;">
						            	<form method="get" action="${pageContext.request.contextPath}/all/suggest/getSuggestListByPage">
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