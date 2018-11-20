package com.lukaszborowski.ShoppingBackend.dao;

import java.util.List;

import com.lukaszborowski.ShoppingBackend.dto.Address;
import com.lukaszborowski.ShoppingBackend.dto.User;

/**
 * Interface of user 
 * @author Lukasz Borowski
 *
 */
public interface UserDAO {

	/**
	 * 
	 * @param user user to add
	 * @return {@code true} if user added successfully
	 */
	boolean addUser(User user);
	
	/**
	 * 
	 * @param email email of user to get
	 * @return user with particular email 
	 */
	User getByEmail(String email);
	
	/**
	 * 
	 * @param address address to add
	 * @return {@code true} if address is added successfully
	 */
	boolean addAddress(Address address);
	
	/**
	 * 
	 * @param user object of user which address we want to get
	 * @return billing address of particular user
	 */
	Address getBillingAddress(User user);
	
	/**
	 * User can have more than one shipping address, so method returns a list of addresses
	 * @param user object of user which addresses we want to get
	 * @return list of shipping addresses
	 */
	List<Address> listShippingAddress(User user);
	
}
