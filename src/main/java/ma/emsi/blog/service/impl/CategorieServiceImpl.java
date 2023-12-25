package ma.emsi.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.emsi.blog.model.Categorie;
import ma.emsi.blog.repository.CategorieRepository;
import ma.emsi.blog.service.CategorieService;

@Service
public class CategorieServiceImpl implements CategorieService {

	@Autowired
	private CategorieRepository categorieRepository;

	@Override
	public void create(Categorie c) {
		categorieRepository.save(c);
	}

	@Override
	public void update(Categorie c) {
		Categorie newCategorie = findById(c.getId());
		if (newCategorie != null) {
			newCategorie.setNom(c.getNom());
			categorieRepository.save(newCategorie);
		}
	}

	@Override
	public void delete(int id) {
		categorieRepository.deleteById(id);
	}

	@Override
	public List<Categorie> findAll() {
		return categorieRepository.findAll();
	}

	@Override
	public Categorie findById(int id) {
		return categorieRepository.findById(id);
	}

	@Override
	public Categorie findByNom(String nom) {
		return categorieRepository.findByNom(nom);
	}

}
