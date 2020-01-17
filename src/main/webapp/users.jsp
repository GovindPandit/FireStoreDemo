<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>User Key</th>
			<th>Username</th>
			<th>Email</th>
			<th>Password</th>
			<th>Edit | Delete</th>
		</tr>
		<c:forEach items="${users}" var="u">
		<tr>
			<td>${u.userkey}</td>
			<td>${u.username}</td>
			<td>${u.email}</td>
			<td>${u.password}</td>
			<td><a href="Edit">Edit</a> | <a href="DeleteController?key=${u.userkey}">Delete</a></td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>