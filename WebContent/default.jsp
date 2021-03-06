<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
</style>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UNSW Book</title>

</head>
<body>
<jsp:include page = "/header.jsp"/><div id="userWall">
<div class="container">
	<h1>Welcome to UNSW Book, Login or register a new user</h1>
	<h2>Login</h2>
	<c:if test="${falseLogin}">
		<div style="color: red">Please enter a valid username and password</div>
		<br>
	</c:if>
	<c:if test="${userBanned}">
		<div style="color: red"> Sorry, you have been previously banned by our systems admin</div>
		<br>
	</c:if>
	<form action = 'controller?action=login' method = 'post'>
	<label>Username:
		<input type = "text" name="username" required/>
	</label>
	<label>Password
	<input type="password" name="password" required/>
	</label>
	<input type = "submit" value = "Login" />
	</form>
	<h2>Register</h2>

<form action = 'controller?action=register' method = 'post'>

<label> Name: 
<input type = "text" name = "name" required/>
</label>

<label> Email Address: 
<input type = "text" name = "emailAddress" required/>
</label>

<label> Gender: 
<select id ="gender" name = "gender">
<option value = "Male" >Male</option>
<option value = "Female">Female</option>
</select>
</label>

<label> Date of birth: 
<select id = "month" name = "month">
<option value = "1" >January</option>
<option value = "2">February</option>
<option value = "3">March</option>
<option value = "4">April</option>
<option value = "5">May</option>
<option value = "6">June</option>
<option value = "7">July</option>
<option value = "8">August</option>
<option value = "9">September</option>
<option value = "10">October</option>
<option value = "11">November</option>
<option value = "12">December</option>
</select>

<select id ="day" name = "day">
<c:forEach begin = "1" end="31" varStatus = "loop">
<option value = "${loop.index}">${loop.index}</option>
</c:forEach>
</select>

<select id ="year" name = "year">
<c:forEach begin = "1900" end="2017" varStatus = "loop2">
<option value = "${loop2.index}">${loop2.index}</option>
</c:forEach>
</select>
</label>

<label> Username: 
<input type = "text" name = "username" required/>
</label>

<label> Password: 
<input type = "password" name = "password" required/>
</label>

<label> Z-ID: 
<input type = "text" name = "zid" required/>
</label>

 <input type = "submit" value = "Create Account" />
 </form>
		

</body>
</html>