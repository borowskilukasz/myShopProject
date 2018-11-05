<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<div class="container">
	<div class="row">
	
		<c:if test="${not empty message}">
			<div class="col-xs-12">
				<div class="alert alert-success alert-dismissible">
					<button type="button" class="close" data-dismiss="alert">&times</button>
					${message}
					
				</div>
			</div>
		</c:if>
		<div class="col-md-offset-2 col-md-8">
		
		
			<div class="panel panel-primary">
			
					<div class="panel-heading">
						<h4>Product Management</h4>
					</div>
					
					
					<div class="panel-body">					
						<sf:form class="form-horizontal" modelAttribute="product"
						 action="${contextRoot}/manage/products"
						 method="POST"
						 enctype="multipart/form-data">						
							<div class="form-group">							
								<label class="control-label col-md-4" for="name">Enter product name:</label>
								<div class="col-md-8">
									<sf:input type="text" path="name" id="name" placeholder="Product Name" class="form-control"/>
									<sf:errors path="name" cssClass="help-block" element="em" />
								</div>
							</div>
							<div class="form-group">							
								<label class="control-label col-md-4" for="brand">Enter brand name:</label>
								<div class="col-md-8">
									<sf:input type="text" path="brand" id="brand" placeholder="Product Brand" class="form-control"/>									
									<sf:errors path="brand" cssClass="help-block" element="em" />
								</div>
							</div>
							<div class="form-group">							
								<label class="control-label col-md-4" for="description">Enter description:</label>
								<div class="col-md-8">
									<sf:textarea path="description" id="description" placeholder="Description" class="form-control"></sf:textarea>
									<sf:errors path="description" cssClass="help-block" element="em" />
								</div>
							</div>
							
							<div class="form-group">							
								<label class="control-label col-md-4" for="brand">Enter unit price:</label>
								<div class="col-md-8">
									<sf:input type="number" path="unitPrice" id="price" placeholder="Product Unit Price" class="form-control"/>
									<sf:errors path="unitPrice" cssClass="help-block" element="em" />
								</div>
							</div>
							<div class="form-group">							
								<label class="control-label col-md-4" for="brand">Quantity Available:</label>
								<div class="col-md-8">
									<sf:input type="number" path="quantity" id="quantity" placeholder="Product Quantity" class="form-control"/>
								</div>
							</div>
							<!-- file element for image -->
							<div class="form-group">							
								<label class="control-label col-md-4" for="file">Select an Image:</label>
								<div class="col-md-8">
									<sf:input type="file" path="file" id="file" class="form-control"/>
									<sf:errors path="file" cssClass="help-block" element="em" />
								</div>
							</div>
							<div class="form-group">							
								<label class="control-label col-md-4" for="categoryId">Select Category:</label>
								<div class="col-md-8">
									<sf:select class="form-control" path="categoryId" id="categoryId"
									items="${categories}"
									itemLabel="name"
									itemValue="id"
									/>
									<c:if test="${product.id == 0}">
									<div class="text-right">
										<br/>
										<button type="button" data-toggle="modal" data-target="#myCategoryModal" class="btn btn-warning btn-xs">Add Category</button>
									</div>
									</c:if>									
								</div>
							</div>
							<div class="form-group">							
								<div class="col-md-offset-4 col-md-8">
									<input type="submit" name="submit" id="submit" value="submit" class="btn btn-primary"/>
								<!--  hiden fields -->
									<sf:hidden path="id"/>
									<sf:hidden path="code"/>
									<sf:hidden path="supplierId"/>
									<sf:hidden path="purchases"/>
									<sf:hidden path="views"/>
								
								</div>
							</div>
						</sf:form>
					</div>
			</div>
		
		</div>
	</div>	
	<div class="row">
		<div class="col-xs-12">
			<h3>Available Products</h3></br>
		</div>
	</div>
		<div class="row">
		<div class="col-xs-12">
			<div style="overflow:auto">
				<table id="adminProductTable" class="table table-striped table-bordered">
					<thread>
						<tr>
							<th>Id</th>
							<th>&#160</th>
							<th>Name</th>
							<th>Quantity</th>
							<th>Unit Price</th>
							<th>Active</th>
							<th>Edit</th>							
						</tr>
					</thread>
					<tfoot>
						<tr>
							<th>Id</th>
							<th>&#160</th>
							<th>Name</th>
							<th>Quantity</th>
							<th>Unit Price</th>
							<th>Active</th>
							<th>Edit</th>							
						</tr>
					</tfoot>
				
				
				</table>
			</div>
		</div>
		
		
	</div>
	
	<div class="modal fade" id="myCategoryModal" role="dialog" tabindex="-1">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
			<!-- Modal Header -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
					<h4 class="modal-title">Add new Category</h4>
				</div>
				<div class="modal-content">
					<!-- Category Form  -->
					<sf:form id="categoryForm" modelAttribute="category" action="${contextRoot}/manage/category"
					method="POST" class="form-horizontal">
						<div class="form-group">
							<label for="name" class="control-label col-md-4">Category Name</label>
							<div class="col-md-8">
								<sf:input type="text" path="name" id="category_name" class="form-control"/>
							</div>
						</div>
						<div class="form-group">
							<label for="description" class="control-label col-md-4">Category Description</label>
							<div class="col-md-8">
								<sf:input cols="" rows="5" path="description" id="category_description" class="form-control"/>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-offset-4 col-md-8">
								<input type="submit" value="Add Category" class="btn btn-primary"/>
							</div>
						</div>
					</sf:form>
				</div>					
			</div>
		</div>
	</div>
	
</div>
		