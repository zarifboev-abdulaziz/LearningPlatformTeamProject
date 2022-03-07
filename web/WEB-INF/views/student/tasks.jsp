<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/22/2022
  Time: 10:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="css/homePage.css">
    <title>Tasks</title>
</head>
<body>


<div class="container">
    <div class="">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <%--                <a href="#" class="navbar-brand">Brand</a>--%>
                <a class="navbar-brand" href="/student/lessonInfo/${lesson.id}">Lessons</a>
                <button type="button" class="navbar-toggler" data-bs-toggle="collapse"
                        data-bs-target="#navbarCollapse">
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
                                <button type="button" class="btn btn-secondary"><i
                                        class="bi-search"></i></button>
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





<div class="container">

<%--    <a class="m-4 btn btn-info" href="/student/lessonInfo/${lesson.id}">Back To Lesson</a><br>--%>

    <h4>${task.title}</h4>
    <hr>
    <p>${task.body}</p>
    <hr>


    <div class="list-group col-6">
        <div class="form-check">
            <form action="/student/checkOption/${lesson.id}/${task.id}" method="post">
        <c:forEach var="option" items="${task.options}">

            <input class="form-check-input" type="radio" name="option" id="exampleRadios1" value="${option.id}">
            <label class="form-check-label" for="exampleRadios1">${option.body}</label><br>

        </c:forEach>
                <br>
                <button class="btn btn-success">Send</button>
            </form>
        </div>
    </div>
    <br><br>


    <c:choose>
        <c:when test="${result.success == true}">
            <p class="text-success">${result.message}</p>
        </c:when>
        <c:when test="${result.success == false}">
            <p class="text-danger">${result.message}</p>
        </c:when>
    </c:choose>

    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">

            <c:forEach var="t" begin="1" end="${lesson.tasks.size()}">

                <li class="page-item"><a class="page-link ${bgColor.get(t-1)} text-light"
                                         href="/student/tasks/${lesson.id}/${lesson.tasks.get(t-1).id}">${t}</a></li>

            </c:forEach>

        </ul>
    </nav>

    <br>
    <h5>Comments</h5>
    <div style="padding: 20px" class="col-6">
        <form action="/comment/task/${task.id}" method="post">
<%--            <input type="text" class="form-control" placeholder="write here..." name="comment">--%>
<%--            <button class="m-4 btn btn-success">Add Comment</button>--%>

            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="write here..." name="comment">
                <div class="input-group-append">
                    <button class="btn btn-outline-primary" type="button">Send</button>
                </div>
            </div>


        </form>
    </div>
    <br>

    <c:forEach var="comment" items="${comments}">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">${comment.user.fullName}</h5>
                <p class="card-text">${comment.body}</p>
            </div>
        </div>
    </c:forEach>



</div>
</body>
</html>
