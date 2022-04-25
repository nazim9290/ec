package com.ecommerce.form;

import java.sql.Timestamp;

public class UserForm {
	private int user_id;
	private String user_name;
	private String user_email;
	private String user_password;
	private String user_image;
	private boolean user_role;
	private String mobail;
	private String address;
	private Timestamp create_time;
	private Timestamp update_time;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_image() {
		return user_image;
	}

	public void setUser_image(String user_image) {
		this.user_image = user_image;
	}

	public boolean isUser_role() {
		return user_role;
	}

	public void setUser_role(boolean user_role) {
		this.user_role = user_role;
	}

	public String getMobail() {
		return mobail;
	}

	public void setMobail(String mobail) {
		this.mobail = mobail;
	}

	public String getAddress() {
		return address;
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

}
