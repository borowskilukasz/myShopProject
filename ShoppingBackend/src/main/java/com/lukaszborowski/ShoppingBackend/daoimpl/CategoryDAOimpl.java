package com.lukaszborowski.ShoppingBackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lukaszborowski.ShoppingBackend.dao.CategoryDAO;
import com.lukaszborowski.ShoppingBackend.dto.Category;

/**
 * This is implementation of categoryDAO interface 
 * @author Lukasz Borowski
 *
 */
@Repository("CategoryDAO")
@Transactional
public class CategoryDAOimpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public boolean add(Category category) {
		try {
			sessionFactory.getCurrentSession().persist(category);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean update(Category category) {
		try {
			sessionFactory.getCurrentSession().update(category);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * In this case category boolean variable is changed to false
	 */
	@Override
	public boolean delete(Category category) {
		category.setActive(false);
		try {
			this.update(category);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> list() {
		String selectActiveCategory = "FROM Category WHERE active =:active";
		
		return sessionFactory.getCurrentSession()
				.createQuery(selectActiveCategory)
				.setParameter("active", true)
				.getResultList();
	}
	
	@Override
	public Category get(int id) {
		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}
	



}
