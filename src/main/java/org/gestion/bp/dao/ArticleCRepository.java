package org.gestion.bp.dao;

import org.gestion.bp.entities.ArticleConsomme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleCRepository extends JpaRepository<ArticleConsomme, Long> {
	
}
