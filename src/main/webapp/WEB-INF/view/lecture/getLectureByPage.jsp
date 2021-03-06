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
<link href="${pageContext.request.contextPath}/assets/libs/morris.js/morris.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
<script src="http://cdn.oesmith.co.uk/morris-0.4.1.min.js"></script>
</head>
	<script>
		$('document').ready(function(){
	    	$("#navAside").load('${pageContext.request.contextPath}/include/navAside.jsp');
		
		//chart
		getGraph(); //그래프 가져오기
		function getGraph() {
			var url ='${pageContext.request.contextPath}/rest/manager/getTeacherLectureChart'; //차트 메소드 맵핑주소
			console.log(url);
			$.ajax({
				type:'get' 
				,url : url
				,success: function(data) { //백앤드 응답 문자열을 자바스크립트 객체로 변환 후 매개값 입력됨
					console.log(data);
					var teacherLectureChart = data.teacherLectureChart; //리스트로 풀기
					console.log(teacherLectureChart);
					var dataSet =[];
					var colors =[];
					for(var i =0; i < teacherLectureChart.length;i=i+1){
						dataSet.push({label:teacherLectureChart[i].teacherName,value:teacherLectureChart[i].totalTime});
						colors.push();
					}
					console.log(dataSet)
					Morris.Donut({
				        element: 'morris-donut-chart',
				        data: dataSet,
				        resize: true,
				        colors:['#5f76e8', '#01caf1', '#8fa0f3']
				    });
				}
			});
		}
		
		
		});
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
								<h4 class="card-title">수강목록</h4>
								<div class="mt-2" style="height: auto; width: auto;">
									<table id="zero_config" class="table table-striped table-bordered table-sm">
										<thead>
											<tr>
												<th>강의번호</th>
												<th>과목</th>
												<th>강의명</th>
												<th>개강일</th>
												<th>수료일</th>
												<th>강의실</th>
												<th>강사</th>
												<th>생성날짜</th>
												<th>
													<a href="${pageContext.request.contextPath}/manager/lecture/addLecture" class="btn btn-primary btn-rounded"> 
														<i class="fas fa-check"></i> 강의 입력
													</a>
												</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="l" items="${lectureList}">
												<tr>
													<td>${l.lectureNo}</td>
													<td>${l.subjectName}</td>
													<td><a href="${pageContext.request.contextPath}/all/lectureNoController?lectureNo=${l.lectureNo}&lectureOne=true">${l.lectureName}</a></td>
													<td>${l.lectureStartDate}</td>
													<td>${l.lectureEndDate}</td>
													<td>${l.lectureRoomName}</td>
													<td>${l.teacherName}</td>
													<td>${l.lectureCreateDate}</td>
													<td>
														<div class="btn-group" role="group" aria-label="Basic example">
															<a href="${pageContext.request.contextPath}/manager/lecture/modifyLecture?lectureNo=${l.lectureNo}" class="btn btn-info">수정</a> 
															<a href="${pageContext.request.contextPath}/manager/lecture/removeLecture?lectureNo=${l.lectureNo}" class="btn btn-danger disabled">삭제</a>
														</div>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<div>
										<form method="get" action="${pageContext.request.contextPath}/teacher/lecture/getLectureByPage">
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
					<div class="col-lg-12">
						<div class="card">
							<div class="card-body">
								<h4 class="card-title">강사별 강의시간 통계 차트</h4>
								<div id="morris-donut-chart"></div>
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
	    <!--Morris JavaScript -->
    <script src="${pageContext.request.contextPath}/assets/libs/raphael/raphael.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/libs/morris.js/morris.min.js"></script>
    <script src="${pageContext.request.contextPath}/dist/js/pages/morris/morris-data.js"></script>

</html>