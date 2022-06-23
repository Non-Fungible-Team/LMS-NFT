<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시험수정</title>
</head>
<body>
	<h1>시험 수정</h1>
	<form method="post" id="updateExam"
		action="${pageContext.request.contextPath}/exam/modifyExam">
		<input type="hidden" name ="examNo" value ="${exam.examNo}">
		<input type="hidden" name ="examQuestionNo" value ="${examQuestion.examQuestionNo}">
		<input type="hidden" name ="exampleNo" value ="${examExample.exampleNo}">
		<table>
<!-- 	    시험 수정 -->
			<tr>
				<td>시험 이름 :</td>
				<td><input type="text" name="examTitle" id="examTitle" value ="${exam.examTitle}" ></td>
			</tr>
			<tr>
				<td>시험 문항 수 :</td>
				<td><input type="text" name="examCount" id="examCount" value ="${exam.examCount}" ></td>
			</tr>
			<tr>
				<td>만점 :</td>
				<td><input type="text" name="examMaxScore" id="examMaxScore" value ="${exam.examMaxScore}" ></td>
			</tr>
			<tr>
				<td>시험시작일시 :</td>
				<td><input type="datetime-local" name="examStartDate" id="examStartDate" value ="${exam.examStartDate}"></td>
			</tr>
			<tr>
				<td>시험종료일시:</td>
				<td><input type="datetime-local" name="examEndDate"	id="examEndDate" value ="${exam.examEndDate}"></td>
			</tr>
<!--	   	문제 수정 -->
			<tr>
				<td>시험 문제 번호 :</td>
				<td><input type="text" name="examQuestionNo" id="examQuestionNo" value ="${examQuestion.examQuestionNo}"></td>
			</tr>
			<tr>
				<td>시험 문제 내용 :</td>
				<td><input type="text" name="examContent" id="examContent" value ="${examQuestion.examContent}"></td>
			</tr>
			<tr>
				<td>시험 문제 답안 :</td>
				<td><input type="text" name="examCorrectAnswer"	id="examCorrectAnswer" value ="${examQuestion.examCorrectAnswer}"></td>
			</tr>
			<tr>
				<td>시험 문제 배점 :</td>
				<td><input type="text" name="examPoint" id="examPoint" value ="${examQuestion.examPoint}"></td>
			</tr>
			<tr>

				<td>시험 문제 타입 :</td>
				<td><input type="radio" name="examType" id="examType" value="객관식">객관식 <input type="radio" name="examType" id="examType" value="주관식">주관식</td>
			</tr>
<!--    	보기 수정 -->
			<tr>
				<td>보기 번호 :</td>
				<td><input type="text" name="exampleNo" id="exampleNo" value ="${examExample.exampleNo}"></td>
			</tr>
			<tr>
				<td>보기 내용 :</td>
				<td><input type="text" name="exampleContent" id="exampleContent" value ="${examExample.exampleNo}"></td>
			</tr>
			<!-- example input 태그가 추가될 영역 -->
			<div id="QuestionSection">
				<button type="button" id="addExamQuestion">문제 추가</button>
			</div>
			<div id="ExampleSection">
				<button type="button" id="addExamExample">보기 추가</button>
			</div>
		</table>
		<div>
			<button class="btn btn-info" type="submit" class="btn btn-default">수정완료</button>
			<input type="reset" class="btn btn-default" value="초기화">
		</div>
	</form>

</body>
</html>