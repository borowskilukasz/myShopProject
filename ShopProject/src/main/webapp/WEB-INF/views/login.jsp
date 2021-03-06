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

<title>Shopp project -${title}</title>

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
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" role="navigation">
			<div class="container">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<a class="navbar-brand" href="${contextRoot}/home">Shop Project</a>
				</div>
			</div>
		</nav>
		<!-- Page Content -->
		<div class="content">

			<c:if test="${not empty message}">
				<div class="row">
					<div class="offset-md-3 col-md-6">
						<div class="alert alert-danger">${message}</div>
					</div>
				</div>
			</c:if>
			
			<c:if test="${not empty logout}">
				<div class="row">
					<div class="offset-md-3 col-md-6">
						<div class="alert alert-danger">${logout}</div>
					</div>
				</div>
			</c:if>


			<div class="container">
				<div class="row">					
					<div class="col"></div>
					<div class="col-md-6 border">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4>Login</h4>
							</div>
							<div class="panel-body">
								<form action="${contextRoot}/login" method="POST"
									class="form-horizontal" id="loginForm">
									<div class="form-group">
										<label for="username" class="col-md-4 control-label">Email:
										</label>
										<div class="col-md-8">
											<input type="text" name="username" id="username"
												class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<label for="password" class="col-md-4 control-label">Password:
										</label>
										<div class="col-md-8">
											<input type="password" name="password" id="password"
												class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<div class="offset-md-4 col-md-8">
											<input type="submit" value="Login" class="btn btn-primary" />
											<input type="hidden" name="${_csrf.parameterName}"
												value="${_csrf.token}" />
										</div>
									</div>
								</form>

							</div>
							<div class="panel-footer">
								<div class="text-right">
									New User - <a href="${contextRoot}/register">Register Here</a>
								</div>
							</div>
						</div>

					</div>					
					<div class="col"></div>
				</div>

			</div>
		</div>
	</div>
	<!-- Footer -->
	<%@include file="./shared/footer.jsp"%>
	<!-- Bootstrap core JavaScript -->
	<script src="${js}/jquery.min.js"></script>
	<script src="${js}/bootstrap.min.js"></script>
	<!--  selfcoded javascript -->
	<script src="${js}/myapp.js"></script>

	<!--  jQuery validation -->
	<script src="${js}/jquery.validate.js"></script>


</body>

</html>
