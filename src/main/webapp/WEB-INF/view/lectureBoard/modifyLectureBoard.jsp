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
<style type="text/css">textarea{width: 100%; height: 1000em; border: none; resize: none;}</style>
</head>
	<script>
		$('document').ready(function(){
			$("#navAside").load('${pageContext.request.contextPath}/include/navAside.jsp');
			
			//파일추가 버튼 생성
			let flag = true;
			var modifyFileUploadCount = 0;
			$('#addModifyLectureFileUpload').click(function(){
				$('.modifyLectureFileList').each(function(){
					if($(this).val() == ''){
						flag = false;
					}
				});
				
				if(flag){
					var label = 'label' + modifyFileUploadCount;
					var fileBtn = 'fileBtn' + modifyFileUploadCount;
					$('#fileSection').append("<label class='input-group-btn' ><span class='btn btn-outline-info btn-rounded'><span  id ="+label+">파일 입력</span><input type='file' id = "+fileBtn+" class='modifyLectureFileList' style='display: none;' name='lectureFileList' ></span></label>");
					
					$('#'+fileBtn+'').change(function(){
						$('#'+label+'').text($('#'+fileBtn+'').val());
					});
					modifyFileUploadCount = modifyFileUploadCount + 1;
				} else {
					alert('첨부되지 않은 modifyLectureFileList가 존재합니다.')
				}
				
			});
			
			//파일 하나 삭제
			$('.removeFileOne').click(function(){
				var url = $(this).data('value');
				var index = $(this).data('index');
				console.log(index);
				
				$.ajax({
					type:'get'
					,url: url
					,success: function(data){ // 백앤드 응답 문자열을 자바스크립트 객체로 변환 후 매개값 입력됨
						//ajax값 가공
						console.log(data);
						console.log('#lectureBoardFile'+index+'');
						$('#lectureBoardFile'+index+'').text('');
					}
				});
			});
			
			//유효성검사
			$('#uploadModifyLectureBoard').click(function(){
				$('#modifyLectureBoardTitleHelper').text('');
				$('#modifyLectureBoardContentHelper').text('');
				$('#modifyLectureBoardPrivilegeHelper').text('');
				let flag = true;
					
				if($('#modifyLectureBoardTitle').val() == ''){
					$('#modifyLectureBoardTitleHelper').text('제목을 입력하세요');
					$('#modifyLectureBoardTitle').focus();
				} else if($('#modifyLectureBoardContent').val() == ''){
					$('#modifyLectureBoardContentHelper').text('내용을 입력하세요');
					$('#modifyLectureBoardContent').focus();
				} else { //첨부되지 않은 파일여부 체크 후 submit
					$('.modifyLectureFileList').each(function(){
						if($(this).val() == ''){
							flag = false;	
						}
					});
					if(flag) {
						$('#modifyLectureBoardForm').submit();
					} else {
						$('#addModifyLectureFileUploadHelper').text('파일을 첨부해 주세요');
						$('#addModifyLectureFileUpload').focus();
					}
				}
			});
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
				        <div class="card-body">
					            <h4 class="card-title">강의게시글 수정</h4>
					            <div>
					            	<a href="${pageContext.request.contextPath}/all/lectureBoard/getLectureBoardListByPage">
										<input type="button" class="btn btn-info" style="float: right" value="목록으로">
									</a>
					            </div>
					            <div class="mt-2" style="height:auto; width:auto;">
									<form id="modifyLectureBoardForm" action="${pageContext.request.contextPath}/teacher/lectureBoard/modifyLectureBoard?lectureBoardNo=${lectureBoard.lectureBoardNo}" method="post" enctype="multipart/form-data" >
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
												<th>제목</th>
												<td>
													<input type="text" name="lectureBoardTitle" id="modifyLectureBoardTitle" class="form-control" value="${lectureBoard.lectureBoardTitle}">
													<span id="modifyLectureBoardTitleHelper" class="helper"></span>
												</td>
											</tr>
											<tr>
							    				<th>내용</th>
								    			<td height= 500px>
								    				<textarea rows="20" Cols="10" name="lectureBoardContent" id="modifyLectureBoardContent" class="form-control">${lectureBoard.lectureBoardContent}</textarea>
								    				<span id="modifyLectureBoardContentHelper" class="helper"></span>
								    			</td>
								    		</tr>
										</table>

										<div>
						            		<button type="button" id = "addModifyLectureFileUpload" class="btn btn-outline-info btn-rounded">파일 업로드 추가</button>
						            		<div id = "fileSection">
						            			<!-- 파일 업로드 input 태그가 추가될 영역 -->
						            		</div>
						            		<span id="addModifyLectureFileUploadHelper" class="helper"></span>
						            	</div>
						            	<br>
						            	<h4>첨부 된 파일 삭제</h4>
						            	<div>
						            		<table id="zero_config" class="table table-striped table-bordered">
						            			<tr>
						            				<th>파일미리보기</th>
													<th>파일타입</th>
													<th>파일사이즈</th>
													<th>첨부파일 삭제</th>
						            			</tr>
						            			<c:forEach var ="f" items="${lectureFileList}" varStatus ="status">
														<tr id ="lectureBoardFile${status.index}">
															<td>
																<c:if test="${f.lectureFileType=='image/gif'||f.lectureFileType=='image/png'||f.lectureFileType == 'image/jpeg'}">
																	<img height="100" width="100" src="${pageContext.request.contextPath}/uploadFile/lectureFile/${f.lectureFileName}">
																</c:if>
																<a href="${pageContext.request.contextPath}/uploadFile/lectureFile/${f.lectureFileName}" download>▶${f.lectureFileOriginName}◀ 파일 다운로드</a>
															</td>
															<td>${f.lectureFileType}</td>
															<td>${f.lectureFileSize}</td>
															<td>
															<button type="button" data-index="${status.index}" data-value="${pageContext.request.contextPath}/rest/teacher/lectureBoard/removeLectureFile?lectureFileName=${f.lectureFileName}&lectureFileNo=${f.lectureFileNo}&lectureBoardNo=${lectureBoard.lectureBoardNo}" class="removeLectureBoardFileOne btn btn-outline-danger">file삭제</button>
															<!-- 
																<a href="${pageContext.request.contextPath}/teacher/lectureBoard/removeLectureFile?lectureFileName=${f.lectureFileName}&lectureFileNo=${f.lectureFileNo}&lectureBoardNo=${lectureBoard.lectureBoardNo}">
													    			<input type="button" class="btn btn-outline-danger" value="file삭제">
													    		</a>
															 -->
															</td>
														</tr>	
										    	</c:forEach>
						            		</table>
						            	</div>
						            	
										<div>
						            		<button type="button" id="uploadModifyLectureBoard" class="btn btn-outline-success" style="float: right">수정</button>
							        	</div>
							        	<div>
											<a href="${pageContext.request.contextPath}/teacher/lectureBoard/removeLectureBoard?lectureBoardNo=${lectureBoard.lectureBoardNo}">
									    		<input type="button" class="btn btn-outline-danger" value="강의게시글 삭제">
									    	</a>
									    </div>
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