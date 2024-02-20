<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Category - Evergreen Books Store </title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	
	<div align="center">
		<h2>Category Management</h2>
		<h3><a href="category_form.jsp">Create New Category</a></h3>
	</div>
	
	<div align="center">
		<c:if test="${CREATE_CATEGORY_SUCCESS_KEY != null}">
			<h4 style="color:green;"><i>${CREATE_CATEGORY_SUCCESS_KEY}</i></h4>
		</c:if>
		<c:if test="${UPDATE_CATEGORY_SUCCESS_KEY != null}">
			<h4 style="color:green;"><i>${UPDATE_CATEGORY_SUCCESS_KEY}</i></h4>
		</c:if>
		<c:if test="${DELETE_CATEGORY_SUCCESS_KEY != null}">
			<h4 style="color:green;"><i>${DELETE_CATEGORY_SUCCESS_KEY}</i></h4>
		</c:if>
		<c:if test="${CATEGORY_NOT_FOUND_KEY != null}">
			<h4 style="color:red;"><i>${CATEGORY_NOT_FOUND_KEY}</i></h4>
		</c:if>
	</div>
	
	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>ID</th>				
				<th>Name</th>				
				<th>Actions</th>				
			</tr>
			<c:forEach var="category" items="${listCategory}" varStatus="status">
   			     <tr>
   			     	<td>${status.index+1}</td>
   			     	<td>${category.categoryId}</td>
   			     	<td>${category.name}</td>
   			     	<td><a href="edit_category?categoryId=${category.categoryId}">Edit</a>&nbsp;&nbsp;&nbsp;<a href="javascript:confirmDelete(${category.categoryId});">Delete</a></td>
   			     </tr>
   			</c:forEach>
   		</table>
	</div>

	<jsp:directive.include file="footer.jsp"/>
	
		<script type="text/javascript">
	
		function confirmDelete(categoryId) {
			if(confirm("Are you sure, you want to delete category with Category ID :" + categoryId)) {
				window.location = "delete_category?categoryId=" + categoryId;
			}
		}
	</script>
</body>
</html>