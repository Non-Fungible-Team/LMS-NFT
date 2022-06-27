<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>Lecture List</title>
<link href="${pageContext.request.contextPath}/static/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/static/dist/css/style.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
<script>
	$(document).ready(function() {
		//개강일, 수료일로 강사, 강의실 검색
		$('#checkLecture').click(function() {
			if($('#lectureStartDate').val()== ' '){
				alert('개강일을 선택해주세요'); 
			}else if($('#lectureEndDate').val()== ' '){
				alert('수료일을 선택해주세요'); 
			}else{
				var lectureStartDate = $('#lectureStartDate').val();
				var lectureEndDate = $('#lectureEndDate').val();
				var url = '${pageContext.request.contextPath}/manager/getTeacherListAndLectureRoomList?lectureEndDate='+lectureEndDate+'&lectureStartDate='+lectureStartDate; 
				$.ajax({
					type:'get'
					,url: url
					,success: function(a){ // 백앤드 응답 문자열을 자바스크립트 객체로 변환 후 매개값 입력됨
					//ajax값 가공
					console.log(a)
					var a2 = JSON.stringify(a);
					var a3 = JSON.parse(a2);
	            	console.log("▶▶▶a2 : "+a);
	                console.log("▶▶▶typeof(a3) : "+typeof(a3));
	               
	                // let 변수 선언 자리에 var 사용해도 상관 없다 
	                let arr = a3.results.juso; // 주소 배열
	                console.log("▶▶▶arr : "+arr);
						
						for(var i=0; i<arr.length; i++){
						$('#roomList').append(`<option name="lectureRoomName" value="a.lecutureRoomList[i]">`+a.lecutureRoomList[i]+`</option>`);
						}
						for(var i=0; i<arr.length; i++){
						$('#roomList').append(`<option name="memberId" value="a.teacherList[i]">`+a.teacherList[i]+`</option>`);
						}
					}
				});
			}
		});
		
		//form submit 유효성 검사
		$('#addLectureBtn').click(function() {
			if($('#subjectName').val() == ' '){
				alert('과목을 선택해주세요'); 
			}else if($('#lectureName').val() == ' '){
				alert('강의명을 입력해주세요'); 
			}else if($('#lectureStartDate').val()== ' '){
				alert('개강일을 선택해주세요'); 
			}else if($('#lectureEndDate').val()== ' '){
				alert('수료일을 선택해주세요'); 
			}else if($('#lectureRoom').val() == ' '){
				alert('강의실을 선택해주세요'); 
			}else if($('#teacher').val()== ' '){
				alert('강사를 선택해주세요'); 
			}else{
				$('#addLectureForm').submit();
			}
		});
	});
</script>
</head>

<body>
	<div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full" data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
		<!-- header include(네비게이션바) -->
		<div id="navAside"></div>
		<!-- main화면 body start -->
		<div class="container p-5 my-5">
			<div class="row">
				<div class="col-lg-10 col-md-6">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title">강의 삽입</h4>
							<div style="height: auto;">
								<form class="mt-4" method="post" id="addLectureForm" action="${pageContext.request.contextPath}/manager/lecture/addLecture">
									<div class="form-group">
										<div>
											과목명 <select id="subjectName" name="subjectNo">
												<option value=" ">과목 선택</option>
												<c:forEach var="s" items="${subjectList}">
													<option value="${s.subjectNo}">${s.subjectName}</option>
												</c:forEach>
											</select>
										</div>
										<br> 강의명 <input type="text" id="lectureName" name="lectureName" class="form-control" placeholder="강의명 입력해주세요">
										<br> 개강일 <input type="date" id="lectureStartDate" class="form-control" name="lectureStartDate">
										<br> 수료일 <input type="date" id="lectureEndDate" class="form-control" name="lectureEndDate"><br>
										<button type = "button" id="checkLecture">강사,강의실 검색</button>
										<!-- 검색시, 강사, 강의실 리스트가 들어갈 자리 -->
										강의실 <select id="lectureRoom" name="lectureRoomName">
										<option value=" ">강의실 선택</option>
										<div id = "lectureRoomList"></div>
										</select>
										강사 <select id="teacherList" name="memberId">
										<option value=" ">강사 선택</option>
										<div id = "teacherList"></div>
										</select>
										</div>
									</div>
										<!-- 작성자 -->
										<input type="hidden" id="lectureWriter" name="lectureWriter" class="form-control" value="${sessionLoginMember.memberId }">
									<button type="button" id="addLectureBtn" class="btn btn-outline-success btn-rounded">
										<i class="fas fa-check"></i>강의 입력
									</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
    	$("#navAside").load('${pageContext.request.contextPath}/include/navAside.jsp');
  	</script>
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