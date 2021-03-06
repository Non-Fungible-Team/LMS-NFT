<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>Non-fungible LMS</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link href="${pageContext.request.contextPath}/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/dist/css/style.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
<script>
$('document').ready(function() {
	
	$('#btnRemove').click(function(){
		var result = confirm("삭제 하시겠습니까?");
				
		if (result) {
			return true;
		} else {
			return false;
		}
	});
}
</script>
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
				<h1>과제 상세보기</h1>
				<!-- main화면 body start -->
				<!-- 첫번쨰 문단 -->
				<div class="row">
					<div class="col-lg-12 col-md-12">
						<div class="card">
							<div class="card-body">
								<h4 class="card-title">과제 상세보기</h4>
									<div class="mt-2" style="height: auto; width: auto;">
									<!-- 테이블 넣는곳, 테이블 색깔 변경 ->class만 변경 -->
										 <div class="table-responsive">
											<table id="zero_config" class="table table-striped table-bordered">
												<tr>
													<td>번호</td>
													<td><input type="text" class="form-control" size="4" name ="homeworkNo" value ="${homeworkOne.homeworkNo}" readonly="readonly"></td>
												</tr>
												<tr>
													<td>강의번호</td>
													<td><input type="text" class="form-control" size="4" name ="lectureNo" value ="${homeworkOne.lectureNo}" readonly="readonly"></td>
												</tr>
												<tr>
													<td>제목</td>
													<td><input type="text" class="form-control" name ="homeworkTitle"  value ="${homeworkOne.homeworkTitle}" readonly="readonly"></td>
												</tr>
												<tr>
													<td>작성자</td>
													<td><input type="text" class="form-control" name="memberId" value="${homeworkOne.memberId }" readonly="readonly"></td>
												</tr>
												<tr>
													<td>내용</td>
													<td><textarea name=homeworkContent class="form-control" rows ="8" cols="70"  readonly="readonly">${homeworkOne.homeworkContent}</textarea></td>
												</tr>
												<tr>
													<td>생성 날짜</td>
													<td><input type="text" class="form-control" name="homeworkCreateDate" size="16" value = "${homeworkOne.homeworkCreateDate}"readonly="readonly"></td>
												</tr>
												<tr>
													<td>제출 기간</td>
													<td>${homeworkOne.homeworkStartDate } ~ ${homeworkOne.homeworkEndDate }</td>
												</tr>
											</table>
										</div>
										<form action="${pageContext.request.contextPath}/teacher/homework/removeHomework" method="post">
											<input type="hidden" name ="homeworkNo" value ="${homeworkOne.homeworkNo}" readonly="readonly">
											<input type="hidden" name ="lectureNo" value ="${homeworkOne.lectureNo}" readonly="readonly">
											<c:if test="${sessionLoginMember.memberLevel==5 && sessionLoginMember.memberId == homeworkOne.memberId }">
												<button type="button" class="btn btn-outline-success btn-rounded" onclick="location.href='${pageContext.request.contextPath}/teacher/homework/modifyHomework?homeworkNo='+${homeworkOne.homeworkNo}">수정</button>
												<button type="submit" id="btnRemove" class="btn btn-outline-success btn-rounded" onclick="return confirm('삭제하면 복구할 수 없습니다.\n정말 삭제 하시겠습니까?');">삭제</button>
											</c:if>
											<c:if test="${sessionLoginMember.memberLevel == 4}">
											<button type="button" class="btn btn-outline-success btn-rounded" onclick="location.href='${pageContext.request.contextPath}/student/homework/addHomeworkSubmit?homeworkNo='+${homeworkOne.homeworkNo}">과제 제출</button>
											</c:if>
											<button type="button" class="btn btn-outline-success btn-rounded float-right" onclick="location.href='${pageContext.request.contextPath}/all/homework/getHomeworkListByPage?lectureNo='+${sessionLectureNo}">목록</button>
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