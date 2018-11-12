package com.lukaszborowski.ShoppingBackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.lukaszborowski.ShoppingBackend.dao.CartLineDAO;
import com.lukaszborowski.ShoppingBackend.dao.ProductDAO;
import com.lukaszborowski.ShoppingBackend.dao.UserDAO;
import com.lukaszborowski.ShoppingBackend.dto.Cart;
import com.lukaszborowski.ShoppingBackend.dto.CartLine;
import com.lukaszborowski.ShoppingBackend.dto.Product;
import com.lukaszborowski.ShoppingBackend.dto.User;

public class CartLineTest {
	
	private static AnnotationConfigApplicationContext context;
	
	private static CartLineDAO cartLineDAO = null;
	private static ProductDAO productDAO= null;
	private static UserDAO userDAO = null;
	
	private Product product = null;
	private User user = null;
	private Cart cart = null;
	private CartLine cartLine = null;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.lukaszborowski.ShoppingBackend");
		context.refresh();
		
		productDAO = (ProductDAO) context.getBean("ProductDAO");
		userDAO = (UserDAO) context.getBean("userDAO");
		cartLineDAO = (CartLineDAO) context.getBean("cartLineDAO");
	}

	
	@Test
	public void testAddNewCartLine() {
		//get the user 
		user = userDAO.getByEmail("lukasz.b12@wp.pl");
		cart = user.getCart();
		product = productDAO.get(1);
		cartLine = new CartLine();
		cartLine.setBuyingPrice(product.getUnitPrice());
		cartLine.setProductCount(cartLine.getProductCount() + 1 );
		cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());
		
		cartLine.setAvailable(true);
		
		cartLine.setCartId(cart.getId());
		cartLine.setProduct(product);
		
		assertEquals("Falied to add cartline", true, cartLineDAO.add(cartLine));
		
		//update cart
		cart.setGrandTotal(cart.getGrandTotal() +cartLine.getTotal());
		cart.setCartLines(cart.getCartLines() + 1 );

		assertEquals("Falied to update cart", true, cartLineDAO.updateCart(cart));
	}
	
	
	
	
	
	
	
	
	
}
