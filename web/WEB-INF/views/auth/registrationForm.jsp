<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/12/2022
  Time: 10:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">


</head>
<body>

<%--<div style="padding: 20px">--%>
<%--    <h4>${message}</h4><hr>--%>

<%--    <h3>Please, fill the registration form</h3><br>--%>

<%--    <form action="/register" method="post">--%>

<%--        <input type="text" class="form-control" placeholder="Enter Full name" name="fullName" required><br>--%>
<%--        <input type="email" class="form-control" placeholder="Enter Email" name="email" required><br>--%>
<%--        <input type="password" class="form-control" placeholder="Enter password" name="password" required><br>--%>

<%--        <button type="submit" class="btn btn-primary">Register</button>--%>
<%--    </form>--%>
<%--</div>--%>



<section class="vh-100" style="background-color: #508bfc;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card shadow-2-strong" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">

                        <h3 class="mb-5">Sign up</h3>
                        <form action="/register" method="post">

                            <div class="form-outline mb-4">
                                <input placeholder="Enter full name" name="fullName" type="text" id="typeEmailX-3" class="form-control form-control-lg" />
                            </div>

                            <div class="form-outline mb-4">
                                <input placeholder="Enter Email" name="email" type="email" id="typeEmailX-2" class="form-control form-control-lg" />
                            </div>

                            <div class="form-outline mb-4">
                                <input placeholder="Enter password" name="password" type="password" id="typePasswordX-2" class="form-control form-control-lg" />
                            </div>

                            <!-- Checkbox -->
                            <div class="form-check d-flex justify-content-start mb-4">
                                <input
                                        class="form-check-input"
                                        type="checkbox"
                                        value=""
                                        id="form1Example3"
                                />
                                <label class="form-check-label" for="form1Example3"> Remember password </label>
                            </div>

                            <button class="btn btn-primary btn-lg btn-block" type="submit">Sign up</button>
                        </form>
                        <hr class="my-4">
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>



</body>
</html>
