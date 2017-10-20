<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <fmt:setBundle basename="locale_us"/>
    <c:if test="${sessionScope.locale == 'locale_us' or empty sessionScope.locale}">
        <fmt:setBundle basename="locale_us"/>
    </c:if>
    <c:if test="${sessionScope.locale == 'locale_ru'}">
        <fmt:setBundle basename="locale_ru"/>
    </c:if>

    <title><spring:message code="course.title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/courseDisplayStyle.css">


    <sec:authorize access="hasRole('ROLE_USER')">
        <jsp:include page="../pages/userNavbar.jsp"></jsp:include>
    </sec:authorize>
    <sec:authorize access="isAnonymous()">
        <jsp:include page="../pages/navbar.jsp"></jsp:include>
    </sec:authorize>

</head>

<body style="background: #FFF0B1;">


<script type="text/javascript">
    function alertFunction() {
        alert("You have to sign up or register to add item to a cart");
    }
</script>


<div id="pizza-menu">

    <div class="container">
        <div class="row">
            <c:forEach var="course" items="${courseList}" varStatus="i">
            <div class="col-md-4">
                <div class="tilt">
                    <a href="#"><img src="${course.imgPath}" alt="..." class="img-circle"></a>
                </div>
                </br>
                <h4>${course.courseName}</h4>
                <p>$${course.coursePrice}</p>


                <div class="quantity">
                    <c:choose>
                        <c:when test="${sessionScope.userType eq 'GUEST'}">
                            <button class="plus-btn" type="button" name="button" value="Add to cart"
                                    onclick="alertFunction()">
                                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                Add to cart
                            </button>
                        </c:when>
                        <c:otherwise>
                            <sec:authorize access="hasRole('ROLE_USER')">
                                <c:if test="${courseCategory eq 'pizza'}">
                                    <c:url var="courseUrl" value="/pizza/${course.id}"/>
                                </c:if>
                                <c:if test="${courseCategory eq 'drink'}">
                                    <c:url var="courseUrl" value="/drinks/${course.id}"/>
                                </c:if>
                                <c:if test="${courseCategory eq 'soup'}">
                                    <c:url var="courseUrl" value="/soup/${course.id}"/>
                                </c:if>
                                <c:if test="${courseCategory eq 'dessert'}">
                                    <c:url var="courseUrl" value="/dessert/${course.id}"/>
                                </c:if>
                                <c:if test="${courseCategory eq 'other'}">
                                    <c:url var="courseUrl" value="/other/${course.id}"/>
                                </c:if>

                                <form action="${courseUrl}" method="post">
                                    <input type="hidden" name="courseCategory" value="${courseCategory}">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                        <%--<input type="hidden" name="courseId" value="${course.id}">--%>
                                    <button class="plus-btn" type="submit" name="button" value="Add to cart">
                                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                        Add to cart
                                    </button>
                                </form>
                            </sec:authorize>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            </c:forEach>

</body>
</html>
