<%--
  Created by IntelliJ IDEA.
  User: muhammadsodiq
  Date: 23/02/22
  Time: 09:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Profile settings</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
</head>
<body>
<div class="m-4 col-4">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Full name</th>
            <th scope="col">Email</th>
            <th scope="col">Balance</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="row">1</th>
            <td>${profile.fullName}</td>
            <td>${profile.email}</td>
            <td>${profile.balance}</td>
        </tr>
        </tbody>
    </table>
    <a href="/user/profile" class="btn btn-primary">Edit profile</a>
    <a href="/user/fillBalance1" class="btn btn-primary">Fill balance</a>
    <br><br>

    <c:choose>
        <c:when test="${isFillingBalance == true}">
            <form action="/user/fillBalance" method="get">
            <input class="m-1" type="text" placeholder="Enter balance" name="fill"><br>
            <button class="m-1 btn btn-primary">Fill</button>
            <br />
            </form>
        </c:when>
    </c:choose>

</div>
</body>
</html>
