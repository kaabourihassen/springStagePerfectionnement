package org.gestion.bp.dao;

import java.util.Optional;
import org.gestion.bp.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository  extends JpaRepository<Categorie, Long>{

    void deleteById(Long catId);

	Optional<Categorie> findById(Long catId);
}
