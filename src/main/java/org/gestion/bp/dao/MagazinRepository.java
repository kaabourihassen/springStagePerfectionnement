package org.gestion.bp.dao;

import org.gestion.bp.entities.Magazin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MagazinRepository  extends JpaRepository<Magazin, Long> {

    Optional<Magazin> findById(Long magazinId);

    void deleteById(Long magazinId);
}
