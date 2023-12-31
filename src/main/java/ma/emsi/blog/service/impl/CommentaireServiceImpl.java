package ma.emsi.blog.service.impl;

import ma.emsi.blog.model.Commentaire;
import ma.emsi.blog.model.Visiteur;
import ma.emsi.blog.repository.CommentaireRepository;
import ma.emsi.blog.repository.VisiteurRepository;
import ma.emsi.blog.service.ArticleService;
import ma.emsi.blog.service.CommentaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentaireServiceImpl implements CommentaireService {

    @Autowired
    private CommentaireRepository commentaireRepository;

    @Autowired
    private VisiteurRepository visiteurRepository;

    @Autowired
    private ArticleService articleService;

    @Override
    public void create(Commentaire c) {
        if (c.getVisiteur() != null && c.getVisiteur().getEmail() != null) {
            Visiteur existingVisiteur = visiteurRepository.findByEmail(c.getVisiteur().getEmail());
            if (existingVisiteur == null) {
                Visiteur newVisiteur = new Visiteur();
                newVisiteur.setEmail(c.getVisiteur().getEmail());
                newVisiteur.setNom(c.getVisiteur().getNom());
                newVisiteur.setNombreCommentaire(1);
                visiteurRepository.save(newVisiteur);
                c.setVisiteur(newVisiteur);
            } else {
                c.setVisiteur(existingVisiteur);
                existingVisiteur.setNombreCommentaire(existingVisiteur.getNombreCommentaire() + 1);
                visiteurRepository.save(existingVisiteur);
            }
        }
        commentaireRepository.save(c);
        articleService.addCommentaire(c);
    }

    @Override
    public void update(Commentaire c) {
        Commentaire newCommentaire = findById(c.getId());
        if (newCommentaire != null) {
            newCommentaire.setDate(c.getDate());
            newCommentaire.setTexte(c.getTexte());
            commentaireRepository.save(newCommentaire);
        }
    }

    @Override
    public void delete(int id) {
        Commentaire c = findById(id);
        articleService.deleteCommentaire(c);
        commentaireRepository.deleteById(id);
    }

    @Override
    public List<Commentaire> findAll() {
        return commentaireRepository.findAll();
    }

    @Override
    public Commentaire findById(int id) {
        return commentaireRepository.findById(id);
    }

    @Override
    public List<Commentaire> findByArticle(int id) {
        return commentaireRepository.findByArticle(id);
    }

}
