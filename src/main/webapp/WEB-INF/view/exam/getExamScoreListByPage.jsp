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
<link rel="icon" type="image/png" sizes="16x16"	href="${pageContext.request.contextPath}/assets/images/favicon.png">
<title>Non-Fungible LMS</title>
<!-- CSS 링크 -->
<link href="${pageContext.request.contextPath}/assets/extra-libs/c3/c3.min.css"	rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/dist/css/style.min.css" rel="stylesheet">
<script	src="${pageContext.request.contextPath}/assets/libs/jquery/dist/jquery.min.js"></script>
<script	src="${pageContext.request.contextPath}/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script	src="${pageContext.request.contextPath}/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script src="https://www.gstatic.com/charts/loader.js"> </script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/chart.js@3.8.0/dist/chart.min.js"></script>
</head>
<script>
	$('document').ready(function(){
		$("#navAside").load(
				'${pageContext.request.contextPath}/include/navAside.jsp');
		

		getGraph();

		function getGraph(){
			var url = '${pageContext.request.contextPath}/rest/all/exam/getExamChart';
			
			var chartMemberId = [];
			var chartExamScore = [];
			var chartExamScoreAvg = [];
			$.ajax({
				type:'get'
				,url: url
				,success: function(data){ 
					console.log(data);
					for(var i=0; i<data.length; i++){
						memberId = data[i].memberId;
						examScore = data[i].examScore;
						examScoreAvg = data[i].examScoreAvg;
						
						chartMemberId.push([memberId]);
						chartExamScore.push([examScore]);
						chartExamScoreAvg.push([examScoreAvg]);
						}
					}
				});
						
			var data = {
					labels: chartMemberId,
					datasets: [{
							label:'점수',
							data:chartExamScore,
							fill: false,
							borderColor:['rgb(255, 99, 132)'
								,'rgb(67,116, 216)'],
							backgroundColor:['rgba(255, 99, 132, 0.2)',
								'rgba(67, 116, 216, 0.2)'],
							borderWidth: 1
							}]
			}
			
				new Chart("myChart", {
						type:"bar"
						,data: data,
							options:{
								responsive: true,
				                scales: {
				                	 y: {
				                         suggestedMin: 0,
				                         suggestedMax: 100
				                     }
				                }
				}});
			}
	});
</script>
<body>
	<div id="main-wrapper" data-theme="light" data-layout="vertical"
		data-navbarbg="skin6" data-sidebartype="full"
		data-sidebar-position="fixed" data-header-position="fixed"
		data-boxed-layout="full">
		<!-- header include(네비게이션바) -->
		<div id="navAside"></div>
		<div class="page-wrapper">
			<div class="container-fluid">
				<h1>시험 점수 리스트</h1>
				<div class="row">
					<div class="col-lg-12 col-md-12">
						<div class="card">
							<div class="card-body">
								<h4 class="card-title">시험 목록</h4>
								<div class="mt-2" style="height: auto; width: auto;">
									<!-- 테이블 넣는곳, 테이블 색깔 변경 ->class만 변경 -->
									<table id="zero_config"
										class="table table-striped table-bordered">
										<thead>
											<tr>
												<th>시험 번호</th>
												<th>시험 제목</th>
												<th>학생이름</th>
												<th>점수</th>
												<th>응시여부</th>
												<th>시험응시일시</th>
												<th>상세보기</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="e" items="${examScoreList}">
												<tr>
													<c:if test="${loginMember.memberLevel == 4 && loginMember.memberId == e.memberId}">
															<td>${e.examNo+1}</td>
															<td>${e.examTitle}</td>
															<td>${e.memberId}</td>
															<td>${e.examScore}</td>
															<td>${e.examAnswerApply}</td>
															<td>${e.examAnswerEndDate}</td>
															<td><a href="${pageContext.request.contextPath}/all/exam/getExamScoreOne?examNo=${e.examNo}&memberId=${e.memberId}">상세보기</a></td>
													</c:if>
													<c:if test="${loginMember.memberLevel==5}">
															<td>${e.examNo+1}</td>
															<td>${e.examTitle}</td>
															<td>${e.memberId}</td>
															<td>${e.examScore}</td>
															<td>${e.examAnswerApply}</td>
															<td>${e.examAnswerEndDate}</td> 
															<td><a href="${pageContext.request.contextPath}/all/exam/getExamScoreOne?examNo=${e.examNo}&memberId=${e.memberId}">상세보기</a></td>
													</c:if>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<div class="row">
										<div class="col-lg-6 col-md-6">
											<div class="card">
												<div class="card-body">
													<canvas id="myChart" style="none" width="100" height="100"></canvas>
												</div>
											</div>
										</div>
									</div>
									<form method="get"
										action="${pageContext.request.contextPath}/all/exam/getExamScoreListByPage">
										<c:if test="${currentPage>1}">
											<button type="submit"
												class="btn btn-outline-warning btn-rounded"
												name="currentPage" value="${currentPage-1}">이전</button>
										</c:if>
										<!-- 목록 사이 번호 표시 -->
										<c:forEach begin="1" end="10" step="1" var="i"
											varStatus="status">
											<c:if test="${ i< 5 && currentPage-(5-i)> 0 }">
												<button type="submit" value="${currentPage-(5-i)}"
													name="currentPage" class="btn btn-outline-info btn-rounded">${currentPage-(5-i)}</button>
											</c:if>
											<c:if test="${ i==5 }">
												<button type="submit" value="${currentPage}"
													name="currentPage"
													class="btn btn-outline-danger btn-rounded">${currentPage}</button>
											</c:if>
											<c:if
												test="${ i > 5 && currentPage+(i-5) <= examScoreLastPage }">
												<button type="submit" value="${currentPage+(i-5)}"
													name="currentPage" class="btn btn-outline-info btn-rounded">${currentPage+(i-5)}</button>
											</c:if>
										</c:forEach>
										<c:if test="${currentPage<examScoreLastPage}">
											<button type="submit"
												class="btn btn-outline-success btn-rounded"
												name="currentPage" value="${currentPage+1}">다음</button>
										</c:if>
									</form>
									<hr>
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