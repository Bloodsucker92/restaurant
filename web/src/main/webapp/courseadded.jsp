<%--
  Created by IntelliJ IDEA.
  User: dzianismitrakhovich
  Date: 20.02.17
  Time: 12:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Course added</title>
    <link rel="stylesheet" href="css/logFormStyles.css"/>
</head>
<body>
<div class="container">

<form name="userForm" method="GET" action="controller">
    <input type="hidden" name="command" value="showcourses" />
    <b>Congratulations!</b><br>
    <span style="color:#58D3F7">Your course ${courseName} (price: ${coursePrice}) was successfully added to our menu!<br></span>
    Click here to see the renewed menu:
    <button type="submit">Show all courses</button>
</form>
</div>
</body>
</html>
