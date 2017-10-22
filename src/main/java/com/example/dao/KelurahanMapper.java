package com.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.KelurahanModel;

@Mapper
public interface KelurahanMapper {
	@Select("SELECT * FROM kelurahan WHERE id = #{id}")
	KelurahanModel selectKelurahan (@Param("id") int id);
}
