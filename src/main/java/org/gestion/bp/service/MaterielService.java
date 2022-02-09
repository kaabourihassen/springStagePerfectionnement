package org.gestion.bp.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
	@Autowired
	MagazinService magazinService;
	@Autowired
	CategoryService categoryService;

	public Materiel createMateriel(Materiel materiel) throws RessourceNotFoundException {
		materiel.setMagazin(magazinService.getOneMagazin(materiel.getMagazin().getMagazinId()));
		materiel.setCategory(categoryService.getCategorie(materiel.getCategory().getCategoryId()));
		return materielRepository.save(materiel);
	}
	public Materiel UpdateMateriel(Long materielId, Materiel a) throws RessourceNotFoundException {
		Materiel materiel = materielRepository.findById(materielId).orElseThrow(()->new RessourceNotFoundException("materiel not found"));
		materiel.setDateRetour(a.getDateRetour());
		materiel.setCategory(a.getCategory());
		materiel.setIntitule(a.getIntitule());
		materiel.setMagazin(a.getMagazin());
		materiel.setMatricule(a.getMatricule());
		return materielRepository.save(materiel);
	}

	public Materiel retrait(Long materielId) throws RessourceNotFoundException {
		Materiel materiel = getOneMateriel(materielId);
		return materielRepository.save(materiel);
	}
	public Materiel versement(Long materielId) throws RessourceNotFoundException {
		Materiel materiel = getOneMateriel(materielId);
		materiel.setDateRetour(LocalDate.now());
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