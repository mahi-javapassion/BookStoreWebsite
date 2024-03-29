<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Users - Evergreen Books Store </title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	
	<div align="center">
		<h2>User Management</h2>
		<h3><a href="user_form.jsp">Create New User</a></h3>
	</div>
	
	<div align="center">
		<c:if test="${CREATE_USERS_SUCCESS_KEY != null}">
			<h4 style="color:green;"><i>${CREATE_USERS_SUCCESS_KEY}</i></h4>
		</c:if>
		<c:if test="${UPDATE_USERS_SUCCESS_KEY != null}">
			<h4 style="color:green;"><i>${UPDATE_USERS_SUCCESS_KEY}</i></h4>
		</c:if>
		<c:if test="${DELETE_USERS_SUCCESS_KEY != null}">
			<h4 style="color:green;"><i>${DELETE_USERS_SUCCESS_KEY}</i></h4>
		</c:if>
		<c:if test="${USERS_NOT_FOUND_KEY != null}">
			<h4 style="color:red;"><i>${USERS_NOT_FOUND_KEY}</i></h4>
		</c:if>
		<c:if test="${ADMIN_DELETE_KEY != null}">
			<h4 style="color:red;"><i>${ADMIN_DELETE_KEY}</i></h4>
		</c:if>
	</div>
	
	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>ID</th>				
				<th>Email</th>				
				<th>Full Name</th>				
				<th>Actions</th>				
			</tr>
			<c:forEach var="user" items="${listUsers}" varStatus="status">
   			     <tr>
   			     	<td>${status.index+1}</td>
   			     	<td>${user.userId}</td>
   			     	<td>${user.email}</td>
   			     	<td>${user.fullName}</td>
   			     	<td><a href="edit_user?userId=${user.userId}">Edit</a>&nbsp;&nbsp;&nbsp;<a href="javascript:confirmDelete(${user.userId});">Delete</a></td>
   			     </tr>
   			</c:forEach>
   		</table>
	</div>


	<jsp:directive.include file="footer.jsp"/>
	
		<script type="text/javascript">
	
		function confirmDelete(userId) {
			if(confirm("Are you sure, you want to delete user with User ID :" + userId)) {
				window.location = "delete_user?userId=" + userId;
			}
		}

	</script>

</body>
</html>