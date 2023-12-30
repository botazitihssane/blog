package ma.emsi.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.emsi.blog.model.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
	@Query("SELECT u FROM Utilisateur u WHERE u.id = :id")
	Utilisateur findUtilisateurById(@Param("id") int id);

	@Query("SELECT u FROM Utilisateur u WHERE u.email = :email")
	Utilisateur getUserByEmail(@Param("email") String email);

	Optional<Utilisateur> findByEmailAndPassword(String email, String encodedPassword);

	Optional<Utilisateur> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

}
