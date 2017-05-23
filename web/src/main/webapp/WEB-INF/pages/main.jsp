<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html><head>
 <%--Check the session scope to set the proper locale--%>
 <fmt:setBundle basename="locale_en" />
 <c:if test="${sessionScope.locale == 'locale_en' or empty sessionScope.locale}" >
  <fmt:setBundle basename="locale_en" />
 </c:if>
 <c:if test="${sessionScope.locale == 'locale_ru'}">
  <fmt:setBundle basename="locale_ru" />
 </c:if>
 <title><spring:message code="main.title"></spring:message> </title>
 <link rel="stylesheet" href="assets/css/logFormStyles.css"/>
  <link rel="stylesheet" href="assets/css/tableStyles.css"/>
  <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/1.12.4/jquery.min.js"></script>
  <script type="text/javascript"
          src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script></head>


<body style="background: #FFF0B1;">
<div class="container">

<form name="userForm" method="GET" action="${pageContext.request.contextPath}/admin/courses/page/1">
 <h3 align="center"><spring:message code="main.welcomemessage"/>${requestScope.user}!</h3>
 <hr/><br>
 <span style="color:#58D3F7"><h5 align="center"><fmt:message key="main.youhaveloggedin"/></h5></span>
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

</body></html>