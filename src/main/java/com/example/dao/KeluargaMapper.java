package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.model.KeluargaModel;
import com.example.model.PendudukModel;

@Mapper
public interface KeluargaMapper {
	@Select("SELECT * FROM keluarga WHERE id = #{id}")
	KeluargaModel selectKeluarga (@Param("id") int id);
	
	@Select("SELECT * FROM keluarga WHERE nomor_kk = #{nomor_kk}")
    @Results(value = {
    		@Result(property="id", column="id"),
    		@Result(property="nomor_kk", column="nomor_kk"),
    		@Result(property="alamat", column="alamat"),
    		@Result(property="rt", column="rt"),
    		@Result(property="rw", column="rw"),
    		@Result(property="id_kelurahan", column="id_kelurahan"),
    		@Result(property="is_tidak_berlaku", column="is_tidak_berlaku"),
    		@Result(property="anggota_keluarga", 
    		column="id", javaType = List.class, many=@Many(select="selectAnggotaKeluarga"))
    })
	KeluargaModel selectKeluargankk (@Param("nomor_kk") String nomor_kk);
	
	@Select("SELECT * FROM penduduk WHERE id_keluarga = #{id}")
    List<PendudukModel> selectAnggotaKeluarga (@Param("id") int id);
}
