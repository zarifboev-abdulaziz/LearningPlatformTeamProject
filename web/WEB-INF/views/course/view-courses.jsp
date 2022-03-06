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
<a class="m-4 btn btn-success" href="/courses/addCourseForm">+ Add new course</a>
<a class="m-4 btn btn-success" href="/mentor/show">Mentors</a>
<a class="m-4 btn btn-warning" href="/admin/home">Home</a>
<div style="padding: 20px">
    <form action="/courses/search" method="post">
        <input type="text" placeholder="Search" name="search">
    </form>
</div>

<div class="container">
<div class="row">

        <c:forEach var="course" items="${courseList}">


            <div class="col-md-3" >
                <div class="card border-success mb-3" style="width: 18rem;">
                    <img src="data:image/png;base64, ${course.imagePath}" class="card-img-top"
                         alt="Here Should be image">
                    <div class="card-body">
                        <h5 class="card-title">${course.name}</h5>
                        <p class="card-text">${course.active}</p>
                        <a class="btn btn-warning" href='/courses/editForm/${course.id}'>Edit</a>
                        <a class="btn btn-danger" href="/courses/delete/${course.id}">Delete</a>
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

<div style="margin-left: 600px">
<c:forEach var="j" begin="1" end="${pages}">


    <a href="/courses/${j}">${j}</a>


</c:forEach>
</div>

</body>
</html>
