package com.ecommerce.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ecommerce.entity.CategoryEntity;

@Mapper
public interface CategoryMapper {

	// get category
	@Select("Select * from category")
	List<CategoryEntity> getAllCategory();

	// get category by id
	@Select("select * from category where category_id = #{id}")
	CategoryEntity getcategoryById(int id);

	// insert
	@Insert("insert into category ( category_name,create_time,update_time) values (#{category_name},now(),now())")
	void insert(CategoryEntity categoryEntity);

	// update
	@Update("update category set category_name = #{category_name} where category_id = #{id}")
	void update(CategoryEntity categoryEntity);

	// delete
	@Delete("delete from category where category_id = #{id}")
	void delete(int id);
}
