package com.ecommerce.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ecommerce.service.CartService;
import com.ecommerce.service.CategoryService;
import com.ecommerce.service.MakerService;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.UserService;

@ControllerAdvice
public class GeneralController {

	@Autowired
	ProductService productService;

	@Autowired
	CartService cartService;

	@Autowired
	MakerService makerService;

	@Autowired
	UserService userService;

	@Autowired
	CategoryService categoryService;

	@SuppressWarnings("unused")

	@ModelAttribute
	public void populateModel(Model model, HttpServletRequest request) {


		model.addAttribute("categoryList", categoryService.getCategory());

	}

}
