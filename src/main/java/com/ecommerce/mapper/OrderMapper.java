package com.ecommerce.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import com.ecommerce.entity.OrderEntity;
import com.ecommerce.entity.OrderItemEntity;

@Mapper
public interface OrderMapper {
	
	// insert
	@Insert("insert into orderdetails ( user_id,status,name,address,mobailNo,email,create_time,update_time) "
			+ "values (#{user_id},#{status},#{name},#{address},#{mobailNo},#{email},now(),now())")
	@Options(useGeneratedKeys = true, keyProperty = "order_id")
	Integer insertOrder(OrderEntity orderEntity);
	
	
	@Insert("insert into orderitem ( order_id,user_id,product_id,price,quantity)"
			+ "values (#{order_id},#{user_id},#{product_id},#{price},#{quantity})")
	void insertOrderItem(OrderItemEntity orderItemEntity);

}
