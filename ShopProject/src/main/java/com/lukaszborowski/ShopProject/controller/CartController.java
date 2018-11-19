package com.lukaszborowski.ShopProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lukaszborowski.ShopProject.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	CartService cartService;

	@RequestMapping("/show")
	public ModelAndView showCart(@RequestParam(name="result", required=false)String result) {
		ModelAndView mv = new ModelAndView("page");
		
		if(result != null) {
			switch(result) {
			
			case "updated":
				mv.addObject("message", "CartLine has been updated successfully!");
				break;
			case "error":
				mv.addObject("message", "Something went wrong!");
				break;
			case "deleted":
				mv.addObject("message", "Cartline has been removed successfully!!");
				break;
			case "added":
				mv.addObject("message", "Cartline has been added successfully!!");
				break;
			case "unavailable":
				mv.addObject("message", "No more this product in shop!!");
				break;
			case "maximum":
				mv.addObject("message", "Cartline has reached maximum size!");
				break;
			}
		}
		mv.addObject("title", "User Cart");
		mv.addObject("userClickShowCart", true);
		//passing cart lines
		mv.addObject("cartLines", cartService.getCartLines());
		
		return mv;
	}
	
	@RequestMapping("/{cartLineId}/update")
	public String updateCart(@PathVariable int cartLineId, @RequestParam int count) {
		String response = cartService.manageCartLine(cartLineId, count);
		
		return"redirect:/cart/show?"+response;
	}
	
	@RequestMapping("/{cartLineId}/delete")
	public String deleteCart(@PathVariable int cartLineId) {
		String response = cartService.deleteCartLine(cartLineId);
		
		return"redirect:/cart/show?"+response;
	}	
	
	@RequestMapping("/add/{productId}/product")
	public String addCart(@PathVariable int productId) {
		String response = cartService.addCartLine(productId);
		
		return"redirect:/cart/show?"+response;
	}	
	
}
