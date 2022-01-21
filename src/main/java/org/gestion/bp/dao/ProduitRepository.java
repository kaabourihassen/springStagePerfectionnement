package org.gestion.bp.dao;


import org.gestion.bp.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitRepository  extends JpaRepository<Produit, Long> {

}
