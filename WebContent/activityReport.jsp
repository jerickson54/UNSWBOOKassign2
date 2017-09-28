<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jason
  Date: 28/9/17
  Time: 12:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Activity Report</title>
    <style type="text/css">
        .table {
            width: 80%;
            margin-left: 5%;
        }
    </style>
</head>
<body>
<jsp:include page = "/header.jsp"/>
<div id="displayUserReport">
    <table class="table">
        <thead>
        <tr>
            <th>Time Stamp</th>
            <th>Description</th>
        </tr>
        <tbody>
    <c:forEach items="${activities}" var="entry">
        <tr>
            <td>${entry.time}</td>
            <td>${entry.description}</td>
        </tr>
    </c:forEach>
    </tbody>
    </table>
</div>
</body>
</html>
