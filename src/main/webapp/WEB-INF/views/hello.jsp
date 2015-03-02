<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page session="false"%>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<body>
	<div>
		<h2>First page</h2>
		<table>
			<tr>
				<td><a href="admin">admin page</a></td>
			</tr>
			<tr>
				<td><a href="registration">register customer(jersey rest
						service)</a></td>
			</tr>
			<tr>
				<td><a href="emp/save">register employee(spring rest
						service)</a></td>
			</tr>
			<tr>
				<td><a href="login">login</a></td>
			</tr>

			<tr>
				<td><a href="rest/customer/all">All customers json</a></td>
			</tr>
			<tr>
				<td><a href="rest/customer/1">customer by with id 1</a></td>
			</tr>
			<tr>
				<td><a href="rest/customer/42">customer with not existing
						id</a></td>
			</tr>
			<tr>
				<td><a href="rest/send/mail">Send mail</a></td>
			</tr>
		</table>
	</div>
</body>
</html>