package ma.emsi.blog.repository;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ma.emsi.blog.model.Commentaire;

public class CommentaireRepositoryTest {

    @Autowired
    private CommentaireRepository commentaireRepository;

    private Commentaire testCommentaire;

    @Before
    public void setUp() {
        // Create and save a Commentaire object for testing
        testCommentaire = new Commentaire();
        // Set properties of testCommentaire as required
        commentaireRepository.save(testCommentaire);
    }

    @Test
    public void whenFindByIdthenReturnCommentaire() {
        Commentaire found = commentaireRepository.findById(testCommentaire.getId());
        assertNotNull("Commentaire should not be null", found);
        // Add more assertions here to check other properties of found Commentaire
    }
}
