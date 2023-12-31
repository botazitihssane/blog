package ma.emsi.blog.service.impl;

import ma.emsi.blog.model.Categorie;
import ma.emsi.blog.repository.CategorieRepository;
import ma.emsi.blog.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieServiceImpl implements CategorieService {

    @Autowired
    private CategorieRepository categorieRepository;

    @Override
    public Categorie create(Categorie c) {
        return categorieRepository.save(c);
    }

    @Override
    public Categorie update(Categorie c) {
        Optional<Categorie> existingCategorie = findById(c.getId());
        if (existingCategorie.isPresent()) {
            Categorie updatedCategorie = existingCategorie.get();
            updatedCategorie.setNom(c.getNom());
            return categorieRepository.save(updatedCategorie);
        }
        return null;
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
    public Optional<Categorie> findById(int id) {
        return categorieRepository.findById(id);
    }

    @Override
    public Optional<Categorie> findByNom(String nom) {
        return categorieRepository.findByNom(nom);
    }

}
