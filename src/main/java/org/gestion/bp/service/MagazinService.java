package org.gestion.bp.service;

import java.util.List;
import org.gestion.bp.dao.MagazinRepository;
import org.gestion.bp.entities.Magazin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class MagazinService {
	
	@Autowired
	MagazinRepository magazinRepository;
	
	public Magazin createMagazin(Magazin magazin) {
		return magazinRepository.save(magazin);
	}
	
	public List<Magazin> findAllMagazins(){
		
		return  magazinRepository.findAll();
	}

	public Magazin UpdateMagazin(Long magazinId,Magazin magazin) {
		Magazin magazin1 = magazinRepository.findById(magazinId);
		return magazinRepository.save(magazin);
	}
	
	public void deleteMagazin(Long magazinId) {
		magazinRepository.deleteById(magazinId);
	}
	
}
