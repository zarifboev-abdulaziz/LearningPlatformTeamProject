<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/17/2022
  Time: 11:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>

<div style="padding: 20px">
    <h1>Welcome To Home Page</h1><br>

    <a class="m-4 btn btn-success" href="/courses/1">Show Courses</a>
    <a class="m-4 btn btn-success" href="/mentor/show">Show Mentors</a>
    <a class="m-4 btn btn-success" href="/user/settings">Profile Settings</a>
    <a class="m-4 btn btn-success" href="/user/logout">Log out</a>
</div>

</body>
</html>
