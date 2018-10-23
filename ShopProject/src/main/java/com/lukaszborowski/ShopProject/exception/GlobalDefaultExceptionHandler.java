package com.lukaszborowski.ShopProject.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handlerNoHandlerFoundException() {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "The Page is not constructed!");
		mv.addObject("errorDescription", "The page you are looking for is not available now!");
		mv.addObject("title", "Error 404 - Page not found!");
		return mv;
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handlerProductNotFoundException() {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "Product not available!");
		mv.addObject("errorDescription", "Product you looking for is not available now!");
		mv.addObject("title", "Error 500 - Product not found!");
		return mv;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(Exception ex) {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "Contact your administrator!");
		mv.addObject("errorDescription", ex.toString());
		mv.addObject("title", "Error");
		return mv;
	}
	
}
