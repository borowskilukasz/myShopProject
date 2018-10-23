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
		default:
			$('#products').addClass('active');
			$('#a_'+menu).addClass("active");
	}
	
	
	//code for jquery dataTable
	//create a dataset
	var products = [
		
		['1', 'ABC'],
		['2', 'ERT'],
		['3', 'ZXC'],
		['4', 'QWE'],
		['5', 'DEF'],
		['6', 'GHI'],
		['7', 'JKL'],
	];
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
});