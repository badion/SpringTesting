<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<title>Hello Facebook</title>
</head>
<body>
	<h3>${facebookProfile.name}
		Hello, <span th:text="${facebookProfile.name}">Some User</span>!
	</h3>

	<img alt="${pict}"
		src="https://graph.facebook.com/${pict}/picture?type=large">
	<h4>Here is your home feed:</h4>
	<c:forEach var="post" items="${feed}">
		<p>fdafdsa</p>
		<p>${post.message}</p>
		<img alt="${post.picture}" src="${post.picture}">

	</c:forEach>

	<div th:each="post:${feed}">
		<b th:text="${post.from.name}">from</b> wrote:
		<p th:text="${post.message}">message text</p>
		<img th:if="${post.picture}" th:src="${post.picture}" />
		<hr />
	</div>

</body>
</html>