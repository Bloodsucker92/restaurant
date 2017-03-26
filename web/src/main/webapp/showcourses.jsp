
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List of all the courses available</title>
    <link rel="stylesheet" href="css/tableStyles.css"/>
</head>
<body>

<table border="1" align="center">
    <tr>
        <th>COURSE</th>
        <th>PRICE</th>
        <th>TYPE</th>
    </tr>
    <c:forEach var="course" items="${requestScope.courseList}" varStatus="i">
        <tr>
            <td align="center">
            <c:out value="${course.courseName}"></c:out>
            </td>
            <td align="center">
                <c:out value="${course.coursePrice}"></c:out>
            </td>
            <td align="center">
                <c:out value="${course.courseType}"></c:out>
            </td>

        </tr>
    </c:forEach>
</table>
</body>
</html>
