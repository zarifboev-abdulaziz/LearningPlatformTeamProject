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

    <p>
        Mentors:
        <c:forEach var="mentor" items="${mentors}">
            <a class="btn btn-success"
               href='/mentors/info/${mentor.id}'>${mentor.fullName}</a>
        </c:forEach>
    </p>

    <h4>Module List</h4>

    <div class="container">
        <div class="row">
            <c:forEach var="module" items="${course.modules}">

                <div class="col-md-3">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Module - ${module.orderNumber}</h5>
                            <p class="card-text">${module.title}</p>
                            <div class="card-footer">
                                <small class="text-muted"><a class="btn btn-success"
                                                             href="/student/lessons/${module.id}">Go</a></small>
                            </div>

                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

</div>
</body>
</html>