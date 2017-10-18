<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UNSW BOOK</title>
</head>
<body>

<jsp:include page = "/header.jsp"/>

<form class="form-horizontal" action="controller?action=queryResults" method="POST">

<div class="form-group">
		  <label for="name" class="control-label col-sm-2">Name</label>
		  <div class="col-sm-10">
		  	<input type="text" class=".form-control" id="name" name="name" placeholder="Search People">
		  </div>
		</div>
		
		<label for="Messages" class="control-label col-sm-2">"Messages"</label>
		  <div class="col-sm-10">
		  	<input type="text" class=".form-control" id="Messages" name="Messages" placeholder="Search Messages">
		  </div>
		
		
		<label for="Friend of Friends" class="control-label col-sm-2">Friend of Friends</label>
		  <div class="col-sm-10">
		  	<input type="text" class=".form-control" id="friends" name="friends" placeholder="Friends">
		  
		</div>
		

<button type="submit" class="btn btn-default">Submit Query</button>
</form>



</body>
</html>