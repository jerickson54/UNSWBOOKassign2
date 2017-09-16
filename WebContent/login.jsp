<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New User</title>
</head>
<style>
label{
color:blue;
}
</style>
<body>

<jsp:include page = "/header.jsp"/>

<form action = 'newUser' method = 'GET'>

<label> Name: 
<input type = "text" name = "name"/>
</label>

<label> Email Address: 
<input type = "text" name = "emailAddress"/>
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
<input type = "text" name = "username"/>
</label>

<label> Password: 
<input type = "password" name = "password"/>
</label>

<label> ZID: 
<input type = "text" name = "zid"/>
</label>

 <input type = "submit" value = "Search" />
 
 
 </form>

</body>
</html>