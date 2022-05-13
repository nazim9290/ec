package com.ecommerce.entity;

import lombok.Data;

@Data
public class OrderItemEntity {
	private int order_id;
	private int user_id;
	private int product_id;
	private int price;
	private int quantity;

}
