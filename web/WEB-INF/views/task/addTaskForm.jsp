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
    <title>Add Task</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>

<div style="padding: 20px" class="col-8 mx-auto">
    <h5>Add Task Form</h5><hr>

    <form action="/tasks/addTask" method="post">

        <input type="text" class="form-control" placeholder="Enter task title" name="title"><br>
        <input type="text" class="form-control" placeholder="Enter task body" name="body"><br>

        <input type="text" class="form-control" placeholder="Enter option 1" name="option1"><br>
        <input type="text" class="form-control" placeholder="Enter option 2" name="option2"><br>
        <input type="text" class="form-control" placeholder="Enter option 3" name="option3"><br>
        <input type="text" class="form-control" placeholder="Enter option 4" name="option4"><br>

        <p>Please select one true option</p><br>

        <input class="form-check-input" type="radio" name="trueOption" id="option1" value="1">
        <label class="form-check-label" for="option1">1-Option</label><br>
        <input class="form-check-input" type="radio" name="trueOption" id="option2" value="2">
        <label class="form-check-label" for="option2">2-Option</label><br>
        <input class="form-check-input" type="radio" name="trueOption" id="option3" value="3">
        <label class="form-check-label" for="option3">3-Option</label><br>
        <input class="form-check-input" type="radio" name="trueOption" id="option4" value="4">
        <label class="form-check-label" for="option4">4-Option</label><br>

        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</div>


</body>
</html>
