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

    <a class="btn btn-info" href='/modules/info/${lesson.module.id}'>Back to Lessons</a>
    <a class="btn btn-success" href='/tasks/addTask'> + Add Task</a>
    <hr>
    <h4>${lesson.title}</h4>
    <p>${lesson.orderNumber} - Lesson</p><br>
    <p>${lesson.body}</p>

    <center>
        <h5>Task List</h5>
    </center>
    <br>
    <table class="table">
        <tr>
            <th>Title</th>
            <th>Task</th>
            <th>Actions</th>
        </tr>

        <c:forEach var="task" items="${tasks}">
            <tr>
                <td>
                    <a class="btn btn-primary" href="/tasks/info/${task.id}">${task.title}</a>
                </td>
                <td>${task.body }</td>
                <td>
                    <a class="btn btn-warning" href='/tasks/editForm/${task.id}'>Edit</a>
                    <a class="btn btn-danger" href="/tasks/delete/${task.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>
