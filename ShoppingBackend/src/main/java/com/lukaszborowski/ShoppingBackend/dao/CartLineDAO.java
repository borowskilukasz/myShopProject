package com.lukaszborowski.ShoppingBackend.dao;

import java.util.List;

import com.lukaszborowski.ShoppingBackend.dto.Cart;
import com.lukaszborowski.ShoppingBackend.dto.CartLine;

public interface CartLineDAO {

	public CartLine get(int id);
	public boolean add(CartLine cartLine);
	public boolean update(CartLine cartLine);
	public boolean delete(CartLine cartLine);
	public List<CartLine> list(int cartId);
	
	
	public List<CartLine> listAvaliable(int catId);
	public CartLine getByCartAndProduct(int cartId, int productId);
	
	boolean updateCart(Cart cart);
	
}
