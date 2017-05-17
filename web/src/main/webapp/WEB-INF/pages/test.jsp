<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>--%>
<%--<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>--%>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%--<%@ page isELIgnored="false"%>--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Home page</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="assets/css/indexPageStyle.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"/>
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous"/>
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>


    <head>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <title>Home page</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="assets/css/indexPageStyle.css"/>

        <link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
    </head>

<body>
<div id="mainWrapper">
    <div class="login-container">
        <div class="login-card">
            <div class="login-form">
                <c:url var="loginUrl" value="/auth" />
                <form action="${loginUrl}" method="post" class="form-horizontal">
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
                    <div class="input-group input-sm">
                        <label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
                        <input type="text" class="form-control" id="username" name="login" placeholder="Enter Username" required>
                    </div>
                    <div class="input-group input-sm">
                        <label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
                    </div>
                    <div class="input-group input-sm">
                        <div class="checkbox">
                            <label><input type="checkbox" id="rememberme" name="remember-me"> Remember Me</label>
                        </div>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />

                    <div class="form-actions">
                        <input type="submit"
                               class="btn btn-block btn-primary btn-default" value="Log in">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>

<%--</head>--%>
<%--<body style="background: #FFF0B1;">--%>

<%--<div class="login-form">--%>



    <%--<form:form class="form-inline" action="${pageContext.request.contextPath}/auth" method="post">--%>

        <%--<div class="form-group">--%>
            <%--<label class="sr-only" for="login">Login</label>--%>
            <%--<input type="text" class="form-control" name="login" id="login" placeholder="Login">--%>
        <%--</div>--%>
        <%--<div class="form-group">--%>
            <%--<label class="sr-only" for="password">Password</label>--%>
            <%--<input type="password" class="form-control" name="password" id="password"--%>
                   <%--placeholder="Password">--%>
        <%--</div>--%>
        <%--&lt;%&ndash;<input type="hidden"&ndash;%&gt;--%>
               <%--&lt;%&ndash;name="${_csrf.parameterName}"&ndash;%&gt;--%>
               <%--&lt;%&ndash;value="${_csrf.token}"/>&ndash;%&gt;--%>
        <%--<button type="submit" class="btn btn-success">Sign in</button>--%>
    <%--</form:form>--%>
<%--</div>--%>

<%--</body>--%>
<%--</html>--%>
