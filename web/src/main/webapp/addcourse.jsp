
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

    <link rel="stylesheet" href="css/logFormStyles.css"/>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
    <script type="text/javascript" src="js/newcoursevalid.js"></script>
</head>
<body>
<div class="container">
<form id="course-add-form" name="newCourseForm" method="POST" action="controller">
    <input type="hidden" name="command" value="addcourse" />
    <fmt:message key="addcourse.entercoursename"/><br/>
    <input type="text" id="courseName" name="courseName" value="" placeholder="<fmt:message key="addcourse.courseplaceholder"/> "/>
    <br/> <fmt:message key="addcourse.entercourseprice"/> <br/>
    <input type="text" id="coursePrice" name="coursePrice" value="" placeholder="<fmt:message key="addcourse.priceplaceholder"/>"/>
    <br/> <fmt:message key="addcourse.choosecategory"/> <br/>
    <select name="dropDownList">
        <option value="appetizer">appetizer</option>
        <option value="drink">drink</option>
        <option value="desert">desert</option>
        <option value="main course">main course</option>
        <option value="soup">soup</option>
        <option value="pizza">pizza</option>
    </select><br>
    <button type="submit"><fmt:message key="addcourse.addbutton"/></button>
    </form>
</div>
</body>
</html>
