<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="servletAndBeans.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UNSW BOOK</title>
</head>
<style>
/*
ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: #333;
}

li {
    float: left;
}

li a {
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

li a:hover:not(.active) {
    background-color: #111;
}

.active {
    background-color: #ffff00;
    color:black;
}
*/
</style>

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
        <li><a href="#">My Profile</a></li>
        <li><a href="#">Advanced Search</a></li>
      </ul>
      <form class="navbar-form navbar-left" action="search" method="GET">
        <div class="form-group">
          <input type="text" class="form-control" id="searchBar" name="searchInput" placeholder="Search friends">
        </div>
        <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
      </form>
    </div>
  </div>
</nav>
<!--
<ul>
  <li><a class="active" href="#home">Home</a></li>
  <li><a href="#news">My Profile</a></li>
  <li><a href="#contact">Search</a></li>
  <form action = 'searchServlet' method = 'GET'>
  
  <input type = "text" id = "searchBar" name = "searchInput" placeholder = "Search friends" />
 	<input type = "submit" value = "Search" />
 
  </form>
</ul>
-->


<div class = "jumbotron text-center" style = "box-shadow:10px 10px 5px #888888;height:50px; background-image:url(img/UNSW_logo.jpg); background-size:auto 100%;">
</div>

</body>
</html>