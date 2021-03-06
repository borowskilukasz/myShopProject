<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container">
		<a class="navbar-brand" href="${contextRoot}/home">Online Shop</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="nav navbar-nav">
				<li class="nav-item" id="home"><a class="nav-link"
					href="${contextRoot}/home">Home <span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item" id="products"><a class="nav-link"
					href="${contextRoot}/show/all/products">Products</a></li>
				<security:authorize access="hasAuthority('ADMIN')">
				<li class="nav-item" id="manageProducts"><a class="nav-link"
					href="${contextRoot}/manage/products">Manage</a></li>
				</security:authorize>
				<li class="nav-item" id="about"><a class="nav-link"
					href="${contextRoot}/about">About</a></li>

				<li class="nav-item" id="contact"><a class="nav-link"
					href="${contextRoot}/contact">Contact</a></li>
			</ul>

			<ul class="nav navbar-nav ml-auto">
				<security:authorize access="isAnonymous()">
					<li class="nav-item" id="contact"><a class="nav-link"
						href="${contextRoot}/register">Sign Up</a></li>

					<li class="nav-item" id="contact"><a class="nav-link"
						href="${contextRoot}/login">Login</a></li>
				</security:authorize>

				<security:authorize access="isAuthenticated()">
					<li class="dropdown" id="userCart"><a
						class="btn btn-default dropdown-toggle" href="javascript:void(0)"
						id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="true"> ${userModel.fullName} <span
							class="caret"></span>
					</a>
						<ul class="dropdown-menu">

							<security:authorize access="hasAuthority('USER')">
								<li><a href="${contextRoot}/cart/show"> <span
										class="glyphicon glyphicon-shopping-cart"></span> <span
										class="badge">${userModel.cart.cartLines}</span> PLN
										${userModel.cart.grandTotal}
								</a></li>
								<li class="divider" role="separator"></li>
							</security:authorize>
							<li><a href="${contextRoot}/perform-logout">Logout</a></li>
						</ul></li>
				</security:authorize>
			</ul>
		</div>
	</div>
</nav>
<script>
	window.userRole = '${userModel.role}';
</script>
