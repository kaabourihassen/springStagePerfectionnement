package org.gestion.bp.web;

import org.gestion.bp.exception.RessourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import org.gestion.bp.entities.Category;
import org.gestion.bp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/categories")
public class CategorieController {

    @Autowired
	CategoryService categoryService;
	 @GetMapping("")
	 public List<Category> getAllCategories(Model model) {
	 	 return categoryService.findAllCategories();
	 }

	@GetMapping("{catId}")
	public Category getCategory(@PathVariable Long catId) throws RessourceNotFoundException {
		return categoryService.getCategorie(catId);
	}

	 @PutMapping(value="/{catId}")
	 public Category updateCateg(@PathVariable Long catId, @RequestBody Category cat) throws RessourceNotFoundException {
		 return categoryService.UpdateCategory(catId,cat);

	 }

	 @PostMapping(value="")
	 public Category insertProdArticleC(@RequestBody Category category) {
	     return categoryService.insertCategory(category);
	 }
	 
	 
	 
	 @DeleteMapping("/{catId}")
	 public void deleteCategorie(@PathVariable Long catId){
		 categoryService.deleteCategory(catId);
	 }
	 
}