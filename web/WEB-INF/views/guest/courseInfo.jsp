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
    <title>Courses</title>
</head>
<body>
<div style="padding: 20px">

    <a class="m-4 btn btn-success" href="/user/home">Back To Courses</a><br>


    <h4>${course.name}</h4>
    <p>${course.description}</p>
    <p>
        Number of modules: ${course.modules.size()} <br>
        Number of lessons: ${lessonCount} <br>
        Mentors:
        <c:forEach var="mentor" items="${mentors}">
            <a class="btn btn-success"
               href='/mentor/mentorInfo/${mentor.id}'> ${mentor.fullName} </a>
        </c:forEach>
    </p>
    <h4>Modules</h4>

    <div class="container">
        <div class="row">
            <c:forEach var="module" items="${course.modules}">

                <div class="col-md-3">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Module - ${module.orderNumber}</h5>
                            <p class="card-text">${module.title}</p>
                            <p class="card-text">Lessons: </p>
                            <c:forEach var="lesson" items="${module.lessons}">
                                <p>${lesson.orderNumber} - ${lesson.title}</p>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>



    <h4 style="color: green">Price: ${course.price}</h4>

    <a class="btn btn-success" href='/login'>Purchase Course</a>

</div>
</body>
</html>
