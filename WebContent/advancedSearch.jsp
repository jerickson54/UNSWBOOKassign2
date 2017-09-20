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
	<h2>Advanced Search</h2>
	<form class="form-horizontal" action="controller?action=advancedSearch" method="POST">
		<div class="form-group">
		  <label for="name" class="control-label col-sm-2">Name</label>
		  <div class="col-sm-10">
		  	<input type="text" class=".form-control" id="name" name="name" placeholder="Name">
		  </div>
		</div>
		<div class="form-group">
		  <label for="gender" class="control-label col-sm-2">Gender</label>
		  <div class="col-sm-10">
		  <select id ="gender" name = "gender">
		  		<option value = "">Not selected</option>
				<option value = "Male" >Male</option>
				<option value = "Female">Female</option>
		</select>
		  </div>
		</div>
		<div class="form-group">
		  <label for="date" class="control-label col-sm-2">Date of Birth</label>
		  <div class="col-sm-10">
		  	<input type="date" class=".form-control" id="date" name="date">
		  </div>
		</div>
		<div class="form-group">
		  <label for="username" class="control-label col-sm-2">Username</label>
		  <div class="col-sm-10">
		  	<input type="text" class=".form-control" id="username" name="username" placeholder="Username">
		  </div>
		</div>
		<div class="form-group">
		  <label for="zid" class="control-label col-sm-2">zID</label>
		  <div class="col-sm-10">
		  	<input type="text" class=".form-control" id="zid" name="zid" placeholder="zID">
		  </div>
		</div>
      	<button type="submit" class="btn btn-default">Search advanced</button>
	</form>
</div>
</body>
</html>