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
    <title><fmt:message key="register.title"/></title>
    <link rel="stylesheet" href="css/logFormStyles.css"/>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
    <script type="text/javascript" src="js/regvalid.js"></script>
</head>
<body>
<div class="container">
<form id="reg-form" name="registerForm" method="POST" action="controller">
    <c:if test="${guest}">
        <span style="color:#58D3F7"><p><fmt:message key="register.nouser"/></p><br></span>
    </c:if>
    <input type="hidden" name="command" value="register" />
    <fmt:message key="register.login"/><br/>
    <input type="text" name="loginReg" id="loginReg" value="" placeholder="<fmt:message key="register.loginplaceholder"/>"/>
    <br/><fmt:message key="register.pass"/> <br/>
    <input type="password" name="passwordReg" id="passwordReg" value="" placeholder="<fmt:message key="register.passwordplaceholder"/> "/>
    <br/>
    ${errorLoginPassMessage}
    <br/>
    ${wrongAction}
    <br/>
    ${nullPage}
    <br/>
    <button type="submit"><fmt:message key="register.loginbutton"/> </button>
</form><hr/>
</div>
</body>
</html>
