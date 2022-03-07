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
    <title>Lessons</title>
</head>
<body>





<div class="container">
    <div class="">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <%--                <a href="#" class="navbar-brand">Brand</a>--%>
                <a class="navbar-brand" href="/student/modules/${module.course.id}">Moduls</a>
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





<div class="container" style="background-color: antiquewhite; height: 100vh">

<%--    <a class="btn btn-info" href='/student/modules/${module.course.id}'>Back to Modules</a>--%>
    <h4>${module.title}</h4>
    <p>${module.orderNumber} - Module</p>

    <h4 style="text-align: center">Lesson List</h4>

    <c:choose>
        <c:when test="${module.lessons.size() != 0}">

            <div class="container">
                <div class="row d-flex justify-content-around">
                    <c:forEach var="i" begin="0" end="${module.lessons.size() - 1}">

                        <div class="col-3 " >
                            <div class="card" style="width: 270px; height: 250px; border-radius:
                            15px">
                                <div class="card-body">
                                    <h5 class="card-title">${module.lessons.get(i).orderNumber}
                                        - ${module.lessons.get(i).title}</h5>
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
                                    <small class="text-muted"><a class="btn btn-info"
                                                                 href="/student/lessonInfo/${module.lessons.get(i).id}">Go</a></small>
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
