<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create New User</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	
	<div align="center">
		<c:if test="${userData == null || userData.userId == null}">
			<h2>Create New User</h2>
		</c:if>
		<c:if test="${userData != null && userData.userId != null}">
			<h2>Edit User</h2>
		</c:if>
	</div>
	<div align="center">
		<c:if test="${USERS_EXISTS_KEY != null}">
			<h4 style="color:red;"><i>${USERS_EXISTS_KEY}</i></h4>
		</c:if>
		<c:if test="${USERS_NOT_FOUND_KEY != null}">
			<h4 style="color:red;"><i>${USERS_NOT_FOUND_KEY}</i></h4>
		</c:if>
	</div>
	
	<div align="center">
		<form action="${userData == null || userData.userId == null ? 'create_user' : 'update_user'}" method="post" onsubmit="return validateFormInput();">
			<table>
				<c:if test="${userData != null && userData.userId != null}">
					<input type="hidden" id="userId" name="userId" size="20" value="${userData.userId}">
					<tr>
						<td align="right">User ID:</td>
						<td align="left">${userData.userId}</td>
					</tr>
				</c:if>
				<tr>
					<td align="right">Email:</td>
					<td align="left"><input type="text" id="email" name="email" size="20" value="${userData.email}"></td>
				</tr>
				<tr>
					<td align="right">Full Name:</td>
					<td align="left"><input type="text" id="fullName" name="fullName" size="20" value="${userData.fullName}"></td>
				</tr>
				<tr>
					<td align="right">Password:</td>
					<td align="left"><input type="password" id="password" name="password" size="20" value="${userData.password}"></td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="${userData == null || userData.userId == null ? 'Save User' : 'Update User'}" size="20">
						<input type="button" value="Cancel" size="20" onclick="javascript:history.go(-1);">
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<jsp:directive.include file="footer.jsp"/>

	<script type="text/javascript">
	
		function validateFormInput() {
			var isValid = true;
			var fieldEmail = document.getElementById("email");
			var fieldFullName = document.getElementById("fullName");
			var fieldPassword = document.getElementById("password");
			
			if(fieldEmail.value.length == 0) {
				alert("Email is required");
				fieldEmail.focus();
				isValid = false;
			}
			else if(fieldFullName.value.length == 0) {
				alert("Full Name is required");
				fieldFullName.focus();
				isValid = false;
			}
			else if(fieldPassword.value.length == 0) {
				alert("Password is required");
				fieldPassword.focus();
				isValid = false;
			}
			return isValid;
		}
	</script>

</body>
</html>