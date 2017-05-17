<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title><fmt:message key="deleteuser.title"/></title>
    <%--<link rel="stylesheet" href="css/logFormStyles.css"/>--%>
    <%--<link rel="stylesheet" href="css/tableStyles.css"/>--%>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript"
            src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>

    <jsp:include page="userNavbar.jsp"></jsp:include>


</head>

<body style="background: #FFF0B1;">
<div class="container">
    <h3>Order list of user ${order.user.username}</h3>
    <c:if test="${requestScope.order==null}">
        You have made no orders yet.
    </c:if>
    <table class="table table-hover" border="1" align="center" style="background: #F3F3F3; width: 70%;">
        <tr>
            <th align="center">Course</th>
            <th align="center">Price</th>
            <th align="center">Category</th>
            <th></th>
        </tr>

        <c:forEach var="course" items="${courseList}">
            <tr>
                <td align="center">
                    <c:out value="${course.courseName}"></c:out>
                </td>
                <td align="center">
                    <c:out value="${course.coursePrice}"></c:out>
                </td>
                <td align="center">
                    <c:out value="${course.courseCategory.courseCategory}"></c:out>
                </td>
                <td align="center">

                    <form action="${pageContext.request.contextPath}/orders/${order.id}/courses/${course.id}" method="get">
                        <button id="deleteBtn" type="submit" class="btn btn-danger"
                                onclick="return confirm('Are you sure you want to delete this order?')">
                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                        </button>
                    </form>
                </td>
            </tr>

        </c:forEach>
    </table>
</div>

<div class="container">
    <c:if test="${requestScope.userDeleted}">
        <fmt:message key="deleteuser.userdeleted"/>
    </c:if>

    <hr/>
</div>
</body>
</html>

