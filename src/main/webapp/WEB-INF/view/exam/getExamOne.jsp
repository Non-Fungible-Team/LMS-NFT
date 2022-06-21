<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>getExamOne</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
   integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
   crossorigin="anonymous">
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
   integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
   crossorigin="anonymous">
<script
   src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
   integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
   crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
   <div class="container">
      <h1>문제보기</h1>
      <a href="${pageContext.request.contextPath}/exam/getExamListByPage"
         class="btn">이전으로</a>
      <table class="table table-striped">
         <thead>
         <tr>
            <th class="text-center">시험 번호</th>
            <th class="text-center">시험 제목</th>
            <th class="text-center">시험 문항수</th>
            <th class="text-center">만점</th>
            <th class="text-center">시험 시작 일시</th>
            <th class="text-center">시험 종료 일시</th>
         </tr>
         </thead>
         <tbody>
         <c:forEach var="e" items="${examOneList}">
         <tr>
            <td class="text-center">${e.examNo}</td>
            <td class="text-center">${e.examTitle}</td>
            <td class="text-center">${e.examCount}</td>
            <td class="text-center">${e.examMaxScore}</td>
            <td class="text-center">${e.examStartDate}</td>
            <td class="text-center">${e.examEndDate}</td>
         </tr>
         </c:forEach>
         </tbody>
      </table>
      <c:forEach var="q" items="${examQuestionOneList}" varStatus="status">
	      <table class="table table-striped">
	      	<thead>
		    	<tr>
		        	<th class="text-center">문제 번호</th>
		            <th class="text-center">문제 내용</th>
		            <th class="text-center">배점</th>
		            <th class="text-center">문제유형</th>
		            <th class="text-center">정답</th>
		            <th class="text-center">보기</th>
		         </tr>
	        </thead>
	        <tbody>
				<tr>
			        <td class="text-center">${q.examQuestionNo}</td>
			        <td class="text-center">${q.examContent}</td>
			        <td class="text-center">${q.examPoint}</td>
			        <td class="text-center">${q.examType}</td>
			        <td class="text-center">${q.examCorrectAnswer}</td>
					<td class="text-center">
								<table>
								<thead>
									<tr>
										<th class="text-center">보기 번호</th>
										<th class="text-center">보기 내용</th>
									</tr>
								</thead>
								<c:forEach var="a" items="${examExampleOneList}">
									<tbody>
										<tr>
											<td class="text-center">${a.exampleContent}</td>
											<td class="text-center">${a.exampleContent}</td>
										</tr>
									</tbody>
								</c:forEach>
								</table>
					</td>
				</tr>
<!-- 	        <table class="table table-striped"> -->
<!-- 	        	<thead> -->
<!-- 					<tr> -->
<!-- 						<th class="text-center">보기 번호</th><th class="text-center">보기 내용</th> -->
<!-- 					</tr> -->
<!-- 				</thead> -->
<%-- 		        	<c:forEach var="a" items="${examExampleOneList}" > --%>
<!-- 			        	<tbody> -->
<!-- 				         		<tr> -->
<%-- 					         		<td class="text-center">${a.exampleNo}</td> <th class="text-center">${a.exampleContent}</td> --%>
<!-- 					         	</tr> -->
<!-- 			         	</tbody> -->
<%-- 			      	</c:forEach> --%>
<!-- 	    	  	</table> -->
	      </table>
      </c:forEach>
      
      
      
<!--       <div style="float: right"> -->
<!--          <a class="btn btn-info" -->
<%--             href="${pageContext.request.contextPath}/exam/updateExam?examNo=${examNo}">수정</a> --%>
<!--          <a class="btn btn-danger" -->
<%--             href="${pageContext.request.contextPath}/exam/removeExam?examNo=${examNo}">삭제</a> --%>
         
<!--          <form method="post" action="removeExam" >  -->
<%--         	 <span><input type="text" name="examNo" value="${examNo}"></span> --%>
<!-- 			 <button type="submit">삭제</button> -->
<!--          </form> -->
<!--       </div> -->
<!--    </div> -->
</body>
</html>