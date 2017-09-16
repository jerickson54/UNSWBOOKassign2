<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UNSW Book</title>
<style>
.imageContainer {
	display: inline-block;
	width: 110px;
	height: 110px;
}

.imageContainer img{
	max-width: 100%;
	max-height: 100%;
}

.contentContainer{
	display: inline-block;
	margin-left: 15px;
}

.resultEntry{
	display: flex;
}
</style>
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
	<!-- Test result -->
	<hr>
	<div id="results"> 
		<div class="resultEntry">
			<div class="imageContainer">
				<a href="#"><img src="img/PN.jpg"></a>
			</div>
			<div class="contentContainer">
				<a href="#"><h4>Patrik Norlin</h4></a>
				<span>Sydney</span>
			</div>
		</div>
		<hr>
	</div>
</div>

</body>
</html>