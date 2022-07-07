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
<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/assets/images/favicon.png">
<title>Non-Fungible LMS</title>
<link href="${pageContext.request.contextPath}/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/dist/css/style.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
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
				<div class="row">
					<div class="col-lg-12 col-md-12">
						<div class="card">
							<div class="card-body">
								<div>
									<h4 class="card-title">건의사항 상세보기</h4>
									<a href="${pageContext.request.contextPath}/all/suggest/getSuggestListByPage">
										<input type="button" class="btn btn-info" style="float: right" value="목록으로">
									</a>
									<c:if test="${sessionLoginMember.memberId == suggest.memberId && suggest.suggestStatus == '답변전'}">
										<a href="${pageContext.request.contextPath}/all/suggest/modifySuggest?suggestNo=${suggest.suggestNo}">
					            			<input type="button" class="btn btn-outline-success" style="float: right" value="건의사항 수정"  onclick="return confirm('답변이 완료된 글은 수정이 불가합니다.');">
					            		</a>
				            		</c:if>
				            		<c:if test="${sessionLoginMember.memberLevel >=6 }">
					            		<a href="${pageContext.request.contextPath}/manager/suggest/addSuggest?root=${suggest.suggestNo}&suggestSecret=${suggest.suggestSecret}&suggestTitle=${suggest.suggestTitle}">
						            		<input type="button" class="btn btn-outline-primary" style="float: right" value="건의사항 답글달기">
						            	</a>
					            	</c:if>
								</div>
								<div class="mt-2" style="height: auto; width: auto;">
									<table id="zero_config" class="table table-striped table-bordered">
										<tr>
											<th>번호</th>
											<td>${suggest.suggestNo}</td>
										</tr>
										<c:if test="${suggest.root != 0}">
											<tr>
												<th>질문 게시판번호</th>
												<td>${suggest.root}</td>
											</tr>
										</c:if>
										<tr>
											<th>비밀글여부</th>
											<td>${suggest.suggestSecret}</td>
										</tr>
										<tr>
											<th>답변상태</th>
											<td>${suggest.suggestStatus}</td>
										</tr>
										<tr>
											<th>제목</th>
											<td>${suggest.suggestTitle}</td>
										</tr>
										<tr>
											<th>내용</th>
											<td>${suggest.suggestContent}</td>
										</tr>
										<tr>
											<th>작성자ID</th>
											<td>${suggest.memberId}</td>
										</tr>
										<!-- 
										<tr>
											<th>읽기권한</th>
											<td></td>
										</tr>
										 -->
										<tr>
											<th>생성날짜</th>
											<td>${suggest.suggestUpdateDate}</td>
										</tr>
									</table>
								</div>
								<div>
									<c:if test="${sessionLoginMember.memberId == suggest.memberId || sessionLoginMember.memberLevel >=6}">
										<a href="${pageContext.request.contextPath}/all/suggest/removeSuggest?suggestNo=${suggest.suggestNo}">
									    		<input type="button" class="btn btn-outline-danger" style="float: right" value="건의사항 삭제[블라인드처리]">
								    	</a>
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