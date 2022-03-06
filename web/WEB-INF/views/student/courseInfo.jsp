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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Courses</title>
</head>
<body>
<div class="container">
<%--    <a class="m-4 btn btn-success" href="/student/activeCourses/1">Back To Courses</a><br>--%>



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



    <h4>${course.name}</h4>
    <p>${course.description}</p>
    <p>
        Number of modules: ${course.modules.size()} <br>
        Number of lessons: ${lessonCount} <br>
        Mentors:
        <c:forEach var="mentor" items="${mentors}">
            <a class="btn btn-success"
               href='/mentor/mentorInfo/${mentor.id}'> ${mentor.fullName} </a>
        </c:forEach>
    </p>
    <h4>Modules</h4>

    <div class="container">
        <div class="row">
            <c:forEach var="module" items="${course.modules}">

                <div class="col-md-3">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Module - ${module.orderNumber}</h5>
                            <p class="card-text">${module.title}</p>
                            <p class="card-text">Lessons: </p>
                            <c:forEach var="lesson" items="${module.lessons}">
                                <p>${lesson.orderNumber} - ${lesson.title}</p>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>



    <h4 style="color: green">Price: ${course.price}</h4>

    <c:choose>
        <c:when test="${isCoursePurchased == true}">
            <button type="button" class="btn btn-secondary" disabled>Purchased</button>
        </c:when>
        <c:when test="${isCoursePurchased == false}">
            <a class="btn btn-success" href='/student/purchaseCourse/${course.id}'>Purchase
                Course</a>
        </c:when>
    </c:choose>


</div>
</body>
</html>
