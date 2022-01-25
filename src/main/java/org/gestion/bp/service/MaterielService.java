package org.gestion.bp.service;

import java.util.List;

import org.gestion.bp.dao.MaterielRepository;
import org.gestion.bp.entities.Materiel;
import org.gestion.bp.exception.RessourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterielService {
	@Autowired
	MaterielRepository materielRepository;


	public Materiel createMateriel(Materiel materiel){
		return materielRepository.save(materiel);
	}
	public Materiel UpdateMateriel(Long materielId, Materiel a) throws RessourceNotFoundException {
		Materiel materiel = materielRepository.findById(materielId).orElseThrow(()->new RessourceNotFoundException("materiel not found"));
		materiel.setDateRetour(a.getDateRetour());
		materiel.setCategorie(a.getCategorie());
		materiel.setIntitule(a.getIntitule());
		materiel.setMagazin(a.getMagazin());
		materiel.setMatricule(a.getMatricule());
		return materielRepository.save(materiel);
	}
	public void deleteMateriel(Long a) {
		materielRepository.deleteById(a);
	}
	
	public Materiel getOneMateriel(Long id) throws RessourceNotFoundException {
			return materielRepository.findById(id).orElseThrow(()->new RessourceNotFoundException("materiel not found"));
	    }

	public List<Materiel> findAllMateriel() {
		return materielRepository.findAll();
	}
	
}