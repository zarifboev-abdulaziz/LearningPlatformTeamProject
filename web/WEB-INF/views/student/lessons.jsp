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

    <c:choose>
        <c:when test="${module.lessons.size() != 0}">

            <div class="container">
                <div class="row">
                    <c:forEach var="i" begin="0" end="${module.lessons.size() - 1}">

                        <div class="col-md-2">
                            <div class="card">
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
