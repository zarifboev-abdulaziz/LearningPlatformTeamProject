<%--
  Created by IntelliJ IDEA.
  User: muhammadsodiq
  Date: 20/02/22
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Mentor Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<h1>Welcome To Home Page</h1><br>


<a class="m-4 btn btn-success" href="/mentor/courses">Show my courses</a>
<a class="m-4 btn btn-success" href="/user/settings">Profile Settings</a>
<a class="m-4 btn btn-success" href="/user/logout">Log out</a>
<br><br>

<div style="padding: 20px">
    <h4>Your Courses</h4><br>

    <div class="container">
        <div class="row">

            <c:forEach var="course" items="${allCourses}">

                <div class="col-md-3">
                    <div class="card border-success mb-3" style="width: 18rem;">
                        <img src="data:image/png;base64, ${course.imagePath}" class="card-img-top"
                             alt="Here Should be image">
                        <div class="card-body">
                            <h5 class="card-title">${course.name}</h5>
                            <p class="card-text">${course.active}</p>
                        </div>
                        <div class="card-footer">
                            <small class="text-muted"><a class="btn btn-info"
                                                         href="/courses/info/${course.id}">More...</a></small>
                        </div>
                    </div>
                </div>

            </c:forEach>
        </div>
    </div>



</div>
</body>
</html>
