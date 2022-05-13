package com.ecommerce.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ecommerce.dto.CartDTO;
import com.ecommerce.entity.CartEntity;

@Mapper
public interface CartMapper {

	// cart count
	@Select("select sum(quantity) from cart where user_id=#{user_id}")
	Object cartCount(int user_id);


	// get user cart
	@Select("Select * from cart INNER JOIN user ON cart.user_id=user.user_id INNER JOIN product ON cart.product_id=product.product_id  where cart.user_id = #{id}")
	List<CartDTO> getUserCart(int user_id);

	@Select("Select * from cart where user_id = #{id}")
	List<CartEntity> getCartByUserId(int user_id);

	@Select("delete from cart where user_id = #{id}")
	void deleteUserCar(int user_id);
	
	// get cart by id
	@Select("select * from cart where cart_id = #{id}")
	CartEntity getcartById(int id);

	// get cart product id
	@Select("select * from cart where product_id = #{id}")
	CartEntity getCartByProductId(int id);

	// insert
	@Insert("insert into cart (user_id,product_id,quantity,total,create_time,update_time) values (#{user_id},#{product_id},#{quantity},#{total},now(),now())")
	void insertCart(CartEntity cartEntity);

	// delete
	@Delete("delete from cart where cart_id = #{id}")
	void deleteCart(int id);


}
