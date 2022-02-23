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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Tasks</title>
</head>
<body>

<div style="padding: 20px">

    <a class="m-4 btn btn-info" href="/student/lessonInfo/${lesson.id}">Back To Lesson</a><br>

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

    <div style="padding: 20px" class="col-6">
        <form>
            <input type="text" class="form-control" placeholder="write here...">
            <a class="m-4 btn btn-success" href="">Add Comment</a>
        </form>
    </div>


</div>
</body>
</html>
