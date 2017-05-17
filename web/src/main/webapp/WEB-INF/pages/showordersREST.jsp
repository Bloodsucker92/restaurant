<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
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
    <script type="text/javascript" src="assets/js/ajaxDeleteItemFromOrder.js"></script>
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

        <script>
            function deleteItem() {

                var token = $("meta[name='_csrf']").attr("content");
                var header = $("meta[name='_csrf_header']").attr("content");
                var orderId = $("#orderId").val();
                var courseId = $("#courseId").val();
                $.ajax({
                    url: '/orders/' + orderId + '/courses/' + courseId,
                    type: 'DELETE',
                    beforeSend: function (xhr) {
                        xhr.setRequestHeader(header, token);
                    },
                    success: function (result) {
                        // Do something with the result
                    }
                })
            }
        </script>

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

                    <form id="deleteButtonForm">
                        <button id="deleteBtn" type="submit" class="btn btn-danger" onclick="deleteItem()">
                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                            <input type="hidden" id="orderId" value="${order.id}">
                            <input type="hidden" id="courseId" value="${course.id}">
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

