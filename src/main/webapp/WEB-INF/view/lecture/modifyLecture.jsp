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
<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/static/assets/images/favicon.png">
<title>Lecture List</title>
<link href="${pageContext.request.contextPath}/static/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/static/dist/css/style.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
</head>
<script>
$(document).ready(function(){
	$("#navAside").load(
			'${pageContext.request.contextPath}/include/navAside.jsp');
	}
</script>
<body>
	<div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full" data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
		<!-- header include(네비게이션바) -->
		<div id="navAside"></div>

		<div class="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12 col-md-6">
						<div class="card">
							<div class="card-body">
								<h4 class="card-title">강의 수정</h4>
								<div class="mt-2" style="height: auto; width: auto;">
									<form class="mt-4" method="post" id="addLectureForm" action="${pageContext.request.contextPath}/manager/lecture/modifyLecture">
										<div class="form-group">
											<div>
												과목명 <select name="subjectNo">
													<option value=" ">과목 선택</option>
													<c:forEach var="s" items="${subjectList}">
														<option value="${s.subjectNo}">${s.subjectName}</option>
													</c:forEach>
												</select>
											</div>
											<br> 강의명 <input type="text" name="lectureName" class="form-control" placeholder="강의명 입력해주세요" value="${lectureName}"> 
											<br> 개강일 <input type="date"  class="form-control" name="lectureStartDate" value="${lectureStartDate }"> 
											<br> 수료일 <input type="date"  class="form-control" name="lectureEndDate" value="${lectureEndDate}"><br>
											<div>
												강의실 <select name=lectureRoomName>
													<option value=" ">강의실 선택</option>
													<c:forEach var="l" items="${lectureRoomList}">
														<option value="${l.lectureRoomName}">${l.lectureRoomName}</option>
													</c:forEach>
												</select>
											</div>
											<br> 강사 <select name="memberId">
												<option value=" ">강사 선택</option>
												<c:forEach var="t" items="${teacherList}">
													<option value="${t.memberId}">${t.teacherName}</option>
												</c:forEach>
											</select>
										</div>
										<!-- 작성자 -->
										<input type="hidden"  name="lectureWriter" class="form-control" value="${sessionLoginMember.memberId }">
										<button type="button" class="btn btn-outline-success btn-rounded">
											<i class="fas fa-check"></i>강의 수정
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