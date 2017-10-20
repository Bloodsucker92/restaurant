<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<html>

    <head>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <title>Home page</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="assets/css/indexPageStyle.css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
              integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"/>
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
              integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous"/>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
                integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
                crossorigin="anonymous"></script>

    </head>
    <body style="background: #FFF0B1;">

    <sec:authorize access="hasRole('ROLE_USER')">
        <jsp:include page="userNavbar.jsp"></jsp:include>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <jsp:forward page="admin.jsp"></jsp:forward>
    </sec:authorize>

    <sec:authorize access="isAnonymous()">
        <jsp:include page="navbar.jsp"></jsp:include>
    </sec:authorize>


    <!--UPPER MENU-->

    <div class="container" id="above-menu">
        <div class="row">
            <div class="col-md-2">
            </div>
            <div class="col-md-2">
                <h4 style="padding-right:100px"><a href="pizza" style="color:#FF7509"><spring:message code="home.pizza"/> </a></h4>
            </div>
            <div class="col-md-2">
                <h4><a href="drinks" style="color:#FF7509"><spring:message code="home.drinks"/> </a></h4>
            </div>
            <div class="col-md-2">
                <h4><a href="dessert" style="color:#FF7509"><spring:message code="home.dessert"/> </a></h4>
            </div>
            <div class="col-md-2">
                <h4><a href="soup" style="color:#FF7509"><spring:message code="home.soup"/> </a></h4>
            </div>
            <div class="col-md-2">
                <h4><a href="other" style="color:#FF7509"><spring:message code="home.other"/> </a></h4>
            </div>
            <div class="col-md-0">
                <h4></h4>
            </div>
        </div>
    </div>

    <br/>


    <!--CAROUSEL-->

    <div class="container">
        <div id="slideshow" class="carousel slide" data-ride="carousel">


            <div class="carousel-inner" role="listbox">

                <div class="item active">
                    <img src="assets/img/pizza_web.jpg" alt="slide"/>
                    <div class="carousel-caption">
                        <h2><spring:message code="home.pizza"/></h2>
                    </div>
                </div>

                <div class="item">
                    <img src="assets/img/coca-cola_web.jpg" alt="slide"/>
                    <div class="carousel-caption">
                        <h2><spring:message code="home.drinks"/> </h2>
                    </div>
                </div>

                <div class="item">
                    <img src="assets/img/desserts_web.jpg" alt="slide"/>
                    <div class="carousel-caption">
                        <h2><spring:message code="home.dessert"/> </h2>
                    </div>
                </div>

                <div class="item">
                    <img src="assets/img/soup2_web.jpg" alt="slide"/>
                    <div class="carousel-caption">
                        <h2><spring:message code="home.soup"/> </h2>
                    </div>
                </div>

                <div class="item">
                    <img src="assets/img/burger_web.jpg" alt="slide"/>
                    <div class="carousel-caption">
                        <h2><spring:message code="home.other"/> </h2>
                    </div>
                </div>

            </div>

        </div>

        <div class="carousel-controls">
            <a class="left carousel-control" href="#slideshow" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left"></span>
                <span class="sr-only">Previous</span>
            </a>
            <ol class="carousel-indicators">
                <li data-target="#slideshow" data-slide-to="0" class="active"></li>
                <li data-target="#slideshow" data-slide-to="1"></li>
                <li data-target="#slideshow" data-slide-to="2"></li>
                <li data-target="#slideshow" data-slide-to="3"></li>
                <li data-target="#slideshow" data-slide-to="4"></li>

            </ol>
            <a class="right carousel-control" href="#slideshow" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </div>


    </body>
</html>
