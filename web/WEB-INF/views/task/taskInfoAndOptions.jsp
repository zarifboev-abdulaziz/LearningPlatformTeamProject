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
    <title>Options</title>
</head>
<body>
<div style="padding: 20px">

    <a class="btn btn-info" href='/lessons/info/${task.lesson.id}'>Back to Tasks</a>
    <a class="btn btn-success" href='/options/addOption'> + Add Option</a>
    <hr>
    <h4>${task.title}</h4>
    <p>${task.body}</p><br>
    <center>
        <h5>Option List</h5>
    </center>
    <br>
    <table class="table">
        <tr>
            <th>body</th>
            <th>Right Answer(s)</th>
            <th>Actions</th>
        </tr>

        <c:forEach var="option" items="${options}">
            <tr>
                <td>
                    <p>${option.body}</p>
                </td>

                <td>
                    <c:choose>
                        <c:when test="${option.rightAnswer == true}">
                            <p>Right Answer</p>
                        </c:when>
                    </c:choose>
                </td>

                <td>
                    <a class="btn btn-warning" href='/options/editForm/${option.id}'>Edit</a>
                    <a class="btn btn-danger" href="/options/delete/${option.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>
