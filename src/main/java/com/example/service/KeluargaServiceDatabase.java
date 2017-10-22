package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.KeluargaMapper;
import com.example.model.KeluargaModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KeluargaServiceDatabase implements KeluargaService {
	@Autowired
	private KeluargaMapper keluargaMapper;

	@Override
	public KeluargaModel selectKeluarga(int id) {
		log.info ("select kelaurga with id {}", id);
        return keluargaMapper.selectKeluarga (id);
	}

	@Override
	public KeluargaModel selectKeluargankk(String nomor_kk) {
		log.info ("select kelaurga with nomor_kk {}", nomor_kk);
        return keluargaMapper.selectKeluargankk (nomor_kk);
	}
	
}
