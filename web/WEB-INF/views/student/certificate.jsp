<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="org.springframework.format.datetime.DateFormatter" %>
<%@ page import="java.time.LocalDate" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 3/5/2022
  Time: 12:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Certificate</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
</head>
<body>
<div style="padding: 20px;">

    <div style="width:800px; height:650px; padding:20px; text-align:center; border: 10px solid #787878">

        <div style="width:750px; height:600px; padding:20px; text-align:center; border: 5px solid #787878">

            <span style="font-size:50px; font-weight:bold">Certificate of Completion</span>
            <br><br>

            <span style="font-size:25px"><i>This is to certify that</i></span>
            <br><br>

            <span style="font-size:30px"><b>${user.fullName}</b></span><br/><br/>

            <span style="font-size:25px"><i>has completed the course</i></span> <br/><br/>

            <span style="font-size:30px">${course.name}</span> <br/><br/>

            <span style="font-size:20px">with good scores</span> <br/><br/><br/><br/>

            <span style="font-size:25px"><i>dated</i></span><br>

            <span style="font-size:30px">
                    <%= LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"))%>
        </span>
            <br>
        </div>

    </div>
</div>
</body>
</html>
