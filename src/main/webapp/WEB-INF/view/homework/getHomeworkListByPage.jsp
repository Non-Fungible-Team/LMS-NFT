<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1"> <!-- 반응형 웹 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<!-- title icon -->
<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/assets/images/favicon.png">
<title>Non-fungible LMS</title>
<!-- CSS 링크 -->
<link href="${pageContext.request.contextPath}/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/dist/css/style.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>

</head>
<script>
	$('document').ready(function(){
	    $("#navAside").load('${pageContext.request.contextPath}/include/navAside.jsp');
	});
</script>
<body>
	<div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full" data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
		<!-- header include(네비게이션바) -->
		<div id="navAside"></div>
		<div class="page-wrapper">
			<div class="container-fluid">
				<h1>과제 목록</h1>
				<div class="row">
					<div class="col-lg-12 col-md-12">
						<div class="card">
							<div class="card-body">
								<h4 class="card-title">과제 목록</h4>
								<a href="${pageContext.request.contextPath}/all/homework/getHomeworkListByPage?lectureNo=${sessionLectureNo}">
									<input type="button" class="btn btn-outline-success btn-rounded" style="float: right" value="목록">
								</a>
								<div class="mt-2" style="height: auto; width: auto;">
									<!-- 테이블 넣는곳, 테이블 색깔 변경 ->class만 변경 -->
									<div class="table-responsive">
										<table id="zero_config" class="table table-striped table-bordered">
												<thead>
													<tr>
	<!-- 													<th>순번</th> -->
														<th>강의번호</th>
														<th>과제</th>
														<th>작성자</th>
														<th>제출 기간</th>
														<c:if test="${sessionLoginMember.memberLevel == 5}">
														<th>제출과제 목록보기</th>
														</c:if>
														<c:if test="${sessionLoginMember.memberLevel == 4}">
														<th>과제 제출</th>
														</c:if>
													
													</tr>
												</thead>
												<tbody>
													<c:forEach var="h" items="${homeworkList}">
	<%-- 											<c:if test="${sessionLectureNo == h.lectureNo }"> --%>
														<tr>
	<%-- 														<td>${h.homeworkNo}</td> --%>
															<td>${h.lectureNo}</td>
															<td><a href="${pageContext.request.contextPath}/all/homework/getHomeworkOne?homeworkNo=${h.homeworkNo}">${h.homeworkTitle }</a></td>
															<td>${h.teacherId }</td>
															<td>${h.homeworkStartDate} ~ ${h.homeworkEndDate }</td>
															<c:if test="${sessionLoginMember.memberLevel == 5}">
																<td><a href="${pageContext.request.contextPath}/all/homework/getHomeworkSubmitListByPage?homeworkNo=${h.homeworkNo}">목록</a></td>
															</c:if>
															<c:if test="${sessionLoginMember.memberLevel == 4 && empty h.homeworkSubmitNo}">
	<%-- 														<c:if test="${sessionLoginMember.memberLevel == 4 && empty h.homeworkSubmitNo && empty h.studentId}"> --%>
																<td><a href="${pageContext.request.contextPath}/student/homework/addHomeworkSubmit?homeworkNo=${h.homeworkNo}">과제제출</a></td>														
															</c:if> 
															<c:if test="${sessionLoginMember.memberLevel == 4 && sessionLoginMember.memberId == h.studentId && not empty h.homeworkSubmitNo }">
	<%-- 														<c:if test="${sessionLoginMember.memberLevel == 4 && sessionLoginMember.memberId == h.studentId && not empty h.homeworkSubmitNo }"> --%>
																<td><a href="${pageContext.request.contextPath}/all/homework/getHomeworkSubmitOne?homeworkSubmitNo=${h.homeworkSubmitNo}"><u>과제보기</u></a></td>														
															</c:if> 
														</tr>
															
															
															
	<%-- 											</c:if> --%>
													</c:forEach>
												</tbody>
											</table>
										</div>
										<!-- 검색 -->
									    <div>
									        <form action="${pageContext.request.contextPath}/all/homework/getHomeworkListByPage" method="get">
									            <div style="border:0 padding-top:2; text-align:center;" >
									            	<input type="text" name="searchWord" style="width:300px; height:32px; font-size:15px; border-radius: 15px; outline: none; padding-left: 10px; background-color: rgp(233,233,233);  text-align:center;" placeholder="Search"/>
									            	<button type="submit" class="btn btn-outline-warning btn-rounded">검색</button></div>
											    <div>
											        <c:if test="${currentPage>1}">
											            <a href="${pageContext.request.contextPath}/all/homework/getHomeworkListByPage?currentPage=${currentPage-1}&searchWord=${searchWord}">이전</a>
											        </c:if>
											        <c:if test="${currentPage<lastPage}">
											            <a href="${pageContext.request.contextPath}/all/homework/getHomeworkListByPage?currentPage=${currentPage+1}&searchWord=${searchWord}">다음</a>
											        </c:if>
											    </div>
									        </form>
									    </div>
										<form method="get" action="${pageContext.request.contextPath}/all/homework/getHomeworkListByPage">
												<c:if test="${currentPage>1}">
													<button type="submit" class="btn btn-outline-warning btn-rounded" name="currentPage" value="${currentPage-1}">이전</button>
												</c:if>
												<!-- 목록 사이 번호 표시 -->
												<c:forEach begin="1" end="10" step="1" var="i" varStatus="status">
													<c:if test="${ i< 5 && currentPage-(5-i)> 0 }">
														<button type = "submit" value ="${currentPage-(5-i)}" name = "currentPage" class="btn btn-outline-info btn-rounded">${currentPage-(5-i)}</button>
													</c:if>
													<c:if test="${ i==5 }">
														<button type = "submit" value ="${currentPage}" name = "currentPage" class="btn btn-outline-danger btn-rounded">${currentPage}</button>
													</c:if>
													<c:if test="${ i > 5 && currentPage+(i-5) <= lastPage }">
														<button type = "submit" value ="${currentPage+(i-5)}" name = "currentPage" class="btn btn-outline-info btn-rounded">${currentPage+(i-5)}</button>
													</c:if>
												</c:forEach>
												<c:if test="${currentPage<lastPage}">
													<button type="submit" class="btn btn-outline-success btn-rounded" name="currentPage" value="${currentPage+1}">다음</button>
												</c:if>
											</form>
										<hr>
										<c:if test="${sessionLoginMember.memberLevel == 5 }">
										<div>
											<button type="button" class="btn btn-success" onclick="location.href='${pageContext.request.contextPath}/teacher/homework/addHomework'"><i class="fas fa-check"></i>과제 입력</button>
									    </div>
									    </c:if>
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