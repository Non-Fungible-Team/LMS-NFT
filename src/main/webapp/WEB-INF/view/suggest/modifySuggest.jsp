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
		
		//유효성검사
		$('#uploadModifySuggest').click(function(){
			$('#modifySuggestTitleHelper').text();
			$('#modifySuggestContentHelper').text();
			$('#modifySuggestSecretHelper').text();
			
			if($('#modifySuggestTitle').val() == ''){
				$('#modifySuggestTitleHelper').text('제목을 입력하세요');
				$('#modifySuggestTitle').focus();
			} else if ($('modifySuggestContent').val() == ''){
				$('#modifySuggestContentHelper').text('내용을 입력하세요');
				$('#modifySuggestContent').focus();
			} else if ($('#modifySuggestSecret').val() == ''){
				$('#modifySuggestSecretHelper').text('비밀글여부를 선택하세요');
				$('#modifySuggestSecret').focus();
			} else {
				$('#modifySuggestForm').submit();
			}
		});
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
								<h4 class="card-title">건의사항 수정</h4>
								<div>
					            	<a href="${pageContext.request.contextPath}/all/suggest/getSuggestListByPage">
										<input type="button" class="btn btn-info" style="float: right" value="목록으로">
									</a>
					            </div>
								<div class="mt-2" style="height: auto; width: auto;">
									<form id="modifySuggestForm" action="${pageContext.request.contextPath}/all/suggest/modifySuggest?suggestNo=${suggest.suggestNo}" method="post">
										<table id="zero_config" class="table table-striped table-bordered">
											<tr>
												<th>번호</th>
												<td>${suggest.suggestNo}</td>
											</tr>
											<tr>
								    			<th>제목</th>
								    			<td>
								    				<input type="text" name="suggestTitle" id="modifySuggestTitle" class="form-control" value="${suggest.suggestTitle}">
								    				<span id="modifySuggestTitleHelper" class="helper"></span>
								    			</td>
								    		</tr>
											<tr>
												<th>내용</th>
												<td height= 500px>
								    				<textarea rows="20" Cols="10" name="suggestContent" id="modifySuggestContent" class="form-control">${suggest.suggestContent}</textarea>
								    				<span id="modifySuggestContentHelper" class="helper"></span>
								    			</td>
											</tr>
											<tr>
												<th>비밀글 여부</th>
												<td>
													<select name = "suggestSecret" id="modifySuggestSecret">
								    					<option value="-1">::비밀글여부를 선택하세요::</option>
								    					<option value="N">공개글로 작성</option>
								    					<option value="Y">비밀글로 작성</option>
								    				</select>
													<span id="modifySuggestSecretHelper" class="helper"></span>
												</td>
											</tr>
										</table>
										<div>
											<button type="button" id = "uploadModifySuggest" class ="btn btn-outline-success btn-rounded" style="float: right">수정</button>
										</div>
										<div>
											<a href="${pageContext.request.contextPath}/manager/suggest/removeSuggest?suggestNo=${suggest.suggestNo}">
									    		<input type="button" class="btn btn-outline-danger" value="건의사항 삭제">
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