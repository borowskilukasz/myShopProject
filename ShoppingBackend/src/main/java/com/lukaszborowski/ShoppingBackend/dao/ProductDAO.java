package com.lukaszborowski.ShoppingBackend.dao;

import java.util.List;

import com.lukaszborowski.ShoppingBackend.dto.Product;

/**
 * Interface with data access object of product
 * @author Lukasz
 *
 */
public interface ProductDAO {

	/**
	 * 
	 * @param productId id of product to get
	 * @return product object with product id passed to this method
	 */
	Product get(int productId);
	
	/**
	 * When we get list of products with this method the result will be a list with all available products in database 
	 * @return list of all products 
	 */
	List<Product> list();
	/**
	 * 
	 * @param product product to add
	 * @return {@code true} if product added successfully
	 */
	boolean add(Product product);
	
	/**
	 * 
	 * @param product product to update
	 * @return {@code true } if product is updated successfully
	 */
	boolean update(Product product);
	
	/**
	 * 
	 * @param product to delete 
	 * @return {@code true} if product is deleted successfully
	 */
	boolean delete(Product product);
	
	/**
	 * This method returns list of products which has boolean variable active set to true
	 * @return list of available products
	 */
	List<Product> listActiveProducts();
	/**
	 * 
	 * @param categoryId id of category products  to get
	 * @return products with particular category id 
	 */
	List<Product> listActiveProductsByCategory(int categoryId);
	
	/**
	 * 
	 * @param count number of products to get
	 * @return concrete number of products
	 */
	List<Product> getLatestActiveProducts(int count);
	
	
}
