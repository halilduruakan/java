<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>

<html>
	<head>
		<title>Login</title>
		
		<link type="text/css"
			  rel="stylesheet"
			  href="${pageContext.request.contextPath}/resources/css/style.css">
			  
	</head>
	<body>
		<div id="wrapper">
			<div id="header">
				<h2>CRM - Login</h2>
			</div>
		</div>
		
		<div id="container">
			<h3>Login</h3>
			
			<c:if test="${param.error != null}">
                 <div class="alert alert-danger">
                     <p>Invalid username and password.</p>
                 </div>
             </c:if>
             <c:if test="${param.logout != null}">
                 <div class="alert alert-success">
                     <p>You have been logged out successfully.</p>
                 </div>
            </c:if>
			
			<form action="login" method="POST">	
				<label>User Name :</label>
				<input type="text" id="username" name="username"/>
				<label>Password :</label>
				<input type="password" id="password" name="password"/>
				<label></label>
				
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				
				<input type="submit" value="Save" class="save" />
			</form>
		
		</div>
	</body>
</html>