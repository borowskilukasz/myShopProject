package com.lukaszborowski.ShopProject.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.lukaszborowski.ShopProject.model.UserModel;
import com.lukaszborowski.ShoppingBackend.dao.UserDAO;
import com.lukaszborowski.ShoppingBackend.dto.User;

@ControllerAdvice
public class GlobalController {

	@Autowired
	private HttpSession session;
	@Autowired
	private UserDAO userDAO;
	
	private UserModel userModel= null;
	
	@ModelAttribute("userModel")
	private UserModel getUserModel() {
		if(session.getAttribute("userModel") == null) {
			
			//add the user model
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			
			User user = userDAO.getByEmail(authentication.getName());
			
			//if user is available create new userModel
			if(user!=null) {
				userModel = new UserModel();
				userModel.setId(user.getId());
				userModel.setEmail(user.getEmail());
				userModel.setRole(user.getRole());
				userModel.setFullName(user.getFirstName() + " " + user.getLastName());

				//set the cart only if user is a buyer
				if(userModel.getRole().equals("USER")) {
					userModel.setCart(user.getCart());
				}				
				//set the userModel in the session				
				session.setAttribute("userModel", userModel);
				return userModel;
			}
			
		}
		//if user is not available return userModel from session
		return (UserModel) session.getAttribute("userModel");
	}
}
