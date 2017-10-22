package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.KelurahanMapper;
import com.example.model.KelurahanModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KelurahanServiceDatabase implements KelurahanService {
	@Autowired
	private KelurahanMapper kelurahanMapper;

	@Override
	public KelurahanModel selectKelurahan(int id) {
		log.info ("select kelurahan with id {}", id);
        return kelurahanMapper.selectKelurahan (id);
	}
	
}
