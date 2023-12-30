package ma.emsi.blog.service;

import java.util.List;

import ma.emsi.blog.model.Proprietaire;

public interface ProprietaireService {

	public void create(Proprietaire p);

	public void update(Proprietaire p);

	public void delete(int id);

	public List<Proprietaire> findAll();

	public Proprietaire findById(int id);

	public boolean existsById(int id);
}
