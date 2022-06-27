<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>getHomeworkSubmitOne</h1>
	
	<table>
		<tr>
			<td>homeworkSubmitNo</td>
			<td>${homeworkSubmitOne.homeworkSubmitNo}</td>
		</tr>
		<tr>
			<td>homeworkNo</td>
			<td>${homeworkSubmitOne.homeworkNo}</td>
		</tr>
		<tr>
			<td>lectureNo</td>
			<td>${sessionLectureNo}</td>
		</tr>
		<tr>
			<td>memberId</td>
			<td>${sessionLoginMember.memberId }</td>
		</tr>
		<tr>
			<td>homeworkSubmitTitle</td>
			<td>${homeworkSubmitOne.homeworkSubmitTitle }</td>
		</tr>
		<tr>
			<td>homeworkSubmitContent</td>
			<td>${homeworkSubmitOne.homeworkSubmitContent }</td>
		</tr>
		<tr>
			<td>homeworkSubmitFileName</td>
			<td>${homeworkSubmitFileList.homeworkSubmitFileName }</td>
		</tr>
		<tr>
			<td>homeworkSubmitCreateDate</td>
			<td>${homeworkSubmitOne.homeworkSubmitCreateDate }</td>
		</tr>
		<tr>
			<td>homeworkSubmitUpdateDate</td>
			<td>${homeworkSubmitOne.homeworkSubmitUpdateDate }</td>
		</tr>
		<tr>
			<td>homeworkSubmitScore</td>
			<td>${homeworkSubmitOne.homeworkSubmitScore }</td>
		</tr>
		<tr>
			<td>homeworkSubmitFeedback</td>
			<td>${homeworkSubmitOne.homeworkSubmitFeedback}</td>
		</tr>
	 
	</table>

</body>
</html>