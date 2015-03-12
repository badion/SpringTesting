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
/* .error { */
/* 	padding: 15px; */
/* 	margin-bottom: 20px; */
/* 	border: 1px solid transparent; */
/* 	border-radius: 4px; */
/* 	color: #a94442; */
/* 	background-color: #f2dede; */
/* 	border-color: #ebccd1; */
/* } */

/* .msg { */
/* 	padding: 15px; */
/* 	margin-bottom: 20px; */
/* 	border: 1px solid transparent; */
/* 	border-radius: 4px; */
/* 	color: #31708f; */
/* 	background-color: #d9edf7; */
/* 	border-color: #bce8f1; */
/* } */

/* #login-box { */
/* 	width: 300px; */
/* 	padding: 20px; */
/* 	margin: 100px auto; */
/* 	background: #fff; */
/* 	-webkit-border-radius: 2px; */
/* 	-moz-border-radius: 2px; */
/* 	border: 1px solid #000; */
/* } */
* {
	margin: 0px;
	padding: 0px;
}

body {
	background: #222526;
	position: relative;
	padding: 20px;
	font-family: verdana;
}

#loginform {
	padding-top: 53px;
	width: 550px;
	height: auto;
	position: relative;
	margin: 0 auto;
}

input {
	display: block;
	margin: 0px auto 15px;
	border-radius: 5px;
	background: #333333;
	width: 85%;
	padding: 12px 20px 12px 10px;
	border: none;
	color: #929999;
	box-shadow: inset 0px 1px 5px #272727;
	font-size: 0.8em;
	-webkit-transition: 0.5s ease;
	-moz-transition: 0.5s ease;
	-o-transition: 0.5s ease;
	-ms-transition: 0.5s ease;
	transition: 0.5s ease;
}

input:focus {
	-webkit-transition: 0.5s ease;
	-moz-transition: 0.5s ease;
	-o-transition: 0.5s ease;
	-ms-transition: 0.5s ease;
	transition: 0.5s ease;
	box-shadow: 0px 0px 5px 1px #161718;
}

button {
	background: #ff5f32;
	border-radius: 50%;
	border: 10px solid #222526;
	font-size: 0.9em;
	color: #fff;
	font-weight: bold;
	cursor: pointer;
	width: 85px;
	height: 85px;
	position: absolute;
	right: -42px;
	top: 54px;
	text-align: center;
	-webkit-transition: 0.5s ease;
	-moz-transition: 0.5s ease;
	-o-transition: 0.5s ease;
	-ms-transition: 0.5s ease;
	transition: 0.5s ease;
}

button:hover {
	background: #222526;
	border-color: #ff5f32;
	-webkit-transition: 0.5s ease;
	-moz-transition: 0.5s ease;
	-o-transition: 0.5s ease;
	-ms-transition: 0.5s ease;
	transition: 0.5s ease;
}

button i {
	font-size: 20px;
	-webkit-transition: 0.5s ease;
	-moz-transition: 0.5s ease;
	-o-transition: 0.5s ease;
	-ms-transition: 0.5s ease;
	transition: 0.5s ease;
}

button:hover i {
	color: #ff5f32;
	-webkit-transition: 0.5s ease;
	-moz-transition: 0.5s ease;
	-o-transition: 0.5s ease;
	-ms-transition: 0.5s ease;
	transition: 0.5s ease;
}

*:focus {
	outline: none;
}

::-webkit-input-placeholder {
	color: #929999;
}

:-moz-placeholder { /* Firefox 18- */
	color: #929999;
}

::-moz-placeholder { /* Firefox 19+ */
	color: #929999;
}

:-ms-input-placeholder {
	color: #929999;
}

h1 {
	text-align: center;
	color: #fff;
	font-size: 13px;
	padding: 12px 0px;
}

#note {
	color: #88887a;
	font-size: 0.8em;
	text-align: left;
	padding-left: 5px;
}

#facebook button {
	text-align: center;
	float: left;
	background: #365195;
	padding: 35px 10px 20px 10px;
	width: 170px;
	height: 135px;
	border-radius: 3px;
	cursor: pointer;
	box-shadow: 0px 0px 10px 2px #161718;
	margin-right: 10px;
	-webkit-transition: 0.5s ease;
	-moz-transition: 0.5s ease;
	-o-transition: 0.5s ease;
	-ms-transition: 0.5s ease;
	transition: 0.5s ease;
}

#facebook:hover button {
	box-shadow: 0px 0px 0px 0px #161718;
	-webkit-transition: 0.5s ease;
	-moz-transition: 0.5s ease;
	-o-transition: 0.5s ease;
	-ms-transition: 0.5s ease;
	transition: 0.5s ease;
}

.fa-facebook button {
	color: #fff;
	font-size: 7em;
	display: block;
}

a {
	color: #88887a;
	text-decoration: none;
	-webkit-transition: 0.5s ease;
	-moz-transition: 0.5s ease;
	-o-transition: 0.5s ease;
	-ms-transition: 0.5s ease;
	transition: 0.5s ease;
}

a:hover {
	color: #fff;
	margin-left: 5px;
	-webkit-transition: 0.5s ease;
	-moz-transition: 0.5s ease;
	-o-transition: 0.5s ease;
	-ms-transition: 0.5s ease;
	transition: 0.5s ease;
}

#mainlogin {
	float: left;
	width: 250px;
	height: 170px;
	padding: 10px 15px;
	position: relative;
	background: #555555;
	border-radius: 3px;
}

#connect {
	font-weight: bold;
	color: #fff;
	font-size: 13px;
	text-align: left;
	font-family: verdana;
	padding-top: 10px;
}

#or {
	position: absolute;
	left: -25px;
	top: 10px;
	background: #222222;
	text-shadow: 0 2px 0px #121212;
	color: #999999;
	width: 40px;
	height: 40px;
	text-align: center;
	border-radius: 50%;
	font-weight: bold;
	line-height: 38px;
	font-size: 13px;
}

#error_message {
	color: red;
	padding: 31px;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.logined {
	margin-left: 686px;
}
</style>

</head>
<body onload='document.loginForm.username.focus();'>
	<h1>Spring Security Custom Login Form (XML)</h1>

	<c:if test="${pageContext.request.userPrincipal.name == null}">
		<div id="loginform">
			<form action="connect/facebook" method="GET">
				<input type="hidden" name="scope" value="read_stream" />
				<div id="facebook">
					<button type="submit" id="facebook_button">
						<i class="fa fa-facebook">Facebook login</i>
					</button>
				</div>
			</form>
			<div id="mainlogin">
				<h1>Login with username or password</h1>
				<form name="loginForm"
					action="<c:url value='j_spring_security_check' />" method="POST">
					<input type="text" name="name" placeholder="username or email"
						value="" required> <input type="password" name="password"
						placeholder="password" value="" required>
					<button type="submit">
						<i class="fa fa-arrow-right">login</i>
					</button>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
				<div id="note">
					<a href="/SpringInActionPart6/cust/save">registration</a>
				</div>
				<div id="error_message">
					<c:if test="${not empty error}">
						<div class="error">${error}</div>
					</c:if>
					<c:if test="${not empty msg}">
						<div class="msg">${msg}</div>
					</c:if>
				</div>
			</div>
		</div>
	</c:if>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<div class="logined">
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
		</div>
	</c:if>
</body>
</html>