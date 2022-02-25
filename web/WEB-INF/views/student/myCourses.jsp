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
<a class="m-4 btn btn-warning" href="/student/home">Home</a>

<c:choose>
    <c:when test="${myCourses.size() != 0}">

        <div class="container">
            <div class="row">


                <c:forEach var="i" begin="0" end="${myCourses.size() - 1}">

                    <div class="col-md-3">
                        <div class="card border-success mb-3" style="width: 18rem;">
                            <img src="data:image/png;base64, ${myCourses.get(i).imagePath}" class="card-img-top"
                                 alt="Here Should be image">
                            <div class="card-body">
                                <h5 class="card-title">${myCourses.get(i).name}</h5>
                                <p class="card-text">${myCourses.get(i).description}</p>
                                <p class="card-text">${progressBar.get(i)}% - completed</p>
                            </div>

                            <div class="container">
                                <div class="progress">
                                    <div class="progress-bar" role="progressbar" style="width: ${progressBar.get(i)}%"
                                         aria-valuenow="25"
                                         aria-valuemin="0" aria-valuemax="100">${progressBar.get(i)}%
                                    </div>
                                </div>
                            </div>
                            <br>

                            <div class="card-footer">
                                <a class="btn btn-success" href="/student/modules/${myCourses.get(i).id}">Go</a>
                            </div>
                        </div>
                    </div>

                </c:forEach>

            </div>
        </div>

    </c:when>
</c:choose>

</body>
</html>
