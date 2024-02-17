<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create New User</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	
	<div align="center">
		<h2>Create New User</h2>
	</div>
	
	<div align="center">
		<form action="create_user" method="post" onsubmit="return validateFormInput();">
			<table>
				<tr>
					<td align="right">Email:</td>
					<td align="left"><input type="text" id="email" name="email" size="20"></td>
				</tr>
				<tr>
					<td align="right">Full Name:</td>
					<td align="left"><input type="text" id="fullName" name="fullName" size="20"></td>
				</tr>
				<tr>
					<td align="right">Password:</td>
					<td align="left"><input type="password" id="password" name="password" size="20"></td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="Save User" size="20">
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