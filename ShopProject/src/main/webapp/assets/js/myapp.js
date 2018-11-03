$(function() {
	
	switch(menu) {
		
		case 'Home':
			$('#home').addClass('active');
			break;
		case 'About us':
			$('#about').addClass('active');
			break;
		case 'Contact':
			$('#contact').addClass('active');
			break;
		case 'Products':
			$('#products').addClass('active');
			break;
		case 'All Products':
			$('#products').addClass('active');
			break;
		case 'Manage Products':
			$('#manageProducts').addClass('active');
			break;
		default:
			$('#products').addClass('active');
			$('#a_'+menu).addClass("active");
	}
	
	
	//code for jquery dataTable
	//create a dataset

	var $table = $('#productListTable');
	
	//execute the below code only when we have this table
	if($table.length){		
		//console.log(window.contextRoot);
		var jsonUrl = '';
		if(window.categoryId == ''){
			jsonUrl = window.contextRoot + '/json/data/all/products';
		}else{
			jsonUrl = window.contextRoot + '/json/data/category/' + window.categoryId + '/products';			
		} 
		$table.DataTable({
			lengthMenu: [[3,5,10,-1], ['3 Records', '5 Records','10 Records','ALL']],
			pageLength: 5,
			ajax: {
				url: jsonUrl,
				dataSrc: ''
			},
			columns: [
					{
						data:'code',
						bSortable: false,
						mRender: function(data, type, row){
							return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="dataTableImg"/>';
						}
					},
					{
						data:'name',					
					},
					{
						data:'brand',					
					},
					{
						data:'unitPrice',					
					},
					{
						data:'quantity',
						mRender: function(data, type, row){
							if(data < 1){
								return '<span style="color:red">Out of Stock!</span>';
							}
							return data;
						}
					},		
					{
						data:'id',
						bSortable: false, 
						mRender: function(data, type, row){
							var str = '';
							str += '<a href="'+window.contextRoot+'/show/'+data+'/product" class="btn btn-primary"> <span class="glyphicon glyphicon-envelope"> </span>View </a>';
							
							if(row.quantity < 1){
								str += '<a href="javascript:void(0)"class="btn btn-success disabled"> <span class="glyphicon glyphicon-envelope"> </span>Buy </a>';
							}else{
							str += '<a href="'+window.contextRoot+'/cart/add/'+data+'/product"class="btn btn-success"> <span class="glyphicon glyphicon-envelope"> </span>Buy </a>';
							}
							return str;
						}
					}
					
					
			]
		
		});
		
	}
	
	//dismissing the alert after 3 seconds
	var $alert =$('.alert');
	
	if($alert.length) {
		setTimeout(function(){
			$alert.fadeOut('slow');
		}, 3000)
	}
	
	//-----------------------------------------------------------------
	
	
	
	
	//Datatable for admin
	
	
	
	
	
var $adminProductsTable = $('#adminProductTable');
	
	//execute the below code only when we have this table
	if($adminProductsTable.length){		
		//console.log(window.contextRoot);
		var jsonUrl = window.contextRoot + '/json/data/admin/all/products';

		$adminProductsTable.DataTable({
			lengthMenu: [[10, 30, 50, -1], ['10 Records', '30 Records','50 Records','ALL']],
			pageLength: 30,
			ajax: {
				url: jsonUrl,
				dataSrc: ''
			},
			columns: [				
					{
						data:'id'
					},
					{
						data:'code',
						bSortable: false,
						mRender: function(data, type, row){
							return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="adminDatatableImg"/>';
						}
					},
					{
						data:'name',					
					},
					{
						data:'brand',					
					},
					{
						data:'quantity',
						mRender: function(data, type, row){
							if(data < 1){
								return '<span style="color:red">Out of Stock!</span>';
							}
							return data;
						}
					},
					{
						data:'unitPrice',					
					},
					{
						data:'isActive',
						bSortable: false,
						mRender: function(data, type, row){
							var str= '';
							
							str += '<label class="switch">';
							if(data){
								str += '<input type="checkbox" checked="checked" value="'+row.id+'"/>';
							}else{
								str += '<input type="checkbox"  value="'+row.id+'"/>';								
							}
								str += '<div class="slider"></div>'	;
								str += '</label>';
							return str;
						}
						
					},
					{
						data:'id',
						bSortable: false,
						mRender: function(data, type, row){
							var str = '';
							str += '<a href="${contextRoot}/manage/'+data+'/product" class="btn btn-warning">';
							str += '<span class="glyphicon glyphicon-pencil"></span></a>';
							return str;
						}
					}					
			],
			initComplete: function(){
				var api = this.api();
				api.$('.switch input[type="checkbox"]').on('change', function() {
					var checkbox = $(this);
					var checked = checkbox.prop('checked');
					var dMsg = (checked)? 'You want to activate the product?':
										  'You want to deactivate the product?';
					var value = checkbox.prop('value');
					
					bootbox.confirm({
						message: dMsg,
						callback: function(confirmed) {
							if(confirmed){
								console.log(value);
								var activationUrl = window.contextRoot + '/manage/product/' + value + '/activation';
								$.post(activationUrl, function(data){
									bootbox.alert({
										size:'medium',
										message: data
									});									
								})
								
							}else{
								checkbox.prop('checked', !checked);
							}
						}
					});				
				});
			}
		});
		
	}
	
});