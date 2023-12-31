package ma.emsi.blog.service;

import ma.emsi.blog.model.Article;
import ma.emsi.blog.model.Commentaire;

import java.time.LocalDate;
import java.util.List;

public interface ArticleService {
    public void create(Article a);

    public void update(Article a);

    public void delete(int id);

    public List<Article> findAll();

    public Article findById(int id);

    public List<Article> sortByDate();

    public List<Article> sortByComments();

    public List<Article> findByDate(LocalDate date);

    public List<Article> searchByKeyword(String keyword);

    public List<Article> searchByCategory(int id);

    public void addCommentaire(Commentaire c);

    public void deleteCommentaire(Commentaire c);
}
