package ma.emsi.blog.service.impl;

import ma.emsi.blog.model.Visiteur;
import ma.emsi.blog.repository.VisiteurRepository;
import ma.emsi.blog.service.VisiteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisiteurServiceImpl implements VisiteurService {

    @Autowired
    private VisiteurRepository visiteurRepository;

    @Override
    public void create(Visiteur visiteur) {
        visiteurRepository.save(visiteur);
    }

    @Override
    public Visiteur findById(int id) {
        return visiteurRepository.findById(id);
    }

    @Override
    public List<Visiteur> findAll() {
        return visiteurRepository.findAll();
    }

    @Override
    public Visiteur findByEmail(String email) {
        return visiteurRepository.findByEmail(email);
    }

    @Override
    public Visiteur findByNom(String nom) {
        return visiteurRepository.findByNom(nom);
    }

}
