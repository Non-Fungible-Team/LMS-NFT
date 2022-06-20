<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modifyBoard</title>
</head>
<body>
	<div>
		<h1>modifyBoard</h1>
		<form action="${pageContext.request.contextPath}/board/modifyBoard?boardNo=${board.boardNo}" method="post">
			<table>
				<tr>
					<th>번호</th>
					<td>${board.boardNo}</td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="boardTitle" values="${board.boardTitle}"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><input type="text" name="boardContent" values="${board.boardContent}"></td>
				</tr>
			</table>
			<button type="submit">수정</button>
		</form>
	</div>
	<ul class="pager">
      <li><a href="${pageContext.request.contextPath}/board/getBoardByPage?boardNo=${board.boardNo}">목록으로</a></li>
    </ul>
</body>
</html>