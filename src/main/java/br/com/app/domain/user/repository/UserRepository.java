package br.com.app.domain.user.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.app.domain.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {
	
	@Query("SELECT u FROM br.com.app.domain.user.model.User u WHERE u.id = :userId AND u.status = 'ATIVO' ")
	Optional<User> findByIdAndUserStatusIsActive(@Param("userId") UUID userId);


}
