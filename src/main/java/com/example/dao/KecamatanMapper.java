package com.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.KecamatanModel;

@Mapper
public interface KecamatanMapper {
	@Select("SELECT * FROM kecamatan WHERE id = #{id}")
	KecamatanModel selectKecamatan (@Param("id") int id);
}
