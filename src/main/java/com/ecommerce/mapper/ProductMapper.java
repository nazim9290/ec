package com.ecommerce.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ecommerce.entity.CategoryEntity;
import com.ecommerce.entity.ProductEntity;

@Mapper
public interface ProductMapper {

	
	
	@Select("Select * from category")
	List<CategoryEntity> getAllCategory();
	

	@Select("Select * from product INNER JOIN category ON product.category_id=category.category_id;")
	List<ProductEntity> getAllProduct();

	@Select("Select * from product WHERE product_id = #{id}")
	ProductEntity getProductById(int id);

//	@Insert("INSERT into product (id,empolyee_name,product_type,amount,date) values(#{id},#{empolyee_name},#{product_type},#{amount},#{date});")
//	void insert(ProductForm product);

//	@Delete("DELETE FROM product WHERE id =#{id}")
//	void deleteproduct(int id);

//	@Update("UPDATE product SET empolyee_name=#{empolyee_name},product_type=#{product_type},amount=#{amount},date=#{date} WHERE id =#{id}")
//	void updateproduct(ProductForm product);

//	@Select("SELECT * FROM product WHERE id = #{id}")
//	List<ProductEntity> search(ProductForm product);

//	@Select("<script>" + "select * from product" + "<where>"
	/*
	 * + "<if test=\"empolyee_name != null and empolyee_name.length>0\">" +
	 * "AND empolyee_name = #{empolyee_name}" + "</if>" +
	 * "<if test=\"product_type != null and product_type.length>0\">" +
	 * "AND product_type = #{product_type}" + "</if>" +
	 * "<if test=\"amount != null and amount.length>0\">" + "AND amount = #{amount}"
	 * + "</if>" + "<if test=\"date != null and date.length>0\">" +
	 * "AND date = #{date}" + "</if>" + "</where>" + "</script>")
	 */

//	List<ProductEntity> findBy(ProductForm product);

}
