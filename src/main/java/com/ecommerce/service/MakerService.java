package com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.MakerEntity;
import com.ecommerce.mapper.MakerMapper;

@Service
public class MakerService {

	@Autowired
	MakerMapper makerMapper;

	// カテゴリ一覧
	public List<MakerEntity> getmaker() {
		return makerMapper.getAllmaker();
	}

	public MakerEntity getmakerById(int id) {

		return makerMapper.getmakerById(id);
	}

	public MakerEntity savemaker(MakerEntity makerEntity) {
		makerMapper.insert(makerEntity);
		return makerEntity;
	}

	public void updatemaker(MakerEntity makerEntity) {
		makerMapper.update(makerEntity);
	}

	public void deletemaker(int id) {
		makerMapper.delete(id);
	}



}
