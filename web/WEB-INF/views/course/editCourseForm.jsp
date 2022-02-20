<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/10/2022
  Time: 3:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <title>Edit Course</title>
</head>
<body>
<div style="padding: 20px">
    <h5>Edit Course Form</h5><hr>

    <form action="/courses/addCourse" method="post" enctype="multipart/form-data">

        <input name="id" type="hidden" value="${course.id}"><br>

        <input type="text" class="form-control" value="${course.name}" name="name"><br>

        <textarea class="form-control" rows="3"
                  name="description" >${course.description}</textarea> <br>

        <input type="text" class="form-control" value="${course.price}" name="price"><br><br>

        <select class="select" name="mentors" multiple>

            <c:forEach var="mentor" items="${mentorList}">
                <option value="${mentor.id}">${mentor.fullName}</option>
            </c:forEach>

        </select><br><br>

        <div class="form-check">
            <input name = "active" type="checkbox" class="form-check-input" id="check">
            <label class = "form-check-label" for="check">This course is active?</label>
        </div><br>

        <div>
            <label class="form-label" for="customFile">${course.imageName}</label>
            <input type="file" class="form-control" id="customFile" value="${course.imageName}" name="file"/>
        </div><br>

        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</div>

</body>
</html>