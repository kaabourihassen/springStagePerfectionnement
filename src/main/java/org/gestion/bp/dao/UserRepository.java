package org.gestion.bp.dao;

import java.util.List;
import java.util.Optional;

import org.gestion.bp.entities.Role;
import org.gestion.bp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE u.username=:nom")
	Optional<User> findByUsername(String nom);
	Optional<User> findById(Long id);

	public List<User> findUsersByRole(Role role);
	
}
