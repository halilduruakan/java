<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
	<head>
		<title>Registration</title>
		<link type="text/css"
			  rel="stylesheet"
			  href="${pageContext.request.contextPath}/resources/css/style.css">


	</head>
	<body>
		<div id="wrapper">
			<div id="header">
				<h2>CRM - Registration</h2>
			</div>
		</div>
		<div id="container">
			<h3>Register</h3>

				  <div class="container">
	   				<label><b>Username</b></label>
				    <input type="text" placeholder="Enter Username" id="username" name="username" class="username" value="halilduruakan"/>
				<br/>
	   				<label><b>Email</b></label>
				    <input type="text" placeholder="Enter Email" id="email" name="email" class="email" value="halilduruakan@gmail.com"/>
				<br>
				    <label><b>Password</b></label>
				    <input type="password" placeholder="Enter Password" id="password" name="password" class="password" value="asdASD123" />
				<br />
	   				<label><b>Password Confirmation</b></label>
				    <input type="password" placeholder="Enter Password" id="passwordconfirmation" name="passwordconfirmation" class="passwordconfirmation" value="halilduruakan@gmail.com"/>
				<br>
				    <button type="submit" class="registration_button">Login</button>
				  </div>
		</div>










			<%-- <form:form action="registration" name="registration" id="registration" modelAttribute="user" method="POST">

				<label>User Name :</label>
					<form:input name="username" id="username" path="userName"/>
				<br>
				<label>First Name :</label>
					<form:input name="password" id="password" path="password"/>
				<label></label>
				<input type="submit" value="Save" class="registbutton" />
			</form:form>
		</div> --%>
	</body>
		<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/validation.js"></script>
</html>
