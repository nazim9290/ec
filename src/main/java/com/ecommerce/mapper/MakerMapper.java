package com.ecommerce.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ecommerce.entity.MakerEntity;

@Mapper
public interface MakerMapper {

	// get maker
	@Select("Select * from maker")
	List<MakerEntity> getAllmaker();

	// get maker by id
	@Select("select * from maker where maker_id = #{id}")
	MakerEntity getmakerById(int id);

	// insert
	@Insert("insert into maker ( maker_name,create_time,update_time) values (#{maker_name},now(),now())")
	void insert(MakerEntity makerEntity);

	// update
	@Update("update maker set maker_name = #{maker_name} where maker_id = #{id}")
	void update(MakerEntity makerEntity);

	// delete
	@Delete("delete from maker where maker_id = #{id}")
	void delete(int id);
}
