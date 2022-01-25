package org.gestion.bp.service;

import java.util.List;

import org.gestion.bp.dao.ArticleCRepository;
import org.gestion.bp.dao.MaterielRepository;
import org.gestion.bp.entities.ArticleConsomme;
import org.gestion.bp.entities.Materiel;
import org.gestion.bp.entities.Produit;
import org.gestion.bp.exception.RessourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleCService {
	@Autowired
	ArticleCRepository articleCRepository;

	public ArticleConsomme createArticle(ArticleConsomme articleConsomme){
		return articleCRepository.save(articleConsomme);
	}
	public ArticleConsomme UpdateArticleC(Long articleId,ArticleConsomme a) throws RessourceNotFoundException {
		ArticleConsomme articleConsomme = articleCRepository.findById(articleId).orElseThrow(()->new RessourceNotFoundException("Article not found"));
		articleConsomme.setQte(a.getQte());
		articleConsomme.setQteMin(a.getQteMin());
		articleConsomme.setCategorie(a.getCategorie());
		articleConsomme.setIntitule(a.getIntitule());
		articleConsomme.setMagazin(a.getMagazin());
		articleConsomme.setOperationProduits(a.getOperationProduits());
		return articleCRepository.save(articleConsomme);
	}
	public void deleteArticleC(Long a) {
		articleCRepository.deleteById(a);
	}
	
	public ArticleConsomme getOneArticle(Long id) throws RessourceNotFoundException {
			return articleCRepository.findById(id).orElseThrow(()->new RessourceNotFoundException("Article not found"));
	    }

	public List<ArticleConsomme> findAllArticleC() {	
		return articleCRepository.findAll();
	}
	
}

