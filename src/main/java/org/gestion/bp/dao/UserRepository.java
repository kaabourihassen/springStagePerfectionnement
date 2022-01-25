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
	Optional<User> findById(Long id);

	List<User> findUsersByRole(Role role);

	Optional<User> findByEmail(String email);
}
