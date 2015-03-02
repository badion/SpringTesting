<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>
<html>
<script>
	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}
</script>


<body>
	<h1>Title : ${title}</h1>
	List of customers : ${message}
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>

	<h1>Employees</h1>
	<a href="http://localhost:8080/SpringInActionPart6/emps">Href to
		json: http://localhost:8080/SpringInActionPart6/emps</a>
	<p>List of employees from RS, that gives json:</p>

	<table border="1">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Age</th>
			<th>Deleting</th>
		</tr>
		<c:forEach var="emp" items="${emps}">
			<tr>
				<td>${emp.id}</td>
				<td><a href="/SpringInActionPart6/emp/${emp.id}">${emp.name}</a></td>
				<td>${emp.age}</td>
				<td><a href="/SpringInActionPart6/emp/delete/${emp.id}"><strong>X</strong></a></td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			Welcome : ${pageContext.request.userPrincipal.name} | <a
				href="javascript:formSubmit()"> Logout</a>
		</h2>
	</c:if>
</body>
</html>