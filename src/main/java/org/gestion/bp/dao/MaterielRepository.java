package org.gestion.bp.dao;


import org.gestion.bp.entities.Materiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterielRepository extends JpaRepository<Materiel, Long> {

    public List<Materiel> findMaterielByPris(boolean pris);
}
