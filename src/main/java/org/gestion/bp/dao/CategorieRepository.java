package org.gestion.bp.dao;

import java.util.Optional;
import org.gestion.bp.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository  extends JpaRepository<Category, Long>{

    void deleteById(Long catId);

	Optional<Category> findById(Long catId);
}
