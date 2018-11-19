package com.lukaszborowski.ShopProject.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lukaszborowski.ShopProject.model.UserModel;
import com.lukaszborowski.ShoppingBackend.dao.CartLineDAO;
import com.lukaszborowski.ShoppingBackend.dao.ProductDAO;
import com.lukaszborowski.ShoppingBackend.dto.Cart;
import com.lukaszborowski.ShoppingBackend.dto.CartLine;
import com.lukaszborowski.ShoppingBackend.dto.Product;

@Service("cartService")
public class CartService {

	@Autowired
	private CartLineDAO cartLineDAO;
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired 
	private HttpSession session;
	/**
	 * 
	 * @return cart of the user who has logged in
	 */
	private Cart getCart() {
		return ((UserModel)session.getAttribute("userModel")).getCart();		
	}
	
	/**
	 * 
	 * @return Entire cart Lines
	 */
	public List<CartLine> getCartLines() {
		Cart cart = this.getCart();
		return cartLineDAO.list(cart.getId());
	}

	public String manageCartLine(int cartLineId, int count) {
		CartLine cartLine =cartLineDAO.get(cartLineId);
		if(cartLine == null) {
			
			return "result=error";
		}else {
			Product product = cartLine.getProduct();
			double oldTotal = cartLine.getTotal();
			if(product.getQuantity() < count) {
				return "result=unavailable";
			}
			
			cartLine.setProductCount(count);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setTotal(product.getUnitPrice()* count);
			
			cartLineDAO.update(cartLine); 
			
			Cart cart = this.getCart();
			
			cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartLine.getTotal());
			cartLineDAO.updateCart(cart);

			return "result=updated";
		}
	}

	public String deleteCartLine(int cartLineId) {
		CartLine cartLine = cartLineDAO.get(cartLineId);
		if(cartLine == null) {
			return "result=error";
		}else {
			Cart cart = this.getCart();
			cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
			cart.setCartLines(cart.getCartLines() -1);
			cartLineDAO.updateCart(cart);
			
			cartLineDAO.delete(cartLine);
			return "result=deleted";
			
		}
	}

	public String addCartLine(int productId) {
		
		String response = null;
		Cart cart = this.getCart();
		CartLine cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), productId);
		if(cartLine == null) {
			cartLine = new CartLine();
			
			Product product = productDAO.get(productId);
			cartLine.setCartId(cart.getId());
			
			cartLine.setProduct(product);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setProductCount(1);			
			cartLine.setTotal(product.getUnitPrice());
			cartLine.setAvailable(true);
			
			cartLineDAO.add(cartLine);
			
			cart.setCartLines(cart.getCartLines()+1);
			cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
			
			cartLineDAO.updateCart(cart);
			
			response = "result=added";
		}else {
			//check if the cart line has reached the maximum count
			response = this.manageCartLine(cartLine.getId(), cartLine.getProductCount() + 1);
		if(cartLine.getProductCount() < 10 ) {
			//update product count for that cartline
		}else {
			response = "result=maximum";
		}
		
		}
		
		return response;
	}
	
	
	
	
	
	
}
