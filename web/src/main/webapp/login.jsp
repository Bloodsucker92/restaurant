<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
 <title>Login</title>
 <link rel="stylesheet" href="css/logFormStyles.css"/>
 <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/1.12.4/jquery.min.js"></script>
 <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
 <script type="text/javascript" src="js/loginvalid.js"></script>
</head>
<%--<script type="text/javascript" src="js/validation.js"></script>--%>

<body>
<div class="container">
<form id="login-form" name="loginForm" method="POST" action="controller">
<input type="hidden" name="command" value="login" />
Login:<br/>
<input type="text" name="login" id="login" value="" placeholder="Your login"/>
<br/>Password:<br/>
<input type="password" name="password" id="password" value="" placeholder="Your password"/>
 <br/>
${errorLoginPassMessage}
 <br/>
${wrongAction}
 <br/>
${nullPage}
 <br/>
<button type="submit">Log in</button>
 <%--onclick="return validateForm()"/>--%>
</form><hr/>
<form name="registerForm" method="post" action="register.jsp">
 <br/>Don't have the account yet?<br/>
 <button type="submit">Register now</button>
 <br/>
</form>
 </div>
</body></html>