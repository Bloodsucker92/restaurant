<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html><head><title>Welcome admin</title>
 <link rel="stylesheet" href="css/logFormStyles.css"/></head>
<body>
<div class="container">
<form name="userForm" method="GET" action="controller">
 <input type="hidden" name="command" value="showcourses" />
 <h3 align="center">Welcome, ${user}!</h3>
 <hr/><br>
 <span style="color:#58D3F7"><h5 align="center">You've just logged in as administrator</h5></span>
 <hr/><br/>
 <button type="submit">Show all courses</button>
</form>
<form action="addcourse.jsp">
 <input type="submit" value="Add a new course" />
</form>
<a href="controller?command=logout">Logout</a>
</div>
</body></html>