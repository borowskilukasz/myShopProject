package com.lukaszborowski.ShopProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

	@RequestMapping(value = {"/", "/home", "/index"})
	public ModelAndView index() {
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","Home");
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

}
