<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@include file="../shared/flows-header.jsp"%>
<div class="content">
	<div class="row">

		<div class="col-md-6 col-md-offset-3">

			<div class="panel panel-primary">

				<div class="panel-heading">
					<h4>Sign Up - Address</h4>
				</div>

				<div class="panel-body">

					<sf:form method="POST" class="form-horizontal" id="billingForm"
						modelAttribute="billing">




						<div class="form-group">
							<label class="control-label col-md-4" for="street">Street</label>
							<div class="col-md-8">
								<sf:input type="text" path="street" class="form-control"
									placeholder="Enter Street" />
									
       							<sf:errors path="street" cssClass="help-block" element="em"/>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="city">City</label>
							<div class="col-md-8">
								<sf:input type="text" path="city" class="form-control"
									placeholder="Enter City Name" />
									
       							<sf:errors path="city" cssClass="help-block" element="em"/>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="postalCode">Postal
								Code</label>
							<div class="col-md-8">
								<sf:input type="text" path="postalCode" class="form-control"
									placeholder="XXXXXX" />
									
       							<sf:errors path="postalCode" cssClass="help-block" element="em"/>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="state">State</label>
							<div class="col-md-8">
								<sf:input type="text" path="state" class="form-control"
									placeholder="Enter State Name" />
									
       							<sf:errors path="state" cssClass="help-block" element="em"/>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="country">Country</label>
							<div class="col-md-8">
								<sf:input type="text" path="country" class="form-control"
									placeholder="Enter Country Name" />
									
       							<sf:errors path="country" cssClass="help-block" element="em"/>
							</div>
						</div>


						<div class="form-group">
							<div class="col-md-offset-4 col-md-8">
								<button type="submit" class="btn btn-primary"
									name="_eventId_personal">Back</button>

								<button type="submit" class="btn btn-primary"
									name="_eventId_confirm">Confirm</button>
							</div>
						</div>


					</sf:form>
				</div>
			</div>
		</div>
	</div>
</div>
<%@include file="../shared/flows-footer.jsp"%>