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
<title>insert attend</title>
<link href="${pageContext.request.contextPath}/static/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/static/dist/css/style.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
<script>
	$(document).ready(function() {
		$("#navAside").load(
		'${pageContext.request.contextPath}/include/navAside.jsp');
		});
		
		//form submit 유효성 검사
		$('#addAttendBtn').click(function() {
			if($('#attendStatus').val() == ' '){
				alert('출석체크 해주세요'); 
			}else{
				$('#"addAttendForm"').submit();
			}
		});
</script>
</head>

<body>
	<div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full" data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
		<!-- header include(네비게이션바) -->
		<div id="navAside"></div>
		<!-- main화면 body start -->
		<div class="container p-5 my-5">
			<div class="row">
				<div class="col-lg-10 col-md-6">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title">출석 체크</h4>
							<div style="height: auto;">
							<form method="post" action="${pageContext.request.contextPath}/teacher/lecture/getAttendList">
								<input type ="hidden" name="lectureNo" value="${lectureNo}">
								<select name="attendDate" onchange ="this.form.submit()">
									<option value="">출석 날짜 선택</option>
									<c:forEach var="ls" items="${lectureScheduleList}">
										<option value="${ls.lectureScheduleDate}">${ls.lectureScheduleDate}</option>
									</c:forEach>
								</select>
							</form>
							<form class="mt-4" method="post" id="addAttendForm" action="${pageContext.request.contextPath}/manager/lecture/addLecture">
							<div class="form-group">
								<table id="zero_config" class="table table-striped table-bordered no-wrap">
								<c:forEach var="a" items="${attendList}">
									<tr>
										<td>학생아이디</td>
										<td>
											<input type="text" name="memberId" value="${a.memberId }">
										</td>
									</tr>
									<tr>
										<td>학생이름</td>
										<td>
											<input type="text" name="studentName" value="${a.studentName}">
										</td>
									</tr>
									<tr>
										<td>강의 번호</td>
										<td>
											<input type="number" name="lectureNo" value="${a.lectureNo}">
										</td>
									</tr>
									<tr>
										<td>출석 상태</td>
										<td>
											<select name="attendStatus" id="attendStatus">
												<option value="출석">출석</option>
												<option value="지각">지각</option>
												<option value="결석">결석</option>
												<option value="병결">병결</option>
												<option value="공결">공결</option>
											</select>
										</td>
									</tr>
									<tr>
										<td>사유</td>
										<td>
											<input type="text" name="attendReason">
										</td>
									</tr>
								</c:forEach>
								</table>
							</div>
							</form>
							</div>
							<!-- 작성자 -->
							<input type="hidden" id="lectureWriter" name="lectureWriter" class="form-control" value="${sessionLoginMember.memberId }">
							<button type="button" id="addAttendBtn" class="btn btn-outline-success btn-rounded">
								<i class="fas fa-check"></i>출석 체크
							</button>
							
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