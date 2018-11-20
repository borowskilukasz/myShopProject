package com.lukaszborowski.ShoppingBackend.dao;

import java.util.List;

import com.lukaszborowski.ShoppingBackend.dto.Category;

public interface CategoryDAO {

	/**
	 * This method is adding category
	 * @param category particular category to add
	 * @return {@code true } if category is added successfully
	 */
	boolean add(Category category);
	
	/**
	 * This method is updating category
	 * @param category particular category to update
	 * @return {@code true } if category is updated successfully
	 */
	boolean update(Category category);
	
	/**
	 * This method is delete category
	 * @param category particular category to delete
	 * @return {@code true } if category is deleted successfully
	 */
	boolean delete(Category category);
	
	/**
	 * 
	 * @return list of categories 
	 */
	List<Category> list();
	
	/**
	 * 
	 * @param id id of concrete category
	 * @return category object
	 */
	Category get(int id);
}
