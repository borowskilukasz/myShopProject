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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
			}
		}
		return mv;
	}

	
	@PostMapping(value="/products")
	public String handleProductSubmission(@Valid @ModelAttribute("product")Product mProduct, BindingResult result, Model model, 
			HttpServletRequest request) {
		
		new ProductValidator().validate(mProduct, result);
		
		if(result.hasErrors()) {			
			model.addAttribute("userClickManageProduct", true);
			model.addAttribute("title", "Manage Products");		
			model.addAttribute("message", "There was a problem with adding product!");		
			return "page";
		}
		
		logger.info(mProduct.toString());
		//create new product record
		mProduct.setActive(true);
		productDAO.add(mProduct);
		
		if(!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
		}
		return "redirect:/manage/products?operation=product";
	}
	
	
	@ModelAttribute("categories")
	public List<Category> getCategories(){
		return categoryDAO.list();
	}
}
