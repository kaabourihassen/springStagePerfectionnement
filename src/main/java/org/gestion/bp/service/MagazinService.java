package org.gestion.bp.service;

import java.util.List;
import org.gestion.bp.dao.MagazinRepository;
import org.gestion.bp.entities.Magazin;
import org.gestion.bp.exception.RessourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MagazinService {
	
	@Autowired
	MagazinRepository magazinRepository;
	
	public Magazin createMagazin(Magazin magazin) {
		return magazinRepository.save(magazin);
	}
	
	public List<Magazin> getAllMagazins(){
		return  magazinRepository.findAll();
	}
	public Magazin getOneMagazin(Long id) throws RessourceNotFoundException {
		return magazinRepository.findById(id).orElseThrow(()-> new RessourceNotFoundException("magazin not found"));
	}

	public Magazin UpdateMagazin(Long magazinId,Magazin magazin) throws RessourceNotFoundException {
		Magazin magazin1 = magazinRepository.findById(magazinId).orElseThrow(()-> new RessourceNotFoundException("magazin not found"));
		magazin1.setNomMagazin(magazin.getNomMagazin());
		magazin1.setProduits(magazin.getProduits());
		return magazinRepository.save(magazin1);
	}
	
	public void deleteMagazin(Long magazinId) {
		magazinRepository.deleteById(magazinId);
	}
	
}
