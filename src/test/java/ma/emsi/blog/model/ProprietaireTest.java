package ma.emsi.blog.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProprietaireTest {

	private Proprietaire proprietaire;

    @Before
    public void setUp() {
        proprietaire = new Proprietaire();
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull("Proprietaire instance should not be null", proprietaire);
    }

    @Test
    public void testParameterizedConstructor() {
        String email = "test@email.com";
        String password = "password123";
        String username = "testUser";
        String photo = "testPhoto.jpg";
        String biographie = "Test biography";

        Proprietaire paramProprietaire = new Proprietaire(email, password, username, photo, biographie);

        assertEquals("Email should match", email, paramProprietaire.getEmail());
        assertEquals("Password should match", password, paramProprietaire.getPassword());
        assertEquals("Username should match", username, paramProprietaire.getUsername());
        assertEquals("Photo should match", photo, paramProprietaire.getPhoto());
        assertEquals("Biographie should match", biographie, paramProprietaire.getBiographie());
    }

    @Test
    public void testSetAndGetPhoto() {
        String expectedPhoto = "newPhoto.jpg";
        proprietaire.setPhoto(expectedPhoto);
        assertEquals("Getter or setter for photo not working", expectedPhoto, proprietaire.getPhoto());
    }

    @Test
    public void testSetAndGetBiographie() {
        String expectedBiographie = "New biography";
        proprietaire.setBiographie(expectedBiographie);
        assertEquals("Getter or setter for biographie not working", expectedBiographie, proprietaire.getBiographie());
    }

    // Additional tests for inherited properties and methods from Utilisateur...

}