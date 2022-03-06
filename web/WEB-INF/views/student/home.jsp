<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/17/2022
  Time: 11:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="css/homePage.css">
</head>
<style>
    #card {
        border-radius: 20px;

    }

    #box {
        margin: 10px;
    }

    #intro {
        background-image: url("https://mdbootstrap.com/img/new/fluid/city/018.jpg");
        height: 100vh;
    }

    #intro {
        background-image: url("https://mdbootstrap.com/img/new/fluid/city/018.jpg");
        height: 300vh;
    }

</style>
<body>


<%--    <h1>Welcome To Home Page</h1><br>--%>
<%--    <a class="m-4 btn btn-success" href="/student/activeCourses/1">Buy Course</a>--%>
<%--    <a class="m-4 btn btn-success" href="/student/myCourses">Show my Courses</a>--%>
<%--    <a class="m-4 btn btn-success" href="/user/settings">Profile Settings</a>--%>
<%--    <a class="m-4 btn btn-success" href="/user/logout">Log out</a>--%>
<%--    <br><br>--%>


<div class="container">
    <div class="">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <%--                <a href="#" class="navbar-brand">Brand</a>--%>
                <a class="navbar-brand" href="/student/activeCourses/1">Buy Course</a>
                <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                    <div class="navbar-nav">
                        <a href="/student/myCourses" class="nav-item nav-link">My courses</a>
                        <a href="/user/settings" class="nav-item nav-link">Profile</a>
<%--                        <div class="nav-item dropdown">--%>
<%--                            <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Messages</a>--%>
<%--                            <div class="dropdown-menu">--%>
<%--                                <a href="#" class="dropdown-item">Inbox</a>--%>
<%--                                <a href="#" class="dropdown-item">Sent</a>--%>
<%--                                <a href="#" class="dropdown-item">Drafts</a>--%>
<%--                            </div>--%>
<%--                        </div>--%>
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
    <div class="row d-flex justify-content-around">

        <c:forEach var="course" items="${allCourses}">

            <div class="col-3" id="box">
                <div class="card mb-3" style="width: 300px" id="card">
                    <img style="border-radius: 20px; height: 230px" src="data:image/png;base64, ${course.imagePath}"
                         class="card-img-top"
                         alt="Here Should be image">
                    <div class="card-body" style="height: 200px; overflow: auto">
                        <h5 class="card-title">${course.name}</h5>
                        <p class="card-text">${course.description}</p>
                    </div>
                    <div class="card-footer">
                        <small class="text-muted"><a class="btn btn-info"
                                                     href="/student/courseInfo/${course.id}">More...</a></small>
                    </div>
                </div>
            </div>

        </c:forEach>

    </div>
</div>


</body>
</html>
