package com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.UserEntity;
import com.ecommerce.form.UserForm;
import com.ecommerce.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	UserMapper userMapper;

	// ユーザー一覧取得
	public List<UserEntity> getUserList() {
		return userMapper.getAll();
	}

	// IDによるユーザー取得
	public UserEntity getUserByID(int id) {
		return userMapper.getUserById(id);
	}

	// IDによるユーザー取得,UserForm戻す
	public UserForm getUserById(int id) {

		UserForm userForm = new UserForm();
		UserEntity user = userMapper.getUserById(id);

		userForm.setUser_id(user.getUser_id());
		userForm.setUser_name(user.getUser_name());
		userForm.setUpdate_time(user.getUpdate_time());

		return userForm;
	}

	// 名前によるユーザー取得
	public List<UserEntity> getUserByName(String username) {
		return userMapper.getUserByName(username);
	}

	public UserEntity getUserByEmail(String email) {
		return userMapper.getUserEntityByEmail(email);
	}

	// ユーザー登録
	public void insertUser(UserForm userForm) {

		System.out.println(userForm.getUser_email());

		UserEntity user = new UserEntity();
		user.setUser_name(userForm.getUser_name());
		user.setUser_email(userForm.getUser_email());
		user.setUser_password(userForm.getUser_password());
		user.setUser_image(userForm.getUser_image());
		user.setUser_role(userForm.getUser_role());
		user.setMobail(userForm.getMobail());
		user.setAddress(userForm.getAddress());

		userMapper.insert(user);
		System.out.println("data inserted");
	}

	// ユーザー更新

	// ユーザー削除
	public void removeUserById(int id) {
		userMapper.delete(id);
	}

}
