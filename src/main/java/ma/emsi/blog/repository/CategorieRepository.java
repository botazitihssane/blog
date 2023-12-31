package ma.emsi.blog.repository;

import ma.emsi.blog.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Integer> {
    Optional<Categorie> findById(int id);

    Optional<Categorie> findByNom(String nom);
}
