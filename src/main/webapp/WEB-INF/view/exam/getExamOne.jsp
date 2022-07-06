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
<link rel="stylesheet"	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<!-- title icon -->
<link rel="icon" type="image/png" sizes="16x16"	href="${pageContext.request.contextPath}/assets/images/favicon.png">
<title>getExamOne</title>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link	href="${pageContext.request.contextPath}/assets/extra-libs/c3/c3.min.css"	rel="stylesheet">
<link	href="${pageContext.request.contextPath}/assets/libs/chartist/dist/chartist.min.css"	rel="stylesheet">
<link	href="${pageContext.request.contextPath}/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css"	rel="stylesheet" />
<link	href="${pageContext.request.contextPath}/dist/css/style.min.css"	rel="stylesheet">
<script	src="${pageContext.request.contextPath}/assets/libs/jquery/dist/jquery.min.js"></script>
<script	src="${pageContext.request.contextPath}/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script	src="${pageContext.request.contextPath}/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<!-- script>
$('#btnRemove').click(function(){
    let ck = new Array(); // let ck = [];
    $('.ck:checked').each(function(index,item){ 
        ck.push($(item).val());
    });
    
    console.log('#btnRemove click');
    $.ajax({
        url:'/exam/removeExam'
        ,type:'POST'
        ,data:{'ck':ck}
        ,success:function(){
            $('#').trigger('click');
        }
    });
});    
$('#').trigger('click');
</script> -->
</head>
<script>
	$('document').ready(function(){
	    $("#navAside").load('${pageContext.request.contextPath}/include/navAside.jsp');
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
				<h1>시험문제 상세보기</h1>
				<!-- main화면 body start -->
				<!-- 첫번쨰 문단 -->
				<div class="row">
					<div class="col-lg-12 col-md-12">
						<div class="card">
							<div class="card-body">
								<h4 class="card-title">문제 보기</h4>
								<div class="mt-2" style="height: auto; width: auto;">
									<!-- 테이블 넣는곳, 테이블 색깔 변경 ->class만 변경 -->
									<table id="zero_config"
										class="table table-striped table-bordered table table-hover">
										<thead>
											<tr>
												<th class="table-primary">시험 번호</th>
												<th class="table-primary">시험 제목</th>
												<th class="table-primary">시험 문항수</th>
												<th class="table-primary">만점</th>
												<th class="table-primary">시험 시작 일시</th>
												<th class="table-primary">시험 종료 일시</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td class="table-light">${examOne.examNo}</td>
												<td class="table-light">${examOne.examTitle}</td>
												<td class="table-light">${examOne.examNo}</td>
												<td class="table-light">${examOne.examMaxScore}</td>
												<td class="table-light">${examOne.examStartDate}</td>
												<td class="table-light">${examOne.examEndDate}</td>
											</tr>
										</tbody>
									</table>
									<c:forEach var="q" items="${examQuestionOneList}"
										varStatus="status">
										<c:set var="qn" value="Q${q.examQuestionNo}"></c:set>
										<table id="zero_config"
											class="table table-striped table-bordered">
											<thead>
												<tr>
													<th class="table-info">문제 번호</th>
													<th class="table-info">문제 내용</th>
													<th class="table-info">배점</th>
													<th class="table-info">문제유형</th>
													<th class="table-info">정답</th>
													<th class="table-info">보기</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td class="table-light">${q.examQuestionNo}</td>
													<td class="table-light">${q.examContent}</td>
													<td class="table-light">${q.examPoint}</td>
													<td class="table-light">${q.examType}</td>
													<td class="table-light">${q.examCorrectAnswer}</td>
													<td class="table-light">
														<table>
															<thead>
																<tr>
																	<th class="table-info">보기 번호</th>
																	<th class="table-info">보기 내용</th>
																</tr>
															</thead>
															<c:forEach var="ee" items="${examExampleOneList}">
																<c:forEach var="example" items="${ee[qn]}">
																	<tbody>
																		<tr>
																			<td class="table-light">${example.exampleNo}</td>
																			<td class="table-light">${example.exampleContent}</td>
																		</tr>
																	</tbody>
																</c:forEach>
															</c:forEach>
														</table>
													</td>
												</tr>
											</tbody>
										</table>
									</c:forEach>
									<div style="float: right">
										<input type="hidden" name="examNo" value="${param.examNo}"
											readonly="readonly">
										<button type="button"
											class="btn btn-outline-success btn-rounded"
											onclick="location.href='${pageContext.request.contextPath}/all/exam/getExamListByPage'">
											<i class="fas fa-check"></i>시험 목록
										</button>
										<button type="button"
											class="btn btn-outline-success btn-rounded"
											onclick="location.href='${pageContext.request.contextPath}/teacher/exam/modifyExam?examNo='+${param.examNo}">
											<i class="fas fa-check"></i>수정
										</button>
									<form
										action="${pageContext.request.contextPath}/teacher/exam/removeExam?examNo=${param.examNo}" method="post">
										<button type="submit" class="btn btn-outline-success btn-rounded" onclick="return confirm('삭제하시겠습니까?');">
											<i class="fas fa-check"></i>삭제
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
<script	src="${pageContext.request.contextPath}/dist/js/app-style-switcher.js"></script>
<script	src="${pageContext.request.contextPath}/dist/js/feather.min.js"></script>
<script	src="${pageContext.request.contextPath}/assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
<script	src="${pageContext.request.contextPath}/dist/js/sidebarmenu.js"></script>
<script	src="${pageContext.request.contextPath}/dist/js/custom.min.js"></script>
<script	src="${pageContext.request.contextPath}/assets/extra-libs/c3/d3.min.js"></script>
<script	src="${pageContext.request.contextPath}/assets/extra-libs/c3/c3.min.js"></script>
<script	src="${pageContext.request.contextPath}/assets/libs/chartist/dist/chartist.min.js"></script>
<script	src="${pageContext.request.contextPath}/assets/libs/chartist-plugin-tooltips/dist/chartist-plugin-tooltip.min.js"></script>
<script	src="${pageContext.request.contextPath}/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.min.js"></script>
<script	src="${pageContext.request.contextPath}/assets/extra-libs/jvector/jquery-jvectormap-world-mill-en.js"></script>
<script	src="${pageContext.request.contextPath}/dist/js/pages/dashboards/dashboard1.min.js"></script>
</html>