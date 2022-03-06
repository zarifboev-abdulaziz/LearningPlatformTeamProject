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

<style>
    body {
        background: #eee
    }

    .card {
        border: none;
        position: relative;
        overflow: hidden;
        border-radius: 8px;
        cursor: pointer
    }

    .card:before {
        content: "";
        position: absolute;
        left: 0;
        top: 0;
        width: 4px;
        height: 100%;
        background-color: #E1BEE7;
        transform: scaleY(1);
        transition: all 0.5s;
        transform-origin: bottom
    }

    .card:after {
        content: "";
        position: absolute;
        left: 0;
        top: 0;
        width: 4px;
        height: 100%;
        background-color: #8E24AA;
        transform: scaleY(0);
        transition: all 0.5s;
        transform-origin: bottom
    }

    .card:hover::after {
        transform: scaleY(1)
    }

    .fonts {
        font-size: 11px
    }

    .social-list {
        display: flex;
        list-style: none;
        justify-content: center;
        padding: 0
    }

    .social-list li {
        padding: 10px;
        color: #8E24AA;
        font-size: 19px
    }

    .buttons a:nth-child(1) {
        border: 1px solid #8E24AA !important;
        color: #8E24AA;
        height: 40px
    }

    .buttons a:nth-child(1):hover {
        border: 1px solid #8E24AA !important;
        color: #fff;
        height: 40px;
        background-color: #8E24AA
    }

    .buttons a:nth-child(2) {
        border: 1px solid #8E24AA !important;
        background-color: #8E24AA;
        color: #fff;
        height: 40px
    }
</style>

<body>
<%--            <td>${profile.fullName}</td>--%>
<%--            <td>${profile.email}</td>--%>
<%--            <td>${profile.balance}</td>--%>
<%--    <a href="/user/profile" class="btn btn-primary">Edit profile</a>--%>
<%--    <a href="/user/fillBalance1" class="btn btn-primary">Fill balance</a>--%>
<%--    <c:choose>--%>
<%--        <c:when test="${isFillingBalance == true}">--%>
<%--            <form action="/user/fillBalance" method="get">--%>
<%--            <input class="m-1" type="text" placeholder="Enter balance" name="fill"><br>--%>
<%--            <button class="m-1 btn btn-primary">Fill</button>--%>
<%--            <br />--%>
<%--            </form>--%>
<%--        </c:when>--%>
<%--    </c:choose>--%>



<div class="container">
    <div class="">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid d-flex">
                <%--                <a href="#" class="navbar-brand">Brand</a>--%>
                <a class="navbar-brand" href="/student/home">Home</a>
                <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                    <span class="navbar-toggler-icon"></span>
                </button>
                        <div class="navbar-nav">
                            <a href="/user/logout" class="nav-item nav-link">Log out</a>
                        </div>

            </div>
        </nav>
    </div>
</div>




<div class="container mt-5">
    <div class="row d-flex justify-content-center">
        <div class="col-md-7">
            <div class="card p-3 py-4">
                <div class="text-center"><img src="https://i.imgur.com/bDLhJiP.jpg" width="100" class="rounded-circle">
                </div>
                <div class="text-center mt-3"><span
                        class="bg-secondary p-1 px-4 rounded text-white">${profile.balance}</span>
                    <h5 class="mt-2 mb-0">${profile.fullName}</h5> <span>Full stack developer</span>
                    <div class="px-4 mt-1">
                        <p class="fonts">Consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et
                            dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi
                            ut aliquip ex ea commodo consequat. </p>
                    </div>
                    <div>
                        <span>${profile.email}</span>
                    </div>
                    <div class="buttons">
                        <a href="/user/profile" class="btn btn-outline-primary px-4">Edit profile</a>
                        <a href="/user/fillBalance1" class="btn btn-outline-primary px-4 ms-3">Fill balance</a>
                    </div>
                    <c:choose>
                        <c:when test="${isFillingBalance == true}">
                            <form action="/user/fillBalance" method="get">
                                <input class="m-1" type="text" placeholder="Enter balance" name="fill"><br>
                                <button class="m-1 btn" style="background-color: #8E24AA; color: white">Fill</button>
                                <br/>
                            </form>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>
