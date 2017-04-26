<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <%--<link rel="stylesheet" href="css/tableStyles.css"/>--%>
    <%--Check the session scope to set the proper locale--%>
    <fmt:setBundle basename="locale_us"/>
    <c:if test="${sessionScope.locale == 'locale_us' or empty sessionScope.locale}">
        <fmt:setBundle basename="locale_us"/>
    </c:if>
    <c:if test="${sessionScope.locale == 'locale_ru'}">
        <fmt:setBundle basename="locale_ru"/>
    </c:if>
    <title><fmt:message key="deleteuser.title"/></title>
    <%--<link rel="stylesheet" href="css/logFormStyles.css"/>--%>
    <link rel="stylesheet" href="css/tableStyles.css"/>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript"
            src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
    <jsp:include page="adminNavbar.jsp"></jsp:include>
</head>
<body style="background: #FFF0B1;">

<div class="container">
    <div class="row">
        <div class="col-md-3">
        </div>
        <div class="col-md-6">
            <table border="1" align="center" class="table table-striped">
                <tr>
                    <th>COURSE</th>
                    <th>PRICE</th>
                    <th>TYPE</th>
                    <th></th>
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
                            <c:out value="${course.courseCategory.courseCategory}"></c:out>
                        </td>
                        <td align="center">
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="deletecourse" id="deleteCourseCommand"/>
                                <input type="hidden" name="id" value="${course.id}" id="id"/>
                                <button id="deleteBtn" type="submit" class="btn btn-danger"
                                        onclick="return confirm('Are you sure you want to delete this course?')">
                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="col-md-3">
        </div>
    </div>
</div>


<div class="container">
    <div class="row">
        <div class="col-md-3">
        </div>
        <div class="col-md-3">
            <form class="form-inline"
                  id="course-pagination-form" name="course-pagination-form" method="get" action="controller"
                  style="padding-left: 50px;">
                <input type="hidden" name="command" value="showcourses"/>
                <ul class="pagination">
                    <c:forEach begin="1" end="${noOfPages}" var="i">
                        <c:choose>
                            <c:when test="${page eq i}">
                                <li class="active"><a href="controller?command=showcourses&page=${i}">${i}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="controller?command=showcourses&page=${i}">${i}</a></li>
                            </c:otherwise>
                        </c:choose>

                    </c:forEach>
                </ul>
            </form>
        </div>
        <div class="col-md-1"></div>
        <div class="col-md-1">
            <form name="pagination-select" method="get" action="controller">
                <input type="hidden" name="command" value="showcourses"/>
            <div class="select">
                <label for="itemsPerPage">Items per page:</label>
                <select class="form-control" id="itemsPerPage" name="itemsPerPage">
                    <option>5</option>
                    <option>10</option>
                    <option>20</option>
                    <option>50</option>
                </select>
                <script type="text/javascript">
                    document.getElementById('itemsPerPage').value = "${itemsPerPage}";
                </script>
            </div>
            <br>
            <button class="btn btn-success" type="submit">Display</button>
            </form>
        </div>
        <div class="col-md-1"></div>
    </div>
    </form>
</div>
<div class="col-md-3"></div>
</div>
</div>


<div class="container">
    <c:if test="${requestScope.courseDeleted}">
    Course has been successfully deleted
    </c:if>
    <div class="container">
        <c:if test="${requestScope.courseAdded}">
        Course was added
        </c:if>
</body>
</html>
