<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <%--Check the session scope to set the proper locale--%>
    <fmt:setBundle basename="locale_en"/>
    <c:if test="${sessionScope.locale == 'locale_en' or empty sessionScope.locale}">
        <fmt:setBundle basename="locale_en"/>
    </c:if>
    <c:if test="${sessionScope.locale == 'locale_ru'}">
        <fmt:setBundle basename="locale_ru"/>
    </c:if>
    <title><fmt:message key="deleteuser.title"/></title>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/1.12.4/jquery.min.js"></script>
    <%--<script type="text/javascript"--%>
    <%--src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.vali.."></script>--%>
    <jsp:include page="adminNavbar.jsp"></jsp:include>
</head>
<body style="background: #FFF0B1;">
<table class="table table-hover" border="1" align="center" style="background: #F3F3F3; width: 70%;">
    <tr>
        <th>Username</th>
        <th>Password</th>
        <th>Id</th>
        <th></th>
    </tr>

    <script>
        function deleteUser(username) {

            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");

            $.ajax({
                url: '${pageContext.request.contextPath}/admin/users/' + username,
                type: 'DELETE',
                beforeSend: function (xhr) {
                    xhr.setRequestHeader(header, token);
                },
                success: function (result) {
                    $('#' + username).remove();
                }
            })
        }
    </script>

    <c:forEach var="user" items="${usersList}" varStatus="i">
        <tr id="${user.username}">
            <td align="center">
                <c:out value="${user.username}"></c:out>
            </td>
            <td align="center">
                <c:out value="${user.userpassword}"></c:out>
            </td>
            <td align="center">
                <c:out value="${user.id}"></c:out>
            </td>
            <td align="center">

                <button id="deleteBtn" class="btn btn-danger"
                        onclick="deleteUser('${user.username}')">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                </button>
            </td>
        </tr>
    </c:forEach>

</table>

<div class="container">
    <c:if test="${requestScope.userDeleted}">
        <fmt:message key="deleteuser.userdeleted"/>
    </c:if>
    <hr/>
</div>
</body>
</html>