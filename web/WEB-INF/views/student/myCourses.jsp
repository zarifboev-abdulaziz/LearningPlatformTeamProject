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
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <title>Courses</title>
</head>
<body>
<a class="m-4 btn btn-warning" href="/student/home">Home</a>

<div class="container">
  <div class="row">

    <c:forEach var="course" items="${myCourses}">

      <div class="col-md-3" >
        <div class="card border-success mb-3" style="width: 18rem;">
          <img src="data:image/png;base64, ${course.imagePath}" class="card-img-top"
               alt="Here Should be image">
          <div class="card-body">
            <h5 class="card-title">${course.name}</h5>
            <p class="card-text">${course.description}</p>
          </div>
          <div class="card-footer">
            <small class="text-muted"><a class="btn btn-success"
                                         href="/student/modules/${course.id}">Go</a></small>
          </div>
        </div>
      </div>

    </c:forEach>

  </div>
</div>


</body>
</html>
