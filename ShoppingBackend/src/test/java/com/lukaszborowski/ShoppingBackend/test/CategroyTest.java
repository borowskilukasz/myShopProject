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
	public void testCRUDCategory() {
		
		//create category
		category = new Category();
		category.setName("Tablets");
		category.setDescryption("Description for tablets!");
		category.setImageURL("CAT_4.png");
		
		assertEquals("Something gone wrong!",true, categoryDAO.add(category));
		category = new Category();
		category.setName("Television");
		category.setDescryption("Description for television!");
		category.setImageURL("CAT_5.png");
		
		assertEquals("Something gone wrong!",true, categoryDAO.add(category));
		//read category by id
		category = categoryDAO.get(2);
		//update category
		category.setName("TV");
		assertEquals("Something goes wrong!", true, categoryDAO.update(category));
		//delete categry
		assertEquals("Something goes wrong!", true, categoryDAO.delete(category));
		
	}
	/*
	@Test
	public void testAddCategory() {
		category = new Category();
		category.setName("Laptop");
		category.setDescryption("Description for laptop!");
		category.setImageURL("CAT_3.png");
		
		assertEquals("Something gone wrong!",true, categoryDAO.add(category));
	}
	@Test 
	public void testGetCategoery() {
		
		category = categoryDAO.get(1);
		assertEquals("Something goes wrong!","Television", category.getName());
	}
	
	@Test
	public void updateTestCategory() {
		category = categoryDAO.get(1);
		
		category.setName("TV");
		
		assertEquals("Something goes wrong!", true, categoryDAO.update(category));
	}
	
	@Test
	public void deleteTestCategory() {
		category = categoryDAO.get(1);
		
		category.setName("TV");
		
		assertEquals("Something goes wrong!", true, categoryDAO.delete(category));
	}
	
	@Test
	public void testListCategory() {
		assertEquals("Something gone wrong?", 3,categoryDAO.list().size());
	}
	*/
	
}
