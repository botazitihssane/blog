package ma.emsi.blog.repository;

import ma.emsi.blog.model.Visiteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisiteurRepository extends JpaRepository<Visiteur, Integer> {
    Visiteur findByEmail(String email);

    Visiteur findById(int id);

    Visiteur findByNom(String nom);
}