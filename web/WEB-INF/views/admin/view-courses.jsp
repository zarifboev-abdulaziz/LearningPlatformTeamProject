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
<a class="m-4 btn btn-success" href="/admin/courses/addForm">+ Add new course</a>
<a class="m-4 btn btn-success" href="/admin/authors">Authors</a>
<div style="padding: 20px">
    <form action="/admin/courses/search" method="post">
        <input type="text" placeholder="Search" name="search">
    </form>
</div>

<table class="table">
    <tr>
        <th>name</th>
        <th>isActive</th>
        <th>Actions</th>
        <th>View</th>
    </tr>

    <c:forEach var="course" items="${courseList}">
        <tr>
            <td>${course.name}</td>
            <td>${course.active}</td>
            <td>
                <a class="btn btn-warning" href='/admin/courses/editForm/${course.id}'>Edit</a>
                <a class="btn btn-danger" href="/admin/courses/delete/${course.id}">Delete</a>
            </td>
            <td>
                <a class="btn btn-info" href="/courses/info/${course.id}">More...</a>
            </td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
