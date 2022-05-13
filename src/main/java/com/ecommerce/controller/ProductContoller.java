package com.ecommerce.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.entity.ProductCategoryMakerEntity;
import com.ecommerce.entity.ProductEntity;
import com.ecommerce.entity.UserEntity;
import com.ecommerce.form.ProductForm;
import com.ecommerce.service.CartService;
import com.ecommerce.service.CategoryService;
import com.ecommerce.service.MakerService;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.UserService;

@Controller
@RequestMapping("product")
public class ProductContoller {

	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";

	@Autowired
	HttpServletRequest request;
	@Autowired
	UserService userService;
	@Autowired
	CartService cartService;
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	MakerService makerService;

	@GetMapping("")
	public String productList(Model model, HttpSession session) {
		UserEntity loginUser = (UserEntity) request.getSession(true).getAttribute("loginUser");
			List<ProductCategoryMakerEntity> productList = new ArrayList<>();
			productList = productService.getAllProduct();
			model.addAttribute("productList", productList);
			model.addAttribute("title", "商品");
			session.setAttribute("cartCount", cartService.cartCount(loginUser.getUser_id()));


		return "product/product";
	}

	@GetMapping("/category/{id}")
	public String shopByCat(@PathVariable int id, Model model) {
		model.addAttribute("productList", productService.getAllProductByCategoryId(id));
		model.addAttribute("title", "商品");
		return "product/product";
	}

	@GetMapping("/register")
	public String productRegister(Model model) {
		model.addAttribute("product", new ProductForm());
		model.addAttribute("makerList", makerService.getmaker());
		model.addAttribute("title", "商品登録");
		return "product/register";
	}

	@PostMapping("/add")
	public String postProAdd(@ModelAttribute("product") ProductForm productForm,
			@RequestParam("productImage") MultipartFile fileProductImage, @RequestParam("imgName") String imgName)
			throws IOException {

		System.out.println("converted");
		// convert form > entity
		ProductEntity product = new ProductEntity();
		product.setProduct_name(productForm.getProduct_name());
		product.setProduct_description(productForm.getProduct_description());
		product.setPrice(productForm.getPrice());
		product.setStock(productForm.getStock());
		product.setCategory_id(productForm.getCategory_id());
		product.setMaker_id(productForm.getMaker_id());

		String imageUUID;
		if (!fileProductImage.isEmpty()) {
			imageUUID = fileProductImage.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
			Files.write(fileNameAndPath, fileProductImage.getBytes());
		} else {
			imageUUID = imgName;
		} // save image
		product.setImage(imageUUID);

		productService.saveProduct(product);
		return "redirect:/product";
	}

	@GetMapping("/details/{pdId}")
	public String userUpdate(@PathVariable("pdId") int pdId, Model model) {
		ProductCategoryMakerEntity product = productService.getProductCategoryMakerById(pdId);
		model.addAttribute("product", product);
		model.addAttribute("title", product.getProduct_name());

		return "product/productDetails";
	}
}
