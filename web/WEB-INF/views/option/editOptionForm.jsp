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
    <title>Add Option</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>

<div style="padding: 20px" class="col-8 mx-auto">
    <h5>Add Option Form</h5><hr>

    <form action="/options/addOption" method="post">

        <input type="hidden" class="form-control" value="${option.id}" name="id"><br>
        <input type="text" class="form-control" value="${option.body}" name="body"><br>

        <div class="form-check">
            <input name = "isRightAnswer" type="checkbox" class="form-check-input" id="check">
            <label class = "form-check-label" for="check"> is correct option? </label>
        </div><br>

        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</div>


</body>
</html>
