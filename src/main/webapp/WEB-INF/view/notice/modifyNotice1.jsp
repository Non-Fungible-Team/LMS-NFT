<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
	<body>
		<div>
			<h1>modifyNotice</h1>
			<form action="${pageContext.request.contextPath}/manager/notice/modifyNotice?noticeNo=${notice.noticeNo}" method="post">
				<table>
					<tr>
						<th>번호</th>
						<td>${notice.noticeNo}</td>
					</tr>
					<tr>
						<th>제목</th>
						<td><input type="text" name="noticeTitle" values="${notice.noticeTitle}"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><input type="text" name="noticeContent" values="${notice.noticeContent}"></td>
					</tr>
				</table>
				<button type="submit">수정</button>
			</form>
		</div>
	    <a href="${pageContext.request.contextPath}/all/notice/getNoticeListByPage">목록으로</a>
	</body>
</html>
