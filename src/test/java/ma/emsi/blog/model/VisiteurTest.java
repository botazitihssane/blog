package ma.emsi.blog.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VisiteurTest {

	  private Visiteur visiteur;

	    @Before
	    public void setUp() {
	        visiteur = new Visiteur();
	    }

	    @Test
	    public void testDefaultConstructor() {
	        assertNotNull("Visiteur instance should not be null", visiteur);
	    }

	    @Test
	    public void testParameterizedConstructor() {
	        int id = 1;
	        String nom = "Test Nom";
	        String email = "test@example.com";
	        int nombreCommentaire = 5;

	        Visiteur paramVisiteur = new Visiteur(id, nom, email, nombreCommentaire);

	        assertEquals("Id should be 1", 1, paramVisiteur.getId());
	        assertEquals("Nom should match", nom, paramVisiteur.getNom());
	        assertEquals("Email should match", email, paramVisiteur.getEmail());
	        assertEquals("Nombre de commentaire should be 5", 5, paramVisiteur.getNombreCommentaire());
	    }

	    @Test
	    public void testSetAndGetId() {
	        int expectedId = 10;
	        visiteur.setId(expectedId);
	        assertEquals("Getter or setter for id not working", expectedId, visiteur.getId());
	    }

	    @Test
	    public void testSetAndGetNom() {
	        String expectedNom = "New Nom";
	        visiteur.setNom(expectedNom);
	        assertEquals("Getter or setter for nom not working", expectedNom, visiteur.getNom());
	    }

	    @Test
	    public void testSetAndGetEmail() {
	        String expectedEmail = "newemail@example.com";
	        visiteur.setEmail(expectedEmail);
	        assertEquals("Getter or setter for email not working", expectedEmail, visiteur.getEmail());
	    }

	    @Test
	    public void testSetAndGetNombreCommentaire() {
	        int expectedNombreCommentaire = 10;
	        visiteur.setNombreCommentaire(expectedNombreCommentaire);
	        assertEquals("Getter or setter for nombreCommentaire not working", expectedNombreCommentaire, visiteur.getNombreCommentaire());
	    }
	}