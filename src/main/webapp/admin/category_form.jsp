<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create New Category</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	
	<div align="center">
		<c:if test="${categoryData == null || category.categoryId == null}">
			<h2>Create New Category</h2>
		</c:if>
		<c:if test="${categoryData != null && category.categoryId != null}">
			<h2>Edit Category</h2>
		</c:if>
	</div>
	<div align="center">
		<c:if test="${CATEGORY_EXISTS_KEY != null}">
			<h4 style="color:red;"><i>${CATEGORY_EXISTS_KEY}</i></h4>
		</c:if>
		<c:if test="${CATEGORY_NOT_FOUND_KEY != null}">
			<h4 style="color:red;"><i>${CATEGORY_NOT_FOUND_KEY}</i></h4>
		</c:if>
	</div>
	
	<div align="center">
		<form action="${categoryData == null || categoryData.categoryId == null ? 'create_category' : 'update_category'}" method="post" 
		onsubmit="return validateFormInput();">
			<table>
				<c:if test="${categoryData != null && categoryData.categoryId != null}">
					<input type="hidden" id="categoryId" name="categoryId" size="20" value="${categoryData.categoryId}">
					<tr>
						<td align="right">Category ID:</td>
						<td align="left">${categoryData.categoryId}</td>
					</tr>
				</c:if>
				<tr>
					<td align="right">Name:</td>
					<td align="left"><input type="text" id="name" name="name" size="20" value="${categoryData.name}"></td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="${categoryData == null || categoryData.categoryId == null ? 'Save Category' : 'Update Category'}" size="20">
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
			var fieldName = document.getElementById("name");
			
			if(fieldName.value.length == 0) {
				alert("Name is required");
				fieldName.focus();
				isValid = false;
			}
			return isValid;
		}
	</script>

</body>
</html>