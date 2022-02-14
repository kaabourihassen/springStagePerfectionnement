package org.gestion.bp.dao;

import org.gestion.bp.entities.Operation;
import org.gestion.bp.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository  extends JpaRepository<Operation, Long>{

}
