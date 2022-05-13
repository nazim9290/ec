package com.ecommerce.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ecommerce.entity.MakerEntity;
import com.ecommerce.entity.ProductCategoryMakerEntity;
import com.ecommerce.entity.ProductEntity;

@Mapper
public interface ProductMapper {

	// getmaker
	@Select("Select * from maker")
	List<MakerEntity> getAllMaker();

	// one to many
	@Select("Select product_id,product_name,product_description,price,stock,image,category_name,maker_name from product INNER JOIN category ON product.category_id=category.category_id INNER JOIN maker ON product.maker_id=maker.maker_id;")
	List<ProductCategoryMakerEntity> getAllProduct();

	// one to many category to product
	@Select("Select product_id,product_name,product_description,price,stock,image,category_name,maker_name from product INNER JOIN category ON product.category_id=category.category_id INNER JOIN maker ON product.maker_id=maker.maker_id where product.category_id=#{id};")
	List<ProductCategoryMakerEntity> getAllProductCategoryId(int id);

	@Select("Select product_id,product_name,product_description,price,stock,image,category_name,maker_name from product INNER JOIN category ON product.category_id=category.category_id INNER JOIN maker ON product.maker_id=maker.maker_id where product_id=#{id};")
	ProductCategoryMakerEntity getProductCategoryMakerById(int id);

	// get one product
	@Select("Select * from product WHERE product_id = #{id}")
	ProductEntity getProductById(int id);

	// insert
	@Insert("insert into product ( product_name,product_description,price,stock,image,category_id,maker_id,create_time,update_time) values (#{product_name},#{product_description},#{price},#{stock},#{image},#{category_id},#{maker_id},now(),now())")
	void saveProduct(ProductEntity productEntity);

	// update
	@Update("update product set product_name = #{product_name}, product_description=#{product_description},price=#{price},stock=#{stock},image=#{image},category_id=#{category_id},maker_id=#{maker_id} where product.product_id = #{product_id}")
	void updateProduct(ProductEntity productEntity);

	// update product stock
	@Update("update product set stock=#{stock} whre product_id=#{product_id}")
	void updateStock(ProductEntity productEntity);

	// delete
	@Delete("delete from product where product_id = #{id}")
	void delete(int id);

}
