<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UNSW Book</title>
<script>
function checkResults(){
	var results = $('.resultEntry').length;
	if (results == 0){
		$("#results").before("<p>No results found</p>");
	}
}
</script>
</head>
<body onload="checkResults()">
<jsp:include page = "/header.jsp"/>
<div class="container">
	<h2>Search Results</h2>
	<hr>
	<div id="results"> 
		<c:forEach items="${results}" var="entry">
			<div class="resultEntry">
				<div class="imageContainer">
					<a href="controller?action=profile&id=${entry.id}"><img src="img/profile_pictures/default-user.png"></a>
				</div>
				<div class="contentContainer">
					<a href="controller?action=profile&friendid=${entry.id}"><h4>${entry.name}</h4></a>
					<p>${entry.gender}</p>
					<a href="controller?action=addFriend&friendid=${entry.id}" class="btn btn-info" role="button">Add as friend</a>
				</div>
			</div>
			<hr>
		</c:forEach>
	</div>
</div>

</body>
</html>