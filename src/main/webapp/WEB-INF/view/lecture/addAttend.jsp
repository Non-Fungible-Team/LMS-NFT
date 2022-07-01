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
				<div class="col-lg-12 col-md-6">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title">출석 체크</h4>
							<div style="height: auto; width: auto;">
							<form class="mt-4" method="post" id="addAttendForm" action="${pageContext.request.contextPath}/manager/lecture/addLecture">
							<div class="form-group">
								<table id="zero_config" class="table table-striped table-bordered no-wrap">
									<tr>
										<th>출석 날짜</th>
										<th>강의 번호</th>
										<th>강의명</th>
										<th>학생 아이디</th>
										<th>학생 이름</th>
										<th>출석 상태</th>
										<th>사유</th>
										<th>체크 날짜</th>
									</tr>
									<tr>
									<form>
									<c:forEach var="a" items="${attendList}">
										<tr>
											<td>${a.attendDate}</td>
											<td>${a.lectureNo}</td>
											<td>${a.lectureName}</td>
											<td>${a.memberId}</td>
											<td>${a.studentName}</td>
											<td>
												<select name="attendStatus">
													<option value="출석">출석</option>
													<option value="지각">지각</option>
													<option value="결석">결석</option>
													<option value="병결">병결</option>
													<option value="공결">공결</option>
												</select>
											</td>
											<td>
												<input type="text" name="attendReason" maxlength="50"> 
											</td>
											<td>
												<input type="text" name="attendCreateDate" value="${a.attendCreateDate}">
											</td>
										</tr>
									</c:forEach>
									</form>
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