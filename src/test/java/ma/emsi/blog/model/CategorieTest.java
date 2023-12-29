package ma.emsi.blog.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import jakarta.validation.ValidatorFactory;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ConstraintViolation;
import java.util.Set;

public class CategorieTest {

	private Categorie categorie;

	@Before
	public void setUp() {
		categorie = new Categorie();
	}

	@Test
	public void testBlankNomValidation() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Categorie categorie = new Categorie();
		categorie.setNom("");
		Set<ConstraintViolation<Categorie>> violations = validator.validate(categorie);

		assertFalse("Nom should not be valid", violations.isEmpty());
		for (ConstraintViolation<Categorie> violation : violations) {
			assertEquals("Le nom ne peut pas etre vide", violation.getMessage());
		}
	}

	@Test
	public void whenAllArgsConstructorUsed_thenPropertiesAreSet() {
		int expectedId = 1;
		String expectedNom = "Test Category";
		Categorie categorie = new Categorie(expectedId, expectedNom);
		assertEquals(expectedId, categorie.getId());
		assertEquals(expectedNom, categorie.getNom());
	}

	@Test
	public void testGetId() {
		categorie.setId(1);
		assertEquals(1, categorie.getId());
	}

	@Test
	public void testSetId() {
		categorie.setId(1);
		assertEquals(1, categorie.getId());
	}

	@Test
	public void testGetNom() {
		categorie.setNom("Test Nom");
		assertEquals("Test Nom", categorie.getNom());
	}

	@Test
	public void testSetNom() {
		categorie.setNom("Test Nom");
		assertEquals("Test Nom", categorie.getNom());
	}
}
