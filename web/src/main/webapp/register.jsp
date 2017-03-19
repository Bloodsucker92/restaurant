<%--
  Created by IntelliJ IDEA.
  User: dzianismitrakhovich
  Date: 13.02.17
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Register form</title>
    <link rel="stylesheet" href="css/logFormStyles.css"/>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
    <script type="text/javascript" src="js/regvalid.js"></script>
</head>
<body>
<div class="container">
<form id="reg-form" name="registerForm" method="POST" action="controller">
    <c:if test="${guest}">
        <span style="color:#58D3F7"><p>There is no user registered under that name. Please sign up.</p><br></span>
    </c:if>
    <input type="hidden" name="command" value="register" />
    Enter your login:<br/>
    <input type="text" name="loginReg" id="loginReg" value="" placeholder="Login"/>
    <br/>Enter your password:<br/>
    <input type="password" name="passwordReg" id="passwordReg" value="" placeholder="Password"/>
    <br/>
    ${errorLoginPassMessage}
    <br/>
    ${wrongAction}
    <br/>
    ${nullPage}
    <br/>
    <button type="submit">Register</button>
</form><hr/>
</div>
</body>
</html>
