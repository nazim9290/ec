package com.ecommerce.dto;

import lombok.Data;

@Data
public class CartDTO {

	private int cart_id;
	private int quantity;
	private int total;
	private int user_id;
	private String user_name;
	private String user_email;
	private String user_password;
	private String user_image;
	private String user_role;
	private String mobail;
	private String address;
	private int product_id;
	private String product_name;
	private String product_description;
	private int price;
	private int stock;
	private String image;
	private String category_name;
	private String maker_name;

}
