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
<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/assets/images/favicon.png">
<title>Non-Fungible LMS</title>
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
	<div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full"  data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
	<!-- header include(네비게이션바) -->
	<div id="navAside"></div>
	<div class="page-wrapper">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12 col-md-12">
				    <div class="card">
				   		<div class ="card-body">
		    				<br>
						    <h4 class="card-title">첨부된 파일</h4>
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
							    		<c:forEach var ="f" items="${lectureFileList}">
											<tr>
												<td>
													<c:if test="${f.lectureFileType=='image/gif'||f.lectureFileType=='image/png'||f.lectureFileType == 'image/jpeg'}">
														<img height="100" width="100" src="${pageContext.request.contextPath}/uploadFile/lectureFile/${f.lectureFileName}">
													</c:if>
													<a href="${pageContext.request.contextPath}/uploadFile/lectureFile/${f.lectureFileName}"  download>▶${f.lectureFileOriginName}◀ 파일 다운로드</a>
												</td>
												<td>${f.lectureFileType}</td>
												<td>${f.lectureFileSize}</td>
											</tr>	
							    		</c:forEach>
							    	</tbody>
							    </table>
							 </div>
						 </div>
				         <div class="card-body">
				        	<div>
				           		<h4 class="card-title">건의게시판 상세보기</h4>
				           	
				            	<a href="${pageContext.request.contextPath}/all/lectureBoard/getLectureBoardListByPage">
									<input type="button" class="btn btn-info" style="float: right" value="목록으로">
								</a>
								<c:if test="${sessionLoginMember.memberLevel >= 5 }">
				            		<a href="${pageContext.request.contextPath}/teacher/lectureBoard/modifyLectureBoard?lectureBoardNo=${lectureBoard.lectureBoardNo}">
				            			<input type="button" class="btn btn-outline-success" style="float: right" value="건의게시글 내용 수정">
				            		</a>
						 	    </c:if>
							</div>
	
				            <div class="mt-2" style="height:auto; width:auto;">
				            	<table id="zero_config" class="table table-striped table-bordered no-wrap">
				            		<tr>
										<th>강의번호</th>
										<td>${lectureBoard.lectureNo}</td>
									</tr>
				            		<tr>
										<th>강의이름</th>
										<td>${lectureBoard.lectureName}</td>
									</tr>
									<tr>
										<th>게시판번호</th>
										<td>${lectureBoard.lectureBoardNo}</td>
									</tr>
									<tr>
										<th>제목</th>
										<td>${lectureBoard.lectureBoardTitle}</td>
									</tr>
									<tr>
										<th>내용</th>
										<td>${lectureBoard.lectureBoardContent}</td>
									</tr>
									<tr>
										<th>블라인드여부</th>
										<td>${lectureBoard.lectureBoardBlind}</td>
									</tr>
									<tr>
										<th>작성자ID</th>
										<td>${lectureBoard.memberId}</td>
									</tr>
									<tr>
										<th>읽기권한</th>
										<td>${lectureBoard.lectureBoardPrivilege}</td>
									</tr>
									<tr>
										<th>생성날짜</th>
										<td>${lectureBoard.lectureBoardCreateDate}</td>
									</tr>
									<tr>
										<th>수정날짜</th>
										<td>${lectureBoard.lectureBoardUpdateDate}</td>
									</tr>
				            	</table>
				            </div>
				         </div>
				         <div class="card-body">
				        	<div>
				           		<h4 class="card-title">건의게시판 댓글입력</h4>
							</div>
	
				            <div class="mt-2" style="height:auto; width:auto;">
				            	<form action = "${pageContext.request.contextPath}/all/lectureBoard/addComment?lectureBoardNo=${lectureBoard.lectureBoardNo}" method="post">
					            	<table id="zero_config" class="table table-striped table-bordered">
							    		<tr>
								    		<th>강의게시판번호</th>
											<th>댓글입력</th>
											<th>작성자ID</th>
										</tr>
										<tr>	
								    		<td><input type="text" name="lectureBoardNo" class="form-control" value="${lectureBoard.lectureBoardNo}" readonly="readonly"></td>
											<td><input type="text" name="commentContent" class="form-control"></td>
											<td><input type="text" name="memberId" id="memberId" class="form-control" value="${sessionLoginMember.memberId}" readonly="readonly"></td>
										</tr>
									    <div>
					            			<button type="submit" class="btn btn-outline-success btn-rounded" style="float: right">댓글입력</button>
						        		</div>
								    </table>
								</form>
								<div class="mt-2" style="height:auto; width:auto;">
				        			<table id="zero_config" class="table table-striped table-bordered">
											<tr>
												<th>댓글번호</th>
												<th>댓글내용</th>
												<th>작성자ID</th>
												<th>댓글작성날짜</th>
												<th>삭제</th>
											</tr>
							    		<c:forEach var ="c" items="${commentList}">
											<tr>
												<td>${c.commentNo}</td>
												<td>${c.commentContent}</td>
												<td>${c.memberId}</td>
												<td>${c.commentCreateDate}</td>
												<td>
													<c:if test="${sessionLoginMember.memberLevel>=6 || sessionLoginMember.memberId == c.memberId}">
														<a href="${pageContext.request.contextPath}/all/lectureBoard/removeLectureCommentOne?lectureBoardNo=${lectureBoard.lectureBoardNo}&commentNo=${c.commentNo}">
												    		<input type="button" class="btn btn-outline-danger" value="댓글 삭제">
												    	</a>
													</c:if>
													<c:if test="${sessionLoginMember.memberLevel < 6 && sessionLoginMember.memberId != c.memberId}">
														<div>본인 글이 아닙니다.</div>
													</c:if>
											    </td>
												
											</tr>	
							    		</c:forEach>
									</table>
									
									<div style="text-align: center;">
						            	<form method="get" action="${pageContext.request.contextPath}/all/lectureBoard/getLectureBoardOne">
						            		<input type ="hidden" name="lectureBoardNo" value = "${lectureBoard.lectureBoardNo}">
											<c:if test="${commentCurrentPage>1}">
												<button type="submit" class="btn btn-outline-warning btn-rounded" name="commentCurrentPage" value="${commentCurrentPage-1}">이전</button>
											</c:if>
											<!-- 목록 사이 번호 표시 -->
											<c:forEach begin="1" end="10" step="1" var="i" varStatus="status">
												<c:if test="${ i< 5 && commentCurrentPage-(5-i)> 0 }">
													<button type = "submit" value ="${commentCurrentPage-(5-i)}" name = "commentCurrentPage" class="btn btn-outline-info btn-rounded">${commentCurrentPage-(5-i)}</button>
												</c:if>
												<c:if test="${ i==5 }">
													<button type = "submit" value ="${commentCurrentPage}" name = "commentCurrentPage" class="btn btn-outline-danger btn-rounded">${commentCurrentPage}</button>
												</c:if>
												<c:if test="${ i > 5 && commentCurrentPage+(i-5) <= commentLastPage }">
													<button type = "submit" value ="${commentCurrentPage+(i-5)}" name = "commentCurrentPage" class="btn btn-outline-info btn-rounded">${commentCurrentPage+(i-5)}</button>
												</c:if>
											</c:forEach>
											<c:if test="${commentCurrentPage<commentLastPage}">
												<button type="submit" class="btn btn-outline-success btn-rounded" name="commentCurrentPage" value="${commentCurrentPage+1}">다음</button>
											</c:if>
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