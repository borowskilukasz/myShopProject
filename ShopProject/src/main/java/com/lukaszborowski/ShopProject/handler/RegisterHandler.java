package com.lukaszborowski.ShopProject.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

import com.lukaszborowski.ShopProject.model.RegisterModel;
import com.lukaszborowski.ShoppingBackend.dao.UserDAO;
import com.lukaszborowski.ShoppingBackend.dto.Address;
import com.lukaszborowski.ShoppingBackend.dto.Cart;
import com.lukaszborowski.ShoppingBackend.dto.User;

@Component
public class RegisterHandler {
	@Autowired 
	UserDAO userDAO;

	public RegisterModel init() {
		return new RegisterModel();
	}
	
	public void addUser(RegisterModel registerModel, User user) {		
		registerModel.setUser(user);
	}
	
	public void addBilling(RegisterModel registerModel, Address billing) {		
		registerModel.setBilling(billing);
	}
	
	public String validateUser(User user, MessageContext error) {
		String transitionValue = "success";
		
		//checking is password matches confirm password
		if(! (user.getPassword().equals( user.getConfirmPassword() ) ) ) {
			error.addMessage(new MessageBuilder()
					.error()
					.source("confirmPassword")
					.defaultText("Password are not the same.")
					.build()
					);
			transitionValue = "failure";
		}
		//check the uniqueness of the email id
		
		if( userDAO.getByEmail( user.getEmail() )!=null ) {
			error.addMessage(new MessageBuilder()
					.error()
					.source("email")
					.defaultText("Email address is alredy used.")
					.build()
					);
			transitionValue = "failure";			
		}
		return transitionValue;
	}

	public String saveAll(RegisterModel model) {
		
		String transitionValue = "success";
		
		User user = model.getUser();
		if(user.getRole().equals("USER")); {
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
			
		}
		
		//save the user
		userDAO.addUser(user);
		
		//get the address
		Address billing = model.getBilling();
		billing.setUserId(user.getId());
		billing.setBilling(true);
		
		//save the address
		userDAO.addAddress(billing);
		
		return transitionValue;
	}
}
