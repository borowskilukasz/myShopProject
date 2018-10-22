package com.lukaszborowski.ShoppingBackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.lukaszborowski.ShoppingBackend.dao.CategoryDAO;
import com.lukaszborowski.ShoppingBackend.dto.Category;

public class CategroyTest {

	private static AnnotationConfigApplicationContext context;
	private static CategoryDAO categoryDAO;
	private Category category;
	
	@BeforeClass
	public static void init() {
		
		context = new AnnotationConfigApplicationContext();
		context.scan("com.lukaszborowski.ShoppingBackend");
		context.refresh();
		
		categoryDAO = (CategoryDAO)context.getBean("CategoryDAO");
		
		
	}
	
	@Test
	public void testAddCategory() {
		category = new Category();
		category.setName("Television");
		category.setDescryption("Description for television!");
		category.setImageURL("CAT_1.png");
		
		assertEquals("Something gone wrong!",true, categoryDAO.add(category));
	}
}
