package com.lukaszborowski.ShoppingBackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.lukaszborowski.ShoppingBackend.dao.UserDAO;
import com.lukaszborowski.ShoppingBackend.dto.Address;
import com.lukaszborowski.ShoppingBackend.dto.Cart;
import com.lukaszborowski.ShoppingBackend.dto.User;

public class UserTest {
	
	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private User user = null;
	private Cart cart =null;
	private Address address = null;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.lukaszborowski.ShoppingBackend");
		context.refresh();
		
		userDAO = (UserDAO) context.getBean("userDAO");
	}
	
/*
	@Test
	public void testAdd() {
		
		user = new User();
		user.setFirstName("Maciej");
		user.setLastName("Skiepko");
		user.setEmail("maciej@o2.pl");
		user.setContactNumber("123456789");
		user.setRole("USER");
		user.setPassword("password123");
		
		assertEquals("Falied to add user", true, userDAO.addUser(user));
		
		address = new Address();
		address.setStreet("ul.Mikołaja 54");
		address.setCity("Narewka");
		address.setState("podlaskie");
		address.setCountry("Poland");
		address.setPostalCode("15-123");
		address.setBilling(true);
		
		
		//link the user with the address using user id
		address.setUserId(user.getId());

		assertEquals("Falied to add address", true, userDAO.addAddress(address));
		
		if(user.getRole().equals("USER")) {
			//create a cart for this user 
			
			cart = new Cart();
			cart.setUser(user);
			
			//add a shiping address for this user
			assertEquals("Falied to add cart", true, userDAO.addCart(cart));
			
			//add a shipping address for this user
			address = new Address();
			address.setStreet("ul.Piłsudskiego 102");
			address.setCity("Narew");
			address.setState("podlaskie");
			address.setCountry("Poland");
			address.setPostalCode("15-123");
			//set shipping to true
			address.setShipping(true);
			
			//link it with the user 
			address.setUserId(user.getId());
			

			//add the shipping adress
			assertEquals("Falied to add shipping address", true, userDAO.addAddress(address));
			
		}
	}
*/
	/*
	@Test
	public void testAdd2() {
		
		user = new User();
		user.setFirstName("Maciej");
		user.setLastName("Skiepko");
		user.setEmail("maciej@o2.pl");
		user.setContactNumber("123456789");
		user.setRole("USER");
		user.setPassword("password123");
		
		
		if(user.getRole().equals("USER")) {
			//create a cart for this user 			
			cart = new Cart();
			cart.setUser(user);			
			user.setCart(cart);
		}

		assertEquals("Falied to add user", true, userDAO.addUser(user));
	}
	
	@Test
	public void testUpdateCart() {
		user = userDAO.getByEmail("maciej@o2.pl");
		
		//get the cart of the user
		cart = user.getCart();
		
		cart.setGrandTotal(55555);
		cart.setCartLines(2);
		assertEquals("Falied to update cart", true, userDAO.updateCart(cart));
	}
	*/
	
	@Test
	public void testCAseAddAddress() {
		//we need to add an user because we creating new table in start this test
		user = new User();
		user.setFirstName("Maciej");
		user.setLastName("Skiepko");
		user.setEmail("maciej@o2.pl");
		user.setContactNumber("123456789");
		user.setRole("USER");
		user.setPassword("password123");
		
		assertEquals("Falied to add the user", true, userDAO.addUser(user));
		// add the address
		address = new Address();
		address.setStreet("ul.Mikołaja 54");
		address.setCity("Narewka");
		address.setState("podlaskie");
		address.setCountry("Poland");
		address.setPostalCode("15-123");
		address.setBilling(true);
		
		assertEquals("Faliedto add billing address", true, userDAO.addAddress(address));
		//add the shipping address
		address = new Address();
		address.setStreet("ul.Piłsudskiego 102");
		address.setCity("Narew");
		address.setState("podlaskie");
		address.setCountry("Poland");
		address.setPostalCode("15-123");
		//set shipping to true
		address.setShipping(true);

		assertEquals("Faliedto add shipping address", true, userDAO.addAddress(address));
	}
}
