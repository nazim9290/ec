package com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.CategoryEntity;
import com.ecommerce.mapper.CategoryMapper;

@Service
public class CategoryService {

	@Autowired
	CategoryMapper categoryMapper;

	// カテゴリ一覧
	public List<CategoryEntity> getCategory() {
		return categoryMapper.getAllCategory();
	}

	public CategoryEntity getCategoryById(int id) {

		return categoryMapper.getcategoryById(id);
	}

	public CategoryEntity saveCategory(CategoryEntity categoryEntity) {
		categoryMapper.insert(categoryEntity);
		return categoryEntity;
	}

	public void updateCategory(CategoryEntity categoryEntity) {
		categoryMapper.update(categoryEntity);
	}

	public void deleteCategory(int id) {
		categoryMapper.delete(id);
	}



}
