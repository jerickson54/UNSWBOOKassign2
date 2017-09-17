<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UNSW Book</title>
</head>
<body>
<jsp:include page = "/header.jsp"/>
<div class="container">
	<h2>${user.name}</h2>
	<p>Username: ${user.username}</p>
	<p>Gender: ${user.gender}</p>
	<p>Date of birth: ${user.dob}</p>
	<p>Age: ${user.age}</p>
	<p>Email: ${user.emailAddress}</p>
	<a href="#" class="btn btn-info" role="button">Add as friend</a>
</div>

</body>
</html>