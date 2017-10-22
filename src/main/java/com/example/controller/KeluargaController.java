package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.KecamatanModel;
import com.example.model.KeluargaModel;
import com.example.model.KelurahanModel;
import com.example.model.KotaModel;
import com.example.service.KecamatanService;
import com.example.service.KeluargaService;
import com.example.service.KelurahanService;
import com.example.service.KotaService;
import com.example.service.PendudukService;

@Controller
public class KeluargaController {
	@Autowired
	PendudukService pendudukDAO;
	
	@Autowired
	KeluargaService keluargaDAO;
	
	@Autowired
	KelurahanService kelurahanDAO;
	
	@Autowired
	KecamatanService kecamatanDAO;
	
	@Autowired
	KotaService kotaDAO;

	@RequestMapping("/keluarga")
    public String viewKeluarga (Model model, @RequestParam(value = "nkk", required = true) String nkk) {
		KeluargaModel keluarga = keluargaDAO.selectKeluargankk (nkk);
        
        if (keluarga != null) {
        	KelurahanModel kelurahan = kelurahanDAO.selectKelurahan(Integer.parseInt(keluarga.getId_kelurahan()));
        	KecamatanModel kecamatan = kecamatanDAO.selectKecamatan(Integer.parseInt(kelurahan.getId_kecamatan()));
        	KotaModel kota = kotaDAO.selectKota(Integer.parseInt(kecamatan.getId_kota()));
            model.addAttribute ("keluarga", keluarga);
            model.addAttribute ("kelurahan", kelurahan);
            model.addAttribute ("kecamatan", kecamatan);
            model.addAttribute ("kota", kota);
            return "viewKeluargaNKK";
        } else {
            model.addAttribute ("nkk", nkk);
            return "not-found";
        }
    }
	
	@RequestMapping("/keluarga/tambah")
    public String tambahKeluarga () {
        return "formTambahKeluarga";
    }
}
