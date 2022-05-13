package com.ecommerce.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ecommerce.dto.CartDTO;
import com.ecommerce.entity.OrderEntity;
import com.ecommerce.entity.OrderItemEntity;
import com.ecommerce.entity.ProductEntity;
import com.ecommerce.entity.UserEntity;
import com.ecommerce.service.CartService;
import com.ecommerce.service.OrderService;
import com.ecommerce.service.ProductService;

@Controller
public class OrderController {

	@Autowired
	HttpServletRequest request;
	@Autowired
	CartService cartService;
	@Autowired
	ProductService productService;
	@Autowired
	OrderService orderService;


	@GetMapping("/checkout")
	public String checkout(Model model) {
		model.addAttribute("order", new OrderEntity());
		UserEntity loginUser = (UserEntity) request.getSession(true).getAttribute("loginUser");

		model.addAttribute("cartCount", cartService.cartCount(loginUser.getUser_id()));
		model.addAttribute("total",
				cartService.getcart(loginUser.getUser_id()).stream().mapToDouble(CartDTO::getTotal).sum());

		return "user/checkout";
	}

	@PostMapping("/payNow")
	public String order(Model model, @ModelAttribute("order") OrderEntity orders) {
		UserEntity loginUser = (UserEntity) request.getSession(true).getAttribute("loginUser");
		List<CartDTO> cartItem = cartService.getcart(loginUser.getUser_id());
		
		OrderEntity order = new OrderEntity();
		order.setUser_id(loginUser.getUser_id());
		order.setStatus("pending");
		order.setName(orders.getName());
		order.setAddress(orders.getAddress());
		order.setMobailNo(orders.getMobailNo());
		order.setEmail(orders.getEmail());
		
		orderService.saveOrder(order);

		int orderid = order.getOrder_id();
		this.saveOrderProcess(cartItem, loginUser.getUser_id(), orderid);

		return "redirect:/product";
	}

	public void saveOrderProcess(List<CartDTO> cartItem, int user_id, int orderid) {
		

		int count = 0;
		while (count < cartItem.size()) {
			// product stock update
			ProductEntity product = productService.getProductById(cartItem.get(count).getProduct_id());
			int newProductStock = product.getStock() - cartItem.get(count).getQuantity();
			product.setStock(newProductStock);
			productService.updateProduct(product);

			// product save into order db
			OrderItemEntity item = new OrderItemEntity();
			item.setOrder_id(orderid);
			item.setUser_id(user_id);
			item.setProduct_id(product.getProduct_id());
			item.setPrice(product.getPrice());
			item.setQuantity(cartItem.get(count).getQuantity());

			orderService.insertOrderItem(item);
			count++;
		}

		cartService.deleteUserCar(user_id);

	}



}
