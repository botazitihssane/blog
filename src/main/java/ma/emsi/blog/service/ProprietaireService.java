package ma.emsi.blog.service;

import ma.emsi.blog.model.Proprietaire;

import java.util.List;

public interface ProprietaireService {

    public void create(Proprietaire p);

    public void update(Proprietaire p);

    public void delete(int id);

    public List<Proprietaire> findAll();

    public Proprietaire findById(int id);

    public boolean existsById(int id);
}
