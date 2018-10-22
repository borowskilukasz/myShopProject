package com.lukaszborowski.ShoppingBackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.lukaszborowski.ShoppingBackend.dao.ProductDAO;
import com.lukaszborowski.ShoppingBackend.dto.Product;

public class ProductTest {

	private static AnnotationConfigApplicationContext context;
	private static ProductDAO productDAO;
	private Product product;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.lukaszborowski.ShoppingBackend");
		context.refresh();
		
		productDAO = (ProductDAO)context.getBean("ProductDAO");
	}
	
	@Test
	public void testCRUDProduct() {
		
		//creating
		product = new Product();
		product.setName("Oppo Selfie S23");
		product.setBrand("Oppo");
		product.setDescription("This is some descryption for Oppo mobile phone");
		product.setUnitPrice(2500);
		product.setActive(true);
		product.setCategoryId(3);
		product.setSupplierId(3);
		assertEquals("Something went wrong in adding!", true, productDAO.add(product));
		
		//reading and updating the category
		product = productDAO.get(2);
		product.setName("Samsung Galaxy S7");
		
		assertEquals("Something went wrong in updating or reading!", true, productDAO.update(product));
		
		assertEquals("Something went wrong while deleting record!", true, productDAO.delete(product));
		assertEquals("Something went wrong while fetching the list of products!", 8, productDAO.list().size());
		
		
		
		
		
	}
}
