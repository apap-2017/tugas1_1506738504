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
import com.example.model.PendudukModel;
import com.example.service.KecamatanService;
import com.example.service.KeluargaService;
import com.example.service.KelurahanService;
import com.example.service.KotaService;
import com.example.service.PendudukService;

@Controller
public class PendudukController {
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

	@RequestMapping("/penduduk")
    public String viewPenduduk (Model model, @RequestParam(value = "nik", required = true) String nik) {
        PendudukModel penduduk = pendudukDAO.selectPenduduk (nik);
        
        if (penduduk != null) {
        	KeluargaModel keluarga = keluargaDAO.selectKeluarga(penduduk.getId_keluarga());
        	KelurahanModel kelurahan = kelurahanDAO.selectKelurahan(Integer.parseInt(keluarga.getId_kelurahan()));
        	KecamatanModel kecamatan = kecamatanDAO.selectKecamatan(Integer.parseInt(kelurahan.getId_kecamatan()));
        	KotaModel kota = kotaDAO.selectKota(Integer.parseInt(kecamatan.getId_kota()));
            model.addAttribute ("penduduk", penduduk);
            model.addAttribute ("keluarga", keluarga);
            model.addAttribute ("kelurahan", kelurahan);
            model.addAttribute ("kecamatan", kecamatan);
            model.addAttribute ("kota", kota);
            return "viewPendudukNIK";
        } else {
            model.addAttribute ("nik", nik);
            return "not-found";
        }
    }
	
	@RequestMapping("/penduduk/tambah")
	public String formTambahPenduduk () {
		return "formTambahPenduduk";
	}
	
	@RequestMapping(value = "/penduduk/tambah/submit", method = RequestMethod.POST)
	public String tambahPenduduk (Model model,
            @RequestParam(value = "nama", required = true) String nama,
            @RequestParam(value = "jenis_kelamin", required = true) String jenis_kelamin,
            @RequestParam(value = "tempat_lahir", required = true) String tempat_lahir,
            @RequestParam(value = "tanggal_lahir", required = true) String tanggal_lahir,
            @RequestParam(value = "golongan_darah", required = true) String golongan_darah,
            @RequestParam(value = "agama", required = true) String agama,
            @RequestParam(value = "status_perkawinan", required = true) String status_perkawinan,
            @RequestParam(value = "pekerjaan", required = true) String pekerjaan,
            @RequestParam(value = "is_wni", required = true) String  is_wni,
            @RequestParam(value = "is_wafat", required = true) String is_wafat,
            @RequestParam(value = "id_keluarga", required = true) String id_keluarga,
            @RequestParam(value = "status_dalam_keluarga", required = true) String status_dalam_keluarga) {
	    
		int jenisKelamin = (jenis_kelamin.equals("Perempuan")) ? 1 : 0;
		int isWni = (is_wni.equals("WNI")) ? 1 : 0;
		int isWafat = (is_wafat.equals("Hidup")) ? 0 : 1;
		
		KeluargaModel keluarga = keluargaDAO.selectKeluarga(Integer.parseInt(id_keluarga));
    	KelurahanModel kelurahan = kelurahanDAO.selectKelurahan(Integer.parseInt(keluarga.getId_kelurahan()));
    	KecamatanModel kecamatan = kecamatanDAO.selectKecamatan(Integer.parseInt(kelurahan.getId_kecamatan()));
    	
    	String nik1 = kecamatan.getKode_kecamatan().substring(0, 6);
    	String hbd[] = tanggal_lahir.split("-");
    	String nik2 = ((jenisKelamin == 0) ? hbd[2] : (Integer.parseInt(hbd[2]) + 40) + "") + hbd[1] + hbd[0].substring(2, 4);
    	String nik3 = "";
    	int counter = pendudukDAO.countNIK(nik1 + nik2) + 1;
    	if (counter < 10) {
    		nik3 = "000" + counter;
    	} else if (counter < 100) {
    		nik3 = "00" + counter;
    	} else if (counter < 1000) {
    		nik3 = "0" + counter;
    	} else {
    		nik3 = "" + counter;
    	}
    	String nik = nik1 + nik2 + nik3;
    	
    	pendudukDAO.addPenduduk(new PendudukModel(0, nik, nama, tempat_lahir, tanggal_lahir, jenisKelamin, isWni, Integer.parseInt(id_keluarga), agama, pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, isWafat));
    	PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
    	model.addAttribute("penduduk", penduduk);
		return "success-add";
	}
}
