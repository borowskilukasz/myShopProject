package com.lukaszborowski.ShopProject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lukaszborowski.ShopProject.util.FileUploadUtility;
import com.lukaszborowski.ShopProject.validator.ProductValidator;
import com.lukaszborowski.ShoppingBackend.dao.CategoryDAO;
import com.lukaszborowski.ShoppingBackend.dao.ProductDAO;
import com.lukaszborowski.ShoppingBackend.dto.Category;
import com.lukaszborowski.ShoppingBackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {
	
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private ProductDAO productDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);
	@GetMapping(value="/products")
	public ModelAndView showManageProducts(@RequestParam(name="operation", required=false)String operation) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickManageProduct", true);
		mv.addObject("title", "Manage Products");
		
		Product product = new Product();
		product.setActive(true);
		product.setSupplierId(1);
		
		mv.addObject("product", product);
		
		if(operation != null) {
			if(operation.equals("product")) {
				mv.addObject("message", "Product added Successfully!");
			}else if(operation.equals("category")) {
				mv.addObject("message", "Category added Successfully!");
				
			}
		}
		return mv;
	}
	
	@GetMapping(value="/{id}/product")
	public ModelAndView showEditProduct(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickManageProduct", true);
		mv.addObject("title", "Manage Products");
		
		//fetch product from database
		Product product = productDAO.get(id);
		//set the product fetch from database
		mv.addObject("product", product);
	
		return mv;
	}

	
	@PostMapping(value="/products")
	public String handleProductSubmission(@Valid @ModelAttribute("product")Product mProduct, BindingResult result, Model model, 
			HttpServletRequest request) {
		
		if(mProduct.getId() == 0 ) {
			new ProductValidator().validate(mProduct, result);
		}else {
			if(!mProduct.getFile().getOriginalFilename().equals("")) {
				new ProductValidator().validate(mProduct, result);
			}
		}
		
		if(result.hasErrors()) {			
			model.addAttribute("userClickManageProduct", true);
			model.addAttribute("title", "Manage Products");		
			model.addAttribute("message", "There was a problem with adding product!");		
			return "page";
		}
		
		logger.info(mProduct.toString());

		mProduct.setActive(true);
		if(mProduct.getId() == 0 ) {
			productDAO.add(mProduct);
		}else {

			productDAO.update(mProduct);
		}
		
		if(!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
		}
		return "redirect:/manage/products?operation=product";
	}
	
	@PostMapping(value="/product/{id}/activation")
	@ResponseBody
	public String handleProductActivation(@PathVariable int id) {
		Product product = productDAO.get(id);
		
		boolean isActive = product.getIsActive();
		
		product.setActive(!product.getIsActive());
		productDAO.update(product);
		
		return (isActive)? 
				"You have successfully deactivated product with id " + product.getId() :
					"You have successfully activated product with id " + product.getId() ;
	}
	
	//to handle category submission
	@PostMapping(value="/category")
	public String handleCategorySubmission(@ModelAttribute Category category) {
		categoryDAO.add(category);
		return "redirect:/manage/products?operation=category";
		
	}
	
	
	@ModelAttribute("categories")
	public List<Category> getCategories(){
		return categoryDAO.list();
	}
	
	@ModelAttribute("category")
	public Category getCategory() {
		return new Category();
	}
}
