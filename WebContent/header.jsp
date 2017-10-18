<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page import="servletAndBeans.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>UNSW BOOK</title>
</head>

<!-- BootStrap  and jquery stuff -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="style.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<body>

<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="default.jsp">Home</a>
    </div>

    <div class="collapse navbar-collapse" id="navbar">
      <ul class="nav navbar-nav">
        <c:if test="${hasLoggedIn}" >
          <li><a href="controller?action=login&isOwnProfile=true">My Profile</a></li>
      		<li><a href = "controller?action=updateInfo"> Update Info</a></li>
        </c:if>
        <li><a href="advancedSearch.jsp">Advanced Search</a></li>
        <li><a href = "graphQuery.jsp"> Graph Display</a></li>
        
      </ul>
      <form class="navbar-form navbar-left" action="controller?action=search" method="post">
        <div class="form-group">
          <input type="text" class="form-control" id="searchBar" name="searchInput" placeholder="Search friends">
        </div>
        <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
      </form>
      
      
      <c:if test="${hasLoggedIn}" >
        <ul>
          <!-- Bell for notifications? -->
          <li>
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
              <span class="glyphicon glyphicon-bell" style="font-size:20px;margin-left: 100px;color:white;margin-top:10px;" onclick=""></span>
            </a>
            <ul class="dropdown-menu">
              <c:forEach items ="${notifications}" var="entry">
                <li><a href="#">${entry}</a></li>
              </c:forEach>
            </ul>
          </li>
          
          <li style="margin-top:-20px;">
            <a  href="controller?action=logout" style = "margin-left:400px;color:white;">Logout</a>
          </li>
        </ul>

      </c:if>
    </div>
  </div>
</nav>

</body>
</html>