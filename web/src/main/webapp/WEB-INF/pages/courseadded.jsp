<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

    <title><fmt:message key="courseadded.title"/></title>

    <link rel="stylesheet" href="css/logFormStyles.css"/>
</head>
<body>
<div class="container">

<form name="userForm" method="GET" action="controller">
    <input type="hidden" name="command" value="showcourses" />
    <b><fmt:message key="courseadded.congrats"/> </b><br>
    <span style="color:#58D3F7"><fmt:message key="courseadded.course"/>
     ${courseName} (<fmt:message key="courseadded.price"/> ${coursePrice})
        <fmt:message key="courseadded.added"/><br></span>
    <fmt:message key="courseadded.newmenu"/>
    <button type="submit"><fmt:message key="courseadded.showcoursesbutton"/> </button>
</form>
</div>
</body>
</html>
