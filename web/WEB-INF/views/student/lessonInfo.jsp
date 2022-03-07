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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="css/homePage.css">
    <title>Modules</title>
</head>
<body>
<div class="container">
    <div class="">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <%--                <a href="#" class="navbar-brand">Brand</a>--%>
                <a class="navbar-brand" href="/student/lessons/${lesson.module.id}">Lessons</a>
                <button type="button" class="navbar-toggler" data-bs-toggle="collapse"
                        data-bs-target="#navbarCollapse">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                    <div class="navbar-nav">
                        <a href="/user/settings" class="nav-item nav-link">Profile</a>
                    </div>
                    <div class="d-flex ">
                        <form class="navbar-nav">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="Search">
                                <button type="button" class="btn btn-secondary"><i
                                        class="bi-search"></i></button>
                            </div>
                        </form>
                        <div class="navbar-nav">
                            <a href="/user/logout" class="nav-item nav-link">Log out</a>
                        </div>
                    </div>
                </div>
            </div>
        </nav>
    </div>
</div>


<div style="padding: 20px" class="container">

    <%--    <a class="btn btn-info" href='/student/lessons/${lesson.module.id}'>Back to Lessons</a>--%>
    <%--    <hr>--%>
<%--    <h4>${lesson.title}</h4>--%>
<%--    <p>${lesson.orderNumber} - Lesson</p><br>--%>
<%--    <center>--%>
<%--        <p>${lesson.body}</p>--%>
<%--    </center>--%>


<%--    <c:choose>--%>
<%--        <c:when test="${lesson.tasks.size() != 0}">--%>
<%--            <a class="btn btn-info" href='/student/tasks/${lesson.id}/${lesson.tasks.get(0).id}'>Solve--%>
<%--                Tasks</a>--%>
<%--        </c:when>--%>
<%--    </c:choose>--%>


    <div class="card text-center">
        <div class="card-header">
            ${lesson.orderNumber} - Lesson
        </div>
        <div class="card-body">
            <h5 class="card-title">${lesson.title}</h5>
            <p class="card-text">${lesson.body}</p>

            <c:choose>
                <c:when test="${lesson.tasks.size() != 0}">
                    <a href="/student/tasks/${lesson.id}/${lesson.tasks.get(0).id}"
                       class="btn btn-primary">Solve tasks</a>
                </c:when>
            </c:choose>


        </div>
        <div class="card-footer text-muted">
            ${lesson.createdAt}
        </div>
    </div>


    <br>
    <h5>Comments</h5>
    <div style="padding: 20px" class="col-6">
        <form action="/comment/lesson/${lesson.id}" method="post">
            <%--            <input type="text" class="form-control" placeholder="write here..." name="comment">--%>
            <%--            <button class="m-4 btn btn-success">Add Comment</button>--%>

            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="write here..." name="comment">
                <div class="input-group-append">
                    <button class="btn btn-outline-primary" type="button">Send</button>
                </div>
            </div>


        </form>
    </div>
    <br>

    <c:forEach var="comment" items="${comments}">
        <div class="card">
            <div class="card-body">

                <div class="d-flex justify-content-between align-items-center">
                    <p class="mb-1">${comment.user.fullName} <span class="small"></span></p>
                    <a href="/student/lessonInfo/reply/${comment.id}"><i
                            class="fas fa-reply fa-xs"></i><span
                            class="small"> reply</span></a>
                </div>
                <p class="card-text">${comment.body}</p>
                <c:choose>
                    <c:when test="${comment.numberOfReplies != 0}">
                        <a href="/comment/showReplies/${comment.id}">show ${comment.numberOfReplies}
                            replies</a>
                    </c:when>
                </c:choose>

                <c:choose>
                    <c:when test="${replyingCommentId == comment.id}">
                        <br>
                        <form action="/comment/reply/${comment.id}" method="post">
                            <input type="text" class="form-control"
                                   placeholder="reply comment here..."
                                   name="replyComment">
                            <a class="m-4 btn btn-danger" href="/student/lessonInfo/${lesson.id}">cancel</a>
                            <button class="m-4 btn btn-success">reply</button>
                        </form>
                        <br>
                    </c:when>
                </c:choose>

                <c:choose>
                    <c:when test="${isShowingReplies == true}">
                        <c:choose>
                            <c:when test="${repliedCommentId == comment.id}">
                                <div class="container">
                                    <a href="/student/lessonInfo/${lesson.id}">hide comments</a>
                                    <c:forEach var="repliedComment" items="${repliedComments}">
                                        <h5>${repliedComment.user.fullName}</h5>
                                        <p>${repliedComment.body}</p>
                                    </c:forEach>
                                </div>
                            </c:when>
                        </c:choose>
                    </c:when>
                </c:choose>

            </div>
        </div>
    </c:forEach>


</div>
</body>
</html>
