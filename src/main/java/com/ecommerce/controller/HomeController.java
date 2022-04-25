package com.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ecommerce.entity.CategoryEntity;
import com.ecommerce.entity.ProductEntity;
import com.ecommerce.service.ProductService;

@Controller
public class HomeController {
	@Autowired
	ProductService productService;

	@GetMapping("")
	public String homePage(Model model) {
		List<CategoryEntity> categoryList = new ArrayList<>();
		categoryList = productService.getCategory();

		List<ProductEntity> productList = new ArrayList<>();
		productList = productService.getallProduct();


		model.addAttribute("productList", productList);

		model.addAttribute("categoryList", categoryList);

		model.addAttribute("title", "Ec サイト");

		productList.forEach((n) -> {
			System.out.println(n);
		});

		return "index";
	}
}
