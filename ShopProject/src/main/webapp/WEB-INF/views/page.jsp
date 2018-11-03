<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Online Shop -${title}</title>

<script>
	window.menu = '${title}';
	window.contextRoot = '${contextRoot}';
</script>
<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap readable theme  -->

<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">
<!-- Bootstrap readable theme  -->

<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${css}/myapp.css" rel="stylesheet">

</head>

<body>
	<div class="wrapper">
		<!-- Navigation -->
		<%@include file="./shared/navbar.jsp"%>

		<!-- Page Content -->
		<div class="content">
			<c:if test="${userClickHome ==true}">
				<%@include file="home.jsp"%>
			</c:if>
			<c:if test="${userClickProducts ==true}">
				<%@include file="products.jsp"%>
			</c:if>
			<!-- Load only when about us is clicked -->
			<c:if test="${userClickAboutUs ==true}">
				<%@include file="about.jsp"%>
			</c:if>
			<!-- Load only when contact is clicked -->
			<c:if test="${userClickContact ==true}">
				<%@include file="contact.jsp"%>
			</c:if>
			<!-- Load only when category is clicked -->
			<c:if test="${userClickProducts == true or userClickCategoryProducts == true }">
				<%@include file="listProducts.jsp"%>
			</c:if>
			<!-- Load only when show products is clicked -->
			<c:if test="${userClickShowProduct == true}">
				<%@include file="singleProduct.jsp"%>
			</c:if>
			<!-- Load only when manage product is clicked -->
			<c:if test="${userClickManageProduct == true}">
				<%@include file="manageProducts.jsp"%>
			</c:if>
		</div>
	</div>
		<!-- Footer -->
		<%@include file="./shared/footer.jsp"%>
		<!-- Bootstrap core JavaScript -->
		<script src="${js}/jquery.min.js"></script>
		<script src="${js}/bootstrap.min.js"></script>
		<!-- Datatable plugin -->
		<script src="${js}/jquery.dataTables.js"></script>
		<!-- Datatable bootstrap -->		
		<script src="${js}/dataTables.bootstrap.js"></script>
		<!-- Bootbox -->		
		<script src="${js}/bootbox.min.js"></script>
		<!--  selfcoded javascript -->
		<script src="${js}/myapp.js"></script>

	
</body>

</html>
