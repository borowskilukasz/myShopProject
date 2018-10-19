package com.lukaszborowski.ShoppingBackend.dao;

import java.util.List;

import com.lukaszborowski.ShoppingBackend.dto.Category;

public interface CategoryDAO {

	List<Category> list();
	Category get(int id);
}
