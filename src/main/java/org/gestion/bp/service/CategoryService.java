package org.gestion.bp.service;

import java.util.List;

import org.gestion.bp.dao.CategorieRepository;
import org.gestion.bp.entities.Category;
import org.gestion.bp.exception.RessourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	@Autowired
	CategorieRepository categoryRepository;
	
	public Category insertCategory(Category category) {
		return categoryRepository.save(category);
	}
	
	public List<Category> findAllCategories(){
		
		return  categoryRepository.findAll();
	}

	public Category UpdateCategory(Long catId, Category category) throws RessourceNotFoundException {
		Category c = getCategorie(catId);
		c.setNomCateg(category.getNomCateg());
		c.setDescription(category.getDescription());
		return categoryRepository.save(c);
	}
	
	public void deleteCategory(Long catId) {
		categoryRepository.deleteById(catId);
	}
	
	public Category getCategorie(Long catId) throws RessourceNotFoundException {
		return categoryRepository.findById(catId).orElseThrow(() -> new RessourceNotFoundException("Categorie introuvable !"));
	}

	
}
