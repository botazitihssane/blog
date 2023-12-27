package ma.emsi.blog.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CategorieTest {

	

	    private Categorie categorie;

	    @Before
	    public void setUp() {
	        categorie = new Categorie();
	    }

	    @Test
	    public void testDefaultConstructor() {
	        assertNotNull("Categorie instance should not be null", categorie);
	    }

	    @Test
	    public void testParameterizedConstructor() {
	        Categorie paramCategorie = new Categorie(1, "TestCategory");
	        assertEquals("Id should be 1", 1, paramCategorie.getId());
	        assertEquals("Nom should be 'TestCategory'", "TestCategory", paramCategorie.getNom());
	    }

	    @Test
	    public void testSetAndGetId() {
	        int expectedId = 10;
	        categorie.setId(expectedId);
	        assertEquals("Getter or setter for id not working", expectedId, categorie.getId());
	    }

	    @Test
	    public void testSetAndGetNom() {
	        String expectedNom = "NewCategory";
	        categorie.setNom(expectedNom);
	        assertEquals("Getter or setter for nom not working", expectedNom, categorie.getNom());
	    }
	}
