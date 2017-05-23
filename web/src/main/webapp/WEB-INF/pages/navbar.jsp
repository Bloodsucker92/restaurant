<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Navbar</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/navbarStyle.css"/>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

</head>
<body>


<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand">Denmi's Pizza</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="home"><spring:message code="user.home"/> <span class="sr-only">(current)</span></a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false"><spring:message code="user.language"/><span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="?locale=en">ENG</a></li>
                        <li><a href="?locale=ru">RUS</a></li>
                    </ul>
                </li>
            </ul>

            <c:url var="registerUrl" value="/register"/>

            <form class="navbar-form navbar-right" method="get" action="${registerUrl}">
                    <button type="submit" class="btn btn-default"><spring:message code="register.registerbutton"/> </button>
            </form>

            <c:url var="loginUrl" value="/auth"/>

                <form action="${loginUrl}" method="post" class="navbar-form navbar-right" style="padding-right:5px;">

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
                        <div class="form-group">
                            <label class="sr-only" for="login"><spring:message code="login.login"/> </label>
                            <input type="text" class="form-control" name="login" id="login" placeholder="Login">
                        </div>
                        <div class="form-group">
                            <label class="sr-only" for="password"><spring:message code="login.pass"/> </label>
                            <input type="password" class="form-control" name="password" id="password"
                                   placeholder="Password">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </div>
                        <input type="submit" class="btn btn-success" value="<spring:message code="login.signin"/>"/>

                </form>


        </div>
    </div>
</nav>


</body>
</html>
