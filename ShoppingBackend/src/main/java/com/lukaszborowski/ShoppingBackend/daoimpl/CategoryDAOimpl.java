package com.lukaszborowski.ShoppingBackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lukaszborowski.ShoppingBackend.dao.CategoryDAO;
import com.lukaszborowski.ShoppingBackend.dto.Category;


@Repository("CategoryDAO")
public class CategoryDAOimpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;
	private static List<Category> categories = new ArrayList<>();
	
	static {
		Category category= new Category();
		
		category.setId(1);
		category.setName("Television");
		category.setDescryption("Description for television!");
		category.setImageURL("CAT_1.png");
		categories.add(category);
		
category= new Category();
		
		category.setId(2);
		category.setName("Phone");
		category.setDescryption("Description for phones!");
		category.setImageURL("CAT_2.png");
		categories.add(category);
		
category= new Category();
		
		category.setId(3);
		category.setName("Laptop");
		category.setDescryption("Description for laptops!");
		category.setImageURL("CAT_3.png");
		categories.add(category);
		
	}
	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		return categories;
	}
	@Override
	public Category get(int id) {
		for(Category category : categories) {
			if(category.getId() == id) {
				return category;
			}
		}
		return null;
	}
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

}
