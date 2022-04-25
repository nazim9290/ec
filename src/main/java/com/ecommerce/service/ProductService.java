package com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.CategoryEntity;
import com.ecommerce.entity.ProductEntity;
import com.ecommerce.mapper.ProductMapper;

@Service
public class ProductService {

	@Autowired
	ProductMapper productMapper;

	public List<CategoryEntity> getCategory() {
		return productMapper.getAllCategory();
	}

	public List<ProductEntity> getallProduct() {
		return productMapper.getAllProduct();
	}

	public ProductEntity productDetails(int id) {
		return productMapper.getProductById(id);
	}

}
