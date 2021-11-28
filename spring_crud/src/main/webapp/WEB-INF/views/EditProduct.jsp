<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<h2>Edit Product</h2>
	<s:form action="updateproduct" method="post" modelAttribute="product">


		<s:hidden path="productId" />
	ProductName : <s:input path="productName" />
		<s:errors path="productName"></s:errors>
		<br>
	Price : <s:input path="productPrice" />
		<s:errors path="productPrice"></s:errors>
		<br>
	Qty : <s:input path="productQty" />
		<s:errors path="productQty"></s:errors>
		<br>

		<input type="submit" value="Update Product" />
	</s:form>

</body>
</html>