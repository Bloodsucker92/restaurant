<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%--Check the session scope to set the proper locale--%>
    <fmt:setBundle basename="locale_us"/>
    <c:if test="${sessionScope.locale == 'locale_us' or empty sessionScope.locale}">
        <fmt:setBundle basename="locale_us"/>
    </c:if>
    <c:if test="${sessionScope.locale == 'locale_ru'}">
        <fmt:setBundle basename="locale_ru"/>
    </c:if>
    <title><fmt:message key="register.title"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Bootstrap validation styles -->

    <link rel="stylesheet" href="css/bootstrapValidator.min.css"/>

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <script src="js/bootstrapValidator.min.js"></script>


</head>
<%--<jsp:include page="navbar.jsp"></jsp:include>--%>
<body style="background: #FFF0B1;">

<div id="register">

    <div class="container">
        <div class="row">
            <div class="panel panel-default" style="width: 65%">
                <div class="panel-heading">Login</div>
                <div class="panel-body">
                    <form id="retry-login-form" name="retry-login-form" method="POST" action="controller"
                          class="form-horizontal">
                        <c:if test="${guest}">
                            <span style="color:#5c95f7"><p>Oops! Seems like you've entered the wrong login or password. Try again:</p><br></span>
                        </c:if>
                        <input type="hidden" name="command" value="login"/>
                        <div class="form-group">
                            <label class="col-md-2 control-label" for="login"><fmt:message
                                    key="register.login"/><br/></label>
                            <div class="col-md-4">
                                <input type="text" class="form-control" name="login" id="login" value=""
                                       placeholder="<fmt:message key="register.loginplaceholder"/>"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label" for="password"><fmt:message
                                    key="register.pass"/></label>
                            <div class="col-md-4">
                                <input type="password" class="form-control" name="password" id="password" value=""
                                       placeholder="<fmt:message key="register.passwordplaceholder"/> "/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-6 col-md-offset-2">
                                <button type="submit" class="btn btn-succes">Log in</button>
                            </div>
                        </div>
                    </form>
                    <hr/>
                </div>
            </div>
            <div class="panel panel-default" style="width: 65%">
                <div class="panel-heading">Registration</div>
                <div class="panel-body">
                    <form class="form-horizontal" action="register.jsp">
                        <span style="color:#5c95f7"><p>Not registered yet? Sign up here:</p><br></span>
                        <button class="btn btn-success" type="submit">
                            Register
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<script type="text/javascript" src="js/loginValidation.js">
</script>

</html>
