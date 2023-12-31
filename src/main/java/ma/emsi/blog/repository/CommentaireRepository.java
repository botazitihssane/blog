package ma.emsi.blog.repository;

import ma.emsi.blog.model.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Integer> {
    Commentaire findById(int id);

    @Query("SELECT a FROM Commentaire a WHERE a.article.id = :id")
    List<Commentaire> findByArticle(@Param("id") int id);
}
