package ma.emsi.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.emsi.blog.model.Proprietaire;

@Repository
public interface ProprietaireRepository extends JpaRepository<Proprietaire, Integer> {

	Proprietaire findById(int id);
}