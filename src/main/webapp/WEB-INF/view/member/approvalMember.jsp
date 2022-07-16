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
<link rel="icon" type="image/png" sizes="16x16" href="/lms/assets/images/favicon.png">
<title>Non-Fungible LMS</title>
<link href="/lms/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="/lms/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="/lms/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="/lms/dist/css/style.min.css" rel="stylesheet">
<script src="/lms/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="/lms/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="/lms/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
</head>
<script>
	//navAside.jsp include 부분
	$('document').ready(function() {
		$("#navAside").load('/lms/include/navAside.jsp');
		});
</script>
<body>
	<div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full" data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
		<!-- header include(네비게이션바) -->
		<div id="navAside"></div>
		<div class="page-wrapper">
			<div class="container-fluid">
				<!-- main화면 body start -->
				<!-- 가입 대기 학생 목록 -->
				<div class="row">
					<div class="col-lg-12 col-md-12">
						<div class="card">
							<div class="mt-2" style="height: auto; width: auto;">
									<div class="card-body">
										<h4 class="card-title">가입 대기 학생 목록</h4>
										<table id="zero_config" class="table table-striped table-bordered">
											<thead>
												<tr>
													<th>아이디</th>
													<th>이름</th>
													<th>이메일</th>
													<th>전체 주소</th>
													<th>가입 요청 날짜</th>
													<th>승인 버튼</th>
												</tr>
											</thead>
											<tbody>
													<c:forEach var="s" items="${studentList}">
														<tr>
															<td>${s.memberId}</td>
															<td>${s.studentName}</td>
															<td>${s.studentEmail}</td>
															<td>${s.addrDetail}</td>
															<td>${s.memberCreateDate}</td>
															<td>
																<a type="button" class="btn btn-outline-success"  href="${pageContext.request.contextPath}/manager/member/approvalMemberAction?memberId=${s.memberId}&memberLevel=${s.memberLevel}">가입 승인</a>
															</td>
														</tr>
													</c:forEach>													
											</tbody>
										</table>
										<div style="text-align: center;">
							            	<form method="get" action="/lms/manager/member/approvalMember">
												<!-- 목록 사이 번호 표시 -->
												<c:if test="${studentCurrentPage>1}">
													<button type="submit" class="btn btn-outline-warning btn-rounded" name="studentCurrentPage" value="${studentCurrentPage-1}">이전</button>
												</c:if>
												<!-- 목록 사이 번호 표시 -->
												<c:forEach begin="1" end="10" step="1" var="i" varStatus="status">
													<c:if test="${ i< 5 && studentCurrentPage-(5-i)> 0 }">
														<button type = "submit" value ="${studentCurrentPage-(5-i)}" name = "studentCurrentPage" class="btn btn-outline-info btn-rounded">${studentCurrentPage-(5-i)}</button>
													</c:if>
													<c:if test="${ i==5 }">
														<button type = "submit" value ="${studentCurrentPage}" name = "studentCurrentPage" class="btn btn-outline-danger btn-rounded">${studentCurrentPage}</button>
													</c:if>
													<c:if test="${ i > 5 && studentCurrentPage+(i-5) <= studentLastPage }">
														<button type = "submit" value ="${studentCurrentPage+(i-5)}" name = "studentCurrentPage" class="btn btn-outline-info btn-rounded">${studentCurrentPage+(i-5)}</button>
													</c:if>
												</c:forEach>
												<c:if test="${studentCurrentPage<studentLastPage}">
													<button type="submit" class="btn btn-outline-success btn-rounded" name="studentCurrentPage" value="${studentCurrentPage+1}">다음</button>
												</c:if>												
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				<!-- 가입 대기 강사 목록 -->
				<div class="row">
					<div class="col-lg-12 col-md-12">
						<div class="card">
							<div class="mt-2" style="height: auto; width: auto;">
									<div class="card-body">
										<h4 class="card-title">가입 대기 강사 목록</h4>
										<table id="zero_config" class="table table-striped table-bordered">
											<thead>
												<tr>
													<th>아이디</th>
													<th>이름</th>
													<th>이메일</th>
													<th>전체 주소</th>
													<th>가입 요청 날짜</th>
													<th>승인 버튼</th>
												</tr>
											</thead>
											<tbody>
													<c:forEach var="t" items="${teacherList}">
														<tr>
															<td>${t.memberId}</td>
															<td>${t.teacherName}</td>
															<td>${t.teacherEmail}</td>
															<td>${t.addrDetail}</td>
															<td>${t.memberCreateDate}</td>
															<td>
																<a type="button" class="btn btn-outline-success"  href="${pageContext.request.contextPath}/manager/member/approvalMemberAction?memberId=${t.memberId}&memberLevel=${t.memberLevel}">가입 승인</a>
															</td>
														</tr>
													</c:forEach>
											</tbody>
										</table>
										<div style="text-align: center;">
							            	<form method="get" action="/lms/manager/member/approvalMember">
												<c:if test="${teacherCurrentPage>1}">
													<button type="submit" class="btn btn-outline-warning btn-rounded" name="teacherCurrentPage" value="${teacherCurrentPage-1}">이전</button>
												</c:if>
												<!-- 목록 사이 번호 표시 -->
												<c:forEach begin="1" end="10" step="1" var="i" varStatus="status">
													<c:if test="${ i< 5 && teacherCurrentPage-(5-i)> 0 }">
														<button type = "submit" value ="${teacherCurrentPage-(5-i)}" name = "teacherCurrentPage" class="btn btn-outline-info btn-rounded">${teacherCurrentPage-(5-i)}</button>
													</c:if>
													<c:if test="${ i==5 }">
														<button type = "submit" value ="${teacherCurrentPage}" name = "teacherCurrentPage" class="btn btn-outline-danger btn-rounded">${teacherCurrentPage}</button>
													</c:if>
													<c:if test="${ i > 5 && teacherCurrentPage+(i-5) <= teacherLastPage }">
														<button type = "submit" value ="${teacherCurrentPage+(i-5)}" name = "teacherCurrentPage" class="btn btn-outline-info btn-rounded">${teacherCurrentPage+(i-5)}</button>
													</c:if>
												</c:forEach>
												<c:if test="${teacherCurrentPage<teacherLastPage}">
													<button type="submit" class="btn btn-outline-success btn-rounded" name="teacherCurrentPage" value="${teacherCurrentPage+1}">다음</button>
												</c:if>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				<!-- 가입 대기 운영자 목록 -->
				<div class="row">
					<div class="col-lg-12 col-md-12">
						<div class="card">
							<div class="mt-2" style="height: auto; width: auto;">
									<div class="card-body">
										<h4 class="card-title">가입 대기 운영자 목록</h4>
										<table id="zero_config" class="table table-striped table-bordered">
											<thead>
												<tr>
													<th>아이디</th>
													<th>이름</th>
													<th>이메일</th>
													<th>전체 주소</th>
													<th>가입 요청 날짜</th>
													<th>승인 버튼</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="m" items="${managerList}">
													<tr>
														<td>${m.memberId}</td>
														<td>${m.managerName}</td>
														<td>${m.managerEmail}</td>
														<td>${m.addrDetail}</td>
														<td>${m.memberCreateDate}</td>
														<td>
															<a type="button" class="btn btn-outline-success"  href="${pageContext.request.contextPath}/manager/member/approvalMemberAction?memberId=${m.memberId}&memberLevel=${m.memberLevel}">가입 승인</a>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										<div style="text-align: center;">
							            	<form method="get" action="/lms/manager/member/approvalMember">
											<c:if test="${managerCurrentPage>1}">
													<button type="submit" class="btn btn-outline-warning btn-rounded" name="managerCurrentPage" value="${managerCurrentPage-1}">이전</button>
												</c:if>
												<!-- 목록 사이 번호 표시 -->
												<c:forEach begin="1" end="10" step="1" var="i" varStatus="status">
													<c:if test="${ i< 5 && managerCurrentPage-(5-i)> 0 }">
														<button type = "submit" value ="${managerCurrentPage-(5-i)}" name = "managerCurrentPage" class="btn btn-outline-info btn-rounded">${managerCurrentPage-(5-i)}</button>
													</c:if>
													<c:if test="${ i==5 }">
														<button type = "submit" value ="${managerCurrentPage}" name = "managerCurrentPage" class="btn btn-outline-danger btn-rounded">${managerCurrentPage}</button>
													</c:if>
													<c:if test="${ i > 5 && managerCurrentPage+(i-5) <= managerLastPage }">
														<button type = "submit" value ="${managerCurrentPage+(i-5)}" name = "managerCurrentPage" class="btn btn-outline-info btn-rounded">${managerCurrentPage+(i-5)}</button>
													</c:if>
												</c:forEach>
												<c:if test="${managerCurrentPage<managerLastPage}">
													<button type="submit" class="btn btn-outline-success btn-rounded" name="managerCurrentPage" value="${managerCurrentPage+1}">다음</button>
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
<script src="/lms/dist/js/app-style-switcher.js"></script>
<script src="/lms/dist/js/feather.min.js"></script>
<script src="/lms/assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
<script src="/lms/dist/js/sidebarmenu.js"></script>
<script src="/lms/dist/js/custom.min.js"></script>
<script src="/lms/assets/extra-libs/c3/d3.min.js"></script>
<script src="/lms/assets/extra-libs/c3/c3.min.js"></script>
<script src="/lms/assets/libs/chartist/dist/chartist.min.js"></script>
<script src="/lms/assets/libs/chartist-plugin-tooltips/dist/chartist-plugin-tooltip.min.js"></script>
<script src="/lms/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.min.js"></script>
<script src="/lms/assets/extra-libs/jvector/jquery-jvectormap-world-mill-en.js"></script>
<script src="/lms/dist/js/pages/dashboards/dashboard1.min.js"></script>
</html>