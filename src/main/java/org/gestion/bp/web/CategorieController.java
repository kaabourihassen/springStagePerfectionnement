package org.gestion.bp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.gestion.bp.dao.CategorieRepository;
import org.gestion.bp.entities.Categorie;
import org.gestion.bp.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.util.List;

@RestController
public class CategorieController {

    @Autowired
    CategorieService categoryService;
	 @GetMapping("/ListCategorie")
	 public List<Categorie> getAllCategories(Model model) {
	 	 return categoryService.findAllCategories();
	 }

	 @PutMapping(value="/updateCateg/{catId}")
	 public Categorie updateCateg(@PathVariable Long catId,@RequestBody Categorie cat){
		 return categoryService.UpdateCategory(catId,cat);

	 }

	 @PostMapping(value="/InsertCategorie")
	 public Categorie insertProdArticleC(@RequestBody Categorie categorie) {
	     return categoryService.insertCategory(categorie);
	 }
	 
	 
	 
	 @RequestMapping("/DeleteCategorie/{catId}")
	 public void deleteCategorie(@PathVariable Long catId){
		 categoryService.deleteCategory(catId);
	 }
	 
}