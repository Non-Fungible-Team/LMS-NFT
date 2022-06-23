<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://ajax.googLeapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$('#addFileUpload').click(function(){
			
			let flag = true;
			
			$('.homeworkSubmitFileList').each(function(){
				if($(this).val() == ''){
					flag = false;
				}
			});
				
			if(flag){
				$('#fileSection').append("<div><input type='file' class='homeworkSubmitFileList' name='homeworkSubmitFileList'/></div>")
			} else {
				alert('파일이 첨부되지 않은 list가 존재합니다')					
			}
		});
		
		
		$('#addHomeworkSubmit').click(function(){
			if($('#homeworkSubmitTitle').val() == ''){
				alert('제목을 입력하세요');
			} else if($('#homeworkSubmitContent').val() == '') {
				alert('내용을 입력하세요');
			} else {
				$('.homeworkSubmitFileList').each(function(){
					if($(this).val()=''){
						flag = false;
					}
				});
				if(flag){
					$('#addHomeworkSubmitFileForm').submit();
				} else {
					alert('파일이 첨부되지 않은 list가 존재합니다');
				}
			}
		});
	});
</script>
</head>
<body>
	<h1>과제 제출</h1>
	
		<form action="addHomeworkSubmit"method="post" id="addHomeworkSubmitFileForm" enctype="multipart/form-data">
			<p><label>작성자</label><input type="text" name="memberId"></p>	
			<p><label>과제번호</label><input type="hidden" name="homeworkNo" value="${homework.homeworkNo}">${homework.homeworkNo}</p>
			<p><label>강의번호</label><input type="text" name="lectureNo" value="${homework.lectureNo}">${homework.lectureNo}</p>	
			<p><label>과제제목</label><input type="text" name="homeworkSubmitTitle" id="homeworkSubmitTitle"></p>	
			<p><label>과제내용</label><textarea name="homeworkSubmitContent" id="homeworkSubmitContent" rows="10" cols="70"></textarea></p>	
			<div>
				<button type="button" id="addFileUpload">파일업로드</button>
				<div id="fileSection"></div>
					<!-- 파일업로드 input 추가될 영역 -->
			</div>
			<button type="submit" id="addHomeworkSubmit">과제 등록</button>
		</form>
</body>
</html>