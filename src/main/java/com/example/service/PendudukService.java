package com.example.service;

import com.example.model.PendudukModel;

public interface PendudukService {
	PendudukModel selectPenduduk (String nik);
	int countNIK (String nik);
	void addPenduduk(PendudukModel penduduk);
}
