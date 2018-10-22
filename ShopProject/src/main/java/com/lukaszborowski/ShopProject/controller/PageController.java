package com.lukaszborowski.ShopProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lukaszborowski.ShoppingBackend.dao.CategoryDAO;
import com.lukaszborowski.ShoppingBackend.dao.ProductDAO;
import com.lukaszborowski.ShoppingBackend.dto.Category;
import com.lukaszborowski.ShoppingBackend.dto.Product;

@Controller
public class PageController {

	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private ProductDAO productDAO;
	@RequestMapping(value = {"/", "/home", "/index"})
	public ModelAndView index() {
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","Home");
		mv.addObject("categories", categoryDAO.list());
		mv.addObject("userClickHome", true);
		return mv;
	}
	
	@RequestMapping(value = {"/about"})
	public ModelAndView about() {
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","About us");
		mv.addObject("userClickAboutUs", true);
		return mv;
	}
	
	@RequestMapping(value = {"/contact"})
	public ModelAndView contact() {
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","Contact");
		mv.addObject("userClickContact", true);
		return mv;
	}
	
	@RequestMapping(value = {"/products"})
	public ModelAndView products() {
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","Products");
		mv.addObject("userClickProducts", true);
		return mv;
	}

	/*
	 * Methods to load all the products and based on category
	 */
	
	@RequestMapping(value = {"/show/all/products"})
	public ModelAndView showAllProducts() {
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","All Products");
		
		//passing the list of categories
		mv.addObject("categories", categoryDAO.list());
		
		mv.addObject("userClickProducts", true);
		return mv;
	}
	
	@RequestMapping(value = {"/show/category/{id}/products"})
	public ModelAndView showCategoryProducts(@PathVariable("id")int id) {
		//categoryDAO to fetch a single category
		Category cat = null;
		cat = categoryDAO.get(id);
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", cat.getName());
		
		//passing the list of categories
		mv.addObject("categories", categoryDAO.list());
		//parsing single category
		mv.addObject("category", cat);
		mv.addObject("userClickCategoryProducts", true);
		return mv;
	}
	
	/*
	 * 
	 * Viewing a single products
	 */
	
	@RequestMapping(value = {"/show/{id}/product"})
	public ModelAndView showSingleProduct(@PathVariable("id")int id) {
		ModelAndView mv = new ModelAndView("page");
		Product product = productDAO.get(id);
		product.setViews(product.getViews() +1);
		
		productDAO.update(product);
		
		
		mv.addObject("title", product.getName());
		mv.addObject("product", product);
		mv.addObject("userClickShowProduct", true);
		
		
		return mv;		
	}
}
