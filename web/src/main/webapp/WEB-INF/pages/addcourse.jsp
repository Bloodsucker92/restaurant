<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <%--Check the session scope to set the proper locale--%>
    <fmt:setBundle basename="locale_en"/>
    <c:if test="${sessionScope.locale == 'locale_en' or empty sessionScope.locale}">
        <fmt:setBundle basename="locale_en"/>
    </c:if>
    <c:if test="${sessionScope.locale == 'locale_ru'}">
        <fmt:setBundle basename="locale_ru"/>
    </c:if>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
              integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
              integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

        <!-- Bootstrap validation styles -->

        <link rel="stylesheet" href="assets/css/bootstrapValidator.min.css"/>

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
                integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
                crossorigin="anonymous"></script>
        <script src="assets/js/bootstrapValidator.min.js"></script>
    <jsp:include page="adminNavbar.jsp"></jsp:include>
</head>
<body style="background: #FFF0B1;">
<div class="container">
    <div class="row">
        <div class="panel panel-default" style="width: 65%">
            <div class="panel-heading">Add new course</div>
            <div class="panel-body">

    <form:form id="course-add-form" name="newCourseForm" method="post" class="form-horizontal"
          action="${pageContext.request.contextPath}/admin/courses" commandName="course">
        <input type="hidden" name="itemsPerPage" value="5">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      <br/>

        <div class="form-group">
            <form:label path="courseName" class="col-md-2 control-label" for="courseName"><spring:message
                    code="addcourse.entercoursename"/><br/></form:label>
            <div class="col-md-4">
        <form:input path="courseName" type="text" id="courseName" name="courseName" value=""
               placeholder='Course name'/>
        <form:errors path="courseName" class="help-inline" cssClass="error"></form:errors>
                </div>
            </div>

        <br/> <br/>

        <div class="form-group">
            <form:label path="coursePrice" class="col-md-2 control-label" for="coursePrice"><spring:message
                    code="addcourse.entercourseprice"/><br/></form:label>
            <div class="col-md-4">
        <form:input path="coursePrice" type="text" id="coursePrice" name="coursePrice" value=""
               placeholder="Course price"/>
        <form:errors path="coursePrice" class="help-inline" cssClass="error"></form:errors>
                </div>
            </div>

        <br/> <spring:message code="addcourse.choosecategory"/> <br/>
        <select name="courseCategory" id="courseCategory">
            <option value="1">pizza</option>
            <option value="2">drink</option>
            <option value="3">soup</option>
            <option value="4">dessert</option>
            <option value="5">other</option>
        </select><br>
        <button type="submit"><spring:message code="addcourse.addbutton"/></button>
    </form:form>
</div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript" src="assets/js/newcoursevalid.js"></script>
</html>
