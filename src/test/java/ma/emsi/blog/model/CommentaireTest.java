package ma.emsi.blog.model;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class CommentaireTest {


    private Commentaire commentaire;
    private Article article;
    private Visiteur visiteur;
    private Proprietaire proprietaire;
    private Commentaire commentaireParent;

    @Before
    public void setUp() {
        article = new Article(); // Assuming a constructor exists
        visiteur = new Visiteur(); // Assuming a constructor exists
        proprietaire = new Proprietaire(); // Assuming a constructor exists
        commentaireParent = new Commentaire();
        commentaire = new Commentaire();
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull("Commentaire instance should not be null", commentaire);
    }

    @Test
    public void testParameterizedConstructor() {
        LocalDate date = LocalDate.now();
        Commentaire paramCommentaire = new Commentaire(1, "Test Text", date, article, visiteur, proprietaire, commentaireParent);

        assertEquals("Id should be 1", 1, paramCommentaire.getId());
        assertEquals("Texte should be 'Test Text'", "Test Text", paramCommentaire.getTexte());
        assertEquals("Date should be today's date", date, paramCommentaire.getDate());
        assertSame("Article should be the same as set", article, paramCommentaire.getArticle());
        assertSame("Visiteur should be the same as set", visiteur, paramCommentaire.getVisiteur());
        assertSame("Proprietaire should be the same as set", proprietaire, paramCommentaire.getProprietaire());
        assertSame("Commentaire Parent should be the same as set", commentaireParent, paramCommentaire.getCommentaireParent());
    }

    @Test
    public void testSetAndGetId() {
        int expectedId = 10;
        commentaire.setId(expectedId);
        assertEquals("Getter or setter for id not working", expectedId, commentaire.getId());
    }

    @Test
    public void testSetAndGetTexte() {
        String expectedTexte = "New Comment";
        commentaire.setTexte(expectedTexte);
        assertEquals("Getter or setter for texte not working", expectedTexte, commentaire.getTexte());
    }

    // Similar tests for other getters and setters...

}