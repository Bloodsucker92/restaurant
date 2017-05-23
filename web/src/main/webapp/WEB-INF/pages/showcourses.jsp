<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
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
    <link rel="stylesheet" href="assets/css/tableStyles.css"/>
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
                <script>
                    function deleteCourse(courseId) {

                        var token = $("meta[name='_csrf']").attr("content");
                        var header = $("meta[name='_csrf_header']").attr("content");
                        $.ajax({
                            url: '${pageContext.request.contextPath}/admin/courses/' + courseId,
                            type: 'DELETE',
                            beforeSend: function(xhr) {
                                xhr.setRequestHeader(header, token);
                            },
                            success: function(result) {
                                $('#' + courseId).remove();
                            }
                        })
                    }
                </script>
                <c:forEach var="course" items="${requestScope.courseList}" varStatus="i">
                    <tr id="${course.id}">
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
                                <button id="deleteBtn" type="button" class="btn btn-danger"
                                        onclick="deleteCourse('${course.id}')">
                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                </button>
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
                  id="course-pagination-form" name="course-pagination-form" method="get"
                  style="padding-left: 50px;">
                <ul class="pagination">
                    <c:forEach begin="1" end="${noOfPages}" var="i">
                        <c:choose>
                            <c:when test="${page eq i}">
                                <li class="active"><a
                                        href="${pageContext.request.contextPath}/admin/courses/page/${i}">${i}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="${pageContext.request.contextPath}/admin/courses/page/${i}">${i}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </ul>
                <ul>
                    <c:choose>
                    <c:when test="${empty sessionScope.sortingOrder}">
                        <li class="active">
                            Sort by:
                            <input type="hidden" name="sort" value="price">
                            <input type="hidden" name="order" value="asc">
                            <button class="btn btn-info" type="submit">
                                Price
                                </button>
                        </li>
                    </c:when>
                        <c:when test="${sessionScope.sortingOrder eq 'desc'}">
                            <li class="active">
                                Sort by:
                                <span class="glyphicon glyphicon-arrow-down" aria-hidden="true"></span>
                                <input type="hidden" name="sort" value="price">
                                <input type="hidden" name="order" value="asc">
                                <button class="btn btn-info" type="submit">
                                    Price
                                </button>
                            </li>
                        </c:when>
                    <c:otherwise>
                        <li class="active">
                            Sort by:
                            <span class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span>
                            <input type="hidden" name="sort" value="price">
                            <input type="hidden" name="order" value="desc">
                            <button class="btn btn-info" type="submit" value="Price">
                                Price
                                </button>
                        </li>
                    </c:otherwise>
                    </c:choose>
                </ul>
            </form>
        </div>
        <div class="col-md-1"></div>
        <div class="col-md-1">
            <form name="pagination-select" method="get"
                  action="${pageContext.request.contextPath}/admin/courses/page/1">
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
