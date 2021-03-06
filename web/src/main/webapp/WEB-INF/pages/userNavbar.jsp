<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>User Navbar</title>
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


    <%--Check the session scope to set the proper locale--%>
    <fmt:setBundle basename="locale_en"/>
    <c:if test="${sessionScope.locale == 'locale_en' or empty sessionScope.locale}">
        <fmt:setBundle basename="locale_en"/>
    </c:if>
    <c:if test="${sessionScope.locale == 'locale_ru'}">
        <fmt:setBundle basename="locale_ru"/>
    </c:if>

</head>
<body>

<%--${sessionScope}--%>

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
                <li class="active"><a href="${pageContext.request.contextPath}/home"><spring:message code="user.home"></spring:message><span class="sr-only">(current)</span></a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false"><spring:message code="user.language"></spring:message><span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="?locale=en">ENG</a></li>
                        <li><a href="?locale=ru">RUS</a></li>
                    </ul>
                </li>
            </ul>
            <%--<c:url var="ordersUrl" value="/"/>--%>
            <form class="navbar-form navbar-left" action="${pageContext.request.contextPath}/orders" method="GET">
                <button type="submit" class="btn btn-default" aria-label="Left Align" style="background:#FFF0B1;
                height: 35px; width: 50px;">
                    <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
                    <c:if test="${not empty sessionScope.orderCount}">
                        <span class="glyphicon glyphicon-asterisk" aria-hidden="true" style="color: red"></span>
                    </c:if>
                </button>
            </form>
            <c:url var="logoutUrl" value="/logout"/>
            <form class="navbar-form navbar-right" action="${logoutUrl}" method="GET">
                <input class="btn btn-danger" type="submit" style="height: 35px;" value="<spring:message code="user.logout"/>"/>
            </form>
            <form class="navbar-form navbar-right">
                <h6 align="center">Greetings, ${sessionScope.loggedinuser}!</h6>
            </form>


        </div>
    </div>
</nav>


</body>
</html>
