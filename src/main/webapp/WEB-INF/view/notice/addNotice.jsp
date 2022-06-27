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
<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/static/assets/images/favicon.png">
<title>addNotice</title>
<!-- CSS 링크 -->
<link href="${pageContext.request.contextPath}/static/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/static/dist/css/style.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
<style type="text/css">textarea{width: 100%; height: 1000em; border: none; resize: none;}</style>
</head>
	<script>
		// html페이지를 다 로드시키고 매개변수함수를 실행
		$('document').ready(function(){ 
			$("#navAside").load('${pageContext.request.contextPath}/include/navAside.jsp');
			
			// 파일추가 버튼 생성
			let flag = true;
			var fileUploadCount = 0;
			$('#addNoticeFileUpload').click(function(){
				$('.noticeFileList').each(function(){
					if($(this).val() == ''){
						flag = false;	
					}
				});
			
				if(flag){
					var label = 'label' + fileUploadCount;
					var fileBtn = 'fileBtun' + fileUploadCount;
					$('#fileSection').append("<label class='input-group-btn' ><span class='btn btn-outline-info btn-rounded'><span  id ="+label+">파일 입력</span><input type='file' id = "+fileBtn+" class='noticeFileList' style='display: none;' name='noticeFileList' ></span></label>");
					
					$('#'+fileBtn+'').change(function(){
						$('#'+label+'').text($('#'+fileBtn+'').val());
					});
					fileUploadCount = fileUploadCount+1;
				} else {
					alert('첨부되지 않은 noticeFileList가 존재합니다.');
				}
				
			});
			
			//유효성검사
			$('#uploadNotice').click(function(){
					$('#noticeTitleHelper').text('');
					$('#noticeContentHelper').text('');
					$('#noticePrivilegeHelper').text('');
					
				if($('#noticeTitle').val() == ''){
					$('#noticeTitleHelper').text('제목을 입력하세요');
					$('#noticeTitle').focus();
				} else if($('#noticeContent').val() == ''){
					$('#noticeContentHelper').text('내용을 입력하세요');
					$('#noticeContent').focus();
				} else if($('#noticePrivilege').val() == '-1'){
					$('#noticePrivilegeHelper').text('읽기권한을 선택하세요');
					$('#noticePrivilege').focus();
				} else {//첨부되지 않은 파일여부 체크 후 submit
					$('.noticeFileList').each(function(){
						if($(this).val() == ''){
							flag = false;
						}
					});
					if(flag){
						$('#addNoticeForm').submit();
					} else {
						$('#addNoticeFileUploadHelper').text('파일을 첨부해 주세요');	
						$('#addNoticeFileUpload').focus();
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
			            <h4 class="card-title">공지사항 입력</h4>
				            <div>
				            	<a href="${pageContext.request.contextPath}/all/notice/getNoticeListByPage">
								  	<input type="button" class="btn btn-info" style="float: right" value="목록으로">
								</a>
							</div>
			            <div class="mt-2" style="height:auto; width:100%;">
			            	<form id="addNoticeForm" action="${pageContext.request.contextPath}/manager/notice/addNotice" method="post" enctype="multipart/form-data">
				            	<table id="zero_config" class="table table-striped table-bordered no-wrap">
									<tr>
						    			<th>제목</th>
						    			<td>
						    				<input type="text" name="noticeTitle" id="noticeTitle" class="form-control">
						    				<span id="noticeTitleHelper" class="helper"></span>
						    			</td>
						    		</tr>
						    		<tr>
						    			<th>작성자</th>
						    			<td>
						    				<input type="text" name="memberId" id="memberId" class="form-control" value="${sessionLoginMember.memberId}" readonly="readonly">
						    				<span id="memberIdHelper" class="helper"></span>
						    			</td>
						    		</tr>
						    		<tr>
						    			<th>내용</th>
						    			<td height= 500px>
						    				<textarea rows="20" Cols="10" name="noticeContent" id="noticeContent" class="form-control" placeholder="내용을 입력해 주세요."></textarea>
						    				<span id="noticeContentHelper" class="helper"></span>
						    			</td>
						    		</tr>
						    		<tr>
						    			<th>읽기권한</th>
						    			<td>
						    				<select name = "noticePrivilege" id="noticePrivilege">
						    					<option value="-1">::읽기권한::</option>
						    					<option value="4">학생이상</option>
						    					<option value="5">강사이상</option>
						    					<option value="6">운영자만</option>
						    				</select>
						    				<span id="noticePrivilegeHelper" class="helper"></span>
						    			</td>
						    		</tr>
				            	</table>
				            	
				            	
				            	
				            	<div>
				            		<button type="button" id = "addNoticeFileUpload" class="btn btn-outline-info btn-rounded">파일 업로드 추가</button>
				            		<div id = "fileSection">
				            			<!-- 파일 업로드 input 태그가 추가될 영역 -->
				            		</div>
				            		<span id="addNoticeFileUploadHelper" class="helper"></span>
				            	</div>
				            	<div>
				            		<button type="button" id="uploadNotice" class="btn btn-outline-success btn-rounded">등록</button>
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