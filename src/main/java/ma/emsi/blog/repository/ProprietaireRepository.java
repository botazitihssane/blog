package ma.emsi.blog.repository;

import ma.emsi.blog.model.Proprietaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProprietaireRepository extends JpaRepository<Proprietaire, Integer> {
    Proprietaire findById(int id);
}