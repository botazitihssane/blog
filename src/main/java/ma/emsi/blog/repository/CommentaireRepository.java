package ma.emsi.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.emsi.blog.model.Commentaire;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire,Integer>{
	Commentaire findById(int id);
}
