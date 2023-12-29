package ma.emsi.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.emsi.blog.model.Categorie;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Integer> {
	Optional<Categorie> findById(int id);

	Optional<Categorie> findByNom(String nom);
}
