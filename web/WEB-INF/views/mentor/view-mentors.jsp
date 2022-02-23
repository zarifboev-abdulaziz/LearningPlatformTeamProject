<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Nodirbek
  Date: 2/23/2022
  Time: 3:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mentors</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>
<div style="padding: 20px">

    <a class="m-4 btn btn-success" href="/mentor/add">+ Add new mentor</a>


    <h3>Mentor list</h3>


    <table class="table">
        <tr>
            <th>Full name</th>
            <th>Email</th>
            <th>Actions</th>
        </tr>

        <c:forEach var="mentor" items="${mentors}">
            <tr>
               <td>${mentor.fullName}</td>
                <td>${mentor.email}</td>
                <td>
                    <a class="btn btn-warning" href='/mentor/edit/${mentor.id}'>Edit</a>
                    <a class="btn btn-danger" href="/mentor/delete/${mentor.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>


</div>
</body>
</html>
