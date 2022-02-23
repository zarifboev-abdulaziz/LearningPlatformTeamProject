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
    <title>Modules</title>
</head>
<body>
<div style="padding: 20px">

    <a class="btn btn-info" href='/courses/info/${module.course.id}'>Back to Modules</a>
    <a class="btn btn-success" href='/lessons/addLesson'>+ Add new Lesson</a>
    <hr>
    <h4>${module.title}</h4>
    <p>${module.orderNumber} - Module</p>

    <center>
        <h4>Lesson List</h4>
    </center>

    <div class="container">
        <div class="row">
            <c:forEach var="lesson" items="${module.lessons}">

                <div class="col-md-3">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title"><a class="btn btn-secondary" href="/lessons/info/${lesson.id}">${lesson.orderNumber} - ${lesson.title}</a></h5>
                            <a class="btn btn-warning" href='/lessons/editLesson/${lesson.id}'>Edit</a>
                            <a class="btn btn-danger" href="/lessons/delete/${lesson.id}">Delete</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

</div>
</body>
</html>
