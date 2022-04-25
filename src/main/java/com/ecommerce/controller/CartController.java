package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.service.ProductService;

@Controller
@RequestMapping("cart")
public class CartController {
	@Autowired
	ProductService productService;

	@GetMapping("/{pdId}")
	public void addToCart(@PathVariable("pdId") int pdId, Model model) {


	}

}
