package com.ecommerce.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.dto.CartDTO;
import com.ecommerce.entity.UserEntity;
import com.ecommerce.service.CartService;
import com.ecommerce.service.ProductService;

@Controller
public class CartController {

	@Autowired
	HttpServletRequest request;
	@Autowired
	CartService cartService;
	@Autowired
	ProductService productService;

	@GetMapping("/cart")
	public String cartGet(Model model) {

		UserEntity loginUser = (UserEntity) request.getSession(true).getAttribute("loginUser");
			List<CartDTO> cartItem = cartService.getcart(loginUser.getUser_id());
			model.addAttribute("cart", cartItem);
			model.addAttribute("total",
					cartService.getcart(loginUser.getUser_id()).stream().mapToDouble(CartDTO::getTotal).sum());

			return "user/updateCartPage";
	}// page cart


	@PostMapping("/addToCart")
	public String addToCart(HttpServletRequest request, HttpSession session, @RequestParam("product_id") int product_id,
			@RequestParam("quantity") int quantity) {

		UserEntity loginUser = (UserEntity) request.getSession(true).getAttribute("loginUser");
		cartService.saveCart(product_id, quantity, loginUser.getUser_id());

		session.setAttribute("cartCount", cartService.cartCount(loginUser.getUser_id()));

		return "redirect:/product";
	}// click add from page viewProduct


	@GetMapping("/cart/removeItem/{id}")
	public String cartItemRemove(HttpServletRequest request, HttpSession session, @PathVariable int id) {
		UserEntity loginUser = (UserEntity) request.getSession(true).getAttribute("loginUser");
		cartService.deletecart(id);
		session.setAttribute("cartCount", cartService.cartCount(loginUser.getUser_id()));

		return "redirect:/cart";
	} // delete 1 product

	// update cart
	@PostMapping("/updateCart")
	public String updateCartItem(HttpServletRequest request, HttpSession session, Model model,
			@RequestParam("product_id") int product_id, @RequestParam("quantity") int quantity) {
		UserEntity loginUser = (UserEntity) request.getSession(true).getAttribute("loginUser");
		cartService.updateCart(product_id, quantity, loginUser.getUser_id());
		session.setAttribute("cartCount", cartService.cartCount(loginUser.getUser_id()));
		model.addAttribute("total",
				cartService.getcart(loginUser.getUser_id()).stream().mapToDouble(CartDTO::getTotal).sum());
		return "redirect:/cart";
	}

	

}
