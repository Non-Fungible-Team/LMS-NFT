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
<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/static/assets/images/favicon.png">
<title>강의시간표 리스트</title>
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
		$("#navAside").load(
			'${pageContext.request.contextPath}/include/navAside.jsp');
	});
</script>
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
								<h4 class="card-title">강의 시간표</h4>
								<div class="mt-2" style="height: auto; width: auto;">
									<!-- 테이블 넣는곳, 테이블 색깔 변경 ->class만 변경 -->
									<table id="zero_config" class="table table-striped table-bordered">
										<thead>
											<tr>
												<th>강의 번호</th>
												<th>수업 날짜</th>
												<th>수업 시작 시간</th>
												<th>수업 종료 시간</th>
												<th>시간표 작성 날짜</th>
												<th>
													<a href="${pageContext.request.contextPath}/manager/lecture/addLectureScheduleForm?lectureNo=${lectureNo}" class="btn btn-primary btn-rounded">시간표 추가</a>
												</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="l" items="${lectureScheduleList}">
												<tr>
													<td>${l.lectureNo}</td>
													<td>${l.lectureScheduleDate}</td>
													<td>${l.lectureScheduleStartDate}</td>
													<td>${l.lectureScheduleEndDate}</td>
													<td>${l.lectureScheduleCreateDate}</td>
													<div class="btn-group" role="group" aria-label="Basic example">
													<c:if test="${sessionLoginMember.memberLevel > 5 }">
														<td>
															<a href="${pageContext.request.contextPath}/modifyLectureSchedule?lectureScheduleDate=${l.lectureScheduleDate}&lectureNo=${l.lectureNo}" class="btn btn-info btn-sm ">수정</a>
															<a href="${pageContext.request.contextPath}/manager/lecture/removeLectureSchedule?lectureScheduleDate=${l.lectureScheduleDate}&lectureNo=${l.lectureNo}" class="btn btn-danger btn-sm">삭제</a>
														</td>
													</c:if>
													</div>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<form method="get" action="${pageContext.request.contextPath}/all/lecture/getLectureScheduleListByLectureNo">
											<c:if test="${currentPage>10}">
												<button type="submit" value="${currentPage-10}" name="currentPage" class="btn btn-secondary">이전목록</button>
											</c:if>
											<!-- 이전 부분 -->
											<c:if test="${currentPage>1}">
												<button type="submit" value="${currentPage-1}" name="currentPage" class="btn btn-secondary">이전</button>
											</c:if>
											<!-- 목록 사이 번호 표시 -->
											<c:forEach begin="1" end="10" step="1" var="i" varStatus="status">
												<c:if test="${ i< 5 && currentPage-(5-i)> 0 }">
													<button type="submit" value="${currentPage-(5-i)}" name="currentPage" class="btn btn-light">${currentPage-(5-i)}</button>
												</c:if>
												<c:if test="${ i==5 }">
													<button type="submit" value="${currentPage}" name="currentPage" class="btn btn-danger">${currentPage}</button>
												</c:if>
												<c:if test="${ i > 5 && currentPage+(i-5) <= lastPage }">
													<button type="submit" value="${currentPage+(i-5)}" name="currentPage" class="btn btn-light">${currentPage+(i-5)}</button>
												</c:if>
											</c:forEach>
											<!-- 다음 부분 -->
											<c:if test="${currentPage< lastPage }">
												<button type="submit" value="${currentPage+1}" name="currentPage" class="btn btn-secondary">다음</button>
											</c:if>
											<!-- 다음목록 표시 -->
											<c:if test="${currentPage+10 <= lastPage }">
												<button type="submit" value="${currentPage+10}" name="currentPage" class="btn btn-secondary">다음목록</button>
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