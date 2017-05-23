<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%--Check the session scope to set the proper locale--%>
    <fmt:setBundle basename="locale_en" />
    <c:if test="${sessionScope.locale == 'locale_en' or empty sessionScope.locale}" >
        <fmt:setBundle basename="locale_en" />
    </c:if>
    <c:if test="${sessionScope.locale == 'locale_ru'}">
        <fmt:setBundle basename="locale_ru" />
    </c:if>

    <title><fmt:message key="login.title"/></title>
    <link rel="stylesheet" href="css/courseDisplayStyle.css">

    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>

    <c:choose>
        <c:when test="${sessionScope.userType eq 'GUEST'}">
            <jsp:include page="navbar.jsp"></jsp:include>
        </c:when>
        <c:otherwise>
            <jsp:include page="userNavbar.jsp"></jsp:include>
        </c:otherwise>
    </c:choose>
    <%--<c:choose>--%>
        <%--<c:when test="${sessionScope.orderCount eq null}">--%>
            <%--<c:set var="orderCount" value="0" scope="session"></c:set>--%>
        <%--</c:when>--%>
        <%--<c:otherwise>--%>
            <%--<c:set var="orderCount" value="${sessionScope.orderCount}" scope="session"></c:set>--%>
        <%--</c:otherwise>--%>
    <%--</c:choose>--%>


</head>
<body style="background: #FFF0B1;">
<%--${sessionScope}--%>
<%--<style>--%>


<%--</style>--%>

<script type="text/javascript">
    function alertFunction() {
        alert("You have to sign up or register to add item to a cart");
    }
</script>


<div id="pizza-menu">
    <!--Hawaiian pizza-->
    <div class="container">
        <div class="row">
            <c:forEach var="pizza" items="${requestScope.courseList}" varStatus="i">
            <div class="col-md-4">
                <div class="tilt">
                    <a href="#"><img src="${pizza.imgPath}" alt="..." class="img-circle"></a>
                </div>
                <br>
                <h4>${pizza.courseName}</h4>
                <p>$${pizza.coursePrice}</p>


                <div class="quantity">
                    <c:choose>
                        <c:when test="${sessionScope.userType eq 'GUEST'}">
                    <button class="plus-btn" type="button" name="button" value="Add to cart" onclick="alertFunction()">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                        Add to cart
                        </button>
                        </c:when>
                        <c:otherwise>
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="makeorder">
                                <input type="hidden" name="define" value="${sessionScope.define}">
                                <input type="hidden" name="orderCount" value="0">
                                <input type="hidden" name="courseId" value="${pizza.id}">
                            <button class="plus-btn" type="submit" name="button" value="Add to cart">
                                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                Add to cart
                            </button>
                            </form>
                        </c:otherwise>
                        </c:choose>
                </div>
            </div>
            </c:forEach>

</body>
</html>
