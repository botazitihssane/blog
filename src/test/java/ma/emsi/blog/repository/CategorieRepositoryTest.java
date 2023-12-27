package ma.emsi.blog.repository;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ma.emsi.blog.model.Categorie;


public class CategorieRepositoryTest {

    @Autowired
    private CategorieRepository categorieRepository;

    private Categorie testCategorie;

    @Before
    public void setUp() {
        // Create and save a Categorie object for testing
        testCategorie = new Categorie();
        testCategorie.setNom("TestCategory");
        categorieRepository.save(testCategorie);
    }

    @Test
    public void whenFindById_thenReturnCategorie() {
        Categorie found = categorieRepository.findById(testCategorie.getId());
        assertNotNull("Categorie should not be null", found);
        assertEquals("Categorie name should match", testCategorie.getNom(), found.getNom());
    }

    @Test
    public void whenFindByNom_thenReturnCategorie() {
        Categorie found = categorieRepository.findByNom(testCategorie.getNom());
        assertNotNull("Categorie should not be null", found);
        assertEquals("Categorie ID should match", testCategorie.getId(), found.getId());
    }
}
