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

    <a class="btn btn-info" href='/student/myCourses'>Back to Courses</a>
    <hr>
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

    <p>
        Mentors:
        <c:forEach var="mentor" items="${mentors}">
            <a class="btn btn-success"
               href='/mentors/info/${mentor.id}'>${mentor.fullName}</a>
        </c:forEach>
    </p>

    <h4>Module List</h4>

    <c:choose>
        <c:when test="${course.modules.size() != 0}">


            <div class="container">
                <div class="row">
                    <c:forEach var="i" begin="0" end="${course.modules.size() - 1}">

                        <div class="col-md-3">
                            <div class="card">
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
