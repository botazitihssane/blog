package ma.emsi.blog.service;

import java.util.List;
import java.util.Optional;

import ma.emsi.blog.model.Categorie;

public interface CategorieService {
	public Categorie create(Categorie c);

	public Categorie update(Categorie c);

	public void delete(int id);

	public List<Categorie> findAll();

	public Optional<Categorie> findById(int id);

	public Optional<Categorie> findByNom(String nom);
}
