<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/19/2022
  Time: 6:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Mentor</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>

<div style="padding: 20px" class="col-8 mx-auto">
    <h5>Edit Mentor Form</h5><hr>

    <form action="/mentor/add" method="post">
        <input type="hidden" class="form-control" name="id" value="${mentor.id}"><br>
        <input type="text" class="form-control" value="${mentor.fullName}" name="fullname"><br>
        <input type="email" class="form-control" value="${mentor.email}" name="email"><br>
        <input type="password" class="form-control" value="${mentor.password}" name="password"><br>
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</div>
</body>
</html>
