<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%--Check the session scope to set the proper locale--%>
    <fmt:setBundle basename="locale_us" />
 <c:if test="${sessionScope.locale == 'locale_us' or empty sessionScope.locale}" >
  <fmt:setBundle basename="locale_us" />
 </c:if>
 <c:if test="${sessionScope.locale == 'locale_ru'}">
  <fmt:setBundle basename="locale_ru" />
 </c:if>

 <title><fmt:message key="login.title"/></title>
 <link rel="stylesheet" href="css/logFormStyles.css"/>
 <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/1.12.4/jquery.min.js"></script>
 <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
 <script type="text/javascript" src="js/loginvalid.js"></script>
</head>

<body>
<%--Include header.jsp which enables locale choice--%>
<div class="header">
    <jsp:include page="header.jsp"></jsp:include>
</div>
<%--The main login form--%>
<div class="container">
<form id="login-form" name="loginForm" method="POST" action="controller">
<input type="hidden" name="command" value="login" />
 <fmt:message key="login.login"></fmt:message><br/>
<input type="text" name="login" id="login" value="" placeholder="<fmt:message key="login.loginplaceholder"/>"/><br>
    <fmt:message key="login.pass"></fmt:message><br/>
<input type="password" name="password" id="password" value="" placeholder="<fmt:message key="login.passwordplaceholder"/>"/>
 <br/>
${errorLoginPassMessage}
 <br/>
${wrongAction}
 <br/>
${nullPage}
 <br/>
<button type="submit"><fmt:message key="login.loginbutton"/></button>
 <%--onclick="return validateForm()"/>--%>
</form><hr/>
<form name="registerForm" method="post" action="register.jsp">
 <br/><fmt:message key="login.loginnewaccount"/><br/>
 <button type="submit"><fmt:message key="login.registerbutton"/></button>
 <br/>
 ${sessionScope.locale}
</form>
 </div>
</body></html>