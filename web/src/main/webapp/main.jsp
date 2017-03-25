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
 <title><fmt:message key="main.title"/></title>
 <link rel="stylesheet" href="css/logFormStyles.css"/></head>


<body>
<div class="container">
<form name="userForm" method="GET" action="controller">
 <input type="hidden" name="command" value="showcourses" />
 <h3 align="center"><fmt:message key="main.welcomemessage"/>${user}!</h3>
 <hr/><br>
 <span style="color:#58D3F7"><h5 align="center"><fmt:message key="main.youhaveloggedin"/></h5></span>
 <hr/><br/>
 <button type="submit"><fmt:message key="main.showcoursesbutton"/> </button>
</form>
<form action="addcourse.jsp">
 <input type="submit" value="<fmt:message key="main.addnewcourse"/>"/>
</form>
 <form name="showUsersForm" method="GET" action="controller">
  <input type="hidden" name="command" value="showusers" />
  <input type="submit" value="<fmt:message key="main.showusers"/>"/>
 </form>
<a href="controller?command=logout"><fmt:message key="main.logout"/> </a>
</div>

</body></html>