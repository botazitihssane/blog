package ma.emsi.blog.model;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

public class UtilisateurTest {

	private Validator validator;

	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testUtilisateurValidation() {
		// Given
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setEmail("");
		utilisateur.setPassword("pass");
		utilisateur.setUsername("");

		// When
		Set<ConstraintViolation<Utilisateur>> violations = validator.validate(utilisateur);

		// Then
		assertFalse(violations.isEmpty());
		assertEquals(3, violations.size());

		for (ConstraintViolation<Utilisateur> violation : violations) {
			System.out.println(violation.getPropertyPath() + " " + violation.getMessage());
		}
	}

	@Test
    public void testUtilisateurConstructor() {
		Utilisateur utilisateur = new Utilisateur("test@example.com", "password123", "username");

		assertEquals("test@example.com", utilisateur.getEmail());
		assertEquals("password123", utilisateur.getPassword());
		assertEquals("username", utilisateur.getUsername());
	}
}
