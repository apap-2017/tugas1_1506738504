package com.example.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.PendudukModel;

@Mapper
public interface PendudukMapper {
	@Select("SELECT * FROM penduduk WHERE nik = #{nik}")
	PendudukModel selectPenduduk (@Param("nik") String nik);
	
	@Select("SELECT count(*) FROM penduduk WHERE nik like #{nik}")
	int countNIK(String nik);
	
	@Insert("INSERT INTO penduduk (nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, is_wni, id_keluarga, agama, pekerjaan, "
			+ "status_perkawinan, status_dalam_keluarga, golongan_darah, is_wafat) VALUES (#{nik}, #{nama}, #{tempat_lahir}, "
			+ "#{tanggal_lahir}, #{jenis_kelamin}, #{is_wni}, #{id_keluarga}, #{agama}, #{pekerjaan}, "
			+ "#{status_perkawinan}, #{status_dalam_keluarga}, #{golongan_darah}, #{is_wafat})")
	void addPenduduk (PendudukModel penduduk);
}
