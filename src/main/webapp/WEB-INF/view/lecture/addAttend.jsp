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
<script>
	$(document).ready(function() {
		$("#navAside").load(
		'${pageContext.request.contextPath}/include/navAside.jsp');
		});
		
</script>
</head>

<body>
	<div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full" data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
		<!-- header include(네비게이션바) -->
		<div id="navAside"></div>
		<div class="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12 col-md-12">
						<div class="card">
							<div class="card-body">
								<h4 class="card-title">출석 체크</h4>
								<div style="height: auto; width: auto;">
									<div class="table-responsive">
										<form action="${pageContext.request.contextPath}/manager/lecture/addAttend" method="post">
										<input type ="hidden" name = "lectureNo" value ="${param.lectureNo}">
											<table class="table table-striped table-bordered">
												<tr>
													<th>강의 번호</th>
													<th>출석 날짜</th>
													<th>강의명</th>
													<th>학생 아이디</th>
													<th>학생 이름</th>
													<th>출석 상태</th>
													<th>사유</th>
												</tr>
												<tr>
													
													<c:forEach var="l" items="${studentLectureList}" varStatus="status">
														<tr>
															<!-- 리스트로 값을 묶어서 -> controller -->
															<td><input type="hidden" name="attendList[${status.index}].lectureNo" value="${l.lectureNo}"></td>
															<td><input type="text" name="attendList[${status.index}].attendDate" value="${attendDate}" readonly="readonly" style="border: none;"></td>
															<td><input type="text" name="attendList[${status.index}].lectureName" value="${l.lectureName}" readonly="readonly" style="border: none;"></td>
															<td><input type="text" name="attendList[${status.index}].memberId" value="${l.memberId}" readonly="readonly" style="border: none;"></td>
															<td><input type="text" name="attendList[${status.index}].studentName" value="${l.studentName}" readonly="readonly" style="border: none;"></td>
															<td>
																<select name="attendList[${status.index}].attendStatus">
																		<option value="출석">출석</option>
																		<option value="지각">지각</option>
																		<option value="결석">결석</option>
																		<option value="병결">병결</option>
																		<option value="공결">공결</option>
																</select>
															</td>
															<td>
																<input type="text" name="attendList[${status.index}].attendReason" maxlength="50" style="border: none;">
															</td>
														</tr>
													</c:forEach>
											</table>
											<button type="submit" id="addAttendBtn" class="btn btn-outline-success btn-rounded">
												<i class="fas fa-check"></i>출석 체크
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