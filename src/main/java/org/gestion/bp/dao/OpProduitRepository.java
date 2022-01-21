package org.gestion.bp.dao;

import org.gestion.bp.entities.OperationProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpProduitRepository extends JpaRepository<OperationProduit, Long> {

}

