package ma.emsi.blog.service;

import java.util.List;

import ma.emsi.blog.model.Categorie;

public interface CategorieService {
	public void create(Categorie c);

	public void update(Categorie c);

	public void delete(int id);

	public List<Categorie> findAll();

	public Categorie findById(int id);

	public Categorie findByNom(String nom);
}
