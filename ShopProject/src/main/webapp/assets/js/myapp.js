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
});