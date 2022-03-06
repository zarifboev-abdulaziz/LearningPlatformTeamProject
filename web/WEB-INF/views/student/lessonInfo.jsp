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

    <a class="btn btn-info" href='/student/lessons/${lesson.module.id}'>Back to Lessons</a>
    <hr>
    <h4>${lesson.title}</h4>
    <p>${lesson.orderNumber} - Lesson</p><br>
    <center>
        <p>${lesson.body}</p>
    </center>


    <c:choose>
        <c:when test="${lesson.tasks.size() != 0}">
            <a class="btn btn-info" href='/student/tasks/${lesson.id}/${lesson.tasks.get(0).id}'>Solve Tasks</a>
        </c:when>
    </c:choose>

    <br>
    <h5>Comments</h5>
    <div style="padding: 20px" class="col-6">
        <form action="/comment/lesson/${lesson.id}" method="post">
            <input type="text" class="form-control" placeholder="write here..." name="comment">
            <button class="m-4 btn btn-success">Add Comment</button>
        </form>
    </div>
    <br>

    <c:forEach var="comment" items="${comments}">
        <div class="card">
            <div class="card-body">

                <div class="d-flex justify-content-between align-items-center">
                    <p class="mb-1">${comment.user.fullName} <span class="small"></span></p>
                    <a href="/student/lessonInfo/reply/${comment.id}"><i class="fas fa-reply fa-xs"></i><span
                            class="small"> reply</span></a>
                </div>
                <p class="card-text">${comment.body}</p>
                <c:choose>
                    <c:when test="${comment.numberOfReplies != 0}">
                        <a href="/comment/showReplies/${comment.id}">show ${comment.numberOfReplies} replies</a>
                    </c:when>
                </c:choose>

                <c:choose>
                    <c:when test="${replyingCommentId == comment.id}">
                        <br>
                        <form action="/comment/reply/${comment.id}" method="post">
                            <input type="text" class="form-control" placeholder="reply comment here..."
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
