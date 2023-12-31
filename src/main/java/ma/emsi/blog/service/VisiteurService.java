package ma.emsi.blog.service;

import ma.emsi.blog.model.Visiteur;

import java.util.List;

public interface VisiteurService {

    void create(Visiteur visiteur);

    Visiteur findById(int id);

    List<Visiteur> findAll();

    Visiteur findByEmail(String email);

    Visiteur findByNom(String nom);
}