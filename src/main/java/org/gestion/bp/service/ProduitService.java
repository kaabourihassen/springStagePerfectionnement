package org.gestion.bp.service;

import java.util.List;
import org.gestion.bp.dao.ProduitRepository;
import org.gestion.bp.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProduitService {
	@Autowired
	ProduitRepository produitRepository;

	public void deleteProduit(Long produitId) {
		produitRepository.deleteById(produitId);
	}
	
	public List<Produit> findAllProduits() {	
		return produitRepository.findAll();
	}

    public Produit getOneProduit(Long id) {
		return produitRepository.getById(id);
    }
}
	


