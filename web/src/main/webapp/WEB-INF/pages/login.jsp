<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <%--Check the session scope to set the proper locale--%>
    <fmt:setBundle basename="locale_en"/>
    <c:if test="${sessionScope.locale == 'locale_en' or empty sessionScope.locale}">
        <fmt:setBundle basename="locale_en"/>
    </c:if>
    <c:if test="${sessionScope.locale == 'locale_ru'}">
        <fmt:setBundle basename="locale_ru"/>
    </c:if>

    <title><spring:message code="login.title"/></title>
    <link rel="stylesheet" href="assets/css/logFormStyles.css"/>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript"
            src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>

</head>

<body>
<%--Include header.jsp which enables locale choice--%>
<div class="header">
    <jsp:include page="header.jsp"></jsp:include>
</div>
<%--The main login form--%>
<div class="container">
    <c:if test="${param.logout != null}">
        <div class="alert alert-success">
            <p><spring:message code="login.logout"/> </p>
        </div>
    </c:if>
    <c:url var="loginUrl" value="/auth"/>
    <form id="login-form" name="loginForm" method="POST" action="${loginUrl}">
        <c:if test="${param.error != null}">
            <div class="alert alert-danger">
                <p><spring:message code="login.error"/> </p>
            </div>
        </c:if>
        <spring:message code="login.login"/><br/>
        <input type="text" name="login" id="login" value=""
               placeholder="<spring:message code="login.loginplaceholder"/>"/><br>
        <spring:message code="login.pass"/><br/>
        <input type="password" name="password" id="password" value=""
               placeholder="<spring:message code="login.passwordplaceholder"/>"/>
        <br/>
        ${errorLoginPassMessage}
        <br/>
        ${wrongAction}
        <br/>
        ${nullPage}
        <br/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit"><spring:message code="login.loginbutton"/>
    </form>
    <hr/>
    <form name="registerForm" method="get" action="${pageContext.request.contextPath}/register">
        <br/><spring:message code="login.loginnewaccount"/><br/>
        <button type="submit"><spring:message code="login.registerbutton"/></button>
        <br/>
        ${sessionScope.locale}
    </form>
</div>
</body>
</html>