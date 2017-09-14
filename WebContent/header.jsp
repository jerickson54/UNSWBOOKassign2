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




</style>

<!-- BootStrap  and jquery stuff -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">


<body>

<ul>
  <li><a class="active" href="#home">Home</a></li>
  <li><a href="#news">My Profile</a></li>
  <li><a href="#contact">Search</a></li>
  <form action = 'searchServlet' method = 'GET'>
  
  <input type = "text" id = "searchBar" name = "searchInput" placeholder = "Search friends" />
 	<input type = "submit" value = "Search" />
 
  </form>

</ul>

<div class = "jumbotron text-center" style = "box-shadow:10px 10px 5px #888888;height:50px; background-image:url(img/UNSW_logo.jpg); background-size:100% 100%;">
</div>

</body>
</html>