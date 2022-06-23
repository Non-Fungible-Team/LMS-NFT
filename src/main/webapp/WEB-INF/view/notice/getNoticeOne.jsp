<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1"> <!-- 반응형 웹 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<!-- title icon -->
<link rel="icon" type="image/png" sizes="16x16" href="assets/images/favicon.png">
<title>getNoticeOne</title>
<!-- CSS 링크 -->
<link href="assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="dist/css/style.min.css" rel="stylesheet">
<script src="assets/libs/jquery/dist/jquery.min.js"></script>
<script src="assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
</head>

<body>
<div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" 
data-sidebartype="full"  data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
	<!-- header include(네비게이션바) -->
	<div id="test"></div>
    <div class="container p-5 my-5 border">
		<div class="row">
			<div class="col-lg-12 col-md-6">
			    <div class="card">
			        <div class="card-body">
			            <h4 class="card-title">getNoticeOne</h4>
			            <div class="mt-2" style="height:auto; width:auto;">
			            	<table id="zero_config" class="table table-striped table-bordered no-wrap">
			            		<tr>
									<th>번호</th>
									<td>${notice.noticeNo}</td>
								</tr>
								<tr>
									<th>제목</th>
									<td>${notice.noticeTitle}</td>
								</tr>
								<tr>
									<th>내용</th>
									<td>${notice.noticeContent}</td>
								</tr>
								<tr>
									<th>블라인드여부</th>
									<td>${notice.noticeBlind}</td>
								</tr>
								<tr>
									<th>맴버ID</th>
									<td>${notice.memberId}</td>
								</tr>
								<tr>
									<th>읽기권한</th>
									<td>${notice.noticePrivilege}</td>
								</tr>
								<tr>
									<th>생성날짜</th>
									<td>${notice.noticeCreateDate}</td>
								</tr>
								<tr>
									<th>수정날짜</th>
									<td>${notice.noticeUpdateDate}</td>
								</tr>
			            	</table>
			            	<a href="${pageContext.request.contextPath}/manager/notice/modifyNotice?noticeNo=${notice.noticeNo}">
			            		<input type="button" class="btn btn-outline-success" value="수정">
			            	</a>
						    <a href="${pageContext.request.contextPath}/manager/notice/removeNotice?noticeNo=${notice.noticeNo}">
						    	<input type="button" class="btn btn-outline-danger" value="삭제">
						    </a>
						    
						      
<%-- 					    
						    <h4>첨부된 파일 정보</h4>
						    <table id="zero_config" class="table table-striped table-bordered no-wrap">
						    	<tr>
							    	<th>파일미리보기</th>
									<th>파일번호</th>
									<th>파일이름</th>
									<th>파일원본이름</th>
									<th>파일타입</th>
									<th>파일사이즈</th>
									<th>삭제</th>
						    	</tr>
						    	<c:forEach var ="f" items=${noticeFileList}>
									<tr>
										<td>
											<c:if test="${f.noticeFileType=='image/gif'||f.noticeFileType=='image/png'||f.noticeFileType == 'image/jpeg'}">
												<img height="100" width="100" src="${pageContext.request.contextPath}/uploadFile/${f.noticeFileName}">
											</c:if>
											<a href="${pageContext.request.contextPath}/uploadFile/${f.noticeFileName}" download>▶${f.noticeFileOriginName}◀ 사진 다운로드</a>
										</td>
										<td>${f.noticeFileId}</td>
										<td>${f.noticeFileName}</td>
										<td>${f.noticeFileOriginName}</td>
										<td>${f.noticefileType}</td>
										<td>${f.noticefileSize}</td>
										<td>
						   					<a href="${pageContext.request.contextPath}/removeNoticeFile?noticeFileId=${f.noticeFileId}&noticeId=${notice.noticeId}">file 삭제</a>
										</td>
									</tr>	
						    	</c:forEach>
						    
						    </table>
						    
						 --%>    
						    
						    
						    
							<a href="${pageContext.request.contextPath}/all/notice/getNoticeListByPage">
								<input type="button" class="btn btn-info" style="float: right" value="목록으로">
							</a>
			             </div>   
				      </div>
				   </div>
				</div>
			</div>
		</div>
	</div>
</body>
	<script>
    	$("#test").load('${pageContext.request.contextPath}/include/test.jsp');
  	</script>

    <script src="dist/js/app-style-switcher.js"></script>
    <script src="dist/js/feather.min.js"></script>
    <script src="assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
    <script src="dist/js/sidebarmenu.js"></script>
    <script src="dist/js/custom.min.js"></script>
    <script src="assets/extra-libs/c3/d3.min.js"></script>
    <script src="assets/extra-libs/c3/c3.min.js"></script>
    <script src="assets/libs/chartist/dist/chartist.min.js"></script>
    <script src="assets/libs/chartist-plugin-tooltips/dist/chartist-plugin-tooltip.min.js"></script>
    <script src="assets/extra-libs/jvector/jquery-jvectormap-2.0.2.min.js"></script>
    <script src="assets/extra-libs/jvector/jquery-jvectormap-world-mill-en.js"></script>
    <script src="dist/js/pages/dashboards/dashboard1.min.js"></script>
    
</html>