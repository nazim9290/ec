package com.ecommerce.entity;

import java.sql.Timestamp;

public class OrderEntity {
	private int order_id;
	private int user_id;
	private String status;
	private String name;
	private String address;
	private String mobailNo;
	private String email;
	private Timestamp create_time;
	private Timestamp update_time;

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobailNo() {
		return mobailNo;
	}

	public void setMobailNo(String mobailNo) {
		this.mobailNo = mobailNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	public Timestamp getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}


}
