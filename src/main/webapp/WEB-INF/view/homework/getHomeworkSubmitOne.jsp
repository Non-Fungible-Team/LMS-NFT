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
<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/static/assets/images/favicon.png">

<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link href="${pageContext.request.contextPath}/static/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/static/dist/css/style.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
<script>
$('document').ready(function() {
	$('#btnRemove').click(function(){
				var result = confirm("삭제 하시겠습니까?");
				
				if (result) {
					$.ajax({
						url:'/homework/removeHomeworkSubmit'
					    ,type :'POST'
					    ,async : false
					});
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
<h1>getHomeworkSubmitOne</h1>
	<div class="row">
				<div class="col-lg-12 col-md-12">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title">제출과제 상세보기</h4>
								<div class="mt-2" style="height: auto; width: auto;">
								<form action="${pageContext.request.contextPath}/all/homework/getHomeworkSubmitOne" method="post">
								<table id="zero_config" class="table table-striped table-bordered">
									<tr>
										<td>homeworkSubmitNo</td>
										<td><input type="number" class="form-control" name="homeworkSubmitNo" value="${homeworkSubmitOne.homeworkSubmitNo}" readonly="readonly"></td>
									</tr>
									<tr>
										<td>homeworkNo</td>
										<td><input type="text" class="form-control" name="homeworkNo" value="${homeworkSubmitOne.homeworkNo}" readonly="readonly"></td>
									</tr>
<!-- 										<tr> -->
<!-- 											<td>lectureNo</td> -->
<%-- 											<td><input type="text" name="lectureNo" value="${homeworkSubmitOne.lectureNo}"></td> --%>
<!-- 										</tr> -->
									<tr>
										<td>memberId</td>
										<td><input type="text" class="form-control" name="memberId" value="${homeworkSubmitOne.memberId }" readonly="readonly"></td>
									</tr>
									<tr>
										<td>homeworkSubmitTitle</td>
										<td><input type="text" class="form-control" name="homeworkSubmitTitle" value="${homeworkSubmitOne.homeworkSubmitTitle }" readonly="readonly"></td>
									</tr>
									<tr>
										<td>homeworkSubmitContent</td>
										<td><textarea name="homeworkSubmitContent" rows="8" cols="70" class="form-control" readonly="readonly">${homeworkSubmitOne.homeworkSubmitContent }</textarea></td>
									</tr>
<!-- 										<tr> -->
<!-- 											<td>homeworkSubmitFileName</td> -->
<%-- 											<td><input type="text" name="homeworkSubmitFileName" value="${homeworkSubmitFileList.homeworkSubmitFileName }"></td> --%>
<!-- 										</tr> -->
									<c:if test="${sessionLoginMember.memberLevel == 4 && homeworkSubmitOne.homeworkSubmitScore !=0 }">
										<tr>
											<td>homeworkSubmitScore</td>
											<td><input type="text" class="form-control" name="homeworkSubmitScore" value="${homeworkSubmitOne.homeworkSubmitScore }"></td>
										</tr>
									</c:if>
									<c:if test="${sessionLoginMember.memberLevel == 4 && not empty homeworkSubmitOne.homeworkSubmitFeedback}">
										<tr>
											<td>homeworkSubmitFeedback</td>
											<td><input type="text" class="form-control" name="homeworkSubmitFeedback" value="${homeworkSubmitOne.homeworkSubmitFeedback}"></td>
										</tr>
									</c:if>
									<tr>
										<td>homeworkSubmitCreateDate</td>
										<td><input type="text" class="form-control" name="homeworkSubmitCreateDate" value="${homeworkSubmitOne.homeworkSubmitCreateDate }" readonly="readonly"></td>
									</tr>
									<tr>
										<td>homeworkSubmitUpdateDate</td>
										<td><input type="text" class="form-control" name="homeworkSubmitUpdateDate" value="${homeworkSubmitOne.homeworkSubmitUpdateDate }" readonly="readonly"></td>
									</tr>
									<c:if test="${sessionLoginMember.memberLevel == 5}">
										<tr>
											<td colspan="2" align="center">점수와 피드백을 입력해 주세요</td>
										</tr>
										<tr>
											<td>homeworkSubmitScore</td>
											<td><input type="text" class="form-control" name="homeworkSubmitScore" value="${homeworkSubmitOne.homeworkSubmitScore }"></td>
										</tr>
										<tr>
											<td>homeworkSubmitFeedback</td>
											<td><input type="text" class="form-control" name="homeworkSubmitFeedback" value="${homeworkSubmitOne.homeworkSubmitFeedback}"></td>
										</tr>
										<tr>
											<td colspan="2" align="center"><button type ="submit" class="btn btn-outline-success btn-rounded"><i class="fas fa-check"></i> 입력 완료</button></td>
										</tr>
									</c:if>
									</form>
											
								 
								</table>
								</div>
								<h4>첨부된 파일 정보</h4>
							    <div>
								    <table id="zero_config" class="table table-striped table-bordered">
								    	<thead>
								    		<tr>
									    		<th>파일미리보기</th>
												<th>파일타입</th>
												<th>파일사이즈</th>
								    		</tr>
								    	</thead>
								    	<tbody>
								    		<c:forEach var ="hf" items="${homeworkSubmitFileList}">
												<tr>
													<td>
														<c:if test="${hf.homeworkSubmitFileType=='image/gif'||hf.homeworkSubmitFileType=='image/png'||hf.homeworkSubmitFileType == 'image/jpeg'}">
															<img height="100" width="100" src="${pageContext.request.contextPath}/static/uploadFile/homeworkFile/${hf.homeworkSubmitFileName}">
														</c:if>
														<a href="${pageContext.request.contextPath}/static/uploadFile/homeworkFile/${hf.homeworkSubmitFileName}"  download>${hf.homeworkSubmitFileOriginal} 파일 다운로드</a>
													</td>
													<td>${hf.homeworkSubmitFileType}</td>
													<td>${hf.homeworkSubmitFileSize}</td>
												</tr>	
								    		</c:forEach>
								    	</tbody>
								    	
								    </table>
								</div>
<%-- 								<form action = "${pageContext.request.contextPath}/teacher/homework/removeHomeworkSubmit" method="post"> --%>
									<input type="hidden" name="homeworkSubmitNo" value="${homeworkSubmitOne.homeworkSubmitNo}" readonly="readonly">
									<c:if test="${sessionLoginMember.memberLevel==4 && sessionLoginMember.memberId == homeworkSubmitOne.memberId }">
										<button type="button" class="btn btn-outline-success btn-rounded float-left" onclick="location.href='${pageContext.request.contextPath}/student/homework/modifyHomeworkSubmit?homeworkSubmitNo='+${homeworkSubmitOne.homeworkSubmitNo}"><i class="fas fa-check">과제 수정</i></button>
										<button type="button" id="btnRemove" class="btn btn-outline-success btn-rounded"><i class="fas fa-check">과제 삭제</i></button>
									</c:if>
									<button type="button" class="btn btn-outline-success btn-rounded float-right" onclick="location.href='${pageContext.request.contextPath}/all/homework/getHomeworkListByPage'"><i class="fas fa-check">과제 목록</i></button>
<!-- 								</form> -->
								 
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