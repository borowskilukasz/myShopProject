package com.lukaszborowski.ShoppingBackend.dao;

import java.util.List;

import com.lukaszborowski.ShoppingBackend.dto.Cart;
import com.lukaszborowski.ShoppingBackend.dto.CartLine;

/**
 * Interface with data access objects 
 * @author Łukasz Borowski
 *
 */
public interface CartLineDAO {

	/**
	 * This method gets the cartline with the aid of id 
	 * @param id id of cartline to get
	 * @return cartline object
	 */
	public CartLine get(int id);
	
	/**
	 * 
	 * @param cartLine the cartline to add
	 * @return {@code true} if cartline is added successfully
	 */
	public boolean add(CartLine cartLine);
	/**
	 * 
	 * @param cartLine the cartline to update
	 * @return {@code true} if the cartline is updated successfully
	 */
	public boolean update(CartLine cartLine);
	/**
	 * 
	 * @param cartLine the cartline to delete
	 * @return {@code true} if the cartline is deleted successfully
 	 */
	public boolean delete(CartLine cartLine);
	/**
	 * 
	 * @param cartId id of the cart to look for
	 * @return list of cartlines of particular cart
	 */
	public List<CartLine> list(int cartId);
	
	/**
	 * 
	 * @param catId id of the cart to look for
	 * @return list of available cartlines 
	 */
	public List<CartLine> listAvaliable(int catId);
	/**
	 * 
	 * @param cartId id of the cart to look for
	 * @param productId id of the product to look for
	 * @return cartline with particular cartId and productId
	 */
	public CartLine getByCartAndProduct(int cartId, int productId);
	
	/**
	 * 
	 * @param cart cart to update
	 * @return {@code true} if cart updated successfully
	 * otherwise returns {@code false} 
	 */
	boolean updateCart(Cart cart);
	
}
