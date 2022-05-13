package com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.ProductCategoryMakerEntity;
import com.ecommerce.entity.ProductEntity;
import com.ecommerce.mapper.ProductMapper;

@Service
public class ProductService {

	@Autowired
	ProductMapper productMapper;

	public List<ProductCategoryMakerEntity> getAllProduct() {
		return productMapper.getAllProduct();
	}

	public List<ProductCategoryMakerEntity> getAllProductByCategoryId(int id) {

		return productMapper.getAllProductCategoryId(id);
	}

	public ProductCategoryMakerEntity getProductCategoryMakerById(int id) {
		return productMapper.getProductCategoryMakerById(id);
	}

	public ProductEntity getProductById(int id) {
		return productMapper.getProductById(id);
	}

	public ProductEntity saveProduct(ProductEntity product) {
		productMapper.saveProduct(product);
		return product;
	}

	public void updateProduct(ProductEntity product) {

		productMapper.updateProduct(product);

	}

	public void updateProductStock(ProductEntity product) {

		productMapper.updateStock(product);

	}

	public void deleteProduct(int id) {

		productMapper.delete(id);
	}

}
