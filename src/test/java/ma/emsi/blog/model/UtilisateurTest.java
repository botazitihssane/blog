package ma.emsi.blog.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UtilisateurTest {
	private Utilisateur utilisateur;

	@Before
	public void setUp() {
		utilisateur = new Utilisateur();
	}

	@Test
	public void testDefaultConstructor() {
		assertNotNull("Utilisateur instance should not be null", utilisateur);
	}

	@Test
	public void testParameterizedConstructorWithId() {
		int id = 1;
		String email = "test@example.com";
		String password = "password123";
		String username = "testUser";

		Utilisateur paramUtilisateur = new Utilisateur(id, email, password, username);

		assertEquals("Id should be 1", 1, paramUtilisateur.getId());
		assertEquals("Email should match", email, paramUtilisateur.getEmail());
		assertEquals("Password should match", password, paramUtilisateur.getPassword());
		assertEquals("Username should match", username, paramUtilisateur.getUsername());
	}

	@Test
	public void testParameterizedConstructorWithoutId() {
		String email = "test@example.com";
		String password = "password123";
		String username = "testUser";

		Utilisateur paramUtilisateur = new Utilisateur(email, password, username);

		assertEquals("Email should match", email, paramUtilisateur.getEmail());
		assertEquals("Password should match", password, paramUtilisateur.getPassword());
		assertEquals("Username should match", username, paramUtilisateur.getUsername());
	}

	@Test
	public void testSetAndGetId() {
		int expectedId = 10;
		utilisateur.setId(expectedId);
		assertEquals("Getter or setter for id not working", expectedId, utilisateur.getId());
	}

	@Test
	public void testSetAndGetEmail() {
		String expectedEmail = "newemail@example.com";
		utilisateur.setEmail(expectedEmail);
		assertEquals("Getter or setter for email not working", expectedEmail, utilisateur.getEmail());
	}

	@Test
	public void testSetAndGetPassword() {
		String expectedPassword = "newPassword123";
		utilisateur.setPassword(expectedPassword);
		assertEquals("Getter or setter for password not working", expectedPassword, utilisateur.getPassword());
	}

	@Test
	public void testSetAndGetUsername() {
		String expectedUsername = "newUsername";
		utilisateur.setUsername(expectedUsername);
		assertEquals("Getter or setter for username not working", expectedUsername, utilisateur.getUsername());
	}
}