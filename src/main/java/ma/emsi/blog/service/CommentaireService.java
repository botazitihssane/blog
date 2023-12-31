package ma.emsi.blog.service;

import ma.emsi.blog.model.Commentaire;

import java.util.List;

public interface CommentaireService {
    void create(Commentaire c);

    void update(Commentaire c);

    void delete(int id);

    List<Commentaire> findAll();

    Commentaire findById(int id);

    List<Commentaire> findByArticle(int id);
}
