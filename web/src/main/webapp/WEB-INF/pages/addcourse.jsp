
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html><head>
    <%--Check the session scope to set the proper locale--%>
    <fmt:setBundle basename="locale_us" />
    <c:if test="${sessionScope.locale == 'locale_us' or empty sessionScope.locale}" >
        <fmt:setBundle basename="locale_us" />
    </c:if>
    <c:if test="${sessionScope.locale == 'locale_ru'}">
        <fmt:setBundle basename="locale_ru" />
    </c:if>

    <title><fmt:message key="addcourse.title"/></title>

    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
    <script type="text/javascript" src="js/newcoursevalid.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
              integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
              integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
                integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
                crossorigin="anonymous"></script>
    <jsp:include page="adminNavbar.jsp"></jsp:include>
</head>
<body style="background: #FFF0B1;">
<div class="container">
<form id="course-add-form" name="newCourseForm" method="POST" action="controller">
    <input type="hidden" name="command" value="addcourse" />
    <input type="hidden" name="itemsPerPage" value="5">
    <fmt:message key="addcourse.entercoursename"/><br/>
    <input type="text" id="courseName" name="courseName" value="" placeholder="<fmt:message key="addcourse.courseplaceholder"/> "/>
    <br/> <fmt:message key="addcourse.entercourseprice"/> <br/>
    <input type="text" id="coursePrice" name="coursePrice" value="" placeholder="<fmt:message key="addcourse.priceplaceholder"/>"/>
    <br/> <fmt:message key="addcourse.choosecategory"/> <br/>
    <select name="categoryOptions" id="categoryOptions">
        <option value="1">pizza</option>
        <option value="2">drink</option>
        <option value="3">soup</option>
        <option value="4">dessert</option>
        <option value="5">other</option>
    </select><br>
    <button type="submit"><fmt:message key="addcourse.addbutton"/></button>
    </form>
</div>
</body>
</html>
