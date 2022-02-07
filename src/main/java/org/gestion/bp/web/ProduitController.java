package org.gestion.bp.web;

import org.gestion.bp.entities.ArticleConsomme;
import org.gestion.bp.entities.Materiel;
import org.gestion.bp.entities.Produit;
import org.gestion.bp.exception.RessourceNotFoundException;
import org.gestion.bp.service.ArticleCService;
import org.gestion.bp.service.MaterielService;
import org.gestion.bp.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produits")
public class ProduitController {

	@Autowired
	private ProduitService produitService;
	@Autowired
	private MaterielService materielService;
	@Autowired
	private ArticleCService articleCService;

	@GetMapping("/")
	public List<Produit> getAllProduits(){
		return produitService.findAllProduits();
	}

	@PostMapping("/materiels")
	public Materiel createMateriel(@RequestBody Materiel materiel) throws RessourceNotFoundException {
		return materielService.createMateriel(materiel);
	}

	@PostMapping("/articles")
	public ArticleConsomme createArticle(@RequestBody ArticleConsomme articleConsomme) throws RessourceNotFoundException {
		return articleCService.createArticle(articleConsomme);
	}

	@GetMapping("/materiels")
	public List<Materiel> getAllMateriels(){
		return materielService.findAllMateriel();
	}

	@GetMapping("/articles")
	public List<ArticleConsomme> getAllArticles(){
		return articleCService.findAllArticleC();
	}

	@GetMapping("/articles/{articleId}")
	public ArticleConsomme getOneArticle(@PathVariable Long articleId) throws RessourceNotFoundException {
		return articleCService.getOneArticle(articleId);
	}
	@GetMapping("/materiels/{materielId}")
	public Materiel getOneMateriel(@PathVariable Long materielId) throws RessourceNotFoundException {
		return materielService.getOneMateriel(materielId);
	}
	@PutMapping("/articles/{articleId}")
	public ArticleConsomme updateOneArticle(@PathVariable Long articleId,@RequestBody ArticleConsomme articleConsomme) throws RessourceNotFoundException {
		return articleCService.UpdateArticleC(articleId,articleConsomme);
	}
	@PutMapping("/materiels/{materielId}")
	public Materiel updateOneMateriel(@PathVariable Long materielId,@RequestBody Materiel materiel) throws RessourceNotFoundException {
		return materielService.UpdateMateriel(materielId,materiel);
	}
	@DeleteMapping("/articles/{articleId}")
	public void deleteOneArticle(@PathVariable Long articleId){
		articleCService.deleteArticleC(articleId);
	}
	@DeleteMapping("/materiels/{materielId}")
	public void deleteOneMateriel(@PathVariable Long materielId){
		materielService.deleteMateriel(materielId);
	}

}