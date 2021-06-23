<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<title>Question Profile</title>
</head>

<body>
	<div class="container">
		<div class="row p-4 justify-content-center">
			<div class="col-sm-6">
				<h3 class="text-center"><a href="/dashboard">Dashboard</a></h3>
			</div>
		</div>
		
		<div class="row p-4">
			<h1 class="text-left"><c:out value="${ myquestion.question }"></c:out></h1>
		</div>
		
		<div class="row">
			<div class="col-sm-6">
				<div class="card-deck">
					<h4><span class="card text-center p-1">Tags: </span></h4>
					<c:forEach items="${myTags}" var="tag" varStatus="loop">
						<h4><span class="card text-center p-1"><c:out value="${tag.subject}"/></span></h4>
					</c:forEach>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="card-deck">
				<div class="card p-2" style="width: 20rem;">
					<table class="table is-bordered is-fullwidth is-striped">
						<thead>
							<tr><td class="is-dark">Answers</td></tr>
						</thead>
						<tbody>
							<c:forEach items="${answers}" var="answer" varStatus="loop">
							<tr><td>
								<c:out value="${answer.answer}"/>
							</td></tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="card p-2" style="width: 20rem;">
					<h2 class="title">Add your answer:</h2>
					<form:form action="/questions" method="post" modelAttribute="ans">
						<p>
							<form:label path="answer">Answer:</form:label>
							<p class="text-danger"><c:out value="${errors}"></c:out></p>
							<form:textarea path="answer" class="textarea"/>
							<input type="hidden" name="question" value="${myquestion.id}">
						</p>
						<div class="row justify-content-center p-4">
							<input type="submit" value="Create" class="btn btn-success"/>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>