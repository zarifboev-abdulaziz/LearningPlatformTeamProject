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
    <title>Lessons</title>
</head>
<body>
<div style="padding: 20px">

    <a class="btn btn-info" href='/student/modules/${module.course.id}'>Back to Modules</a>
    <hr>
    <h4>${module.title}</h4>
    <p>${module.orderNumber} - Module</p>

    <h4>Lesson List</h4>

    <div class="container">
        <div class="row">
            <c:forEach var="lesson" items="${module.lessons}">

                <div class="col-md-2">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">${lesson.orderNumber} - ${lesson.title}</h5>
                            <a class="btn btn-info" href="/student/lessonInfo/${lesson.id}">Go</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

</div>
</body>
</html>
