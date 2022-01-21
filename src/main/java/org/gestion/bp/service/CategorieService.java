package org.gestion.bp.service;

import java.util.List;
import java.util.Optional;

import org.gestion.bp.dao.CategorieRepository;
import org.gestion.bp.entities.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategorieService {

	@Autowired
	CategorieRepository categoryRepository;
	
	public Categorie insertCategory(Categorie category) {
		return categoryRepository.save(category);
	}
	
	public List<Categorie> findAllCategories(){
		
		return  categoryRepository.findAll();
	}

	public Categorie UpdateCategory(Long catId, Categorie category) {
		Categorie c = getCategorie(catId);
		c.setNomCateg(category.getNomCateg());
		c.setDescription(category.getDescription());
		return categoryRepository.save(c);
	}
	
	public void deleteCategory(Long catId) {
		categoryRepository.deleteById(catId);
	}
	
	public Categorie getCategorie(Long catId) {
		return categoryRepository.findById(catId).orElseThrow(() -> new RuntimeException("Categorie introuvable !"));
	}

	
}
