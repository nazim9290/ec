package com.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.entity.CategoryEntity;
import com.ecommerce.entity.ProductEntity;
import com.ecommerce.service.ProductService;

@Controller
@RequestMapping("product")
public class ProductContoller {

	@Autowired
	ProductService productService;

	@GetMapping("")
	public String productList(Model model) {
		List<CategoryEntity> categoryList = new ArrayList<>();
		categoryList = productService.getCategory();
		model.addAttribute("categoryList", categoryList);

		List<ProductEntity> productList = new ArrayList<>();
		productList = productService.getallProduct();

		model.addAttribute("productList", productList);


		model.addAttribute("title", "商品");
		return "product/product";
	}

	@GetMapping("/register")
	public String productRegister(Model model) {
		model.addAttribute("title", "商品登録");
		return "product/register";
	}

	@GetMapping("/details/{pdId}")
	public String userUpdate(@PathVariable("pdId") int pdId, Model model) {
		ProductEntity product = productService.productDetails(pdId);
		System.out.println(product);

		model.addAttribute("product", product);
		model.addAttribute("title", product.getProduct_name());

		return "product/productDetails";
	}
}
