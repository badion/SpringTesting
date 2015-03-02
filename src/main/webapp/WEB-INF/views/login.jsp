<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Login Page</title>
<script>
	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}
</script>

<style type="text/css">
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

#login-box {
	width: 300px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}
</style>

</head>
<body onload='document.loginForm.username.focus();'>
	<h1>Spring Security Custom Login Form (XML)</h1>

	<div id="login-box">
		<h3>Login with Username and Password</h3>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>


		<!-- If user in session is null, then show login form with facebook authorizing button -->
		<c:if test="${pageContext.request.userPrincipal.name == null}">
			<form name='loginForm'
				action="<c:url value='j_spring_security_check' />" method='POST'>
				<table>
					<tr>
						<td>User:</td>
						<td><input type='text' name='name' value=''></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><input type='password' name='password' /></td>
					</tr>
					<tr>
						<td colspan="1"><input name="submit" type="submit"
							value="submit" /></td>
						<td><a href="/SpringInActionPart6/cust/save">registration</a></td>

					</tr>
				</table>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
			<h3>Connect to Facebook</h3>
			<form action="connect/facebook" method="GET">
				<input type="hidden" name="scope" value="read_stream" />
				<div class="formInfo">
					<p>You aren't connected to Facebook yet. Click the button to
						connect this application with your Facebook account.</p>
				</div>
				<p>
					<button type="submit">Connect to Facebook</button>
				</p>
			</form>
		</c:if>

		<!-- If user in session != null, then show hello message with name and logout button -->
		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h2>
				Welcome : ${pageContext.request.userPrincipal.name} | <a
					href="javascript:formSubmit()"> Logout</a>
			</h2>
			<c:url value="/j_spring_security_logout" var="logoutUrl" />
			<a
				href="https://www.facebook.com/logout.php?next=http://localhost:8080/SpringInActionPart6/login&access_token=CAAVZCgw3HTq4BAEqZCKOcYlEtX4lyPaJJSAPM4XTTg706gD6x5kZBbODbFc7q75ZCGyNJvnMaruc2x48i9LH5ZAiMF0tmpiU1ZC3uZCvmfXWk3oLkcxtn6Pc5jTbXHSyXTsZBnWCXdc9tddHcwfHUG86A0PZCjpQCqZC07JvHaAuVeulLxBst0xp3JsvoBLDgn4nZBsXZCmA5xfXpJopNU7BDPt1">Logout
				from facebook</a>
			<form action="${logoutUrl}" method="post" id="logoutForm">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
		</c:if>
	</div>

</body>
</html>