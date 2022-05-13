package com.ecommerce.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ecommerce.entity.UserEntity;

@Mapper
public interface UserMapper {

	// get all
	@Select("select * from user")
	List<UserEntity> getAll();

	// get user by id
	@Select("select * from user where id = #{id}")
	UserEntity getUserById(int id);

	// get user by name (練習)
	@Select("select * from user where name Like CONCAT('%',#{name},'%')")
	List<UserEntity> getUserByName(String name);

	@Select("select * from user where user_email=#{email}")
	UserEntity getUserEntityByEmail(String email);


	// insert
	@Insert("insert into user ( user_name,user_email,user_password,user_image,user_role,mobail,address,create_time,update_time) "
			+ "values (#{user_name},#{user_email},#{user_password},#{user_image},#{user_role},#{mobail},#{address},now(),now())")
	void insert(UserEntity user);

	// update
	@Update("update user set id = #{id}, name = #{name}, sex = #{sex}, password = #{password} where user_id = #{id}")
	void update(UserEntity user);

	// delete
	@Delete("delete from user where user_id = #{id}")
	void delete(Integer id);

}
