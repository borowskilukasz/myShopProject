package com.lukaszborowski.ShoppingBackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lukaszborowski.ShoppingBackend.dao.CartLineDAO;
import com.lukaszborowski.ShoppingBackend.dto.Cart;
import com.lukaszborowski.ShoppingBackend.dto.CartLine;

/**
 * Implementation of cartLineDAO
 * @author Lukasz Borowski
 *
 */
@Repository("cartLineDAO")
@Transactional
public class CartLineDAOImpl implements CartLineDAO {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * This method gets the cartline with the aid of id 
	 * @param id id of cartline to get
	 * @return cartline object
	 */
	@Override
	public CartLine get(int id) {
		return sessionFactory.getCurrentSession().get(CartLine.class, id);
	}
	
	/**
	 * 
	 * @param cartLine the cartline to add
	 * @return {@code true} if cartline is added successfully
	 */
	@Override
	public boolean add(CartLine cartLine) {
		try {
			sessionFactory.getCurrentSession().persist(cartLine);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 
	 * @param cartLine the cartline to update
	 * @return {@code true} if the cartline is updated successfully
	 */
	@Override
	public boolean update(CartLine cartLine) {
		try {
			sessionFactory.getCurrentSession().update(cartLine);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * 
	 * @param cartLine the cartline to delete
	 * @return {@code true} if the cartline is deleted successfully
 	 */
	@Override
	public boolean delete(CartLine cartLine) {
		try {
			sessionFactory.getCurrentSession().delete(cartLine);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * 
	 * @param cartId id of the cart to look for
	 * @return list of cartlines of particular cart
	 */
	@Override
	public List<CartLine> list(int cartId) {
		String query = "FROM CartLine WHERE cartId = :cartId";
		return sessionFactory.getCurrentSession()
				.createQuery(query, CartLine.class)
				.setParameter("cartId", cartId)
				.getResultList();
	}

	/**
	 * 
	 * @param catId id of the cart to look for
	 * @return list of available cartlines 
	 */
	@Override
	public List<CartLine> listAvaliable(int cartId) {
		String query = "FROM CartLine WHERE cartId = :cartId AND available = :available";
		return sessionFactory.getCurrentSession()
				.createQuery(query, CartLine.class)
				.setParameter("cartId", cartId)
				.setParameter("available", true)
				.getResultList();
	}

	/**
	 * 
	 * @param cartId id of the cart to look for
	 * @param productId id of the product to look for
	 * @return cartline with particular cartId and productId
	 */
	@Override
	public CartLine getByCartAndProduct(int cartId, int productId) {
		String query = "FROM CartLine WHERE cartId = :cartId AND product.id = :productId";
		try {
		return sessionFactory.getCurrentSession()
				.createQuery(query, CartLine.class)
				.setParameter("cartId", cartId)
				.setParameter("productId", productId)
				.getSingleResult();
		}catch(Exception ex) {
			return null;
		}
	}

	/**
	 * 
	 * @param cart cart to update
	 * @return {@code true} if cart updated successfully
	 * otherwise returns {@code false} 
	 */
	@Override
	public boolean updateCart(Cart cart) {
		try {
			sessionFactory.getCurrentSession().update(cart);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
