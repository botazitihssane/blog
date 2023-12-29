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

	private Validator validator;

	@Before
	public void setUp() {
		categorie = new Categorie();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testGetAndSetId() {
		int id = 1;
		categorie.setId(id);
		assertEquals(id, categorie.getId());
	}

	@Test
	public void testGetAndSetNom() {
		String nom = "Technology";
		categorie.setNom(nom);
		assertEquals(nom, categorie.getNom());
	}

	@Test
	public void testConstructorWithParameters() {
		int id = 1;
		String nom = "Technology";
		Categorie categorieWithParams = new Categorie(id, nom);

		assertEquals(id, categorieWithParams.getId());
		assertEquals(nom, categorieWithParams.getNom());
	}

	@Test
	public void testDefaultConstructor() {
		Categorie newCategorie = new Categorie();
		assertNotNull(newCategorie);
	}

	@Test
	public void whenNomNotBlank_thenNoConstraintViolations() {
		Categorie categorie = new Categorie();
		categorie.setNom("Technology");

		Set<ConstraintViolation<Categorie>> violations = validator.validate(categorie);

		assertTrue(violations.isEmpty());
	}

	@Test
	public void whenNomBlank_thenConstraintViolation() {
		Categorie categorie = new Categorie();
		categorie.setNom("");

		Set<ConstraintViolation<Categorie>> violations = validator.validate(categorie);

		assertFalse(violations.isEmpty());
		assertEquals(1, violations.size());
		assertEquals("Le nom ne peut pas etre vide", violations.iterator().next().getMessage());
	}
}
