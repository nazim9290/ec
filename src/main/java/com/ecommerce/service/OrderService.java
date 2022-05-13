package com.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.OrderEntity;
import com.ecommerce.entity.OrderItemEntity;
import com.ecommerce.mapper.OrderMapper;

@Service
public class OrderService {
	@Autowired
	OrderMapper orderMapper;

	public int saveOrder(OrderEntity order) {

		return orderMapper.insertOrder(order);
	}

	public void insertOrderItem(OrderItemEntity orderItemEntity) {
		orderMapper.insertOrderItem(orderItemEntity);
	}

}
