<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/10/2022
  Time: 2:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="css/homePage.css">
    <title>Modules</title>
</head>
<style>
    #intro {
        /*background-image: url("https://mdbootstrap.com/img/new/fluid/city/018.jpg");*/
        height: 100vh;
        /*color: white;*/
        background-color: antiquewhite;
    }
</style>
<body>



<div class="container">
    <div class="">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <%--                <a href="#" class="navbar-brand">Brand</a>--%>
                <a class="navbar-brand" href="/student/myCourses">Courses</a>
                <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                    <div class="navbar-nav">
                        <a href="/user/settings" class="nav-item nav-link">Profile</a>
                    </div>
                    <div class="d-flex ">
                        <form class="navbar-nav">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="Search">
                                <button type="button" class="btn btn-secondary"><i class="bi-search"></i></button>
                            </div>
                        </form>
                        <div class="navbar-nav">
                            <a href="/user/logout" class="nav-item nav-link">Log out</a>
                        </div>
                    </div>
                </div>
            </div>
        </nav>
    </div>
</div>


<div class="container" id="intro">

<%--    <a class="btn btn-info" href='/student/myCourses'>Back to Courses</a>--%>
<%--    <hr>--%>

    <h4>${course.name}</h4>
    <p>${course.description}</p>

    <br>
    <c:choose>
    <c:when test="${isCourseCompleted == true}">
        <a class="btn btn-success" href='/student/getCertificate/${course.id}'>Get Certificate</a>
        <br>
    </c:when>
    </c:choose>
    <br>



    <div class="nav-item dropdown">
        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown" style="font-size:
        20px; color: black">Mentors
        </a>
        <div class="dropdown-menu">

            <c:forEach var="mentor" items="${mentors}">
                <a class="dropdown-item"
                   href='/mentors/info/${mentor.id}'>${mentor.fullName}</a>
            </c:forEach>
<%--            <a href="#" class="dropdown-item">Inbox</a>--%>
<%--            <a href="#" class="dropdown-item">Sent</a>--%>
<%--            <a href="#" class="dropdown-item">Drafts</a>--%>
        </div>
    </div>



<%--    <p>--%>
<%--        Mentors:--%>
<%--        <c:forEach var="mentor" items="${mentors}">--%>
<%--            <a class="btn btn-success"--%>
<%--               href='/mentors/info/${mentor.id}'>${mentor.fullName}</a>--%>
<%--        </c:forEach>--%>
<%--    </p>--%>

    <h4 style="text-align: center">Module List</h4>

    <c:choose>
        <c:when test="${course.modules.size() != 0}">


            <div class="d-flex justify-content-around">
                <div class="row">
                    <c:forEach var="i" begin="0" end="${course.modules.size() - 1}">

                        <div class="col-3" >
                            <div class="card" style="height: 250px; width: 300px; border-radius:
                            15px ">
                                <div class="card-body">
                                    <h5 class="card-title">Module - ${course.modules.get(i).orderNumber}</h5>
                                    <p class="card-text">${course.modules.get(i).title}</p>
                                    <p class="card-text">${progressBar.get(i)}% - completed</p>
                                </div>

                                <div class="container">
                                    <div class="progress">
                                        <div class="progress-bar" role="progressbar"
                                             style="width: ${progressBar.get(i)}%" aria-valuenow="25"
                                             aria-valuemin="0" aria-valuemax="100">${progressBar.get(i)}%
                                        </div>
                                    </div>
                                </div>
                                <br>

                                <div class="card-footer">
                                    <small class="text-muted"><a class="btn btn-success"
                                                                 href="/student/lessons/${course.modules.get(i).id}">Go</a></small>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

        </c:when>
    </c:choose>

</div>
</body>
</html>
