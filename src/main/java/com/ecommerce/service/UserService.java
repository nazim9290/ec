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


	public UserEntity getUserEntityByEmail(String email) {
		return userMapper.getUserEntityByEmail(email);
	}

	// ユーザー登録
	public void insertUser(UserForm userForm) {
		// password を暗号化する
//		userForm.setPassword(bcryptEncoder.encode(userForm.getPassword()));

		userMapper.insert(userForm);
	}

	// ユーザー更新


	// ユーザー削除
	public void deleteUser(String id) {
		userMapper.delete(Integer.parseInt(id));
	}

}
