
<html>
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <fmt:setBundle basename="locale_en" />
    <c:if test="${sessionScope.locale == 'locale_en' or empty sessionScope.locale}" >
        <fmt:setBundle basename="locale_en" />
    </c:if>
    <c:if test="${sessionScope.locale == 'locale_ru'}">
        <fmt:setBundle basename="locale_ru" />
    </c:if>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Admin page</title>
    <link rel="stylesheet" href="assets/css/logFormStyles.css"/>
    <link rel="stylesheet" href="assets/css/tableStyles.css"/>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript"
            src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
<jsp:include page="adminNavbar.jsp"/>
</head>
<body style="background: #FFF0B1;">
<div class="container">

    <form name="userForm" method="GET" action="${pageContext.request.contextPath}/admin/courses/page/1">
        <h3 align="center"><spring:message code="main.welcomemessage"/>!</h3>
        <hr/><br>
        <span style="color:#58D3F7"><h5 align="center"><spring:message code="main.youhaveloggedin"/></h5></span>
        <hr/><br/>
        <div class="well" style="background: #335c84;">
            <button type="submit"><spring:message code="main.showcoursesbutton"/> </button>
            <div class="select">
                <label for="itemsPerPage">Items per page:</label>
                <select class="form-control" id="itemsPerPage" name="itemsPerPage">
                    <option>5</option>
                    <option>10</option>
                    <option>20</option>
                    <option>50</option>
                </select>
            </div>
        </div>
    </form>

    <form action="${pageContext.request.contextPath}/admin/courses/add" method="get">
        <input type="submit" value="<spring:message code="main.addnewcourse"/>"/>
    </form>
    <form name="showUsersForm" method="GET" action="${pageContext.request.contextPath}/admin/users">
        <input type="submit" value="<spring:message code="main.showusers"/>"/>
    </form>
</div>

</body>
</html>
