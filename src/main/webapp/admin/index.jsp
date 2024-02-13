<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Evergreen Books Store Administration</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	
	<div align="center">
		<h2>Administrative Dashboard</h2>
	</div>
	
	<div align="center">
		<hr width="60%">
		<h2>Quick Actions</h2>
		<a href="create_book">New Book</a>&nbsp;&nbsp;&nbsp;
		<a href="create_user">New User</a>&nbsp;&nbsp;&nbsp;
		<a href="create_category">New Category</a>&nbsp;&nbsp;&nbsp;
		<a href="create_customer">New Customer</a>
	</div>

	<div align="center">
		<hr width="60%">
		<h2>Recent Sales</h2>
	</div>

	<div align="center">
		<hr width="60%">
		<h2>Recent Reviews</h2>
	</div>

	<div align="center">
		<hr width="60%">
		<h2>Statistics</h2>
		<hr width="60%">
	</div>
	
	<jsp:directive.include file="footer.jsp"/>
	
</body>
</html>