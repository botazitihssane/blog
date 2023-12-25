package ma.emsi.blog.service;

import java.util.List;

import ma.emsi.blog.model.Visiteur;

public interface VisiteurService {

	void create(Visiteur visiteur);

	Visiteur findById(int id);

	List<Visiteur> findAll();
	
	Visiteur findByEmail(String email);
	
	Visiteur findByNom(String nom);
}