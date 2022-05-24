package com.ecommerce.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

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

import com.ecommerce.entity.CategoryEntity;
import com.ecommerce.entity.MakerEntity;
import com.ecommerce.entity.ProductEntity;
import com.ecommerce.service.CategoryService;
import com.ecommerce.service.MakerService;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.UserService;

@Controller
@RequestMapping("admin")
public class AdminController {
	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
	@Autowired
	ProductService productService;
	@Autowired
	UserService userService;
	@Autowired
	CategoryService categoryService;

	@Autowired
	MakerService makerService;

	@GetMapping("")
	public String adminHome(HttpServletRequest request, HttpSession session) {
		return "admin/adminHome";

	}

	// Accounts
	@GetMapping("/users")
	public String getAcc(Model model) {

		model.addAttribute("users", userService.getUserList());

		return "admin/users";
	}

	@GetMapping("/users/delete/{id}")
	public String deleteUser(@PathVariable int id) {
		userService.removeUserById(id);
		return "redirect:/admin/users";
	}

	// Categories session
	@GetMapping("/categories")
	public String getCat(Model model) {
		model.addAttribute("categories", categoryService.getCategory());
		return "admin/categories";
	}// view all categories

	@GetMapping("/categories/add")
	public String getCatAdd(Model model) {
		model.addAttribute("category", new CategoryEntity());
		return "admin/categoriesAdd";
	}// form add new category

	@PostMapping("/categories/add")
	public String postCatAdd(@ModelAttribute("category") CategoryEntity categoryEntity) {
		categoryService.saveCategory(categoryEntity);
		return "redirect:/admin/categories";
	}// form add new category > do add

	@GetMapping("/categories/delete/{id}")
	public String deleteCat(@PathVariable int id) {
		categoryService.deleteCategory(id);
		return "redirect:/admin/categories";
	}// delete 1 category

	@GetMapping("/categories/update/{id}")
	public String updateCat(@PathVariable int id, Model model) {
		Optional<CategoryEntity> category = Optional.of(categoryService.getCategoryById(id));
		if (category.isPresent()) {
			model.addAttribute("category", category.get());
			return "admin/categoriesAdd";
		} else {
			return "404";
		}
		}


	// maker session
	@GetMapping("/maker")
	public String getmaker(Model model) {
		model.addAttribute("makers", makerService.getmaker());
		return "admin/maker";
	}// view all makers

	@GetMapping("/makers/add")
	public String getmakerAdd(Model model) {
		model.addAttribute("maker", new MakerEntity());
		return "admin/makerAdd";
	}// form add new maker

	@PostMapping("/makers/add")
	public String postmakerAdd(@ModelAttribute("maker") MakerEntity makerEntity) {
		makerService.savemaker(makerEntity);
		return "redirect:/admin/maker";
	}// form add new maker > do add

	@GetMapping("/makers/delete/{id}")
	public String deletemaker(@PathVariable int id) {
		makerService.deletemaker(id);
		return "redirect:/admin/maker";
	}// delete 1 maker

	@GetMapping("/makers/update/{id}")
	public String updatemaker(@PathVariable int id, Model model) {
		Optional<MakerEntity> maker = Optional.of(makerService.getmakerById(id));
		if (maker.isPresent()) {
			model.addAttribute("maker", maker.get());
			return "admin/makersAdd";
		} else {
			return "404";
		}
	}

	// admin products

	@GetMapping("/products")
	public String getPro(Model model) {
		model.addAttribute("products", productService.getAllProduct());
		return "admin/products";
	}// view all products

	@GetMapping("/products/update/{id}")
	public String updateProducts(@PathVariable int id, Model model) {
		ProductEntity product = productService.getProductById(id);
		model.addAttribute("product", product);
		model.addAttribute("categoryList", categoryService.getCategory());
		model.addAttribute("makerList", makerService.getmaker());
		model.addAttribute("title", product.getProduct_name());
		return "admin/updateProduct";
	}

	@PostMapping("/products/update")
	public String postProAdd(@ModelAttribute("product") ProductEntity product,
			@RequestParam("productImage") MultipartFile fileProductImage, @RequestParam("imgName") String imgName)
			throws IOException {

		// convert form > entity
		ProductEntity products = new ProductEntity();
		products.setProduct_name(product.getProduct_name());
		products.setProduct_description(product.getProduct_description());
		products.setPrice(product.getPrice());
		products.setStock(product.getStock());
		products.setCategory_id(product.getCategory_id());
		products.setMaker_id(product.getMaker_id());

		String imageUUID;
		if (!fileProductImage.isEmpty()) {
			imageUUID = fileProductImage.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
			Files.write(fileNameAndPath, fileProductImage.getBytes());
		} else {
			imageUUID = imgName;
		} // save image
		product.setImage(imageUUID);
		productService.updateProduct(product);

		return "redirect:/product";
	}

	@GetMapping("/products/delete/{id}")
	public String deletePro(@PathVariable int id) {
		productService.deleteProduct(id);
		return "redirect:/admin/products";
	}


}
