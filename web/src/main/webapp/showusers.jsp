
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <%--Check the session scope to set the proper locale--%>
    <fmt:setBundle basename="locale_us" />
    <c:if test="${sessionScope.locale == 'locale_us' or empty sessionScope.locale}" >
        <fmt:setBundle basename="locale_us" />
    </c:if>
    <c:if test="${sessionScope.locale == 'locale_ru'}">
        <fmt:setBundle basename="locale_ru" />
    </c:if>
    <title><fmt:message key="deleteuser.title"/></title>
    <link rel="stylesheet" href="css/logFormStyles.css"/>
    <link rel="stylesheet" href="css/tableStyles.css"/>
</head>
<body>
<table border="1" align="center">
    <tr>
        <th>Username</th>
        <th>Password</th>
    </tr>
    <c:forEach var="user" items="${requestScope.usersList}" varStatus="i">
        <tr>
            <td align="center">
                <c:out value="${user.username}"></c:out>
            </td>
            <td align="center">
                <c:out value="${user.userpassword}"></c:out>
            </td>
        </tr>
    </c:forEach>
</table>
<div class="container">
    <c:if test="${requestScope.userDeleted}">
        <fmt:message key="deleteuser.userdeleted"/>
    </c:if>
    <form id="delete-user-form" name="deleteUserForm" method="POST" action="controller">
        <input type="hidden" name="command" value="deleteuser" />
        <fmt:message key="deleteuser.enterusername"/><br/>
        <input type="text" name="username" id="login" value="" placeholder="<fmt:message key="deleteuser.usernameplaceholder"/> "/><br>
        <br/>
        <fmt:message key="deleteuser.enterpassword"/>
        <input type="text" name="userpassword" id="login" value="" placeholder="<fmt:message key="deleteuser.userpasswordplaceholder"/> "/><br>
        <button type="submit"><fmt:message key="deleteuser.deletebutton"/></button>
        <%--onclick="return validateForm()"/>--%>
    </form><hr/>
    </div>
</body>
</html>
