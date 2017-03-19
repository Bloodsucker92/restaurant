<%--
  Created by IntelliJ IDEA.
  User: dzianismitrakhovich
  Date: 20.02.17
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adding a new course</title>
    <link rel="stylesheet" href="css/logFormStyles.css"/>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
    <script type="text/javascript" src="js/newcoursevalid.js"></script>
</head>
<body>
<div class="container">
<form id="course-add-form" name="newCourseForm" method="POST" action="controller">
    <input type="hidden" name="command" value="addcourse" />
    Enter course name:<br/>
    <input type="text" id="courseName" name="courseName" value="" placeholder="Course"/>
    <br/>Enter course price:<br/>
    <input type="text" id="coursePrice" name="coursePrice" value="" placeholder="Price"/>
    <br/> Choose category:<br/>
    <select name="dropDownList">
        <option value="appetizer">appetizer</option>
        <option value="drink">drink</option>
        <option value="desert">desert</option>
        <option value="main course">main course</option>
        <option value="soup">soup</option>
        <option value="pizza">pizza</option>
    </select><br>
    <button type="submit">Add</button>
    </form>
</div>
</body>
</html>
