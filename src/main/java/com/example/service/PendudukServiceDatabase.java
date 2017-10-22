package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PendudukMapper;
import com.example.model.PendudukModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PendudukServiceDatabase implements PendudukService {
	@Autowired
    private PendudukMapper pendudukMapper;

	@Override
	public PendudukModel selectPenduduk(String nik) {
		log.info ("select penduduk with nik {}", nik);
        return pendudukMapper.selectPenduduk (nik);
	}

	@Override
	public int countNIK(String nik) {
		log.info ("count penduduk with nik like {}", nik);
        return pendudukMapper.countNIK (nik);
	}

	@Override
	public void addPenduduk(PendudukModel penduduk) {
		log.info ("add penduduk with {}", penduduk);
		pendudukMapper.addPenduduk(penduduk);
	}
}
