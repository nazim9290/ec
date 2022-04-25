package com.ecommerce.entity;

import java.sql.Timestamp;

public class MakerEntity {

	private int maker_id;
	private int maker_name;
	private Timestamp create_time;
	private Timestamp update_time;
	public int getMaker_id() {
		return maker_id;
	}
	public void setMaker_id(int maker_id) {
		this.maker_id = maker_id;
	}
	public int getMaker_name() {
		return maker_name;
	}
	public void setMaker_name(int maker_name) {
		this.maker_name = maker_name;
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
