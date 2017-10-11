<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
#border{
border-style:solid;
border-width:7px;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UNSW BOOK</title>
</head>
<body>
<jsp:include page = "/header.jsp"/>

<h1>Here is your extracted data from the messages: </h1>
<div id = "border">
<c:forEach items="${results.getExtractionResults()}" var="element">
<p>${element}</p>
</c:forEach>
</div>

</body>
</html>