package ma.emsi.blog.repository;

import ma.emsi.blog.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    public Article findById(int id);

    public List<Article> findByDate(LocalDate date);

    @Query("SELECT a FROM Article a ORDER BY a.nombreCommentaire DESC")
    public List<Article> sortByComments();

    @Query("SELECT a FROM Article a ORDER BY a.date DESC")
    public List<Article> sortByDate();

    @Query("SELECT a FROM Article a WHERE LOWER(a.titre) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(a.texte) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Article> searchByKeyword(@Param("keyword") String keyword);

    @Query("SELECT a FROM Article a WHERE a.categorie.id = :id")
    List<Article> findByCategory(@Param("id") int id);
}
