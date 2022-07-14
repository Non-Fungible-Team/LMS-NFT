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
								<!-- noticeBlindList -->
								<c:if test="${sessionLoginMember.memberLevel >=6}">
									<div class="card-body">
									<h4 class="card-title">공지 블라인드(삭제) 목록</h4>
										<table id="zero_config" class="table table-striped table-bordered">
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
													<c:forEach var="n" items="${noticeBlindList}">
														<tr>
															<td>${n.noticeNo}</td>
															<td>${n.noticeTitle}</td>
															<td>${n.privilegeName}</td>
															<td>${n.memberId}</td>
															<td>${n.noticeCreateDate}</td>
														</tr>
													</c:forEach>
								                </tr>
				                            </tbody>
										</table>
										<div style="text-align: center;">
							            	<form method="get" action="${pageContext.request.contextPath}/manager/blind/blindList">
												<c:if test="${noticeBlindCurrentPage>1}">
													<button type="submit" class="btn btn-outline-warning btn-rounded" name="noticeBlindCurrentPage" value="${noticeBlindCurrentPage-1}">이전</button>
												</c:if>
												<!-- 목록 사이 번호 표시 -->
												<c:forEach begin="1" end="10" step="1" var="i" varStatus="status">
													<c:if test="${ i< 5 && noticeBlindCurrentPage-(5-i)> 0 }">
														<button type = "submit" value ="${noticeBlindCurrentPage-(5-i)}" name = "noticeBlindCurrentPage" class="btn btn-outline-info btn-rounded">${noticeBlindCurrentPage-(5-i)}</button>
													</c:if>
													<c:if test="${ i==5 }">
														<button type = "submit" value ="${noticeBlindCurrentPage}" name = "noticeBlindCurrentPage" class="btn btn-outline-danger btn-rounded">${noticeBlindCurrentPage}</button>
													</c:if>
													<c:if test="${ i > 5 && noticeBlindCurrentPage+(i-5) <= noticeBlindLastPage }">
														<button type = "submit" value ="${noticeBlindCurrentPage+(i-5)}" name = "noticeBlindCurrentPage" class="btn btn-outline-info btn-rounded">${noticeBlindCurrentPage+(i-5)}</button>
													</c:if>
												</c:forEach>
												<c:if test="${noticeBlindCurrentPage < noticeBlindLastPage}">
													<button type="submit" class="btn btn-outline-success btn-rounded" name="noticeBlindCurrentPage" value="${noticeBlindCurrentPage+1}">다음</button>
												</c:if>
											</form>
										</div>
									</div>
								</c:if>	
								
								<!-- suggestBlindList -->
								<div class="card-body">
									<h4 class="card-title">건의 블라인드(삭제) 목록</h4>
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
												<c:forEach var="s" items="${suggestBlindList}">
													<tr>
														<td>${s.suggestNo}</td>
														<td>${s.suggestStatus}</td>
														<td>
															<c:if test="${sessionLoginMember.memberLevel>=6 || sessionLoginMember.memberId == s.memberId || sessionLoginMember.memberLevel < 6 && s.suggestSecret =='N'}">
																<c:if test="${s.root != 0}">
																	┗【Re:】
																</c:if>
																${s.suggestTitle}
															</c:if>
															<c:if test="${sessionLoginMember.memberLevel < 6 && s.suggestSecret =='Y' && sessionLoginMember.memberId != s.memberId}">
																<c:if test="${s.root != 0}">
																	┗【Re:】
																</c:if>
																비밀글입니다.
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
						            	<form method="get" action="${pageContext.request.contextPath}/manager/blind/blindList">
											<c:if test="${suggestBlindCurrentPage>1}">
												<button type="submit" class="btn btn-outline-warning btn-rounded" name="suggestBlindCurrentPage" value="${suggestBlindCurrentPage-1}">이전</button>
											</c:if>
											<!-- 목록 사이 번호 표시 -->
											<c:forEach begin="1" end="10" step="1" var="i" varStatus="status">
												<c:if test="${ i< 5 && suggestBlindCurrentPage-(5-i)> 0 }">
													<button type = "submit" value ="${suggestBlindCurrentPage-(5-i)}" name = "suggestBlindCurrentPage" class="btn btn-outline-info btn-rounded">${suggestBlindCurrentPage-(5-i)}</button>
												</c:if>
												<c:if test="${ i==5 }">
													<button type = "submit" value ="${suggestBlindCurrentPage}" name = "suggestBlindCurrentPage" class="btn btn-outline-danger btn-rounded">${suggestBlindCurrentPage}</button>
												</c:if>
												<c:if test="${ i > 5 && suggestBlindCurrentPage+(i-5) <= suggestBlindLastPage }">
													<button type = "submit" value ="${suggestBlindCurrentPage+(i-5)}" name = "suggestBlindCurrentPage" class="btn btn-outline-info btn-rounded">${suggestBlindCurrentPage+(i-5)}</button>
												</c:if>
											</c:forEach>
											<c:if test="${suggestBlindCurrentPage < suggestBlindLastPage}">
												<button type="submit" class="btn btn-outline-success btn-rounded" name="suggestBlindCurrentPage" value="${suggestBlindCurrentPage+1}">다음</button>
											</c:if>
										</form>
									</div>
								</div>
								
								<!-- lectureBoardBlindList -->
					        	<div class="card-body">
			            			<h4 class="card-title">강의 게시판</h4>
				            		<table id="zero_config" class="table table-striped table-bordered">
				            			<thead>
				                            <tr>
				                                <th>강의번호</th>
				                                <th>강의이름</th>
												<th>제목</th>
												<th>작성자ID</th>
												<th>생성날짜</th>
				                            </tr>
			                            </thead>
			                            <tbody>
							                <tr>
												<c:forEach var="lb" items="${lectureBoardBlindList}">
													<tr>
														<td>${sessionLecture.lectureNo}</td>
														<td>${sessionLecture.lectureName}</td>
														<td>${lb.lectureBoardTitle}</td>
														<td>${lb.memberId}</td>
														<td>${lb.lectureBoardCreateDate}</td>
													</tr>
												</c:forEach>
							                </tr>
			                            </tbody>
					            	</table>
					            	<div style="text-align: center;">
						            	<form method="get" action="${pageContext.request.contextPath}/manager/blind/blindList">
											<c:if test="${lectureBoardBlindCurrentPage>1}">
												<button type="submit" class="btn btn-outline-warning btn-rounded" name="lectureBoardBlindCurrentPage" value="${lectureBoardBlindCurrentPage-1}">이전</button>
											</c:if>
											<!-- 목록 사이 번호 표시 -->
											<c:forEach begin="1" end="10" step="1" var="i" varStatus="status">
												<c:if test="${ i< 5 && lectureBoardBlindCurrentPage-(5-i)> 0 }">
													<button type = "submit" value ="${lectureBoardBlindCurrentPage-(5-i)}" name = "lectureBoardBlindCurrentPage" class="btn btn-outline-info btn-rounded">${lectureBoardBlindCurrentPage-(5-i)}</button>
												</c:if>
												<c:if test="${ i==5 }">
													<button type = "submit" value ="${lectureBoardBlindCurrentPage}" name = "lectureBoardBlindCurrentPage" class="btn btn-outline-danger btn-rounded">${lectureBoardBlindCurrentPage}</button>
												</c:if>
												<c:if test="${ i > 5 && lectureBoardBlindCurrentPage+(i-5) <= lectureBoardBlindLastPage }">
													<button type = "submit" value ="${lectureBoardBlindCurrentPage+(i-5)}" name = "lectureBoardBlindCurrentPage" class="btn btn-outline-info btn-rounded">${lectureBoardBlindCurrentPage+(i-5)}</button>
												</c:if>
											</c:forEach>
											<c:if test="${lectureBoardBlindCurrentPage < lectureBoardBlindLastPage}">
												<button type="submit" class="btn btn-outline-success btn-rounded" name="lectureBoardBlindCurrentPage" value="${lectureBoardBlindCurrentPage+1}">다음</button>
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