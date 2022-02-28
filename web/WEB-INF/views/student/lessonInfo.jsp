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

    <a class="btn btn-info" href='/student/lessons/${lesson.module.id}'>Back to Lessons</a>
    <hr>
    <h4>${lesson.title}</h4>
    <p>${lesson.orderNumber} - Lesson</p><br>
    <center>
        <p>${lesson.body}</p>
    </center>


    <c:choose>
        <c:when test="${lesson.tasks.size() != 0}">
            <a class="btn btn-info" href='/student/tasks/${lesson.id}/${lesson.tasks.get(0).id}'>Solve Tasks</a>
        </c:when>
    </c:choose>

    <br>
    <h5>Comments</h5>
    <div style="padding: 20px" class="col-6">
        <form action="/comment/lesson/${lesson.id}" method="post">
            <input type="text" class="form-control" placeholder="write here..." name="comment">
            <button class="m-4 btn btn-success">Add Comment</button>
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
