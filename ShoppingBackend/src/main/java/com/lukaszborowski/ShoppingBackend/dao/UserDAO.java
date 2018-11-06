package com.lukaszborowski.ShoppingBackend.dao;

import com.lukaszborowski.ShoppingBackend.dto.Address;
import com.lukaszborowski.ShoppingBackend.dto.Cart;
import com.lukaszborowski.ShoppingBackend.dto.User;

public interface UserDAO {

	boolean addUser(User user);
	
	boolean addAddress(Address address);
	
	boolean addCart(Cart cart);
}
