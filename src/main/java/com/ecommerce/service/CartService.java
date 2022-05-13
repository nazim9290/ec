package com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.CartDTO;
import com.ecommerce.entity.CartEntity;
import com.ecommerce.entity.ProductEntity;
import com.ecommerce.mapper.CartMapper;

@Service
public class CartService {
	@Autowired
	ProductService productService;

	@Autowired
	CartMapper cartMapper;

	// カテゴリ一覧
	public List<CartDTO> getcart(int user_id) {
		return cartMapper.getUserCart(user_id);
	}

	public void deleteUserCar(int user_id) {
		cartMapper.deleteUserCar(user_id);
	}


	public Object cartCount(int user_id) {
		Object count = cartMapper.cartCount(user_id);

		return count;

	}


	public CartEntity getcartById(int id) {

		return cartMapper.getcartById(id);
	}

	public void saveCart(int id, int quantity, int user_id) {

		List<CartEntity> cart = cartMapper.getCartByUserId(user_id);

		ProductEntity p = productService.getProductById(id);

		boolean hasProductInTheCart = false;

		if (cart != null) {

			for (CartEntity item : cart) {
				if (item.getProduct_id() == p.getProduct_id()) {
					hasProductInTheCart = true;
					item.setQuantity(quantity);
					item.setTotal(quantity * p.getPrice());
					System.out.println("multiple =" + quantity * p.getPrice());
					cartMapper.deleteCart(item.getCart_id());
					cartMapper.insertCart(item);
				}
			}

			if (!hasProductInTheCart && (cart != null)) {
				CartEntity cartItem = new CartEntity();
				cartItem.setUser_id(user_id);
				cartItem.setProduct_id(id);
				cartItem.setQuantity(quantity);
				cartItem.setTotal(quantity * p.getPrice());
				System.out.println("multiple =" + quantity * p.getPrice());
				cartMapper.insertCart(cartItem);

			}

		}

	}

	public CartEntity updateCart(int product_id, int quantity, int user_id) {

		List<CartEntity> cart = cartMapper.getCartByUserId(user_id);
		ProductEntity p = productService.getProductById(product_id);

		for (CartEntity item : cart) {
			if (item.getProduct_id() == product_id) {
				item.setQuantity(quantity);
				item.setTotal(quantity * p.getPrice());
				System.out.println("multiple =" + quantity * p.getPrice());
				cartMapper.deleteCart(item.getCart_id());
				cartMapper.insertCart(item);
				return item;
			}
		}
		return null;

	}

	public void deletecart(int id) {
		cartMapper.deleteCart(id);
	}

}
