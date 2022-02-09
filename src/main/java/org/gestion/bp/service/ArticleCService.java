package org.gestion.bp.service;

import java.util.List;

import org.gestion.bp.dao.ArticleCRepository;
import org.gestion.bp.entities.ArticleConsomme;
import org.gestion.bp.exception.RessourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleCService {
	@Autowired
	ArticleCRepository articleCRepository;
	@Autowired
	MagazinService magazinService;
	@Autowired
	CategoryService categoryService;

	public ArticleConsomme createArticle(ArticleConsomme articleConsomme) throws RessourceNotFoundException {
		articleConsomme.setMagazin(magazinService.getOneMagazin(articleConsomme.getMagazin().getMagazinId()));
		articleConsomme.setCategory(categoryService.getCategorie(articleConsomme.getCategory().getCategoryId()));
		return articleCRepository.save(articleConsomme);
	}
	public ArticleConsomme UpdateArticleC(Long articleId,ArticleConsomme a) throws RessourceNotFoundException {
		ArticleConsomme articleConsomme = articleCRepository.findById(articleId).orElseThrow(()->new RessourceNotFoundException("Article not found"));
		articleConsomme.setQte(a.getQte());
		articleConsomme.setQteMin(a.getQteMin());
		articleConsomme.setCategory(a.getCategory());
		articleConsomme.setIntitule(a.getIntitule());
		articleConsomme.setMatricule(a.getMatricule());
		articleConsomme.setMagazin(a.getMagazin());
		return articleCRepository.save(articleConsomme);
	}
	public void deleteArticleC(Long a) {
		articleCRepository.deleteById(a);
	}

	public Boolean retraitQTE(int qte,Long articleId) throws RessourceNotFoundException {
		ArticleConsomme articleConsomme = getOneArticle(articleId);
		if(articleConsomme.getQte()-qte<articleConsomme.getQteMin()){
			return false;
		}else {
			articleConsomme.setQte(articleConsomme.getQte()-qte);
			articleCRepository.save(articleConsomme);
			return true;
		}
	}
	public ArticleConsomme versementQTE(int qte,Long articleId) throws RessourceNotFoundException {
		ArticleConsomme articleConsomme = getOneArticle(articleId);
		articleConsomme.setQte(articleConsomme.getQte()+qte);
		return articleCRepository.save(articleConsomme);
	}
	
	public ArticleConsomme getOneArticle(Long id) throws RessourceNotFoundException {
		return articleCRepository.findById(id).orElseThrow(()->new RessourceNotFoundException("Article not found"));
	}

	public List<ArticleConsomme> findAllArticleC() {	
		return articleCRepository.findAll();
	}
	
}

